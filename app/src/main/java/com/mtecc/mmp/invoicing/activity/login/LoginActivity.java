package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.activity.login.bean.LoginUserInfo;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_user_name)
    EditText loginUserName;
    @BindView(R.id.login_user_pwd)
    EditText loginUserPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private String url;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        String userName = PreferencesUtils.getString(LoginActivity.this, InvoicingConstants.USER_LOGNAME, "");
        String userPwd = PreferencesUtils.getString(LoginActivity.this, InvoicingConstants.USER_PWD, "");
        if (TextUtils.isEmpty(userName)) {
            loginUserName.setText("");
        } else {
            loginUserName.setText(userName);
        }
        if (TextUtils.isEmpty(userPwd)) {
            loginUserPwd.setText("");
        } else {
            loginUserPwd.setText(userPwd);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
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


    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String userName = loginUserName.getText().toString().trim();
                String userPwd = loginUserPwd.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                    showToast("用户名或密码不能为空!");
                    return;
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                requestNetLogin(userName, userPwd);
                break;
            case R.id.tv_register:
//                startActivity(new Intent(this, RegistrationSMSActivity.class));
                startActivity(new Intent(this, RegisrationPWDActivity.class));
                break;
        }
    }

    private void requestNetLogin(String userName, final String userPwd) {
        url = InvoicingConstants.BASE_URL + InvoicingConstants.LOGIN_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("logname", userName)
                .addParams("password", userPwd)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息LoginActivity" + e.toString());
                            Toast.makeText(LoginActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息LoginActivity" + response.toString());
                            Gson gson = new Gson();
                            LoginUserInfo loginUserInfo = gson.fromJson(response, LoginUserInfo.class);
                            if (loginUserInfo != null) {
                                int result = loginUserInfo.getResult();
                                if (result == 200) {
                                    //请求成功
                                    storageMsg(loginUserInfo, userPwd);


                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else if (result == 500) {
                                    //用户名或密码错误
                                    showToast("用户名或密码错误!请重新输入");
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }
                });
    }

    /**
     * 存储信息
     *
     * @param loginUserInfo
     */
    private void storageMsg(LoginUserInfo loginUserInfo, String pwd) {
        //存储个人信息
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_NAME, loginUserInfo.getUser().getUsername());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ID, loginUserInfo.getUser().getUserid() + "");
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_LOGNAME, loginUserInfo.getUser().getLogname());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_PWD, pwd);
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_TEL_PHONE, loginUserInfo.getUser().getTelphone());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ADDRESS, loginUserInfo.getUser().getAddress());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_CRESTER_TIMER_STR, loginUserInfo.getUser().getCreatdateStr());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_EMAIL, loginUserInfo.getUser().getEmail());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ROLE, loginUserInfo.getUser().getRole());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_AGE, loginUserInfo.getUser().getUserage());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_SEX, loginUserInfo.getUser().getSex());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_CARDNUM, loginUserInfo.getUser().getCardnum());
        //存储企业信息
        LoginUserInfo.UserBean.CidBean cidBean = loginUserInfo.getUser().getCid();
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_ADDRESS, cidBean.getAddress());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_CODE, cidBean.getClicence());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_END_DATA, cidBean.getEnddateStr());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_FAREN, cidBean.getQyfr());
        PreferencesUtils.putInt(LoginActivity.this, InvoicingConstants.QY_ID, cidBean.getCid());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_JJFW, cidBean.getJjfw());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_NAME, cidBean.getCname());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_STATUS, cidBean.getState());
    }


}
