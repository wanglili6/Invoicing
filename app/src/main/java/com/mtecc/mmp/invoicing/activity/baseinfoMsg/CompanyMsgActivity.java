package com.mtecc.mmp.invoicing.activity.baseinfoMsg;

import android.content.Context;
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
 * 公司信息
 */
public class CompanyMsgActivity extends BaseActivity {

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
    @BindView(R.id.company_msg_name)
    TextView companyMsgName;
    @BindView(R.id.company_msg_code)
    TextView companyMsgCode;
    @BindView(R.id.company_msg_faren)
    TextView companyMsgFaren;
    @BindView(R.id.company_msg_vaild_until)
    TextView companyMsgVaildUntil;
    @BindView(R.id.company_msg_status)
    TextView companyMsgStatus;
    @BindView(R.id.company_msg_address)
    TextView companyMsgAddress;
    @BindView(R.id.company_msg_range)
    TextView companyMsgRange;
    @BindView(R.id.person_msg_role)
    TextView personMsgRole;
    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("基础信息");
        initcompanyMsg();
    }

    /**
     * 初始化公司信息
     */
    private void initcompanyMsg() {
        String name = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_NAME, "");
        String address = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_ADDRESS, "");
        String code = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_CODE, "");
        String endDatetime = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_END_DATA, "");
        String qufaren = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_FAREN, "");
        String jjRange = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_JJFW, "");
        String status = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.QY_STATUS, "");
        String role = PreferencesUtils.getString(CompanyMsgActivity.this, InvoicingConstants.USER_ROLE, "");
        if (TextUtils.isEmpty(name)) {
            companyMsgName.setText("暂无");
        } else {
            companyMsgName.setText(name);
        }

        if (TextUtils.isEmpty(role)) {
            personMsgRole.setText("暂无");
        } else {
            personMsgRole.setText(role);
        }
        if (TextUtils.isEmpty(address)) {
            companyMsgAddress.setText("暂无");
        } else {
            companyMsgAddress.setText(address);
        }

        if (TextUtils.isEmpty(qufaren)) {
            companyMsgFaren.setText("暂无");
        } else {
            companyMsgFaren.setText(qufaren);
        }
        if (TextUtils.isEmpty(endDatetime)) {
            companyMsgVaildUntil.setText("暂无");
        } else {
            companyMsgVaildUntil.setText(endDatetime);
        }
        if (TextUtils.isEmpty(code)) {
            companyMsgCode.setText("暂无");
        } else {
            companyMsgCode.setText(code);
        }
        if (TextUtils.isEmpty(jjRange)) {
            companyMsgRange.setText("暂无");
        } else {
            companyMsgRange.setText(jjRange);
        }

        if (status.equals("0")) {
            companyMsgStatus.setText("正常");
        } else {
            companyMsgStatus.setText("注销");
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_company_msg;
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

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
