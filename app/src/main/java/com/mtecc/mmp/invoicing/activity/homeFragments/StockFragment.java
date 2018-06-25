package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.inStock.StockCommodityListActivity;
import com.mtecc.mmp.invoicing.activity.transfer.TransferListActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 库存
 */

public class StockFragment extends Fragment {

    Unbinder unbinder;
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
    @BindView(R.id.stock_rl_commodity)
    RelativeLayout stockRlCommodity;
    @BindView(R.id.stock_rl_supplier)
    RelativeLayout stockRlSupplier;
    @BindView(R.id.stock_rl_client)
    RelativeLayout stockRlClient;
    @BindView(R.id.stock_rl_depot)
    RelativeLayout stockRlDepot;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_stock_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是库存的oncreate");
        tvTitle.setText("库存");


        return inflate;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            LogUtils.i(isVisibleToUser + "====刷新页面");
        } else {
            LogUtils.i(isVisibleToUser + "====不显示页面");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.stock_rl_commodity, R.id.stock_rl_supplier, R.id.stock_rl_client, R.id.stock_rl_depot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stock_rl_commodity:
                //库存预警
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(getContext(), StockCommodityListActivity.class);
                bundle.putString(InvoicingConstants.STOCK_TYPE, InvoicingConstants.STOCK_WORING);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.stock_rl_supplier:
                Intent intentSee = new Intent();
                Bundle bundleSee = new Bundle();
                intentSee.setClass(getContext(), StockCommodityListActivity.class);
                bundleSee.putString(InvoicingConstants.STOCK_TYPE, InvoicingConstants.STOCK_SEE);
                intentSee.putExtras(bundleSee);
                startActivity(intentSee);
                //库存查看
                break;
            case R.id.stock_rl_client:
                //商品出库
                Intent intentOutBound = new Intent();
                Bundle bundleOutBound = new Bundle();
                intentOutBound.setClass(getContext(), TransferListActivity.class);
                bundleOutBound.putString(InvoicingConstants.STOCK_TYPE, InvoicingConstants.STOCK_OutBound);
                intentOutBound.putExtras(bundleOutBound);
                startActivity(intentOutBound);
                break;
            case R.id.stock_rl_depot:
                //调拨管理
                Intent transferInter = new Intent();
                Bundle bundleTransfer = new Bundle();
                transferInter.setClass(getContext(), TransferListActivity.class);
                bundleTransfer.putString(InvoicingConstants.STOCK_TYPE, InvoicingConstants.STOCK_Transfer);
                transferInter.putExtras(bundleTransfer);
                startActivity(transferInter);
                break;
        }
    }
}
