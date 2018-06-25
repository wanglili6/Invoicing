package com.mtecc.mmp.invoicing.activity.transfer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SelectBatchBean;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferCommodityBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/27.
 * 单级商品列表管理
 */

public class TransferSelectCommodityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<TransferCommodityBean.DataBean> mList;
    private ListView lv;
    private String type;

    public TransferSelectCommodityListAdapter(Context mContext, List<TransferCommodityBean.DataBean> mList, ListView commodityListRecycleView, String type) {
        this.mContext = mContext;
        this.mList = mList;
        this.lv = commodityListRecycleView;
        this.type = type;
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
        holder.llSnum.setVisibility(View.VISIBLE);
        if (type.equals("show")) {
            holder.commodityMoney.setVisibility(View.VISIBLE);
        } else {
            holder.commodityMoney.setVisibility(View.GONE);
        }
        holder.commodityListImgBatch.setVisibility(View.GONE);
        TransferCommodityBean.DataBean dataBean = mList.get(position);
        holder.commodityTvName.setText(dataBean.getProId().getProName() + "");
        //剩余库存
        holder.commodityMsgTvSnum.setText(dataBean.getKcsum() + "");
        String proCode = dataBean.getProId().getProCode();
        String barcode = dataBean.getProId().getBarcode();
        if (TextUtils.isEmpty(proCode) && !TextUtils.isEmpty(barcode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(proCode)) {
            holder.commodityTvCoding.setText(barcode + "");

        }
        holder.commodityTvNorm.setText(dataBean.getProId().getMeas() + " / " + dataBean.getProId().getMeaunit());
        holder.commodityTvShelfLife.setText(dataBean.getProId().getProbzq() + "");
        holder.commodityTvBatchNum.setText(dataBean.getBatchsum() + "");
        List<SelectBatchBean> stringDataBeanMap = mList.get(position).getmSelectMap();
        if (stringDataBeanMap != null && stringDataBeanMap.size() != 0) {
            holder.commodityImgNum.setVisibility(View.VISIBLE);
            int num = 0;
            int size = stringDataBeanMap.size();
            for (int i = 0; i < size; i++) {
                num = num + stringDataBeanMap.get(i).getNum();
            }
            holder.commodityImgNum.setText(num + "");
            holder.commodityMoney.setText(dataBean.getmSelectMoney() + "");
            holder.selectCommodityCbox.setBackgroundResource(R.mipmap.selext_yixuanze_icon);
        } else {
            holder.commodityMoney.setText("0.0");
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
        @BindView(R.id.commodity_money)
        TextView commodityMoney;
        @BindView(R.id.select_commodity_cbox)
        ImageView selectCommodityCbox;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;
        @BindView(R.id.ll_snum)
        LinearLayout llSnum;
        @BindView(R.id.commodity_msg_tv_snum)
        TextView commodityMsgTvSnum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
