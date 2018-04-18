package com.mtecc.mmp.invoicing.activity.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.shop.ShopSeeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {
    private Context mContext;
    private List<String> mList;

    public ShopListAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ShopListAdapter.ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_list_iteam, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopListAdapter.ShopViewHolder holder, int position) {
        holder.shopTvName.setText(mList.get(position));
        holder.shopListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:跳转查看店铺详情
                Intent intent = new Intent();
                intent.setClass(mContext, ShopSeeActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_img)
        ImageView shopImg;
        @BindView(R.id.shop_tv_name)
        TextView shopTvName;
        @BindView(R.id.shop_tv_code)
        TextView shopTvCode;
        @BindView(R.id.shop_tv_status)
        TextView shopTvStatus;
        @BindView(R.id.shop_list_ll)
        LinearLayout shopListLl;

        ShopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
