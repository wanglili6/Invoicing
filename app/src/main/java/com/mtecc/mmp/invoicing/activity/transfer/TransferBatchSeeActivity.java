package com.mtecc.mmp.invoicing.activity.transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.transfer.adapter.OutbatchSeeAdapter;
import com.mtecc.mmp.invoicing.activity.transfer.adapter.TransferbatchSeeAdapter;
import com.mtecc.mmp.invoicing.activity.transfer.bean.OutBoundBean;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferBatchBean;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 调拨批次列表--查看调拨批次
 */
public class TransferBatchSeeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
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
    @BindView(R.id.out_bound_list_saoma)
    ImageView outBoundListSaoma;
    @BindView(R.id.out_bound_list_view)
    SwipeMenuListView outBoundListView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_out_bound_list)
    LinearLayout llOutBoundList;
    @BindView(R.id.tv_error)
    TextView tvError;
    private AwayKetBordUtils awayKetBordUtils;
    List<TransferBatchBean.DataBean> mTransferList = new ArrayList<>();
    List<OutBoundBean.DataBean> mOutBoundList = new ArrayList<>();

    private String goodsid = "";
    private String goodsName = "";
    private String stockType = "";
    private String shop_id = "";
    private int pagenum = 1;
    private String url;
    private OutbatchSeeAdapter outbatchSeeAdapter;
    private TransferbatchSeeAdapter transferbatchSeeAdapter;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.GONE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        goodsid = parms.getString(InvoicingConstants.goodsid);
        goodsName = parms.getString(InvoicingConstants.goodsName);
        stockType = parms.getString(InvoicingConstants.STOCK_TYPE);
        tvTitle.setText(goodsName + "的批次");
        mTransferList.clear();
        mOutBoundList.clear();
        shop_id = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
            requestNetTranferBatchList(pagenum + "", shop_id, goodsid);
            transferbatchSeeAdapter = new TransferbatchSeeAdapter(this, mTransferList, stockType);
            outBoundListView.setAdapter(transferbatchSeeAdapter);
            transferbatchSeeAdapter.notifyDataSetChanged();
        } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
            requestNetOutBoundBatchList(pagenum + "", shop_id, goodsid);
            outbatchSeeAdapter = new OutbatchSeeAdapter(this, mOutBoundList, stockType);
            outBoundListView.setAdapter(outbatchSeeAdapter);
            outbatchSeeAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_out_bound_list;
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
                refreshlayout.finishRefresh(1500);
                mOutBoundList.clear();
                mTransferList.clear();
                pagenum = 1;
                if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
                    requestNetTranferBatchList(pagenum + "", shop_id, goodsid);

                } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
                    requestNetOutBoundBatchList(pagenum + "", shop_id, goodsid);

                }
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetTranferBatchList(pagenum + "", shop_id, goodsid);

            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_select, R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_search:
                //点击搜索图标
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                awayKetBordUtils.showKeyboard(etSerch);
                break;
            case R.id.rl_select:
                //点击搜索图标
                Intent intent = new Intent(TransferBatchSeeActivity.this, AddTransferActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_search:
                //点击搜索
                awayKetBordUtils.putAwayKetBord();
                mOutBoundList.clear();
                mTransferList.clear();
                pagenum = 1;
                if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
                    requestNetTranferBatchList(pagenum + "", shop_id, goodsid);
                } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
                    requestNetOutBoundBatchList(pagenum + "", shop_id, goodsid);
                }
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

    /**
     * 获取商品批次的信息
     *
     * @param page
     * @param shopid
     * @param goodsid
     */
    private void requestNetTranferBatchList(String page, String shopid, String goodsid) {
        url = InvoicingConstants.BASE_URL + InvoicingConstants.tranBatchList_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("goodsid", goodsid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息CommodityListActivity" + e.toString());
                            Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            LogUtils.d("返回值信息CommodityListActivity" + response.toString());
                            Gson gson = new Gson();
                            TransferBatchBean transferBatchBean = gson.fromJson(response, TransferBatchBean.class);
                            if (transferBatchBean != null) {
                                List<TransferBatchBean.DataBean> dataList = transferBatchBean.getData();
                                if (dataList != null) {
                                    mTransferList.addAll(dataList);
                                    if (mTransferList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        transferbatchSeeAdapter.notifyDataSetChanged();
                                        outBoundListView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            outBoundListView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            showToast("暂无数据");

                                        }
                                    }
                                } else {
                                    outBoundListView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                            Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 获取商品批次的信息
     *
     * @param page
     * @param shopid
     * @param goodsid
     */
    private void requestNetOutBoundBatchList(String page, String shopid, String goodsid) {
        url = InvoicingConstants.BASE_URL + InvoicingConstants.moveBatchList_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("goodsid", goodsid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息CommodityListActivity" + e.toString());
                            Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            LogUtils.d("返回值信息CommodityListActivity" + response.toString());
                            Gson gson = new Gson();
                            OutBoundBean outBoundBean = gson.fromJson(response, OutBoundBean.class);
                            if (outBoundBean != null) {
                                List<OutBoundBean.DataBean> dataList = outBoundBean.getData();
                                if (dataList != null) {
                                    mOutBoundList.addAll(dataList);
                                    if (mOutBoundList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        outbatchSeeAdapter.notifyDataSetChanged();
                                        outBoundListView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            outBoundListView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            showToast("暂无数据");

                                        }
                                    }
                                } else {
                                    outBoundListView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                            Toast.makeText(TransferBatchSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
