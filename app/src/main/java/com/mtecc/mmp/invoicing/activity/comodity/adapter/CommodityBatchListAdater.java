package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.SeeImgActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/28.
 */

public class CommodityBatchListAdater extends BaseAdapter {

    private Context mContext;
    private List<BatchBean> mList;

    public CommodityBatchListAdater(Context mContext, List<BatchBean> mList) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.commodity_batch_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final BatchBean batchBean = mList.get(position);
        holder.tvBacthTime.setText(batchBean.getBatchstartTimer());
        holder.tvBatchSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看附件
                LogUtils.d("查看附件");
//                Intent intent = new Intent();
//                intent.putExtra("imgurl", (Serializable) batchBean.getImgUrl());
//                intent.setClass(mContext, SeeImgActivity.class);
//                mContext.startActivity(intent);
            }
        });

        holder.tvBatchDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除批次
                LogUtils.d("删除批次");
                View exitView = ShowDalogUtils.showCustomizeDialog(mContext, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(mContext, false, exitView);
                exitClick(exitView, dialog, batchBean);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_bacth_time)
        TextView tvBacthTime;
        @BindView(R.id.tv_batch_see)
        TextView tvBatchSee;
        @BindView(R.id.tv_batch_del)
        TextView tvBatchDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 是否删除
     *
     * @param exitView
     * @param dialog
     */
    private void exitClick(View exitView, final AlertDialog dialog, final BatchBean bean) {
        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除当前批次?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mList.remove(bean);
                notifyDataSetChanged();

            }
        });
    }
}

