package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
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
import com.mtecc.mmp.invoicing.activity.employee.EmployeeSeeActivity;
import com.mtecc.mmp.invoicing.activity.employee.adapter.SelectEmployeedialogListAdapter;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.activity.role.RoleListActivity;
import com.mtecc.mmp.invoicing.activity.role.bean.RoleBean;
import com.mtecc.mmp.invoicing.activity.shop.adapter.RoleListFenPeiAdapter;
import com.mtecc.mmp.invoicing.activity.shop.adapter.ShopEmployeeSwitchListAdapter;
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

public class ShopEmployeeActivity extends BaseActivity {

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
    List<EmployeeListBean.DataBean> mList = new ArrayList<>();
    List<RoleBean.DataBean> mRoleList = new ArrayList<>();
    List<EmployeeListBean.DataBean> mDislogList = new ArrayList<>();
    private ShopEmployeeSwitchListAdapter adapter;
    int pagenum = 1;
    private int cid;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private String shopId = "";
    private RoleListFenPeiAdapter roleListFenPeiAdapter;
    private String userId = "";

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        shopId = parms.getString("shopId");
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        tvTitle.setText("店铺员工");
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        mList.clear();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        userId = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        requestNetShopList(pagenum + "", shopId);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                openItem.setWidth(dp2px(100));
                openItem.setTitle("分配角色");
                openItem.setTitleSize(16);
                openItem.setTitleColor(Color.WHITE);
                // 将创建的菜单项添加进菜单中
                menu.addMenuItem(openItem);
            }
        };
        shopListRecyclerView.setMenuCreator(creator);
        shopListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        adapter = new ShopEmployeeSwitchListAdapter(this, mList);
        shopListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        View view1 = ShowDalogUtils.showCustomizeDialog(ShopEmployeeActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(ShopEmployeeActivity.this, false, view1);
        showDialog.dismiss();

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_shop_employee;
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
                mList.clear();
                pagenum = 1;
                requestNetShopList(pagenum + "", shopId);
                adapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetShopList(pagenum + "", shopId);

            }
        });

        shopListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                EmployeeListBean.DataBean dataBean = mList.get(position);
                if (title.equals("分配角色")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(ShopEmployeeActivity.this, R.layout.get_role_list_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(ShopEmployeeActivity.this, false, customizeDialog);
                    bindRoleClick(customizeDialog, alertDialog, dataBean);

                }
                return false;
            }
        });

        shopListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmployeeListBean.DataBean dataBean = mList.get(position);
                Intent intentemployee = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(InvoicingConstants.selectuserid, dataBean);
                bundle.putString(InvoicingConstants.Employee_List_TYPE, InvoicingConstants.SHOP_Employee);
                intentemployee.setClass(ShopEmployeeActivity.this, EmployeeSeeActivity.class);
                intentemployee.putExtras(bundle);
                startActivity(intentemployee);
            }
        });
    }

    /**
     * 绑定角色
     *
     * @param customizeDialog
     * @param alertDialog
     * @param dataBean
     */
    private void bindRoleClick(View customizeDialog, final AlertDialog alertDialog, final EmployeeListBean.DataBean dataBean) {
        requestNetRoleList(cid + "");
        ListView listView = customizeDialog.findViewById(R.id.role_list_view);
        ImageView imgX = customizeDialog.findViewById(R.id.img_x_dialog);
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        roleListFenPeiAdapter = new RoleListFenPeiAdapter(ShopEmployeeActivity.this, mRoleList);
        listView.setAdapter(roleListFenPeiAdapter);
        roleListFenPeiAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                RoleBean.DataBean dataBean1 = mRoleList.get(position);
                requestNetSetRole(dataBean.getUserid() + "", dataBean1.getUsergpid() + "", shopId);
            }
        });


    }

    @Override
    public void doBusiness(Context mContext) {


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
//                mList.clear();
//                shopname = etSerch.getText().toString().trim();
//                pagenum = 1;
//                requestNetShopList(pagenum + "", limit, cid + "", shopname);
//                adapter.notifyDataSetChanged();
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
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(ShopEmployeeActivity.this, R.layout.add_select_employee_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(ShopEmployeeActivity.this, false, customizeDialog);
                SelectEmployeeClick(customizeDialog, alertDialog);
                break;
        }
    }

    /**
     * 添加店员
     *
     * @param customizeDialog
     * @param alertDialog
     */
    private void SelectEmployeeClick(View customizeDialog, final AlertDialog alertDialog) {
        requestNetuserList(cid + "");
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        TextView tvnames = customizeDialog.findViewById(R.id.tv_select_names);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_select_sure);
        SelectEmployeedialogListAdapter selectEmployeedialogListAdapter = new SelectEmployeedialogListAdapter(ShopEmployeeActivity.this,
                mDislogList, mList, tvnames, tvSure, shopId, smartRefreshLayout, alertDialog);
        selectList.setAdapter(selectEmployeedialogListAdapter);
        selectEmployeedialogListAdapter.notifyDataSetChanged();
        ImageView imgX = customizeDialog.findViewById(R.id.img_x_dialog);
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 店铺员工信息
     *
     * @param page
     * @param shopid
     */
    private void requestNetShopList(String page, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.manlistfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息ShopEmployeeActivity" + response.toString());
                            Gson gson = new Gson();
                            EmployeeListBean employeeListBean = gson.fromJson(response, EmployeeListBean.class);
                            if (employeeListBean != null) {
                                List<EmployeeListBean.DataBean> dataList = employeeListBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    adapter.notifyDataSetChanged();
                                    shopListRecyclerView.setVisibility(View.VISIBLE);
                                    tvError.setVisibility(View.GONE);

                                } else {
                                    shopListRecyclerView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 获取所有可用员工信息
     *
     * @param cid
     */
    private void requestNetuserList(String cid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.manlistforcompany_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("cid", cid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息ShopEmployeeActivity" + response.toString());
                            Gson gson = new Gson();
                            EmployeeListBean employeeListBean = gson.fromJson(response, EmployeeListBean.class);
                            if (employeeListBean != null) {
                                List<EmployeeListBean.DataBean> dataList = employeeListBean.getData();
                                if (dataList != null) {
                                    mDislogList.addAll(dataList);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 获取角色列表
     *
     * @param cid
     */
    private void requestNetRoleList(String cid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.manlistforRole_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + shopId);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("cid", cid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息ShopEmployeeActivity" + e.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息ShopEmployeeActivity" + response.toString());
                            Gson gson = new Gson();
                            RoleBean roleBean = gson.fromJson(response, RoleBean.class);
                            if (roleBean != null) {
                                List<RoleBean.DataBean> dataBeanList = roleBean.getData();
                                if (dataBeanList != null) {
                                    mRoleList.addAll(dataBeanList);
                                    roleListFenPeiAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(ShopEmployeeActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 分配角色
     */
    private void requestNetSetRole(String userid, String groupid, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.bindRole_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + shopId);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("userid", userid)
                .addParams("groupid", groupid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息ShopEmployeeActivity" + e.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息ShopEmployeeActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("分配角色成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("分配角色失败!");
                                }
                            } else {
                                Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopEmployeeActivity" + e1.toString());
                            Toast.makeText(ShopEmployeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
