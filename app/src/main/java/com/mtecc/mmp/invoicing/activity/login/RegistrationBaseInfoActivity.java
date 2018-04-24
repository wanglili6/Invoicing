package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.activity.login.bean.LoginUserInfo;
import com.mtecc.mmp.invoicing.activity.login.bean.RegistrationInfoBean;
import com.mtecc.mmp.invoicing.activity.login.bean.ResultBean;
import com.mtecc.mmp.invoicing.activity.setting.bean.CompanyInfoBean;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 注册以及修改基础信息
 */
public class RegistrationBaseInfoActivity extends BaseActivity {


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
    @BindView(R.id.resgister_et_pname)
    EditText resgisterEtPname;
    @BindView(R.id.register_et_pnum)
    EditText registerEtPnum;
    @BindView(R.id.register_spinner_psex)
    Spinner registerSpinnerPpSex;
    @BindView(R.id.register_et_pphone)
    EditText registerEtPphone;
    @BindView(R.id.registration_pcode)
    EditText registrationPcode;
    @BindView(R.id.register_et_emily)
    EditText registerEtEmily;
    @BindView(R.id.register_et_paddress)
    EditText registerEtPaddress;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.resgister_et_name)
    EditText resgisterEtName;
    @BindView(R.id.register_et_faren)
    EditText registerEtFaren;
    @BindView(R.id.register_et_code)
    EditText registerEtCode;
    @BindView(R.id.register_et_adress)
    EditText registerEtAdress;
    @BindView(R.id.register_tv_valid_until)
    TextView registerTvValidUntil;
    @BindView(R.id.register_et_range)
    EditText registerEtRange;
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
    @BindView(R.id.register_switch_status)
    Switch registerswStatus;
    @BindView(R.id.ll_info_person_msg)
    LinearLayout llInfoPersonMsg;
    List<String> spinnerNameList = new ArrayList<>();
    List<String> spinnervalueList = new ArrayList<>();
    //个人信息
    String pName = "";//姓名
    String pAge = "";//年龄
    String pSex = "";//性别
    String pPhone = "";//手机号
    String pCode = "";//身份证号
    String pEmily = "";//邮箱
    String pAddress = "";//地址
    //企业信息
    String oName = "";//公司名称
    String oFaren = "";//公司法人
    String oCode = "";//营业执照号
    String oAddress = "";//公司地址
    String oVaileUntil = "";//有效期至
    String oRange = "";//经营范围
    String status = "";//经营范围
    private UseSystemUtils userSystemutils = null;
    private AlertDialog alertDialog;
    private AlertDialog exitAlertDialog;
    private String baseInfoType;
    private String regisName = "";//用户名
    private String regisPWD = "";//密码

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        userSystemutils = new UseSystemUtils(this);
        ivBack.setVisibility(View.VISIBLE);
        spinnerNameList.clear();
        spinnervalueList.clear();
        spinnerNameList.add("请选择");
        spinnerNameList.add("男");
        spinnerNameList.add("女");
        spinnervalueList.add("-1");
        spinnervalueList.add("0");
        spinnervalueList.add("1");
        parms = getIntent().getExtras();
        baseInfoType = parms.getString(InvoicingConstants.BASE_INFO_TYPE);
        if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.regis)) {
            regisName = parms.getString(InvoicingConstants.regisName);
            regisPWD = parms.getString(InvoicingConstants.regisPWD);
            tvTitle.setText("填写注册信息");
            registerTvRegister.setText("注册");
            llInfoPersonMsg.setVisibility(View.VISIBLE);
            registerswStatus.setVisibility(View.GONE);
        } else if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.amend)) {
            tvTitle.setText("修改基础信息");
            registerTvRegister.setText("保存");
            llInfoPersonMsg.setVisibility(View.GONE);
            registerswStatus.setVisibility(View.VISIBLE);
            String name = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_NAME, "");
            String address = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_ADDRESS, "");
            String code = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_CODE, "");
            String endDatetime = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_END_DATA, "");
            String qufaren = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_FAREN, "");
            String jjRange = PreferencesUtils.getString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_JJFW, "");

            if (TextUtils.isEmpty(name)) {
                resgisterEtName.setText("暂无");
            } else {
                resgisterEtName.setText(name);
            }


            if (TextUtils.isEmpty(address)) {
                registerEtAdress.setText("暂无");
            } else {
                registerEtAdress.setText(address);
            }

            if (TextUtils.isEmpty(qufaren)) {
                registerEtFaren.setText("暂无");
            } else {
                registerEtFaren.setText(qufaren);
            }
            if (TextUtils.isEmpty(endDatetime)) {
                registerTvValidUntil.setText("暂无");
            } else {
                registerTvValidUntil.setText(endDatetime);
            }
            if (TextUtils.isEmpty(code)) {
                registerEtCode.setText("暂无");
            } else {
                registerEtCode.setText(code);
            }
            if (TextUtils.isEmpty(jjRange)) {
                registerEtRange.setText("暂无");
            } else {
                registerEtRange.setText(jjRange);
            }


        }

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_registration_base_info;
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
        ArrayAdapter mAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, spinnerNameList);
        registerSpinnerPpSex.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        registerSpinnerPpSex.setSelection(0);
        setSpinner();
        registerswStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_STATUS, "0");
                    status = "0";
                } else {
                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_STATUS, "");
                    status = "";
                }
            }
        });
    }

    /**
     * 设置选择性别
     */
    private void setSpinner() {
        registerSpinnerPpSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.d(spinnerNameList.get(position) + "-----" + spinnervalueList.get(position));
                pSex = spinnervalueList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.rl_back, R.id.register_tv_valid_until, R.id.register_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_tv_valid_until:
                //选择企业有效期
                if (userSystemutils != null) {
                    userSystemutils.useSystemTimeNoHms(registerTvValidUntil);
                }
                break;
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
        if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.regis)) {
            pName = resgisterEtPname.getText().toString().trim();
            pAge = registerEtPnum.getText().toString().trim();
            pPhone = registerEtPphone.getText().toString().trim();
            pCode = registrationPcode.getText().toString().trim();
            pEmily = registerEtEmily.getText().toString().trim();
            pAddress = registerEtPaddress.getText().toString().trim();

            if (TextUtils.isEmpty(pName) || pName.equals("")) {
                showToast("用户的真实姓名不能为空!");
                alertDialog.dismiss();
                return;
            }
            if (TextUtils.isEmpty(pAge) || pAge.equals("")) {
                showToast("用户的年龄不能为空!");
                alertDialog.dismiss();
                return;
            }
            if (TextUtils.isEmpty(pSex) || pSex.equals("-1")) {
                showToast("用户的性别不能为空!");
                alertDialog.dismiss();
                return;
            }
            if (TextUtils.isEmpty(pPhone) || pPhone.equals("")) {
                showToast("用户的手机号不能为空!");
                alertDialog.dismiss();
                return;
            }
            if (TextUtils.isEmpty(pCode) || pCode.equals("")) {
                showToast("用户的身份证号不能为空!");
                alertDialog.dismiss();
                return;
            }
            companyMsg();
            registSetMsg();
        } else if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.amend)) {
            oName = resgisterEtName.getText().toString().trim();
            oFaren = registerEtFaren.getText().toString().trim();
            oCode = registerEtCode.getText().toString().trim();
            oAddress = registerEtAdress.getText().toString().trim();
            oVaileUntil = registerTvValidUntil.getText().toString().trim();
            oRange = registerEtRange.getText().toString().trim();
            int qyid = PreferencesUtils.getInt(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_ID, 0);
            if (qyid == 0) {
                showToast("修改信息失败!");
                alertDialog.dismiss();
                return;
            }
            upDataMsg(qyid);
        }


    }

    private void registSetMsg() {
        //TODO:提交逻辑
        RegistrationInfoBean registrationInfoBean = new RegistrationInfoBean();
        //个人信息
        registrationInfoBean.setLogname(regisName);
        registrationInfoBean.setPassword(regisPWD);
        registrationInfoBean.setUsername(pName);
        registrationInfoBean.setAddress(pAddress);
        registrationInfoBean.setUserage(pAge);
        registrationInfoBean.setSex(pSex);
        registrationInfoBean.setTelphone(pPhone);
        registrationInfoBean.setCardnum(pCode);
        registrationInfoBean.setEmail(pEmily);
        //企业信息
        registrationInfoBean.setCname(oName);
        registrationInfoBean.setQyfr(oFaren);
        registrationInfoBean.setClicence(oCode);
        registrationInfoBean.setC_address(oAddress);
        registrationInfoBean.setEnddateStr(oVaileUntil);
        registrationInfoBean.setJjfw(oRange);

        Gson gson = new Gson();
        String infoJson = gson.toJson(registrationInfoBean);
        LogUtils.d("注册信息" + infoJson);
        requestNetRegisterInfo(infoJson);
    }

    private void upDataMsg(int qyid) {
        //TODO:修改提交
        CompanyInfoBean companyInfoBean = new CompanyInfoBean();
        //企业信息
        companyInfoBean.setCname(oName);
        companyInfoBean.setQyfr(oFaren);
        companyInfoBean.setClicence(oCode);
        companyInfoBean.setC_address(oAddress);
        companyInfoBean.setEnddateStr(oVaileUntil);
        companyInfoBean.setJjfw(oRange);
        companyInfoBean.setCid(qyid);
        companyInfoBean.setState(status);

        Gson gson = new Gson();
        String infoJson = gson.toJson(companyInfoBean);
        LogUtils.d("修改信息" + infoJson);
        requestNetUpDataInfo(infoJson);
    }

    /**
     * 企业信息
     */
    private void companyMsg() {
        //企业信息
        oName = resgisterEtName.getText().toString().trim();
        oFaren = registerEtFaren.getText().toString().trim();
        oCode = registerEtCode.getText().toString().trim();
        oAddress = registerEtAdress.getText().toString().trim();
        oVaileUntil = registerTvValidUntil.getText().toString().trim();
        oRange = registerEtRange.getText().toString().trim();

        if (TextUtils.isEmpty(oName) || oName.equals("")) {
            showToast("企业名称不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(oFaren) || oFaren.equals("")) {
            showToast("企业法人不能为空!");
            alertDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(oCode) || oCode.equals("")) {
            showToast("营业执照号不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(oAddress) || oAddress.equals("")) {
            showToast("企业地址不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(oVaileUntil) || oVaileUntil.equals("")) {
            showToast("有效期不能为空!");
            alertDialog.dismiss();
            return;
        }
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
                        try {
                            alertDialog.dismiss();
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e.toString());
                            Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.USER_LOGNAME, regisName);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.USER_PWD, "");
                                    Intent intent = new Intent();
                                    intent.setClass(RegistrationBaseInfoActivity.this, RegistrationBaseInfoActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    showToast("注册失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (
                                Exception e1)

                        {
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e1.toString());
                            Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 修改企业信息
     *
     * @param infoJson
     */
    private void requestNetUpDataInfo(String infoJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.update_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("CompanyInfoBean", infoJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            alertDialog.dismiss();
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e.toString());
                            Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    oName = resgisterEtName.getText().toString().trim();
                                    oFaren = registerEtFaren.getText().toString().trim();
                                    oCode = registerEtCode.getText().toString().trim();
                                    oAddress = registerEtAdress.getText().toString().trim();
                                    oVaileUntil = registerTvValidUntil.getText().toString().trim();
                                    oRange = registerEtRange.getText().toString().trim();
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_ADDRESS, oAddress);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_CODE, oCode);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_END_DATA, oVaileUntil);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_FAREN, oFaren);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_JJFW, oRange);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_NAME, oName);
                                    PreferencesUtils.putString(RegistrationBaseInfoActivity.this, InvoicingConstants.QY_STATUS, status);
                                    finish();
                                } else {
                                    showToast("修改失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息RegistrationBaseInfoActivity" + e1.toString());
                            Toast.makeText(RegistrationBaseInfoActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
