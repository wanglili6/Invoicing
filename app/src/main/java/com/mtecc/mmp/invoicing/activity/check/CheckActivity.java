package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.bean.AuditBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 审核页面
 */
public class CheckActivity extends BaseActivity {

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
    @BindView(R.id.check_tv_name)
    TextView checkTvName;
    @BindView(R.id.check_tv_money)
    TextView checkTvMoney;
    @BindView(R.id.check_tv_code)
    TextView checkTvCode;
    @BindView(R.id.check_tv_timer)
    TextView checkTvTimer;
    @BindView(R.id.tv_user_text)
    TextView tvUserText;
    @BindView(R.id.img_check)
    ImageView imgCheck;
    @BindView(R.id.rl_see_datial)
    RelativeLayout rlSeeDatial;
    @BindView(R.id.et_check_yijian)
    EditText etCheckYijian;
    @BindView(R.id.rb_jieguo_yes)
    RadioButton rbJieguoYes;
    @BindView(R.id.rb_jieguo_no)
    RadioButton rbJieguoNo;
    @BindView(R.id.rg_jieguo)
    RadioGroup rgJieguo;
    @BindView(R.id.check_commit)
    TextView checkCommit;
    private String checkId = "";
    private String result = "";
    private String checkType;
    private PrichaseIncomeBean.DataBean dataBean;
    private String auditmess;
    private String userId;
    private AlertDialog showDialog;
    private String url;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        View view1 = ShowDalogUtils.showCustomizeDialog(CheckActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(CheckActivity.this, false, view1);
        showDialog.dismiss();
        ivBack.setVisibility(View.VISIBLE);
        userId = PreferencesUtils.getString(CheckActivity.this, InvoicingConstants.USER_ID, "");
        parms = getIntent().getExtras();
        dataBean = (PrichaseIncomeBean.DataBean) parms.getSerializable(InvoicingConstants.check_id);
        checkType = parms.getString(InvoicingConstants.check_type);
        if (checkType.equals(InvoicingConstants.check_sales)) {
            //销售审核
            tvTitle.setText("销售订单审核");
            tvUserText.setText("分销商");
        } else if (checkType.equals(InvoicingConstants.check_purchases)) {
            //采购审核
            tvTitle.setText("采购订单审核");
            tvUserText.setText("进货商");
        }
        if (dataBean != null) {
            checkTvName.setText(dataBean.getHdid() + "");
            checkTvMoney.setText(dataBean.getSum() + "");
            checkTvCode.setText(dataBean.getMername() + "");
            checkTvTimer.setText(dataBean.getHddate() + "");
        }

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_check;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        rgJieguo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_jieguo_yes:
                        result = "1";
                        break;
                    case R.id.rb_jieguo_no:
                        result = "0";
                        break;
                }
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.rl_see_datial, R.id.check_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_see_datial:
                //查看订单详情
                Intent expendintent = new Intent();
                Bundle expendbundle = new Bundle();
                expendbundle.putSerializable(InvoicingConstants.hdid, (Serializable) dataBean);
                if (checkType.equals(InvoicingConstants.check_purchases)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases);
                } else if (checkType.equals(InvoicingConstants.check_purchases_out)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_purchases_out);
                } else if (checkType.equals(InvoicingConstants.check_sales)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales);
                } else if (checkType.equals(InvoicingConstants.check_sales_out)) {
                    expendbundle.putString(InvoicingConstants.check_type, InvoicingConstants.check_sales_out);
                }
                expendintent.setClass(CheckActivity.this, SeeOrdersActivity.class);
                expendintent.putExtras(expendbundle);
                startActivity(expendintent);
                break;
            case R.id.check_commit:
                //提交审核
                showDialog.show();
                commitAuditOrder();
                break;
        }
    }

    /**
     * 提交审核
     */
    private void commitAuditOrder() {
        auditmess = etCheckYijian.getText().toString().trim();
        if (dataBean == null) {
            showToast("货单不能为空");
            showDialog.dismiss();
            return;
        } else {
            String hdid = dataBean.getHdid();
            if (TextUtils.isEmpty(hdid)) {
                showToast("货单不能为空");
                showDialog.dismiss();
                return;
            }
        }
        if (TextUtils.isEmpty(result)) {
            showToast("审核结果不能为空");
            showDialog.dismiss();
            return;
        } else {
            if (result.equals("0")) {
                if (TextUtils.isEmpty(auditmess)) {
                    showToast("审核意见不能为空");
                    showDialog.dismiss();
                    return;
                }
            }
        }

        AuditBean auditBean = new AuditBean();
        auditBean.setHdid(dataBean.getHdid() + "");
        auditBean.setAuditman(Integer.valueOf(userId));
        auditBean.setAuditmess(auditmess);
        auditBean.setAuditresult(result);
        Gson gson = new Gson();
        String json = gson.toJson(auditBean);
        requestNetCommit(json);
    }

    /**
     * 提交订单
     */
    private void requestNetCommit(final String json) {
        if (checkType.equals(InvoicingConstants.check_sales)) {
            //销售审核
            url = InvoicingConstants.BASE_URL + InvoicingConstants.SaveAudit_URL;
        } else if (checkType.equals(InvoicingConstants.check_purchases)) {
            //采购审核
            url = InvoicingConstants.BASE_URL + InvoicingConstants.SaveenterAudit_URL;
        }
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + json);
        PostFormBuilder post = OkHttpUtils.post();
        post.tag(this)
                .addParams("auditBean", json)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(CheckActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("审核订单成功!");
                                    finish();
                                } else {
                                    showToast("审核订单成功!");
                                }
                            } else {
                                Toast.makeText(CheckActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {

                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(CheckActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
