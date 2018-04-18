package com.mtecc.mmp.invoicing.activity.baseinfoMsg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                intent.setClass(this,CompanyMsgActivity.class);
                startActivity(intent);
                break;
        }
    }
}
