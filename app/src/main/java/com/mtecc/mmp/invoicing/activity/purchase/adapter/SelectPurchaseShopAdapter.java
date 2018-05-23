package com.mtecc.mmp.invoicing.activity.purchase.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.login.bean.ShopSelectBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 选择商铺的适配器
 */

public class SelectPurchaseShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopListBean.DataBean>  batchBeenList;
    private AlertDialog alertDialog;

    public SelectPurchaseShopAdapter(Context mContext, List<ShopListBean.DataBean>  batchBeenList, AlertDialog alertDialog) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;
        this.alertDialog = alertDialog;
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
        ShopListBean.DataBean dataBean = batchBeenList.get(position);
        holder.shopTvName.setTextSize(14);
        holder.shopTvName.setText(dataBean.getShopname());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.employee_img)
        ImageView employeeImg;
        @BindView(R.id.shop_tv_name)
        TextView shopTvName;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
