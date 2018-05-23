package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.login.adapter.SelectShopAdapter;
import com.mtecc.mmp.invoicing.activity.login.bean.ShopSelectBean;
import com.mtecc.mmp.invoicing.activity.purchase.AddPurchaseActivity;
import com.mtecc.mmp.invoicing.activity.purchase.PurchaseListActivity;
import com.mtecc.mmp.invoicing.activity.sales.AddSalesActivity;
import com.mtecc.mmp.invoicing.activity.sales.SalesListActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.DataTimerUtils;
import com.mtecc.mmp.invoicing.activity.incomeExpend.InComeExpendActivity;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 首页
 */

public class HomeFragment extends Fragment {


    @BindView(R.id.home_img_back_left)
    ImageView homeImgBackLeft;
    @BindView(R.id.home_tv_money)
    TextView homeTvMoney;
    @BindView(R.id.home_rl_income_expends)
    RelativeLayout homeRlIncomeExpends;
    @BindView(R.id.home_img_back_right)
    ImageView homeImgBackRight;
    @BindView(R.id.home_tv_income)
    TextView homeTvIncome;
    @BindView(R.id.home_tv_timer)
    TextView homeTvTimer;
    @BindView(R.id.home_tv_expend)
    TextView homeTvExpend;
    @BindView(R.id.home_pudchase)
    TextView homePudchase;
    @BindView(R.id.tv_purchase_num)
    TextView tvPurchaseNum;
    @BindView(R.id.home_pudchase_note)
    TextView homePudchaseNote;
    @BindView(R.id.tv_pudchase_note_num)
    TextView tvPudchaseNoteNum;
    @BindView(R.id.home_salse_return)
    TextView homeSalseReturn;
    @BindView(R.id.tv_salse_return_num)
    TextView tvSalseReturnNum;
    @BindView(R.id.home_sell)
    TextView homeSell;
    @BindView(R.id.tv_sell_num)
    TextView tvSellNum;
    @BindView(R.id.home_sell_note)
    TextView homeSellNote;
    @BindView(R.id.tv_sell_note_num)
    TextView tvSellNoteNum;
    @BindView(R.id.home_sell_salse_return)
    TextView homeSellSalseReturn;
    @BindView(R.id.tv_sell_salse_return_num)
    TextView tvSellSalseReturnNum;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.img_select_dialog)
    ImageView imgSelectDialog;
    @BindView(R.id.rl_shop_name)
    RelativeLayout rlShopName;
    Unbinder unbinder;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_home_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initData();

        return inflate;
    }


    /**
     * 初始化数据
     */
    private void initData() {
        final boolean isuseradmin = getActivity().getIntent().getBooleanExtra("isuseradmin", false);
        if (isuseradmin) {
            String name = getActivity().getIntent().getStringExtra("name");
            tvShopName.setText(name);
            imgSelectDialog.setVisibility(View.GONE);
        } else {
            String ishavemoreshop = getActivity().getIntent().getStringExtra("ishavemoreshop");
            if (!TextUtils.isEmpty(ishavemoreshop)) {
                if (ishavemoreshop.equals("1")) {
                    imgSelectDialog.setVisibility(View.GONE);
                    String shopname = getActivity().getIntent().getStringExtra("shopname");
                    tvShopName.setText(shopname);
                } else if (ishavemoreshop.equals("2")) {
                    imgSelectDialog.setVisibility(View.VISIBLE);
                    String shopname = getActivity().getIntent().getStringExtra("shopname");
                    tvShopName.setText(shopname);
                    final List<ShopSelectBean.ShoplistBean> shopList = (List<ShopSelectBean.ShoplistBean>) getActivity().getIntent().getSerializableExtra("shopList");
                    rlShopName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View customizeDialog = ShowDalogUtils.showCustomizeDialog(getContext(), R.layout.add_selectshop_dialog);
                            AlertDialog alertDialog = ShowDalogUtils.showDialog(getContext(), false, customizeDialog);
                            SelectEmployeeClick(customizeDialog, alertDialog, shopList, isuseradmin);
                        }
                    });

                }

            }
        }

    }

    /**
     * 选择店铺
     *
     * @param customizeDialog
     * @param alertDialog
     * @param shoplist
     * @param isuseradmin
     */
    private void SelectEmployeeClick(View customizeDialog, final AlertDialog alertDialog, final List<ShopSelectBean.ShoplistBean> shoplist, final boolean isuseradmin) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        imgxDialog.setVisibility(View.VISIBLE);
        tvselct.setText("切换店铺");
        SelectShopAdapter selectShopList = new SelectShopAdapter(getContext(), shoplist, alertDialog, isuseradmin);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopSelectBean.ShoplistBean shoplistBean = shoplist.get(position);
                PreferencesUtils.putString(getContext(), InvoicingConstants.SHOP_ID, shoplistBean.getShopid() + "");
                tvShopName.setText(shoplistBean.getShopname());
                alertDialog.dismiss();
            }
        });
        imgxDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            LogUtils.i(isVisibleToUser + "====刷新页面");
        } else {
            LogUtils.i(isVisibleToUser + "====不显示页面");
        }
    }

    @OnClick({R.id.home_img_back_left, R.id.home_rl_income_expends, R.id.home_img_back_right, R.id.home_pudchase, R.id.home_pudchase_note, R.id.home_salse_return, R.id.home_sell, R.id.home_sell_note, R.id.home_sell_salse_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_img_back_left:
                //左边箭头
                String timer = homeTvTimer.getText().toString().trim();
                if (!TextUtils.isEmpty(timer)) {
                    if (timer.equals("本日")) {
                        int yearData = DataTimerUtils.getYearData();
                        LogUtils.d("本年:--" + yearData);
                        homeTvTimer.setText("本年");
                    } else if (timer.equals("本年")) {
                        int monthData = DataTimerUtils.getMonthData();
                        LogUtils.d("本月:--" + monthData);
                        homeTvTimer.setText("本月");
                    } else if (timer.equals("本月")) {
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //获取本周开始第一天
                        Date timesWeekmorning = DataTimerUtils.getTimesWeekmorning();
                        String weekmorning = format.format(timesWeekmorning);
                        //获取本周结束的最后一天
                        Date timesWeeknight = DataTimerUtils.getTimesWeeknight();
                        String weekNight = format.format(timesWeeknight);
                        LogUtils.d("本周开始的日期:--" + weekmorning + "-----本周结束的日期" + weekNight);
                        homeTvTimer.setText("本周");
                    } else if (timer.equals("本周")) {
                        String todayhData = DataTimerUtils.getTodayhData();
                        LogUtils.d("today:--" + todayhData);
                        homeTvTimer.setText("本日");
                    }
                } else {
                    Toast.makeText(getContext(), "统计时间错误!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_rl_income_expends:
                //收支结余
                startActivity(new Intent(getActivity(), InComeExpendActivity.class));
                break;
            case R.id.home_img_back_right:
                //右边箭头
                String tvtimer = homeTvTimer.getText().toString().trim();
                if (!TextUtils.isEmpty(tvtimer)) {
                    if (tvtimer.equals("本日")) {
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //获取本周开始第一天
                        Date timesWeekmorning = DataTimerUtils.getTimesWeekmorning();
                        String weekmorning = format.format(timesWeekmorning);
                        ////获取本周结束的最后一天
                        Date timesWeeknight = DataTimerUtils.getTimesWeeknight();
                        String weekNight = format.format(timesWeeknight);
                        LogUtils.d("本周开始的日期:--" + weekmorning + "-----本周结束的日期" + weekNight);
                        homeTvTimer.setText("本周");
                    } else if (tvtimer.equals("本周")) {
                        int monthData = DataTimerUtils.getMonthData();
                        LogUtils.d("本月:--" + monthData);
                        homeTvTimer.setText("本月");
                    } else if (tvtimer.equals("本月")) {
                        int yearData = DataTimerUtils.getYearData();
                        LogUtils.d("本年:--" + yearData);
                        homeTvTimer.setText("本年");
                    } else if (tvtimer.equals("本年")) {
                        String todayhData = DataTimerUtils.getTodayhData();
                        LogUtils.d("today:--" + todayhData);
                        homeTvTimer.setText("本日");
                    }
                } else {
                    Toast.makeText(getContext(), "统计时间错误!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_pudchase:
                //采购单
                Intent intent = new Intent(getContext(), AddPurchaseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", "income");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.home_pudchase_note:
                //采购订单
                startActivity(new Intent(getContext(), PurchaseListActivity.class));
                break;
            case R.id.home_salse_return:
                //采购退货单
                Intent tuiintent = new Intent(getContext(), AddPurchaseActivity.class);
                Bundle tuibundle = new Bundle();
                tuibundle.putString("type", "out");
                tuiintent.putExtras(tuibundle);
                startActivity(tuiintent);
                break;
            case R.id.home_sell:
                //销售单
                Intent salesintent = new Intent(getContext(), AddSalesActivity.class);
                Bundle salesbundle = new Bundle();
                salesbundle.putString("type", "income");
                salesintent.putExtras(salesbundle);
                startActivity(salesintent);
                break;
            case R.id.home_sell_note:
                //销售订单
                startActivity(new Intent(getContext(), SalesListActivity.class));
                break;
            case R.id.home_sell_salse_return:
                //销售退货单
                Intent tuisalesintent = new Intent(getContext(), AddSalesActivity.class);
                Bundle tuisalesbundle = new Bundle();
                tuisalesbundle.putString("type", "out");
                tuisalesintent.putExtras(tuisalesbundle);
                startActivity(tuisalesintent);
                break;
        }
    }
}
