package com.mtecc.mmp.invoicing.activity.baseinfoMsg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息
 */
public class PersonMsgActivity extends BaseActivity {

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
    @BindView(R.id.person_msg_name)
    TextView personMsgName;
    @BindView(R.id.person_msg_age)
    TextView personMsgAge;
    @BindView(R.id.person_msg_sex)
    TextView personMsgSex;
    @BindView(R.id.person_msg_phone)
    TextView personMsgPhone;
    @BindView(R.id.person_msg_code)
    TextView personMsgCode;
    @BindView(R.id.person_msg_role)
    TextView personMsgRole;
    @BindView(R.id.person_msg_emily)
    TextView personMsgEmily;
    @BindView(R.id.person_msg_address)
    TextView personMsgAddress;
    @BindView(R.id.person_msg_rl_more)
    RelativeLayout personMsgRlMore;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("个人信息");
        initPersonMsg();


    }

    /**
     * 初始化用户信息
     */
    private void initPersonMsg() {
        String name = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_NAME, "");
        String phone = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_TEL_PHONE, "");
        String address = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_ADDRESS, "");
        String cardNum = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_CARDNUM, "");
        String email = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_EMAIL, "");
        String role = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_ROLE, "");
        String age = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_AGE, "");
        String sex = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_SEX, "");
        if (TextUtils.isEmpty(name)) {
            personMsgName.setText("暂无");
        } else {
            personMsgName.setText(name);
        }

        if (TextUtils.isEmpty(phone)) {
            personMsgPhone.setText("暂无");
        } else {
            personMsgPhone.setText(phone);
        }

        if (TextUtils.isEmpty(address)) {
            personMsgAddress.setText("暂无");
        } else {
            personMsgAddress.setText(address);
        }
        if (TextUtils.isEmpty(email)) {
            personMsgEmily.setText("暂无");
        } else {
            personMsgEmily.setText(email);
        }
        if (TextUtils.isEmpty(role)) {
            personMsgRole.setText("暂无");
        } else {
            personMsgRole.setText(role);
        }
        if (TextUtils.isEmpty(age)) {
            personMsgAge.setText("暂无");
        } else {
            personMsgAge.setText(age);
        }
        if (TextUtils.isEmpty(cardNum)) {
            personMsgCode.setText("暂无");
        } else {
            personMsgCode.setText(cardNum);
        }
        if (TextUtils.isEmpty(sex)) {

            personMsgSex.setText("暂无");
        } else {
            if (sex.equals("1")) {
                personMsgSex.setText("女");
            } else if (sex.equals("0")) {
                personMsgSex.setText("男");
            }
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_person_msg;
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

    @OnClick({R.id.rl_back, R.id.person_msg_rl_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.person_msg_rl_more:
                Intent intent = new Intent();
                intent.setClass(this, CompanyMsgActivity.class);
                startActivity(intent);
                break;
        }
    }
}
