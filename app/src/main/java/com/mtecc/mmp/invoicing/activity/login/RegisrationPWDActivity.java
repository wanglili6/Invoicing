package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    boolean resultName = false;

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
        registrationUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String name = registrationUserName.getText().toString().trim();
                    requestNetVerificationName(name);
                }
            }
        });
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
            }
        } else {
            showToast("用户名或密码不能为空!");
            return;
        }
        if (resultName) {
            showToast("用户名已被注册!");
            return;
        }
        Intent intent = new Intent(this, RegistrationBaseInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(InvoicingConstants.BASE_INFO_TYPE, InvoicingConstants.regis);
        bundle.putString(InvoicingConstants.regisName, userName);
        bundle.putString(InvoicingConstants.regisPWD, userPWD);
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
                finish();

            }
        });
    }

    private void requestNetVerificationName(final String userName) {
        final String url = InvoicingConstants.BASE_URL + InvoicingConstants.ValidateLogName_URL;
        LogUtils.d("验证用户名的url" + url);
        LogUtils.d("验证用户名的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("logname", userName)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息RegisrationPWDActivity" + e.toString());
                            Toast.makeText(RegisrationPWDActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RegisrationPWDActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息RegisrationPWDActivity" + response.toString());
                            //解析json 对象
                            JSONObject jsonObject = new JSONObject(response);
                            boolean result = jsonObject.optBoolean("result");
                            if (result) {
                                resultName = true;
                                registrationUserName.setText("");
                                registrationUserName.setHint(userName + "(用户名已被注册!请重新输入)");
                                registrationUserName.setHintTextColor(getResources().getColor(R.color.text_hint_red));
                            } else {
                                resultName = false;
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RegisrationPWDActivity" + e1.toString());
                        }
                    }
                });
    }
}
