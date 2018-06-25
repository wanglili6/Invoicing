package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.SeeOrdersActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.PurchaseSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * 采购列表
 */
public class PurchaseOrderListActivity extends BaseActivity {
    @BindView(R.id.purchase_list_recycler_view)
    SwipeMenuListView purchaseListRecyclerView;
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
    private PurchaseSwitchListAdapter adapter;
    List<PrichaseIncomeBean.DataBean> mList = new ArrayList<>();
    int pagenum = 1;
    String hdid = "";
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private String SHOP_ID = "";
    private String type = "";
    private String pstype = "";
    private String url = "";
    private boolean isPause;
    private String merid = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        type = parms.getString(InvoicingConstants.TYPE);
        pstype = parms.getString(InvoicingConstants.STOCK_TYPE);
        SHOP_ID = PreferencesUtils.getString(PurchaseOrderListActivity.this, InvoicingConstants.SHOP_ID, "");
        ivBack.setVisibility(View.VISIBLE);
        if (pstype.equals(InvoicingConstants.NoADDRETURN)) {
            imgSelect.setVisibility(View.VISIBLE);
            imgSelect.setBackgroundResource(R.mipmap.add_select);
        } else {
            imgSelect.setVisibility(View.GONE);
            merid = parms.getString(InvoicingConstants.Merchants_ID);
            SHOP_ID = parms.getString(InvoicingConstants.SHOP_ID);
     
        }

        if (type.equals(InvoicingConstants.PURCHASE)) {
            tvTitle.setText("采购记录");
        } else if (type.equals(InvoicingConstants.SALES)) {
            tvTitle.setText("销售记录");
        }
        awayKetBordUtils = new AwayKetBordUtils(PurchaseOrderListActivity.this, getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(PurchaseOrderListActivity.this, InvoicingConstants.QY_ID, 0);

        requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID);
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.purchase_out_fragment;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        if (!pstype.equals(InvoicingConstants.ADDRETURN)) {
            SwipeMenuCreator creator = new SwipeMenuCreator() {
                @Override
                public void create(SwipeMenu menu) {
                    switch (menu.getViewType()) {
                        case 1:
                            SwipeMenuItem deleteItem = new SwipeMenuItem(
                                    getApplicationContext());
                            // 创建“退货”项
                            deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                            deleteItem.setWidth(dp2px(100));
                            deleteItem.setTitle("退货");
                            deleteItem.setTitleSize(18);
                            deleteItem.setTitleColor(Color.WHITE);
                            // 将创建的菜单项添加进菜单中
                            menu.addMenuItem(deleteItem);
                            break;
                        case 2:
                            SwipeMenuItem item = new SwipeMenuItem(
                                    getApplicationContext());
                            item.setWidth(dp2px(0));
                            // 将创建的菜单项添加进菜单中
                            menu.addMenuItem(item);
                            break;
                        case 3:
                            // 创建“退货”项
                            SwipeMenuItem editItem = new SwipeMenuItem(
                                    getApplicationContext());
                            editItem.setBackground(new ColorDrawable(Color.parseColor("#0099FF")));
                            editItem.setWidth(dp2px(100));
                            editItem.setTitle("编辑");
                            editItem.setTitleSize(18);
                            editItem.setTitleColor(Color.WHITE);
                            // 将创建的菜单项添加进菜单中
                            menu.addMenuItem(editItem);
                            break;
                    }


                }
            };
            purchaseListRecyclerView.setMenuCreator(creator);
            purchaseListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        }

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
                requestNetEnterGoodsList(pagenum + "", hdid, cid + "", SHOP_ID);
                adapter.notifyDataSetChanged();
            }
        });
        purchaseListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrichaseIncomeBean.DataBean dataBean = mList.get(position);
                Intent expendintent = new Intent();
                Bundle expendbundle = new Bundle();
                expendbundle.putSerializable(InvoicingConstants.hdid, (Serializable) dataBean);
                if (pstype.equals(InvoicingConstants.ADDRETURN)) {
                    //销售退货
                    expendintent.setClass(PurchaseOrderListActivity.this, AddReturnsActivity.class);
                    expendintent.putExtras(expendbundle);
                    setResult(4, expendintent);
                    finish();
                } else {
                    //查看
                    if (type.equals(InvoicingConstants.PURCHASE)) {
                        expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                    } else if (type.equals(InvoicingConstants.SALES)) {
                        expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                    }
                    expendintent.setClass(PurchaseOrderListActivity.this, SeeOrdersActivity.class);
                    expendintent.putExtras(expendbundle);
                    startActivity(expendintent);
                }


            }
        });
        purchaseListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                PrichaseIncomeBean.DataBean dataBean = mList.get(position);
                String title = menu.getMenuItem(index).getTitle();
                if (title.equals("退货")) {
                    Intent intentOutBound = new Intent();
                    Bundle bundleOutBound = new Bundle();
                    if (type.equals(InvoicingConstants.PURCHASE)) {
                        //采购退货
                        intentOutBound.setClass(PurchaseOrderListActivity.this, AddReturnsActivity.class);
                        bundleOutBound.putString(InvoicingConstants.TYPE, type);
                    } else if (type.equals(InvoicingConstants.SALES)) {
                        //销售退货
                        intentOutBound.setClass(PurchaseOrderListActivity.this, AddReturnsActivity.class);
                        bundleOutBound.putString(InvoicingConstants.TYPE, type);
                    }
                    bundleOutBound.putSerializable(InvoicingConstants.hdid, (Serializable) dataBean);
                    intentOutBound.putExtras(bundleOutBound);
                    startActivity(intentOutBound);
                } else if (title.equals("编辑")) {
                    Intent intentOutBound = new Intent();
                    Bundle bundleOutBound = new Bundle();
                    if (type.equals(InvoicingConstants.PURCHASE)) {
                        //采购编辑
                        intentOutBound.setClass(PurchaseOrderListActivity.this, AddPurchaseActivity.class);
                        bundleOutBound.putString(InvoicingConstants.TYPE, "edit");
                    } else if (type.equals(InvoicingConstants.SALES)) {
                        //销售编辑
                        intentOutBound.setClass(PurchaseOrderListActivity.this, AddSalesActivity.class);
                        bundleOutBound.putString(InvoicingConstants.TYPE, "edit");
                    }
                    bundleOutBound.putSerializable(InvoicingConstants.hdid, (Serializable) dataBean);
                    intentOutBound.putExtras(bundleOutBound);
                    startActivity(intentOutBound);
                }
                return false;
            }
        });
        adapter = new PurchaseSwitchListAdapter(PurchaseOrderListActivity.this, mList, "0", type);
        purchaseListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doBusiness(Context mContext) {

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
        PostFormBuilder post = OkHttpUtils.post();
        if (pstype.equals(InvoicingConstants.ADDRETURN)) {
            post.addParams("merid", merid);
            post.addParams("state", "1");
        }
        LogUtils.d("登陆的url" + url);
        post.tag(this)
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
                            Toast.makeText(PurchaseOrderListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(PurchaseOrderListActivity.this, "没有更多数据!", Toast.LENGTH_SHORT).show();
                                        }
                                        adapter.notifyDataSetChanged();
                                        llList.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            llList.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            Toast.makeText(PurchaseOrderListActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    llList.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    Toast.makeText(PurchaseOrderListActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(PurchaseOrderListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                            Toast.makeText(PurchaseOrderListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
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

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause) {
            smartRefreshLayout.autoRefresh();
        }
    }

}
