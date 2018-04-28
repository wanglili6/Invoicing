package com.mtecc.mmp.invoicing.activity.comodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看商品
 */
public class SeeCommodityActivity extends BaseActivity {

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
    @BindView(R.id.commodity_see_genre)
    TextView commoditySeeGenre;
    @BindView(R.id.commodity_see_name)
    TextView commoditySeeName;
    @BindView(R.id.commodity_see_code)
    TextView commoditySeeCode;
    @BindView(R.id.commodity_see_bar_code)
    TextView commoditySeeBarCode;
    @BindView(R.id.commodity_see_guige)
    TextView commoditySeeGuige;
    @BindView(R.id.commodity_see_danwei)
    TextView commoditySeeDanwei;
    @BindView(R.id.commodity_see_baozhiqi)
    TextView commoditySeeBaozhiqi;
    @BindView(R.id.employee_see_shop)
    TextView employeeSeeShop;
    @BindView(R.id.employee_see_role)
    TextView employeeSeeRole;
    @BindView(R.id.commodity_see_start_name)
    TextView commoditySeeStartName;
    @BindView(R.id.commodity_see_biao)
    TextView commoditySeeBiao;
    @BindView(R.id.commodity_see_address)
    TextView commoditySeeAddress;
    @BindView(R.id.commodity_see_start_code)
    TextView commoditySeeStartCode;
    @BindView(R.id.see_edit)
    TextView seeEdit;
    @BindView(R.id.see_delete)
    TextView seeDelete;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看商品");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_see_commodity;
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
                Intent roleintent = new Intent(this, AddCommodityActivity.class);
                Bundle rolebundle = new Bundle();
                rolebundle.putString(InvoicingConstants.COMMODITY_TYPE, InvoicingConstants.COMMODITY_EDIT);
                roleintent.putExtras(rolebundle);
                startActivity(roleintent);
                break;
            case R.id.see_delete:
                break;
        }
    }
}
