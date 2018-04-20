package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class BatchListAdapter extends BaseAdapter {

    private Context mContext;
    private List<BatchBean> mList;
    IBatchImgOnClickListerner iBatchImgOnClickListerner;

    public BatchListAdapter(Context mContext, List<BatchBean> mList) {
        this.mContext = mContext;
        this.mList = mList;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.add_batch_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.commodityDialogEtName.setText(mList.get(position).getBatchName());
        holder.addBatchRecyclcerView.setLayoutManager(new GridLayoutManager(mContext, 3));

        List<String> imgUrl = mList.get(position).getImgUrl();
        if (imgUrl != null) {
//            imgUrl.add(imgUrl.size(), "");
        } else {
            imgUrl = new ArrayList<>();
            imgUrl.add(0, "");
        }
        ImgListAdapter imgListAdapter = new ImgListAdapter(mContext, imgUrl);
        holder.addBatchRecyclcerView.setAdapter(imgListAdapter);
        imgListAdapter.notifyDataSetChanged();

        imgListAdapter.setiImgOnClickListerner(new ImgListAdapter.IImgOnClickListerner() {
            @Override
            public void onImgClick(int pos, String imgUrl) {
                LogUtils.d("点击图片" + pos + imgUrl);
                if (iBatchImgOnClickListerner != null) {
                    iBatchImgOnClickListerner.onBatchImgClick(position,pos, imgUrl);
                }
            }
        });


        holder.addIteamImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatchBean batchBean = new BatchBean();
                batchBean.setBatchName("");
                mList.add(batchBean);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.commodity_dialog_et_name)
        EditText commodityDialogEtName;
        @BindView(R.id.batch_img_add_iteam)
        public ImageView addIteamImg;
        @BindView(R.id.add_batch_recyclcer_view)
        public RecyclerView addBatchRecyclcerView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static interface IBatchImgOnClickListerner {
        public void onBatchImgClick(int position,int imgposition, String imgUrl);
    }

    public void setiImgOnClickListerner(IBatchImgOnClickListerner iBatchImgOnClickListerner) {
        this.iBatchImgOnClickListerner = iBatchImgOnClickListerner;
    }

    public IBatchImgOnClickListerner getiImgOnClickListerner() {
        return iBatchImgOnClickListerner;
    }
}
