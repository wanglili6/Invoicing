package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.login.bean.RegistrationInfoBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 注册信息
 */
public class ShortRegistrationInfoActivity extends BaseActivity {

    //个人信息
    String pName = "";//姓名
    String pPhone = "";//手机号
    //企业信息
    String oName = "";//公司名称

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
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.resgister_et_name)
    EditText resgisterEtName;
    @BindView(R.id.resgister_et_pname)
    EditText resgisterEtPname;
    @BindView(R.id.register_et_pphone)
    EditText registerEtPphone;
    @BindView(R.id.ll_info_person_msg)
    LinearLayout llInfoPersonMsg;
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
    @BindView(R.id.registration_user_name)
    EditText registrationUserName;
    @BindView(R.id.registration_pwd)
    EditText registrationPwd;
    @BindView(R.id.registration_agin_pwd)
    EditText registrationAginPwd;
    @BindView(R.id.ll_info_user_pwd)
    LinearLayout llInfoUserPwd;
    private UseSystemUtils userSystemutils = null;
    private AlertDialog alertDialog;
    private AlertDialog exitAlertDialog;
    private String baseInfoType;
    private String regisName = "";//用户名
    private String regisPWD = "";//密码
    private String regisagingPWD = "";//确认密码
    boolean resultName = false;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        userSystemutils = new UseSystemUtils(this);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("填写注册信息");
        registerTvRegister.setText("注册");
        llInfoPersonMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_short_registratio_info;
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


    @OnClick({R.id.rl_back, R.id.register_tv_register, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, alertDialog);

                break;
            case R.id.register_tv_register:
                View view1 = ShowDalogUtils.showCustomizeDialog(this, R.layout.send_customize);
                this.alertDialog = ShowDalogUtils.showDialog(this, false, view1);
                judgmentValueIsEmpty();

                break;
            case R.id.tv_login:
                Intent in = new Intent(this, LoginActivity.class);
                startActivity(in);
                finish();

                break;
        }
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
                //TODO:返回页面
                finish();


            }
        });
    }

    /**
     * 判断值是否为空---必填
     */
    private void judgmentValueIsEmpty() {
        pName = resgisterEtPname.getText().toString().trim();
        pPhone = registerEtPphone.getText().toString().trim();
        regisName = registrationUserName.getText().toString().trim();
        regisPWD = registrationPwd.getText().toString().trim();
        regisagingPWD = registrationAginPwd.getText().toString().trim();
        if (!TextUtils.isEmpty(regisName) && !TextUtils.isEmpty(regisPWD) && !TextUtils.isEmpty(regisagingPWD)) {
            if (!regisPWD.equals(regisagingPWD)) {
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
        if (TextUtils.isEmpty(pName) || pName.equals("")) {
            showToast("用户的姓名不能为空!");
            alertDialog.dismiss();
            return;
        }


        if (TextUtils.isEmpty(pPhone) || pPhone.equals("")) {
            showToast("用户的手机号不能为空!");
            alertDialog.dismiss();
            return;
        }
        //企业信息
        oName = resgisterEtName.getText().toString().trim();
        if (TextUtils.isEmpty(oName) || oName.equals("")) {
            showToast("企业名称不能为空!");
            alertDialog.dismiss();
            return;
        }
        registSetMsg();


    }

    private void registSetMsg() {
        //TODO:提交逻辑
        RegistrationInfoBean registrationInfoBean = new RegistrationInfoBean();
        //个人信息
        registrationInfoBean.setLogname(regisName);
        registrationInfoBean.setUsername(pName);
        registrationInfoBean.setPassword(regisPWD);
        registrationInfoBean.setTelphone(pPhone);
        //企业信息
        registrationInfoBean.setCname(oName);

        Gson gson = new Gson();
        String infoJson = gson.toJson(registrationInfoBean);
        LogUtils.d("注册信息" + infoJson);
        requestNetRegisterInfo(infoJson);
    }

    /**
     * 提交注册信息
     *
     * @param infoJson
     */
    private void requestNetRegisterInfo(String infoJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.register_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("RegistrationInfoBean", infoJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        alertDialog.dismiss();
                        try {
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e.toString());
                            Toast.makeText(ShortRegistrationInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        alertDialog.dismiss();
                        try {
                            LogUtils.d("返回值信息RegistrationBaseInfoActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("reslut");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    PreferencesUtils.putString(ShortRegistrationInfoActivity.this, InvoicingConstants.USER_LOGNAME, regisName);
                                    PreferencesUtils.putString(ShortRegistrationInfoActivity.this, InvoicingConstants.USER_PWD, "");
                                    Intent intent = new Intent();
                                    intent.setClass(ShortRegistrationInfoActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    showToast("注册成功,去登陆!");
                                    finish();
                                } else {
                                    showToast("注册失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(ShortRegistrationInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e1.toString());
                            Toast.makeText(ShortRegistrationInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
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
                            Toast.makeText(ShortRegistrationInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
