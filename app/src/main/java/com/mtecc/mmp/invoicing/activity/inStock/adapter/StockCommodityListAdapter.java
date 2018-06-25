package com.mtecc.mmp.invoicing.activity.inStock.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.inStock.bean.StockListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/27.
 * 单级商品列表管理
 */

public class StockCommodityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<StockListBean.DataBean> mList;
    private String stockTyep;

    public StockCommodityListAdapter(Context mContext, List<StockListBean.DataBean> mList, String stockTyep) {
        this.mContext = mContext;
        this.mList = mList;
        this.stockTyep = stockTyep;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stock_commodity_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        StockListBean.DataBean dataBean = mList.get(position);
        holder.commodityTvName.setText(dataBean.getProId().getProName() + "");
        if (stockTyep.equals(InvoicingConstants.STOCK_SEE)) {
            holder.imgXianzhi.setVisibility(View.GONE);
        } else if (stockTyep.equals(InvoicingConstants.STOCK_WORING)) {
            holder.imgXianzhi.setVisibility(View.VISIBLE);
        }
        String proCode = dataBean.getProId().getProCode();
        String barcode = dataBean.getProId().getBarcode();
        if (TextUtils.isEmpty(proCode) && !TextUtils.isEmpty(barcode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(proCode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        holder.tvDioaboNum.setText(dataBean.getTranCount() + "");
        holder.stockTvInsum.setText(dataBean.getInsum() + "");
        holder.stockTvSellsum.setText(dataBean.getSellsum() + "");
        holder.stockTvResum.setText(dataBean.getResum() + "");
        holder.stockTvBatchsum.setText(dataBean.getBatchsum() + "");
        holder.stockTvShopName.setText(dataBean.getShopid().getShopname() + "");

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.commodity_img)
        ImageView commodityImg;
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.batch_msg_tv_dioabo_num)
        TextView tvDioaboNum;
        @BindView(R.id.commodity_img_num)
        TextView commodityImgNum;
        @BindView(R.id.commodity_tv_coding)
        TextView commodityTvCoding;
        @BindView(R.id.stock_tv_insum)
        TextView stockTvInsum;
        @BindView(R.id.stock_tv_name)
        TextView stockTvShopName;
        @BindView(R.id.stock_tv_sellsum)
        TextView stockTvSellsum;
        @BindView(R.id.stock_tv_resum)
        TextView stockTvResum;
        @BindView(R.id.img_xianzhi)
        ImageView imgXianzhi;
        @BindView(R.id.stock_tv_batchsum)
        TextView stockTvBatchsum;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
