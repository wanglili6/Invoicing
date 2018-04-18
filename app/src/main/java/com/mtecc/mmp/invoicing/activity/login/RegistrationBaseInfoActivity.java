package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    Spinner registerSpinnerPsex;
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
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
    List<String> spinnerNameList = new ArrayList<>();
    List<String> spinnervalueList = new ArrayList<>();

    String sex = "";//性别
    private UseSystemUtils userSystemutils = null;

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
        String baseInfoType = parms.getString(InvoicingConstants.BASE_INFO_TYPE);
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
//        RegistrationSexAdapter registrationSexAdapter = new RegistrationSexAdapter(this, spinnerNameList);
        ArrayAdapter mAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, spinnerNameList);
        registerSpinnerPsex.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        registerSpinnerPsex.setSelection(0);
        setSpinner();
    }

    /**
     * 设置选择性别
     */
    private void setSpinner() {
        registerSpinnerPsex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                registerSpinnerPsex.setSelection(position);
                LogUtils.d(spinnerNameList.get(position) + "-----" + spinnervalueList.get(position));
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
                finish();
                break;
            case R.id.register_tv_register:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
