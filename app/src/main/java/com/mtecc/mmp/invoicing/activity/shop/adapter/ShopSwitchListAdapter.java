package com.mtecc.mmp.invoicing.activity.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeListActivity;
import com.mtecc.mmp.invoicing.activity.shop.ShopSeeActivity;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class ShopSwitchListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopListBean.DataBean> mList;

    public ShopSwitchListAdapter(Context mContext, List<ShopListBean.DataBean> mList) {
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
        ShopViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_list_iteam, parent, false);
            holder = new ShopViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ShopViewHolder) convertView.getTag();
        }
        final ShopListBean.DataBean dataBean = mList.get(position);
        holder.shopTvName.setText(dataBean.getShopname());
        holder.shopTvCode.setText(dataBean.getShopnum() + "");
        holder.shopTvTimer.setText(dataBean.getCreatedate());
        holder.shopTvCount.setText(dataBean.getMancount() + "");
        holder.shopTvCreateMan.setText(dataBean.getCreateman());
        String shopstate = dataBean.getShopstate();
        if (shopstate.equals("0")) {
            holder.shopTvStatus.setText("正常");
        } else {
            holder.shopTvStatus.setText("注销");
        }

        return convertView;
    }

    static class ShopViewHolder {
        @BindView(R.id.shop_img)
        ImageView shopImg;
        @BindView(R.id.img_employee)
        LinearLayout shopEmployee;
        @BindView(R.id.shop_tv_name)
        TextView shopTvName;
        @BindView(R.id.shop_tv_code)
        TextView shopTvCode;
        @BindView(R.id.shop_tv_status)
        TextView shopTvStatus;
        @BindView(R.id.shop_tv_timer)
        TextView shopTvTimer;
        @BindView(R.id.shop_tv_count)
        TextView shopTvCount;
        @BindView(R.id.shop_tv_creat_man)
        TextView shopTvCreateMan;
        @BindView(R.id.shop_list_ll)
        LinearLayout shopListLl;

        ShopViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
