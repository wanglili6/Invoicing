package com.mtecc.mmp.invoicing.activity.comodity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
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
import com.mtecc.mmp.invoicing.activity.SaoMaActivity;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.CommodityBatchListAdater;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeAddBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.views.NoScrollListView;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加商品
 */
public class AddCommodityActivity extends BaseActivity {

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
    @BindView(R.id.radio_btn_ybz)
    RadioButton radioBtnYbz;
    @BindView(R.id.radio_btn_sz)
    RadioButton radioBtnSz;
    @BindView(R.id.radio_group_genre)
    RadioGroup radioGroupGenre;
    @BindView(R.id.commodity_et_name)
    EditText commodityEtName;
    @BindView(R.id.commodity_et_code)
    EditText commodityEtCode;
    @BindView(R.id.commodity_et_bar_code)
    EditText commodityEtBarCode;
    @BindView(R.id.commodity_img_bar_code)
    ImageView commodityImgBarCode;
    @BindView(R.id.commodity_tv_type)
    TextView commodityTvType;
    @BindView(R.id.commodity_et_guige)
    EditText commodityEtGuige;
    @BindView(R.id.commodity_et_danwei)
    EditText commodityEtDanwei;
    @BindView(R.id.commodity_et_baozhiqi)
    EditText commodityEtBaozhiqi;
    @BindView(R.id.commodity_et_start)
    EditText commodityEtStart;
    @BindView(R.id.commodity_et_start_address)
    EditText commodityEtStartAddress;
    @BindView(R.id.commodity_et_start_biao)
    EditText commodityEtStartBiao;
    @BindView(R.id.commodity_et_start_code)
    EditText commodityEtStartCode;
    @BindView(R.id.commodity_tv_commit)
    TextView commodityTvCommit;
    @BindView(R.id.commodity_batch_list_view)
    NoScrollListView commodityBacthListView;

    String genre = "";//类型
    String name = "";//名字
    String code = "";//编码
    String barcode = "";//条形码
    String type = "";//类别
    String guige = "";//规格
    String danwei = "";//单位
    String baozhiqi = "";//保质期


