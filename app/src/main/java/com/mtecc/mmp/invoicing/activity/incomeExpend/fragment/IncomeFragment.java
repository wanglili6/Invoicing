package com.mtecc.mmp.invoicing.activity.incomeExpend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtecc.mmp.invoicing.R;

/**
 * Created by wll on 2018/4/16.
 * 销售收入
 */

public class IncomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.income_fragment, container, false);
        return view;
    }
}
