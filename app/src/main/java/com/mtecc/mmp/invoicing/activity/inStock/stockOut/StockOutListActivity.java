package com.mtecc.mmp.invoicing.activity.inStock.stockOut;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 出库列表
 */
public class StockOutListActivity extends BaseActivity {


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
    @BindView(R.id.img_stock_out_list_saoma)
    ImageView imgStockOutListSaoma;
    @BindView(R.id.stock_out_list_recycler_view)
    SwipeRefreshLayout stockOutListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_stock_out_list)
    LinearLayout llStockOutList;
    @BindView(R.id.tv_error)
    TextView tvError;
    private AwayKetBordUtils awayKetBordUtils;
    List<PurchaseListBean> mList = new ArrayList<>();

    int pagenum = 1;
    String limit = "20";
    String shopname = "";
    String shopStatus = "";
    private int cid;
    private AlertDialog showDialog;
    private String shopid = "";
    private String SHOP_ID = "";
    private boolean isPause = false;
    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_stock_out_list;
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
                shopname = etSerch.getText().toString().trim();
                pagenum = 1;
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
