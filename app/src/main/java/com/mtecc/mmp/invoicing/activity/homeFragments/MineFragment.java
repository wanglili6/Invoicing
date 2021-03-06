package com.mtecc.mmp.invoicing.activity.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.approve.ApproveActivity;
import com.mtecc.mmp.invoicing.activity.baseinfoMsg.CompanyMsgActivity;
import com.mtecc.mmp.invoicing.activity.baseinfoMsg.PersonMgsBean;
import com.mtecc.mmp.invoicing.activity.baseinfoMsg.PersonMsgActivity;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeListActivity;
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.role.RoleListActivity;
import com.mtecc.mmp.invoicing.activity.setting.SettingActivity;
import com.mtecc.mmp.invoicing.activity.setting.SettingPwdActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopListActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wll on 2017/12/9.
 * 我的
 */

public class MineFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.mine_img_company)
    ImageView mineImgCompany;
    @BindView(R.id.mine_rl_msg)
    RelativeLayout mineRlMsg;
    @BindView(R.id.mine_rl_shop)
    RelativeLayout mineRlShop;
    @BindView(R.id.mine_rl_person)
    RelativeLayout mineRlPerson;
    @BindView(R.id.mine_rl_setting)
    RelativeLayout mineRlSetting;
    @BindView(R.id.mine_tv_exit)
    TextView mineTvExit;
    @BindView(R.id.mine_tv_qy_name)
    TextView mineTvQyName;
    @BindView(R.id.mine_tv_user_name)
    TextView mineTvUserName;
    @BindView(R.id.tv_commoidty_status)
    TextView minetvCommoidtyStatus;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String qy_status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.main_mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        LogUtils.i("这是我的的oncreate");
        tvTitle.setText("我的信息");
        qyStatus();
        initData();

        return inflate;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            LogUtils.i(isVisibleToUser + "====刷新页面");
            qyStatus();
        } else {
            LogUtils.i(isVisibleToUser + "====不显示页面");
        }
    }

    /**
     * 设置企业是否认证
     */
    private void qyStatus() {
        qy_status = PreferencesUtils.getString(getContext(), InvoicingConstants.QY_STATUS, "");
        if (qy_status.equals("0")) {
            minetvCommoidtyStatus.setText("未认证");
            minetvCommoidtyStatus.setTextColor(getResources().getColor(R.color.text_gray_color));
        } else if (qy_status.equals("1")) {
            minetvCommoidtyStatus.setText("待认证");
            minetvCommoidtyStatus.setTextColor(getResources().getColor(R.color.text_money_color));
        } else if (qy_status.equals("2")) {
            minetvCommoidtyStatus.setText("已认证");
            minetvCommoidtyStatus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String qyName = PreferencesUtils.getString(getContext(), InvoicingConstants.QY_NAME, "");
        String userName = PreferencesUtils.getString(getContext(), InvoicingConstants.USER_NAME, "");
        mineTvQyName.setText(qyName);
        mineTvUserName.setText(userName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.mine_rl_role, R.id.mine_rl_msg, R.id.mine_rl_shop, R.id.mine_rl_person, R.id.mine_rl_setting, R.id.mine_tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_rl_msg:
                //查看基础信息
                startActivity(new Intent(getContext(), PersonMsgActivity.class));
                break;
            case R.id.mine_rl_shop:
                //TODO:店铺管理
                Intent intent = new Intent();
                intent.setClass(getContext(), ShopListActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_rl_person:
                //TODO:员工管理
                Intent intentemployee = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.Employee_List_TYPE, InvoicingConstants.companyEmployeeAdd);
                intentemployee.setClass(getContext(), EmployeeListActivity.class);
                intentemployee.putExtras(bundle);
                startActivity(intentemployee);
                break;
            case R.id.mine_rl_role:
                //公司认证
                Intent roleintent = new Intent(getContext(), ApproveActivity.class);
                startActivity(roleintent);
                break;
            case R.id.mine_rl_setting:
                //设置界面
                Intent setintent = new Intent(getContext(), SettingPwdActivity.class);
                startActivity(setintent);
                break;
            case R.id.mine_tv_exit:
                //退出登录
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(getContext(), R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(getContext(), false, customizeDialogView);
                initcustomizeDialogView(customizeDialogView, alertDialog);
                break;
        }
    }

    /**
     * 退出
     *
     * @param customizeDialogView
     * @param alertDialog
     */
    private void initcustomizeDialogView(View customizeDialogView, final AlertDialog alertDialog) {
        View tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        View tvSure = customizeDialogView.findViewById(R.id.tv_sure);

        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                PreferencesUtils.putString(getContext(), InvoicingConstants.USER_PWD, "");
                Intent in = new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        qyStatus();
    }
}
