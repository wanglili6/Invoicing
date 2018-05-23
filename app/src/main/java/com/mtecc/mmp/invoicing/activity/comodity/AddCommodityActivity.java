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
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.SaoMaActivity;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.ImgListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.SelectGoodsAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityExistedBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.GoodsTypeBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.SeeCommodityBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.CompressionPhotoUtils;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.views.NoScrollRecycleView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加商品
 */
public class AddCommodityActivity extends BaseActivity {


    String genre = "";//类型
    String name = "";//名字
    String code = "";//编码
    String barcode = "";//条形码
    String type = "";//类别
    String guige = "";//规格
    String danwei = "";//单位
    String baozhiqi = "";//保质期
    String bz = "";//保质期
    String trademark = "";//保质期
    String state = "";//保质期


    String commodityUser = "";//生产商
    String commodityUserAddress = "";//生产商地址
    String commodityUserCode = "";//生产商许可号
    String commodityUserbiao = "";//生产商标
    String grade = "";//类别等级
    String parentCode = "";//上一级的code
    String parentCode3 = "";//上一级的code
    String typeCode1 = "";//类别code
    String typeCode2 = "";//类别code
    String typeCode3 = "";//类别code
    String typeCode = "";//类别code
    String bzqdanwei = "";//类别code

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
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.commodity_et_bar_code)
    EditText commodityEtBarCode;
    @BindView(R.id.commodity_img_bar_code)
    ImageView commodityImgBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout llBarCode;
    @BindView(R.id.spinner_type1)
    Spinner spinnerType1;
    @BindView(R.id.spinner_type2)
    Spinner spinnerType2;
    @BindView(R.id.spinner_type3)
    Spinner spinnerType3;
    @BindView(R.id.spinner_bzq)
    Spinner spinnerBZQ;
    @BindView(R.id.commodity_et_guige)
    EditText commodityEtGuige;
    @BindView(R.id.commodity_et_danwei)
    EditText commodityEtDanwei;
    @BindView(R.id.commodity_et_trademark)
    EditText commodityEtTrademark;
    @BindView(R.id.commodity_et_baozhiqi)
    EditText commodityEtBaozhiqi;
    @BindView(R.id.commodity_et_start)
    EditText commodityEtStart;
    @BindView(R.id.commodity_et_start_biao)
    EditText commodityEtStartBiao;
    @BindView(R.id.commodity_et_start_address)
    EditText commodityEtStartAddress;
    @BindView(R.id.commodity_et_start_code)
    EditText commodityEtStartCode;
    @BindView(R.id.commodity_tv_commit)
    TextView commodityTvCommit;
    @BindView(R.id.commodity_et_bz)
    TextView commodityetBz;
    @BindView(R.id.recycle_list_pic)
    NoScrollRecycleView recycleListPic;

    private String commodityType;
    private AlertDialog showDialog;
    List<BatchBean> mBacthlist = new ArrayList<>();
    private List<String> goodsType1List = new ArrayList<>();//食品分类第一个
    private List<String> goodsType2List = new ArrayList<>();//食品分类第一个
    private List<String> goodsType3List = new ArrayList<>();//食品分类第一个

    private List<GoodsTypeBean.SplbListBean> goodsTypeBean1List = new ArrayList<>();//食品分类第一个
    private List<GoodsTypeBean.SplbListBean> goodsTypeBean2List = new ArrayList<>();//食品分类第一个
    private List<GoodsTypeBean.SplbListBean> goodsTypeBean3List = new ArrayList<>();//食品分类第一个

    private List<String> goodsBZQDWList = new ArrayList<>();//保质期单位
    private List<GoodsTypeBean.BzqBean> goodsBZQDWBeanList = new ArrayList<>();//保质期单位
    private List<CommodityExistedBean.DataBean> mSplbListBeanList = new ArrayList<>();//保质期单位
    private int cid;
    private String shop_id;
    private String user_id;
    private boolean isselelct = true;
    private String code3;
    private Gson gson;
    private String proid;
    private Uri photoUri;
    private String picPath;
    List<String> picPathlist = new ArrayList<>();
    private ImgListAdapter imgListAdapter;
    private String pic_path;

    @Override
    public void widgetClick(View v) {

    }

    /**
     * 设置选择框数据
     *
     * @param spinner
     * @param mSpinnerList
     * @param selectpos
     */
    private void initSpinner(Spinner spinner, List<String> mSpinnerList, int selectpos) {
        //适配器
        ArrayAdapter<String> spfloneAdapter = new ArrayAdapter<String>(AddCommodityActivity.this, R.layout.registration_sex_item, mSpinnerList);
        //设置样式
        spfloneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(spfloneAdapter);
        spinner.setSelection(selectpos);
        spfloneAdapter.notifyDataSetChanged();
    }

    @Override
    public void initParms(Bundle parms) {
        gson = new Gson();
        View view1 = ShowDalogUtils.showCustomizeDialog(AddCommodityActivity.this, R.layout.send_customize);
        showDialog = ShowDalogUtils.showDialog(AddCommodityActivity.this, false, view1);
        showDialog.dismiss();
        cid = PreferencesUtils.getInt(this, InvoicingConstants.QY_ID, 0);
        user_id = PreferencesUtils.getString(this, InvoicingConstants.USER_ID, "");
        shop_id = PreferencesUtils.getString(this, InvoicingConstants.SHOP_ID, "");
        parms = getIntent().getExtras();
        commodityType = parms.getString(InvoicingConstants.COMMODITY_TYPE);
        ivBack.setVisibility(View.VISIBLE);
        rquestNetGoodsType("1", "", "");
        if (!TextUtils.isEmpty(commodityType)) {
            if (commodityType.equals(InvoicingConstants.COMMODITY_ADD)) {
                tvTitle.setText("添加商品");
            } else if (commodityType.equals(InvoicingConstants.COMMODITY_EDIT)) {
                proid = parms.getString(InvoicingConstants.COMMODITY_Id);
                tvTitle.setText("编辑商品");
                requestNetGetCommodity(proid);
            }
        }
        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        LogUtils.d("width-display :" + dm2.widthPixels);
        if (dm2.widthPixels <= 1080) {
            recycleListPic.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recycleListPic.setLayoutManager(new GridLayoutManager(this, 3));
        }
        picPathlist.clear();
        picPathlist.add("");
        imgListAdapter = new ImgListAdapter(this, picPathlist, "add");
        recycleListPic.setAdapter(imgListAdapter);
        imgListAdapter.notifyDataSetChanged();
        imgListAdapter.setiImgOnClickListerner(new ImgListAdapter.IImgOnClickListerner() {
            @Override
            public void onImgClick(int position, String imgUrl) {
                if (TextUtils.isEmpty(imgUrl)) {
                    if (picPathlist.size() < 4) {
                        picPhoto();

                    } else {
                        showToast("商品图片最多为3张!");
                        return;
                    }
                } else {
                    ArrayList<String> imgList = new ArrayList<String>();
                    for (int i = 0; i < picPathlist.size(); i++) {
                        if (!TextUtils.isEmpty(picPathlist.get(i))) {
                            imgList.add(picPathlist.get(i));
                        }
                    }
                    Intent intent = new Intent();
                    intent.setClass(AddCommodityActivity.this, SwipeActivity.class);
                    intent.putStringArrayListExtra("imagelist", imgList);
                    intent.putExtra("position", position + "");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(AddCommodityActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            }
        });

        imgListAdapter.setiIImgDelOnClickListerner(new ImgListAdapter.IImgDelOnClickListerner() {
            @Override
            public void onDelClick(int position, String imgUrl) {
                View exitView = ShowDalogUtils.showCustomizeDialog(AddCommodityActivity.this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(AddCommodityActivity.this, false, exitView);
                exitClick(exitView, dialog, position, imgUrl);
            }
        });

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
                        genre = "1";
                        llCode.setVisibility(View.GONE);
                        llBarCode.setVisibility(View.VISIBLE);

                        break;

                    case R.id.radio_btn_sz:
                        //散装
                        genre = "0";
                        llCode.setVisibility(View.VISIBLE);
                        llBarCode.setVisibility(View.GONE);
                        break;
                }
            }
        });

        commodityEtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    showDialog.show();
                    name = commodityEtName.getText().toString().trim();//名字
                    requestNetGetCommodity("", cid + "", name);


                }
            }
        });


        commodityEtBarCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    showDialog.show();
                    barcode = commodityEtBarCode.getText().toString().trim();//条形码
                    requestNetGetCommodity(barcode, cid + "", "");
                }
            }
        });
