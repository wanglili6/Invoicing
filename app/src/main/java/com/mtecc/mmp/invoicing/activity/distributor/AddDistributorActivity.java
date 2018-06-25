package com.mtecc.mmp.invoicing.activity.distributor;

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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributorBean;
import com.mtecc.mmp.invoicing.activity.distributor.bean.SeeDistributorBean;
import com.mtecc.mmp.invoicing.activity.distributor.adapter.AddDoucumenlistAdapter;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加分销商/进货商
 */
public class AddDistributorActivity extends BaseActivity {

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
    @BindView(R.id.et_distributor_name)
    EditText etDistributorName;
    @BindView(R.id.et_distributor_code)
    EditText etDistributorCode;
    @BindView(R.id.radio_btn_yes)
    RadioButton radioYes;
    @BindView(R.id.radio_btn_no)
    RadioButton radioNo;
    @BindView(R.id.radio_group_genre)
    RadioGroup radioGroupGenre;
    @BindView(R.id.commodity_et_qq)
    EditText commodityEtQq;
    @BindView(R.id.ll_distributor_qq)
    LinearLayout llDistributorQq;
    @BindView(R.id.commodity_et_wexin)
    EditText commodityEtWexin;
    @BindView(R.id.ll_distributor_weixin)
    LinearLayout llDistributorWeixin;
    @BindView(R.id.et_distributor_faren)
    EditText etDistributorFaren;
    @BindView(R.id.et_distributor_bar_code)
    EditText etDistributorBarCode;
    @BindView(R.id.et_distributor_phone)
    EditText etDistributorPhone;
    @BindView(R.id.et_distributor_car_num)
    EditText etDistributorCarNum;
    @BindView(R.id.et_distributor_email)
    EditText etDistributorEmail;
    @BindView(R.id.et_distributor_address)
    EditText etDistributorAddress;
    @BindView(R.id.et_distributor_range)
    EditText etDistributorRange;
    @BindView(R.id.et_distributor_until_timer)
    EditText etDistributoruntilTimer;
    @BindView(R.id.lv_distributor)
    ListView lvDistributor;
    @BindView(R.id.tv_distributor_commit)
    TextView tvDistributorCommit;
    String isPerson = "";
    String name = "";
    String id = "";
    String code = "";
    String qq = "";
    String wexin = "";
    String faren = "";
    String barCode = "";
    String carCode = "";
    String phone = "";
    String email = "";
    String address = "";
    String range = "";
    String untileTimer = "";
    List<DistributorBean.CardBean> doucumentList = new ArrayList<>();
    List<DistributorBean.CardBean> getdoucumentList = new ArrayList<>();
    private AddDoucumenlistAdapter adapter;
    private AlertDialog showDialog;
    UseSystemUtils useSystemUtils = new UseSystemUtils(this);
    private String distributorType;
    private int cid;
    private String merId;
    private String user_id;
    private String merchants_distributor_type;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        merchants_distributor_type = parms.getString(InvoicingConstants.Merchants_Distributor_type);
        if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
            distributorType = parms.getString(InvoicingConstants.Distributor_TYPE);
            if (distributorType.equals(InvoicingConstants.Distributor_ADD)) {
                tvTitle.setText("添加分销商");

            } else if (distributorType.equals(InvoicingConstants.Distributor_EDIT)) {
                tvTitle.setText("修改分销商");
                merId = parms.getString(InvoicingConstants.Distributor_ID);
                requestNetSeeDistribution(merId);
            }
            adapter = new AddDoucumenlistAdapter(this, doucumentList, "add", -1);
            lvDistributor.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
                distributorType = parms.getString(InvoicingConstants.Merchants_TYPE);
                if (distributorType.equals(InvoicingConstants.Merchants_ADD)) {
                    tvTitle.setText("添加供货商");

                } else if (distributorType.equals(InvoicingConstants.Merchants_EDIT)) {
                    tvTitle.setText("修改供货商");
                    merId = parms.getString(InvoicingConstants.Merchants_ID);
                    requestNetSeeDistribution(merId);
                }
                adapter = new AddDoucumenlistAdapter(this, doucumentList, "add", -1);
                lvDistributor.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }


        View view1 = ShowDalogUtils.showCustomizeDialog(this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(this, false, view1);
        showDialog.dismiss();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_add_distributor;
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
                    case R.id.radio_btn_yes:
                        isPerson = "1";
                        llDistributorQq.setVisibility(View.VISIBLE);
                        llDistributorWeixin.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radio_btn_no:
                        isPerson = "0";
                        llDistributorQq.setVisibility(View.GONE);
                        llDistributorWeixin.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.img_distributor_zhengjian, R.id.tv_distributor_commit, R.id.et_distributor_until_timer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.img_distributor_zhengjian:
                //添加证件
                Intent doucumentintent = new Intent();
                doucumentintent.putExtra(InvoicingConstants.DOUCUMENT_TYPE, InvoicingConstants.DOUCUMENT_ADD);
                doucumentintent.setClass(AddDistributorActivity.this, AddDocumentActivity.class);
                startActivityForResult(doucumentintent, 1);
                break;
            case R.id.tv_distributor_commit:
                //提交分销商
                showDialog.show();
                commitDistributor();
                break;
            case R.id.et_distributor_until_timer:
                //提交分销商
                useSystemUtils.useSystemTimeNoHms(etDistributoruntilTimer);
                break;
        }
    }

    /**
     * 提交分销商
     */
    private void commitDistributor() {
        name = etDistributorName.getText().toString().trim();
        code = etDistributorCode.getText().toString().trim();
        qq = commodityEtQq.getText().toString().trim();
        wexin = commodityEtWexin.getText().toString().trim();
        faren = etDistributorFaren.getText().toString().trim();
        barCode = etDistributorBarCode.getText().toString().trim();
        phone = etDistributorPhone.getText().toString().trim();
        carCode = etDistributorCarNum.getText().toString().trim();
        email = etDistributorEmail.getText().toString().trim();
        address = etDistributorAddress.getText().toString().trim();
        range = etDistributorRange.getText().toString().trim();
        untileTimer = etDistributoruntilTimer.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showDialog.dismiss();
            showToast("商户名称不能为空!");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            showDialog.dismiss();
            showToast("统一社会信用码不能为空!");
            return;
        }

        if (TextUtils.isEmpty(isPerson)) {
            showDialog.dismiss();
            showToast("是否为个人不能为空!");
            return;
        }
        if (TextUtils.isEmpty(faren)) {
            showDialog.dismiss();
            showToast("法人不能为空!");
            return;
        }
        if (TextUtils.isEmpty(barCode)) {
            showDialog.dismiss();
            showToast("身份证不能为空!");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showDialog.dismiss();
            showToast("联系电话不能为空!");
            return;
        }

        DistributorBean distributorBean = new DistributorBean();
        distributorBean.setMername(name);
        distributorBean.setEntregno(code);
        distributorBean.setIsgr(isPerson);
        if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
            distributorBean.setMertype(InvoicingConstants.Distributor_mertype);
        } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            distributorBean.setMertype(InvoicingConstants.Merchants_mertype);
        }
        distributorBean.setQqnum(qq);

        distributorBean.setCid(cid);
        distributorBean.setWxnum(wexin);
        distributorBean.setContacts(faren);
        distributorBean.setCardcode(carCode);
        distributorBean.setBankcard(barCode);
        distributorBean.setEmail(email);
        distributorBean.setContactstel(phone);
        distributorBean.setAddress(address);
        distributorBean.setMerscope(range);
        distributorBean.setCardBeanList(doucumentList);
        distributorBean.setCreateuser(Integer.valueOf(user_id));
        Map<String, String> imgMap = new HashMap<String, String>();
        int size = doucumentList.size();
        for (int i = 0; i < size; i++) {
            DistributorBean.CardBean cardBean = doucumentList.get(i);
            imgMap.put(cardBean.getMercardnum(), cid + user_id + cardBean.getMercardnum() + ".png");
        }

        for (int i = 0; i < doucumentList.size(); i++) {
            DistributorBean.CardBean cardBean = doucumentList.get(i);
            String imgUrl = cardBean.getImgUrl();
            String substring = imgUrl.substring(0, 4);
            if (substring.equals("http")) {
                for (int j = 0; j < getdoucumentList.size(); j++) {
                    DistributorBean.CardBean cardBean1 = getdoucumentList.get(j);
                    String procardid = cardBean.getProcardid() + "";
                    if (procardid.equals(cardBean1.getProcardid() + "")) {
                        imgMap.put(cardBean.getMercardnum(), cardBean1.getImgName());
                    }
                }
            }
        }
        distributorBean.setPicmap(imgMap);
        Gson gson = new Gson();

        if (merchants_distributor_type.equals(InvoicingConstants.Distributor_TYPE)) {
            if (distributorType.equals(InvoicingConstants.Distributor_ADD)) {
                String distributorJson = gson.toJson(distributorBean);
                LogUtils.i("提交分销商" + distributorJson);
                requestNetAddDistribution(distributorJson, doucumentList);

            } else if (distributorType.equals(InvoicingConstants.Distributor_EDIT)) {
                distributorBean.setMerid(Integer.valueOf(merId));
                String distributorJson = gson.toJson(distributorBean);
                LogUtils.i("提交分销商" + distributorJson);
                requestNetEditDistribution(distributorJson, doucumentList);
            }
        } else if (merchants_distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            if (distributorType.equals(InvoicingConstants.Merchants_ADD)) {
                String distributorJson = gson.toJson(distributorBean);
                LogUtils.i("提交分销商" + distributorJson);
                requestNetAddDistribution(distributorJson, doucumentList);

            } else if (distributorType.equals(InvoicingConstants.Merchants_EDIT)) {
                distributorBean.setMerid(Integer.valueOf(merId));
                String distributorJson = gson.toJson(distributorBean);
                LogUtils.i("提交分销商" + distributorJson);
                requestNetEditDistribution(distributorJson, doucumentList);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    String doucumentType = data.getStringExtra("doucumentType");
                    int editPostion = data.getIntExtra("editPostion", -2);
                    List<DistributorBean.CardBean> picImgUrlList = (List<DistributorBean.CardBean>) data.getSerializableExtra("picImgUrlList");
                    if (picImgUrlList != null) {
                        if (picImgUrlList.size() == 1) {
                            String enddate = picImgUrlList.get(0).getEnddate();
                            if (!TextUtils.isEmpty(enddate)) {
                                if (doucumentType.equals(InvoicingConstants.DOUCUMENT_ADD)) {
                                    doucumentList.addAll(picImgUrlList);
                                    adapter.notifyDataSetChanged();
                                } else if (doucumentType.equals(InvoicingConstants.DOUCUMENT_EDIT)) {
                                    doucumentList.set(editPostion, picImgUrlList.get(0));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            if (doucumentType.equals(InvoicingConstants.DOUCUMENT_ADD)) {
                                doucumentList.addAll(picImgUrlList);
                                adapter.notifyDataSetChanged();
                            } else if (doucumentType.equals(InvoicingConstants.DOUCUMENT_EDIT)) {
                                doucumentList.set(editPostion, picImgUrlList.get(0));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }


                }
                break;
        }
    }

    /**
     * 添加批次
     */
    private void requestNetAddDistribution(String merchantBean, List<DistributorBean.CardBean> doucumentList) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.buyersAdd_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        for (int i = 0; i < doucumentList.size(); i++) {
            DistributorBean.CardBean cardBean = doucumentList.get(i);
            String imgUrl = cardBean.getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                post.addFile(cardBean.getMercardnum(), cid + user_id + cardBean.getMercardnum() + ".png", new File(cardBean.getImgUrl()));

            }
        }
        post.addParams("merchantBean", merchantBean);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddDistributorActivity" + e.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddDistributorActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加成功!");
                                    finish();
                                } else {
                                    showToast("添加失败!");
                                }
                            } else {
                                Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 编辑批次
     */
    private void requestNetEditDistribution(String merchantBean, List<DistributorBean.CardBean> doucumentList) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.buyersEdit_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        for (int i = 0; i < doucumentList.size(); i++) {
            DistributorBean.CardBean cardBean = doucumentList.get(i);
            String imgUrl = cardBean.getImgUrl();

            if (!TextUtils.isEmpty(imgUrl)) {
                String substring = imgUrl.substring(0, 4);
                if (!substring.equals("http")) {
                    post.addFile(cardBean.getMercardnum(), cid + user_id + cardBean.getMercardnum() + ".png", new File(cardBean.getImgUrl()));
                }
            }

        }
        post.addParams("merchantBean", merchantBean);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddDistributorActivity" + e.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddDistributorActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("修改成功!");
                                    finish();
                                } else {
                                    showToast("修改失败!");
                                }
                            } else {
                                Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 分销查询
     */
    private void requestNetSeeDistribution(final String merId) {
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
                            LogUtils.d("错误信息AddDistributorActivity" + e.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("错误信息AddDistributorActivity" + response);
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
                                            etDistributorName.setText(meridBean.getMername() + "");
                                            etDistributorCode.setText(meridBean.getEntregno() + "");
                                            String isgr = meridBean.getIsgr();
                                            if (isgr.equals("0")) {
                                                radioYes.setChecked(false);
                                                radioNo.setChecked(true);
                                                llDistributorQq.setVisibility(View.GONE);
                                                llDistributorWeixin.setVisibility(View.GONE);
                                            } else if (isgr.equals("1")) {
                                                radioYes.setChecked(true);
                                                radioNo.setChecked(false);
                                                commodityEtQq.setText(meridBean.getQqnum() + "");
                                                commodityEtWexin.setText(meridBean.getWxnum() + "");
                                                llDistributorQq.setVisibility(View.VISIBLE);
                                                llDistributorWeixin.setVisibility(View.VISIBLE);
                                            }
                                            etDistributorFaren.setText(meridBean.getContacts() + "");
                                            etDistributorBarCode.setText(meridBean.getBankcard() + "");
                                            etDistributorPhone.setText(meridBean.getContactstel() + "");
                                            etDistributorCarNum.setText(meridBean.getCardcode() + "");
                                            etDistributorEmail.setText(meridBean.getEmail() + "");
                                            etDistributorAddress.setText(meridBean.getAddress() + "");
                                            etDistributorRange.setText(meridBean.getMerscope() + "");
                                        }
                                    }
                                } else {
                                    SeeDistributorBean.ChantBean chantBean = seeDistributorBean.getChant();
                                    if (chantBean != null) {
                                        etDistributorName.setText(chantBean.getMername() + "");
                                        etDistributorCode.setText(chantBean.getEntregno() + "");
                                        String isgr = chantBean.getIsgr();
                                        if (isgr.equals("0")) {
                                            radioYes.setChecked(false);
                                            radioNo.setChecked(true);
                                            llDistributorQq.setVisibility(View.GONE);
                                            llDistributorWeixin.setVisibility(View.GONE);
                                        } else if (isgr.equals("1")) {
                                            radioYes.setChecked(true);
                                            radioNo.setChecked(false);
                                            commodityEtQq.setText(chantBean.getQqnum() + "");
                                            commodityEtWexin.setText(chantBean.getWxnum() + "");
                                            llDistributorQq.setVisibility(View.VISIBLE);
                                            llDistributorWeixin.setVisibility(View.VISIBLE);
                                        }
                                        etDistributorFaren.setText(chantBean.getContacts() + "");
                                        etDistributorBarCode.setText(chantBean.getBankcard() + "");
                                        etDistributorPhone.setText(chantBean.getContactstel() + "");
                                        etDistributorCarNum.setText(chantBean.getCardcode() + "");
                                        etDistributorEmail.setText(chantBean.getEmail() + "");
                                        etDistributorAddress.setText(chantBean.getAddress() + "");
                                        etDistributorRange.setText(chantBean.getMerscope() + "");
                                    }
                                }
                                doucumentList.clear();
                                getdoucumentList.clear();
                                for (int i = 0; i < size; i++) {
                                    SeeDistributorBean.DataBean dataBean = dataBeanList.get(i);
                                    if (dataBean != null) {
                                        SeeDistributorBean.DataBean.FilecardidBean filecardidBean = dataBean.getFilecardid();
                                        DistributorBean.CardBean picBean = new DistributorBean.CardBean();
                                        picBean.setProcardid(dataBean.getMercardid());
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
                                        picBean.setImgName(filecardidBean.getPath());
                                        doucumentList.add(picBean);
                                        getdoucumentList.add(picBean);
                                        adapter = new AddDoucumenlistAdapter(AddDistributorActivity.this, doucumentList, "add", -1);
                                        lvDistributor.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                }


                            }

                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDistributorActivity" + e1.toString());
                            Toast.makeText(AddDistributorActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
