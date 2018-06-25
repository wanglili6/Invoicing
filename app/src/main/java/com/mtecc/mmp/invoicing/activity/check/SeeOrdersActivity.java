package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyachi.stepview.VerticalStepView;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SeePurchaserBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.views.NoScrollExpandaleListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 查看订单详情
 */
public class SeeOrdersActivity extends BaseActivity {

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
    @BindView(R.id.orders_see_code)
    TextView ordersSeeCode;
    @BindView(R.id.tv_see_purchases_sales)
    TextView tvSeePurchasesSales;
    @BindView(R.id.orders_see_purchases_sales)
    TextView ordersSeePurchasesSales;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.orders_see_shop)
    TextView ordersSeeShop;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.orders_see_user)
    TextView ordersSeeUser;
    @BindView(R.id.ll_slect_user)
    LinearLayout llSlectUser;
    @BindView(R.id.orders_see_income)
    TextView ordersSeeIncome;
    @BindView(R.id.ll_out_shop)
    LinearLayout llOutShop;
    @BindView(R.id.orders_see_out_shop)
    TextView ordersSeeOutShop;
    @BindView(R.id.ll_imcome_shop)
    LinearLayout llImcomeShop;
    @BindView(R.id.time_line_list)
    VerticalStepView timeLineList;
    @BindView(R.id.ll_audit)
    LinearLayout llAudit;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    private Gson gson;
    private SeeCommpdityExAdapter seeCommpdityExAdapter;
    private String checkType;
    private String hdid = "";
    private String shop_id;
    private String shtype;
    private PrichaseIncomeBean.DataBean dataBean;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看详情");
        parms = getIntent().getExtras();
        checkType = parms.getString(InvoicingConstants.check_type);
        dataBean = (PrichaseIncomeBean.DataBean) parms.getSerializable(InvoicingConstants.hdid);
        boolean isUserXSAudit = PreferencesUtils.getBoolean(SeeOrdersActivity.this, InvoicingConstants.isUseXSAuidt);
        boolean isUseCGAuidt = PreferencesUtils.getBoolean(SeeOrdersActivity.this, InvoicingConstants.isUseCGAuidt);

        if (checkType.equals(InvoicingConstants.check_purchases)) {
            tvSeeCode.setText("进货单编号");
            tvSeePurchasesSales.setText("进货商");
            shtype = InvoicingConstants.SHOP_Shehe;
            if (isUseCGAuidt) {
                llAudit.setVisibility(View.VISIBLE);
            } else {
                llAudit.setVisibility(View.GONE);
            }
        } else if (checkType.equals(InvoicingConstants.check_purchases_out)) {
            tvSeeCode.setText("采购退货单编号");
            tvSeePurchasesSales.setText("进货商");
        } else if (checkType.equals(InvoicingConstants.check_sales)) {
            tvSeeCode.setText("销售单编号");
            tvSeePurchasesSales.setText("分销商");
            shtype = InvoicingConstants.SEAL_Shehe;
            if (isUserXSAudit) {
                llAudit.setVisibility(View.VISIBLE);
            } else {
                llAudit.setVisibility(View.GONE);
            }
        } else if (checkType.equals(InvoicingConstants.STOCK_OutBound)) {
            tvSeeCode.setText("出库单编号");
            llImcomeShop.setVisibility(View.GONE);
            llOutShop.setVisibility(View.VISIBLE);
            llShop.setVisibility(View.GONE);
            llUser.setVisibility(View.GONE);
            llSlectUser.setVisibility(View.GONE);
        } else if (checkType.equals(InvoicingConstants.STOCK_Transfer)) {
            tvSeeCode.setText("调拨单编号");
            llImcomeShop.setVisibility(View.VISIBLE);
            llOutShop.setVisibility(View.VISIBLE);
            llShop.setVisibility(View.GONE);
            llUser.setVisibility(View.GONE);
            llSlectUser.setVisibility(View.GONE);
        }
        shop_id = PreferencesUtils.getString(SeeOrdersActivity.this, InvoicingConstants.SHOP_ID, "");
        if (dataBean != null) {
            ordersSeeCode.setText(dataBean.getHdid());
            ordersSeePurchasesSales.setText(dataBean.getMername());
            ordersSeeShop.setText(dataBean.getShopname());
            ordersSeeUser.setText(dataBean.getHdtitle());
//            ordersSeeIncome.setText(dataBean.getPurchaseUserName());
//            ordersSeeOutShop.setText(dataBean.getPurchaseUserName());
            hdid = dataBean.getHdid();
            requestNetDetails(hdid, shop_id, shtype);
        }


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_see_orders;
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

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 设置合计总数
     */
    private void setTotalNumMoney(List<SeePurchaserBean.DataBean> mSelectCommodityMa) {
        int totalNum = 0;
        double totalmoney = 0.0;
        int size = mSelectCommodityMa.size();
        for (int i = 0; i < size; i++) {
            SeePurchaserBean.DataBean dataBean = mSelectCommodityMa.get(i);
            int dataBeansize = dataBean.getPclist().size();
            for (int j = 0; j < dataBeansize; j++) {
                SeePurchaserBean.DataBean.PclistBean pclistBean = dataBean.getPclist().get(j);
                String nums = pclistBean.getEnterqty();
                if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                    totalNum = totalNum + Integer.parseInt(nums);
                    String enterprice = pclistBean.getEnterprice() + "";
                    if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                        //当个批次的价格
                        double entermoney = Double.parseDouble(enterprice);
                        //批次的数量
                        BigDecimal numDecimal = new BigDecimal(nums);
                        BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                        BigDecimal multiply = numDecimal.multiply(enterBigDecimal);
                        BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                        BigDecimal add = totalmoneyBigDecimal.add(multiply);
                        totalmoney = add.doubleValue();
                    } else {
                        totalmoney = totalmoney + Integer.parseInt(nums) * 0.0;
                    }
                }
            }

        }
        tvBatchNum.setText(totalNum + "");
        tvTotalMoney.setText(totalmoney + "");

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
                            Toast.makeText(SeeOrdersActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                List<SeePurchaserBean.AuditBean> audit = seePurchaserBean.getAudit();

                                if (audit.size() != 0) {
                                    List<String> list0 = new ArrayList<>();
                                    SeePurchaserBean.AuditBean bean = audit.get(audit.size() - 1);
                                    if (bean.getAuditresult().equals("0")) {
                                        list0.add("审核未通过");
                                    } else if (bean.getAuditresult().equals("1")) {
                                        list0.add("审核通过");
                                    } else {
                                        list0.add("审核中...");
                                    }
                                    int size = audit.size();
                                    for (int i = 0; i < size; i++) {
                                        SeePurchaserBean.AuditBean auditBean = audit.get(i);
                                        String auditresult = auditBean.getAuditresult();
                                        if (auditresult.equals("0")) {
                                            auditresult = "未通过";
                                        } else if (auditresult.equals("1")) {
                                            auditresult = "通过";

                                        }
                                        list0.add(auditBean.getTimeStr() + " , 审核人 : " +
                                                auditBean.getAuditman().getUsername()
                                                + " , 审核结果 : " + auditresult
                                                + " , 审核意见 :" + auditBean.getAuditmess());
                                    }

                                    setTimeLine(list0);
                                } else {
                                    llAudit.setVisibility(View.GONE);
                                }

                                List<SeePurchaserBean.DataBean> dataBeanList = seePurchaserBean.getData();
                                if (dataBeanList != null) {
                                    seeCommpdityExAdapter = new SeeCommpdityExAdapter(SeeOrdersActivity.this, dataBeanList, checkType);
                                    addBatchListView.setAdapter(seeCommpdityExAdapter);
                                    seeCommpdityExAdapter.notifyDataSetChanged();
                                    setTotalNumMoney(dataBeanList);
                                }
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                            Toast.makeText(SeeOrdersActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setTimeLine(List<String> list0) {
        timeLineList.setStepsViewIndicatorComplectingPosition(0)//设置完成的步数
                .reverseDraw(true)//default is true
                .setStepViewTexts(list0)//总步骤
                .setLinePaddingProportion(1.5f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(SeeOrdersActivity.this, R.color.text_black_color))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(SeeOrdersActivity.this, R.color.line_gray_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(SeeOrdersActivity.this, R.color.text_black_color))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(SeeOrdersActivity.this, R.color.text_gray_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(SeeOrdersActivity.this, R.mipmap.gray_dian))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(SeeOrdersActivity.this, R.mipmap.gray_dian))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(SeeOrdersActivity.this, R.mipmap.red_dian));//设置StepsViewIndicator AttentionIcon
    }

    /**
     * 设置timeline的样式
     *
     * @param strings
     * @param list0
     */
    private void setTimeLine(String[] strings, String[] list0) {

    }

}
