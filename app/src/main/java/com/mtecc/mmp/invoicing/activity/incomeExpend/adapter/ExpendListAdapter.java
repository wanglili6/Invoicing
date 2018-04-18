package com.mtecc.mmp.invoicing.activity.incomeExpend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/17.
 */

public class ExpendListAdapter extends RecyclerView.Adapter<ExpendListAdapter.InComeExpendViewHolder> {
    private Context mContext;
    private ArrayList<String> list;

    public ExpendListAdapter(Context mContext, ArrayList<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public InComeExpendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        InComeExpendViewHolder inComeExpendViewHolder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.income_expend_list_iteam, parent, false);
        inComeExpendViewHolder = new InComeExpendViewHolder(view);
        return inComeExpendViewHolder;
    }

    @Override
    public void onBindViewHolder(InComeExpendViewHolder holder, int position) {
        holder.incomeExpendImg.setBackgroundResource(R.mipmap.expending);
        holder.incomeExpendTvName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class InComeExpendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.income_expend_img)
        ImageView incomeExpendImg;
        @BindView(R.id.income_expend_tv_name)
        TextView incomeExpendTvName;
        @BindView(R.id.income_expend_tv_num)
        TextView incomeExpendTvNum;
        @BindView(R.id.income_expend_tv_time)
        TextView incomeExpendTvTime;

        InComeExpendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
