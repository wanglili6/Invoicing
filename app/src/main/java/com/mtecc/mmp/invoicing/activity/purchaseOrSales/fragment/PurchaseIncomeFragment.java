package com.mtecc.mmp.invoicing.activity.purchaseOrSales.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.SeeOrdersActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.PurchaseSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by wll on 2018/4/16.
 * 采购历史
 */

public class PurchaseIncomeFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.purchase_list_recycler_view)
    ListView purchaseListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.iv_bar_search)
    RelativeLayout ivBarSearch;
    @BindView(R.id.et_serch)
    EditText etSerch;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.rl_serch_bar)
    LinearLayout rlSerchBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ll_seach)
    LinearLayout llSeach;
    @BindView(R.id.img_commodity_list_saoma)
    ImageView imgCommodityListSaoma;
    private PurchaseSwitchListAdapter adapter;
    List<PrichaseIncomeBean.DataBean> mList = new ArrayList<>();
    int pagenum = 1;
    String hdid = "";
    String shopname = "";
    String shopStatus = "";
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private String shopid = "";
    private String SHOP_ID = "";
    private boolean isPause = false;
    private String type;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.purchase_out_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        awayKetBordUtils = new AwayKetBordUtils(getActivity(), getActivity().getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(getActivity(), InvoicingConstants.QY_ID, 0);
        SHOP_ID = PreferencesUtils.getString(getActivity(), InvoicingConstants.SHOP_ID, "");
        requestNetEnterGoodsList(pagenum + "", "", cid + "", SHOP_ID);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mList.clear();
                pagenum = 1;
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID);
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pagenum++;
                requestNetEnterGoodsList(pagenum + "", cid + "", "", SHOP_ID);

            }
        });


        purchaseListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrichaseIncomeBean.DataBean dataBean = mList.get(position);
                Intent expendintent = new Intent();
                Bundle expendbundle = new Bundle();
                if (type.equals(InvoicingConstants.PURCHASE)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                } else if (type.equals(InvoicingConstants.SALES)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                }
                expendbundle.putSerializable(InvoicingConstants.hdid, (Serializable) dataBean);
                expendintent.setClass(getContext(), SeeOrdersActivity.class);
                expendintent.putExtras(expendbundle);
                startActivity(expendintent);
            }
        });

        adapter = new PurchaseSwitchListAdapter(getContext(), mList, "0", type);
        purchaseListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            LogUtils.i(isVisibleToUser + "====刷新页面");

            if (smartRefreshLayout != null) {
                smartRefreshLayout.autoRefresh();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 订单列表
     *
     * @param page
     * @param hdid   订单编号
     * @param cid
     * @param shopid
     */
    private void requestNetEnterGoodsList(String page, String hdid, String cid, String shopid) {
        if (type.equals(InvoicingConstants.PURCHASE)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.listfortable_URL;
        } else if (type.equals(InvoicingConstants.SALES)) {
            url = InvoicingConstants.BASE_URL + InvoicingConstants.outlistfortable_URL;
        }
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("hdid", hdid)//订单编号
                .addParams("cid", cid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        try {
                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
                            Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        try {
                            LogUtils.d("返回值信息PurchaseListActivity" + response.toString());
                            Gson gson = new Gson();
                            PrichaseIncomeBean prichaseIncomeBean = gson.fromJson(response, PrichaseIncomeBean.class);
                            if (prichaseIncomeBean != null) {
                                List<PrichaseIncomeBean.DataBean> dataList = prichaseIncomeBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            Toast.makeText(getActivity(), "没有更多数据!", Toast.LENGTH_SHORT).show();
                                        }
                                        adapter.notifyDataSetChanged();
                                        llList.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            llList.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            Toast.makeText(getActivity(), "暂无数据!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    llList.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    Toast.makeText(getActivity(), "暂无数据!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                            Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_bar_search:
                //点击搜索图标
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                awayKetBordUtils.showKeyboard(etSerch);
                break;
            case R.id.tv_search:
                //点击搜索
                awayKetBordUtils.putAwayKetBord();
                mList.clear();
                hdid = etSerch.getText().toString().trim();
                pagenum = 1;
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_serch:
                //点击叉号
                awayKetBordUtils.putAwayKetBord();
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;

        }
    }


}
