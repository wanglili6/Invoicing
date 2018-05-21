package com.mtecc.mmp.invoicing.activity.purchase.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.BatchListActivity;
import com.mtecc.mmp.invoicing.activity.comodity.SeeCommodityActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.purchase.SelectBatchListActivity;
import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/27.
 * 单级商品列表管理
 */

public class SelectCommodityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommodityListBean.DataBean> mList;
    private ListView lv;

    public SelectCommodityListAdapter(Context mContext, List<CommodityListBean.DataBean> mList, ListView commodityListRecycleView) {
        this.mContext = mContext;
        this.mList = mList;
        this.lv = commodityListRecycleView;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.commodity_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.selectCommodityCbox.setVisibility(View.VISIBLE);
        holder.commodityListImgBatch.setVisibility(View.GONE);
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
        Map<String, SelectBatchBean> stringDataBeanMap = mList.get(position).getmSelectMap();
        if (stringDataBeanMap != null && stringDataBeanMap.size() != 0) {
            holder.commodityImgNum.setVisibility(View.VISIBLE);
            int num = 0;
            for (String key : stringDataBeanMap.keySet()) {
                num = num + stringDataBeanMap.get(key).getNum();
            }
            holder.commodityImgNum.setText(num + "");
            holder.selectCommodityCbox.setBackgroundResource(R.mipmap.selext_yixuanze_icon);
        } else {
            holder.commodityImgNum.setVisibility(View.GONE);
            holder.selectCommodityCbox.setBackgroundResource(R.mipmap.select_weixuanze_icon);
        }

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
        @BindView(R.id.commodity_img_num)
        TextView commodityImgNum;
        @BindView(R.id.select_commodity_cbox)
        ImageView selectCommodityCbox;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
