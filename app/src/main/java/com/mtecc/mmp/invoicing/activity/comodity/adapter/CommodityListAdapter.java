package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.SeeCommodityActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/27.
 * 单级商品列表管理
 */

public class CommodityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public CommodityListAdapter(Context mContext, List<String> mList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.commodity_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String name = mList.get(position);
        holder.commodityTvName.setText(name);
        holder.commodityListImgBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_LIST);
                intent.setClass(mContext, AddBatchActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        holder.commodityListImgBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(mContext, SeeCommodityActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.commodity_img)
        ImageView commodityImg;
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.commodity_tv_coding)
        TextView commodityTvCoding;
        @BindView(R.id.commodity_tv_norm)
        TextView commodityTvNorm;
        @BindView(R.id.commodity_tv_genre)
        TextView commodityTvGenre;
        @BindView(R.id.commodity_tv_shelf_life)
        TextView commodityTvShelfLife;
        @BindView(R.id.commodity_tv_type)
        TextView commodityTvType;
        @BindView(R.id.commodity_tv_price)
        TextView commodityTvPrice;
        @BindView(R.id.commodity_list_img_batch)
        TextView commodityListImgBatch;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
