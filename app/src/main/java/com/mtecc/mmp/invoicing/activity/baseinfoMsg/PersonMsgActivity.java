package com.mtecc.mmp.invoicing.activity.baseinfoMsg;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 个人信息
 */
public class PersonMsgActivity extends BaseActivity {


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
    @BindView(R.id.person_msg_role)
    TextView personMsgRole;
    @BindView(R.id.person_msg_name)
    EditText personMsgName;
    @BindView(R.id.rl_person_msg_name)
    RelativeLayout rlPersonMsgName;
    @BindView(R.id.person_msg_age)
    EditText personMsgAge;
    @BindView(R.id.rl_person_msg_age)
    RelativeLayout rlPersonMsgAge;
    @BindView(R.id.person_msg_sex)
    EditText personMsgSex;
    @BindView(R.id.rl_person_msg_sex)
    RelativeLayout rlPersonMsgSex;
    @BindView(R.id.person_msg_phone)
    EditText personMsgPhone;
    @BindView(R.id.rl_person_msg_phone)
    RelativeLayout rlPersonMsgPhone;
    @BindView(R.id.person_msg_code)
    EditText personMsgCode;
    @BindView(R.id.rl_person_msg_code)
    RelativeLayout rlPersonMsgCode;
    @BindView(R.id.person_msg_emily)
    EditText personMsgEmily;
    @BindView(R.id.rl_person_msg_emily)
    RelativeLayout rlPersonMsgEmily;
    @BindView(R.id.person_msg_address)
    EditText personMsgAddress;
    @BindView(R.id.rl_person_msg_address)
    RelativeLayout rlPersonMsgAddress;
    @BindView(R.id.person_tv_msg)
    TextView personTvMsg;
    private String name = "";
    private String phone = "";
    private String address = "";
    private String cardNum = "";
    private String email = "";
    private String role = "";
    private String age = "";
    private String sex = "";
    private int cid = 0;
    private String userId = "";
    private AlertDialog showDialog;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("个人信息");
        initPersonMsg();
        View view1 = ShowDalogUtils.showCustomizeDialog(this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(this, false, view1);
        showDialog.dismiss();

    }

    /**
     * 初始化用户信息
     */
    private void initPersonMsg() {
        name = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_NAME, "");
        phone = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_TEL_PHONE, "");
        address = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_ADDRESS, "");
        cardNum = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_CARDNUM, "");
        email = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_EMAIL, "");
        role = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_ROLE, "");
        age = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_AGE, "");
        sex = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_SEX, "");
        cid = PreferencesUtils.getInt(PersonMsgActivity.this, InvoicingConstants.QY_ID, 0);
        userId = PreferencesUtils.getString(PersonMsgActivity.this, InvoicingConstants.USER_ID, "");
        if (TextUtils.isEmpty(name)) {
            personMsgName.setText("暂无");
        } else {
            personMsgName.setText(name);
        }

        if (TextUtils.isEmpty(phone)) {
            personMsgPhone.setText("暂无");
        } else {
            personMsgPhone.setText(phone);
        }

        if (TextUtils.isEmpty(address)) {
            personMsgAddress.setText("暂无");
        } else {
            personMsgAddress.setText(address);
        }
        if (TextUtils.isEmpty(email)) {
            personMsgEmily.setText("暂无");
        } else {
            personMsgEmily.setText(email);
        }
        if (TextUtils.isEmpty(role)) {
            personMsgRole.setText("暂无");
        } else {
            personMsgRole.setText(role);
        }
        if (TextUtils.isEmpty(age)) {
            personMsgAge.setText("暂无");
        } else {
            personMsgAge.setText(age);
        }
        if (TextUtils.isEmpty(cardNum)) {
            personMsgCode.setText("暂无");
        } else {
            personMsgCode.setText(cardNum);
        }
        if (TextUtils.isEmpty(sex)) {

            personMsgSex.setText("暂无");
        } else {
            personMsgSex.setText(sex);
        }
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_person_msg;
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


    /**
     * 完善个人信息
     */
    private void personMsg() {
        name = personMsgName.getText().toString().trim();
        phone = personMsgPhone.getText().toString().trim();
        address = personMsgAddress.getText().toString().trim();
        email = personMsgEmily.getText().toString().trim();
        age = personMsgAge.getText().toString().trim();
        cardNum = personMsgCode.getText().toString().trim();
        sex = personMsgSex.getText().toString().trim();

        if (phone.length()!=11){
            showDialog.dismiss();
            showToast("手机号格式不正确!");
            return;
        }
        if (cardNum.length()!=18){
            showDialog.dismiss();
            showToast("身份证格式不正确!");
            return;
        }
        PersonMgsBean personMsgBean = new PersonMgsBean();
        personMsgBean.setUsername(name);
        personMsgBean.setCardnum(cardNum);
        personMsgBean.setAddress(address);
        personMsgBean.setUserage(age);
        personMsgBean.setSex(sex);
        personMsgBean.setTelphone(phone);
        personMsgBean.setEmail(email);
        personMsgBean.setCid(cid);
        personMsgBean.setUserid(Integer.parseInt(userId));

        Gson gson = new Gson();
        String userJson = gson.toJson(personMsgBean);
        requestNetPerson(userJson);
    }

    /**
     * 提交注册信息
     *
     * @param userJson
     */
    private void requestNetPerson(String userJson) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.CangePersonInfo_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("personMsgBean", userJson)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息PersonMsgActivity" + e.toString());
                            Toast.makeText(PersonMsgActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息PersonMsgActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        showDialog.dismiss();
                        try {
                            LogUtils.d("返回值信息PersonMsgActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("保存信息成功!");
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_NAME, name);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_TEL_PHONE, phone);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_ADDRESS, address);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_EMAIL, email);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_AGE, age);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_SEX, sex);
                                    PreferencesUtils.putString(PersonMsgActivity.this, InvoicingConstants.USER_CARDNUM, cardNum);
                                    finish();
                                } else {
                                    showToast("保存信息失败请重新提交!");
                                }
                            } else {
                                Toast.makeText(PersonMsgActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (
                                Exception e1)

                        {
                            LogUtils.d("错误信息PersonMsgActivity" + e1.toString());
                            Toast.makeText(PersonMsgActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @OnClick({R.id.person_tv_msg, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

            case R.id.person_tv_msg:
                //完善个人信息
                LogUtils.i("完善个人信息");
                personMsg();
                break;
        }
    }
}
