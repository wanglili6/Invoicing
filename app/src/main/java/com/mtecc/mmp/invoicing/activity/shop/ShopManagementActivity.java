package com.mtecc.mmp.invoicing.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.login.RegistrationBaseInfoActivity;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopAddBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
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
    Switch shopSpinnerStatus;
    @BindView(R.id.shop_mange_address)
    EditText shopMangeAddress;
    @BindView(R.id.shop_commit)
    TextView shopCommit;
    @BindView(R.id.ll_shop_status)
    LinearLayout llShopStayus;
    private String shopType = "";
    private String shopName = "";//店铺名称
    private String shopCode = "";//店铺编码
    private String shopstatus = "";//店铺状态
    private String shopAddrss = "";//店铺地址
    private AlertDialog alertDialog;
    private Gson gson;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        gson = new Gson();
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("添加店铺");
        llShopStayus.setVisibility(View.GONE);

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

    }


    @OnClick({R.id.rl_back, R.id.shop_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialogialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialogialog);
                break;
            case R.id.shop_commit:
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

        if (TextUtils.isEmpty(shopAddrss) || shopAddrss.equals("")) {
            showToast("店铺地址不能为空!");
            alertDialog.dismiss();
            return;
        }
        ShopAddBean shopAddBean = new ShopAddBean();
        shopAddBean.setShopname(shopName);
        shopAddBean.setShopnum(shopCode);
        shopAddBean.setAddress(shopAddrss);
        String addJson = gson.toJson(shopAddBean);
        requestNetAddShop(addJson);
        finish();

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

    /**
     * 添加店铺
     *
     * @param addJson
     */
    private void requestNetAddShop(String addJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.shopAdd_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("ywshopBean", addJson)
                .addParams("userid", PreferencesUtils.getString(this, InvoicingConstants.USER_ID, ""))
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            alertDialog.dismiss();
                            LogUtils.d("错误信息ShopManagementActivity" + e.toString());
                            Toast.makeText(ShopManagementActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopManagementActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            alertDialog.dismiss();
                            LogUtils.d("返回值信息ShopManagementActivity" + response.toString());

                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("创建成功!");
                                    finish();
                                } else {
                                    showToast("创建失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(ShopManagementActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息ShopManagementActivity" + e1.toString());
                            Toast.makeText(ShopManagementActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
