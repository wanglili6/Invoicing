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
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    @BindView(R.id.employee_see_login_pwd)
    TextView employeeSeeLoginPwd;
    @BindView(R.id.employee_see_address)
    TextView employeeSeeAddress;
    @BindView(R.id.employee_see_role)
    TextView employeeSeeRole;
    private String type;
    private String shopId;
    private EmployeeListBean.DataBean selectDataBean;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看详情");
        parms = getIntent().getExtras();
        type = parms.getString(InvoicingConstants.Employee_List_TYPE);
        shopId = parms.getString(InvoicingConstants.shopId);
        selectDataBean = (EmployeeListBean.DataBean) parms.getSerializable(InvoicingConstants.selectuserid);
        employeeSeeName.setText(selectDataBean.getUsername() + "");
        employeeSeeLoginName.setText(selectDataBean.getLogname() + "");
        employeeSeeLoginPwd.setText(selectDataBean.getPassword() + "");
        employeeSeeAge.setText(selectDataBean.getUserage() + "");
        String sex = selectDataBean.getSex();
        if (sex.equals("0")) {
            employeeSeeSex.setText("男");
        } else if (sex.equals("1")) {
            employeeSeeSex.setText("女");
        }

        employeeSeePhone.setText(selectDataBean.getTelphone() + "");
        employeeSeeCode.setText(selectDataBean.getCardnum() + "");
        String empstate = selectDataBean.getEmpstate();
        if (empstate.equals("0")) {
            employeeSeeStatus.setText("正常");
        } else {
            employeeSeeStatus.setText("注销");
        }
        employeeSeeShop.setText(selectDataBean.getShopname() + "");
        employeeSeeRole.setText(selectDataBean.getRole() + "");
        employeeSeeAddress.setText(selectDataBean.getAddress() + "");
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

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
