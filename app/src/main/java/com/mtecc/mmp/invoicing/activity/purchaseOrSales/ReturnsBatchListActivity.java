package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

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
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.ReturnsBatchAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SeePurchaserBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.activity.transfer.TransferommodityListActivity;
import com.mtecc.mmp.invoicing.activity.transfer.adapter.TransferSelectBatchAdapter;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferCommodityBean;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferGoodsBatchBean;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 选择批次
 */
public class ReturnsBatchListActivity extends BaseActivity {

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

    @BindView(R.id.tv_select_names)
    TextView tvSelectNames;
    @BindView(R.id.img_shop_car)
    ImageView imgShopCar;
    @BindView(R.id.img_select_num)
    TextView imgSelectNum;
    @BindView(R.id.tv_select_sure)
    TextView tvSelectSure;
    @BindView(R.id.ll_select_sure)
    LinearLayout llSelectSure;
    private List<TransferCommodityBean.DataBean> mList = new ArrayList<>();
    private Map<String, SelectBatchBean> mSelectMap = new HashMap<>();
    private String batchid = "";
    private ReturnsBatchAdapter batchAdapter;
    private boolean isPause = false;
    private String shopid = "";
    private String hdid = "";
    private String type = "";
    private String shtype = "";
    private String goodsid = "";
    private String goodsName = "";
    private int page = 1;
    private String batchNum = "";
    private String batchEndTimer = "";
    private String batchStartTimer = "";
    private UseSystemUtils useSystemUtils;
    private String position;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        llSelectSure.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.GONE);
        imgLeftSelect.setVisibility(View.GONE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        imgShopCar.setBackgroundResource(R.mipmap.koudai_icon);
        parms = getIntent().getExtras();
        useSystemUtils = new UseSystemUtils(ReturnsBatchListActivity.this);
        goodsName = parms.getString(InvoicingConstants.goodsName);
        goodsid = parms.getString(InvoicingConstants.COMMODITY_Id);
        shopid = parms.getString(InvoicingConstants.shopId);
        hdid = parms.getString(InvoicingConstants.hdid);
        type = parms.getString(InvoicingConstants.TYPE);
        if (type.equals(InvoicingConstants.PURCHASE)) {
            shtype = InvoicingConstants.SHOP_Shehe;
        } else if (type.equals(InvoicingConstants.SALES)) {
            shtype = InvoicingConstants.SEAL_Shehe;

        }
        mList.clear();
        position = parms.getString("position");
        tvTitle.setText(goodsName + "的批次");
        Map<String, SelectBatchBean> mgetSelectMap = (Map<String, SelectBatchBean>) parms.getSerializable(InvoicingConstants.select_Batch);
        if (mgetSelectMap != null) {
            mSelectMap.clear();
            mSelectMap.putAll(mgetSelectMap);
        }
        batchAdapter = new ReturnsBatchAdapter(this, mList, tvSelectNames, imgSelectNum, type);
        batchListView.setAdapter(batchAdapter);
        batchAdapter.notifyDataSetChanged();
        requestNetDetails(hdid, shopid, shtype);

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
                requestNetDetails(hdid, shopid, shtype);
                batchAdapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mapMlist();
                requestNetDetails(hdid, shopid, shtype);
                batchAdapter.notifyDataSetChanged();
            }
        });
    }

    private void mapMlist() {
        for (int i = 0; i < mList.size(); i++) {
            TransferCommodityBean.DataBean value = mList.get(i);
            SelectBatchBean batchBean = new SelectBatchBean();
            batchBean.setNum(value.getNum());
            batchBean.setBatchnum(value.getPbatchid().getBatchnum() + "");
            batchBean.setBatchdate(value.getPbatchid().getBatchdateStr() + "");
            String enterprice = value.getPbatchid().getEnterprice();
            String sellprice = value.getPbatchid().getSellprice();
            String saleprice = value.getPbatchid().getSaleprice();
            if (TextUtils.isEmpty(enterprice)) {
                enterprice = "0.0";
            }
            if (TextUtils.isEmpty(sellprice)) {
                sellprice = "0.0";
            }
            if (TextUtils.isEmpty(saleprice)) {
                saleprice = "0.0";
            }
            batchBean.setEnterprice(enterprice);
            batchBean.setSaleprice(saleprice);
            batchBean.setSellprice(sellprice);
            batchBean.setPbatchid(value.getPbatchid().getPbatchid());
            batchBean.setIsdel(value.getPbatchid().getIsdel());
            batchBean.setRemark(value.getPbatchid().getRemark());
            batchBean.setSid(value.getSid());
            batchBean.setBhid(value.getPbatchid().getBhid());
            batchBean.setKcsum(value.getKcsum() + "");
            mSelectMap.put(mList.get(i).getPbatchid().getPbatchid() + "", batchBean);
        }

    }

    /**
     * 设置个数和钱数
     */
    private void setNumMoney() {
        int totalNum = 0;
        double totalmoney = 0.0;
        for (int i = 0; i < mList.size(); i++) {
            TransferCommodityBean.DataBean value = mList.get(i);
            String nums = value.getNum() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                totalNum = totalNum + value.getNum();
                String enterprice = value.getPbatchid().getSellprice();
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(value.getPbatchid().getSellprice());
                    double selectNum = Double.parseDouble(String.valueOf(value.getNum()));
                    BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                    BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
                    BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                    BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
                    BigDecimal add = totalmoneyBigDecimal.add(multiply);
                    totalmoney = add.doubleValue();
                } else {
                    totalmoney = totalmoney + value.getNum() * 0.0;
                }
            }
        }
        imgSelectNum.setText(totalNum + "");
        tvSelectNames.setText(totalmoney + "");
        LogUtils.i("个数===" + totalNum + "钱数----" + totalmoney);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.tv_select_sure, R.id.rl_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_select:
                //添加批次
                mapMlist();
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
                mapMlist();
                mList.clear();
                page = 1;
                batchAdapter.notifyDataSetChanged();

                break;
            case R.id.tv_select_sure:
                mapMlist();
                Intent batchintent = new Intent(ReturnsBatchListActivity.this, TransferommodityListActivity.class);
                Bundle batchbundle = new Bundle();
                batchbundle.putString(InvoicingConstants.COMMODITY_Id, goodsid + "");
                batchbundle.putString("position", position + "");
                batchbundle.putSerializable(InvoicingConstants.select_Batch, (Serializable) mSelectMap);
                String money = tvSelectNames.getText().toString().trim();
                String totalnum = imgSelectNum.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    batchbundle.putDouble(InvoicingConstants.select_Batch_money, 0.0);
                } else {
                    batchbundle.putDouble(InvoicingConstants.select_Batch_money, Double.parseDouble(money));
                }
                if (TextUtils.isEmpty(totalnum)) {
                    batchbundle.putInt(InvoicingConstants.select_Batch_num, 0);
                } else {
                    batchbundle.putInt(InvoicingConstants.select_Batch_num, Integer.parseInt(totalnum));
                }
                batchintent.putExtras(batchbundle);
                setResult(2, batchintent);
                finish();
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
                mapMlist();
                mList.clear();

                page = 1;
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
     * 订单详情
     *
     * @param hdid   订单编号
     * @param shopid
     */
    private void requestNetDetails(String hdid, String shopid, String shtype) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.LookBargin_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("hdid", hdid)//订单编号
                .addParams("shopid", shopid)
                .addParams("shtype", shtype)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        smartRefreshLayout.finishLoadmore();
                        smartRefreshLayout.finishRefresh();
                        try {
                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
                            Toast.makeText(ReturnsBatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        smartRefreshLayout.finishLoadmore();
                        smartRefreshLayout.finishRefresh();
                        try {
                            LogUtils.d("返回值信息PurchaseListActivity" + response.toString());
                            Gson gson = new Gson();
                            SeePurchaserBean seePurchaserBean = gson.fromJson(response, SeePurchaserBean.class);
                            if (seePurchaserBean != null) {
                                List<SeePurchaserBean.DataBean> dataBeanList = seePurchaserBean.getData();
                                if (dataBeanList != null) {
                                    int size = dataBeanList.size();
                                    for (int i = 0; i < size; i++) {
                                        SeePurchaserBean.DataBean.GoodBean goodsBean = dataBeanList.get(i).getGood();
                                        String proId = goodsBean.getProId() + "";
                                        if (proId.equals(goodsid)) {

                                            List<SeePurchaserBean.DataBean.PclistBean> pclist = dataBeanList.get(i).getPclist();
                                            mList.clear();
                                            setseeNumMoney(pclist);
                                        }
                                    }
                                    batchAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                            Toast.makeText(ReturnsBatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 编辑设置个数和钱数
     */
    private void setseeNumMoney(List<SeePurchaserBean.DataBean.PclistBean> pclist) {
        for (int i = 0; i < pclist.size(); i++) {
            TransferCommodityBean.DataBean dataBean = new TransferCommodityBean.DataBean();
            TransferCommodityBean.DataBean.PbatchidBean batchidBean = new TransferCommodityBean.DataBean.PbatchidBean();
            SeePurchaserBean.DataBean.PclistBean pclistBean = pclist.get(i);
            batchidBean.setPbatchid(pclistBean.getPbatchid().getPbatchid());
            batchidBean.setBatchdateStr(pclistBean.getPbatchid().getBatchdateStr());
            String saleprice = pclistBean.getPbatchid().getSaleprice();
            String enterprice1 = pclistBean.getPbatchid().getEnterprice();
            String sellprice = pclistBean.getPbatchid().getSellprice();
            if (TextUtils.isEmpty(saleprice)) {
                saleprice = "0.0";
            }
            if (TextUtils.isEmpty(enterprice1)) {
                enterprice1 = "0.0";
            }
            if (TextUtils.isEmpty(sellprice)) {
                sellprice = "0.0";
            }
            batchidBean.setSaleprice(saleprice);
            batchidBean.setSellprice(sellprice + "");
            batchidBean.setSellprice(sellprice + "");
            batchidBean.setEnterprice(enterprice1);
            batchidBean.setRemark(pclistBean.getPbatchid().getRemark());
            batchidBean.setBatchnum(pclistBean.getPbatchid().getBatchnum());
            batchidBean.setProName(pclistBean.getPbatchid().getProName());
            batchidBean.setIsdel(pclistBean.getPbatchid().getIsdel());
            batchidBean.setBhid(pclistBean.getBhid());
            dataBean.setPbatchid(batchidBean);
            String kcsum = pclistBean.getKcsum();
            if (TextUtils.isEmpty(kcsum)) {
                kcsum = "0";
            }
            dataBean.setKcsum(Integer.parseInt(kcsum));
            String enterqty = pclistBean.getEnterqty();
            if (TextUtils.isEmpty(enterqty)) {
                enterqty = "0";
            }
            dataBean.setCgum(Integer.parseInt(enterqty));
            mList.add(dataBean);

            if (mSelectMap != null) {
                if (mSelectMap.size() == 0) {
                    for (int j = 0; j < mList.size(); j++) {
                        mList.get(j).setNum(0);
                    }
                } else {
                    Iterator<String> iterator = mSelectMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        String selectpbatchid = iterator.next();
                        for (int j = 0; j < mList.size(); j++) {
                            String pbatchid = mList.get(j).getPbatchid().getPbatchid() + "";
                            if (selectpbatchid.equals(pbatchid)) {
                                mList.get(j).setNum(mSelectMap.get(selectpbatchid).getNum());
                            } else {
                                continue;

                            }
                        }
                    }

                }
            }
            setNumMoney();
        }
    }
}
