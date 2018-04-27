package com.mtecc.mmp.invoicing.activity.employee.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.SelectEmployeeListActivity;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by wll on 2018/4/18.
 */

public class SelectEmployeedialogListAdapter extends BaseAdapter {
    private Context mContext;
    private List<EmployeeListBean.DataBean> mList;
    private TextView tvSelectNames;
    private TextView tvSelectSure;
    private List<EmployeeListBean.DataBean> shopEmployeeList;
    private String shopId;
    private SmartRefreshLayout adapter;
    private AlertDialog alertDialog;
    String userStr = "";
    String names = "";
    HashMap<Integer, EmployeeListBean.DataBean> stateProfession = new HashMap<Integer, EmployeeListBean.DataBean>();
    HashMap<Integer, EmployeeListBean.DataBean> noSelctMap = new HashMap<Integer, EmployeeListBean.DataBean>();

    public SelectEmployeedialogListAdapter(Context mContext, List<EmployeeListBean.DataBean> mList,
                                           List<EmployeeListBean.DataBean> shopEmployeeList,
                                           TextView tvSelectNames,
                                           TextView tvSelectSure,
                                           String shopId,
                                           SmartRefreshLayout adapter,
                                           AlertDialog alertDialog) {
        this.mContext = mContext;
        this.mList = mList;
        this.tvSelectNames = tvSelectNames;
        this.tvSelectSure = tvSelectSure;
        this.shopEmployeeList = shopEmployeeList;
        this.shopId = shopId;
        this.adapter = adapter;
        this.alertDialog = alertDialog;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.employee_dialog_list_iteam, parent, false);
            holder = new EmployeeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (EmployeeViewHolder) convertView.getTag();
        }
        final EmployeeListBean.DataBean dataBean = mList.get(position);
        for (int i = 0; i < shopEmployeeList.size(); i++) {
            if (dataBean.getUserid() == shopEmployeeList.get(i).getUserid()) {
                noSelctMap.put(position, mList.get(position));
            }
        }
        holder.checkBoxSelect.setEnabled((noSelctMap.get(position) == null ? true : false));
        holder.employeeTvName.setText(dataBean.getUsername());

        final EmployeeViewHolder finalHolder = holder;
        holder.shopListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < shopEmployeeList.size(); i++) {
                    if (dataBean.getUserid() == shopEmployeeList.get(i).getUserid()) {
                        Toast.makeText(mContext, "不能重复添加店员!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (finalHolder.checkBoxSelect.isChecked()) {
                    finalHolder.checkBoxSelect.setChecked(false);
                } else {
                    finalHolder.checkBoxSelect.setChecked(true);
                }
            }
        });


        holder.checkBoxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                names = "";
                userStr = "";
                if (isChecked) {
                    stateProfession.put(position, mList.get(position));
                } else {
                    stateProfession.remove(position);
                }
                Iterator<Integer> iterator = stateProfession.keySet().iterator();
                while (iterator.hasNext()) {
                    Integer next = iterator.next();
                    EmployeeListBean.DataBean bean = stateProfession.get(next);
                    names = bean.getUsername() + "," + names;
                    userStr = bean.getUserid() + "," + userStr;
                }
                LogUtils.i("选择的人员" + names);
                tvSelectNames.setText(names);
            }
        });
        holder.checkBoxSelect.setChecked((stateProfession.get(position) == null ? false : true));

        tvSelectSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userStr)) {
                    Toast.makeText(mContext, "没有选择任何人员!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
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
                                    adapter.autoRefresh();
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
        @BindView(R.id.check_box_select)
        CheckBox checkBoxSelect;
        @BindView(R.id.shop_list_ll)
        RelativeLayout shopListLl;


        EmployeeViewHolder(View view) {

            ButterKnife.bind(this, view);
        }
    }
}
