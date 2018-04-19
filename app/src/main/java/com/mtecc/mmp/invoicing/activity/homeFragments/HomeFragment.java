package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.utils.DataTimerUtils;
import com.mtecc.mmp.invoicing.activity.incomeExpend.InComeExpendActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
                        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                break;
            case R.id.home_pudchase_note:
                //采购订单
                break;
            case R.id.home_salse_return:
                //采购退货单
                break;
            case R.id.home_sell:
                //销售单
                break;
            case R.id.home_sell_note:
                //销售订单
                break;
            case R.id.home_sell_salse_return:
                //销售退货单
                break;
        }
    }
}
