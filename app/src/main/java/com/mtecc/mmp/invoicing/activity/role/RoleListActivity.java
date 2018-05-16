package com.mtecc.mmp.invoicing.activity.role;

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
import com.mtecc.mmp.invoicing.activity.role.adapter.RoleListAdapter;
import com.mtecc.mmp.invoicing.activity.role.bean.RoleAddBean;
import com.mtecc.mmp.invoicing.activity.role.bean.RoleBean;
import com.mtecc.mmp.invoicing.activity.shop.SelectShopListActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopManagementActivity;
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

/**
 * 角色管理
 */
public class RoleListActivity extends BaseActivity {

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
    @BindView(R.id.ll_seach)
    LinearLayout llSeach;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.role_list_recycler_view)
    SwipeMenuListView roleListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    private AwayKetBordUtils awayKetBordUtils;
    List<RoleBean.DataBean> mList = new ArrayList<>();
    private String roleName;
    private int pagenum = 1;
    private RoleListAdapter roleListAdapter;
    private String roleStatus = "0";
    private AlertDialog showDialog;
    private String roleId = "";
    private String type = "";
    private int cid;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        llSeach.setVisibility(View.GONE);
        if (parms != null) {
            type = parms.getString(InvoicingConstants.role_TYPE);
        }
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        tvTitle.setText("角色管理");
        mList.clear();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        requestNetRoleList(cid + "", pagenum + "");

        roleListAdapter = new RoleListAdapter(this, mList);
        roleListRecyclerView.setAdapter(roleListAdapter);
        roleListAdapter.notifyDataSetChanged();

        View view1 = ShowDalogUtils.showCustomizeDialog(RoleListActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(RoleListActivity.this, false, view1);
        showDialog.dismiss();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_role_list;
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
                requestNetRoleList(cid + "", pagenum + "");
                roleListAdapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1500);
                pagenum++;
                requestNetRoleList(cid + "", pagenum + "");
                roleListAdapter.notifyDataSetChanged();

            }
        });

        if (type.equals(InvoicingConstants.role_see)) {
            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // 创建“打开”项
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    openItem.setBackground(new ColorDrawable(Color.parseColor("#0099FF")));
                    openItem.setWidth(dp2px(100));
                    openItem.setTitle("编辑");
                    openItem.setTitleSize(16);
                    openItem.setTitleColor(Color.WHITE);
                    // 将创建的菜单项添加进菜单中
                    menu.addMenuItem(openItem);

                    // 创建“删除”项
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    deleteItem.setBackground(new ColorDrawable(Color.parseColor("#FD3B31")));
                    deleteItem.setWidth(dp2px(100));
                    deleteItem.setTitle("删除");
                    deleteItem.setTitleSize(16);
                    deleteItem.setTitleColor(Color.WHITE);
                    // 将创建的菜单项添加进菜单中
                    menu.addMenuItem(deleteItem);
                }
            };
            roleListRecyclerView.setMenuCreator(creator);
            roleListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            roleListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    String title = menu.getMenuItem(index).getTitle();
                    RoleBean.DataBean dataBean = mList.get(position);
                    if (title.equals("编辑")) {
                        View customizeDialog = ShowDalogUtils.showCustomizeDialog(RoleListActivity.this, R.layout.edit_role_dialog);
                        AlertDialog alertDialog = ShowDalogUtils.showDialog(RoleListActivity.this, false, customizeDialog);
                        editClick(customizeDialog, alertDialog, dataBean);

                    } else if (title.equals("删除")) {
                        View customizeDialog = ShowDalogUtils.showCustomizeDialog(RoleListActivity.this, R.layout.exit_dialog);
                        AlertDialog alertDialog = ShowDalogUtils.showDialog(RoleListActivity.this, false, customizeDialog);
                        dialogClick(customizeDialog, alertDialog, dataBean);
                    }
                    return false;
                }
            });
        } else if (type.equals(InvoicingConstants.role_select)) {
            roleListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    RoleBean.DataBean dataBean = mList.get(position);
                    Intent intentemployee = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("roleName", dataBean.getUsergpname());
                    bundle.putString("roleId", dataBean.getUsergpid() + "");
                    intentemployee.setClass(RoleListActivity.this, EmployeeListActivity.class);
                    intentemployee.putExtras(bundle);
                    setResult(2, intentemployee);
                    finish();
                }
            });
        }


    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.img_select, R.id.rl_back, R.id.iv_bar_search, R.id.et_serch, R.id.iv_serch, R.id.rl_serch_bar, R.id.tv_search})
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
                roleName = etSerch.getText().toString().trim();
                pagenum = 1;
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
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(RoleListActivity.this, R.layout.edit_role_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(RoleListActivity.this, false, customizeDialog);
                editClick(customizeDialog, alertDialog);
                break;
        }
    }

    /**
     * 修改
     *
     * @param customizeDialog
     * @param alertDialog
     * @param dataBean
     */
    private void editClick(View customizeDialog, final AlertDialog alertDialog, final RoleBean.DataBean dataBean) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        final TextView tvTitle = customizeDialog.findViewById(R.id.role_tv_title);
        final EditText tvname = customizeDialog.findViewById(R.id.role_mange_name);
        final EditText tvDes = customizeDialog.findViewById(R.id.role_mange_des);
        Switch tvstatus = customizeDialog.findViewById(R.id.role_spinner_status);

        if (dataBean != null) {
            tvTitle.setText("修改店铺");
            tvname.setText(dataBean.getUsergpname());
            tvDes.setText(dataBean.getUsergpdesc());
            String shopstate = dataBean.getIsactive();
            if (shopstate.equals("可用")) {
                tvstatus.setChecked(true);
            } else if (shopstate.equals("禁用")) {
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
                    roleStatus = "0";
                } else {
                    roleStatus = "";
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
                String des = tvDes.getText().toString().trim();
                RoleAddBean roleBean = new RoleAddBean();
                roleBean.setUsergpdesc(des);
                roleBean.setCid(cid);
                roleBean.setUsergpname(name);
                roleBean.setUsergpid(dataBean.getUsergpid());
                roleBean.setIsactive(roleStatus);
                Gson gson = new Gson();
                String editJson = gson.toJson(roleBean);
                requestNetEditShop(editJson);
                LogUtils.i("角色修改" + editJson);
                smartRefreshLayout.autoRefresh();
            }
        });
    }

    /**
     * 添加
     *
     * @param customizeDialog
     * @param alertDialog
     */
    private void editClick(View customizeDialog, final AlertDialog alertDialog) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        final TextView tvTitle = customizeDialog.findViewById(R.id.role_tv_title);
        final EditText tvname = customizeDialog.findViewById(R.id.role_mange_name);
        final EditText tvDes = customizeDialog.findViewById(R.id.role_mange_des);
        LinearLayout tvstatus = customizeDialog.findViewById(R.id.ll_role_status);
        tvstatus.setVisibility(View.GONE);
        tvTitle.setText("添加角色");
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:添加
                showDialog.show();
                alertDialog.dismiss();
                String name = tvname.getText().toString().trim();
                String des = tvDes.getText().toString().trim();
                requestNetAddtShop(name, des, cid + "");
                smartRefreshLayout.autoRefresh();
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
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog, RoleBean.DataBean dataBean) {
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
            roleId = dataBean.getUsergpid() + "";
        }
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的是
                alertDialog.dismiss();
                showDialog.show();
                LogUtils.i("删除的角色" + roleId);
                requestNetDelRole(roleId);
                smartRefreshLayout.autoRefresh();
            }
        });
    }

    /**
     * 列表
     *
     * @param cid
     */
    private void requestNetRoleList(String cid, String page) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.roleList_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("cid", cid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息RoleListActivity" + e.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息RoleListActivity" + response.toString());

                            Gson gson = new Gson();
                            RoleBean roleBean = gson.fromJson(response, RoleBean.class);
                            if (roleBean != null) {
                                List<RoleBean.DataBean> dataBeanList = roleBean.getData();
                                if (dataBeanList != null) {
                                    mList.addAll(dataBeanList);
                                    roleListAdapter.notifyDataSetChanged();
                                    llList.setVisibility(View.VISIBLE);
                                    tvError.setVisibility(View.GONE);

                                } else {
                                    llList.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    Toast.makeText(RoleListActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 修改
     *
     * @param editJson
     */
    private void requestNetEditShop(String editJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.roleEdit_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("roleBean", editJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息RoleListActivity" + e.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息RoleListActivity" + response.toString());

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
                                Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 添加角色
     *
     * @param usergpname
     * @param usergpdesc
     * @param cid
     */
    private void requestNetAddtShop(String usergpname, String usergpdesc, String cid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.roleAdd_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + usergpname);
        LogUtils.d("登陆的url" + usergpdesc);
        LogUtils.d("登陆的url" + cid);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("usergpname", usergpname)
                .addParams("usergpdesc", usergpdesc)
                .addParams("cid", cid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息RoleListActivity" + e.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息RoleListActivity" + response.toString());

                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("添加失败!");
                                }
                            } else {
                                Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 删除
     *
     * @param id
     */
    private void requestNetDelRole(String id) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.roleDelete_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("usergpid", id)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息RoleListActivity" + e.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息RoleListActivity" + response.toString());

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
                                Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RoleListActivity" + e1.toString());
                            Toast.makeText(RoleListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
