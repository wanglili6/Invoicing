package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.resgister_et_name)
    EditText resgisterEtName;//公司名称
    @BindView(R.id.register_et_faren)
    EditText registerEtFaren;//公司法人
    @BindView(R.id.register_et_code)
    EditText registerEtCode;//营业执照号
    @BindView(R.id.register_et_adress)
    EditText registerEtAdress;//公司地址
    @BindView(R.id.register_et_valid_until)
    EditText registerEtValidUntil;//有效期至
    @BindView(R.id.register_et_status)
    EditText registerEtStatus;//公司状态
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;//注册按钮

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("填写注册信息");
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

    }

    @OnClick({R.id.rl_back, R.id.register_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
