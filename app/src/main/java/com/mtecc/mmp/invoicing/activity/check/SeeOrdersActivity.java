package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.bean.SeeOrdersBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.views.NoScrollExpandaleListView;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.orders_see_shop)
    TextView ordersSeeShop;
    @BindView(R.id.orders_see_user)
    TextView ordersSeeUser;
    @BindView(R.id.add_batch_list_view)
    NoScrollExpandaleListView addBatchListView;
    @BindView(R.id.tv_batch_num)
    TextView tvBatchNum;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;

    private Gson gson;
    private SeeCommpdityExAdapter seeCommpdityExAdapter;
    private String checkType;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看详情");
        parms = getIntent().getExtras();
        checkType = parms.getString(InvoicingConstants.check_type);
        if (checkType.equals(InvoicingConstants.check_purchases)) {
            tvSeeCode.setText("进货单编号");
            tvSeePurchasesSales.setText("进货商");
        } else if (checkType.equals(InvoicingConstants.check_purchases_out)) {
            tvSeeCode.setText("采购退货单编号");
            tvSeePurchasesSales.setText("进货商");
        } else if (checkType.equals(InvoicingConstants.check_sales)) {
            tvSeeCode.setText("销售单编号");
            tvSeePurchasesSales.setText("分销商");
        } else if (checkType.equals(InvoicingConstants.check_sales_out)) {
            tvSeeCode.setText("销售退货单编号");
            tvSeePurchasesSales.setText("分销商");
        }
        String json = "{\"mSelectCommodityMap\":[{\"mSelectMap\":[{\"batchnum\":\"12312\",\"enterprice\":\"12\",\"num\":1,\"pbatchid\":6}],\"mSelectMoney\":12.0,\"mSelectNum\":1,\"proId\":9,\"proName\":\"白砂糖\"},{\"mSelectMap\":[{\"batchnum\":\"36\",\"enterprice\":\"25\",\"num\":1,\"pbatchid\":11},{\"batchnum\":\"3\",\"enterprice\":\"9\",\"num\":1,\"pbatchid\":20},{\"batchnum\":\"777uuu\",\"enterprice\":\"1\",\"num\":6,\"pbatchid\":23},{\"batchnum\":\"36\",\"enterprice\":\"5\",\"num\":1,\"pbatchid\":12}],\"mSelectMoney\":45.0,\"mSelectNum\":9,\"proId\":14,\"proName\":\"牛奶\"},{\"mSelectMap\":[{\"batchnum\":\"\",\"enterprice\":\"5.89\",\"num\":1,\"pbatchid\":25},{\"batchnum\":\"111\",\"enterprice\":\"122\",\"num\":1,\"pbatchid\":28}],\"mSelectMoney\":127.89,\"mSelectNum\":2,\"proId\":15,\"proName\":\"可乐\"},{\"mSelectMap\":[{\"batchnum\":\"2569bb\",\"enterprice\":\"8.9\",\"num\":4,\"pbatchid\":32}],\"mSelectMoney\":35.6,\"mSelectNum\":4,\"proId\":16,\"proName\":\"豆角\"},{\"mSelectMap\":[{\"batchnum\":\"696987412\",\"enterprice\":\"5\",\"num\":3,\"pbatchid\":21}],\"mSelectMoney\":15.0,\"mSelectNum\":3,\"proId\":12,\"proName\":\"瓜子\"}],\"purchaseCode\":\"CGJH21527062433118\",\"purchaseMerchantId\":\"1\",\"purchaseMerchantName\":\"中国蒙牛有限公司\",\"purchaseShopId\":\"2\",\"purchaseShopName\":\"我家店铺二\",\"purchaseUserId\":\"56\",\"purchaseUserName\":\"王丽丽\"}";
        gson = new Gson();
        SeeOrdersBean seeOrdersBean = gson.fromJson(json, SeeOrdersBean.class);
        if (seeOrdersBean != null) {

            ordersSeeCode.setText(seeOrdersBean.getPurchaseCode());
            ordersSeePurchasesSales.setText(seeOrdersBean.getPurchaseMerchantName());
            ordersSeeShop.setText(seeOrdersBean.getPurchaseShopName());
            ordersSeeUser.setText(seeOrdersBean.getPurchaseUserName());
            List<SeeOrdersBean.MSelectCommodityMapBean> mSelectCommodityMap =
                    seeOrdersBean.getMSelectCommodityMap();
            if (mSelectCommodityMap != null) {
                seeCommpdityExAdapter = new SeeCommpdityExAdapter(this, mSelectCommodityMap);
                addBatchListView.setAdapter(seeCommpdityExAdapter);
                seeCommpdityExAdapter.notifyDataSetChanged();
                setTotalNumMoney(mSelectCommodityMap);
            }
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
    private void setTotalNumMoney(List<SeeOrdersBean.MSelectCommodityMapBean> mSelectCommodityMa) {
        int totalNum = 0;
        double totalmoney = 0.0;
        int size = mSelectCommodityMa.size();
        for (int i = 0; i < size; i++) {
            SeeOrdersBean.MSelectCommodityMapBean dataBean = mSelectCommodityMa.get(i);
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
}
