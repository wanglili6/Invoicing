package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchase.adapter.PurchaseSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.purchase.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购审核
 */
public class CheckListActivity extends BaseActivity {

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
    @BindView(R.id.purchase_list_recycler_view)
    ListView purchaseListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private CheckListAdapter adapter;
    int pagenum = 1;
    String limit = "20";
    String shopname = "";
    String shopStatus = "";
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private String shopid = "";
    private String SHOP_ID = "";
    private boolean isPause = false;
    List<PurchaseListBean> mList = new ArrayList<>();
    private String type = "";

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        type = parms.getString(InvoicingConstants.check_type);
        if (type.equals(InvoicingConstants.check_purchases)) {
            tvTitle.setText("采购审核");

        } else if (type.equals(InvoicingConstants.check_sales)) {
            tvTitle.setText("销售审核");

        }
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        mList.clear();
        for (int i = 0; i < 20; i++) {
            Random runnable = new Random();
            int i2 = runnable.nextInt(2);
            PurchaseListBean bean = new PurchaseListBean();
            bean.setCode(i + "09090" + i);
            bean.setMoney(i + "000" + i);
            bean.setName(i + "订单名称" + i);
            bean.setTimer(i + "09090" + i);
            bean.setState("0");
            bean.setType(i2 + "");
            mList.add(bean);
        }
        cid = PreferencesUtils.getInt(CheckListActivity.this, InvoicingConstants.QY_ID, 0);
        SHOP_ID = PreferencesUtils.getString(CheckListActivity.this, InvoicingConstants.SHOP_ID, "");
        adapter = new CheckListAdapter(CheckListActivity.this, mList, type);
        purchaseListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_purchase_check_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
                pagenum = 1;
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
//                requestNetShopList(pagenum + "", limit, cid + "", "", SHOP_ID);

            }
        });


        purchaseListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PurchaseListBean purchaseListBean = mList.get(position);
                Intent incomeintent = new Intent();
                Bundle incomebundle = new Bundle();
                incomeintent.setClass(CheckListActivity.this, CheckActivity.class);
                if (type.equals(InvoicingConstants.check_purchases)) {
                    if (purchaseListBean.getType().equals("0")) {
                        incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                    } else {
                        incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases_out);
                    }

                } else if (type.equals(InvoicingConstants.check_sales)) {
                    if (purchaseListBean.getType().equals("0")) {
                        incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                    } else {
                        incomebundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales_out);
                    }

                }
                incomebundle.putString(InvoicingConstants.check_id, "");
                incomeintent.putExtras(incomebundle);
                startActivity(incomeintent);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

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
                shopname = etSerch.getText().toString().trim();
                pagenum = 1;
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
