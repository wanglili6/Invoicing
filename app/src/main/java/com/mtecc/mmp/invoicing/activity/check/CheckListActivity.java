package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 采购审核
 */
public class CheckListActivity extends BaseActivity {

    @BindView(R.id.iv_bar_search)
    RelativeLayout ivBarSearch;
    @BindView(R.id.et_serch)
    EditText etSerch;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.rl_serch_bar)
    LinearLayout rlSerchBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ll_seach)
    LinearLayout llSeach;
    @BindView(R.id.img_commodity_list_saoma)
    ImageView imgCommodityListSaoma;
    @BindView(R.id.purchase_list_recycler_view)
    ListView purchaseListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private CheckListAdapter adapter;
    int pagenum = 1;
    String limit = "20";
    String shopname = "";
    String shopStatus = "";
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private String hdid = "";
    private String SHOP_ID = "";
    private boolean isPause = false;
    List<PrichaseIncomeBean.DataBean> mList = new ArrayList<>();
    private String type = "";
    private String status = "0";
    //销货订单审核
    private String state = "";
    private String url;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        type = parms.getString(InvoicingConstants.check_type);
        if (type.equals(InvoicingConstants.check_purchases)) {
            tvTitle.setText("采购审核");
            state = "2";

        } else if (type.equals(InvoicingConstants.check_sales)) {
            tvTitle.setText("销售审核");
            state = "2";
        }
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(CheckListActivity.this, InvoicingConstants.QY_ID, 0);
        SHOP_ID = PreferencesUtils.getString(CheckListActivity.this, InvoicingConstants.SHOP_ID, "");
        requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID, state);
        adapter = new CheckListAdapter(CheckListActivity.this, mList, type, status);
        purchaseListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_purchase_check_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pagenum = 1;
                mList.clear();
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID, state);
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pagenum++;
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID, state);
                adapter.notifyDataSetChanged();
            }
        });


        purchaseListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrichaseIncomeBean.DataBean dataBean = mList.get(position);
                Intent incomeintent = new Intent();
                Bundle incomebundle = new Bundle();
                incomeintent.setClass(CheckListActivity.this, CheckActivity.class);
                if (type.equals(InvoicingConstants.check_purchases)) {
                    incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                } else if (type.equals(InvoicingConstants.check_sales)) {
                    incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                }
                incomebundle.putSerializable(InvoicingConstants.check_id, (Serializable) dataBean);
                incomeintent.putExtras(incomebundle);
                startActivity(incomeintent);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    /**
     * 订单列表
     *
     * @param page
     * @param cid
     * @param shopid
     */
    private void requestNetEnterGoodsList(String page, String hdid, String cid, String shopid, String state) {
        if (type.equals(InvoicingConstants.check_purchases)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.listfortable_URL;
        } else if (type.equals(InvoicingConstants.check_sales)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.outlistfortable_URL;
        }
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("state", state)
                .addParams("hdid", hdid)//订单编号
                .addParams("cid", cid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        try {
                            LogUtils.d("错误信息CheckListActivity" + e.toString());
                            Toast.makeText(CheckListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息CheckListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        try {
                            LogUtils.d("返回值信息CheckListActivity" + response.toString());
                            Gson gson = new Gson();
                            PrichaseIncomeBean prichaseIncomeBean = gson.fromJson(response, PrichaseIncomeBean.class);
                            if (prichaseIncomeBean != null) {
                                List<PrichaseIncomeBean.DataBean> dataList = prichaseIncomeBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            Toast.makeText(CheckListActivity.this, "没有更多数据!", Toast.LENGTH_SHORT).show();
                                        }
                                        adapter.notifyDataSetChanged();
                                        llList.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            llList.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            Toast.makeText(CheckListActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    llList.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    Toast.makeText(CheckListActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(CheckListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CheckListActivity" + e1.toString());
                            Toast.makeText(CheckListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_bar_search:
                //点击搜索图标
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                awayKetBordUtils.showKeyboard(etSerch);
                break;
            case R.id.tv_search:
                //点击搜索
                awayKetBordUtils.putAwayKetBord();
                mList.clear();
                hdid = etSerch.getText().toString().trim();
                pagenum = 1;
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID, state);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_serch:
                //点击叉号
                awayKetBordUtils.putAwayKetBord();
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;

        }
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
