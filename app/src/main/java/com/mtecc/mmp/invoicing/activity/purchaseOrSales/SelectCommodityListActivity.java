package com.mtecc.mmp.invoicing.activity.purchaseOrSales;

import android.content.Context;
import android.content.Intent;
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
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.SaoMaActivity;
import com.mtecc.mmp.invoicing.activity.comodity.AddCommodityActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter.SelectCommodityListAdapter;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectBatchBean;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 选择商品列表
 */
public class SelectCommodityListActivity extends BaseActivity {


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
    ListView commodityListRecycleView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_seach)
    LinearLayout llSeach;
    @BindView(R.id.tv_select_names)
    TextView tvSelectNames;
    @BindView(R.id.img_shop_car)
    ImageView imgShopCar;

    @BindView(R.id.img_select_num)
    TextView imgSelectNum;
    @BindView(R.id.tv_select_sure)
    TextView tvSelectSure;
    @BindView(R.id.ll_select_sure)
    LinearLayout llSelectSure;
    private SelectCommodityListAdapter commodityListAdapter;
    List<CommodityListBean.DataBean> mList = new ArrayList<>();
    private Map<String, CommodityListBean.DataBean> mSelectCommodityMap = new HashMap<>();
    int pagenum = 1;
    String isuseradmin = "";
    String proCode = "";
    String proName = "";
    String barcode = "";
    String cid = "";
    String shopid = "";
    private boolean isPause;
    private EditText commodityDialogEtBarCode;
    private String type = "show";//采购,销售显示;
    private String pstype;


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        llSelectSure.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgLeftSelect.setVisibility(View.GONE);
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
//        mapMlist();
        Map<String, CommodityListBean.DataBean> mgetCommidotySelectMap = (Map<String, CommodityListBean.DataBean>) parms.getSerializable(InvoicingConstants.select_Commiodty);
        if (mgetCommidotySelectMap != null) {
            mSelectCommodityMap.clear();
            mSelectCommodityMap.putAll(mgetCommidotySelectMap);
        }
        requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
        commodityListRecycleView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        commodityListAdapter = new SelectCommodityListAdapter(this, mList, commodityListRecycleView, type);
        commodityListRecycleView.setAdapter(commodityListAdapter);
        commodityListAdapter.notifyDataSetChanged();
        commodityListRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //选择批次
                Intent intent = new Intent(SelectCommodityListActivity.this, SelectBatchListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.COMMODITY_Id, mList.get(position).getProId() + "");
                bundle.putString(InvoicingConstants.goodsName, mList.get(position).getProName() + "");
                bundle.putString("position", position + "");
                List<SelectBatchBean> selectBatchBeen = mList.get(position).getmSelectMap();
                Map<String, SelectBatchBean> selectMap = new HashMap<String, SelectBatchBean>();
                if (selectBatchBeen != null) {
                    int size = selectBatchBeen.size();
                    for (int i = 0; i < size; i++) {
                        SelectBatchBean batchBean = selectBatchBeen.get(i);
                        selectMap.put(batchBean.getPbatchid() + "", batchBean);
                    }
                }

                bundle.putSerializable(InvoicingConstants.select_Batch, (Serializable) selectMap);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });


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
                mapMlist();
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
                mapMlist();
                requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);

            }
        });


    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void mapMlist() {
        for (int i = 0; i < mList.size(); i++) {
            CommodityListBean.DataBean dataBean = mList.get(i);
            mSelectCommodityMap.put(dataBean.getProId() + "", dataBean);

        }
    }

    @OnClick({R.id.tv_select_sure, R.id.rl_back, R.id.img_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search, R.id.img_commodity_list_saoma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_select_sure:
                Gson gson = new Gson();
                String s = gson.toJson(mSelectCommodityMap);
                LogUtils.i("选择的商品" + s);
                mapMlist();
                Intent batchintent = new Intent(SelectCommodityListActivity.this, AddPurchaseActivity.class);
                Bundle batchbundle = new Bundle();
                batchbundle.putSerializable(InvoicingConstants.select_Commiodty, (Serializable) mSelectCommodityMap);
                batchintent.putExtras(batchbundle);
                setResult(1, batchintent);
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
                mapMlist();
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
                            mapMlist();
                            mList.clear();
                            requestNetCommodityList(pagenum + "", isuseradmin, proCode, proName, barcode, cid, shopid);
                            commodityListAdapter.notifyDataSetChanged();
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(SelectCommodityListActivity.this, "解析二维码失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case 2:
                    Bundle extras = data.getExtras();
                    Map<String, SelectBatchBean> mgetSelectMap = (Map<String, SelectBatchBean>) extras.getSerializable(InvoicingConstants.select_Batch);
                    int selectposition = Integer.parseInt(extras.getString("position"));
                    double selectmoney = extras.getDouble(InvoicingConstants.select_Batch_money);
                    int selectNum = extras.getInt(InvoicingConstants.select_Batch_num);
                    if (mgetSelectMap != null) {
                        List<SelectBatchBean> mSelectMap = new ArrayList<>();
                        Iterator<String> iterator = mgetSelectMap.keySet().iterator();
                        while (iterator.hasNext()) {
                            String next = iterator.next();
                            String num1 = mgetSelectMap.get(next).getNum() + "";
                            if (!num1.equals("0")) {
                                mSelectMap.add(mgetSelectMap.get(next));
                            }
                        }
                        mList.get(selectposition).setmSelectMap(mSelectMap);
                        mList.get(selectposition).setmSelectMoney(selectmoney);
                        mList.get(selectposition).setmSelectNum(selectNum);
                        mapMlist();
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
                mapMlist();
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
                            Toast.makeText(SelectCommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                                    if (mSelectCommodityMap != null) {
                                        if (mSelectCommodityMap.size() == 0) {
                                            int size = mList.size();
                                            for (int i = 0; i < size; i++) {
                                                mList.get(i).setmSelectMap(null);
                                                mList.get(i).setmSelectNum(0);
                                                mList.get(i).setmSelectMoney(0.0);
                                            }
                                        } else {
                                            Iterator<String> iterator = mSelectCommodityMap.keySet().iterator();
                                            while (iterator.hasNext()) {
                                                String selectpbatchid = iterator.next();
                                                for (int j = 0; j < mList.size(); j++) {
                                                    String pbatchid = mList.get(j).getProId() + "";
                                                    if (selectpbatchid.equals(pbatchid)) {
                                                        mList.get(j).setmSelectMap(mSelectCommodityMap.get(selectpbatchid).getmSelectMap());
                                                        mList.get(j).setmSelectNum(mSelectCommodityMap.get(selectpbatchid).getmSelectNum());
                                                        mList.get(j).setmSelectMoney(mSelectCommodityMap.get(selectpbatchid).getmSelectMoney());
                                                    } else {
                                                        continue;

                                                    }
                                                }
                                            }

                                        }
                                    }
                                    setNumMoney();
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
                                Toast.makeText(SelectCommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息CommodityListActivity" + e1.toString());
                            Toast.makeText(SelectCommodityListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
     * 设置个数和钱数
     */
    private void setNumMoney() {
        int totalNum = 0;
        double totalmoney = 0.0;
        for (int i = 0; i < mList.size(); i++) {
            CommodityListBean.DataBean dataBean = mList.get(i);
            String nums = dataBean.getmSelectNum() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                totalNum = totalNum + dataBean.getmSelectNum();
                String enterprice = dataBean.getmSelectMoney() + "";
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(enterprice);
                    BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                    BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                    BigDecimal add = totalmoneyBigDecimal.add(enterBigDecimal);
                    totalmoney = add.doubleValue();
                } else {
                    totalmoney = totalmoney + dataBean.getmSelectNum() * 0.0;
                }
            }
        }
        imgSelectNum.setText(totalNum + "");
        tvSelectNames.setText(totalmoney + "");
        LogUtils.i("个数===" + totalNum + "钱数----" + totalmoney);
    }

}
