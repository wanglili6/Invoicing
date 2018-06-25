package com.mtecc.mmp.invoicing.activity.distributor;

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
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributionListBean;
import com.mtecc.mmp.invoicing.activity.distributor.adapter.DistributorlistAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.AddPurchaseActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.AddReturnsActivity;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.AddSalesActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.AwayKetBordUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
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
 * 分销商/供货商的列表
 */
public class DistributorLIstActivity extends BaseActivity {

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
    @BindView(R.id.distribution_list_recycler_view)
    SwipeMenuListView distributionListRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_list)
    LinearLayout llList;
    @BindView(R.id.tv_error)
    TextView tvError;
    private AwayKetBordUtils awayKetBordUtils;
    private AlertDialog showDialog;
    private List<DistributionListBean.DataBean> mList = new ArrayList<>();
    private DistributorlistAdapter distributorlistAdapter;
    private String distributorid = "";
    private String mername = "";
    private String entregno = "";
    private int cid = 0;
    private int pagenum = 1;
    private boolean isPause;
    private String merchants_distributor_type = "";
    private String merchants_type = "";
    private String type;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        merchants_distributor_type = parms.getString(InvoicingConstants.Merchants_Distributor_type);
        type = parms.getString(InvoicingConstants.TYPE);
        cid = PreferencesUtils.getInt(DistributorLIstActivity.this, InvoicingConstants.QY_ID, 0);
        awayKetBordUtils = new AwayKetBordUtils(this, getWindow());
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        etSerch.setHint("请输入查询的名称");
        View view1 = ShowDalogUtils.showCustomizeDialog(DistributorLIstActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(DistributorLIstActivity.this, false, view1);
        showDialog.dismiss();
        mList.clear();
        if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
            tvTitle.setText("分销商管理");
            merchants_type = parms.getString(InvoicingConstants.Distributor_TYPE);
            requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Distributor_mertype);
            distributorlistAdapter = new DistributorlistAdapter(this, mList, InvoicingConstants.Distributor_TYPE);
            distributionListRecyclerView.setAdapter(distributorlistAdapter);
            distributorlistAdapter.notifyDataSetChanged();
        } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            merchants_type = parms.getString(InvoicingConstants.Merchants_TYPE);
            tvTitle.setText("供货商管理");
            requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Merchants_mertype);
            distributorlistAdapter = new DistributorlistAdapter(this, mList, InvoicingConstants.Merchants_TYPE);
            distributionListRecyclerView.setAdapter(distributorlistAdapter);
            distributorlistAdapter.notifyDataSetChanged();
        }
        if (!merchants_type.equals(InvoicingConstants.Distributor_Select) && !merchants_type.equals(InvoicingConstants.Merchants_Select)) {
            setSwipeMenuListViewCreater();
        }

    }

    /**
     * 设置SwipeMenuListView的SwipeMenuCreator
     * <p>
     * 是否侧滑
     */
    private void setSwipeMenuListViewCreater() {
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
        distributionListRecyclerView.setMenuCreator(creator);
        distributionListRecyclerView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_distributor_list;
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
                mList.clear();
                pagenum = 1;
                if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Distributor_mertype);
                } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Merchants_mertype);
                }
                distributorlistAdapter.notifyDataSetChanged();

            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pagenum++;
                if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Distributor_mertype);
                } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Merchants_mertype);
                }
                distributorlistAdapter.notifyDataSetChanged();

            }
        });
        distributionListRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                DistributionListBean.DataBean dataBean = mList.get(position);
                if (title.equals("编辑")) {
                    //跳转编辑页面
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                        bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                        bundle.putString(InvoicingConstants.Distributor_TYPE, InvoicingConstants.Distributor_EDIT);
                        bundle.putString(InvoicingConstants.Distributor_ID, dataBean.getMerid() + "");
                    } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                        bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Merchants_TYPE);
                        bundle.putString(InvoicingConstants.Merchants_TYPE, InvoicingConstants.Merchants_EDIT);
                        bundle.putString(InvoicingConstants.Merchants_ID, dataBean.getMerid() + "");
                    }

                    intent.putExtras(bundle);
                    intent.setClass(DistributorLIstActivity.this, AddDistributorActivity.class);
                    startActivity(intent);

                } else if (title.equals("删除")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(DistributorLIstActivity.this, R.layout.exit_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(DistributorLIstActivity.this, false, customizeDialog);
                    dialogClick(customizeDialog, alertDialog, dataBean);
                }
                return false;
            }
        });

        distributionListRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查看详情
                DistributionListBean.DataBean dataBean = mList.get(position);
                Intent intentemployee = new Intent();
                Bundle bundle = new Bundle();
                if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                    if (merchants_type.equals(InvoicingConstants.Distributor_Select)) {
                        bundle.putString(InvoicingConstants.Distributor_Name, dataBean.getMername() + "");
                        bundle.putString(InvoicingConstants.Distributor_ID, dataBean.getMerid() + "");
                        if (TextUtils.isEmpty(type)) {
                            intentemployee.setClass(DistributorLIstActivity.this, AddSalesActivity.class);
                        } else {
                            if (type.equals(InvoicingConstants.SALES)) {
                                intentemployee.setClass(DistributorLIstActivity.this, AddReturnsActivity.class);
                            }
                        }
                        intentemployee.putExtras(bundle);
                        setResult(3, intentemployee);
                        finish();
                    } else {
                        bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                        bundle.putString(InvoicingConstants.Distributor_ID, dataBean.getMerid() + "");
                        intentemployee.setClass(DistributorLIstActivity.this, SeeDistributorActivity.class);
                        intentemployee.putExtras(bundle);
                        startActivity(intentemployee);
                    }

                } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                    if (merchants_type.equals(InvoicingConstants.Merchants_Select)) {
                        bundle.putString(InvoicingConstants.Merchants_Name, dataBean.getMername() + "");
                        bundle.putString(InvoicingConstants.Merchants_ID, dataBean.getMerid() + "");
                        if (TextUtils.isEmpty(type)) {
                            intentemployee.setClass(DistributorLIstActivity.this, AddPurchaseActivity.class);
                        } else {
                            if (type.equals(InvoicingConstants.PURCHASE)) {
                                intentemployee.setClass(DistributorLIstActivity.this, AddReturnsActivity.class);
                            }
                        }
                        intentemployee.putExtras(bundle);
                        setResult(3, intentemployee);
                        finish();
                    } else {
                        bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Merchants_TYPE);
                        bundle.putString(InvoicingConstants.Merchants_ID, dataBean.getMerid() + "");
                        intentemployee.setClass(DistributorLIstActivity.this, SeeDistributorActivity.class);
                        intentemployee.putExtras(bundle);
                        startActivity(intentemployee);
                    }
                }


            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

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
                            etSerch.setText(result);
                            if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                                requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Distributor_mertype);
                            } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                                requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Merchants_mertype);
                            }
                            distributorlistAdapter.notifyDataSetChanged();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(DistributorLIstActivity.this, "解析二维码失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        }
    }

    /**
     * 删除对话框
     *
     * @param customizeDialog
     * @param alertDialog
     * @param dataBean
     */
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog, final DistributionListBean.DataBean dataBean) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvContant = customizeDialog.findViewById(R.id.dialog_tv_contant);
        tvContant.setText("是否删除当前分销商?");
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
                //TODO:点击的是
                alertDialog.dismiss();
                if (dataBean != null) {
                    distributorid = dataBean.getMerid() + "";
                }

                requestNetDelDistributor(distributorid);
            }
        });
    }


    @OnClick({R.id.rl_back, R.id.rl_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search, R.id.img_distrubutor_list_saoma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_select:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                    bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Distributor_TYPE);
                    bundle.putString(InvoicingConstants.Distributor_TYPE, InvoicingConstants.Distributor_ADD);
                } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                    bundle.putString(InvoicingConstants.Merchants_Distributor_type, InvoicingConstants.Merchants_TYPE);
                    bundle.putString(InvoicingConstants.Merchants_TYPE, InvoicingConstants.Merchants_ADD);
                }

                intent.putExtras(bundle);
                intent.setClass(DistributorLIstActivity.this, AddDistributorActivity.class);
                startActivity(intent);
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
                mername = etSerch.getText().toString().trim();
                //TODO:筛选请求
                mList.clear();
                pagenum = 1;
                if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Distributor_mertype);
                } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                    requestNetCommodityList(pagenum + "", mername, entregno, cid + "", InvoicingConstants.Merchants_mertype);
                }

                distributorlistAdapter.notifyDataSetChanged();

                break;
            case R.id.img_distrubutor_list_saoma:
                Intent intent1 = new Intent();
                intent1.setClass(this, SaoMaActivity.class);
                startActivityForResult(intent1, 1);
                break;
        }
    }

    /**
     * @param page
     * @param mername  //进/销商名称
     * @param entregno //营业执照号
     * @param cid      公司ID
     * @param mertype  类型： 1分销商 2进货商
     */
    private void requestNetCommodityList(String page, final String mername, final String entregno, final String cid, final String mertype) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.buyers_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("mername", mername)
                .addParams("entregno", entregno)
                .addParams("cid", cid)
                .addParams("mertype", mertype)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息DistributorLIstActivity" + e.toString());
                            Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息DistributorLIstActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.finishLoadmore();
                            LogUtils.d("返回值信息DistributorLIstActivity" + response.toString());
                            Gson gson = new Gson();
                            DistributionListBean distributionListBean = gson.fromJson(response, DistributionListBean.class);
                            if (distributionListBean != null) {
                                List<DistributionListBean.DataBean> dataList = distributionListBean.getData();
                                if (dataList != null) {
                                    mList.addAll(dataList);
                                    if (mList.size() != 0) {
                                        if (dataList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        distributorlistAdapter.notifyDataSetChanged();
                                        distributionListRecyclerView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataList.size() == 0) {
                                            distributionListRecyclerView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            if (!TextUtils.isEmpty(mername) || !TextUtils.isEmpty(entregno)) {
                                                tvError.setText("无符合条件的数据!");
                                                showToast("无符合条件的数据!");
                                            } else {
                                                showToast("暂无数据,请进行添加!");
                                            }
                                        }
                                    }
                                } else {
                                    distributionListRecyclerView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息DistributorLIstActivity" + e1.toString());
                            Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 删除分销商
     */
    private void requestNetDelDistributor(String merId) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.buyersdelete_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + merId);
        PostFormBuilder post = OkHttpUtils.post();
        post.addParams("merId", merId);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息DistributorLIstActivity" + e.toString());
                            Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息DistributorLIstActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息DistributorLIstActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("删除分销商成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("删除分销商失败!");
                                }
                            } else {
                                Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息DistributorLIstActivity" + e1.toString());
                            Toast.makeText(DistributorLIstActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
}
