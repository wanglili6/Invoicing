package com.mtecc.mmp.invoicing.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 首页
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.btn_ceshi)
    Button btnCeshi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_home_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是首页的oncreate");
        initData();

        return inflate;
    }


    /**
     * 初始化数据
     */
    private void initData() {
        btnCeshi.setText("首页");
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
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;

        }
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
}
