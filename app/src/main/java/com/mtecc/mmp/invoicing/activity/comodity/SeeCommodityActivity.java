package com.mtecc.mmp.invoicing.activity.comodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.SeeCommodityBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    @BindView(R.id.see_ll_bar_code)
    LinearLayout seeLlBarcode;
    @BindView(R.id.see_ll_code)
    LinearLayout seeLlcode;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.commodity_see_genre)
    TextView commoditySeeGenre;
    @BindView(R.id.commodity_see_remark)
    TextView commoditySeeRemark;
    @BindView(R.id.commodity_see_name)
    TextView commoditySeeName;
    @BindView(R.id.commodity_see_code)
    TextView commoditySeeCode;
    @BindView(R.id.commodity_see_bar_code)
    TextView commoditySeeBarCode;
    @BindView(R.id.commodity_see_guige)
    TextView commoditySeeGuige;
    @BindView(R.id.commodity_see_type)
    TextView commoditySeeType;
    @BindView(R.id.commodity_see_trademark)
    TextView commoditySeeTradmark;
    @BindView(R.id.commodity_see_danwei)
    TextView commoditySeeDanwei;
    @BindView(R.id.commodity_see_baozhiqi)
    TextView commoditySeeBaozhiqi;

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
    private String proid;
    private AlertDialog showDialog;
    private Gson gson;
    private boolean isPause;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        parms = getIntent().getExtras();
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看商品");
        proid = parms.getString("proid");
        gson = new Gson();
        View view1 = ShowDalogUtils.showCustomizeDialog(SeeCommodityActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(SeeCommodityActivity.this, false, view1);
        showDialog.dismiss();
        requestNetGetCommodity(proid);
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

    @OnClick({R.id.rl_batch, R.id.rl_back, R.id.see_edit, R.id.see_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.see_edit:
                Intent roleintent = new Intent(this, AddCommodityActivity.class);
                Bundle rolebundle = new Bundle();
                rolebundle.putString(InvoicingConstants.COMMODITY_TYPE, InvoicingConstants.COMMODITY_EDIT);
                rolebundle.putString(InvoicingConstants.COMMODITY_Id, proid);
                roleintent.putExtras(rolebundle);
                startActivity(roleintent);
                break;
            case R.id.see_delete:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                editClick(exitView, dialog);
                break;
            case R.id.rl_batch:
                Intent intent = new Intent(SeeCommodityActivity.this, BatchListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.COMMODITY_Id, proid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    /**
     * 是否返回
     *
     * @param exitView
     * @param dialog
     */
    private void editClick(View exitView, final AlertDialog dialog) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除此商品?");
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

                requestNetdelCommodity(proid);

            }
        });
    }

    /**
     * 商品查询
     */
    private void requestNetGetCommodity(final String proid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.lookGood_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + proid);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("proid", proid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息SeeCommodityActivity" + e.toString());
                            Toast.makeText(SeeCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息SeeCommodityActivity" + response.toString());
                            SeeCommodityBean seecommodityBean = gson.fromJson(response, SeeCommodityBean.class);
                            if (seecommodityBean != null) {
                                SeeCommodityBean.DataBean commodityBean = seecommodityBean.getData();
                                String protype = commodityBean.getProtype();
                                if (protype.equals("0")) {
                                    seeLlcode.setVisibility(View.VISIBLE);
                                    seeLlBarcode.setVisibility(View.GONE);
                                    commoditySeeGenre.setText("散装");
                                    commoditySeeCode.setText(commodityBean.getProCode());
                                } else {
                                    seeLlcode.setVisibility(View.GONE);
                                    seeLlBarcode.setVisibility(View.VISIBLE);
                                    commoditySeeGenre.setText("预包装");
                                    commoditySeeBarCode.setText(commodityBean.getBarcode());

                                }
                                commoditySeeName.setText(commodityBean.getProName());
                                commoditySeeGuige.setText(commodityBean.getMeas());
                                commoditySeeDanwei.setText(commodityBean.getMeaunit());
                                String bzqunit = commodityBean.getBzqunit();
                                if (bzqunit.equals("1")) {
                                    commoditySeeBaozhiqi.setText(commodityBean.getProbzq() + "天");

                                } else if (bzqunit.equals("2")) {
                                    commoditySeeBaozhiqi.setText(commodityBean.getProbzq() + "个月");

                                } else if (bzqunit.equals("3")) {
                                    commoditySeeBaozhiqi.setText(commodityBean.getProbzq() + "年");

                                }
                                commoditySeeStartName.setText(commodityBean.getMernameorplace());
                                commoditySeeBiao.setText(commodityBean.getMernameorplace());
                                commoditySeeAddress.setText(commodityBean.getMernameorplace());
                                commoditySeeStartCode.setText(commodityBean.getMernameorplace());
                                commoditySeeTradmark.setText(commodityBean.getTrademark());
                                commoditySeeRemark.setText(commodityBean.getRemark());
                                SeeCommodityBean.DataBean.ObtypeBean obtype = commodityBean.getObtype();
                                if (obtype != null) {
                                    String grade = obtype.getGrade();
                                    if (grade.equals("3")) {
                                        commoditySeeType.setText(obtype.getParentCode().getParentCode().getContent() + "-" + obtype.getParentCode().getContent() + "-" + obtype.getContent());
                                    } else if (grade.equals("2")) {

                                        commoditySeeType.setText(obtype.getParentCode().getContent() + "-" + obtype.getContent());
                                    } else if (grade.equals("1")) {
                                        commoditySeeType.setText(obtype.getContent());

                                    }
                                }
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                            Toast.makeText(SeeCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 刪除
     */
    private void requestNetdelCommodity(final String proid) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.deleteGood_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + proid);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("proid", proid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息SeeCommodityActivity" + e.toString());
                            Toast.makeText(SeeCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息SeeCommodityActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("编辑商品成功!");
                                    finish();
                                } else {
                                    showToast("编辑商品失败!");
                                }
                            } else {
                                Toast.makeText(SeeCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeCommodityActivity" + e1.toString());
                            Toast.makeText(SeeCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause) {
            requestNetGetCommodity(proid);
        }
    }
}
