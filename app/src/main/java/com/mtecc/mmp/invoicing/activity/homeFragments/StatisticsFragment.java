package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    @BindView(R.id.btn_ceshi)
    Button btnCeshi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_statistics_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是统计的oncreate");
        initData();

        return inflate;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            LogUtils.i(isVisibleToUser+"====刷新页面");
        }else {
            LogUtils.i(isVisibleToUser+"====不显示页面");
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        btnCeshi.setText("统计");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_ceshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ceshi:
               LogUtils.d("测试:--","这是统计");
                break;

        }
    }
}
