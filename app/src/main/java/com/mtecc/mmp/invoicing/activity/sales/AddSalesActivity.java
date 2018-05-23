package com.mtecc.mmp.invoicing.activity.sales;

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
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.activity.sales.adapter.SelectCommpdityExAdapter;
import com.mtecc.mmp.invoicing.activity.sales.adapter.SelectEmployeeAdapter;
import com.mtecc.mmp.invoicing.activity.sales.adapter.SelectSalesShopAdapter;
import com.mtecc.mmp.invoicing.activity.sales.bean.SalesCommitBean;
import com.mtecc.mmp.invoicing.activity.sales.bean.SelectCommodityBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
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
 * 采购订单
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
    @BindView(R.id.add_sales_code)
    EditText addSalesCode;
    @BindView(R.id.add_sales_merchants)
    EditText addSalesMerchants;
    @BindView(R.id.add_sales_shop)
    EditText addSalesShop;
    @BindView(R.id.add_sales_user)
    EditText addSalesUser;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.add_sales_commit)
    TextView addSalesCommit;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_see_purchases_sales)
    TextView tvSeePurchasesSales;
    @BindView(R.id.tv_see_code)
    TextView tvSeeCode;
    String salesCode = "";//订单号
    String salesShopId = "";//店铺id
    String salesShopName = "";//店铺id
    String salesUserId = "";//审核人
    String salesUserName = "";//审核人
    String salesMerchantId = "";//供货商
    String salesMerchantName = "";//供货商名字
    private int cid = 0;
    private String user_id;
    private Map<String, CommodityListBean.DataBean> mSelectCommodityMap = new HashMap<>();
    private List<CommodityListBean.DataBean> mSelectCommodityList = new ArrayList<>();
    private SelectCommpdityExAdapter selectCommpdityExAdapter;
    private String shopid;
    private AlertDialog showDialog;

    @Override
    public void widgetClick(View v) {


    }


    @Override
    public void initParms(Bundle parms) {
        View view1 = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, view1);
        showDialog.dismiss();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        String type = parms.getString("type");
        long timeMillis = System.currentTimeMillis();
        if (type.equals("income")) {
            tvTitle.setText("采购订单");
            tvSeeCode.setText("销货单编号");
            addSalesCode.setText("XSJH" + cid + timeMillis);
            salesCode = addSalesCode.getText().toString().trim();
        } else if (type.equals("out")){
            tvSeeCode.setText("销售退货单编号");
            tvTitle.setText("退货订单");
            addSalesCode.setText("XSTH" + cid + timeMillis);
            salesCode = addSalesCode.getText().toString().trim();
        }
        //时间戳

        selectCommpdityExAdapter = new SelectCommpdityExAdapter(this, mSelectCommodityList);
        addBatchListView.setAdapter(selectCommpdityExAdapter);
        selectCommpdityExAdapter.notifyDataSetChanged();
        tvBatchNum.setText("0");
        tvTotalMoney.setText("0.0");
        //删除商品
        selectCommpdityExAdapter.setOndelListerner(new SelectCommpdityExAdapter.OnDelListerner() {
            @Override
            public void onDelClick(int groupPostion) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, exitView);
                delClick(exitView, dialog, groupPostion);
            }
        });

        //编辑批次--进货价--数量
        selectCommpdityExAdapter.setOnEditBtachLIsterner(new SelectCommpdityExAdapter.OnEditBtachListerner() {
            @Override
            public void onEditClick(int groupPostion, int childPostion) {
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.edit_select_commodoty_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, customizeDialog);
                editClick(customizeDialog, alertDialog, groupPostion, childPostion);
            }
        }); //编辑批次--进货价--数量
        selectCommpdityExAdapter.setOnDelBtachLIsterner(new SelectCommpdityExAdapter.OnDelBtachListerner() {
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
        return R.layout.activity_add_sales;
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


    @OnClick({R.id.rl_back, R.id.add_sales_commit, R.id.add_sales_shop, R.id.add_sales_user, R.id.tv_select_user, R.id.commodity_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_sales_shop:
                //选择店铺
                requestNetShopList();
                break;
            case R.id.add_sales_user:
                //审核人
                requestNetEmployeeList();
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
                intent.setClass(AddSalesActivity.this, SelectSalesCommodityListActivity.class);
                bundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
            case R.id.add_sales_commit:
                //提交采购单
                showDialog.show();
                commitSales();
                break;
        }
    }

    /**
     * 提交采购单
     */
    private void commitSales() {
        salesCode = addSalesCode.getText().toString().trim();
        salesMerchantName = addSalesMerchants.getText().toString().trim();
        salesShopName = addSalesShop.getText().toString().trim();
        salesUserName = addSalesUser.getText().toString().trim();
        if (TextUtils.isEmpty(salesCode)) {
            showToast("进货单编号不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(salesMerchantName)) {
            showToast("进货商不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(salesShopName)) {
            showToast("店铺不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(salesUserName)) {
            showToast("审核人不能为空!");
            showDialog.dismiss();
            return;
        }
        SalesCommitBean salesCommitBean = new SalesCommitBean();
        salesCommitBean.setSalesCode(salesCode);
        salesCommitBean.setSalesMerchantId(salesMerchantId);
        salesCommitBean.setSalesMerchantName(salesMerchantName);
        salesCommitBean.setSalesShopId(salesShopId);
        salesCommitBean.setSalesShopName(salesShopName);
        salesCommitBean.setSalesUserId(salesUserId);
        salesCommitBean.setSalesUserName(salesUserName);
        if (mSelectCommodityMap.size() == 0) {
            showToast("选择的商品不能为空!");
            showDialog.dismiss();
            return;
        } else {
            Iterator<String> iterator = mSelectCommodityMap.keySet().iterator();
            List<SelectCommodityBean> mSelectCommodityList = new ArrayList<>();
            while (iterator.hasNext()) {
                String next = iterator.next();
                CommodityListBean.DataBean dataBean = mSelectCommodityMap.get(next);
                SelectCommodityBean selectCommodityBean = new SelectCommodityBean();
                selectCommodityBean.setProId(dataBean.getProId());
                selectCommodityBean.setProName(dataBean.getProName());
                selectCommodityBean.setmSelectNum(dataBean.getmSelectNum());
                selectCommodityBean.setmSelectMoney(dataBean.getmSelectMoney());
                selectCommodityBean.setProCode(dataBean.getProCode());
                selectCommodityBean.setMeas(dataBean.getMeas());
                selectCommodityBean.setMeaunit(dataBean.getMeaunit());
                if (dataBean.getmSelectMap() != null && dataBean.getmSelectMap().size() != 0) {
                    selectCommodityBean.setmSelectMap(dataBean.getmSelectMap());
                }
                mSelectCommodityList.add(selectCommodityBean);
            }
            salesCommitBean.setmSelectCommodityMap(mSelectCommodityList);
        }
        Gson gson = new Gson();
        String commitJson = gson.toJson(salesCommitBean);
        LogUtils.i("提交采购单===" + commitJson);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Map<String, CommodityListBean.DataBean> mgetSelectMap = (Map<String, CommodityListBean.DataBean>) extras.getSerializable(InvoicingConstants.select_Commiodty);
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
                        selectCommpdityExAdapter.notifyDataSetChanged();
                        setTotalNumMoney();
                    }
                }

                break;

            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    salesMerchantName = extras.getString(InvoicingConstants.Distributor_Name);
                    salesMerchantId = extras.getString(InvoicingConstants.Distributor_ID);
                    addSalesMerchants.setText(salesMerchantName + "");
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
                CommodityListBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
                mSelectCommodityList.remove(dataBean);
                mSelectCommodityMap.remove(dataBean.getProId() + "");
                setTotalNumMoney();
                selectCommpdityExAdapter.notifyDataSetChanged();
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
                CommodityListBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
                if (dataBean.getmSelectMap() != null) {
                    dataBean.getmSelectMap().remove(childPostion);
                    if (dataBean.getmSelectMap().size() == 0) {
                        mSelectCommodityList.remove(groupPostion);
                        mSelectCommodityMap.remove(dataBean.getProId() + "");
                    }
                } else {
                    mSelectCommodityList.remove(groupPostion);
                    mSelectCommodityMap.remove(dataBean.getProId() + "");
                }
                setNumMoney(dataBean);
                setTotalNumMoney();

                selectCommpdityExAdapter.notifyDataSetChanged();
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
        final CommodityListBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        if (dataBean != null) {
            SelectBatchBean batchBean = dataBean.getmSelectMap().get(childPostion);
            String enterprice = batchBean.getEnterprice();
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
                String enterprice = batchBean.getEnterprice();
                if (!TextUtils.isEmpty(price)) {
                    if (!price.equals(enterprice)) {
                        BatchPicListBean batchPicListBean = new BatchPicListBean();
                        batchPicListBean.setEnterprice(price);
                        batchPicListBean.setPbatchid(batchBean.getPbatchid());
                        Gson gson = new Gson();
                        String editJson = gson.toJson(batchPicListBean);
                        requestNeteditCommodity(editJson, commodityNum, price, batchBean, groupPostion, childPostion);
                    } else {
                        setBatchMsg(batchBean, price, commodityNum, groupPostion, childPostion);
                        selectCommpdityExAdapter.notifyDataSetChanged();
                    }

                } else {
                    showToast("请输入进货价!");
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
     * 查看公司员工
     */
    private void requestNetEmployeeList() {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employee_listfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils.post().tag(this)
                .addParams("page", "1")
                .addParams("cid", cid + "")
                .addParams("shopid", shopid)
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
                            EmployeeListBean employeeListBean = gson.fromJson(response, EmployeeListBean.class);
                            if (employeeListBean != null) {
                                List<EmployeeListBean.DataBean> dataList = employeeListBean.getData();
                                if (dataList != null) {
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddSalesActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(AddSalesActivity.this, false, customizeDialog);
                                    SelectEmployeeClick(customizeDialog, alertDialog, dataList);
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
        bean.setEnterprice(price);
        bean.setPbatchid(selctbatchBean.getPbatchid());
        bean.setNum(Integer.parseInt(commodityNum));
        CommodityListBean.DataBean dataBean = mSelectCommodityList.get(groupPostion);
        if (!commodityNum.equals("0")) {
            dataBean.getmSelectMap().set(childPostion, bean);
        } else {
            dataBean.getmSelectMap().remove(childPostion);
        }
        setNumMoney(dataBean);
        if (dataBean.getmSelectMap() != null) {
            if (dataBean.getmSelectMap().size() != 0) {
                mSelectCommodityMap.put(dataBean.getProId() + "", dataBean);
            } else {
                mSelectCommodityList.remove(groupPostion);
                mSelectCommodityMap.remove(dataBean.getProId() + "");
            }
        } else {
            mSelectCommodityList.remove(groupPostion);
            mSelectCommodityMap.remove(dataBean.getProId() + "");
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
            CommodityListBean.DataBean dataBean = mSelectCommodityMap.get(next);
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
    private void setNumMoney(CommodityListBean.DataBean Selectbean) {
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
     * 选择审核人
     *
     * @param customizeDialog
     * @param alertDialog
     */
    private void SelectEmployeeClick(View customizeDialog, final AlertDialog alertDialog, final List<EmployeeListBean.DataBean> datalist) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        tvselct.setText("选择审核人");
        SelectEmployeeAdapter selectShopList = new SelectEmployeeAdapter(AddSalesActivity.this, datalist, alertDialog);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                salesUserId = datalist.get(position).getUserid() + "";
                salesUserName = datalist.get(position).getUsername() + "";
                addSalesUser.setText(salesUserName + "");
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
        SelectSalesShopAdapter selectShopList = new SelectSalesShopAdapter(AddSalesActivity.this, shoplist, alertDialog);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                salesShopId = shoplist.get(position).getShopid() + "";
                salesShopName = shoplist.get(position).getShopname() + "";
                addSalesShop.setText(salesShopName + "");
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
