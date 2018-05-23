package com.mtecc.mmp.invoicing.activity.purchase.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.mtecc.mmp.invoicing.activity.check.SeeOrdersActivity;
import com.mtecc.mmp.invoicing.activity.purchase.AddPurchaseActivity;
import com.mtecc.mmp.invoicing.activity.purchase.PurchaseListActivity;
import com.mtecc.mmp.invoicing.activity.purchase.adapter.PurchaseSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.purchase.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopAddBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.views.MySwipeMenuListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * Created by wll on 2018/4/16.
 * 退货单
 */

public class PurchaseOutFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.purchase_list_recycler_view)
    MySwipeMenuListView purchaseListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    List<PurchaseListBean> mList = new ArrayList<>();
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
    private PurchaseListActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.purchase_out_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        awayKetBordUtils = new AwayKetBordUtils(getActivity(), getActivity().getWindow());
        mList.clear();

        for (int i1 = 0; i1 < 20; i1++) {
            Random random = new Random();
            int i = random.nextInt(3);

            PurchaseListBean bean = new PurchaseListBean();
            bean.setCode(i1 + "09090" + i);
            bean.setMoney(i1 + "000" + i);
            bean.setName(i1 + "订单名称" + i);
            bean.setTimer(i1 + "09090" + i);
            bean.setState(i + "");
            mList.add(bean);
        }
        cid = PreferencesUtils.getInt(getActivity(), InvoicingConstants.QY_ID, 0);
        SHOP_ID = PreferencesUtils.getString(getActivity(), InvoicingConstants.SHOP_ID, "");
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
//                mList.clear();
                pagenum = 1;
//                requestNetShopList(pagenum + "", limit, cid + "", "", SHOP_ID);
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

        purchaseListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
//                ShopListBean.DataBean dataBean = mList.get(position);
//                if (title.equals("编辑")) {
//                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(PurchaseListActivity.this, R.layout.edit_dialog);
//                    AlertDialog alertDialog = ShowDalogUtils.showDialog(PurchaseListActivity.this, false, customizeDialog);
//                    editClick(customizeDialog, alertDialog, dataBean);
//
//                } else if (title.equals("删除")) {
//                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(PurchaseListActivity.this, R.layout.exit_dialog);
//                    AlertDialog alertDialog = ShowDalogUtils.showDialog(PurchaseListActivity.this, false, customizeDialog);
//                    dialogClick(customizeDialog, alertDialog, dataBean);
//                }
                return false;
            }
        });

        purchaseListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PurchaseListBean purchaseListBean = mList.get(position);
                Intent expendintent = new Intent();
                Bundle expendbundle = new Bundle();

                expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases_out);

                expendintent.setClass(getContext(), SeeOrdersActivity.class);
                expendintent.putExtras(expendbundle);
                startActivity(expendintent);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.parseColor("#0099FF")));
                openItem.setWidth(dp2px(100));
                openItem.setTitle("编辑");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);

                // 创建“删除”项
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                deleteItem.setWidth(dp2px(100));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(deleteItem);
            }
        };
        purchaseListRecyclerView.setMenuCreator(creator);
        purchaseListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        activity = (PurchaseListActivity) getActivity();
        purchaseListRecyclerView.setNestedpParent(activity.getIncomeSwitchOverVp());
        adapter = new PurchaseSwitchListAdapter(getContext(), mList, "1");
        purchaseListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 店铺信息
     *
     * @param page
     * @param limit
     * @param cid
     * @param shopname
     */
    private void requestNetShopList(String page, String limit, String cid, String shopname, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shoplistfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("limit", limit)
                .addParams("cid", cid)
                .addParams("shopid", shopid)
                .addParams("shopname", shopname)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
                            Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息PurchaseListActivity" + response.toString());
                            Gson gson = new Gson();
//                            ShopListBean shopListBean = gson.fromJson(response, ShopListBean.class);
//                            if (shopListBean != null) {
//                                List<ShopListBean.DataBean> dataList = shopListBean.getData();
//                                if (dataList != null) {
//                                    mList.addAll(dataList);
//                                    if (mList.size() != 0) {
//                                        if (dataList.size() == 0) {
//                                            showToast("没有更多数据!");
//                                        }
//                                        adapter.notifyDataSetChanged();
//                                        llList.setVisibility(View.VISIBLE);
//                                        tvError.setVisibility(View.GONE);
//                                    } else {
//                                        if (dataList.size() == 0) {
//                                            llList.setVisibility(View.GONE);
//                                            tvError.setVisibility(View.VISIBLE);
//                                            showToast("暂无店铺,请进行添加!");
//                                        }
//                                    }
//                                } else {
//                                    llList.setVisibility(View.GONE);
//                                    tvError.setVisibility(View.VISIBLE);
//                                    showToast("暂无数据!");
//                                }
//
//                            } else {
//                                Toast.makeText(PurchaseListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
//                            }


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
                shopname = etSerch.getText().toString().trim();
                pagenum = 1;
                requestNetShopList(pagenum + "", limit, cid + "", shopname, SHOP_ID);
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

    /**
     * 删除对话框
     *
     * @param customizeDialog
     * @param alertDialog
     * @param dataBean
     */
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog, ShopListBean.DataBean dataBean) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvContant = customizeDialog.findViewById(R.id.dialog_tv_contant);
        tvContant.setText("是否删除店铺?");
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });
        if (dataBean != null) {
            shopid = dataBean.getShopid() + "";
        }
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的是
                alertDialog.dismiss();
                showDialog.show();
