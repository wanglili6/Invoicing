package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityExistedBean;
import com.mtecc.mmp.invoicing.activity.login.bean.ShopSelectBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 选择商品的适配器
 */

public class SelectGoodsAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommodityExistedBean.DataBean> batchBeenList;
    private AlertDialog alertDialog;
    private boolean isuseradmin;

    public SelectGoodsAdapter(Context mContext, List<CommodityExistedBean.DataBean> batchBeenList) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;

    }

    @Override
    public int getCount() {
        return batchBeenList != null ? batchBeenList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return batchBeenList != null ? batchBeenList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_dialog_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommodityExistedBean.DataBean dataBean = batchBeenList.get(position);
        holder.shopTvName.setTextSize(16);
        holder.shopTvCode.setVisibility(View.VISIBLE);
        holder.employeeImg.setBackgroundResource(R.mipmap.goods_icon);
        holder.shopTvName.setText(dataBean.getProName());
        String proCode = dataBean.getProCode();
        String barcode = dataBean.getBarcode();
        if (TextUtils.isEmpty(proCode) && !TextUtils.isEmpty(barcode)) {
            holder.shopTvCode.setText(barcode);

        }

        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(proCode)) {
            holder.shopTvCode.setText(proCode);

        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.employee_img)
        ImageView employeeImg;
        @BindView(R.id.shop_tv_name)
        TextView shopTvName;
        @BindView(R.id.shop_tv_code)
        TextView shopTvCode;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
