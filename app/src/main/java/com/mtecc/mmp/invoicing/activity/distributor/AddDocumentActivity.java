package com.mtecc.mmp.invoicing.activity.distributor;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.SwipeActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;
import com.mtecc.mmp.invoicing.activity.distributor.adapter.DoucumentListAdapter;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributorBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加证件
 */
public class AddDocumentActivity extends BaseActivity {

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
    @BindView(R.id.add_document_list_view)
    ListView addDocumentListView;
    @BindView(R.id.tv_distributor_commit)
    TextView tvDistributorCommit;
    private List<DistributorBean.CardBean> mlist = new ArrayList<>();
    private List<DistributorBean.CardBean> newBeanmlist;
    private DoucumentListAdapter batchListAdapter;
    private int picPosiion;
    private String pic_path;
    private String numName;
    private String doucumentType;
    private int editPostion;
    private List<DicBean.DataBean> mDiclist = new ArrayList<>();
    private String imageName;
    private DistributorBean.CardBean editCarbean;

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("添加证件");
        mlist.clear();
        mDiclist.clear();
        requestNetgetDicList();
        doucumentType = getIntent().getStringExtra(InvoicingConstants.DOUCUMENT_TYPE);
        if (doucumentType.equals(InvoicingConstants.DOUCUMENT_ADD)) {
            DistributorBean.CardBean picBean = new DistributorBean.CardBean();
            picBean.setMercardtype("");
            picBean.setCardtypeName("");
            picBean.setRemark("");
            picBean.setEnddate("");
            mlist.add(picBean);
            batchListAdapter = new DoucumentListAdapter(this, mlist, tvDistributorCommit, mDiclist, InvoicingConstants.DOUCUMENT_ADD);
            addDocumentListView.setAdapter(batchListAdapter);
            batchListAdapter.notifyDataSetChanged();
        } else if (doucumentType.equals(InvoicingConstants.DOUCUMENT_EDIT)) {
            mlist.clear();
            editPostion = parms.getInt("postion");
            editCarbean = (DistributorBean.CardBean) parms.getSerializable("cardBean");
            DistributorBean.CardBean picBean = new DistributorBean.CardBean();
            picBean.setMercardtype(editCarbean.getMercardtype());
            picBean.setCardtypeName(editCarbean.getCardtypeName());
            picBean.setRemark(editCarbean.getRemark());
            picBean.setImgUrl(editCarbean.getImgUrl());
            picBean.setImgName(editCarbean.getImgName());
            picBean.setProcardid(editCarbean.getProcardid());
            picBean.setMercardnum(editCarbean.getMercardnum());
            picBean.setEnddate(editCarbean.getEnddate());
            mlist.add(picBean);
            batchListAdapter = new DoucumentListAdapter(this, mlist, tvDistributorCommit, mDiclist, InvoicingConstants.DOUCUMENT_EDIT);
            addDocumentListView.setAdapter(batchListAdapter);
            batchListAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_add_document;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

        batchListAdapter.setiSeeOnClickListerner(new DoucumentListAdapter.IBatchSeeImgListerner() {
            @Override
            public void onBatchSee(int position, String imgUrl) {
                if (!TextUtils.isEmpty(imgUrl)) {
                    ArrayList<String> imgUrlList = new ArrayList<String>();
                    imgUrlList.add(imgUrl);
                    Intent intent = new Intent();
                    intent.setClass(AddDocumentActivity.this, SwipeActivity.class);
                    intent.putStringArrayListExtra("imagelist", imgUrlList);
                    intent.putExtra("position", 1 + "");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(AddDocumentActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                } else {
                    showToast("请先拍照,才能进行查看");
                }

            }

        });
        //拍照
        batchListAdapter.setiImgOnClickListerner(new DoucumentListAdapter.IBatchImgOnClickListerner() {
            @Override
            public void onBatchImgClick(int position, String
                    imgUrl, List<DistributorBean.CardBean> mList) {
                newBeanmlist = new ArrayList<>();
                newBeanmlist.addAll(mList);
                DistributorBean.CardBean cardBean = mList.get(position);
                numName = cardBean.getMercardnum();
                picPosiion = position;
                picPhoto();
            }
        });
//提交的
        batchListAdapter.setiAddBatchOnClickListerner(new DoucumentListAdapter.IAddBatchOnClickListerner() {
            @Override
            public void onAddBatchClick(List<DistributorBean.CardBean> mPicimgList) {
                Intent doucumentintent = new Intent();
                doucumentintent.putExtra("picImgUrlList", (Serializable) mPicimgList);
                doucumentintent.putExtra("doucumentType", doucumentType);
                doucumentintent.putExtra("editPostion", editPostion);
                doucumentintent.setClass(AddDocumentActivity.this, AddDistributorActivity.class);
                setResult(1, doucumentintent);
                finish();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.tv_distributor_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                Intent doucumentintent = new Intent();
                doucumentintent.putExtra("picImgUrlList", (Serializable) mlist);
                doucumentintent.putExtra("doucumentType", doucumentType);
                doucumentintent.putExtra("editPostion", editPostion);
                doucumentintent.setClass(AddDocumentActivity.this, AddDistributorActivity.class);
                setResult(1, doucumentintent);
                finish();
                break;
            case R.id.tv_distributor_commit:
                break;
        }
    }

    /**
     * 照相
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
        Uri photoURI = FileProvider.getUriForFile(AddDocumentActivity.this,
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
                            DistributorBean.CardBean bean = new DistributorBean.CardBean();
                            bean.setMercardtype(newBeanmlist.get(picPosiion).getMercardtype());
                            bean.setCardtypeName(newBeanmlist.get(picPosiion).getCardtypeName());
                            bean.setEnddate(newBeanmlist.get(picPosiion).getEnddate());
                            bean.setMercardnum(newBeanmlist.get(picPosiion).getMercardnum());
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
                            LogUtils.d("错误信息AddDocumentActivity" + e.toString());
                            Toast.makeText(AddDocumentActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDocumentActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息AddDocumentActivity" + response.toString());
                            Gson gson = new Gson();
                            DicBean dicBean = gson.fromJson(response, DicBean.class);
                            if (dicBean != null) {
                                List<DicBean.DataBean> data = dicBean.getData();
                                mDiclist.clear();
                                mDiclist.addAll(data);

                            } else {
                                Toast.makeText(AddDocumentActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddDocumentActivity" + e1.toString());
                            Toast.makeText(AddDocumentActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
