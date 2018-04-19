package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.register_et_status)
    EditText registerEtStatus;
    @BindView(R.id.register_et_range)
    EditText registerEtRange;
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
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
    String oStatus = "";//经营状态
    String oVaileUntil = "";//有效期至
    String oRange = "";//经营范围
    private UseSystemUtils userSystemutils = null;
    private AlertDialog alertDialog;
    private AlertDialog exitAlertDialog;
    private String baseInfoType;

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
        spinnerNameList.add("其他");
        spinnervalueList.add("0");
        spinnervalueList.add("1");
        spinnervalueList.add("2");
        spinnervalueList.add("3");
        parms = getIntent().getExtras();
        baseInfoType = parms.getString(InvoicingConstants.BASE_INFO_TYPE);
        if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.regis)) {
            tvTitle.setText("填写注册信息");
            registerTvRegister.setText("注册");
        } else if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.amend)) {
            tvTitle.setText("修改基础信息");
            registerTvRegister.setText("保存");
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
                if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.regis)) {
                    Intent intent = new Intent(RegistrationBaseInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.amend)) {
                    finish();
                }


            }
        });
    }

    /**
     * 判断值是否为空---必填
     */
    private void judgmentValueIsEmpty() {
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
        if (TextUtils.isEmpty(pSex) || pSex.equals("0")) {
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
        //企业信息
        oName = resgisterEtName.getText().toString().trim();
        oFaren = registerEtFaren.getText().toString().trim();
        oCode = registerEtCode.getText().toString().trim();
        oAddress = registerEtAdress.getText().toString().trim();
        oStatus = registerEtStatus.getText().toString().trim();
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
//TODO:提交逻辑
        if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.regis)) {
            Intent intent = new Intent(RegistrationBaseInfoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (!TextUtils.isEmpty(baseInfoType) && baseInfoType.equals(InvoicingConstants.amend)) {
            finish();
        }

    }


}