//        commodityEtBarCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                requestNetGetCommodity(s.toString(), cid + "", "");
//            }
//        });
    }

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
        String pic_pathFileName = str + ".png";

        // 根据文件地址创建文件
        pic_path = Environment.getExternalStorageDirectory().toString() + "/mtecc/invoing/" + pic_pathFileName;
        File file = new File(pic_path);
        // 把文件地址转换成Uri格式
        Uri photoURI = FileProvider.getUriForFile(AddCommodityActivity.this,
                getApplicationContext().getPackageName() + ".fileProvider",
                file);
        // 设置系统相机拍摄照片完成后图片文件的存放地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        startActivityForResult(intent, 109);
    }

    @Override
    public void doBusiness(Context mContext) {
        goodsType1List.clear();
        goodsType2List.clear();
        goodsType3List.clear();
        goodsBZQDWList.clear();


        goodsTypeBean1List.clear();
        goodsTypeBean2List.clear();
        goodsTypeBean3List.clear();
        goodsBZQDWBeanList.clear();


        goodsType1List.add("请选择");
        goodsType2List.add("请选择");
        goodsType3List.add("请选择");
        goodsBZQDWList.add("请选择保质期单位");

        goodsTypeBean1List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
        goodsTypeBean2List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
        goodsTypeBean3List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
        goodsBZQDWBeanList.add(new GoodsTypeBean.BzqBean("请选择保质期单位", 0));


        initSpinner(spinnerType1, goodsType1List, 0);
        initSpinner(spinnerType2, goodsType2List, 0);
        initSpinner(spinnerType3, goodsType3List, 0);
        initSpinner(spinnerBZQ, goodsBZQDWList, 0);


        spinnerType1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    GoodsTypeBean.SplbListBean SplbListBean = goodsTypeBean1List.get(position);
                    parentCode = SplbListBean.getCode();
                    if (!TextUtils.isEmpty(SplbListBean.getCode())) {
                        typeCode1 = SplbListBean.getCode();
                    }
                    typeCode = typeCode1;

                    grade = "2";
                    rquestNetGoodsType(grade, parentCode, parentCode3);


                } else {
                    typeCode = typeCode1;
                    goodsType2List.clear();
                    goodsTypeBean2List.clear();
                    goodsType2List.add("请选择");
                    goodsTypeBean2List.add(new GoodsTypeBean.SplbListBean("请选择", ""));

                    goodsType3List.clear();
                    goodsTypeBean3List.clear();
                    goodsType3List.add("请选择");
                    goodsTypeBean3List.add(new GoodsTypeBean.SplbListBean("请选择", ""));

                    initSpinner(spinnerType2, goodsType2List, 0);
                    initSpinner(spinnerType3, goodsType3List, 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    GoodsTypeBean.SplbListBean SplbListBean = goodsTypeBean2List.get(position);
                    grade = "3";
                    parentCode = SplbListBean.getCode();
                    if (!TextUtils.isEmpty(SplbListBean.getCode())) {
                        typeCode2 = SplbListBean.getCode();
                        typeCode = typeCode2;
                    }
                    parentCode3 = code3;
                    rquestNetGoodsType(grade, parentCode, parentCode3);

                } else {
                    typeCode = typeCode1;
                    goodsType3List.clear();
                    goodsTypeBean3List.clear();
                    goodsType3List.add("请选择");
                    goodsTypeBean3List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                    initSpinner(spinnerType3, goodsType3List, 0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerType3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    GoodsTypeBean.SplbListBean SplbListBean = goodsTypeBean3List.get(position);
                    if (!TextUtils.isEmpty(SplbListBean.getCode())) {
                        typeCode3 = SplbListBean.getCode();
                        typeCode = typeCode3;
                    }
                    LogUtils.i("选择的第三季" + typeCode);
                } else {
                    if (TextUtils.isEmpty(typeCode2)) {
                        typeCode = typeCode1;
                    } else {
                        typeCode = typeCode2;

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerBZQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GoodsTypeBean.BzqBean bzqBean = goodsBZQDWBeanList.get(position);
                bzqdanwei = bzqBean.getBUSINID() + "";
                LogUtils.i("选择的单位" + bzqdanwei);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.rl_back, R.id.commodity_img_bar_code, R.id.commodity_tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                View exitView = ShowDalogUtils.showCustomizeDialog(this, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, exitView);
                exitClick(exitView, dialog);
                break;
            case R.id.commodity_img_bar_code:
                //扫码
                getbarCodePression();
                break;
            case R.id.commodity_tv_commit:
                //提交
                showDialog.show();
                commit();
                break;

        }
    }

    /**
     * 提交
     */
    private void commit() {

        name = commodityEtName.getText().toString().trim();//名字
        code = commodityEtCode.getText().toString().trim();//编码
        barcode = commodityEtBarCode.getText().toString().trim();//条形码
        guige = commodityEtGuige.getText().toString().trim();//规格
        danwei = commodityEtDanwei.getText().toString().trim();//单位
        baozhiqi = commodityEtBaozhiqi.getText().toString().trim();
        bz = commodityetBz.getText().toString().trim();
        trademark = commodityEtTrademark.getText().toString().trim();

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
        if (genre.equals("0")) {
            if (TextUtils.isEmpty(code) || code.equals("")) {
                showToast("商品编号不能为空!");
                showDialog.dismiss();
                return;
            }
        } else if (genre.equals("1")) {
            if (TextUtils.isEmpty(barcode) || barcode.equals("")) {
                showToast("商品条形码不能为空!");
                showDialog.dismiss();
                return;
            }
        }

        if (TextUtils.isEmpty(typeCode)) {
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
        if (TextUtils.isEmpty(bzqdanwei) || bzqdanwei.equals("")) {
            showToast("保质期单位不能为空!");
            showDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(commodityUser) || commodityUser.equals("")) {
            showToast("生产商不能为空!");
            showDialog.dismiss();
            return;
        }

//        if (TextUtils.isEmpty(commodityUserAddress) || commodityUserAddress.equals("")) {
//            showToast("生产商地址不能为空!");
//            showDialog.dismiss();
//            return;
//        }
        if (TextUtils.isEmpty(commodityUserCode) || commodityUserCode.equals("")) {
            showToast("生产许可证号不能为空!");
            showDialog.dismiss();
            return;
        }
        //TODO:提交逻辑
        CommodityBean commodityBean = new CommodityBean();
        commodityBean.setProName(name);
        commodityBean.setBarcode(barcode);
        commodityBean.setProCode(code);
        commodityBean.setMeas(guige);
        commodityBean.setMeaunit(danwei);
        commodityBean.setObtype(typeCode);
        commodityBean.setProbzq(baozhiqi);
        commodityBean.setRemark(bz);
        commodityBean.setProtype(genre);
        commodityBean.setTrademark(trademark);
        commodityBean.setBzqunit(bzqdanwei);
        commodityBean.setBzqunit(bzqdanwei);
        commodityBean.setMernameorplace(commodityUser);


        if (!TextUtils.isEmpty(commodityType) && commodityType.equals(InvoicingConstants.COMMODITY_ADD)) {
            String productBean = gson.toJson(commodityBean);
            requestNetAddCommodity(productBean, shop_id, cid + "", user_id);
        } else if (!TextUtils.isEmpty(commodityType) && commodityType.equals(InvoicingConstants.COMMODITY_EDIT)) {
            commodityBean.setProId(Integer.valueOf(proid));
            String productBean = gson.toJson(commodityBean);
            requestNetEditCommodity(productBean, user_id);
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

        switch (requestCode) {
            case 1:
                if (data != null) {
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
                            requestNetGetCommodity(result, cid + "", "");

                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(AddCommodityActivity.this, "解析二维码失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case 12:
                //添加批次
                if (data != null) {
                    List<BatchBean> bactchList = (List<BatchBean>) data.getSerializableExtra(InvoicingConstants.BATCH_Add_list);

                    if (bactchList != null) {
                        mBacthlist.addAll(bactchList);
                    } else {
                        Toast.makeText(AddCommodityActivity.this, "数据为空!", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

            case 109:
                if (resultCode == RESULT_OK) {
                    LogUtils.i("onActivityResult: RESULT_OK " + pic_path);
                    String imagepath = CompressionPhotoUtils.compressImage(pic_path, pic_path, 50);
                    LogUtils.i("onActivityResult: RESULT_OK " + imagepath);
                    picPathlist.add(imagepath);
                    imgListAdapter.notifyDataSetChanged();
                } else {
                    LogUtils.i("onActivityResult: " + pic_path);
                    File file = new File(pic_path);
                    if (!file.exists()) {
                    }

                }
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

                finish();

            }
        });
    }

    /**
     * 获取商品类别
     *
     * @param grade
     * @param parentCode
     */
    private void rquestNetGoodsType(final String grade, String parentCode, final String code) {

        String url = InvoicingConstants.BASE_URL + InvoicingConstants.toAdd_URL;
        LogUtils.d("登陆的url" + url);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("grade", grade)
                .addParams("parentCode", parentCode)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {

                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());

                            GoodsTypeBean goodsTypeBean = gson.fromJson(response, GoodsTypeBean.class);
                            if (goodsTypeBean != null) {
                                if (grade.equals("1")) {
                                    List<GoodsTypeBean.SplbListBean> dataList = goodsTypeBean.getSplbList();
                                    goodsTypeBean1List.clear();
                                    goodsType1List.clear();
                                    goodsTypeBean1List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                                    goodsTypeBean1List.addAll(dataList);
                                    int size = goodsTypeBean1List.size();
                                    for (int i = 0; i < size; i++) {
                                        goodsType1List.add(goodsTypeBean1List.get(i).getContent());
                                    }
                                    initSpinner(spinnerType1, goodsType1List, 0);
                                    List<GoodsTypeBean.BzqBean> bzqBeanList = goodsTypeBean.getBzq();
                                    goodsBZQDWList.clear();
                                    goodsBZQDWBeanList.clear();
                                    goodsBZQDWBeanList.add(new GoodsTypeBean.BzqBean("请选择保质期单位", 0));
                                    goodsBZQDWBeanList.addAll(bzqBeanList);
                                    int size1 = goodsBZQDWBeanList.size();
                                    for (int i = 0; i < size1; i++) {
                                        goodsBZQDWList.add(goodsBZQDWBeanList.get(i).getBUSINNAME());
                                    }

                                    initSpinner(spinnerBZQ, goodsBZQDWList, 0);
                                } else if (grade.equals("2")) {
                                    List<GoodsTypeBean.SplbListBean> dataList = goodsTypeBean.getSplbList();
                                    if (!TextUtils.isEmpty(code)) {
                                        goodsTypeBean2List.clear();
                                        goodsType2List.clear();
                                        goodsTypeBean2List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                                        goodsTypeBean2List.addAll(dataList);
                                        int size = goodsTypeBean2List.size();
                                        for (int i = 0; i < size; i++) {
                                            goodsType2List.add(goodsTypeBean2List.get(i).getContent());
                                        }
                                        for (int i = 0; i < size; i++) {
                                            if (code.equals(goodsTypeBean2List.get(i).getCode())) {
                                                initSpinner(spinnerType2, goodsType2List, i);
                                            }
                                        }

                                    } else {
                                        goodsTypeBean2List.clear();
                                        goodsType2List.clear();
                                        goodsTypeBean2List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                                        goodsTypeBean2List.addAll(dataList);
                                        int size = goodsTypeBean2List.size();
                                        for (int i = 0; i < size; i++) {
                                            goodsType2List.add(goodsTypeBean2List.get(i).getContent());
                                        }
                                        initSpinner(spinnerType2, goodsType2List, 0);
                                    }

                                } else if (grade.equals("3")) {
                                    List<GoodsTypeBean.SplbListBean> dataList = goodsTypeBean.getSplbList();
                                    if (!TextUtils.isEmpty(code)) {
                                        goodsTypeBean3List.clear();
                                        goodsType3List.clear();
                                        goodsTypeBean3List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                                        goodsTypeBean3List.addAll(dataList);
                                        int size = goodsTypeBean3List.size();
                                        for (int i = 0; i < size; i++) {
                                            goodsType3List.add(goodsTypeBean3List.get(i).getContent());
                                        }
                                        for (int i = 0; i < size; i++) {
                                            if (code.equals(goodsTypeBean3List.get(i).getCode())) {
                                                initSpinner(spinnerType3, goodsType3List, i);
                                                parentCode3 = "";
                                            }
                                        }
                                    } else {
                                        goodsTypeBean3List.clear();
                                        goodsType3List.clear();
                                        goodsTypeBean3List.add(new GoodsTypeBean.SplbListBean("请选择", ""));
                                        goodsTypeBean3List.addAll(dataList);
                                        int size = goodsTypeBean3List.size();
                                        for (int i = 0; i < size; i++) {
                                            goodsType3List.add(goodsTypeBean3List.get(i).getContent());
                                        }
                                        initSpinner(spinnerType3, goodsType3List, 0);
                                    }


                                }


                            } else {
                                Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 添加商品
     */
    private void requestNetAddCommodity(String productBean, String shopid, String cid, String userid) {
//        String imgUrl = cardBean.getImgUrl();
//        if (!TextUtils.isEmpty(imgUrl)) {
//            post.addFile(cardBean.getCardnum(), cid + user_id + cardBean.getCardnum() + ".png", new File(imgUrl));
//        }
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.AddGoogs_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + productBean);
        LogUtils.d("登陆的url" + shopid);
        PostFormBuilder post = OkHttpUtils.post();
        if (picPathlist != null && picPathlist.size() != 0) {
//            ArrayList<String> imgList = new ArrayList<String>();
            for (int i = 0; i < picPathlist.size(); i++) {
                if (!TextUtils.isEmpty(picPathlist.get(i))) {
//                    imgList.add(picPathlist.get(i));
                    post.addFile("imgname" + i, "imgname" + i + ".png", new File(picPathlist.get(i)));
                }
            }
        }

        post.tag(this)
                .addParams("productBean", productBean)
                .addParams("shopid", shopid)
                .addParams("cid", cid)
                .addParams("userid", userid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result != 0) {
                                String reslut = result + "";
                                if (reslut.equals("200")) {
                                    showToast("添加商品成功!");
                                    finish();
                                } else {
                                    showToast("添加商品失败!");
                                }
                            } else {
                                Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 商品查询
     */
    private void requestNetGetCommodity(String barcode, String cid, String proName) {
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.searchGood_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + barcode);
        LogUtils.d("登陆的url" + cid);
        LogUtils.d("登陆的url" + proName);
        OkHttpUtils
                .post()
                .tag(this)
                .addParams("barcode", barcode)
                .addParams("cid", cid)
                .addParams("proName", proName)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());

                            CommodityExistedBean commodityExistedBean = gson.fromJson(response, CommodityExistedBean.class);
                            if (commodityExistedBean != null) {
                                final List<CommodityExistedBean.DataBean> splbListBeanList = commodityExistedBean.getData();
                                if (splbListBeanList != null && splbListBeanList.size() != 0) {
                                    View customizeDialog = ShowDalogUtils.showCustomizeDialog(AddCommodityActivity.this, R.layout.add_selectshop_dialog);
                                    AlertDialog alertDialog = ShowDalogUtils.showDialog(AddCommodityActivity.this, false, customizeDialog);
                                    SelectEmployeeClick(customizeDialog, alertDialog, splbListBeanList);
//                                    mSplbListBeanList.clear();
//                                    mSplbListBeanList.addAll(splbListBeanList);
//                                    setPopuWindows();
                                }


                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
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
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());
                            SeeCommodityBean commodityExistedBean = gson.fromJson(response, SeeCommodityBean.class);
                            if (commodityExistedBean != null) {
                                SeeCommodityBean.DataBean data = commodityExistedBean.getData();
                                setEditDataMsg(data);
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 提价编辑商品
     */
    private void requestNetEditCommodity(final String productBean, String userid) {
//        String imgUrl = cardBean.getImgUrl();
//        if (!TextUtils.isEmpty(imgUrl)) {
//            post.addFile(cardBean.getCardnum(), cid + user_id + cardBean.getCardnum() + ".png", new File(imgUrl));
//        }
        String url = InvoicingConstants.BASE_URL + InvoicingConstants.EditGood_URL;
        LogUtils.d("登陆的url" + url);
        LogUtils.d("登陆的url" + productBean);
        LogUtils.d("登陆的url" + userid);
        PostFormBuilder post = OkHttpUtils.post();
        if (picPathlist != null && picPathlist.size() != 0) {
//            ArrayList<String> imgList = new ArrayList<String>();
            for (int i = 0; i < picPathlist.size(); i++) {
                if (!TextUtils.isEmpty(picPathlist.get(i))) {
//                    imgList.add(picPathlist.get(i));
                    post.addFile("imgname" + i, "imgname" + i + ".png", new File(picPathlist.get(i)));
                }
            }
        }
        post.tag(this)
                .addParams("productBean", productBean)
                .addParams("userid", userid)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("错误信息AddCommodityActivity" + e.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            showDialog.dismiss();
                            LogUtils.d("返回值信息AddCommodityActivity" + response.toString());
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
                                Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e1) {
                            LogUtils.d("错误信息AddCommodityActivity" + e1.toString());
                            Toast.makeText(AddCommodityActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 选择店铺
     *
     * @param customizeDialog
     * @param alertDialog
     */
    private void SelectEmployeeClick(View customizeDialog, final AlertDialog alertDialog, final List<CommodityExistedBean.DataBean> splbListBeanList) {
        ListView selectList = customizeDialog.findViewById(R.id.select_list_view);
        ImageView imgxDialog = customizeDialog.findViewById(R.id.img_x_dialog);
        TextView tvselct = customizeDialog.findViewById(R.id.tv_select);
        imgxDialog.setVisibility(View.VISIBLE);
        tvselct.setText("选择要带入的商品信息");

        SelectGoodsAdapter selectGoodsAdapter = new SelectGoodsAdapter(AddCommodityActivity.this, splbListBeanList);
        selectList.setAdapter(selectGoodsAdapter);
        selectGoodsAdapter.notifyDataSetChanged();
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommodityExistedBean.DataBean dataBean = splbListBeanList.get(position);
                if (dataBean != null) {
                    setDataMsg(dataBean);
                }

            }


        });

        imgxDialog.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 设置数据
     *
     * @param dataBean
     */
    private void setDataMsg(CommodityExistedBean.DataBean dataBean) {
        String protype = dataBean.getProtype();
        if (protype.equals("0")) {
            radioBtnSz.setChecked(true);
            radioBtnYbz.setChecked(false);
        } else {
            radioBtnSz.setChecked(false);
            radioBtnYbz.setChecked(true);
        }
        commodityEtName.setText(dataBean.getProName());//名字
        commodityEtCode.setText(dataBean.getProCode() + "");//编码
        commodityEtBarCode.setText(dataBean.getBarcode() + "");//条形码
        commodityEtGuige.setText(dataBean.getMeas());//规格
        commodityEtDanwei.setText(dataBean.getMeaunit());//单位
        commodityEtBaozhiqi.setText(dataBean.getProbzq());
        commodityetBz.setText(dataBean.getRemark());
        commodityEtTrademark.setText(dataBean.getTrademark());
        commodityEtStart.setText(dataBean.getMernameorplace());//生产商
        commodityEtStartAddress.setText("");//生产商地址
        commodityEtStartCode.setText("");//生产商许可号
        commodityEtStartBiao.setText("");


        CommodityExistedBean.DataBean.ObtypeBean obtype = dataBean.getObtype();
        String grade = obtype.getGrade();
        code3 = obtype.getCode();

        if (grade.equals("3")) {
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCodeBean = obtype.getParentCode();
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX.ParentCodeBean parentCode1Bean = parentCodeBean.getParentCode();
            for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                if (parentCode1Bean.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                    parentCode = parentCode1Bean.getCode();
                    parentCode3 = parentCodeBean.getCode();
                    initSpinner(spinnerType1, goodsType1List, i);
                }
            }
        } else if (grade.equals("2")) {
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCodeBean = obtype.getParentCode();
            if (parentCodeBean != null) {
                for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                    if (parentCodeBean.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                        parentCode = parentCodeBean.getCode();
                        parentCode3 = obtype.getCode();
                        initSpinner(spinnerType1, goodsType1List, i);
                    }
                }
            }
        } else if (grade.equals("1")) {
            if (obtype != null) {
                for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                    if (obtype.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                        parentCode = "1";
                        parentCode3 = obtype.getCode();
                        initSpinner(spinnerType1, goodsType1List, i);
                    }
                }
            }
        }
        String bzqunit = dataBean.getBzqunit();
        if (!TextUtils.isEmpty(bzqunit)) {
            int size = goodsBZQDWBeanList.size();
            for (int i = 0; i < size; i++) {
                if (bzqunit.equals(goodsBZQDWBeanList.get(i).getBUSINID() + "")) {
                    initSpinner(spinnerBZQ, goodsBZQDWList, i);
                }
            }
        }
    }

    /**
     * 设置数据
     *
     * @param dataBean
     */
    private void setEditDataMsg(SeeCommodityBean.DataBean dataBean) {
        String protype = dataBean.getProtype();
        if (protype.equals("0")) {
            radioBtnSz.setChecked(true);
            radioBtnYbz.setChecked(false);
        } else {
            radioBtnSz.setChecked(false);
            radioBtnYbz.setChecked(true);
        }
        commodityEtName.setText(dataBean.getProName());//名字
        commodityEtCode.setText(dataBean.getProCode() + "");//编码
        commodityEtBarCode.setText(dataBean.getBarcode() + "");//条形码
        commodityEtGuige.setText(dataBean.getMeas());//规格
        commodityEtDanwei.setText(dataBean.getMeaunit());//单位
        commodityEtBaozhiqi.setText(dataBean.getProbzq());
        commodityetBz.setText(dataBean.getRemark());
        commodityEtTrademark.setText(dataBean.getTrademark());
        commodityEtStart.setText(dataBean.getMernameorplace());//生产商
        commodityEtStartAddress.setText("");//生产商地址
        commodityEtStartCode.setText("");//生产商许可号
        commodityEtStartBiao.setText("");


        SeeCommodityBean.DataBean.ObtypeBean obtype = dataBean.getObtype();
        String grade = obtype.getGrade();
        code3 = obtype.getCode();

        if (grade.equals("3")) {
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCodeBean = obtype.getParentCode();
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX.ParentCodeBean parentCode1Bean = parentCodeBean.getParentCode();
            for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                if (parentCode1Bean.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                    parentCode = parentCode1Bean.getCode();
                    parentCode3 = parentCodeBean.getCode();
                    initSpinner(spinnerType1, goodsType1List, i);
                }
            }
        } else if (grade.equals("2")) {
            CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCodeBean = obtype.getParentCode();
            if (parentCodeBean != null) {
                for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                    if (parentCodeBean.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                        parentCode = parentCodeBean.getCode();
                        parentCode3 = obtype.getCode();
                        initSpinner(spinnerType1, goodsType1List, i);
                    }
                }
            }
        } else if (grade.equals("1")) {
            if (obtype != null) {
                for (int i = 0; i < goodsTypeBean1List.size(); i++) {
                    if (obtype.getCode().equals(goodsTypeBean1List.get(i).getCode())) {
                        parentCode = "1";
                        parentCode3 = obtype.getCode();
                        initSpinner(spinnerType1, goodsType1List, i);
                    }
                }
            }
        }
        String bzqunit = dataBean.getBzqunit();
        if (!TextUtils.isEmpty(bzqunit)) {
            int size = goodsBZQDWBeanList.size();
            for (int i = 0; i < size; i++) {
                if (bzqunit.equals(goodsBZQDWBeanList.get(i).getBUSINID() + "")) {
                    initSpinner(spinnerBZQ, goodsBZQDWList, i);
                }
            }
        }
    }

    /**
     * 是否返回
     *
     * @param exitView
     * @param dialog
     * @param position
     */
    private void exitClick(View exitView, final AlertDialog dialog, final int position, final String imgurl) {
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
                myDeleteFile(imgurl);
                picPathlist.remove(imgurl);
                showToast("已删除图片");
                imgListAdapter.notifyDataSetChanged();


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
}
