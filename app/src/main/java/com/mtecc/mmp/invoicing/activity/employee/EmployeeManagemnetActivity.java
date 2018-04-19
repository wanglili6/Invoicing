package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeManagemnetActivity extends BaseActivity {

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
    @BindView(R.id.employee_et_pname)
    EditText employeeEtPname;
    @BindView(R.id.employee_et_pnum)
    EditText employeeEtPnum;
    @BindView(R.id.employee_spinner_psex)
    Spinner employeeSpinnerPsex;
    @BindView(R.id.employee_et_pphone)
    EditText employeeEtPphone;
    @BindView(R.id.employee_pcode)
    EditText employeePcode;
    @BindView(R.id.employee_spinner_status)
    Spinner employeeSpinnerStatus;
    @BindView(R.id.employee_spinner_shop)
    Spinner employeeSpinnerShop;
    @BindView(R.id.employee_spinner_role)
    Spinner employeeSpinnerRole;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.employee_et_name)
    EditText employeeEtName;
    @BindView(R.id.employee_et_pwd)
    EditText employeeEtPwd;
    @BindView(R.id.employee_et_again_pwd)
    EditText employeeEtAgainPWd;
    @BindView(R.id.employee_tv_register)
    TextView employeeTvRegister;
    List<String> statusspinnerNameList = new ArrayList<>();
    List<String> statusspinnervalueList = new ArrayList<>();
    List<String> sexspinnerNameList = new ArrayList<>();
    List<String> sexspinnervalueList = new ArrayList<>();

    private String employeeType = "";
    private String name = "";//姓名
    private String age = "";//年龄
    private String sex = "";//性别
    private String phone = "";//手机号
    private String code = "";//身份证号
    private String status = "";//状态
    private String shop = "";//所属店铺
    private String role = "";//角色
    private String userName = "";//用户名
    private String userPwd = "";//密码
    private String againPwd = "";//确认密码

    private AlertDialog alertDialog;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        ivBack.setVisibility(View.VISIBLE);
        statusspinnerNameList.clear();
        statusspinnervalueList.clear();
        statusspinnerNameList.add("请选择");
        statusspinnerNameList.add("正常");
        statusspinnerNameList.add("注销");
        statusspinnervalueList.add("0");
        statusspinnervalueList.add("1");
        statusspinnervalueList.add("2");
        sexspinnerNameList.clear();
        sexspinnervalueList.clear();
        sexspinnerNameList.add("请选择");
        sexspinnerNameList.add("男");
        sexspinnerNameList.add("女");
        sexspinnerNameList.add("其他");
        sexspinnervalueList.add("0");
        sexspinnervalueList.add("1");
        sexspinnervalueList.add("2");
        sexspinnervalueList.add("3");
        employeeType = parms.getString(InvoicingConstants.EMPLOYEE_TYPE);
        if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_ADD)) {
            tvTitle.setText("添加员工");

        } else if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_EDIT)) {
            tvTitle.setText("编辑员工");
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_employee_managemnet;
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
        ArrayAdapter mstatusAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, statusspinnerNameList);
        employeeSpinnerStatus.setAdapter(mstatusAdapter);
        mstatusAdapter.notifyDataSetChanged();
        employeeSpinnerStatus.setSelection(0);
        ArrayAdapter msexAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, sexspinnerNameList);
        employeeSpinnerPsex.setAdapter(msexAdapter);
        msexAdapter.notifyDataSetChanged();
        employeeSpinnerStatus.setSelection(0);
        employeeSpinnerPsex.setSelection(0);
        setSpinner();
    }

    /**
     * spinner的点击事件
     */
    private void setSpinner() {
        //员工状态
        employeeSpinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = statusspinnervalueList.get(position);
                LogUtils.d(statusspinnerNameList.get(position) + "-----" + statusspinnervalueList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //性别
        employeeSpinnerPsex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = sexspinnervalueList.get(position);
                LogUtils.d(statusspinnerNameList.get(position) + "-----" + statusspinnervalueList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.rl_back, R.id.employee_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialog);
                break;
            case R.id.employee_tv_register:
                View view1 = ShowDalogUtils.showCustomizeDialog(this, R.layout.send_customize);
                alertDialog = ShowDalogUtils.showDialog(this, false, view1);
                judgmentValueIsEmpty();
                break;
        }
    }

    /**
     * 判断值是否为空---必填
     */
    private void judgmentValueIsEmpty() {
        name = employeeEtPname.getText().toString().trim();
        age = employeeEtPnum.getText().toString().trim();
        phone = employeeEtPphone.getText().toString().trim();
        code = employeePcode.getText().toString().trim();
        userName = employeeEtName.getText().toString().trim();
        userPwd = employeeEtPwd.getText().toString().trim();
        againPwd = employeeEtAgainPWd.getText().toString().trim();

        if (TextUtils.isEmpty(name) || name.equals("")) {
            showToast("员工姓名不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(age) || age.equals("")) {
            showToast("年龄不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(sex) || sex.equals("0")) {
            showToast("性别不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(phone) || phone.equals("")) {
            showToast("手机号不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(code) || phone.equals("")) {
            showToast("身份证号不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(status) || status.equals("0")) {
            showToast("员工状态不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(shop) || shop.equals("")) {
            showToast("所属店铺不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(role) || role.equals("")) {
            showToast("角色不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(userName) || userName.equals("")) {
            showToast("员工用户名不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(userPwd) || userPwd.equals("")) {
            showToast("员工密码不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(againPwd) || againPwd.equals("")) {
            showToast("确认密码不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (!TextUtils.isEmpty(userPwd) && !TextUtils.isEmpty(againPwd)) {
            if (!userPwd.equals(againPwd)) {
                showToast("两次密码输入不一致!");
                alertDialog.dismiss();
                return;
            }
        }

        //TODO:提交逻辑
        if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_ADD)) {

            finish();
        } else if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_EDIT)) {
            finish();
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

                finish();

            }
        });
    }
}
