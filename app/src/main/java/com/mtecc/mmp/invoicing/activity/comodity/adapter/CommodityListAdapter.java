package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.CommodityBean;
import com.mtecc.mmp.invoicing.activity.shop.ShopManagementActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class CommodityListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CommodityBean> mList;

    public CommodityListAdapter(Context mContext, List<CommodityBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(mContext).inflate(R.layout.commodity_list, parent, false);
        return new CommodityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((CommodityViewHolder) holder).commodityTvTypeName.setText(mList.get(position).getTitle());
        ((CommodityViewHolder) holder).rlList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        CommodityListItamAdapter commodityListItamAdapter = new CommodityListItamAdapter(mContext, mList.get(position).getNameList());
        ((CommodityViewHolder) holder).rlList.setAdapter(commodityListItamAdapter);
        commodityListItamAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    static class CommodityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commodity_tv_type_name)
        TextView commodityTvTypeName;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;
        @BindView(R.id.rl_list)
        RecyclerView rlList;


        CommodityViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
