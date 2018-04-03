package com.mtecc.mmp.invoicing.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.adapter.MyPagerAdapter;
import com.mtecc.mmp.invoicing.base.bean.TabEntity;
import com.mtecc.mmp.invoicing.homeFragments.HomeFragment;
import com.mtecc.mmp.invoicing.homeFragments.ManangerFragment;
import com.mtecc.mmp.invoicing.homeFragments.MineFragment;
import com.mtecc.mmp.invoicing.homeFragments.StatisticsFragment;
import com.mtecc.mmp.invoicing.homeFragments.StockFragment;
import com.mtecc.mmp.invoicing.views.ViewPagerSlide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_vp)
    ViewPagerSlide mainVp;
    @BindView(R.id.main_bottom_layout)
    CommonTabLayout mainBottomLayout;
    //记录点击的返回的时间
    private long exitTime = 0;
    /*文本信息*/
    private String[] mTitles = {"首页", "管理", "库存", "统计", "我的"};
    /*未选择时的icon*/
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect, R.mipmap.tab_more_unselect};
    /*选择时的icon*/
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select, R.mipmap.tab_more_select};
    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    ArrayList<Fragment> mFragments = new ArrayList<>();

    /**
     * view点击事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        mainVp.setOffscreenPageLimit(4);

  /*添加数据集*/
        HomeFragment homeFragment = new HomeFragment();
        ManangerFragment manangerFragment = new ManangerFragment();
        StockFragment stockFragment = new StockFragment();
        StatisticsFragment statisticsFragment = new StatisticsFragment();
        MineFragment mineFragment = new MineFragment();
        for (int i = 0; i < mTitles.length; i++) {
            String mTitle = mTitles[i];
            if (mTitle.equals("首页")) {
                mFragments.add(homeFragment);
            }
            if (mTitle.equals("管理")) {
                mFragments.add(manangerFragment);
            }
            if (mTitle.equals("库存")) {
                mFragments.add(stockFragment);
            }
            if (mTitle.equals("统计")) {
                mFragments.add(statisticsFragment);
            }
            if (mTitle.equals("我的")) {
                mFragments.add(mineFragment);
            }
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), MainActivity.this, mFragments, mTitles);
        mainVp.setAdapter(myPagerAdapter);
        myPagerAdapter.notifyDataSetChanged();

        mainBottomLayout.setTabData(mTabEntities);
        mainBottomLayout.showDot(2);
        mainBottomLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainVp.setCurrentItem(position);
                if (position == 2) {
                    mainBottomLayout.hideMsg(2);
                } else if (position == 1) {
                    mainBottomLayout.hideMsg(1);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        //设置ViewPage是否可以滑动
        mainVp.setScanScroll(false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
