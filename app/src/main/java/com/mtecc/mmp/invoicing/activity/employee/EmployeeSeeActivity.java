package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeSeeActivity extends BaseActivity {

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
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.employee_see_name)
    TextView employeeSeeName;
    @BindView(R.id.employee_see_login_name)
    TextView employeeSeeLoginName;
    @BindView(R.id.employee_see_age)
    TextView employeeSeeAge;
    @BindView(R.id.employee_see_sex)
    TextView employeeSeeSex;
    @BindView(R.id.employee_see_phone)
    TextView employeeSeePhone;
    @BindView(R.id.employee_see_code)
    TextView employeeSeeCode;
    @BindView(R.id.employee_see_status)
    TextView employeeSeeStatus;
    @BindView(R.id.employee_see_shop)
    TextView employeeSeeShop;
    @BindView(R.id.employee_see_role)
    TextView employeeSeeRole;
    @BindView(R.id.see_edit)
    TextView seeEdit;
    @BindView(R.id.see_delete)
    TextView seeDelete;

    @Override
    public void widgetClick(View v) {

    }



    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看详情");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_employee_see;
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

    @OnClick({R.id.rl_back, R.id.see_edit, R.id.see_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.see_edit:
                //编辑员工信息
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.EMPLOYEE_TYPE, InvoicingConstants.EMPLOYEE_EDIT);
                intent.setClass(this, EmployeeManagemnetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.see_delete:
                //删除
                View customizeDialog = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, customizeDialog);
                dialogClick(customizeDialog, alertDialog);
                break;
        }
    }

    /**
     * 删除对话框
     *
     * @param customizeDialog
     * @param alertDialog
     */
    private void dialogClick(View customizeDialog, final AlertDialog alertDialog) {
        TextView tvDiss = customizeDialog.findViewById(R.id.tv_diss);
        TextView tvSure = customizeDialog.findViewById(R.id.tv_sure);
        TextView tvContant = customizeDialog.findViewById(R.id.dialog_tv_contant);
        tvContant.setText("是否删除员工?");
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的否
                alertDialog.dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:点击的是
                alertDialog.dismiss();
            }
        });
    }
}
