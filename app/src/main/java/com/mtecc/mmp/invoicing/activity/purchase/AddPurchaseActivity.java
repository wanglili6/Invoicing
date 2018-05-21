package com.mtecc.mmp.invoicing.activity.purchase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.CommodityListActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.views.NoScrollListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购订单
 */
public class AddPurchaseActivity extends BaseActivity {


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
    @BindView(R.id.add_purchase_user)
    EditText addPurchaseUser;
    @BindView(R.id.add_batch_list_view)
    NoScrollListView addBatchListView;
    @BindView(R.id.add_purchase_commit)
    TextView addPurchaseCommit;

    String purchaseCode = "";//订单号
    String purchaseShopId = "";//店铺id
    String purchaseUserId = "";//审核人
    String purchaseMerchantId = "";//供货商
    private int cid;
    private String user_id;
    private Map<String, CommodityListBean.DataBean> mSelectCommodityMap = new HashMap<>();
    private List<CommodityListBean.DataBean> mSelectCommodityList = new ArrayList<>();

    @Override
    public void widgetClick(View v) {


    }


    @Override
    public void initParms(Bundle parms) {
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        String type = parms.getString("type");
        long timeMillis = System.currentTimeMillis();
        if (type.equals("income")) {
            tvTitle.setText("采购订单");
            addPurchaseCode.setText("CGJH" + cid + timeMillis);

        } else {
            tvTitle.setText("退货订单");
            addPurchaseCode.setText("CGTH" + cid + timeMillis);
        }
        //时间戳


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


    @OnClick({R.id.rl_back, R.id.add_purchase_shop, R.id.add_purchase_user, R.id.tv_select_user, R.id.commodity_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_purchase_shop:
                //选择店铺
                break;
            case R.id.add_purchase_user:
                //审核人
                break;
            case R.id.tv_select_user:
                //供货商
                break;
            case R.id.commodity_add_iteam:
                //选择商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(AddPurchaseActivity.this, SelectCommodityListActivity.class);
                bundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
        }
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
                        Map<String, CommodityListBean.DataBean> mSelectMap = new HashMap<>();
                        Iterator<String> iterator = mgetSelectMap.keySet().iterator();
                        mSelectCommodityList.clear();
                        while (iterator.hasNext()) {
                            String next = iterator.next();
                            String selctnum = mgetSelectMap.get(next).getmSelectNum() + "";
                            String selctMoney = mgetSelectMap.get(next).getmSelectMoney() + "";
                            if (!TextUtils.isEmpty(selctnum) && !selctnum.equals("0")) {
                                mSelectCommodityList.add(mgetSelectMap.get(next));
                            }
                        }

                    }
                }

                break;
        }
    }
}