//                requestNetdelShop(shopid);
            }
        });
    }

    /**
     * 编辑
     *
     * @param customizeDialog
     * @param alertDialog
     * @param dataBean
     */
    private void editClick(View customizeDialog, final AlertDialog alertDialog, final ShopListBean.DataBean dataBean) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        final EditText tvname = customizeDialog.findViewById(R.id.shop_mange_name);
        final EditText tvcode = customizeDialog.findViewById(R.id.shop_mange_code);
        final EditText tvaddress = customizeDialog.findViewById(R.id.shop_mange_address);
        Switch tvstatus = customizeDialog.findViewById(R.id.shop_spinner_status);
        if (dataBean != null) {
            tvname.setText(dataBean.getShopname());
            tvcode.setText(dataBean.getShopnum() + "");
            tvaddress.setText(dataBean.getShopaddress());
            String shopstate = dataBean.getShopstate();
            if (shopstate.equals("0")) {
                tvstatus.setChecked(true);
            } else {
                tvstatus.setChecked(false);
            }
        }
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });
        tvstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shopStatus = "0";
                } else {
                    shopStatus = "";
                }
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:修改
                showDialog.show();
                alertDialog.dismiss();
                String name = tvname.getText().toString().trim();
                String code = tvcode.getText().toString().trim();
                String address = tvaddress.getText().toString().trim();
                ShopAddBean shopAddBean = new ShopAddBean();
                shopAddBean.setShopname(name);
                shopAddBean.setShopnum(code);
                shopAddBean.setAddress(address);
                shopAddBean.setShopstate(shopStatus);
                shopAddBean.setShopid(dataBean.getShopid());
                Gson gson = new Gson();
                String editJson = gson.toJson(shopAddBean);
//                requestNetEditShop(editJson);

            }
        });
    }

//    /**
//     * 修改
//     *
//     * @param editJson
//     */
//    private void requestNetEditShop(String editJson) {
//        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shopEdit_URL;
//        LogUtils.d("登陆的url" + url);
//        OkHttpUtils
//                .post()
//                .tag(this)
//                .addParams("ywshopBean", editJson)
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        try {
//                            showDialog.dismiss();
//                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
//                            Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
//                        } catch (Exception e1) {
//                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        try {
//                            showDialog.dismiss();
//                            LogUtils.d("返回值信息PurchaseListActivity" + response.toString());
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            int result = jsonObject.optInt("result");
//                            if (result != 0) {
//                                String reslut = result + "";
//                                if (reslut.equals("200")) {
//                                    showToast("修改成功!");
//                                    smartRefreshLayout.autoRefresh();
//                                } else {
//                                    showToast("修改失败!");
//                                }
//                            } else {
//                                Toast.makeText(PurchaseListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e1) {
//                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
//                            Toast.makeText(PurchaseListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }


//    /**
//     * 删除店铺
//     *
//     * @param shopId
//     */
//    private void requestNetdelShop(String shopId) {
//        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shopDel_URL;
//        LogUtils.d("登陆的url" + url);
//        LogUtils.d("登陆的url" + shopId);
//        OkHttpUtils
//                .post()
//                .tag(this)
//                .addParams("shopid", shopId)
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        try {
//                            showDialog.dismiss();
//                            LogUtils.d("错误信息PurchaseListActivity" + e.toString());
//                            Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
//                        } catch (Exception e1) {
//                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        try {
//                            showDialog.dismiss();
//                            LogUtils.d("返回值信息PurchaseListActivity" + response.toString());
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            int result = jsonObject.optInt("result");
//                            if (result != 0) {
//                                String reslut = result + "";
//                                if (reslut.equals("200")) {
//                                    showToast("删除成功!");
//                                    smartRefreshLayout.autoRefresh();
//                                } else {
//                                    showToast("删除失败!");
//                                }
//                            } else {
//                                Toast.makeText(PurchaseListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e1) {
//                            LogUtils.d("错误信息PurchaseListActivity" + e1.toString());
//                            Toast.makeText(PurchaseListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }


}
