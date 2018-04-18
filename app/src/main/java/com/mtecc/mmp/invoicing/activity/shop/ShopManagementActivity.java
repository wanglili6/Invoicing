package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.os.Bundle;
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
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

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
                finish();
                break;
            case R.id.setting_pwd_amend:
                break;
        }
    }
}
