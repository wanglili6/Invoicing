package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.distributor.DistributorLIstActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.SelectPurchaseShopAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PurchaseCommitBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SeePurchaserBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectCommodityBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.activity.transfer.TransferommodityListActivity;
import com.mtecc.mmp.invoicing.activity.transfer.adapter.TransferExAdapter;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferCommodityBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.views.NoScrollExpandaleListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

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
 * 销售订单
 */
public class AddSalesActivity extends BaseActivity {


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
    @BindView(R.id.add_purchase_code)
    EditText addPurchaseCode;
    @BindView(R.id.add_purchase_merchants)
    EditText addPurchaseMerchants;
    @BindView(R.id.add_purchase_shop)
    EditText addPurchaseShop;
    @BindView(R.id.add_purchase_miaoshu)
    EditText addPurchaseMiaoshu;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.add_purchase_commit)
    TextView addPurchaseCommit;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_type_text)
    TextView tvTypeText;
    @BindView(R.id.tv_see_code)
    TextView tvSeeCode;
    @BindView(R.id.ll_purchase_code)
    LinearLayout llPurchaseCode;
    String purchaseShopId = "";//店铺id
    String purchaseCode = "";//店铺id
    String purchaseShopName = "";//店铺id
    String purchaseHdtitle = "";//货单描述
    String purchaseMerchantId = "";//供货商
    String purchaseMerchantName = "";//供货商名字
    private int cid = 0;
    private String user_id;
    private Map<String, TransferCommodityBean.DataBean> mSelectCommodityMap = new HashMap<>();
    private List<TransferCommodityBean.DataBean> mSelectCommodityList = new ArrayList<>();
    private TransferExAdapter transferExAdapter;
    private String shopid;
    private String shopName;
    private AlertDialog showDialog;
    private boolean isuseradmin;
    private boolean isUseXSAuidt;
    private SelectPurchaseShopAdapter selectShopListAdapter;
    private String type = "";
    private String bhid = "";
    private PrichaseIncomeBean.DataBean dataOrderBean;
    private String url;

    @Override
    public void widgetClick(View v) {


    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        type = parms.getString(InvoicingConstants.TYPE);
        View view1 = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, view1);
        showDialog.dismiss();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        ivBack.setVisibility(View.VISIBLE);
        isuseradmin = PreferencesUtils.getBoolean(this, InvoicingConstants.isuseradmin, false);
        isUseXSAuidt = PreferencesUtils.getBoolean(this, InvoicingConstants.isUseXSAuidt, false);
        ivBack.setVisibility(View.VISIBLE);
        if (!isuseradmin) {
            shopName = PreferencesUtils.getString(this, InvoicingConstants.SHOP_Name, "");
            purchaseShopId = shopid;
            purchaseShopName = shopName;
            addPurchaseShop.setText(shopName + "");
        }
        if (type.equals("add")) {
            tvTitle.setText("采购订单");
        } else if (type.equals("edit")) {
            tvTitle.setText("编辑订单");
            llPurchaseCode.setVisibility(View.VISIBLE);
            dataOrderBean = (PrichaseIncomeBean.DataBean) parms.getSerializable(InvoicingConstants.hdid);
            if (dataOrderBean != null) {
                addPurchaseCode.setText(dataOrderBean.getHdid() + "");
                addPurchaseMerchants.setText(dataOrderBean.getMername() + "");
                addPurchaseShop.setText(dataOrderBean.getShopname() + "");
                addPurchaseMiaoshu.setText(dataOrderBean.getHdtitle() + "");
                purchaseCode = dataOrderBean.getHdid();//店铺id
                purchaseShopId = dataOrderBean.getShopid();//店铺id
                purchaseShopName = dataOrderBean.getShopname();//店铺id
                purchaseHdtitle = dataOrderBean.getHdtitle();//货单描述
                purchaseMerchantId = dataOrderBean.getMerid();//供货商
                purchaseMerchantName = dataOrderBean.getMername();//供货商名字
                requestNetDetails(purchaseCode, shopid, InvoicingConstants.SEAL_Shehe);
            }
        }
        tvTypeText.setText("分销商");
        transferExAdapter = new TransferExAdapter(this, mSelectCommodityList, "show");
        addBatchListView.setAdapter(transferExAdapter);
        transferExAdapter.notifyDataSetChanged();
        tvBatchNum.setText("0");
        tvTotalMoney.setText("0.0");
        //删除商品
        transferExAdapter.setOndelListerner(new TransferExAdapter.OnDelListerner() {
            @Override
            public void onDelClick(int groupPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, exitView);
                delClick(exitView, dialog, groupPostion);
            }
        });

        //编辑批次--进货价--数量
        transferExAdapter.setOnEditBtachLIsterner(new TransferExAdapter.OnEditBtachListerner() {
            @Override
            public void onEditClick(int groupPostion, int childPostion) {
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.edit_select_commodoty_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, customizeDialog);
                editClick(customizeDialog, alertDialog, groupPostion, childPostion);
            }
        }); //编辑批次--进货价--数量
        transferExAdapter.setOnDelBtachLIsterner(new TransferExAdapter.OnDelBtachListerner() {
            @Override
            public void onDelBtachClick(int groupPostion, int childPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, exitView);
                delBtachClick(exitView, dialog, groupPostion, childPostion);
            }
        });
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_add_purchase;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @OnClick({R.id.rl_back, R.id.add_purchase_commit, R.id.add_purchase_shop, R.id.tv_select_user, R.id.commodity_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_purchase_shop:
                //选择店铺
                if (isuseradmin) {
                    requestNetShopList();
                }
                break;

            case R.id.tv_select_user:
                //分销商
                Intent supplierintent = new Intent();
                Bundle distributorbundle = new Bundle();
                distributorbundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                distributorbundle.putString(InvoicingConstants.Distributor_TYPE, InvoicingConstants.Distributor_Select);
                supplierintent.setClass(AddSalesActivity.this, DistributorLIstActivity.class);
                supplierintent.putExtras(distributorbundle);
                startActivityForResult(supplierintent, 3);
                break;
            case R.id.commodity_add_iteam:
                //选择商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(AddSalesActivity.this, TransferommodityListActivity.class);
                if (TextUtils.isEmpty(purchaseShopId)) {
                    showToast("请选择店铺");
                    return;
                }
                bundle.putString(InvoicingConstants.shopId, purchaseShopId + "");
                bundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                bundle.putString(InvoicingConstants.TYPE, "show");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
            case R.id.add_purchase_commit:
                //提交采购单
                showDialog.show();
                commitPurchase();
                break;
        }
    }

    /**
     * 提交采购单
     */
    private void commitPurchase() {
        purchaseMerchantName = addPurchaseMerchants.getText().toString().trim();
        purchaseShopName = addPurchaseShop.getText().toString().trim();
        purchaseHdtitle = addPurchaseMiaoshu.getText().toString().trim();//货单描述

        if (TextUtils.isEmpty(purchaseMerchantName) || TextUtils.isEmpty(purchaseMerchantId)) {
            showToast("分销商不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(purchaseShopName) || TextUtils.isEmpty(purchaseShopId)) {
            showToast("店铺不能为空!");
            showDialog.dismiss();
            return;
        }

        PurchaseCommitBean purchaseCommitBean = new PurchaseCommitBean();
        purchaseCommitBean.setPurchaseMerchantId(purchaseMerchantId);
        purchaseCommitBean.setPurchaseMerchantName(purchaseMerchantName);
        purchaseCommitBean.setPurchaseShopId(purchaseShopId);
        purchaseCommitBean.setPurchaseShopName(purchaseShopName);
        purchaseCommitBean.setPurchaseUserId(user_id);
        purchaseCommitBean.setPurchaseHdtitle(purchaseHdtitle);
        // 1系统未开启  2 开启审核
        if (isUseXSAuidt) {
            purchaseCommitBean.setPurchaseState("2");
        } else {
            purchaseCommitBean.setPurchaseState("1");
        }
        if (mSelectCommodityMap.size() == 0) {
            showToast("选择的商品不能为空!");
            showDialog.dismiss();
            return;
        } else {
            Iterator<String> iterator = mSelectCommodityMap.keySet().iterator();
            List<SelectCommodityBean> mSelectCommodityList = new ArrayList<>();
            while (iterator.hasNext()) {
                String next = iterator.next();
                TransferCommodityBean.DataBean dataBean = mSelectCommodityMap.get(next);
                SelectCommodityBean selectCommodityBean = new SelectCommodityBean();
                selectCommodityBean.setProId(dataBean.getProId().getProId());
                selectCommodityBean.setProName(dataBean.getProId().getProName());
                selectCommodityBean.setmSelectNum(dataBean.getmSelectNum());
                selectCommodityBean.setmSelectMoney(dataBean.getmSelectMoney());
                selectCommodityBean.setProCode(dataBean.getProId().getProCode());
                selectCommodityBean.setBarCode(dataBean.getProId().getBarcode());
                selectCommodityBean.setMeas(dataBean.getProId().getMeas());
                selectCommodityBean.setMeas(dataBean.getProId().getMeas());
                selectCommodityBean.setSid(dataBean.getSid());
                selectCommodityBean.setMeaunit(dataBean.getProId().getMeaunit());
                if (dataBean.getmSelectMap() != null && dataBean.getmSelectMap().size() != 0) {
                    selectCommodityBean.setmSelectMap(dataBean.getmSelectMap());
                }
                mSelectCommodityList.add(selectCommodityBean);
            }
            purchaseCommitBean.setmSelectCommodityMap(mSelectCommodityList);
        }
        if (type.equals("edit")) {
            purchaseCommitBean.setHdid(purchaseCode);
            purchaseCommitBean.setBhid(bhid);
            purchaseCommitBean.setPurchaseHdtype("2");
        }
        Gson gson = new Gson();
        String commitJson = gson.toJson(purchaseCommitBean);
        LogUtils.i("提交采购单===" + commitJson);
        requestNetCommit(commitJson);
    }

    /**
     * 提交订单
     */
    private void requestNetCommit(final String selectCommodityBean) {
        if (type.equals("add")) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.outSaveBargin_URL;
        } else if (type.equals("edit")) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.EditBargin_URL;
        }
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + selectCommodityBean);
        PostFormBuilder post = OkHttpUtils.post();
        post.tag(this)
                .addParams("selectCommodityBean", selectCommodityBean)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("提交订单成功!");
                                    if (type.equals("add")) {
                                        View exitView = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.exit_dialog);
                                        AlertDialog dialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, exitView);
                                        exitClick(exitView, dialog);
                                    } else if (type.equals("edit")) {
                                        finish();
                                    }
                                } else {
                                    showToast("提交订单失败!");
                                }
                            } else {
                                Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {

                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Map<String, TransferCommodityBean.DataBean> mgetSelectMap = (Map<String, TransferCommodityBean.DataBean>) extras.getSerializable(InvoicingConstants.select_Commiodty);
                    if (mgetSelectMap != null) {
                        Iterator<String> iterator = mgetSelectMap.keySet().iterator();
                        mSelectCommodityList.clear();
                        mSelectCommodityMap.clear();
                        while (iterator.hasNext()) {
                            String next = iterator.next();
                            String selctnum = mgetSelectMap.get(next).getmSelectNum() + "";
                            if (!TextUtils.isEmpty(selctnum) && !selctnum.equals("0")) {
                                mSelectCommodityList.add(mgetSelectMap.get(next));
                                mSelectCommodityMap.put(next, mgetSelectMap.get(next));
                            }
                        }
                        transferExAdapter.notifyDataSetChanged();
                        setTotalNumMoney();
                    }
                }

                break;

            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    purchaseMerchantName = extras.getString(InvoicingConstants.Distributor_Name);
                    purchaseMerchantId = extras.getString(InvoicingConstants.Distributor_ID);
                    addPurchaseMerchants.setText(purchaseMerchantName + "");
                }

                break;
        }
    }

    /**
     * 是否返回
     *
     * @param exitView
     * @param dialog
     */
    private void exitClick(View exitView, final AlertDialog dialog) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否继续录入新的采购订单?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                Intent intent = new Intent(AddSalesActivity.this, AddSalesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.TYPE, "add");
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }

    /**
     * 是否删除商品
     *
     * @param exitView
     * @param dialog
     */
    private void delClick(View exitView, final AlertDialog dialog, final int groupPostion) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除当前商品选择所有批次的信息?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
                if (type.equals("add")) {
                    delectGoods(groupPostion);
                } else if (type.equals("edit")) {
                    //TODO:删除商品
                    List<SelectBatchBean> selectBatchBeanList = dataBean.getmSelectMap();
                    int size = selectBatchBeanList.size();
                    for (int i = 0; i < size; i++) {
                        SelectBatchBean selectBatchBean = selectBatchBeanList.get(i);
                        String gethbid = selectBatchBean.getBhid() + "";
                        if (!TextUtils.isEmpty(gethbid)) {
                            bhid += selectBatchBean.getBhid() + ",";
                        }
                    }
                    delectGoods(groupPostion);

                }

            }
        });
    }

    private void delectGoods(int groupPostion) {
        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        mSelectCommodityList.remove(dataBean);
        mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
        setTotalNumMoney();
        transferExAdapter.notifyDataSetChanged();
        showToast("删除商品成功!");
    }

    /**
     * 是否删除
     *
     * @param exitView
     * @param dialog
     */
    private void delBtachClick(View exitView, final AlertDialog dialog, final int groupPostion, final int childPostion) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除当前选择批次的信息?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
                if (type.equals("add")) {
                    delectBtach(groupPostion, childPostion);
                } else if (type.equals("edit")) {
                    //TODO:删除批次
                    String gethbid = dataBean.getmSelectMap().get(childPostion).getBhid() + "";
                    if (!TextUtils.isEmpty(gethbid)) {
                        bhid += dataBean.getmSelectMap().get(childPostion).getBhid() + "";
                    }
                    delectBtach(groupPostion, childPostion);

                }


            }
        });
    }

    private void delectBtach(int groupPostion, int childPostion) {
        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        if (dataBean.getmSelectMap() != null) {
            dataBean.getmSelectMap().remove(childPostion);
            if (dataBean.getmSelectMap().size() == 0) {
                mSelectCommodityList.remove(groupPostion);
                mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
            }
        } else {
            mSelectCommodityList.remove(groupPostion);
            mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
        }
        setNumMoney(dataBean);
        setTotalNumMoney();

        transferExAdapter.notifyDataSetChanged();
        showToast("删除批次信息成功!");
    }

    /**
     * 编辑
     */
    private void editClick(View customizeDialog, final AlertDialog alertDialog, final int groupPostion, final int childPostion) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvText = customizeDialog.findViewById(R.id.tv_text);
        final EditText tvEnterPrice = customizeDialog.findViewById(R.id.commodity_enter_price);
        final EditText tvNum = customizeDialog.findViewById(R.id.commodity_num);
        final EditText tvname = customizeDialog.findViewById(R.id.commodity_enter_name);
        final TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        tvText.setText("零售价");
        if (dataBean != null) {
            SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
            String enterprice = batchBean.getSellprice();
            tvname.setText(batchBean.getBatchdate() + "");
            tvEnterPrice.setText(enterprice + "");
            tvNum.setText(batchBean.getNum() + "");
        }
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
                //TODO:修改
                alertDialog.dismiss();
                String price = tvEnterPrice.getText().toString().trim();
                String commodityNum = tvNum.getText().toString().trim();
                if (!TextUtils.isEmpty(commodityNum)) {
                    int selectum = Integer.parseInt(commodityNum);
                    if (selectum > 0 || selectum != 0) {
                        SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
                        String enterprice = batchBean.getSellprice();
                        if (!TextUtils.isEmpty(price)) {
                            if (!price.equals(enterprice)) {
                                List<BatchPicListBean.CardBean> mpicBancthList = new ArrayList<BatchPicListBean.CardBean>();
                                BatchPicListBean batchPicListBean = new BatchPicListBean();
                                batchPicListBean.setEnterprice(price);
                                batchPicListBean.setPbatchid(batchBean.getPbatchid());
                                batchPicListBean.setBatchdate(batchBean.getBatchdate());
                                batchPicListBean.setBatchnum(batchBean.getBatchnum());
                                batchPicListBean.setSaleprice(batchBean.getSaleprice());
                                batchPicListBean.setSellprice(batchBean.getSellprice());
                                batchPicListBean.setCardBeanlist(mpicBancthList);
                                Gson gson = new Gson();
                                String editJson = gson.toJson(batchPicListBean);
                                //TODO:修改批次报错
                                requestNeteditCommodity(editJson, commodityNum, price, batchBean, groupPostion, childPostion);
                            } else {
                                setBatchMsg(batchBean, price, commodityNum, groupPostion, childPostion);
                                transferExAdapter.notifyDataSetChanged();
                            }
                        }


                    } else {
                        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
                        if (type.equals("add")) {
                            delectBtach(groupPostion, childPostion);
                        } else if (type.equals("edit")) {
                            //TODO:删除批次
                            String gethbid = dataBean.getmSelectMap().get(childPostion).getBhid() + "";
                            if (!TextUtils.isEmpty(gethbid)) {
                                bhid += dataBean.getmSelectMap().get(childPostion).getBhid() + "";
                            }
                            delectBtach(groupPostion, childPostion);

                        }
                    }


                } else {
                    showToast("请输入零售价!");
                    return;
                }


            }
        });
    }

    /**
     * 店铺信息
     */
    private void requestNetShopList() {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shoplistfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", "1")
                .addParams("limit", "1000")
                .addParams("cid", cid + "")
                .addParams("shopid", "")
                .addParams("shopname", "")
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息AddSalesActivity" + e.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddSalesActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddSalesActivity" + response.toString());
                            Gson gson = new Gson();
                            ShopListBean shopSelectBean = gson.fromJson(response, ShopListBean.class);
                            if (shopSelectBean != null) {
                                List<ShopListBean.DataBean> shoplist = shopSelectBean.getData();
                                if (shoplist != null) {
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, customizeDialog);
                                    SelectShopClick(customizeDialog, alertDialog, shoplist);
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddSalesActivity" + e1.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 修改批次
     */
    private void requestNeteditCommodity(final String batchBean, final String commodityNum, final String price, final SelectBatchBean selctbatchBean, final int groupPostion, final int childPostion) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.editBatch_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + batchBean);
        post.addParams("batchBean", batchBean);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息AddSalesActivity" + e.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddSalesActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddSalesActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("修改批次成功!");
                                    setBatchMsg(selctbatchBean, price, commodityNum, groupPostion, childPostion);
                                } else {
                                    showToast("修改批次失败!");
                                }
                            } else {
                                Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddSalesActivity" + e1.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 修改数据
     *
     * @param selctbatchBean
     * @param price
     * @param commodityNum
     * @param groupPostion
     * @param childPostion
     */
    private void setBatchMsg(SelectBatchBean selctbatchBean, String price, String commodityNum, int groupPostion, int childPostion) {
        SelectBatchBean bean = new SelectBatchBean();
        bean.setBatchnum(selctbatchBean.getBatchnum());
        bean.setEnterprice(selctbatchBean.getEnterprice());
        bean.setBatchdate(selctbatchBean.getBatchdate());
        bean.setSaleprice(selctbatchBean.getSaleprice());
        bean.setSellprice(price);
        bean.setIsdel(selctbatchBean.getIsdel());
        bean.setRemark(selctbatchBean.getRemark());
        bean.setKcsum(selctbatchBean.getKcsum());
        bean.setPbatchid(selctbatchBean.getPbatchid());
        bean.setBhid(selctbatchBean.getBhid());
        bean.setSid(selctbatchBean.getSid());
        bean.setNum(Integer.parseInt(commodityNum));
        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        int kcsum = Integer.parseInt(selctbatchBean.getKcsum());
        int num = Integer.parseInt(commodityNum);
        if (kcsum < num) {
            showToast("销货数量不能高于剩余库存数量!");
            return;
        }
        if (!commodityNum.equals("0")) {
            dataBean.getmSelectMap().set(childPostion, bean);
        } else {
            dataBean.getmSelectMap().remove(childPostion);
        }
        setNumMoney(dataBean);
        if (dataBean.getmSelectMap() != null) {
            if (dataBean.getmSelectMap().size() != 0) {
                mSelectCommodityMap.put(dataBean.getProId().getProId() + "", dataBean);
            } else {
                mSelectCommodityList.remove(groupPostion);
                mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
            }
        } else {
            mSelectCommodityList.remove(groupPostion);
            mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
        }

        setTotalNumMoney();
        showToast("修改批次信息成功!");

    }

    /**
     * 设置合计总数
     */
    private void setTotalNumMoney() {
        Iterator<String> iterator = mSelectCommodityMap.keySet().iterator();
        int totalNum = 0;
        double totalmoney = 0.0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            TransferCommodityBean.DataBean dataBean = mSelectCommodityMap.get(next);
            String nums = dataBean.getmSelectNum() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                totalNum = totalNum + dataBean.getmSelectNum();
                String enterprice = dataBean.getmSelectMoney() + "";
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(enterprice);
                    BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                    BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                    BigDecimal add = totalmoneyBigDecimal.add(enterBigDecimal);
                    totalmoney = add.doubleValue();
                } else {
                    totalmoney = totalmoney + dataBean.getmSelectNum() * 0.0;
                }
            }
        }
        tvBatchNum.setText(totalNum + "");
        tvTotalMoney.setText(totalmoney + "");
    }


    /**
     * 设置个数和钱数
     *
     * @param Selectbean
     */
    private void setNumMoney(TransferCommodityBean.DataBean Selectbean) {
        int totalNum = 0;
        double totalmoney = 0.0;
        for (int i = 0; i < Selectbean.getmSelectMap().size(); i++) {
            SelectBatchBean batchBean = Selectbean.getmSelectMap().get(i);
            String enterprice = batchBean.getSellprice();
            double entermoney = 0.0;
            if (TextUtils.isEmpty(enterprice)) {
                entermoney = 0.0;
            } else {
                entermoney = Double.parseDouble(enterprice);
            }
            totalNum = totalNum + batchBean.getNum();
            double selectNum = Double.parseDouble(String.valueOf(batchBean.getNum()));
            BigDecimal totalmoneyDecimal = new BigDecimal(totalmoney);
            BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
            BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
            BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
            totalmoney = totalmoneyDecimal.add(multiply).doubleValue();
        }
        Selectbean.setmSelectNum(totalNum);
        Selectbean.setmSelectMoney(totalmoney);

        LogUtils.i("个数===" + totalNum + "钱数----" + totalmoney);
    }

    /**
     * 选择店铺
     *
     * @param customizeDialog
     * @param alertDialog
     * @param shoplist
     */
    private void SelectShopClick(View customizeDialog, final AlertDialog alertDialog, final List<ShopListBean.DataBean> shoplist) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        tvselct.setText("选择店铺");
        selectShopListAdapter = new SelectPurchaseShopAdapter(AddSalesActivity.this, shoplist, alertDialog);
        selectList.setAdapter(selectShopListAdapter);
        selectShopListAdapter.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                purchaseShopId = shoplist.get(position).getShopid() + "";
                purchaseShopName = shoplist.get(position).getShopname() + "";
                addPurchaseShop.setText(purchaseShopName + "");
            }
        });

        imgxDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
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

                        try {
                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {

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
                                        TransferCommodityBean.DataBean dataBean = new TransferCommodityBean.DataBean();
                                        TransferCommodityBean.DataBean.ProIdBean proIdBean = new TransferCommodityBean.DataBean.ProIdBean();
                                        proIdBean.setProId(goodsBean.getProId());
                                        proIdBean.setRemark(goodsBean.getRemark());
                                        proIdBean.setProbzq(goodsBean.getProbzq());
                                        proIdBean.setState(goodsBean.getState());
                                        proIdBean.setMeas(goodsBean.getMeas());
                                        proIdBean.setProCode(goodsBean.getProCode());
                                        proIdBean.setBarcode(goodsBean.getBarcode());
                                        proIdBean.setProtype(goodsBean.getProtype());
                                        proIdBean.setProName(goodsBean.getProName());
                                        proIdBean.setTrademark(goodsBean.getTrademark());
                                        proIdBean.setBatchCount(goodsBean.getBatchCount());
                                        proIdBean.setMeaunit(goodsBean.getMeaunit());
                                        proIdBean.setMernameorplace(goodsBean.getMernameorplace());
                                        dataBean.setProId(proIdBean);
                                        List<SeePurchaserBean.DataBean.PclistBean> pclist = dataBeanList.get(i).getPclist();
                                        setseeNumMoney(pclist, dataBean);
                                        mSelectCommodityList.add(dataBean);
                                        mSelectCommodityMap.put(goodsBean.getProId() + "", dataBean);
                                    }
                                    transferExAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                            Toast.makeText(AddSalesActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 编辑设置个数和钱数
     */
    private void setseeNumMoney(List<SeePurchaserBean.DataBean.PclistBean> pclist, TransferCommodityBean.DataBean dataBean) {
        int totalNum = 0;
        double totalmoney = 0.0;
        List<SelectBatchBean> selectBatchBeanList = new ArrayList<>();
        for (int i = 0; i < pclist.size(); i++) {
            SeePurchaserBean.DataBean.PclistBean pclistBean = pclist.get(i);
            SelectBatchBean selectBatchBean = new SelectBatchBean();
            selectBatchBean.setPbatchid(pclistBean.getPbatchid().getPbatchid());
            selectBatchBean.setBatchdate(pclistBean.getPbatchid().getBatchdateStr());
            selectBatchBean.setBatchnum(pclistBean.getPbatchid().getBatchnum());
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
            selectBatchBean.setSaleprice(saleprice);
            selectBatchBean.setSellprice(sellprice + "");
            selectBatchBean.setNum(Integer.parseInt(pclistBean.getEnterqty()));
            //剩余库存
            selectBatchBean.setKcsum(pclistBean.getKcsum() + "");
            selectBatchBean.setEnterprice(enterprice1);
            selectBatchBean.setBhid(pclistBean.getBhid());
            selectBatchBeanList.add(selectBatchBean);
            String nums = pclistBean.getEnterqty() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                int enterqty = Integer.parseInt(pclistBean.getEnterqty());
                totalNum = totalNum + enterqty;
                String enterprice = pclistBean.getEnterprice();
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(enterprice);
                    double selectNum = Double.parseDouble(String.valueOf(pclistBean.getEnterqty()));
                    BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                    BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
                    BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                    BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
                    BigDecimal add = totalmoneyBigDecimal.add(multiply);
                    totalmoney = add.doubleValue();
                } else {
                    totalmoney = totalmoney + enterqty * 0.0;
                }
            }
        }
        dataBean.setmSelectNum(totalNum);
        dataBean.setmSelectMoney(totalmoney);
        dataBean.setmSelectMap(selectBatchBeanList);
        LogUtils.i("个数===" + totalNum + "钱数----" + totalmoney);
    }
}
