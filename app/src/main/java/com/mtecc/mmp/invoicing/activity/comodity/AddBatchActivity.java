package com.mtecc.mmp.invoicing.activity.comodity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
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
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.SeeBatchMsgBean;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.BatchListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.mtecc.mmp.invoicing.views.NoScrollListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加批次信息
 */
public class AddBatchActivity extends BaseActivity {

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
    @BindView(R.id.commodity_tv_commit)
    TextView CommondityTvCommit;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.batch_img_add_iteam)
    ImageView batchImgAddIteam;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.rl_add_card_list)
    RelativeLayout rlAddCardList;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.add_batch_list_view)
    NoScrollListView addBatchListView;
    @BindView(R.id.commodity_dialog_et_name)
    EditText commodityDialogEtName;
    @BindView(R.id.commodity_dialog_et_num)
    EditText commodityDialogEtNum;
    @BindView(R.id.commodity_dialog_et_lshoujia)
    EditText commodityDialogEtLshoujia;
    @BindView(R.id.commodity_dialog_et_jhuojia)
    EditText commodityDialogEtJhuojia;
    @BindView(R.id.commodity_dialog_et_pfjia)
    EditText commodityDialogEtPfjia;
    @BindView(R.id.commodity_dialog_et_remark)
    EditText commodityDialogEtRemark;
    private String name = "";
    private String pihaoNum = "";
    private String lshougj = "";
    private String jhuojia = "";
    private String pfajia = "";
    private String picPath = "";
    private String picName = "";
    private List<BatchPicListBean.CardBean> mlist;
    private List<DicBean.DataBean> mDiclist = new ArrayList<>();
    private BatchListAdapter batchListAdapter;
    private int picPosiion;
    List<BatchPicListBean.CardBean> newBeanmlist;
    //    List<BatchPicListBean.BatchListBean.PicListBean> newPiclist;
    private UseSystemUtils useSystemUtils;
    private String batchType;
    private String imageName;
    private String pic_path;
    private String numName;//图片名字
    private String mimgurl;
    private AlertDialog showDialog;
    private String goodsId;
    private String pbatchid;
    private int cid;
    private String user_id;


    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        useSystemUtils = new UseSystemUtils(this);
        mlist = new ArrayList<>();
        ivBack.setVisibility(View.VISIBLE);
        parms = getIntent().getExtras();
        batchType = parms.getString(InvoicingConstants.BATCH_TYPE);
        View view1 = ShowDalogUtils.showCustomizeDialog(AddBatchActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddBatchActivity.this, false, view1);
        showDialog.dismiss();
        requestNetgetDicList();
        if (batchType.equals(InvoicingConstants.BATCH_ADD)) {
            tvTitle.setText("添加批次");
            BatchPicListBean.CardBean picBean = new BatchPicListBean.CardBean();
            picBean.setCardnum("");
            picBean.setCardtype("");
            picBean.setCharddate("");
            mlist.add(picBean);
            goodsId = parms.getString(InvoicingConstants.COMMODITY_Id);

        } else if (batchType.equals(InvoicingConstants.BATCH_Edit)) {
            tvTitle.setText("编辑批次");
            pbatchid = parms.getString("pbatchid");
            requestNetSeeBatch(pbatchid);
        }


        batchListAdapter = new BatchListAdapter(this, mlist, CommondityTvCommit, mDiclist, rlAddCardList, batchImgAddIteam);
        addBatchListView.setAdapter(batchListAdapter);
        batchListAdapter.notifyDataSetChanged();

        batchListAdapter.setiSeeOnClickListerner(new BatchListAdapter.IBatchSeeImgListerner() {
            @Override
            public void onBatchSee(int position, String imgUrl) {
                if (!TextUtils.isEmpty(imgUrl)) {
                    ArrayList<String> imgUrlList = new ArrayList<String>();
                    imgUrlList.add(imgUrl);
                    Intent intent = new Intent();
                    intent.setClass(AddBatchActivity.this, SwipeActivity.class);
                    intent.putStringArrayListExtra("imagelist", imgUrlList);
                    intent.putExtra("position", 1 + "");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(AddBatchActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                } else {
                    showToast("请先拍照,才能进行查看");
                }

            }

        });
        batchListAdapter.setiImgOnClickListerner(new BatchListAdapter.IBatchImgOnClickListerner() {
            @Override
            public void onBatchImgClick(int position, String
                    imgUrl, List<BatchPicListBean.CardBean> mList) {
                mimgurl = imgUrl;
                newBeanmlist = new ArrayList<>();
                newBeanmlist.addAll(mList);
                BatchPicListBean.CardBean cardBean = mList.get(position);
                numName = cardBean.getCardnum();
                picPosiion = position;
                picPhoto();
            }
        });


        batchListAdapter.setiAddBatchOnClickListerner(new BatchListAdapter.IAddBatchOnClickListerner() {
            @Override
            public void onAddBatchClick(final List<BatchPicListBean.CardBean> mPicimgList) {

                requestCommit(mPicimgList);


            }

        });
        if (mlist == null || mlist.size() == 0) {
            CommondityTvCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestCommit(null);
                }
            });
        }
    }

    private void requestCommit(List<BatchPicListBean.CardBean> mPicimgList) {
        name = commodityDialogEtName.getText().toString().trim();
        pihaoNum = commodityDialogEtNum.getText().toString().trim();
        lshougj = commodityDialogEtLshoujia.getText().toString().trim();
        jhuojia = commodityDialogEtJhuojia.getText().toString().trim();
        pfajia = commodityDialogEtPfjia.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showDialog.dismiss();
            showToast("生产日期不能为空");
            return;
        }
        BatchPicListBean batchPicListBean = new BatchPicListBean();
        batchPicListBean.setBatchdate(name);
        batchPicListBean.setBatchnum(pihaoNum);
        batchPicListBean.setSaleprice(pfajia);
        batchPicListBean.setSellprice(lshougj);
        batchPicListBean.setEnterprice(jhuojia);
        List<BatchPicListBean.CardBean> mselectPicimgList = new ArrayList<>();
        Map<String, String> imgMap = new HashMap<String, String>();
        if (mPicimgList != null) {
            int size = mPicimgList.size();
            for (int i = 0; i < size; i++) {
                BatchPicListBean.CardBean cardBean = mPicimgList.get(i);
                if (!TextUtils.isEmpty(cardBean.getCardnum())) {
                    if (!TextUtils.isEmpty(cardBean.getImgUrl())) {
                        imgMap.put(cardBean.getCardnum(), cid + user_id + cardBean.getCardnum() + ".png");
                    }
                    mselectPicimgList.add(cardBean);
                }
            }

            batchPicListBean.setpicmap(imgMap);
            batchPicListBean.setCardBeanlist(mselectPicimgList);
        } else {
            mPicimgList = new ArrayList<>();
            batchPicListBean.setCardBeanlist(mPicimgList);
        }

