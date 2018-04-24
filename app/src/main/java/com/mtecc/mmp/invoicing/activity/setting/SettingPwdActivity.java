package com.mtecc.mmp.invoicing.activity.setting;

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
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;

import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
        String userId = PreferencesUtils.getString(this, InvoicingConstants.USER_ID);
        String userpwd = PreferencesUtils.getString(this, InvoicingConstants.USER_PWD);
        if (!TextUtils.isEmpty(olePwd) && !TextUtils.isEmpty(newPWD) && !TextUtils.isEmpty(againPWD)) {
            if (!TextUtils.isEmpty(userpwd)) {
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
        requestNetChangePwd(userId, userpwd, newPWD);
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

    /**
     * 修改密码
     *
     * @param userid
     * @param oldpass
     * @param newpass
     */
    private void requestNetChangePwd(String userid, String oldpass, String newpass) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.changePwd_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("userid", userid)
                .addParams("oldpass", oldpass)
                .addParams("newpass", newpass)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息SettingPwdActivity" + e.toString());
                            Toast.makeText(SettingPwdActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SettingPwdActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息SettingPwdActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    PreferencesUtils.putString(SettingPwdActivity.this, InvoicingConstants.USER_PWD, "");
                                    Intent intent = new Intent();
                                    intent.setClass(SettingPwdActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    showToast("修改密码失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(SettingPwdActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息SettingPwdActivity" + e1.toString());
                            Toast.makeText(SettingPwdActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