    String commodityUser = "";//生产商
    String commodityUserAddress = "";//生产商地址
    String commodityUserCode = "";//生产商许可号
    String commodityUserbiao = "";//生产商标
    private String commodityType;
    private AlertDialog showDialog;
    List<BatchBean> mBacthlist = new ArrayList<>();
    private CommodityBatchListAdater commodityBatchListAdater;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        commodityType = parms.getString(InvoicingConstants.COMMODITY_TYPE);
        ivBack.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(commodityType)) {
            if (commodityType.equals(InvoicingConstants.COMMODITY_ADD)) {
                tvTitle.setText("添加商品");
            } else if (commodityType.equals(InvoicingConstants.COMMODITY_EDIT)) {
                tvTitle.setText("编辑商品");
            }
        }

        View view1 = ShowDalogUtils.showCustomizeDialog(AddCommodityActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddCommodityActivity.this, false, view1);
        showDialog.dismiss();

        commodityBatchListAdater = new CommodityBatchListAdater(this, mBacthlist);
        commodityBacthListView.setAdapter(commodityBatchListAdater);
        commodityBatchListAdater.notifyDataSetChanged();

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_add_commodity;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        radioGroupGenre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn_ybz:
                        //预包装
                        genre = "0";
                        commodityEtCode.setEnabled(false);
                        commodityEtCode.setText("预包装商品只可填入条形码");
                        commodityEtCode.setTextColor(getResources().getColor(R.color.text_hint_red));
                        commodityEtBarCode.setEnabled(true);

                        break;

                    case R.id.radio_btn_sz:
                        //散装
                        genre = "1";
                        commodityEtBarCode.setEnabled(false);
                        commodityEtBarCode.setText("散装商品只可填入商品编码");
                        commodityEtCode.setTextColor(getResources().getColor(R.color.text_hint_red));
                        commodityEtCode.setEnabled(true);
                        break;
                }
            }
        });
        commodityEtBarCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (TextUtils.isEmpty(genre)) {
                        showToast("请先选择商品类别!");
                        commodityEtBarCode.setFocusable(false);
                    } else {
                        commodityEtBarCode.setEnabled(true);
                    }

                }
            }
        });
        commodityEtCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (TextUtils.isEmpty(genre)) {
                        showToast("请先选择商品类别!");
                        commodityEtCode.setFocusable(false);
                    } else {
                        commodityEtCode.setEnabled(true);
                    }
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.commodity_img_bar_code, R.id.commodity_tv_commit, R.id.batch_img_add_iteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialog);
                break;
            case R.id.commodity_img_bar_code:
                //扫码
                if (TextUtils.isEmpty(genre)) {
                    showToast("请先选择商品类别!");
                } else {
                    if (genre.equals("0")) {
                        getbarCodePression();
                    } else {
                        showToast("散装商品只可填入商品编码!");
                    }
                }
                break;
            case R.id.commodity_tv_commit:
                //提交
                commit();
                break;
            case R.id.batch_img_add_iteam:
                //添加批次
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_ADD);
                intent.setClass(this, AddBatchActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 12);
                break;
        }
    }

    private void commit() {
        name = commodityEtName.getText().toString().trim();//名字
        code = commodityEtCode.getText().toString().trim();//编码
        barcode = commodityEtBarCode.getText().toString().trim();//条形码
        type = commodityTvType.getText().toString().trim();//类别
        guige = commodityEtGuige.getText().toString().trim();//规格
        danwei = commodityEtGuige.getText().toString().trim();//单位
        baozhiqi = commodityEtBaozhiqi.getText().toString().trim();
        commodityUser = commodityEtStart.getText().toString().trim();//生产商
        commodityUserAddress = commodityEtStartAddress.getText().toString().trim();//生产商地址
        commodityUserCode = commodityEtStartCode.getText().toString().trim();//生产商许可号
        commodityUserbiao = commodityEtStartBiao.getText().toString().trim();
        if (TextUtils.isEmpty(genre) || genre.equals("")) {
            showToast("商品类型不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(name) || name.equals("")) {
            showToast("商品名称不能为空!");
            showDialog.dismiss();
            return;
        }
        if (genre.equals("1")) {
            if (TextUtils.isEmpty(code) || code.equals("")) {
                showToast("商品编号不能为空!");
                showDialog.dismiss();
                return;
            }
        } else if (genre.equals("0")) {
            if (TextUtils.isEmpty(barcode) || barcode.equals("")) {
                showToast("商品条形码不能为空!");
                showDialog.dismiss();
                return;
            }
        }

        if (TextUtils.isEmpty(type) || type.equals("0")) {
            showToast("商品类别不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(guige) || guige.equals("")) {
            showToast("规格不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(danwei) || danwei.equals("")) {
            showToast("单位不能为空!");
            showDialog.dismiss();
            return;
        }


        if (TextUtils.isEmpty(baozhiqi) || baozhiqi.equals("")) {
            showToast("保质期不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(commodityUser) || commodityUser.equals("")) {
            showToast("生产商不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(commodityUserbiao) || commodityUserbiao.equals("")) {
            showToast("商标不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(commodityUserAddress) || commodityUserAddress.equals("")) {
            showToast("生产商地址不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(commodityUserCode) || commodityUserCode.equals("")) {
            showToast("生产许可证号不能为空!");
            showDialog.dismiss();
            return;
        }
        //TODO:提交逻辑

        if (!TextUtils.isEmpty(commodityType) && commodityType.equals(InvoicingConstants.COMMODITY_ADD)) {
            finish();
        } else if (!TextUtils.isEmpty(commodityType) && commodityType.equals(InvoicingConstants.COMMODITY_EDIT)) {
            finish();
        }
    }

    /**
     * 动态添加权限
     */
    public void getbarCodePression() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(AddCommodityActivity.this, Manifest.permission.CAMERA);
        if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
            //没有权限，申请权限
            ActivityCompat.requestPermissions(AddCommodityActivity.this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.VIBRATE,}, 100);
        } else if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //已经有了权限 ，不需要申请
            Intent intent = new Intent(AddCommodityActivity.this, SaoMaActivity.class);
            startActivityForResult(intent, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(AddCommodityActivity.this, SaoMaActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    //处理扫描结果（在界面上显示）
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            final String result = bundle.getString(CodeUtils.RESULT_STRING);
                            LogUtils.i("onActivityResult: " + result);
                            commodityEtBarCode.setText(result);
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(AddCommodityActivity.this, "解析二维码失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case 12:
                    //添加批次

                    List<BatchBean> bactchList = (List<BatchBean>) data.getSerializableExtra(InvoicingConstants.BATCH_Add_list);

                    if (bactchList != null) {
                        mBacthlist.addAll(bactchList);
                        commodityBatchListAdater.notifyDataSetChanged();
                    } else {
                        Toast.makeText(AddCommodityActivity.this, "数据为空!", Toast.LENGTH_SHORT).show();
                    }


                    break;
            }
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
