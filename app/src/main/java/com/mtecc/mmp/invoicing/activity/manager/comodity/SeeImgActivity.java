package com.mtecc.mmp.invoicing.activity.manager.comodity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.manager.comodity.adapter.ImgSeeListAdapter;
import com.mtecc.mmp.invoicing.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看附件
 */
public class SeeImgActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_see_recycler_view)
    RecyclerView imgSeeRecyclerView;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("查看附件");
        List<String> imgurl = (List<String>) getIntent().getSerializableExtra("imgurl");
        ImgSeeListAdapter imgSeeListAdapter = new ImgSeeListAdapter(this, imgurl);
        imgSeeRecyclerView.setAdapter(imgSeeListAdapter);
        imgSeeListAdapter.notifyDataSetChanged();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_see_img;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        GridLayoutManager layoutManage = new GridLayoutManager(this, 3);
        imgSeeRecyclerView.setLayoutManager(layoutManage);
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
}
