package com.mtecc.mmp.invoicing.activity.purchase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchase.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class PurchaseSwitchListAdapter extends BaseAdapter {
    private Context mContext;
    private List<PurchaseListBean> mList;
    private String type;

    public PurchaseSwitchListAdapter(Context mContext, List<PurchaseListBean> mList, String type) {
        this.mContext = mContext;
        this.mList = mList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.purchase_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PurchaseListBean purchaseListBean = mList.get(position);
        holder.purchaseTvName.setText(purchaseListBean.getName());
        holder.purchaseTvMoney.setText(purchaseListBean.getMoney() + "");
        holder.purchaseTvCode.setText(purchaseListBean.getCode());
        holder.purchaseTvTimer.setText(purchaseListBean.getTimer());
        if (type.equals("0")) {
            holder.purchaseImg.setBackgroundResource(R.mipmap.up_jin_icon);
        } else if (type.equals("1")) {
            holder.purchaseImg.setBackgroundResource(R.mipmap.tui_icon);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.purchase_img)
        ImageView purchaseImg;
        @BindView(R.id.purchase_tv_name)
        TextView purchaseTvName;
        @BindView(R.id.purchase_tv_money)
        TextView purchaseTvMoney;
        @BindView(R.id.purchase_tv_code)
        TextView purchaseTvCode;
        @BindView(R.id.purchase_tv_timer)
        TextView purchaseTvTimer;
        @BindView(R.id.img_purchase)
        LinearLayout imgPurchase;
        @BindView(R.id.purchase_list_ll)
        LinearLayout purchaseListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
