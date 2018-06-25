package com.mtecc.mmp.invoicing.activity.comodity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.mtecc.mmp.invoicing.activity.SaoMaActivity;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.CommodityListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.shop.ShopEmployeeActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopListActivity;
import com.mtecc.mmp.invoicing.activity.shop.adapter.ShopSwitchListAdapter;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
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
 * 商品列表
 */
public class CommodityListActivity extends BaseActivity {


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
    @BindView(R.id.img_commodity_list_saoma)
    ImageView imgCommodityListSaoma;
    @BindView(R.id.commodity_list_tv_default)
    TextView commodityListTvDefault;
    @BindView(R.id.commodity_list_tv_detop)
    TextView commodityListTvDetop;
    @BindView(R.id.commodity_list_tv_price)
    TextView commodityListTvPrice;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.commodity_list_recycle_view)
    SwipeMenuListView commodityListRecycleView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private CommodityListAdapter commodityListAdapter;
    List<CommodityListBean.DataBean> mList = new ArrayList<>();
    int pagenum = 1;
    String isuseradmin = "";
    String proCode = "";
    String proName = "";
    String barcode = "";
    String cid = "";
    String shopid = "";
    private boolean isPause;
    private EditText commodityDialogEtBarCode;
    private AlertDialog showDialog;


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgLeftSelect.setVisibility(View.VISIBLE);
        tvTitle.setText("商品列表");
        etSerch.setHint("请输入查询的商品的名称");
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        boolean aBoolean = PreferencesUtils.getBoolean(this, InvoicingConstants.isuseradmin, false);
        shopid = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0) + "";
        if (aBoolean) {
            isuseradmin = "true";
        } else {
            isuseradmin = "false";
        }
        View view1 = ShowDalogUtils.showCustomizeDialog(CommodityListActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(CommodityListActivity.this, false, view1);
        showDialog.dismiss();


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_commodity_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        //设置recycleView的布局管理器
//        commodityListRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mList.clear();
                pagenum = 1;
                requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
                commodityListAdapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pagenum++;
                requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);

            }
        });

        commodityListRecycleView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                CommodityListBean.DataBean dataBean = mList.get(position);
                if (title.equals("编辑")) {
                    Intent roleintent = new Intent(CommodityListActivity.this, AddCommodityActivity.class);
                    Bundle rolebundle = new Bundle();
                    rolebundle.putString(InvoicingConstants.COMMODITY_TYPE, InvoicingConstants.COMMODITY_EDIT);
                    rolebundle.putString(InvoicingConstants.COMMODITY_Id, dataBean.getProId() + "");
                    roleintent.putExtras(rolebundle);
                    startActivity(roleintent);

                } else if (title.equals("删除")) {
                    View exitView = ShowDalogUtils.showCustomizeDialog(CommodityListActivity.this, R.layout.exit_dialog);
                    AlertDialog dialog = ShowDalogUtils.showDialog(CommodityListActivity.this, false, exitView);
                    editClick(exitView, dialog, dataBean.getProId());
                }
                return false;
            }
        });
        commodityListRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查看商品
                CommodityListBean.DataBean dataBean = mList.get(position);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("proid", dataBean.getProId() + "");
                intent.setClass(CommodityListActivity.this, SeeCommodityActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    @Override
    public void doBusiness(Context mContext) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // 创建“打开”项
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.parseColor("#0099FF")));
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
        commodityListRecycleView.setMenuCreator(creator);
        commodityListRecycleView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
        commodityListAdapter = new CommodityListAdapter(this, mList);
        commodityListRecycleView.setAdapter(commodityListAdapter);
        commodityListAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.rl_back, R.id.img_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search, R.id.img_commodity_list_saoma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_bar_search:
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                break;
            case R.id.iv_serch:
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                etSerch.setText("");
                break;
            case R.id.tv_search:
                proName = etSerch.getText().toString().trim();
                //TODO:筛选请求
                mList.clear();
                pagenum = 1;
                requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
                commodityListAdapter.notifyDataSetChanged();

                break;
            case R.id.img_commodity_list_saoma:
                Intent intent = new Intent();
                intent.setClass(this, SaoMaActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.img_left_select:
                //筛选
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(this, R.layout.commodity_shaixuan_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, customizeDialogView);
                dialogClick(customizeDialogView, dialog);
                break;
            case R.id.img_select:
                //增加商品
                Intent roleintent = new Intent(this, AddCommodityActivity.class);
                Bundle rolebundle = new Bundle();
                rolebundle.putString(InvoicingConstants.COMMODITY_TYPE, InvoicingConstants.COMMODITY_ADD);
                roleintent.putExtras(rolebundle);
                startActivity(roleintent);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    //处理扫描结果（在界面上显示）
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            final String result = bundle.getString(CodeUtils.RESULT_STRING);
                            LogUtils.i("onActivityResult: " + result);
                            barcode = result;
                            requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
                            commodityListAdapter.notifyDataSetChanged();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(CommodityListActivity.this, "解析二维码失败!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;

            }
        }
    }

    /**
     * 对话框的初始化以及点击事件
     *
     * @param customizeDialogView
     * @param dialog
     */
    private void dialogClick(View customizeDialogView, final AlertDialog dialog) {
        TextView tvSure = customizeDialogView.findViewById(R.id.tv_sure);
        TextView tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        final EditText commodityDialogEtName = customizeDialogView.findViewById(R.id.commodity_dialog_et_name);
        commodityDialogEtBarCode = customizeDialogView.findViewById(R.id.commodity_dialog_et_bar_code);
        final EditText commodityDialogEtCode = customizeDialogView.findViewById(R.id.commodity_dialog_et_code);
        proName = etSerch.getText().toString().trim();
        commodityDialogEtName.setText(proName);
        commodityDialogEtBarCode.setText(barcode);
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                proName = commodityDialogEtName.getText().toString().trim();
                barcode = commodityDialogEtBarCode.getText().toString().trim();
                proCode = commodityDialogEtCode.getText().toString().trim();
                if (!TextUtils.isEmpty(proName)) {
                    rlSerchBar.setVisibility(View.VISIBLE);
                    ivBarSearch.setVisibility(View.GONE);
                    etSerch.setText(proName);
                } else {
                    rlSerchBar.setVisibility(View.GONE);
                    ivBarSearch.setVisibility(View.VISIBLE);
                    etSerch.setText("");
                }
                //TODO:筛选请求
                mList.clear();
                pagenum = 1;
                requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
                commodityListAdapter.notifyDataSetChanged();


            }
        });
    }

    /**
     * @param page
     * @param isuseradmin 是否是商家管理员
     * @param proCode     商品编号
     * @param proName     名称
     * @param barcode     条码
     * @param cid         公司id
     * @param shopid      店铺id
     */
    private void requestNetCommodityList(String page, String isuseradmin, final String proCode, final String proName, final String barcode, String cid, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.GoogSlistfortable_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("isuseradmin", isuseradmin)
                .addParams("proCode", proCode)
                .addParams("proName", proName)
                .addParams("barcode", barcode)
                .addParams("cid", cid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息CommodityListActivity" + e.toString());
                            Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            LogUtils.d("返回值信息CommodityListActivity" + response.toString());
                            Gson gson = new Gson();
                            CommodityListBean commodityListBean = gson.fromJson(response, CommodityListBean.class);
                            if (commodityListBean != null) {
                                List<CommodityListBean.DataBean> dataList = commodityListBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        commodityListAdapter.notifyDataSetChanged();
                                        commodityListRecycleView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            commodityListRecycleView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            if (!TextUtils.isEmpty(proName) || !TextUtils.isEmpty(proCode) || !TextUtils.isEmpty(barcode)) {
                                                tvError.setText("无符合条件的数据!");
                                                showToast("无符合条件的数据!");
                                            } else {
                                                showToast("暂无商品,请进行添加!");
                                            }
                                        }
                                    }
                                } else {
                                    commodityListRecycleView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                            Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    /**
     * 是否返回
     *
     * @param exitView
     * @param dialog
     * @param proId
     */
    private void editClick(View exitView, final AlertDialog dialog, final int proId) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除此商品?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDialog.show();
                requestNetdelCommodity(proId + "");

            }
        });
    }

    /**
     * 刪除
     */
    private void requestNetdelCommodity(final String proid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.deleteGood_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + proid);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("proid", proid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息SeeCommodityActivity" + e.toString());
                            Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息SeeCommodityActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("删除商品成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("删除商品失败!");
                                }
                            } else {
                                Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                            Toast.makeText(CommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
