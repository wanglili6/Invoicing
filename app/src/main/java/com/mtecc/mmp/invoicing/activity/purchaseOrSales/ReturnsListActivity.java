package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.ReturnsExListAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.ReturnListBean;
import com.mtecc.mmp.invoicing.activity.transfer.AddTransferActivity;
import com.mtecc.mmp.invoicing.activity.transfer.TransferBatchSeeActivity;
import com.mtecc.mmp.invoicing.activity.transfer.adapter.TransferListAdapter;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TranferListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
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

public class ReturnsListActivity extends BaseActivity {
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
    ExpandableListView outBoundListView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_out_bound_list)
    LinearLayout llOutBoundList;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.img_left_select)
    ImageButton imgLeftSelect;
    private AwayKetBordUtils awayKetBordUtils;
    List<ReturnListBean.DataBean> mList = new ArrayList<>();
    private int cid = 0;
    private String proName = "";
    private String barcode = "";
    private String proCode = "";
    private String shopid = "";
    private int pagenum = 1;
    private ReturnsExListAdapter adapter;
    private String stockType;
    private String url;
    private boolean isPause;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        stockType = parms.getString(InvoicingConstants.STOCK_TYPE);
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setVisibility(View.GONE);
        llSeach.setVisibility(View.GONE);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        if (stockType.equals(InvoicingConstants.PURCHASE)) {
            tvTitle.setText("采购退货记录");
        } else if (stockType.equals(InvoicingConstants.SALES)) {
            tvTitle.setText("销售退货记录");
        }
        requestNetTranferList(pagenum + "", cid + "", shopid);
        adapter = new ReturnsExListAdapter(this, mList, stockType);
        outBoundListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_returns_list;
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
                mList.clear();
                pagenum = 1;
                requestNetTranferList(pagenum + "", cid + "", shopid);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetTranferList(pagenum + "", cid + "", shopid);

            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_select, R.id.img_left_select, R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_search:
                //点击搜索图标
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                awayKetBordUtils.showKeyboard(etSerch);
                break;
            case R.id.rl_select:
                //添加订单
                Intent intent = new Intent(ReturnsListActivity.this, AddReturnsActivity.class);
                Bundle bundleOutBound = new Bundle();
                bundleOutBound.putString(InvoicingConstants.TYPE, stockType);
                intent.putExtras(bundleOutBound);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_search:
                //点击搜索
                awayKetBordUtils.putAwayKetBord();
                mList.clear();
                proName = etSerch.getText().toString().trim();
                pagenum = 1;
                requestNetTranferList(pagenum + "", cid + "", shopid);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_serch:
                //点击叉号
                awayKetBordUtils.putAwayKetBord();
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");

                break;
            case R.id.img_left_select:
                //筛选
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(this, R.layout.commodity_shaixuan_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, customizeDialogView);
                dialogClick(customizeDialogView, dialog);
                break;

        }
    }

    /**
     * 对话框的初始化以及点击事件
     *
     * @param customizeDialogView
     * @param dialog
     */
    private void dialogClick(View customizeDialogView, final AlertDialog dialog) {
        TextView tvSure = customizeDialogView.findViewById(R.id.tv_sure);
        TextView tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        final EditText commodityDialogEtName = customizeDialogView.findViewById(R.id.commodity_dialog_et_name);
        final EditText commodityDialogEtBarCode = customizeDialogView.findViewById(R.id.commodity_dialog_et_bar_code);
        final EditText commodityDialogEtCode = customizeDialogView.findViewById(R.id.commodity_dialog_et_code);
        proName = etSerch.getText().toString().trim();
        commodityDialogEtName.setText(proName);
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                proName = commodityDialogEtName.getText().toString().trim();
                barcode = commodityDialogEtBarCode.getText().toString().trim();
                proCode = commodityDialogEtCode.getText().toString().trim();
                if (!TextUtils.isEmpty(proName)) {
                    rlSerchBar.setVisibility(View.VISIBLE);
                    ivBarSearch.setVisibility(View.GONE);
                    etSerch.setText(proName);
                } else {
                    rlSerchBar.setVisibility(View.GONE);
                    ivBarSearch.setVisibility(View.VISIBLE);
                    etSerch.setText("");
                }
                //TODO:筛选请求
                mList.clear();
                pagenum = 1;
                requestNetTranferList(pagenum + "", cid + "", shopid);
                adapter.notifyDataSetChanged();


            }
        });
    }

    /**
     * 获取退货商品
     *
     * @param page
     * @param cid
     * @param shopid
     */
    private void requestNetTranferList(String page, final String cid, final String shopid) {
        if (stockType.equals(InvoicingConstants.PURCHASE)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.tenterRecords_URL;
        } else {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.forSaleBack_URL;
        }
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("cid", cid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息CommodityListActivity" + e.toString());
                            Toast.makeText(ReturnsListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                            ReturnListBean returnListBean = gson.fromJson(response, ReturnListBean.class);
                            if (returnListBean != null) {
                                List<ReturnListBean.DataBean> dataList = returnListBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        adapter.notifyDataSetChanged();
                                        outBoundListView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            outBoundListView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            if (!TextUtils.isEmpty(proName) || !TextUtils.isEmpty(proCode) || !TextUtils.isEmpty(barcode)) {
                                                tvError.setText("无符合条件的数据!");
                                                showToast("无符合条件的数据!");
                                            } else {
                                                showToast("暂无数据!");
                                            }
                                        }
                                    }
                                } else {
                                    outBoundListView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(ReturnsListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                            Toast.makeText(ReturnsListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
