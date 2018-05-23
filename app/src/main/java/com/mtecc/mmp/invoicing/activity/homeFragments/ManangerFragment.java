package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.CheckListActivity;
import com.mtecc.mmp.invoicing.activity.comodity.CommodityListActivity;
import com.mtecc.mmp.invoicing.activity.distributor.DistributorLIstActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 管理
 */

public class ManangerFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.manage_rl_commodity)
    RelativeLayout manageRlCommodity;
    @BindView(R.id.manage_rl_supplier)
    RelativeLayout manageRlSupplier;
    @BindView(R.id.manage_rl_client)
    RelativeLayout manageRlClient;
    @BindView(R.id.manage_rl_depot)
    RelativeLayout manageRlDepot;
    @BindView(R.id.manage_rl_income)
    RelativeLayout manageRlIncome;
    @BindView(R.id.manage_rl_expend)
    RelativeLayout manageRlExpend;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_mananger_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是管理的oncreate");
        tvTitle.setText("管理");
        initData();
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

    /**
     * 初始化数据
     */
    private void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.manage_rl_commodity, R.id.manage_rl_supplier, R.id.manage_rl_client, R.id.manage_rl_depot, R.id.manage_rl_income, R.id.manage_rl_expend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manage_rl_commodity:
                //商品管理
                Intent intent = new Intent();
                intent.setClass(getContext(), CommodityListActivity.class);
                startActivity(intent);
                break;
            case R.id.manage_rl_supplier:
                //进货商
                Intent supplierintent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Merchants_TYPE);
                bundle.putString(InvoicingConstants.Merchants_TYPE, "list");
                supplierintent.setClass(getContext(), DistributorLIstActivity.class);
                supplierintent.putExtras(bundle);
                startActivity(supplierintent);
                break;
            case R.id.manage_rl_client:
                //客户管理
                Intent clientintent = new Intent();
                Bundle clienbundle = new Bundle();
                clienbundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                clienbundle.putString(InvoicingConstants.Distributor_TYPE, "list");
                clientintent.setClass(getContext(), DistributorLIstActivity.class);
                clientintent.putExtras(clienbundle);
                startActivity(clientintent);
                break;
            case R.id.manage_rl_depot:
                break;
            case R.id.manage_rl_income:
                //采购审核
                Intent incomeintent = new Intent();
                Bundle incomebundle = new Bundle();
                incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                incomeintent.setClass(getContext(), CheckListActivity.class);
                incomeintent.putExtras(incomebundle);
                startActivity(incomeintent);
                break;
            case R.id.manage_rl_expend:
                //销售审核
                Intent expendintent = new Intent();
                Bundle expendbundle = new Bundle();
                expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                expendintent.setClass(getContext(), CheckListActivity.class);
                expendintent.putExtras(expendbundle);
                startActivity(expendintent);
                break;
        }
    }

}
