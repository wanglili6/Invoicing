package com.mtecc.mmp.invoicing.activity.inStock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.inStock.adapter.StockBatchAdapter;
import com.mtecc.mmp.invoicing.activity.inStock.bean.BatchListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
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

public class StockBatchListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_left_select)
    TextView tvLeftSelect;
    @BindView(R.id.img_left_select)
    ImageButton imgLeftSelect;
    @BindView(R.id.rl_right_left)
    RelativeLayout rlRightLeft;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.batch_list_view)
    SwipeMenuListView batchListView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
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

    private List<BatchListBean.DataBean> mList = new ArrayList<>();
    private String batchid = "";
    private StockBatchAdapter batchAdapter;
    private boolean isPause = false;
    private String goodsid = "";
    private int page = 1;
    private String batchNum = "";
    private String batchEndTimer = "";
    private String batchStartTimer = "";
    private UseSystemUtils useSystemUtils;
    private String stockType;
    private String shopid = "";

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.GONE);
        imgLeftSelect.setVisibility(View.GONE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        tvTitle.setText("批次信息");
        etSerch.setHint("请输入查询的批号");
        parms = getIntent().getExtras();
        useSystemUtils = new UseSystemUtils(StockBatchListActivity.this);
        goodsid = parms.getString(InvoicingConstants.COMMODITY_Id);
        stockType = parms.getString(InvoicingConstants.STOCK_TYPE);
        shopid = parms.getString(InvoicingConstants.SHOP_ID);
        batchAdapter = new StockBatchAdapter(this, mList, stockType);
        batchListView.setAdapter(batchAdapter);
        batchAdapter.notifyDataSetChanged();

        requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer,shopid);


//        batchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //
//                Intent intent = new Intent();
//                intent.putExtra("pbatchid", mList.get(position).getPbatchid() + "");
//                intent.setClass(StockBatchListActivity.this, SeeBatchMsgActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_batch_list;
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
                mList.clear();
                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer,shopid);
                batchAdapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer,shopid);
                batchAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.rl_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_select:
                //添加批次
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_ADD);
                bundle.putString(InvoicingConstants.COMMODITY_Id, goodsid);
                intent.setClass(this, AddBatchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img_left_select:
                //筛选
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(this, R.layout.batch_shaixuan_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, customizeDialogView);
                shaixuanClick(customizeDialogView, dialog);
                break;
            case R.id.iv_bar_search:
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                break;
            case R.id.iv_serch:
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;
            case R.id.tv_search:
                batchNum = etSerch.getText().toString().trim();
                //TODO:筛选请求
                mList.clear();
                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer,shopid);
                batchAdapter.notifyDataSetChanged();

                break;
        }
    }

    /**
     * 对话框的初始化以及点击事件
     *
     * @param customizeDialogView
     * @param dialog
     */
    private void shaixuanClick(View customizeDialogView, final AlertDialog dialog) {
        TextView tvSure = customizeDialogView.findViewById(R.id.tv_sure);
        TextView tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        final EditText batch_dialog_et_num = customizeDialogView.findViewById(R.id.batch_dialog_et_num);
        final EditText batch_dialog_et_end = customizeDialogView.findViewById(R.id.batch_dialog_et_end);
        final EditText batch_dialog_et_start = customizeDialogView.findViewById(R.id.batch_dialog_et_start);
        batchNum = etSerch.getText().toString().trim();
        batch_dialog_et_num.setText(batchNum);
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
                batchNum = batch_dialog_et_num.getText().toString().trim();
                batchEndTimer = batch_dialog_et_end.getText().toString().trim();
                batchStartTimer = batch_dialog_et_start.getText().toString().trim();
                if (!TextUtils.isEmpty(batchNum)) {
                    rlSerchBar.setVisibility(View.VISIBLE);
                    ivBarSearch.setVisibility(View.GONE);
                    etSerch.setText(batchNum);
                } else {
                    rlSerchBar.setVisibility(View.GONE);
                    ivBarSearch.setVisibility(View.VISIBLE);
                    etSerch.setText("");
                }
                //TODO:筛选请求
                mList.clear();

                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer,shopid);
                batchAdapter.notifyDataSetChanged();


            }
        });
        batch_dialog_et_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                useSystemUtils.useSystemTimeNoHms(batch_dialog_et_end);
            }
        });
        batch_dialog_et_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useSystemUtils.useSystemTimeNoHms(batch_dialog_et_start);
            }
        });
    }

    /**
     * 获取批次信息
     *
     * @param page
     * @param goodsid
     * @param batchnumStr
     * @param endDate
     * @param startDate
     */
    private void requestNetBatchList(String page, String goodsid, final String batchnumStr,
                                     final String endDate, final String startDate, final String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.goodsBatchList_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("goodsid", goodsid)
                .addParams("shopid", shopid)
                .addParams("batchnumStr", batchnumStr)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息BatchListActivity" + e.toString());
                            Toast.makeText(StockBatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishLoadmore();
                            smartRefreshLayout.finishRefresh();
                            LogUtils.d("返回值信息BatchListActivity" + response.toString());
                            Gson gson = new Gson();
                            BatchListBean batchListBean = gson.fromJson(response, BatchListBean.class);
                            if (batchListBean != null) {
                                List<BatchListBean.DataBean> dataBeanList = batchListBean.getData();
                                if (dataBeanList != null) {
                                    mList.addAll(dataBeanList);
                                    if (mList.size() != 0) {
                                        if (dataBeanList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        batchAdapter.notifyDataSetChanged();
                                        batchListView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataBeanList.size() == 0) {
                                            batchListView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            if (!TextUtils.isEmpty(batchnumStr) || !TextUtils.isEmpty(endDate) || !TextUtils.isEmpty(startDate)) {
                                                tvError.setText("无符合条件的数据!");
                                                showToast("无符合条件的数据!");
                                            } else {
                                                showToast("暂无批次,请进行添加!");
                                            }
                                        }
                                    }
                                } else {
                                    batchListView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(StockBatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                            Toast.makeText(StockBatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
