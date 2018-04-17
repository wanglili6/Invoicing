package com.mtecc.mmp.invoicing.activity.incomeExpend;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.incomeExpend.adapter.SwitchoverAdapter;
import com.mtecc.mmp.invoicing.activity.incomeExpend.fragment.ExpendFragment;
import com.mtecc.mmp.invoicing.activity.incomeExpend.fragment.IncomeFragment;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.bean.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InComeExpendActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.home_tv_income)
    TextView homeTvIncome;
    @BindView(R.id.home_tv_expend)
    TextView homeTvExpend;
    @BindView(R.id.home_tv_surplus)
    TextView homeTvSurplus;
    @BindView(R.id.income_segment_tab_layout)
    SegmentTabLayout incomeSegmentTabLayout;
    @BindView(R.id.income_switch_over_vp)
    ViewPager incomeSwitchOverVp;
    private String[] mTitles = {"销售收入", "采购支出"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("收支明细");
        IncomeFragment incomeFragment = new IncomeFragment();
        mFragments.add(incomeFragment);
        ExpendFragment expendFragment = new ExpendFragment();
        mFragments.add(expendFragment);

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_in_come_expend;
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
        switchover();
    }

    /**
     * 切换viewpage
     */
    private void switchover() {

        incomeSwitchOverVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        incomeSegmentTabLayout.setTabData(mTitles);
        incomeSegmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                incomeSwitchOverVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        incomeSwitchOverVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                incomeSegmentTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        incomeSwitchOverVp.setCurrentItem(1);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
