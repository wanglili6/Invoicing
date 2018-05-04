package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.adapter.EmployeeListAdapter;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    RecyclerView shopListRecyclerView;
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
            requestNetEmployeeList(pagenum + "", cid);
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
        shopListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
                mList.clear();
                pagenum = 1;

                requestNetEmployeeList(pagenum + "", cid + "");


                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;

                requestNetEmployeeList(pagenum + "", cid + "");


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
    private void requestNetEmployeeList(String page, String id) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employee_listfortable_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + id);
        OkHttpUtils.post().tag(this)
                .addParams("page", page)
                .addParams("cid", id)
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
}
