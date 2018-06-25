package com.mtecc.mmp.invoicing.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.activity.employee.adapter.SelectEmployeedialogListAdapter;
import com.mtecc.mmp.invoicing.activity.login.adapter.SelectShopAdapter;
import com.mtecc.mmp.invoicing.activity.login.bean.LoginUserInfo;
import com.mtecc.mmp.invoicing.activity.login.bean.ShopSelectBean;
import com.mtecc.mmp.invoicing.activity.shop.ShopEmployeeActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_user_name)
    EditText loginUserName;
    @BindView(R.id.login_user_pwd)
    EditText loginUserPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private String url;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        String userName = PreferencesUtils.getString(LoginActivity.this, InvoicingConstants.USER_LOGNAME, "");
        String userPwd = PreferencesUtils.getString(LoginActivity.this, InvoicingConstants.USER_PWD, "");
        if (TextUtils.isEmpty(userName)) {
            loginUserName.setText("");
        } else {
            loginUserName.setText(userName);
        }
        if (TextUtils.isEmpty(userPwd)) {
            loginUserPwd.setText("");
        } else {
            loginUserPwd.setText(userPwd);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
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


    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String userName = loginUserName.getText().toString().trim();
                String userPwd = loginUserPwd.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                    showToast("用户名或密码不能为空!");
                    return;
                }
                requestNetLogin(userName, userPwd);
                break;
            case R.id.tv_register:
