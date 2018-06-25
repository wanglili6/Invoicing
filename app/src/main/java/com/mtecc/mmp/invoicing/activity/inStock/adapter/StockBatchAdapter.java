package com.mtecc.mmp.invoicing.activity.inStock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.inStock.bean.BatchListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 批次列表的适配器
 */

public class StockBatchAdapter extends BaseAdapter {
    private Context mContext;
    private List<BatchListBean.DataBean> batchBeenList;
    private String stockType;


    public StockBatchAdapter(Context mContext, List<BatchListBean.DataBean> batchBeenList, String stockType) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;
        this.stockType = stockType;


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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stock_batch_msg_iterm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BatchListBean.DataBean dataBean = batchBeenList.get(position);
        holder.batchMsgTvTimer.setText(dataBean.getPbatchid().getBatchnum());
        holder.batchMsgTvName.setText(dataBean.getPbatchid().getBatchdateStr());
        holder.batchMsgTvTotalNum.setText(dataBean.getKcsum() + "");
        holder.batchMsgSellnum.setText(dataBean.getSellsum() + "");
        holder.batchMsgInnum.setText(dataBean.getInsum() + "");


        holder.batchMsgTvDioaboNum.setText(dataBean.getTranCount() + "");
        holder.batchMsgTvacCount.setText(dataBean.getAcCount() + "");
        holder.batchMsgTvOutNum.setText(dataBean.getMoveCount() + "");
        if (stockType.equals(InvoicingConstants.STOCK_SEE)) {
            holder.tvState.setVisibility(View.GONE);
            holder.imgState.setVisibility(View.GONE);
        } else if (stockType.equals(InvoicingConstants.STOCK_TYPE)) {
            holder.tvState.setVisibility(View.VISIBLE);
            holder.imgState.setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.batch_msg_tv_name)
        TextView batchMsgTvName;
        @BindView(R.id.batch_msg_tv_timer)
        TextView batchMsgTvTimer;
        @BindView(R.id.batch_msg_innum)
        TextView batchMsgInnum;
        @BindView(R.id.batch_msg_sellnum)
        TextView batchMsgSellnum;
        @BindView(R.id.batch_msg_tv_out_num)
        TextView batchMsgTvOutNum;
        @BindView(R.id.batch_msg_tv_total_num)
        TextView batchMsgTvTotalNum;
        @BindView(R.id.batch_msg_tv_dioabo_num)
        TextView batchMsgTvDioaboNum;
        @BindView(R.id.batch_msg_tv_account)
        TextView batchMsgTvacCount;
        @BindView(R.id.img_state)
        ImageView imgState;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.ll_out)
        LinearLayout llOut;
        @BindView(R.id.ll_transfer)
        LinearLayout llTransfer;
        @BindView(R.id.ll_tuihuo)
        LinearLayout llTuihuo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
