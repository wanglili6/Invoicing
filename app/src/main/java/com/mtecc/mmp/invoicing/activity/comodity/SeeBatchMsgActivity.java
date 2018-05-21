package com.mtecc.mmp.invoicing.activity.comodity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.mtecc.mmp.invoicing.activity.comodity.adapter.SeeBatchPicListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.views.NoScrollListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SeeBatchMsgActivity extends BaseActivity {

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
    @BindView(R.id.commodity_see_et_name)
    EditText commoditySeeEtName;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.commodity_see_et_num)
    EditText commoditySeeEtNum;
    @BindView(R.id.commodity_see_et_lshoujia)
    EditText commoditySeeEtLshoujia;
    @BindView(R.id.commodity_see_et_jhuojia)
    EditText commoditySeeEtJhuojia;
    @BindView(R.id.commodity_see_et_pfjia)
    EditText commoditySeeEtPfjia;
    @BindView(R.id.see_batch_list_view)
    NoScrollListView seeBatchListView;
    //    private BatchPicListBean.BatchListBean batchPicListBean;
//    private SeeBatchPicListAdapter seeBatchPicListAdapter;
    private String batchPicid;
    List<BatchPicListBean.CardBean> mList = new ArrayList<>();
    List<DicBean.DataBean> mDicList = new ArrayList<>();

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("批次详情");
        batchPicid = getIntent().getStringExtra("pbatchid");
        requestNetSeeBatch(batchPicid);

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_see_batch_msg;
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
                            LogUtils.d("错误信息SeeBatchMsgActivity" + e.toString());
                            Toast.makeText(SeeBatchMsgActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeBatchMsgActivity" + e1.toString());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.d("返回值信息SeeBatchMsgActivity" + response.toString());
                            Gson gson = new Gson();
                            SeeBatchMsgBean seeBatchMsgBean = gson.fromJson(response, SeeBatchMsgBean.class);
                            if (seeBatchMsgBean != null) {
                                List<SeeBatchMsgBean.CardListBean> cardList = seeBatchMsgBean.getCardList();
                                int size = cardList.size();
                                mList.clear();

                                List<SeeBatchMsgBean.DiclistBean> diclist = seeBatchMsgBean.getDiclist();
                                for (int i = 0; i < size; i++) {
                                    SeeBatchMsgBean.CardListBean cardListBean = cardList.get(i);
                                    commoditySeeEtName.setText(cardListBean.getPbatchid().getBatchdateStr());
                                    commoditySeeEtNum.setText(cardListBean.getPbatchid().getBatchnum());
                                    commoditySeeEtLshoujia.setText(cardListBean.getPbatchid().getSellprice());
                                    commoditySeeEtJhuojia.setText(cardListBean.getPbatchid().getEnterprice());
                                    commoditySeeEtPfjia.setText(cardListBean.getPbatchid().getSaleprice());
                                    BatchPicListBean.CardBean picBean = new BatchPicListBean.CardBean();
                                    picBean.setCardnum(cardListBean.getCardnum());
                                    int diclistsize = diclist.size();
                                    mDicList.clear();
                                    for (int j = 0; j < diclistsize; j++) {
                                        SeeBatchMsgBean.DiclistBean diclistBean = diclist.get(j);
                                        if (cardListBean.getCardtype().equals(diclistBean.getBUSINID() + "")) {
                                            picBean.setCardtypeName(diclistBean.getBUSINNAME());
                                        }
                                        DicBean.DataBean dataBean = new DicBean.DataBean();
                                        dataBean.setBUSINID(diclistBean.getBUSINID());
                                        dataBean.setStatus(diclistBean.getStatus());
                                        dataBean.setBUSINNAME(diclistBean.getBUSINNAME());
                                        dataBean.setBusintypeid(diclistBean.getBusintypeid());
                                        dataBean.setBusintypename(diclistBean.getBusintypename());
                                        dataBean.setDicid(diclistBean.getDicid());
                                        mDicList.add(dataBean);
                                    }
                                    picBean.setCharddate(cardListBean.getCharddateStr());
                                    picBean.setRemark(cardListBean.getRemark());
                                    picBean.setImgUrl(InvoicingConstants.IMAGEURL + cardListBean.getFilecardid().getParentpath() + "/" + cardListBean.getFilecardid().getPath());
                                    mList.add(picBean);
                                }
                                SeeBatchPicListAdapter seeBatchPicListAdapter = new SeeBatchPicListAdapter(SeeBatchMsgActivity.this, mList, mDicList);
                                seeBatchListView.setAdapter(seeBatchPicListAdapter);
                                seeBatchPicListAdapter.notifyDataSetChanged();

                                seeBatchPicListAdapter.setiSeeOnClickListerner(new SeeBatchPicListAdapter.IBatchSeeImgListerner() {
                                    @Override
                                    public void onBatchSee(int position, String imgUrl) {
                                        if (!TextUtils.isEmpty(imgUrl)) {
                                            ArrayList<String> imgUrlList = new ArrayList<String>();
                                            imgUrlList.add(imgUrl);
                                            Intent intent = new Intent();
                                            intent.setClass(SeeBatchMsgActivity.this, SwipeActivity.class);
                                            intent.putStringArrayListExtra("imagelist", imgUrlList);
                                            intent.putExtra("position", 1 + "");
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SeeBatchMsgActivity.this).toBundle());
                                            } else {
                                                startActivity(intent);
                                            }
                                        } else {
                                            showToast("请先拍照,才能进行查看");
                                        }

                                    }

                                });


                            }
                        } catch (Exception e1) {
                            LogUtils.d("错误信息SeeBatchMsgActivity" + e1.toString());
                            Toast.makeText(SeeBatchMsgActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