//                //从列表添加
        Gson gson = new Gson();
        if (batchType.equals(InvoicingConstants.BATCH_ADD)) {
            String batchJson = gson.toJson(batchPicListBean);
            LogUtils.d("批次管理" + batchJson);
            requestNetAddCommodity(batchJson, goodsId, mPicimgList);

        } else if (batchType.equals(InvoicingConstants.BATCH_Edit)) {
            batchPicListBean.setPbatchid(Integer.valueOf(pbatchid));
            String batchJson = gson.toJson(batchPicListBean);
            LogUtils.d("批次管理" + batchJson);
            requestNeteditCommodity(batchJson, mPicimgList);
        }
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_add_batch;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(final Context mContext) {

    }

    /**
     * 拍照
     */
    private void picPhoto() {
        Intent intent = new Intent();
        // 指定开启系统相机的Action
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //获取当前时间
        long date = System.currentTimeMillis();
        Date curDate = new Date(date);
        String str = formatter.format(curDate);
        String pic_pathFileName = str + numName + ".png";
        imageName = numName + "";
        // 根据文件地址创建文件
        pic_path = Environment.getExternalStorageDirectory().toString() + "/mtecc/invoing/" + pic_pathFileName;
        File file = new File(pic_path);
        // 把文件地址转换成Uri格式
        Uri photoURI = FileProvider.getUriForFile(AddBatchActivity.this,
                getApplicationContext().getPackageName() + ".fileProvider",
                file);
        // 设置系统相机拍摄照片完成后图片文件的存放地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        startActivityForResult(intent, 2);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                switch (requestCode) {
                    case 2:
                        if (resultCode == RESULT_OK) {
                            LogUtils.i("onActivityResult: RESULT_OK " + pic_path);
                            String imagepath = CompressionPhotoUtils.compressImage(pic_path, pic_path, 50);
                            LogUtils.i("onActivityResult: RESULT_OK " + imagepath);
                            LogUtils.i("onActivityResult: RESULT_OK " + imageName);
                            BatchPicListBean.CardBean bean = new BatchPicListBean.CardBean();
                            bean.setCardtype(newBeanmlist.get(picPosiion).getCardtype());
                            bean.setCardtypeName(newBeanmlist.get(picPosiion).getCardtypeName());
                            bean.setCharddate(newBeanmlist.get(picPosiion).getCharddate());
                            bean.setCardnum(newBeanmlist.get(picPosiion).getCardnum());
                            bean.setRemark(newBeanmlist.get(picPosiion).getRemark());
                            bean.setImgUrl(imagepath);
                            newBeanmlist.set(picPosiion, bean);
                            mlist.clear();
                            mlist.addAll(newBeanmlist);
                            LogUtils.d("更换的数据" + picPosiion);
                            batchListAdapter.notifyDataSetChanged();
                        } else {
                            LogUtils.i("onActivityResult: " + pic_path);
                            File file = new File(pic_path);
                            if (!file.exists()) {
                                imageName = "";
                            }

                        }
                        break;
                }

            } catch (Exception e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick({R.id.rl_back, R.id.commodity_dialog_et_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialog);
                break;
            case R.id.commodity_dialog_et_name:
                useSystemUtils.useSystemTimeNoHms(commodityDialogEtName);
                break;
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
                //TODO:返回页面
                finish();


            }
        });
    }

    /**
     * 获取证件信息
     */
    private void requestNetgetDicList() {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.getDic_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息AddBatchActivity" + e.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddBatchActivity" + response.toString());
                            Gson gson = new Gson();
                            DicBean dicBean = gson.fromJson(response, DicBean.class);
                            if (dicBean != null) {
                                List<DicBean.DataBean> data = dicBean.getData();
                                mDiclist.clear();
                                mDiclist.addAll(data);

                            } else {
                                Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 添加批次
     */
    private void requestNetAddCommodity(String batchBean, String goodid, List<BatchPicListBean.CardBean> mPicimgList) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.addBatch_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + goodid);
        LogUtils.d("登陆的url" + batchBean);
        for (int i = 0; i < mPicimgList.size(); i++) {
            BatchPicListBean.CardBean cardBean = mPicimgList.get(i);
            String imgUrl = cardBean.getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                post.addFile(cardBean.getCardnum(), cid + user_id + cardBean.getCardnum() + ".png", new File(imgUrl));
            }
        }
        post.addParams("batchBean", batchBean);
        post.addParams("goodid", goodid);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddBatchActivity" + e.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddBatchActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加批次成功!");
                                    finish();
                                } else {
                                    showToast("添加批次失败!");
                                }
                            } else {
                                Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 修改批次
     */
    private void requestNeteditCommodity(String batchBean, List<BatchPicListBean.CardBean> mPicimgList) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.editBatch_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + batchBean);
        if (mPicimgList != null) {
            for (int i = 0; i < mPicimgList.size(); i++) {
                BatchPicListBean.CardBean cardBean = mPicimgList.get(i);
                String imgUrl = cardBean.getImgUrl();
                if (!TextUtils.isEmpty(imgUrl)) {
                    String substring = imgUrl.substring(0, 4);
                    if (!substring.equals("http")) {
                        post.addFile(cardBean.getCardnum(), cid + user_id + cardBean.getCardnum() + ".png", new File(cardBean.getImgUrl()));
                    }
                }

            }
        }
        post.addParams("batchBean", batchBean);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddBatchActivity" + e.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddBatchActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("修改批次成功!");
                                    finish();
                                } else {
                                    showToast("修改批次失败!");
                                }
                            } else {
                                Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 查看批次详情
     */
    private void requestNetSeeBatch(String pbatchid) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.toEditBatch_URL;
        PostFormBuilder post = OkHttpUtils.post();

        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + pbatchid);

        post.addParams("pbatchid", pbatchid);
        post.tag(this)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddBatchActivity" + e.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddBatchActivity" + response.toString());
                            Gson gson = new Gson();
                            SeeBatchMsgBean seeBatchMsgBean = gson.fromJson(response, SeeBatchMsgBean.class);
                            if (seeBatchMsgBean != null) {
                                SeeBatchMsgBean.BatchBean batchBean = seeBatchMsgBean.getBatch();
                                commodityDialogEtName.setText(batchBean.getBatchdateStr());
                                commodityDialogEtNum.setText(batchBean.getBatchnum());
                                commodityDialogEtLshoujia.setText(batchBean.getSellprice());
                                commodityDialogEtJhuojia.setText(batchBean.getEnterprice());
                                commodityDialogEtPfjia.setText(batchBean.getSaleprice());
                                commodityDialogEtRemark.setText(batchBean.getRemark());
                                List<SeeBatchMsgBean.CardListBean> cardList = seeBatchMsgBean.getCardList();
                                List<SeeBatchMsgBean.DiclistBean> diclist = seeBatchMsgBean.getDiclist();
                                int size = cardList.size();
                                mlist.clear();
                                if (size > 0) {
                                    for (int i = 0; i < size; i++) {
                                        SeeBatchMsgBean.CardListBean cardListBean = cardList.get(i);
                                        BatchPicListBean.CardBean picBean = new BatchPicListBean.CardBean();
                                        picBean.setCardnum(cardListBean.getCardnum());
                                        int diclistsize = diclist.size();
                                        for (int j = 0; j < diclistsize; j++) {
                                            if (cardListBean.getCardtype().equals(diclist.get(j).getBUSINID() + "")) {
                                                picBean.setCardtypeName(diclist.get(j).getBUSINNAME());
                                            }
                                        }
                                        picBean.setCharddate(cardListBean.getCharddateStr());
                                        picBean.setRemark(cardListBean.getRemark());
                                        SeeBatchMsgBean.CardListBean.FilecardidBean filecardid = cardListBean.getFilecardid();
                                        if (filecardid != null) {
                                            picBean.setImgUrl(InvoicingConstants.IMAGEURL + cardListBean.getFilecardid().getParentpath() + "/" + cardListBean.getFilecardid().getPath());
                                        }
                                        mlist.add(picBean);
                                        batchListAdapter.notifyDataSetChanged();
                                    }
                                } else if (size == 0) {
                                    if (mlist.size() == 0) {
                                        rlAddCardList.setVisibility(View.VISIBLE);
                                        batchImgAddIteam.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                BatchPicListBean.CardBean picBean = new BatchPicListBean.CardBean();
                                                mlist.add(picBean);
                                                batchListAdapter.notifyDataSetChanged();
                                                if (mlist.size() == 0) {
                                                    rlAddCardList.setVisibility(View.VISIBLE);
                                                } else {
                                                    rlAddCardList.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                                    } else {
                                        rlAddCardList.setVisibility(View.GONE);
                                    }

                                }

                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddBatchActivity" + e1.toString());
                            Toast.makeText(AddBatchActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
