package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class SeeBatchPicListAdapter extends BaseAdapter {

    private Context mContext;
    private List<BatchPicListBean.CardBean> mList;
    private List<DicBean.DataBean> mDiclist;
    IBatchSeeImgListerner iBatchSeeImgListerner;

    public SeeBatchPicListAdapter(Context mContext, List<BatchPicListBean.CardBean> mList, List<DicBean.DataBean> mDiclist) {
        this.mContext = mContext;
        this.mList = mList;
        this.mDiclist = mDiclist;

    }


    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.see_batch_pic_list_itdream, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.batchSeeType.setText(mList.get(position).getCardtypeName());
        holder.batchSeeTime.setText(mList.get(position).getCharddate());
        holder.batchSeeNum.setText(mList.get(position).getCardnum());
        holder.batchSeeRemark.setText(mList.get(position).getRemark());
        Glide.with(mContext)
                .load(mList.get(position).getImgUrl())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.imgZhangjian);


        holder.imgZhangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iBatchSeeImgListerner != null) {
                    iBatchSeeImgListerner.onBatchSee(position, mList.get(position).getImgUrl());
                }


            }
        });


        return convertView;
    }


    public static interface IBatchSeeImgListerner {
        public void onBatchSee(int position, String imgUrl);
    }

    public void setiSeeOnClickListerner(IBatchSeeImgListerner iBatchDelOnClickListerner) {
        this.iBatchSeeImgListerner = iBatchDelOnClickListerner;
    }

    public IBatchSeeImgListerner getiBatchSeeOnClickListerner() {
        return iBatchSeeImgListerner;
    }


    static class ViewHolder {
        @BindView(R.id.batch_img_add_iteam)
        ImageView batchImgAddIteam;
        @BindView(R.id.batch_img_del_iteam)
        ImageView batchImgDelIteam;
        @BindView(R.id.textView6)
        TextView textView6;
        @BindView(R.id.batch_see_type)
        TextView batchSeeType;
        @BindView(R.id.batch_see_num)
        TextView batchSeeNum;
        @BindView(R.id.batch_see_time)
        TextView batchSeeTime;
        @BindView(R.id.batch_see_remark)
        TextView batchSeeRemark;
        @BindView(R.id.img_zhangjian)
        ImageView imgZhangjian;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
