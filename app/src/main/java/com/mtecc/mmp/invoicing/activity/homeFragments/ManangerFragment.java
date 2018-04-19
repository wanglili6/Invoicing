package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.CommodityListActivity;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeSeeActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopListActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopManagementActivity;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_mananger_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是管理的oncreate");
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
                Intent intent = new Intent();
                intent.setClass(getContext(), CommodityListActivity.class);
                startActivity(intent);
                break;
            case R.id.manage_rl_supplier:
                break;
            case R.id.manage_rl_client:
                break;
            case R.id.manage_rl_depot:
                break;
            case R.id.manage_rl_income:
                break;
            case R.id.manage_rl_expend:
                break;
        }
    }

}