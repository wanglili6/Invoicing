package com.mtecc.mmp.invoicing.activity.homeFragments;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 统计
 */

public class StatisticsFragment extends Fragment {
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
    @BindView(R.id.statistics_rl_commodity)
    RelativeLayout statisticsRlCommodity;
    @BindView(R.id.statistics_rl_supplier)
    RelativeLayout statisticsRlSupplier;
    @BindView(R.id.statistics_rl_client)
    RelativeLayout statisticsRlClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_statistics_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是统计的oncreate");
        tvTitle.setText("统计");


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


    @OnClick({R.id.statistics_rl_commodity, R.id.statistics_rl_supplier, R.id.statistics_rl_client})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.statistics_rl_commodity:
                break;
            case R.id.statistics_rl_supplier:
                break;
            case R.id.statistics_rl_client:
                break;
        }
    }
}
