package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.adapter.EmployeeListAdapter;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.activity.shop.ShopEmployeeActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopListActivity;
import com.mtecc.mmp.invoicing.activity.shop.adapter.ShopSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.R.attr.type;
import static com.mtecc.mmp.invoicing.base.InvoicingConstants.shopId;
import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

public class EmployeeListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.iv_bar_search)
    RelativeLayout ivBarSearch;
    @BindView(R.id.et_serch)
    EditText etSerch;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_serch_bar)
    LinearLayout rlSerchBar;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.shop_list_recycler_view)
    SwipeMenuListView shopListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private int pagenum = 1;
    List<EmployeeListBean.DataBean> mList = new ArrayList<>();
    private EmployeeListAdapter adapter;
    private String cid;
    private boolean isPause = false;
    private String shop_id = "";

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0) + "";
        shop_id = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        parms = getIntent().getExtras();
        if (parms != null) {
            //全部员工
            tvTitle.setText("员工列表");
            requestNetEmployeeList(pagenum + "", cid, shop_id);
            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // 创建“打开”项
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    openItem.setBackground(new ColorDrawable(Color.parseColor("#0099FF")));
                    openItem.setWidth(dp2px(100));
                    openItem.setTitle("编辑");
                    openItem.setTitleSize(18);
                    openItem.setTitleColor(Color.WHITE);
                    // 将创建的菜单项添加进菜单中
                    menu.addMenuItem(openItem);

                    // 创建“删除”项
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                    deleteItem.setWidth(dp2px(100));
                    deleteItem.setTitle("删除");
                    deleteItem.setTitleSize(18);
                    deleteItem.setTitleColor(Color.WHITE);
                    // 将创建的菜单项添加进菜单中
                    menu.addMenuItem(deleteItem);
                }
            };
            shopListRecyclerView.setMenuCreator(creator);
            shopListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            adapter = new EmployeeListAdapter(this, mList);
            shopListRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_employee_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        //设置recycleView的布局管理器
//        shopListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
                mList.clear();
                pagenum = 1;

                requestNetEmployeeList(pagenum + "", cid + "", shop_id);


                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;

                requestNetEmployeeList(pagenum + "", cid + "", shop_id);


            }
        });

        shopListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                EmployeeListBean.DataBean dataBean = mList.get(position);
                if (title.equals("编辑")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(InvoicingConstants.EMPLOYEE_TYPE, InvoicingConstants.EMPLOYEE_EDIT);
                    bundle.putSerializable(InvoicingConstants.EMPLOYEE_userId, dataBean);
                    intent.setClass(EmployeeListActivity.this, EmployeeManagemnetActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else if (title.equals("删除")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(EmployeeListActivity.this, R.layout.exit_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(EmployeeListActivity.this, false, customizeDialog);
                    dialogClick(customizeDialog, alertDialog, dataBean.getUserid() + "");
                }
                return false;
            }
        });

        shopListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmployeeListBean.DataBean dataBean = mList.get(position);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(InvoicingConstants.selectuserid, dataBean);
                bundle.putString(InvoicingConstants.Employee_List_TYPE, InvoicingConstants.companyEmployeeAdd);
                //TODO:跳转查看员工详情
                intent.setClass(EmployeeListActivity.this, EmployeeSeeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch, R.id.img_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_bar_search:
                //点击搜索图标
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                break;
            case R.id.tv_search:
                //点击搜索

                break;
            case R.id.iv_serch:
                //点击叉号
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;
            case R.id.img_select:
                //点击加号
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.EMPLOYEE_TYPE, InvoicingConstants.EMPLOYEE_ADD);
                intent.setClass(this, EmployeeManagemnetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


                break;
        }
    }


    /**
     * 查看公司员工
     *
     * @param page
     * @param id
     */
    private void requestNetEmployeeList(String page, String id, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employee_listfortable_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + id);
        OkHttpUtils.post().tag(this)
                .addParams("page", page)
                .addParams("cid", id)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息EmployeeListActivity" + e.toString());
                            Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息EmployeeListActivity" + response.toString());
                            Gson gson = new Gson();
                            EmployeeListBean employeeListBean = gson.fromJson(response, EmployeeListBean.class);
                            if (employeeListBean != null) {
                                List<EmployeeListBean.DataBean> dataList = employeeListBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        adapter.notifyDataSetChanged();
                                        shopListRecyclerView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            shopListRecyclerView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            showToast("暂无员工,请进行添加!");
                                        }
                                    }

                                } else {
                                    shopListRecyclerView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeListActivity" + e1.toString());
                            Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause) {
            smartRefreshLayout.autoRefresh();
        }
    }

    /**
     * 删除对话框
     *
     * @param customizeDialog
     * @param alertDialog
     * @param userId
     */
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog, final String userId) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvContant = customizeDialog.findViewById(R.id.dialog_tv_contant);
        tvContant.setText("是否删除员工?");
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的是
                alertDialog.dismiss();

                requestNetDelEmployeeList(userId);
//                if (type.equals(InvoicingConstants.companyEmployeeAdd)) {
//                    //删除员工
//                } else if (type.equals(InvoicingConstants.SHOP_Employee)) {
//                    //删除店铺员工
//                    requestNetShopEmployeeList(selectDataBean.getUserid() + "", shopId);
//
//                }
            }
        });
    }

    /**
     * 删除员工
     *
     * @param userid
     */
    private void requestNetDelEmployeeList(String userid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employeedelete_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + userid);
        OkHttpUtils.post().tag(this)
                .addParams("userid", userid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e.toString());
                            Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息EmployeeSeeActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    Toast.makeText(EmployeeListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    Toast.makeText(EmployeeListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                            Toast.makeText(EmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
