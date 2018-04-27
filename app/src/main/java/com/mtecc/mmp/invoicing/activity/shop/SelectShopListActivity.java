package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeListActivity;
import com.mtecc.mmp.invoicing.activity.shop.adapter.ShopListAdapter;
import com.mtecc.mmp.invoicing.activity.shop.adapter.ShopSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopAddBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

public class SelectShopListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.iv_bar_search)
    RelativeLayout ivBarSearch;
    @BindView(R.id.et_serch)
    EditText etSerch;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_serch_bar)
    LinearLayout rlSerchBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.shop_list_recycler_view)
    ListView shopListRecyclerView;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<ShopListBean.DataBean> mList = new ArrayList<>();
    private ShopListAdapter adapter;
    int pagenum = 1;
    String limit = "20";
    String shopname = "";
    String shopStatus = "";
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private String shopid = "";

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        tvTitle.setText("选择店铺");
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        requestNetShopList(pagenum + "", limit, cid + "", "");


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
                mList.clear();
                pagenum = 1;
                requestNetShopList(pagenum + "", limit, cid + "", "");
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetShopList(pagenum + "", limit, cid + "", "");

            }
        });


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_shop_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        shopListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopListBean.DataBean dataBean = mList.get(position);
                Intent intentemployee = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("shopName", dataBean.getShopname());
                bundle.putString("shopId", dataBean.getShopid() + "");
                intentemployee.setClass(SelectShopListActivity.this, EmployeeListActivity.class);
                intentemployee.putExtras(bundle);
                setResult(1, intentemployee);
                finish();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new ShopListAdapter(this, mList);
        shopListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @OnClick({R.id.rl_back, R.id.iv_bar_search, R.id.tv_search, R.id.iv_serch, R.id.img_select})
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
                requestNetShopList(pagenum + "", limit, cid + "", shopname);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_serch:
                //点击叉号
                awayKetBordUtils.putAwayKetBord();
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;
            case R.id.img_select:
                //点击加号
                Intent intent = new Intent();
                intent.setClass(this, ShopManagementActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 店铺信息
     *
     * @param page
     * @param limit
     * @param cid
     * @param shopname
     */
    private void requestNetShopList(String page, String limit, String cid, String shopname) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shoplistfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("limit", limit)
                .addParams("cid", cid)
                .addParams("shopname", shopname)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息ShopListActivity" + e.toString());
                            Toast.makeText(SelectShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息ShopListActivity" + response.toString());
                            Gson gson = new Gson();
                            ShopListBean shopListBean = gson.fromJson(response, ShopListBean.class);
                            if (shopListBean != null) {
                                List<ShopListBean.DataBean> dataList = shopListBean.getData();
                                if (dataList != null) {
                                    if (dataList.size() == 0) {
                                        llList.setVisibility(View.GONE);
                                        tvError.setVisibility(View.VISIBLE);
                                        showToast("暂无数据!");
                                    } else {
                                        mList.addAll(dataList);
                                        adapter.notifyDataSetChanged();
                                        llList.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    }

                                } else {
                                    llList.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(SelectShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                            Toast.makeText(SelectShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
