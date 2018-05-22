package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 批次列表的适配器
 */

public class BatchAdapter extends BaseAdapter {
    private Context mContext;
    private List<BatchBean.DataBean> batchBeenList;

    public BatchAdapter(Context mContext, List<BatchBean.DataBean> batchBeenList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.batch_msg_iterm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BatchBean.DataBean dataBean = batchBeenList.get(position);
        holder.batchMsgTvName.setText(dataBean.getBatchnum());
        holder.batchMsgTvtimer.setText(dataBean.getBatchdateStr());
        String enterprice = dataBean.getEnterprice();
        String saleprice = dataBean.getSaleprice();
        String sellprice = dataBean.getSellprice();
        if (TextUtils.isEmpty(enterprice)) {
            enterprice = "0.0";
        }
        if (TextUtils.isEmpty(saleprice)) {
            saleprice = "0.0";
        }
        if (TextUtils.isEmpty(sellprice)) {
            sellprice = "0.0";
        }
        holder.batchMsgTvjiage.setText("进货价: " + enterprice + "\r\n" + "批发价价: " + saleprice + "\r\n" + "零售价: " + sellprice);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.batch_msg_tv_name)
        TextView batchMsgTvName;
        @BindView(R.id.batch_msg_tv_timer)
        TextView batchMsgTvtimer;
        @BindView(R.id.batch_msg_tv_jiage)
        TextView batchMsgTvjiage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
