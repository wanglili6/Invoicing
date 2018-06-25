package com.mtecc.mmp.invoicing.activity.transfer;

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
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.SelectPurchaseShopAdapter;
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
 * 调拨,出库订单
 */
public class AddTransferActivity extends BaseActivity {
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
    @BindView(R.id.add_transfer_income_shop)
    EditText addTransferIncomeShop;
    @BindView(R.id.add_transfer_out_shop)
    EditText addTransferOutShop;
    @BindView(R.id.add_transfer_out_reason)
    EditText addTransferOutReason;
    @BindView(R.id.commodity_add_iteam)
    ImageView commodityAddIteam;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.add_purchase_commit)
    TextView addPurchaseCommit;
    @BindView(R.id.ll_reson)
    LinearLayout llReason;
    @BindView(R.id.ll_in_shop)
    LinearLayout llInShop;
    private int cid = 0;
    private String user_id;
    private Map<String, TransferCommodityBean.DataBean> mSelectCommodityMap = new HashMap<>();
    private List<TransferCommodityBean.DataBean> mSelectCommodityList = new ArrayList<>();
    private TransferExAdapter transferExAdapter;
    private String shopid = "";
    private String shopName = "";
    private AlertDialog showDialog;
    String outReason = "";//出库原因
    String incomeShopId = "";//店铺id
    String incomeShopName = "";//店铺id
    String outShopId = "";//店铺id
    String outShopName = "";//店铺id
    private boolean isuseradmin;
    private String stockType;
    private String url;

    @Override
    public void widgetClick(View v) {


    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        stockType = parms.getString(InvoicingConstants.STOCK_TYPE);
        View view1 = ShowDalogUtils.showCustomizeDialog(AddTransferActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddTransferActivity.this, false, view1);
        showDialog.dismiss();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        shopName = PreferencesUtils.getString(this, InvoicingConstants.SHOP_Name, "");
        isuseradmin = PreferencesUtils.getBoolean(this, InvoicingConstants.isuseradmin, false);
        if (!isuseradmin) {
            outShopId = shopid;
            outShopName = shopName;
            addTransferOutShop.setText(shopName + "");
        }
        ivBack.setVisibility(View.VISIBLE);
        tvTotalMoney.setVisibility(View.GONE);
        if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
            tvTitle.setText("调拨单");
            llInShop.setVisibility(View.VISIBLE);
            llReason.setVisibility(View.GONE);
        } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
            tvTitle.setText("出库单");
            llInShop.setVisibility(View.GONE);
            llReason.setVisibility(View.VISIBLE);
        }

        transferExAdapter = new TransferExAdapter(this, mSelectCommodityList, "noShow");
        addBatchListView.setAdapter(transferExAdapter);
        transferExAdapter.notifyDataSetChanged();
        tvBatchNum.setText("0");
        tvTotalMoney.setText("0.0");
        //删除商品
        transferExAdapter.setOndelListerner(new TransferExAdapter.OnDelListerner() {
            @Override
            public void onDelClick(int groupPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddTransferActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddTransferActivity.this, false, exitView);
                delClick(exitView, dialog, groupPostion);
            }
        });

        //编辑批次--进货价--数量
        transferExAdapter.setOnEditBtachLIsterner(new TransferExAdapter.OnEditBtachListerner() {
            @Override
            public void onEditClick(int groupPostion, int childPostion) {
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddTransferActivity.this, R.layout.edit_select_commodoty_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(AddTransferActivity.this, false, customizeDialog);
                editClick(customizeDialog, alertDialog, groupPostion, childPostion);
            }
        });
        //删除批次
        transferExAdapter.setOnDelBtachLIsterner(new TransferExAdapter.OnDelBtachListerner() {
            @Override
            public void onDelBtachClick(int groupPostion, int childPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddTransferActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddTransferActivity.this, false, exitView);
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
        return R.layout.activity_add_transfer;
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


    @OnClick({R.id.rl_back, R.id.add_purchase_commit, R.id.add_transfer_income_shop, R.id.add_transfer_out_shop, R.id.commodity_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_transfer_income_shop:
                //选择店铺
                requestNetShopList("income");
                break;
            case R.id.add_transfer_out_shop:
                //选择店铺
                if (isuseradmin) {
                    requestNetShopList("out");
                }
                break;
            case R.id.commodity_add_iteam:
                //选择商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(AddTransferActivity.this, TransferommodityListActivity.class);
                bundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                if (TextUtils.isEmpty(outShopId)) {
                    showToast("请选择出库店铺");
                    return;
                }
                bundle.putString(InvoicingConstants.shopId, outShopId + "");
                bundle.putString(InvoicingConstants.TYPE, "noShow");
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
        outReason = addTransferOutReason.getText().toString().trim();
        incomeShopName = addTransferIncomeShop.getText().toString().trim();
        outShopName = addTransferOutShop.getText().toString().trim();


        if (TextUtils.isEmpty(outShopName)) {
            showToast("出库店铺不能为空!");
            showDialog.dismiss();
            return;
        }
        PurchaseCommitBean purchaseCommitBean = new PurchaseCommitBean();
        purchaseCommitBean.setOutShopId(outShopId);
        purchaseCommitBean.setOutShopName(outShopName);
        purchaseCommitBean.setPurchaseUserId(user_id);
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
        Gson gson = new Gson();
        if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
            if (TextUtils.isEmpty(incomeShopName)) {
                showToast("入库店铺不能为空!");
                showDialog.dismiss();
                return;
            }
            purchaseCommitBean.setPurchaseShopId(incomeShopId);
            purchaseCommitBean.setPurchaseShopName(incomeShopName);
            String commitJson = gson.toJson(purchaseCommitBean);
            LogUtils.i("提交采购单===" + commitJson);
            //提交调拨
            requestNetCommit(commitJson);
        } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
            //提交出库
            purchaseCommitBean.setOutReason(outReason);
            String commitJson = gson.toJson(purchaseCommitBean);
            LogUtils.i("提交采购单===" + commitJson);
            requestNetCommit(commitJson);
        }
    }

    /**
     * 提交订单
     */
    private void requestNetCommit(final String commitJson) {
        if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.tranStockGoods_URL;
        } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
            //提交出库
            url = InvoicingConstants.BASE_URL + InvoicingConstants.moveStockGoods_URL;
        }


        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + commitJson);
        PostFormBuilder post = OkHttpUtils.post();
        post.tag(this)
                .addParams("purchaseCommitBean", commitJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddTransferActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                    showToast("提交成功!");
                                    finish();
                                } else {
                                    showToast("提交失败!");
                                }
                            } else {
                                Toast.makeText(AddTransferActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {

                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddTransferActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
        }
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
                dialog.dismiss();
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
        final EditText tvEnterPrice = customizeDialog.findViewById(R.id.commodity_enter_price);
        final EditText tvNum = customizeDialog.findViewById(R.id.commodity_num);
        final EditText tvname = customizeDialog.findViewById(R.id.commodity_enter_name);
        final LinearLayout llEnterPlace = customizeDialog.findViewById(R.id.ll_enter_place);
        final TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        llEnterPlace.setVisibility(View.GONE);
        if (dataBean != null) {
            SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
            String enterprice = batchBean.getEnterprice();
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
                String price = tvEnterPrice.getText().toString().trim();
                String commodityNum = tvNum.getText().toString().trim();
                SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
                setBatchMsg(batchBean, price, commodityNum, groupPostion, childPostion);
                transferExAdapter.notifyDataSetChanged();
                alertDialog.dismiss();

            }
        });
    }

    /**
     * 店铺信息
     */
    private void requestNetShopList(final String type) {
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
                            Toast.makeText(AddTransferActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddTransferActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(AddTransferActivity.this, false, customizeDialog);
                                    SelectShopClick(customizeDialog, alertDialog, shoplist, type);
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddPurchaseActivity" + e1.toString());
                            Toast.makeText(AddTransferActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
        bean.setKcsum(selctbatchBean.getKcsum());
        bean.setPbatchid(selctbatchBean.getPbatchid());
        bean.setSid(selctbatchBean.getSid());
        bean.setNum(Integer.parseInt(commodityNum));
        TransferCommodityBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        int kcsum = Integer.parseInt(selctbatchBean.getKcsum());
        int num = Integer.parseInt(commodityNum);
        if (kcsum < num) {
            showToast("调拨数量不能高于剩余库存数量!");
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
            String enterprice = batchBean.getEnterprice();
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
     * @param type
     */
    private void SelectShopClick(View customizeDialog, final AlertDialog alertDialog, final List<ShopListBean.DataBean> shoplist, final String type) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        tvselct.setText("选择店铺");
        SelectPurchaseShopAdapter selectShopList = new SelectPurchaseShopAdapter(AddTransferActivity.this, shoplist, alertDialog);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                if (type.equals("income")) {
                    incomeShopId = shoplist.get(position).getShopid() + "";
                    incomeShopName = shoplist.get(position).getShopname() + "";
                    addTransferIncomeShop.setText(incomeShopName + "");

                } else {
                    outShopId = shoplist.get(position).getShopid() + "";
                    outShopName = shoplist.get(position).getShopname() + "";
                    addTransferOutShop.setText(outShopName + "");
                }
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
