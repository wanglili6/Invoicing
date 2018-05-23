package com.mtecc.mmp.invoicing.activity.sales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mtecc.mmp.invoicing.R;

import com.mtecc.mmp.invoicing.activity.sales.fragment.SalesIncomeFragment;
import com.mtecc.mmp.invoicing.activity.sales.fragment.SalesOutFragment;
import com.mtecc.mmp.invoicing.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购列表
 */
public class SalesListActivity extends BaseActivity {

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
    @BindView(R.id.income_segment_tab_layout)
    SegmentTabLayout incomeSegmentTabLayout;
    @BindView(R.id.income_switch_over_vp)
    ViewPager incomeSwitchOverVp;
    private String[] mTitles = {"销货历史", "退货历史"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void widgetClick(View v) {

    }


    public ViewPager getIncomeSwitchOverVp() {
        return incomeSwitchOverVp;
    }

    public void setIncomeSwitchOverVp(ViewPager incomeSwitchOverVp) {
        this.incomeSwitchOverVp = incomeSwitchOverVp;
    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        tvTitle.setText("销售历史");
        SalesIncomeFragment incomeFragment = new SalesIncomeFragment();
        mFragments.add(incomeFragment);
        SalesOutFragment expendFragment = new SalesOutFragment();
        mFragments.add(expendFragment);
        setIncomeSwitchOverVp(incomeSwitchOverVp);
        setImgSelect(imgSelect);
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_purchase_list;
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
        incomeSwitchOverVp.setCurrentItem(0);
    }

    @OnClick({R.id.rl_back, R.id.img_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.img_select:
                int currentItem = incomeSwitchOverVp.getCurrentItem();
                if (currentItem == 0) {
                    Log.i(TAG, "onClick: " + "计划内");
                    Intent intent = new Intent(this, AddSalesActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "income");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (currentItem == 1) {
                    Intent intent = new Intent(this, AddSalesActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "out");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;

        }
    }

    public void setImgSelect(ImageButton imgSelect) {
        this.imgSelect = imgSelect;
    }

    public ImageButton getImgSelect() {
        return imgSelect;
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
