package com.mtecc.mmp.invoicing.activity.incomeExpend.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wll on 2018/4/16.
 */

public class SwitchoverAdapter extends FragmentPagerAdapter {
    FragmentManager fm;
    String[] mTitles;
    ArrayList<Fragment> mFragments;

    public SwitchoverAdapter(FragmentManager fm, String[] mTitles, ArrayList<Fragment> mFragments) {
        super(fm);
        this.fm = fm;
        this.mTitles = mTitles;
        this.mFragments = mFragments;
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
