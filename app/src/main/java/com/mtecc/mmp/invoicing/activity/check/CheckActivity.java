package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchase.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        checkId = parms.getString(InvoicingConstants.check_id);
        tvTitle.setText("订单审核");
        parms = getIntent().getExtras();
        checkType = parms.getString(InvoicingConstants.check_type);


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
                break;
        }
    }
}
