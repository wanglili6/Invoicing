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
    @BindView(R.id.see_edit)
    TextView seeEdit;
    @BindView(R.id.see_delete)
    TextView seeDelete;
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
        if (type.equals(InvoicingConstants.companyEmployeeAdd)) {
            seeEdit.setVisibility(View.VISIBLE);
            seeDelete.setText("删除");
        } else if (type.equals(InvoicingConstants.SHOP_Employee)) {
            seeDelete.setText("移除");
        }
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
                bundle.putSerializable(InvoicingConstants.EMPLOYEE_userId, selectDataBean);
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

                if (type.equals(InvoicingConstants.companyEmployeeAdd)) {
                    //删除员工
                    requestNetEmployeeList(selectDataBean.getUserid() + "");
                } else if (type.equals(InvoicingConstants.SHOP_Employee)) {
                    //删除店铺员工
                    requestNetShopEmployeeList(selectDataBean.getUserid() + "", shopId);

                }
            }
        });
    }

    /**
     * 删除员工
     *
     * @param userid
     * @param shopid
     */
    private void requestNetShopEmployeeList(String userid, String shopid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.unbindMan_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + userid);
        LogUtils.d("登陆的url" + shopid);
        OkHttpUtils.post().tag(this)
                .addParams("userid", userid)
                .addParams("shopid", shopid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e.toString());
                            Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息EmployeeSeeActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    Toast.makeText(EmployeeSeeActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(EmployeeSeeActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                            Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    /**
     * 删除员工
     *
     * @param userid
     */
    private void requestNetEmployeeList(String userid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.employeedelete_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + userid);
        OkHttpUtils.post().tag(this)
                .addParams("userid", userid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e.toString());
                            Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息EmployeeSeeActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    Toast.makeText(EmployeeSeeActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(EmployeeSeeActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息EmployeeSeeActivity" + e1.toString());
                            Toast.makeText(EmployeeSeeActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
