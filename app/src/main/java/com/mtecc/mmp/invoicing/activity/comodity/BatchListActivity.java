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
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.BatchAdapter;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

public class BatchListActivity extends BaseActivity {

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
    @BindView(R.id.tv_error)
    TextView tvError;
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
    @BindView(R.id.batch_list_view)
    SwipeMenuListView batchListView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
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
    private List<BatchBean.DataBean> mList = new ArrayList<>();
    private String batchid = "";
    private BatchAdapter batchAdapter;
    private boolean isPause = false;
    private String goodsid = "";
    private int page = 1;
    private String batchNum = "";
    private String batchEndTimer = "";
    private String batchStartTimer = "";
    private UseSystemUtils useSystemUtils;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgLeftSelect.setVisibility(View.VISIBLE);
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        tvTitle.setText("批次信息");
        parms = getIntent().getExtras();
        useSystemUtils = new UseSystemUtils(BatchListActivity.this);
        goodsid = parms.getString(InvoicingConstants.COMMODITY_Id);

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
        batchListView.setMenuCreator(creator);
        batchListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        batchAdapter = new BatchAdapter(this, mList);
        batchListView.setAdapter(batchAdapter);
        batchAdapter.notifyDataSetChanged();

        requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer);

        batchListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String title = menu.getMenuItem(index).getTitle();
                if (title.equals("编辑")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_Edit);
                    bundle.putString("pbatchid", mList.get(position).getPbatchid() + "");
                    intent.setClass(BatchListActivity.this, AddBatchActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else if (title.equals("删除")) {
                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(BatchListActivity.this, R.layout.exit_dialog);
                    AlertDialog alertDialog = ShowDalogUtils.showDialog(BatchListActivity.this, false, customizeDialog);
                    dialogClick(customizeDialog, alertDialog, mList.get(position));
                }
                return false;
            }
        });

        batchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查看详情
                Intent intent = new Intent();
                intent.putExtra("pbatchid", mList.get(position).getPbatchid() + "");
                intent.setClass(BatchListActivity.this, SeeBatchMsgActivity.class);
                startActivity(intent);
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
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog, final BatchBean.DataBean dataBean) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvContant = customizeDialog.findViewById(R.id.dialog_tv_contant);
        tvContant.setText("是否删除当前批次?");
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });
        if (dataBean != null) {
            batchid = dataBean.getPbatchid() + "";
        }
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的是
                alertDialog.dismiss();
                requestNetDelCommodity(batchid);
            }
        });
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_batch_list;
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
                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer);
                batchAdapter.notifyDataSetChanged();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer);
                batchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.rl_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_select:
                //添加批次
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_ADD);
                bundle.putString(InvoicingConstants.COMMODITY_Id, goodsid);
                intent.setClass(this, AddBatchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img_left_select:
                //筛选
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(this, R.layout.batch_shaixuan_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, customizeDialogView);
                shaixuanClick(customizeDialogView, dialog);
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
                batchNum = etSerch.getText().toString().trim();
                //TODO:筛选请求
                mList.clear();
                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer);
                batchAdapter.notifyDataSetChanged();

                break;
        }
    }

    /**
     * 对话框的初始化以及点击事件
     *
     * @param customizeDialogView
     * @param dialog
     */
    private void shaixuanClick(View customizeDialogView, final AlertDialog dialog) {
        TextView tvSure = customizeDialogView.findViewById(R.id.tv_sure);
        TextView tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        final EditText batch_dialog_et_num = customizeDialogView.findViewById(R.id.batch_dialog_et_num);
        final EditText batch_dialog_et_end = customizeDialogView.findViewById(R.id.batch_dialog_et_end);
        final EditText batch_dialog_et_start = customizeDialogView.findViewById(R.id.batch_dialog_et_start);
        batchNum = etSerch.getText().toString().trim();
        batch_dialog_et_num.setText(batchNum);
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
                batchNum = batch_dialog_et_num.getText().toString().trim();
                batchEndTimer = batch_dialog_et_end.getText().toString().trim();
                batchStartTimer = batch_dialog_et_start.getText().toString().trim();
                if (!TextUtils.isEmpty(batchNum)) {
                    rlSerchBar.setVisibility(View.VISIBLE);
                    ivBarSearch.setVisibility(View.GONE);
                    etSerch.setText(batchNum);
                } else {
                    rlSerchBar.setVisibility(View.GONE);
                    ivBarSearch.setVisibility(View.VISIBLE);
                    etSerch.setText("");
                }
                //TODO:筛选请求
                mList.clear();
                page = 1;
                requestNetBatchList(page + "", goodsid, batchNum, batchEndTimer, batchStartTimer);
                batchAdapter.notifyDataSetChanged();


            }
        });
        batch_dialog_et_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                useSystemUtils.useSystemTimeNoHms(batch_dialog_et_end);
            }
        });
        batch_dialog_et_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useSystemUtils.useSystemTimeNoHms(batch_dialog_et_start);
            }
        });
    }

    /**
     * 获取批次信息
     *
     * @param page
     * @param goodsid
     * @param batchnumStr
     * @param endDate
     * @param startDate
     */
    private void requestNetBatchList(String page, String goodsid, final String batchnumStr,
                                     final String endDate, final String startDate) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.batchList_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("page", page)
                .addParams("goodsid", goodsid)
                .addParams("batchnumStr", batchnumStr)
                .addParams("endDate", endDate)
                .addParams("startDate", startDate)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息BatchListActivity" + e.toString());
                            Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            smartRefreshLayout.finishLoadmore();
                            smartRefreshLayout.finishRefresh();
                            LogUtils.d("返回值信息BatchListActivity" + response.toString());
                            Gson gson = new Gson();
                            BatchBean batchBean = gson.fromJson(response, BatchBean.class);
                            if (batchBean != null) {
                                List<BatchBean.DataBean> dataBeanList = batchBean.getData();
                                if (dataBeanList != null) {
                                    mList.addAll(dataBeanList);
                                    if (mList.size() != 0) {
                                        if (dataBeanList.size() == 0) {
                                            showToast("没有更多数据!");
                                        }
                                        batchAdapter.notifyDataSetChanged();
                                        batchListView.setVisibility(View.VISIBLE);
                                        tvError.setVisibility(View.GONE);
                                    } else {
                                        if (dataBeanList.size() == 0) {
                                            batchListView.setVisibility(View.GONE);
                                            tvError.setVisibility(View.VISIBLE);
                                            if (!TextUtils.isEmpty(batchnumStr) || !TextUtils.isEmpty(endDate) || !TextUtils.isEmpty(startDate)) {
                                                tvError.setText("无符合条件的数据!");
                                                showToast("无符合条件的数据!");
                                            } else {
                                                showToast("暂无批次,请进行添加!");
                                            }
                                        }
                                    }
                                } else {
                                    batchListView.setVisibility(View.GONE);
                                    tvError.setVisibility(View.VISIBLE);
                                    showToast("暂无数据!");
                                }

                            } else {
                                Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                            Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 删除批次
     */
    private void requestNetDelCommodity(String pbatchid) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.deleteBatch_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + pbatchid);
        PostFormBuilder post = OkHttpUtils.post();
        post.addParams("pbatchid", pbatchid);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息BatchListActivity" + e.toString());
                            Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息BatchListActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("删除批次成功!");
                                    smartRefreshLayout.autoRefresh();
                                } else {
                                    showToast("删除批次失败!");
                                }
                            } else {
                                Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息BatchListActivity" + e1.toString());
                            Toast.makeText(BatchListActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
