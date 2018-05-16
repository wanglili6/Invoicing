package com.mtecc.mmp.invoicing.activity.manager.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.manager.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.manager.comodity.SeeCommodityActivity;
import com.mtecc.mmp.invoicing.activity.manager.comodity.bean.CommodityListBean;
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
    private List<CommodityListBean.DataBean> mList;

    public CommodityListAdapter(Context mContext, List<CommodityListBean.DataBean> mList) {
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
        final CommodityListBean.DataBean dataBean = mList.get(position);
        holder.commodityTvName.setText(dataBean.getProName() + "");
        String proCode = dataBean.getProCode();
        String barcode = dataBean.getBarcode();
        if (TextUtils.isEmpty(proCode) && !TextUtils.isEmpty(barcode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(proCode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        holder.commodityTvNorm.setText(dataBean.getMeas() + " / " + dataBean.getMeaunit());
        holder.commodityTvShelfLife.setText(dataBean.getProbzq() + "");
        holder.commodityTvBatchNum.setText(dataBean.getBatchCount() + "");

        holder.commodityListImgBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加批次
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.BATCH_TYPE, InvoicingConstants.BATCH_ADD);
                bundle.putString(InvoicingConstants.COMMODITY_Id, dataBean.getProId() + "");
                intent.setClass(mContext, AddBatchActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        holder.commodityListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看商品
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("proid", dataBean.getProId() + "");
                intent.setClass(mContext, SeeCommodityActivity.class);
                intent.putExtras(bundle);
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
        @BindView(R.id.commodity_tv_batch_num)
        TextView commodityTvBatchNum;
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
