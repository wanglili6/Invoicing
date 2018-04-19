package com.mtecc.mmp.invoicing.activity.login;

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
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisrationPWDActivity extends BaseActivity {

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
    @BindView(R.id.registration_user_name)
    EditText registrationUserName;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.registration_pwd)
    EditText registrationPwd;
    @BindView(R.id.registration_agin_pwd)
    EditText registrationAginPwd;
    @BindView(R.id.tv_registration)
    TextView tvRegistration;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置用户名和密码");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_regisration_pwd;
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

    @OnClick({R.id.rl_back, R.id.tv_registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, alertDialog);
                break;
            case R.id.tv_registration:
                registrationNext();
                break;
        }
    }

    /**
     * 注册下一步
     */
    private void registrationNext() {
        String userName = registrationUserName.getText().toString().trim();
        String userPWD = registrationPwd.getText().toString().trim();
        String userAgainPWD = registrationAginPwd.getText().toString().trim();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPWD) && !TextUtils.isEmpty(userAgainPWD)) {
            if (!userPWD.equals(userAgainPWD)) {
                showToast("两次密码不一致!");
                return;
            } else {
                //TODO:跳转页面
            }
        } else {
            showToast("用户名或密码不能为空!");
            return;
        }
        Intent intent = new Intent(this, RegistrationBaseInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(InvoicingConstants.BASE_INFO_TYPE, InvoicingConstants.regis);
        intent.putExtras(bundle);
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
        contactTV.setText("是否离开注册页面?");
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
                Intent intent = new Intent(RegisrationPWDActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
