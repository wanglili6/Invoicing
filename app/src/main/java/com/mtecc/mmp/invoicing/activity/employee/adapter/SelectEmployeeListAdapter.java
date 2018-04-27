package com.mtecc.mmp.invoicing.activity.employee.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.SelectEmployeeListActivity;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.activity.shop.ShopListActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by wll on 2018/4/18.
 */

public class SelectEmployeeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<EmployeeListBean.DataBean> mList;
    private TextView tvSelectNames;
    private TextView tvSelectSure;
    private List<EmployeeListBean.DataBean> shopEmployeeList;
    private ListView shopListRecyclerView;
    private String shopId;
    String userStr = "";
    List<EmployeeListBean.DataBean> sList = new ArrayList<>();

    public SelectEmployeeListAdapter(Context mContext, List<EmployeeListBean.DataBean> mList,
                                     ListView shopListRecyclerView,
                                     List<EmployeeListBean.DataBean> shopEmployeeList,
                                     TextView tvSelectNames,
                                     TextView tvSelectSure,
                                     String shopId) {
        this.mContext = mContext;
        this.mList = mList;
        this.tvSelectNames = tvSelectNames;
        this.tvSelectSure = tvSelectSure;
        this.shopEmployeeList = shopEmployeeList;
        this.shopListRecyclerView = shopListRecyclerView;
        this.shopId = shopId;

    }


    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EmployeeViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.employee_list_iteam, parent, false);
            holder = new EmployeeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (EmployeeViewHolder) convertView.getTag();
        }

        final EmployeeListBean.DataBean dataBean = mList.get(position);
        holder.employeeTvName.setText(dataBean.getUsername() + "(" + dataBean.getRole() + ")");
        holder.employeeTvCode.setText(dataBean.getCardnum());
        holder.employeeTvPhone.setText(dataBean.getTelphone());
        holder.employeeTvStatus.setText(dataBean.getEmpstate());
        holder.employeeTvShop.setText(dataBean.getEmpstate());
        holder.checkBoxSelect.setVisibility(View.VISIBLE);
        holder.checkBoxSelect.setClickable(false);
        String empstate = dataBean.getEmpstate();
        final EmployeeViewHolder finalHolder = holder;
        if (empstate.equals("0")) {
            holder.employeeTvStatus.setText("正常");
        } else {
            holder.employeeTvStatus.setText("注销");
        }
        for (int i = 0; i < shopEmployeeList.size(); i++) {
            if (dataBean.getUserid() == shopEmployeeList.get(i).getUserid()) {
                shopListRecyclerView.setItemChecked(position, true);
                holder.checkBoxSelect.setEnabled(false);
            }
        }
        final boolean itemChecked = shopListRecyclerView.isItemChecked(position);
        holder.checkBoxSelect.setChecked(itemChecked);
        holder.shopListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < shopEmployeeList.size(); i++) {
                    if (dataBean.getUserid() == shopEmployeeList.get(i).getUserid()) {
                        Toast.makeText(mContext, "不能重复添加店员!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (itemChecked) {
                    shopListRecyclerView.setItemChecked(position, false);
                } else {
                    shopListRecyclerView.setItemChecked(position, true);
                }
            }
        });

        holder.checkBoxSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < shopEmployeeList.size(); i++) {
                    if (dataBean.getUserid() == shopEmployeeList.get(i).getUserid()) {
                        Toast.makeText(mContext, "不能重复添加店员!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                LogUtils.i("lalalla" + "CheckedItemCount = " + shopListRecyclerView.getCheckedItemCount());
                LogUtils.i("lalalla" + "CheckedItemCount = " + shopListRecyclerView.getCheckedItemPositions());
                shopListRecyclerView.setItemChecked(position, finalHolder.checkBoxSelect.isChecked());
            }
        });

        holder.checkBoxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sList.add(dataBean);
                } else {
                    sList.remove(dataBean);
                }
            }
        });
        String names = "";
        userStr = "";
        for (int i = 0; i < sList.size(); i++) {
            EmployeeListBean.DataBean bean = sList.get(i);
            names = bean.getUsername() + "," + names;
            userStr = bean.getUserid() + "," + userStr;
        }
        tvSelectNames.setText(names);
        notifyDataSetChanged();

        tvSelectSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userStr)) {
                    Toast.makeText(mContext, "没有选择任何人员!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    requestNetBindMan();

                }
            }


        });
        return convertView;
    }

    private void requestNetBindMan() {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.bindMan_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + shopId);
        LogUtils.d("登陆的url" + userStr);
        OkHttpUtils.post().tag(this)
                .addParams("userStr", userStr)
                .addParams("shopid", shopId)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息SelectEmployeeListAdapter" + e.toString());
                            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SelectEmployeeListAdapter" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息SelectEmployeeListAdapter" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                                    SelectEmployeeListActivity selectEmployeeListActivity = (SelectEmployeeListActivity) mContext;
                                    selectEmployeeListActivity.finish();
                                } else {
                                    Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SelectEmployeeListAdapter" + e1.toString());
                            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    static class EmployeeViewHolder {
        @BindView(R.id.employee_img)
        ImageView employeeImg;
        @BindView(R.id.employee_tv_name)
        TextView employeeTvName;
        @BindView(R.id.employee_tv_code)
        TextView employeeTvCode;
        @BindView(R.id.employee_tv_phone)
        TextView employeeTvPhone;
        @BindView(R.id.employee_tv_status)
        TextView employeeTvStatus;
        @BindView(R.id.employee_tv_shop)
        TextView employeeTvShop;
        @BindView(R.id.check_box_select)
        CheckBox checkBoxSelect;
        @BindView(R.id.shop_list_ll)
        LinearLayout shopListLl;

        EmployeeViewHolder(View view) {

            ButterKnife.bind(this, view);
        }
    }
}