//                startActivity(new Intent(this, RegistrationSMSActivity.class));
                startActivity(new Intent(this, ShortRegistrationInfoActivity.class));
                break;
        }
    }

    /**
     * 登陆
     *
     * @param userName
     * @param userPwd
     */
    private void requestNetLogin(String userName, final String userPwd) {
        url = InvoicingConstants.BASE_URL + InvoicingConstants.LOGIN_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("logname", userName)
                .addParams("password", userPwd)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息LoginActivity" + e.toString());
                            Toast.makeText(LoginActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息LoginActivity" + response.toString());
                            Gson gson = new Gson();
                            LoginUserInfo loginUserInfo = gson.fromJson(response, LoginUserInfo.class);
                            if (loginUserInfo != null) {
                                int result = loginUserInfo.getResult();
                                if (result == 200) {
                                    boolean isuseradmin = loginUserInfo.isIsuseradmin();
                                    boolean isHaveCGAuidt = loginUserInfo.isHaveCGAuidt();
                                    boolean isHaveXSAuidt = loginUserInfo.isHaveXSAuidt();
                                    boolean isUseCGAuidt = loginUserInfo.isUseCGAuidt();
                                    boolean isUseXSAuidt = loginUserInfo.isUseXSAuidt();
                                    PreferencesUtils.putBoolean(LoginActivity.this, InvoicingConstants.isuseradmin, isuseradmin);
                                    PreferencesUtils.putBoolean(LoginActivity.this, InvoicingConstants.isHaveCGAuidt, isHaveCGAuidt);
                                    PreferencesUtils.putBoolean(LoginActivity.this, InvoicingConstants.isHaveXSAuidt, isHaveXSAuidt);
                                    PreferencesUtils.putBoolean(LoginActivity.this, InvoicingConstants.isUseCGAuidt, isUseCGAuidt);
                                    PreferencesUtils.putBoolean(LoginActivity.this, InvoicingConstants.isUseXSAuidt, isUseXSAuidt);
                                    if (isuseradmin) {
                                        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_ID, "");
                                        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_Name, "");
                                        storageMsg(loginUserInfo, userPwd);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("isuseradmin", isuseradmin);
                                        intent.putExtra("name", "商户管理员");
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        int ishavemoreshop = loginUserInfo.getIshavemoreshop();
                                        if (ishavemoreshop == 1) {
                                            //请求成功
                                            storageMsg(loginUserInfo, userPwd);
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("isuseradmin", isuseradmin);
                                            intent.putExtra("ishavemoreshop", "1");
                                            intent.putExtra("shopname", loginUserInfo.getShop().getShopname());
                                            PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_ID, loginUserInfo.getShop().getShopid() + "");
                                            PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_Name, loginUserInfo.getShop().getShopname() + "");
                                            startActivity(intent);
                                            finish();
                                        } else if (ishavemoreshop == 0) {
                                            showToast("您当前没有绑定店铺,请联系管理员进行绑定!");
                                            PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_ID, "");
                                            PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_Name, "");
                                        } else {
                                            storageMsg(loginUserInfo, userPwd);
                                            requestNetShops(loginUserInfo.getUser().getUserid() + "", "2", isuseradmin);

                                        }
                                    }

                                } else if (result == 500) {
                                    //用户名或密码错误
                                    showToast("用户名或密码错误!请重新输入");
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }
                });
    }

    /**
     * 所有店铺
     *
     * @param userid
     * @param msg
     * @param isuseradmin
     */
    private void requestNetShops(String userid, final String msg, final boolean isuseradmin) {
        url = InvoicingConstants.BASE_URL + InvoicingConstants.SELECT_SHOP_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("userid", userid)
                .addParams("msg", msg)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息LoginActivity" + e.toString());
                            Toast.makeText(LoginActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息LoginActivity" + response.toString());
                            Gson gson = new Gson();
                            ShopSelectBean shopSelectBean = gson.fromJson(response, ShopSelectBean.class);
                            if (shopSelectBean != null) {
                                List<ShopSelectBean.ShoplistBean> shoplist = shopSelectBean.getShoplist();
                                if (shoplist != null) {
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(LoginActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(LoginActivity.this, false, customizeDialog);
                                    SelectEmployeeClick(customizeDialog, alertDialog, shoplist, isuseradmin);
                                }
                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息LoginActivity" + e1.toString());
                        }
                    }
                });
    }

    /**
     * 选择店铺
     *
     * @param customizeDialog
     * @param alertDialog
     * @param shoplist
     * @param isuseradmin
     */
    private void SelectEmployeeClick(View customizeDialog, final AlertDialog alertDialog, final List<ShopSelectBean.ShoplistBean> shoplist, final boolean isuseradmin) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        imgxDialog.setVisibility(View.GONE);
        tvselct.setText("选择店铺");
        SelectShopAdapter selectShopList = new SelectShopAdapter(LoginActivity.this, shoplist, alertDialog, isuseradmin);
        selectList.setAdapter(selectShopList);
        selectShopList.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopSelectBean.ShoplistBean shoplistBean = shoplist.get(position);
                PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_ID, shoplistBean.getShopid() + "");
                PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.SHOP_Name, shoplistBean.getShopname() + "");
                alertDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("isuseradmin", isuseradmin);
                intent.putExtra("ishavemoreshop", "2");
                intent.putExtra("shopname", shoplistBean.getShopname());
                intent.putExtra("shopList", (Serializable) shoplist);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 存储信息
     *
     * @param loginUserInfo
     */
    private void storageMsg(LoginUserInfo loginUserInfo, String pwd) {
        //存储个人信息
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_NAME, loginUserInfo.getUser().getUsername());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ID, loginUserInfo.getUser().getUserid() + "");
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_LOGNAME, loginUserInfo.getUser().getLogname());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_PWD, pwd);
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_TEL_PHONE, loginUserInfo.getUser().getTelphone());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ADDRESS, loginUserInfo.getUser().getAddress());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_CRESTER_TIMER_STR, loginUserInfo.getUser().getCreatdateStr());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_EMAIL, loginUserInfo.getUser().getEmail());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_ROLE, loginUserInfo.getUser().getRole());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_AGE, loginUserInfo.getUser().getUserage());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_SEX, loginUserInfo.getUser().getSex());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.USER_CARDNUM, loginUserInfo.getUser().getCardnum());
        //存储企业信息
        LoginUserInfo.UserBean.CidBeanXX cidBean = loginUserInfo.getUser().getCid();
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_ADDRESS, cidBean.getAddress());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_CODE, cidBean.getClicence());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_END_DATA, cidBean.getEnddateStr());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_FAREN, cidBean.getQyfr());
        PreferencesUtils.putInt(LoginActivity.this, InvoicingConstants.QY_ID, cidBean.getCid());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_JJFW, cidBean.getJjfw());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_NAME, cidBean.getCname());
        PreferencesUtils.putString(LoginActivity.this, InvoicingConstants.QY_STATUS, cidBean.getState());
    }


}
