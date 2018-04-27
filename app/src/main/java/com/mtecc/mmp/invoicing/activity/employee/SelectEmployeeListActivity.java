package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.adapter.SelectEmployeeListAdapter;
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

public class SelectEmployeeListActivity extends BaseActivity {
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
    ListView shopListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_select_names)
    TextView tvSelectNames;
    @BindView(R.id.tv_select_sure)
    TextView tvSelectSure;
    private String employee_list_type = "";
    private int pagenum = 1;
    List<EmployeeListBean.DataBean> mList = new ArrayList<>();
    private SelectEmployeeListAdapter adapter;
    private String shopId;
    private String cid;
    private List<EmployeeListBean.DataBean> shopEmployeeList;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        shopEmployeeList = (List<EmployeeListBean.DataBean>) parms.getSerializable(InvoicingConstants.shopEmployeeSelect);
        shopId =  parms.getString(InvoicingConstants.shopId);
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0) + "";
        //全部员工
        tvTitle.setText("员工列表");
        requestNetEmployeeList(pagenum + "", cid);
        shopListRecyclerView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new SelectEmployeeListAdapter(this, mList, shopListRecyclerView, shopEmployeeList,tvSelectNames,tvSelectSure,shopId);
        shopListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
                mList.clear();
                pagenum = 1;
                requestNetEmployeeList(pagenum + "", cid);
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetEmployeeList(pagenum + "", cid);
            }
        });


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_select_employee_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        //设置recycleView的布局管理器
    }

    @Override
    public void setListener() {

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
                bundle.putString(InvoicingConstants.EMPLOYEE_TYPE, InvoicingConstants.companyEmployeeAdd);
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
        String url = "";
        url = InvoicingConstants.BASE_URL + InvoicingConstants.employee_listfortable_URL;
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
                            Toast.makeText(SelectEmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                    adapter.notifyDataSetChanged();
                                    shopListRecyclerView.setVisibility(View.VISIBLE);
                                    tvError.setVisibility(View.GONE);

                                } else {
                                    shopListRecyclerView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(SelectEmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeListActivity" + e1.toString());
                            Toast.makeText(SelectEmployeeListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
