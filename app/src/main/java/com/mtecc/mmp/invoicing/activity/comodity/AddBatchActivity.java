package com.mtecc.mmp.invoicing.activity.comodity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.BatchListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;

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
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.add_batch_list_view)
    ListView addBatchListView;
    private Uri photoUri;
    private String picPath;
    private String picName;
    private List<BatchBean> mlist;
    private BatchListAdapter batchListAdapter;
    private int onClickPosiion;
    List<String> newPathlist;
    List<BatchBean> newlist;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("添加批次");
        mlist = new ArrayList<>();
        BatchBean batchBean = new BatchBean();
        batchBean.setBatchName("");
        List<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("");
        batchBean.setImgUrl(imgUrlList);
        mlist.add(batchBean);
        batchListAdapter = new BatchListAdapter(this, mlist);
        addBatchListView.setAdapter(batchListAdapter);
        batchListAdapter.notifyDataSetChanged();
        batchListAdapter.setiImgOnClickListerner(new BatchListAdapter.IBatchImgOnClickListerner() {
            @Override
            public void onBatchImgClick(int position, int pos, String imgUrl, List<String> finalImgUrlList, List<BatchBean> mList) {
                LogUtils.d("点击图片" + position + imgUrl);
                if (TextUtils.isEmpty(imgUrl)) {
                    newPathlist = new ArrayList<>();
                    newlist = new ArrayList<>();
                    newlist.addAll(mList);
                    newPathlist.addAll(finalImgUrlList);
                    onClickPosiion = position;
                    picPhoto();
                } else {
                    showToast("放大图片");
                }


            }

        });
        batchListAdapter.setiDelOnClickListerner(new BatchListAdapter.IBatchDelImgOnClickListerner() {
            @Override
            public void onBatchDelClick(int pos, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchBean> mList) {
                showToast("删除图片");
            }
        });

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
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

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
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
                                //压缩照片
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = false;
                                options.inSampleSize = 8;
                                Bitmap bitmap = BitmapFactory.decodeStream(
                                        cr.openInputStream(uri), null, options);
                                //压缩
                                List<String> picPathlist = new ArrayList<>();
                                picPathlist.addAll(newPathlist);

                                picPath = CompressionPhotoUtils.compressImage(path, path, 50);
                                picPathlist.add(picPath);
                                BatchBean element = new BatchBean();
                                element.setImgUrl(picPathlist);
                                newlist.set(onClickPosiion, element);
                                mlist.clear();
                                mlist.addAll(newlist);
                                LogUtils.d("更换的数据" + onClickPosiion);
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


}
