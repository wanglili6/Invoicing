package com.mtecc.mmp.invoicing.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.login.RegisrationPWDActivity;
import com.mtecc.mmp.invoicing.activity.login.RegistrationBaseInfoActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class SettingPwdActivity extends BaseActivity {

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
    @BindView(R.id.setting_pwd_old)
    EditText settingPwdOld;
    @BindView(R.id.setting_pwd_new)
    EditText settingPwdNew;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.setting_pwd_again)
    EditText settingPwdAgain;
    @BindView(R.id.setting_pwd_amend)
    TextView settingPwdAmend;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("修改密码");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting_pwd;
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

    @OnClick({R.id.rl_back, R.id.setting_pwd_amend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, alertDialog);
                break;
            case R.id.setting_pwd_amend:
                //TODO:修改密码
                amendPwd();
                break;
        }
    }

    /**
     * 注册下一步
     */
    private void amendPwd() {
        String olePwd = settingPwdOld.getText().toString().trim();
        String newPWD = settingPwdNew.getText().toString().trim();
        String againPWD = settingPwdAgain.getText().toString().trim();
        if (!TextUtils.isEmpty(olePwd) && !TextUtils.isEmpty(newPWD) && !TextUtils.isEmpty(againPWD)) {
            String userpwd = PreferencesUtils.getString(this, InvoicingConstants.USER_PWD);
            if (!TextUtils.isEmpty(userpwd)){
                if (!olePwd.equals(userpwd)) {
                    showToast("原密码输入错误!");
                    return;
                }
            }
            if (!newPWD.equals(againPWD)) {
                showToast("两次密码不一致!");
                return;
            }
        } else {
            showToast("用户名或密码不能为空!");
            return;
        }
        Intent intent = new Intent(SettingPwdActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 是否返回
     *
     * @param exitView
     * @param dialog
     */
    private void exitClick(View exitView, final AlertDialog dialog) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否离开当前页面?");
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

                finish();

            }
        });
    }
}
