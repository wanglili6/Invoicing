package com.mtecc.mmp.invoicing.activity.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.MainActivity;
import com.mtecc.mmp.invoicing.activity.comodity.AddCommodityActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityExistedBean;
import com.mtecc.mmp.invoicing.activity.login.LoginActivity;
import com.mtecc.mmp.invoicing.activity.login.bean.ShopSelectBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.PreferencesUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 选择商铺的适配器
 */

public class SelectShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopSelectBean.ShoplistBean> batchBeenList;
    private AlertDialog alertDialog;
    private boolean isuseradmin;

    public SelectShopAdapter(Context mContext, List<ShopSelectBean.ShoplistBean> batchBeenList, AlertDialog alertDialog, boolean isuseradmin) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;
        this.alertDialog = alertDialog;
        this.isuseradmin = isuseradmin;
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
        final ShopSelectBean.ShoplistBean shoplistBean = batchBeenList.get(position);

        holder.shopTvName.setText(shoplistBean.getShopname());

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
