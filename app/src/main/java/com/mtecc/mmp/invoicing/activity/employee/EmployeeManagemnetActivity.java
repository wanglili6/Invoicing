package com.mtecc.mmp.invoicing.activity.employee;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeAddBean;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EmployeeManagemnetActivity extends BaseActivity {
    List<String> sexspinnerNameList = new ArrayList<>();
    List<String> sexspinnervalueList = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_left_select)
    TextView tvLeftSelect;
    @BindView(R.id.img_left_select)
    ImageButton imgLeftSelect;
    @BindView(R.id.rl_right_left)
    RelativeLayout rlRightLeft;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
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
    @BindView(R.id.shop_spinner_status)
    Switch shopSpinnerStatus;
    @BindView(R.id.ll_shop_status)
    LinearLayout llShopStatus;
    @BindView(R.id.employee_emily)
    EditText employeeEmily;
    @BindView(R.id.employee_address)
    EditText employeeAddress;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.employee_et_name)
    EditText employeeEtName;
    @BindView(R.id.employee_et_pwd)
    EditText employeeEtPwd;
    @BindView(R.id.employee_et_again_pwd)
    EditText employeeEtAgainPwd;
    @BindView(R.id.employee_tv_register)
    TextView employeeTvRegister;


    private String employeeType = "";
    private String name = "";//姓名
    private String age = "";//年龄
    private String sex = "";//性别
    private String phone = "";//手机号
    private String code = "";//身份证号
    private String status = "0";//状态
    private String address = "";//地址
    private String eamil = "";//邮箱
    private String userName = "";//用户名
    private String userPwd = "";//密码
    private String againPwd = "";//确认密码

    private AlertDialog alertDialog;
    private int cid = 0;
    private AlertDialog showDialog;
    private EmployeeListBean.DataBean selectDataBean;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        ivBack.setVisibility(View.VISIBLE);
        sexspinnerNameList.clear();
        sexspinnervalueList.clear();
        sexspinnerNameList.add("请选择");
        sexspinnerNameList.add("男");
        sexspinnerNameList.add("女");
        sexspinnervalueList.add("-1");
        sexspinnervalueList.add("0");
        sexspinnervalueList.add("1");
        employeeType = parms.getString(InvoicingConstants.EMPLOYEE_TYPE);
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_ADD)) {
            tvTitle.setText("添加员工");
            llShopStatus.setVisibility(View.GONE);

        } else if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_EDIT)) {
            tvTitle.setText("编辑员工");
            selectDataBean = (EmployeeListBean.DataBean) parms.getSerializable(InvoicingConstants.EMPLOYEE_userId);
            llShopStatus.setVisibility(View.VISIBLE);
            if (selectDataBean != null) {
                String username = selectDataBean.getUsername();
                employeeEtPname.setText(username + "");
                employeeEtPnum.setText(selectDataBean.getUserage() + "");
                employeeEtPphone.setText(selectDataBean.getTelphone() + "");
                employeePcode.setText(selectDataBean.getCardnum() + "");
                employeeEmily.setText(selectDataBean.getEmail() + "");
                employeeAddress.setText(selectDataBean.getAddress() + "");
                employeeEtName.setText(selectDataBean.getLogname() + "");
                employeeEtPwd.setText(selectDataBean.getPassword() + "");
                if (selectDataBean.getEmpstate().equals("0")) {
                    shopSpinnerStatus.setChecked(true);
                } else {
                    shopSpinnerStatus.setChecked(false);
                }
                employeeEtAgainPwd.setText("");
            } else {

                showToast("数据错误!");
            }

            shopSpinnerStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        status = "0";
                    } else {
                        status = "";
                    }
                }
            });
        }
        View view1 = ShowDalogUtils.showCustomizeDialog(EmployeeManagemnetActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(EmployeeManagemnetActivity.this, false, view1);
        showDialog.dismiss();
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

        ArrayAdapter msexAdapter = new ArrayAdapter<String>(this, R.layout.registration_sex_item, sexspinnerNameList);
        employeeSpinnerPsex.setAdapter(msexAdapter);
        msexAdapter.notifyDataSetChanged();
        employeeSpinnerPsex.setSelection(0);
        setSpinner();
    }

    /**
     * spinner的点击事件
     */
    private void setSpinner() {

        //性别
        employeeSpinnerPsex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = sexspinnervalueList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * 判断值是否为空---必填
     */
    private void judgmentValueIsEmpty() {
        name = employeeEtPname.getText().toString().trim();
        age = employeeEtPnum.getText().toString().trim();
        phone = employeeEtPphone.getText().toString().trim();
        code = employeePcode.getText().toString().trim();
        eamil = employeeEmily.getText().toString().trim();
        address = employeeAddress.getText().toString().trim();
        userName = employeeEtName.getText().toString().trim();
        userPwd = employeeEtPwd.getText().toString().trim();
        againPwd = employeeEtAgainPwd.getText().toString().trim();
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
            EmployeeAddBean employeeAddBean = new EmployeeAddBean();
            employeeAddBean.setCid(cid);
            employeeAddBean.setUsername(name);
            employeeAddBean.setLogname(userName);
            employeeAddBean.setCardnum(code);
            employeeAddBean.setPassword(userPwd);
            employeeAddBean.setSex(sex);
            employeeAddBean.setEmail(eamil);
            employeeAddBean.setAddress(address);
            employeeAddBean.setTelphone(phone);
            employeeAddBean.setUserage(age);
            Gson gson = new Gson();
            String addJson = gson.toJson(employeeAddBean);
            requestNetAddEmployee(addJson);
        } else if (!TextUtils.isEmpty(employeeType) && employeeType.equals(InvoicingConstants.EMPLOYEE_EDIT)) {
            EmployeeAddBean employeeAddBean = new EmployeeAddBean();
            employeeAddBean.setCid(cid);
            employeeAddBean.setUserid(selectDataBean.getUserid());
            employeeAddBean.setLogname(userName);
            employeeAddBean.setEmpstate(status);
            employeeAddBean.setCardnum(code);
            employeeAddBean.setPassword(userPwd);
            employeeAddBean.setUsername(name);
            employeeAddBean.setSex(sex);
            employeeAddBean.setEmail(eamil);
            employeeAddBean.setAddress(address);
            employeeAddBean.setTelphone(phone);
            employeeAddBean.setUserage(age);
            Gson gson = new Gson();
            String editJson = gson.toJson(employeeAddBean);
            requestNetAddEmployee(editJson);
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


    @OnClick({R.id.rl_back, R.id.employee_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialog);
                break;
//            case R.id.ll_shop:
//                //店铺
//                Intent intentemployee = new Intent();
//                intentemployee.setClass(EmployeeManagemnetActivity.this, SelectShopListActivity.class);
//                startActivityForResult(intentemployee, 1);
//                break;
//            case R.id.ll_role:
//                //角色
//                Intent roleintent = new Intent(EmployeeManagemnetActivity.this, RoleListActivity.class);
//                Bundle rolebundle = new Bundle();
//                rolebundle.putString(InvoicingConstants.role_TYPE, InvoicingConstants.role_select);
//                roleintent.putExtras(rolebundle);
//                startActivityForResult(roleintent, 2);
//                break;
            case R.id.employee_tv_register:
                View view1 = ShowDalogUtils.showCustomizeDialog(this, R.layout.send_customize);
                alertDialog = ShowDalogUtils.showDialog(this, false, view1);
                judgmentValueIsEmpty();
                break;
        }
    }


    /**
     * 添加用户
     */
    private void requestNetAddEmployee(String addJosn) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employeeAdd_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + addJosn);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("userBean", addJosn)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e.toString());
                            Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息EmployeeManagemnetActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加员工成功!");
                                    finish();
                                } else {
                                    showToast("添加员工失败!");
                                }
                            } else {
                                Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e1.toString());
                            Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 编辑用户
     */
    private void requestNetEditEmployee(String addJosn) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employeeEdit_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + addJosn);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("userBean", addJosn)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e.toString());
                            Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息EmployeeManagemnetActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加员工成功!");
                                    finish();
                                } else {
                                    showToast("添加员工失败!");
                                }
                            } else {
                                Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeManagemnetActivity" + e1.toString());
                            Toast.makeText(EmployeeManagemnetActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
