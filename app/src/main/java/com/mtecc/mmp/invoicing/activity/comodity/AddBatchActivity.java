package com.mtecc.mmp.invoicing.activity.comodity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.BatchListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;
import com.mtecc.mmp.invoicing.views.NoScrollListView;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
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
    private Uri photoUri;
    private String name = "";
    private String pihaoNum = "";
    private String lshougj = "";
    private String jhuojia = "";
    private String pfajia = "";
    private String picPath = "";
    private String picName = "";
    private List<BatchPicBean> mlist;
    private BatchListAdapter batchListAdapter;
    private int picPosiion;
    List<String> newPathlist;
    List<BatchPicBean> newPiclist;
    private UseSystemUtils useSystemUtils;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        useSystemUtils = new UseSystemUtils(this);

        mlist = new ArrayList<>();
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("添加批次");
        BatchPicBean picBean = new BatchPicBean();
        picBean.setBatchcode("");
        picBean.setBatchcarType("");
        picBean.setBatchtimer("");
        List<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("");
        picBean.setImgUrl(imgUrlList);
        mlist.add(picBean);


        batchListAdapter = new BatchListAdapter(this, mlist, CommondityTvCommit);
        addBatchListView.setAdapter(batchListAdapter);
        batchListAdapter.notifyDataSetChanged();
        //点击图片
        batchListAdapter.setiImgOnClickListerner(new BatchListAdapter.IBatchImgOnClickListerner() {
            @Override
            public void onBatchImgClick(int position, int pos, String imgUrl, List<String> finalImgUrlList, List<BatchPicBean> mList) {
                LogUtils.d("点击图片" + position + imgUrl);
                if (TextUtils.isEmpty(imgUrl)) {
                    newPathlist = new ArrayList<>();
                    newPiclist = new ArrayList<>();
                    newPiclist.addAll(mList);
                    newPathlist.addAll(finalImgUrlList);
                    picPosiion = position;
                    picPhoto();
                } else {
                    ArrayList<String> imgUrlList = new ArrayList<String>();
                    for (int i = 0; i < finalImgUrlList.size(); i++) {
                        if (!TextUtils.isEmpty(finalImgUrlList.get(i))) {
                            imgUrlList.add(finalImgUrlList.get(i));
                        }
                    }
                    Intent intent = new Intent();
                    intent.setClass(AddBatchActivity.this, SwipeActivity.class);
                    intent.putStringArrayListExtra("imagelist", imgUrlList);
                    intent.putExtra("position", pos + "");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(AddBatchActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                }


            }

        });
        batchListAdapter.setiDelOnClickListerner(new BatchListAdapter.IBatchDelImgOnClickListerner() {
            @Override
            public void onBatchDelClick(int position, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchPicBean> mList) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddBatchActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddBatchActivity.this, false, exitView);
                exitClick(exitView, dialog, position, imgposition, finalImgUrlList, mlist);

            }
        });

        batchListAdapter.setiAddBatchOnClickListerner(new BatchListAdapter.IAddBatchOnClickListerner() {
            @Override
            public void onAddBatchClick(List<BatchPicBean> mPicimgList) {
                name = commodityDialogEtName.getText().toString().trim();
                pihaoNum = commodityDialogEtNum.getText().toString().trim();
                lshougj = commodityDialogEtLshoujia.getText().toString().trim();
                jhuojia = commodityDialogEtJhuojia.getText().toString().trim();
                pfajia = commodityDialogEtPfjia.getText().toString().trim();
                BatchBean batchBean = new BatchBean();
                batchBean.setBatchstartTimer(name);
                batchBean.setBatchnum(pihaoNum);
                batchBean.setBatchlShouji(lshougj);
                batchBean.setBatchjHuojia(jhuojia);
                batchBean.setBatchpfajia(pfajia);
                batchBean.setPicList(mPicimgList);

                //从列表添加
                Gson gson = new Gson();
                String batchJson = gson.toJson(batchBean);
                LogUtils.d("批次管理" + batchJson);


            }
        });

    }

    /**
     * 删除本地文件
     */

    public void myDeleteFile(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
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
     * 是否返回
     *
     * @param exitView
     * @param dialog
     * @param position
     * @param imgposition
     * @param finalImgUrlList
     * @param mlist
     */
    private void exitClick(View exitView, final AlertDialog dialog, final int position, final int imgposition, final List<String> finalImgUrlList, final List<BatchPicBean> mlist) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除当前图片?");
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
                showToast("已删除图片");
                newPathlist = new ArrayList<>();
                newPiclist = new ArrayList<>();
                newPiclist.addAll(mlist);
                newPathlist.addAll(finalImgUrlList);
                myDeleteFile(newPathlist.get(imgposition));
                newPathlist.remove(imgposition);
                picPosiion = position;
                BatchPicBean element = new BatchPicBean();
                element.setBatchcarType(newPiclist.get(picPosiion).getBatchcarType());
                element.setBatchcode(newPiclist.get(picPosiion).getBatchcode());
                element.setBatchtimer(newPiclist.get(picPosiion).getBatchtimer());
                element.setImgUrl(newPathlist);
                newPiclist.set(picPosiion, element);
                AddBatchActivity.this.mlist.clear();
                AddBatchActivity.this.mlist.addAll(newPiclist);
                LogUtils.d("更换的数据" + picPosiion);
                batchListAdapter.notifyDataSetChanged();

            }
        });
    }


    private void picPhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
//				requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //针对4.4以下
            SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String path = timeStampFormat2.format(new Date());
            ContentValues values2 = new ContentValues();//和hashtable类似但是只能存基本数据类型不能存对象
            values2.put(MediaStore.Images.Media.TITLE, path);
            photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values2);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            //启动照相机
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 2);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                switch (requestCode) {
                    case 2:
                        // 当选择的图片不为空的话，在获取到图片的途径
                        Uri uri = null;
                        if (data != null && data.getData() != null) {
                            uri = data.getData();
                        }
                        if (uri == null) {
                            if (photoUri != null) {
                                uri = photoUri;
                            }
                        }
                        String[] pojo = {MediaStore.Images.Media.DATA};
                        Cursor cursor = this.getContentResolver().query(uri,
                                pojo, null, null, null);
                        if (cursor != null) {
                            ContentResolver cr = this.getContentResolver();
                            int colunm_index = cursor
                                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            cursor.moveToFirst();
                            String path = cursor.getString(colunm_index);
                            // 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
                            // 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                            if (path.endsWith("jpg") || path.endsWith("png")) {
                                picPath = path;
                                //压缩
                                List<String> picPathlist = new ArrayList<>();
                                picPathlist.addAll(newPathlist);

                                picPath = CompressionPhotoUtils.compressImage(path, path, 50);
                                picPathlist.add(picPath);
                                BatchPicBean bean = new BatchPicBean();
                                bean.setBatchcarType(newPiclist.get(picPosiion).getBatchcarType());
                                bean.setBatchcode(newPiclist.get(picPosiion).getBatchcode());
                                bean.setBatchtimer(newPiclist.get(picPosiion).getBatchtimer());
                                bean.setImgUrl(picPathlist);
                                newPiclist.set(picPosiion, bean);
                                mlist.clear();
                                mlist.addAll(newPiclist);
                                LogUtils.d("更换的数据" + picPosiion);
                                batchListAdapter.notifyDataSetChanged();
                                for (int i = 0; i < picPathlist.size(); i++) {
                                    LogUtils.d("选中的图片" + picPathlist.get(i));

                                }

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
}
