package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.mtecc.mmp.invoicing.activity.distributor.DistributorLIstActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.SelectPurchaseShopAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PurchaseCommitBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectCommodityBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
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
 * 退货订单
 */
public class AddReturnsActivity extends BaseActivity {
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
    @BindView(R.id.tv_see_code)
    TextView tvSeeCode;
    @BindView(R.id.add_purchase_code)
    EditText addPurchaseCode;
    @BindView(R.id.tv_orders_text)
    TextView tvOrdersText;
    @BindView(R.id.add_returns_orders)
    EditText addReturnsOrders;
    @BindView(R.id.tv_select_orders)
    TextView tvSelectOrders;
    @BindView(R.id.tv_type_text)
    TextView tvTypeText;
    @BindView(R.id.add_purchase_merchants)
    EditText addPurchaseMerchants;
    @BindView(R.id.tv_select_user)
    TextView tvSelectUser;
    @BindView(R.id.add_purchase_shop)
    EditText addPurchaseShop;
    @BindView(R.id.add_purchase_miaoshu)
    EditText addPurchaseMiaoshu;
    @BindView(R.id.commodity_add_iteam)
    ImageView commodityAddIteam;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_reason_text)
    TextView tvReasonText;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.add_purchase_commit)
    TextView addPurchaseCommit;
    @BindView(R.id.ll_orders)
    LinearLayout llOrders;
    private int cid = 0;
    private String user_id;
    private Map<String, TransferCommodityBean.DataBean> mSelectCommodityMap = new HashMap<>();
    private List<TransferCommodityBean.DataBean> mSelectCommodityList = new ArrayList<>();
    private TransferExAdapter transferExAdapter;
    private String shopid;
    private AlertDialog showDialog;
    private String psType;
    String purchaseShopId = "";//店铺id
    String purchaseShopName = "";//店铺id
    String purchaseHdtitle = "";//货单原因
    String purchaseMerchantId = "";//供货商
    String purchaseMerchantName = "";//供货商名字
    String purchaseMerchantCode = "";//订单编号
    private PrichaseIncomeBean.DataBean hdidBean;
    private String url;
    private String enterprice;
    private boolean isuseradmin;

    @Override
    public void widgetClick(View v) {


    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        psType = parms.getString(InvoicingConstants.TYPE);
        hdidBean = (PrichaseIncomeBean.DataBean) parms.getSerializable(InvoicingConstants.hdid);
        View view1 = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, view1);
        showDialog.dismiss();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        isuseradmin = PreferencesUtils.getBoolean(this, InvoicingConstants.isuseradmin, false);
        if (!isuseradmin) {
            shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
            String shopName = PreferencesUtils.getString(this, InvoicingConstants.SHOP_Name, "");
            purchaseShopId = shopid;
            purchaseShopName = shopName;
            addPurchaseShop.setText(purchaseShopName + "");
        }
        ivBack.setVisibility(View.VISIBLE);
        //显示采购订单编号
        llOrders.setVisibility(View.VISIBLE);
        tvReasonText.setText("退货原因");
        addPurchaseMiaoshu.setHint("请输入退货原因");
        if (psType.equals(InvoicingConstants.PURCHASE)) {
            tvTitle.setText("采购退货单");
            tvTypeText.setText("供货商");
            tvOrdersText.setText("采购订单号");
            addPurchaseMerchants.setHint("请选择供货商");
        } else if (psType.equals(InvoicingConstants.SALES)) {
            tvTitle.setText("销售退货单");
            tvTypeText.setText("分销商");
            tvOrdersText.setText("销售订单号");
            addPurchaseMerchants.setHint("请选择分销商");
        }

        if (hdidBean != null) {
            purchaseShopName = hdidBean.getShopname();//店铺id
            purchaseShopId = hdidBean.getShopid();//店铺id
            purchaseMerchantId = hdidBean.getMerid();//店铺id
            purchaseMerchantName = hdidBean.getMername();//供货商名字
            purchaseMerchantCode = hdidBean.getHdid();//订单编号
            addReturnsOrders.setText(hdidBean.getHdid() + "");
            addPurchaseMerchants.setText(hdidBean.getMername() + "");
            addPurchaseShop.setText(hdidBean.getShopname() + "");
        }

        transferExAdapter = new TransferExAdapter(this, mSelectCommodityList, "show");
        addBatchListView.setAdapter(transferExAdapter);
        transferExAdapter.notifyDataSetChanged();
        tvBatchNum.setText("0");
        tvTotalMoney.setText("0.0");
        //删除商品
        transferExAdapter.setOndelListerner(new TransferExAdapter.OnDelListerner() {
            @Override
            public void onDelClick(int groupPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, exitView);
                delClick(exitView, dialog, groupPostion);
            }
        });

        //编辑批次--进货价--数量
        transferExAdapter.setOnEditBtachLIsterner(new TransferExAdapter.OnEditBtachListerner() {
            @Override
            public void onEditClick(int groupPostion, int childPostion) {
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.edit_select_commodoty_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, customizeDialog);
                editClick(customizeDialog, alertDialog, groupPostion, childPostion);
            }
        }); //编辑批次--进货价--数量
        transferExAdapter.setOnDelBtachLIsterner(new TransferExAdapter.OnDelBtachListerner() {
            @Override
            public void onDelBtachClick(int groupPostion, int childPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, exitView);
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
        addPurchaseMerchants.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    purchaseMerchantName = "";
                    purchaseMerchantId = "";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @OnClick({R.id.add_returns_orders, R.id.rl_back, R.id.add_purchase_commit, R.id.add_purchase_shop, R.id.tv_select_user, R.id.commodity_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_select_user:
                //选择供货商
                if (hdidBean == null) {
                    Intent supplierintent = new Intent();
                    Bundle distributorbundle = new Bundle();
                    if (psType.equals(InvoicingConstants.PURCHASE)) {
                        distributorbundle.putString(InvoicingConstants.TYPE, InvoicingConstants.PURCHASE);
                        distributorbundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Merchants_TYPE);
                        distributorbundle.putString(InvoicingConstants.Merchants_TYPE, InvoicingConstants.Merchants_Select);
                    } else if (psType.equals(InvoicingConstants.SALES)) {
                        distributorbundle.putString(InvoicingConstants.TYPE, InvoicingConstants.SALES);
                        distributorbundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                        distributorbundle.putString(InvoicingConstants.Distributor_TYPE, InvoicingConstants.Distributor_Select);
                    }
                    supplierintent.setClass(AddReturnsActivity.this, DistributorLIstActivity.class);
                    supplierintent.putExtras(distributorbundle);
                    startActivityForResult(supplierintent, 3);

                }

                break;
            case R.id.add_returns_orders:
                //选择订单号
                if (hdidBean == null) {
                    Intent purchaseInt = new Intent(AddReturnsActivity.this, PurchaseOrderListActivity.class);
                    Bundle purchasebundle = new Bundle();
                    purchasebundle.putString(InvoicingConstants.STOCK_TYPE, InvoicingConstants.ADDRETURN);
                    purchasebundle.putString(InvoicingConstants.TYPE, psType);
                    purchasebundle.putString(InvoicingConstants.Merchants_ID, purchaseMerchantId + "");
                    purchasebundle.putString(InvoicingConstants.SHOP_ID, purchaseShopId + "");
                    purchaseInt.putExtras(purchasebundle);
                    startActivityForResult(purchaseInt, 4);
                }
                break;
            case R.id.add_purchase_shop:
                //选择店铺
                if (isuseradmin) {
                    if (hdidBean == null) {
                        requestNetShopList();
                    }
                }

                break;
            case R.id.commodity_add_iteam:
                //选择商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(AddReturnsActivity.this, ReturnsCommodityListActivity.class);
                if (TextUtils.isEmpty(purchaseShopId)) {
                    showToast("请选择店铺");
                    return;
                }
                bundle.putString(InvoicingConstants.shopId, purchaseShopId + "");
                bundle.putString(InvoicingConstants.hdid, purchaseMerchantCode + "");
                bundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                bundle.putString(InvoicingConstants.TYPE, psType);
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
        purchaseMerchantCode = addReturnsOrders.getText().toString().trim();//货单描述

        if (TextUtils.isEmpty(purchaseMerchantName) || TextUtils.isEmpty(purchaseMerchantId)) {
            showToast("商户不能为空!");
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
        purchaseCommitBean.setHdid(purchaseMerchantCode);
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
                selectCommodityBean.setSid(dataBean.getSid());
                selectCommodityBean.setMeaunit(dataBean.getProId().getMeaunit());
                if (dataBean.getmSelectMap() != null && dataBean.getmSelectMap().size() != 0) {
                    selectCommodityBean.setmSelectMap(dataBean.getmSelectMap());
                }
                mSelectCommodityList.add(selectCommodityBean);
            }
            purchaseCommitBean.setmSelectCommodityMap(mSelectCommodityList);
        }
        if (TextUtils.isEmpty(purchaseHdtitle) || TextUtils.isEmpty(purchaseHdtitle)) {
            showToast("退货原因不能为空!");
            showDialog.dismiss();
            return;
        }
        Gson gson = new Gson();
        String commitJson = gson.toJson(purchaseCommitBean);
        LogUtils.i("提交采购单===" + commitJson);
        requestNetCommit(commitJson);
    }

    /**
     * 提交订单
     */
    private void requestNetCommit(final String purchaseCommitBean) {
        if (psType.equals(InvoicingConstants.PURCHASE)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.moveTenter_URL;
        } else if (psType.equals(InvoicingConstants.SALES)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.moveSaleBack_URL;
        }
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + purchaseCommitBean);
        PostFormBuilder post = OkHttpUtils.post();
        post.tag(this)
                .addParams("purchaseCommitBean", purchaseCommitBean)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                    finish();
//                                    View exitView = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.exit_dialog);
//                                    AlertDialog dialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, exitView);
//                                    exitClick(exitView, dialog);
                                } else {
                                    showToast("提交订单失败!");
                                }
                            } else {
                                Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {

                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                    if (psType.equals(InvoicingConstants.PURCHASE)) {
                        purchaseMerchantName = extras.getString(InvoicingConstants.Merchants_Name);
                        purchaseMerchantId = extras.getString(InvoicingConstants.Merchants_ID);
                    } else if (psType.equals(InvoicingConstants.SALES)) {
                        purchaseMerchantName = extras.getString(InvoicingConstants.Distributor_Name);
                        purchaseMerchantId = extras.getString(InvoicingConstants.Distributor_ID);
                    }
                    addPurchaseMerchants.setText(purchaseMerchantName);
                    addReturnsOrders.setText("");
                    addPurchaseShop.setText("");
                    purchaseShopId = "";
                    purchaseShopName = "";
                    purchaseMerchantCode = "";
                }
                break;
            case 4:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    PrichaseIncomeBean.DataBean dataBean = (PrichaseIncomeBean.DataBean) extras.getSerializable(InvoicingConstants.hdid);
                    addReturnsOrders.setText(dataBean.getHdid() + "");
                    addPurchaseMerchants.setText(dataBean.getMername() + "");
                    addPurchaseShop.setText(dataBean.getShopname() + "");
                    purchaseMerchantName = dataBean.getMername();
                    purchaseMerchantId = dataBean.getMerid();
                    purchaseShopId = dataBean.getShopid();
                    purchaseShopName = dataBean.getShopname();
                    purchaseMerchantCode = dataBean.getHdid();
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
//                Intent intent = new Intent(AddReturnsActivity.this, AddReturnsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("type", "income");
//                intent.putExtras(bundle);
//                startActivity(intent);


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
                mSelectCommodityList.remove(dataBean);
                mSelectCommodityMap.remove(dataBean.getProId().getProId() + "");
                setTotalNumMoney();
                transferExAdapter.notifyDataSetChanged();
                showToast("删除商品成功!");
            }
        });
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
        });
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
        if (psType.equals(InvoicingConstants.PURCHASE)) {
            tvText.setText("进货价");
        } else {
            tvText.setText("零售价");

        }
        if (dataBean != null) {
            SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
            if (psType.equals(InvoicingConstants.PURCHASE)) {
                enterprice = batchBean.getEnterprice();
            } else {
                enterprice = batchBean.getSellprice();
            }
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
                SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
                String enterprice = batchBean.getSellprice();
                if (!TextUtils.isEmpty(price)) {
                    if (!price.equals(enterprice)) {
                        List<BatchPicListBean.CardBean> mpicBancthList = new ArrayList<BatchPicListBean.CardBean>();
                        BatchPicListBean batchPicListBean = new BatchPicListBean();
                        if (psType.equals(InvoicingConstants.PURCHASE)) {
                            batchPicListBean.setEnterprice(price);
                            batchPicListBean.setSellprice(batchBean.getSellprice());
                        } else {
                            batchPicListBean.setEnterprice(batchBean.getEnterprice());
                            batchPicListBean.setSellprice(price);
                        }
                        batchPicListBean.setPbatchid(batchBean.getPbatchid());
                        batchPicListBean.setBatchdate(batchBean.getBatchdate());
                        batchPicListBean.setBatchnum(batchBean.getBatchnum());
                        batchPicListBean.setSaleprice(batchBean.getSaleprice());
                        batchPicListBean.setCardBeanlist(mpicBancthList);
                        Gson gson = new Gson();
                        String editJson = gson.toJson(batchPicListBean);
                        //TODO:修改批次报错
                        requestNeteditCommodity(editJson, commodityNum, price, batchBean, groupPostion, childPostion);
                    } else {
                        setBatchMsg(batchBean, price, commodityNum, groupPostion, childPostion);
                        transferExAdapter.notifyDataSetChanged();
                    }

                } else {
                    if (psType.equals(InvoicingConstants.PURCHASE)) {
                        showToast("请输入进货价!");
                    } else {
                        showToast("请输入零售价!");

                    }
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
                            LogUtils.d("错误信息AddPurchaseActivity" + e.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddPurchaseActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddPurchaseActivity" + response.toString());
                            Gson gson = new Gson();
                            ShopListBean shopSelectBean = gson.fromJson(response, ShopListBean.class);
                            if (shopSelectBean != null) {
                                List<ShopListBean.DataBean> shoplist = shopSelectBean.getData();
                                if (shoplist != null) {
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddReturnsActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(AddReturnsActivity.this, false, customizeDialog);
                                    SelectShopClick(customizeDialog, alertDialog, shoplist);
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddPurchaseActivity" + e1.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                            LogUtils.d("错误信息AddPurchaseActivity" + e.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddPurchaseActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddPurchaseActivity" + response.toString());
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
                                Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddPurchaseActivity" + e1.toString());
                            Toast.makeText(AddReturnsActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
        bean.setEnterprice(price);
        bean.setBatchdate(selctbatchBean.getBatchdate());
        bean.setSaleprice(selctbatchBean.getSaleprice());
        bean.setSellprice(selctbatchBean.getSellprice());
        bean.setIsdel(selctbatchBean.getIsdel());
        bean.setRemark(selctbatchBean.getRemark());
        bean.setCgNum(selctbatchBean.getCgNum());
        bean.setBhid(selctbatchBean.getBhid());
        bean.setKcsum(selctbatchBean.getKcsum());
        bean.setPbatchid(selctbatchBean.getPbatchid());
        bean.setSid(selctbatchBean.getSid());
        bean.setBhid(selctbatchBean.getBhid());
        bean.setNum(Integer.parseInt(commodityNum));
        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        String kcsum1 = selctbatchBean.getKcsum();
        if (TextUtils.isEmpty(kcsum1)) {
            kcsum1 = "0";
        }
        String cgNum = selctbatchBean.getCgNum();
        if (TextUtils.isEmpty(cgNum)) {
            cgNum = "0";
        }
        int kcsum = Integer.parseInt(kcsum1);
        int num = Integer.parseInt(commodityNum);
        int cnum = Integer.parseInt(cgNum);
        //TODO:
        if (kcsum < num) {
            if (psType.equals(InvoicingConstants.PURCHASE)) {
                showToast("采购退货数量不能高于剩余库存数量!");
            } else if (psType.equals(InvoicingConstants.SALES)) {
                showToast("销货退货数量不能高于剩余库存数量!");

            }
            return;
        } else {
            if (cnum < num) {
                if (psType.equals(InvoicingConstants.PURCHASE)) {
                    showToast("采购退货数量不能高于采购数量!");
                } else if (psType.equals(InvoicingConstants.SALES)) {
                    showToast("销货退货数量不能高于销货数量!");

                }
                return;
            }
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
        SelectPurchaseShopAdapter selectShopList = new SelectPurchaseShopAdapter(AddReturnsActivity.this, shoplist, alertDialog);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
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


}
