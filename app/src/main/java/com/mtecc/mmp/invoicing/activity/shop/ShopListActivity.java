package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
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

public class ShopListActivity extends BaseActivity {

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
    SwipeMenuListView shopListRecyclerView;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<ShopListBean.DataBean> mList = new ArrayList<>();
    private ShopSwitchListAdapter adapter;
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
        tvTitle.setText("我的店铺");
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

        View view1 = ShowDalogUtils.showCustomizeDialog(ShopListActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(ShopListActivity.this, false, view1);
        showDialog.dismiss();

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
        //设置recycleView的布局管理器
//        shopListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.parseColor("#C8C7CC")));
                openItem.setWidth(dp2px(100));
                openItem.setTitle("编辑");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);

                // 创建“删除”项
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                deleteItem.setWidth(dp2px(100));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(deleteItem);
            }
        };
        shopListRecyclerView.setMenuCreator(creator);
        shopListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        adapter = new ShopSwitchListAdapter(this, mList);
        shopListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        shopListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                ShopListBean.DataBean dataBean = mList.get(position);
                if (title.equals("编辑")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(ShopListActivity.this, R.layout.edit_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(ShopListActivity.this, false, customizeDialog);
                    editClick(customizeDialog, alertDialog, dataBean);

                } else if (title.equals("删除")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(ShopListActivity.this, R.layout.exit_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(ShopListActivity.this, false, customizeDialog);
                    dialogClick(customizeDialog, alertDialog, dataBean);
                }
                return true;
            }
        });
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
                Bundle bundle = new Bundle();
                intent.setClass(this, ShopManagementActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
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
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                requestNetdelShop(shopid);
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
                requestNetEditShop(editJson);

            }
        });
    }

    /**
     * 修改
     *
     * @param editJson
     */
    private void requestNetEditShop(String editJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shopEdit_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("ywshopBean", editJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息ShopListActivity" + e.toString());
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息ShopListActivity" + response.toString());

                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("修改成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("修改失败!");
                                }
                            } else {
                                Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 删除店铺
     *
     * @param shopId
     */
    private void requestNetdelShop(String shopId) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shopDel_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + shopId);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("shopid", shopId)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息ShopListActivity" + e.toString());
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息ShopListActivity" + response.toString());

                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("删除成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("删除失败!");
                                }
                            } else {
                                Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopListActivity" + e1.toString());
                            Toast.makeText(ShopListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
