package com.mtecc.mmp.invoicing.activity.incomeExpend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.incomeExpend.adapter.ExpendListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wll on 2018/4/16.
 * 采购支出
 */

public class ExpendFragment extends Fragment {


    @BindView(R.id.expend_list_view)
    RecyclerView expendListView;
    @BindView(R.id.income_smart_refesh_layout)
    SmartRefreshLayout incomeSmartRefeshLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.expend_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        ArrayList<String> nameList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            nameList.add("分销商" + i);
        }
        expendListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ExpendListAdapter adapter = new ExpendListAdapter(getContext(), nameList);
        expendListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        refeshLoadMore();

    }

    /**
     * 下拉刷新,加载更多
     */
    private void refeshLoadMore() {
        incomeSmartRefeshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);

            }
        });

        incomeSmartRefeshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            LogUtils.i(isVisibleToUser + "====刷新页面");
            if (incomeSmartRefeshLayout != null) {
                incomeSmartRefeshLayout.autoRefresh();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
