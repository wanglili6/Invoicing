package com.mtecc.mmp.invoicing.activity.approve;

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
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.SwipeActivity;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApproveActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rg_select)
    RadioGroup rgSelect;
    @BindView(R.id.rb_duo_code)
    RadioButton rbDuoCode;
    @BindView(R.id.rb_putong_code)
    RadioButton rbPutongCode;
    @BindView(R.id.resgister_et_name)
    EditText resgisterEtName;
    @BindView(R.id.approve_et_faren)
    EditText approveEtFaren;
    @BindView(R.id.approve_et_code)
    EditText approveEtCode;
    @BindView(R.id.ll_ty)
    LinearLayout llTy;
    @BindView(R.id.approve_et_yy_code)
    EditText approveEtYyCode;
    @BindView(R.id.ll_yy)
    LinearLayout llYy;
    @BindView(R.id.approve_et_adress)
    EditText approveEtAdress;
    @BindView(R.id.approve_tv_valid_until)
    TextView approveTvValidUntil;
    @BindView(R.id.approve_et_range)
    EditText approveEtRange;
    @BindView(R.id.qy_tv_approve)
    TextView qyTvApprove;
    @BindView(R.id.img_zhangjian)
    ImageView imgZhengjian;

    String codeType = "";
    String qyname = "";
    String qyfaren = "";
    String qycode = "";
    String qyAddress = "";
    String qyUntilTimer = "";
    String qyrange = "";
    private UseSystemUtils userSystemutils;
    private Uri photoUri;
    private String picPath = "";
    private AlertDialog showDialog;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("企业认证");
        userSystemutils = new UseSystemUtils(this);
        View view1 = ShowDalogUtils.showCustomizeDialog(ApproveActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(ApproveActivity.this, false, view1);
        showDialog.dismiss();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_approve;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {
        approveEtCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (TextUtils.isEmpty(codeType)) {
                        showToast("请选择证件类型!");
                    }
                }

            }
        });

        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_duo_code:
                        codeType = "0";
                        llTy.setVisibility(View.VISIBLE);
                        llYy.setVisibility(View.GONE);
                        break;

                    case R.id.rb_putong_code:
                        codeType = "1";
                        llTy.setVisibility(View.GONE);
                        llYy.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.qy_tv_approve, R.id.approve_tv_valid_until, R.id.tv_select_pic, R.id.img_zhangjian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog alertDialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, alertDialog);
                break;
            case R.id.qy_tv_approve:
                //进行认证
                commanyMsg();

                break;
            case R.id.approve_tv_valid_until:
                //选择企业有效期
                if (qyUntilTimer != null) {
                    userSystemutils.useSystemTimeNoHms(approveTvValidUntil);
                }

                break;
            case R.id.tv_select_pic:
                View selectView = ShowDalogUtils.showCustomizeDialog(this, R.layout.selelct_pic_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, selectView, Gravity.BOTTOM);
                selectClick(selectView, dialog);

                break;
            case R.id.img_zhangjian:
                ArrayList<String> imgUrlList = new ArrayList<String>();
                imgUrlList.add(picPath);
                Intent intent = new Intent();
                intent.setClass(ApproveActivity.this, SwipeActivity.class);
                intent.putStringArrayListExtra("imagelist", imgUrlList);
                intent.putExtra("position", 1 + "");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ApproveActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }

                break;
        }
    }

    /**
     * 选择拍照
     *
     * @param selectView
     * @param dialog
     */
    private void selectClick(View selectView, final AlertDialog dialog) {
        TextView tvPhoto = selectView.findViewById(R.id.dialog_tv_photo);
        TextView tvXiangce = selectView.findViewById(R.id.dialog_tv_xiangce);
        TextView tvExit = selectView.findViewById(R.id.dialog_tv_exit);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //从相册选择
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型
                startActivityForResult(intent, 1);
            }
        });

        tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //拍照
                picPhoto();
            }
        });
    }

    /**
     * 企业信息
     */
    private void commanyMsg() {
        qyname = resgisterEtName.getText().toString().trim();
        qyfaren = approveEtFaren.getText().toString().trim();
        qyAddress = approveEtAdress.getText().toString().trim();
        qyUntilTimer = approveTvValidUntil.getText().toString().trim();
        qyrange = approveEtRange.getText().toString().trim();
        if (TextUtils.isEmpty(codeType)) {
            showDialog.dismiss();
            showToast("请选择证件类型!");
            return;
        } else {
            if (codeType.equals("0")) {
                qycode = approveEtCode.getText().toString().trim();
                if (qycode.length() != 18) {
                    showDialog.dismiss();
                    showToast("统一社会信用码不符合!");
                    return;
                }
            } else if (codeType.equals("1")) {
                qycode = approveEtYyCode.getText().toString().trim();
                if (qycode.length() != 15) {
                    showDialog.dismiss();
                    showToast("营业执照号不符合!");
                    return;
                }
            }
        }
        if (TextUtils.isEmpty(qyname)) {
            showDialog.dismiss();
            showToast("公司名称不能为空!");
            return;
        }
        if (TextUtils.isEmpty(qyfaren)) {
            showDialog.dismiss();
            showToast("公司法人不能为空!");
            return;
        }
        if (TextUtils.isEmpty(qycode)) {
            showDialog.dismiss();
            showToast("公司统一社会信用码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(qyAddress)) {
            showDialog.dismiss();
            showToast("公司地址不能为空!");
            return;
        }
        if (TextUtils.isEmpty(qyrange)) {
            showDialog.dismiss();
            showToast("公司范围不能为空!");
            return;
        }

        if (TextUtils.isEmpty(picPath)) {
            showDialog.dismiss();
            showToast("证件照片不能为空!");
            return;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                // 当选择的图片不为空的话，在获取到图片的途径
                Uri urix = null;
                if (data != null && data.getData() != null) {
                    urix = data.getData();
                }
                String[] pojox = {MediaStore.Images.Media.DATA};
                Cursor cursorx = this.getContentResolver().query(urix,
                        pojox, null, null, null);
                if (cursorx != null) {
                    ContentResolver cr = this.getContentResolver();
                    int colunm_index = cursorx
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursorx.moveToFirst();
                    String path = cursorx.getString(colunm_index);
                    // 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
                    // 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        //压缩
                        picPath = CompressionPhotoUtils.compressImage(path, path, 50);
                        LogUtils.d("更换的数据" + picPath);
                        Glide.with(this)
                                .load(picPath)
                                .centerCrop()
                                .into(imgZhengjian);

                    }
                }
                break;
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
                        //压缩
                        picPath = CompressionPhotoUtils.compressImage(path, path, 50);
                        LogUtils.d("更换的数据" + picPath);
                        Glide.with(this)
                                .load(picPath)
                                .centerCrop()
                                .into(imgZhengjian);

                    }
                }
                break;
        }
    }
}
