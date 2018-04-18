package com.mtecc.mmp.invoicing.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.login.RegistrationBaseInfoActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActivity {

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
    @BindView(R.id.setting_rl_msg)
    RelativeLayout settingRlMsg;
    @BindView(R.id.setting_rl_pwd)
    RelativeLayout settingRlPwd;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.setting_rl_msg, R.id.setting_rl_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.setting_rl_msg:
                //修改基础信息
                Intent intent = new Intent(this, RegistrationBaseInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BASE_INFO_TYPE, InvoicingConstants.amend);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.setting_rl_pwd:
                //修改密码
                Intent pwdintent = new Intent(this, SettingPwdActivity.class);
                startActivity(pwdintent);
                break;
        }
    }
}
