package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.os.Bundle;
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
    @BindView(R.id.register_et_pnum)
    EditText registerEtPnum;
    @BindView(R.id.employee_spinner_psex)
    Spinner employeeSpinnerPsex;
    @BindView(R.id.register_et_pphone)
    EditText registerEtPphone;
    @BindView(R.id.registration_pcode)
    EditText registrationPcode;
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
    @BindView(R.id.register_et_faren)
    EditText registerEtFaren;
    @BindView(R.id.register_et_code)
    EditText registerEtCode;
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
    private String employeeType = "";
    List<String> spinnerNameList = new ArrayList<>();
    List<String> spinnervalueList = new ArrayList<>();
    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        ivBack.setVisibility(View.VISIBLE);
        spinnerNameList.clear();
        spinnervalueList.clear();
        spinnerNameList.add("请选择");
        spinnerNameList.add("正常");
        spinnerNameList.add("注销");
        spinnervalueList.add("0");
        spinnervalueList.add("1");
        spinnervalueList.add("2");
        employeeType = parms.getString(InvoicingConstants.EMPLOYEE_TYPE);
        if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_ADD)) {
            tvTitle.setText("添加员工");

        }else if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_EDIT)) {
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
        ArrayAdapter mAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, spinnerNameList);
        employeeSpinnerStatus.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        employeeSpinnerStatus.setSelection(0);
        setSpinner();
    }
    /**
     * 设置店铺状态
     */
    private void setSpinner() {
        employeeSpinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.d(spinnerNameList.get(position) + "-----" + spinnervalueList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @OnClick({R.id.rl_back, R.id.register_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.register_tv_register:
                break;
        }
    }
}
