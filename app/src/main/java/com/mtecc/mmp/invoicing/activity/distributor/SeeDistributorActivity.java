package com.mtecc.mmp.invoicing.activity.distributor;

import android.content.Context;
import android.os.Bundle;
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
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.distributor.adapter.AddDoucumenlistAdapter;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributorBean;
import com.mtecc.mmp.invoicing.activity.distributor.bean.SeeDistributorBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.views.NoScrollListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SeeDistributorActivity extends BaseActivity {

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
    @BindView(R.id.tv_distributor_name)
    TextView tvDistributorName;
    @BindView(R.id.tv_distributor_code)
    TextView tvDistributorCode;
    @BindView(R.id.tv_distributor_until_timer)
    TextView tvDistributorUntilTimer;
    @BindView(R.id.tv_distributor_is_person)
    TextView tvDistributorIsPerson;
    @BindView(R.id.tv_distributor_qq)
    TextView tvDistributorQq;
    @BindView(R.id.ll_distributor_qq)
    LinearLayout llDistributorQq;
    @BindView(R.id.tv_distributor_weixin)
    TextView tvDistributorWeixin;
    @BindView(R.id.ll_distributor_weixin)
    LinearLayout llDistributorWeixin;
    @BindView(R.id.tv_distributor_faren)
    TextView tvDistributorFaren;
    @BindView(R.id.tv_distributor_bar_code)
    TextView tvDistributorBarCode;
    @BindView(R.id.tv_distributor_phone)
    TextView tvDistributorPhone;
    @BindView(R.id.tv_distributor_car_num)
    TextView tvDistributorCarNum;
    @BindView(R.id.tv_distributor_email)
    TextView tvDistributorEmail;
    @BindView(R.id.tv_distributor_address)
    TextView tvDistributorAddress;
    @BindView(R.id.tv_distributor_range)
    TextView tvDistributorRange;
    @BindView(R.id.img_distributor_zhengjian)
    ImageView imgDistributorZhengjian;
    @BindView(R.id.lv_distributor)
    NoScrollListView lvDistributor;
    @BindView(R.id.tv_distributor_commit)
    TextView tvDistributorCommit;
    private String merId;
    List<DistributorBean.CardBean> cardBeanArrayList = new ArrayList<>();
    private AddDoucumenlistAdapter addDoucumenlistAdapter;
    private String merchants_distributor_type;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        merchants_distributor_type = parms.getString(InvoicingConstants.Merchants_Distributor_type);
        if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
            tvTitle.setText("查看进销商");
            merId = parms.getString(InvoicingConstants.Distributor_ID);

        } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            tvTitle.setText("查看进销商");
            merId = parms.getString(InvoicingConstants.Merchants_ID);
        }
        requestNetGetCommodity(merId);
        imgDistributorZhengjian.setVisibility(View.GONE);
        addDoucumenlistAdapter = new AddDoucumenlistAdapter(this, cardBeanArrayList, "see", -1);
        lvDistributor.setAdapter(addDoucumenlistAdapter);
        addDoucumenlistAdapter.notifyDataSetChanged();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_see_distributor;
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

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 分销查询
     */
    private void requestNetGetCommodity(final String merId) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.buyerstoEdit_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + merId);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("merId", merId)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息SeeDistributorActivity" + e.toString());
                            Toast.makeText(SeeDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeDistributorActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("错误信息SeeDistributorActivity" + response);
                            Gson gson = new Gson();
                            SeeDistributorBean seeDistributorBean = gson.fromJson(response, SeeDistributorBean.class);
                            if (seeDistributorBean != null) {
                                List<SeeDistributorBean.DataBean> dataBeanList = seeDistributorBean.getData();
                                List<SeeDistributorBean.DiclistBean> diclist = seeDistributorBean.getDiclist();
                                int size = dataBeanList.size();
                                int diclistsize = diclist.size();
                                if (size != 0) {
                                    SeeDistributorBean.DataBean dataBean = dataBeanList.get(0);
                                    if (dataBean != null) {
                                        SeeDistributorBean.DataBean.MeridBean meridBean = dataBean.getMerid();
                                        if (meridBean != null) {
                                            tvDistributorName.setText(meridBean.getMername() + "");
                                            tvDistributorCode.setText(meridBean.getEntregno() + "");
                                            tvDistributorUntilTimer.setText(meridBean.getEntregno() + "");
                                            String isgr = meridBean.getIsgr();
                                            if (isgr.equals("0")) {
                                                tvDistributorIsPerson.setText("否");
                                                llDistributorQq.setVisibility(View.GONE);
                                                llDistributorWeixin.setVisibility(View.GONE);
                                            } else if (isgr.equals("1")) {
                                                tvDistributorIsPerson.setText("是");
                                                tvDistributorQq.setText(meridBean.getQqnum() + "");
                                                tvDistributorWeixin.setText(meridBean.getWxnum() + "");
                                                llDistributorQq.setVisibility(View.VISIBLE);
                                                llDistributorWeixin.setVisibility(View.VISIBLE);
                                            }
                                            tvDistributorFaren.setText(meridBean.getContacts() + "");
                                            tvDistributorBarCode.setText(meridBean.getBankcard() + "");
                                            tvDistributorPhone.setText(meridBean.getContactstel() + "");
                                            tvDistributorCarNum.setText(meridBean.getCardcode() + "");
                                            tvDistributorEmail.setText(meridBean.getEmail() + "");
                                            tvDistributorAddress.setText(meridBean.getAddress() + "");
                                            tvDistributorRange.setText(meridBean.getMerscope() + "");
                                        }
                                    }
                                } else {
                                    SeeDistributorBean.ChantBean chantBean = seeDistributorBean.getChant();
                                    if (chantBean != null) {
                                        tvDistributorName.setText(chantBean.getMername() + "");
                                        tvDistributorCode.setText(chantBean.getEntregno() + "");
                                        tvDistributorUntilTimer.setText(chantBean.getEntregno() + "");
                                        String isgr = chantBean.getIsgr();
                                        if (isgr.equals("0")) {
                                            tvDistributorIsPerson.setText("否");
                                            llDistributorQq.setVisibility(View.GONE);
                                            llDistributorWeixin.setVisibility(View.GONE);
                                        } else if (isgr.equals("1")) {
                                            tvDistributorIsPerson.setText("是");
                                            tvDistributorQq.setText(chantBean.getQqnum() + "");
                                            tvDistributorWeixin.setText(chantBean.getWxnum() + "");
                                            llDistributorQq.setVisibility(View.VISIBLE);
                                            llDistributorWeixin.setVisibility(View.VISIBLE);
                                        }
                                        tvDistributorFaren.setText(chantBean.getContacts() + "");
                                        tvDistributorBarCode.setText(chantBean.getBankcard() + "");
                                        tvDistributorPhone.setText(chantBean.getContactstel() + "");
                                        tvDistributorCarNum.setText(chantBean.getCardcode() + "");
                                        tvDistributorEmail.setText(chantBean.getEmail() + "");
                                        tvDistributorAddress.setText(chantBean.getAddress() + "");
                                        tvDistributorRange.setText(chantBean.getMerscope() + "");
                                    }
                                }
                                cardBeanArrayList.clear();
                                for (int i = 0; i < size; i++) {
                                    SeeDistributorBean.DataBean dataBean = dataBeanList.get(i);
                                    if (dataBean != null) {
                                        SeeDistributorBean.DataBean.FilecardidBean filecardidBean = dataBean.getFilecardid();
                                        DistributorBean.CardBean picBean = new DistributorBean.CardBean();
                                        picBean.setMercardtype(dataBean.getMercardtype());
                                        picBean.setMercardnum(dataBean.getMercardnum());
                                        picBean.setRemark(filecardidBean.getRemark());
                                        picBean.setEnddate(dataBean.getEnddateStr());
                                        for (int j = 0; j < diclistsize; j++) {
                                            SeeDistributorBean.DiclistBean diclistBean = diclist.get(j);
                                            if (dataBean.getMercardtype().equals(diclistBean.getBUSINID() + "")) {
                                                picBean.setCardtypeName(diclistBean.getBUSINNAME());
                                            }
                                        }
                                        picBean.setImgUrl(InvoicingConstants.IMAGEURL + filecardidBean.getParentpath() + "/" + filecardidBean.getPath());
                                        cardBeanArrayList.add(picBean);
                                        addDoucumenlistAdapter.notifyDataSetChanged();
                                    }
                                }


                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeDistributorActivity" + e1.toString());
                            Toast.makeText(SeeDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
