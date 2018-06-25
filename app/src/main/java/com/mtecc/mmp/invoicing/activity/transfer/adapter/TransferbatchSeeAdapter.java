package com.mtecc.mmp.invoicing.activity.transfer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferBatchBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/30.
 */

public class TransferbatchSeeAdapter extends BaseAdapter {
    private Context mContext;
    private List<TransferBatchBean.DataBean> mList;
    private String stockType;

    public TransferbatchSeeAdapter(Context mContext, List<TransferBatchBean.DataBean> mList, String stockType) {
        this.mContext = mContext;
        this.mList = mList;
        this.stockType = stockType;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.out_bound_batch_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TransferBatchBean.DataBean dataBean = mList.get(position);
        TransferBatchBean.DataBean.CshopBean cshop = dataBean.getCshop();
        TransferBatchBean.DataBean.RshopBean rshop = dataBean.getRshop();
        //调拨
        holder.llIncomeShop.setVisibility(View.VISIBLE);
        holder.tvNumText.setText("调拨数量:");
        holder.tvTimerText.setText("调拨时间:");
        holder.tvUserText.setText("调拨人:");
        if (cshop != null) {
            holder.tvOutShop.setText(cshop.getShopname());
        }
        if (rshop != null) {
            holder.tvInconmeShop.setText(rshop.getShopname());
        }
        holder.tvTranferUser.setText(dataBean.getDbuser().getUsername());


        String tranStatus = dataBean.getTranStatus();
        holder.batchTvName.setText(dataBean.getTranBatch());


        holder.tvTranferTime.setText(dataBean.getDbdateStr());
        holder.tvTranferNum.setText(dataBean.getDbqty() + "");
        if (!TextUtils.isEmpty(tranStatus) && tranStatus.equals("0")) {
            holder.imgCheck.setBackgroundResource(R.mipmap.tranfered_icon);
            holder.tvCheck.setTextColor(mContext.getResources().getColor(R.color.check_green_color));
            holder.tvCheck.setText("已完成");
        } else if (!TextUtils.isEmpty(tranStatus) && tranStatus.equals("1")) {
            holder.imgCheck.setBackgroundResource(R.mipmap.tranfering_icon);
            holder.tvCheck.setTextColor(mContext.getResources().getColor(R.color.check_yellow_color));
            if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
                //调拨
                holder.tvCheck.setText("调拨中");

            } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
                //出库
                holder.tvCheck.setText("出库中");

            }
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.batch_tv_name)
        TextView batchTvName;
        @BindView(R.id.tv_out_shop)
        TextView tvOutShop;
        @BindView(R.id.ll_income_shop)
        LinearLayout llIncomeShop;
        @BindView(R.id.tv_inconme_shop)
        TextView tvInconmeShop;
        @BindView(R.id.tv_timer_text)
        TextView tvTimerText;
        @BindView(R.id.tv_tranfer_time)
        TextView tvTranferTime;
        @BindView(R.id.tv_user_text)
        TextView tvUserText;
        @BindView(R.id.tv_tranfer_user)
        TextView tvTranferUser;
        @BindView(R.id.tv_num_text)
        TextView tvNumText;
        @BindView(R.id.tv_tranfer_num)
        TextView tvTranferNum;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.tv_check)
        TextView tvCheck;
        @BindView(R.id.out_bound_list_ll)
        LinearLayout outBoundListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
