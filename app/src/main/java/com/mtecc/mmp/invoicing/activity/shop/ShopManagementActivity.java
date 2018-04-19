package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.login.RegistrationBaseInfoActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加和编辑店铺
 */
public class ShopManagementActivity extends BaseActivity {

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
    @BindView(R.id.shop_mange_name)
    EditText shopMangeName;
    @BindView(R.id.shop_mange_code)
    EditText shopMangeCode;
    @BindView(R.id.shop_spinner_status)
    Spinner shopSpinnerStatus;
    @BindView(R.id.shop_mange_address)
    EditText shopMangeAddress;
    @BindView(R.id.setting_pwd_amend)
    TextView settingPwdAmend;
    private String shopType = "";
    private String shopName = "";//店铺名称
    private String shopCode = "";//店铺编码
    private String shopstatus = "";//店铺状态
    private String shopAddrss = "";//店铺地址
    List<String> spinnerNameList = new ArrayList<>();
    List<String> spinnervalueList = new ArrayList<>();
    private AlertDialog alertDialog;

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
        shopType = parms.getString(InvoicingConstants.SHOP_TYPE);
        if (!TextUtils.isEmpty(shopType) && shopType.equals(InvoicingConstants.SHOP_ADD)) {
            tvTitle.setText("添加店铺");
        } else if (!TextUtils.isEmpty(shopType) && shopType.equals(InvoicingConstants.SHOP_EDIT)) {
            tvTitle.setText("编辑店铺");
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_shop_management;
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
        shopSpinnerStatus.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        shopSpinnerStatus.setSelection(0);
        setSpinner();
    }

    /**
     * 设置店铺状态
     */
    private void setSpinner() {
        shopSpinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shopstatus = spinnervalueList.get(position);
                LogUtils.d(spinnerNameList.get(position) + "-----" + spinnervalueList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.rl_back, R.id.setting_pwd_amend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, alertDialog);
                break;
            case R.id.setting_pwd_amend:
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
        shopName = shopMangeName.getText().toString().trim();
        shopCode = shopMangeCode.getText().toString().trim();
        shopAddrss = shopMangeAddress.getText().toString().trim();

        if (TextUtils.isEmpty(shopName) || shopName.equals("")) {
            showToast("店铺名称不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(shopCode) || shopCode.equals("")) {
            showToast("店铺编码不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(shopstatus) || shopstatus.equals("0")) {
            showToast("店铺状态不能为空!");
            alertDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(shopAddrss) || shopAddrss.equals("")) {
            showToast("店铺地址不能为空!");
            alertDialog.dismiss();
            return;
        }
       
       
            //TODO:提交逻辑
        if (!TextUtils.isEmpty(shopType) && shopType.equals(InvoicingConstants.SHOP_ADD)) {
          
            finish();
        } else if (!TextUtils.isEmpty(shopType) && shopType.equals(InvoicingConstants.SHOP_EDIT)) {
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
