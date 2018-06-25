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
import com.mtecc.mmp.invoicing.activity.transfer.bean.OutBoundBean;
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferBatchBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/30.
 */

public class OutbatchSeeAdapter extends BaseAdapter {
    private Context mContext;
    private List<OutBoundBean.DataBean> mList;
    private String stockType;

    public OutbatchSeeAdapter(Context mContext, List<OutBoundBean.DataBean> mList, String stockType) {
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
        OutBoundBean.DataBean dataBean = mList.get(position);
        OutBoundBean.DataBean.CkshopBean ckshop = dataBean.getCkshop();
        holder.llIncomeShop.setVisibility(View.GONE);
        holder.tvNumText.setText("出库数量:");
        holder.tvTimerText.setText("出库时间:");
        holder.tvUserText.setText("出库人:");
        if (ckshop != null) {
            holder.tvOutShop.setText(ckshop.getShopname());
        }
        holder.tvTranferUser.setText(dataBean.getCkuser().getUsername());
        holder.llStaust.setVisibility(View.GONE);
        holder.llCkreason.setVisibility(View.VISIBLE);
        String ckreason = dataBean.getCkreason();
        holder.tvCkreason.setText(ckreason + "");
        holder.batchTvName.setText(dataBean.getMoveBatch());
        holder.tvTranferTime.setText(dataBean.getCkdateStr());
        holder.tvTranferNum.setText(dataBean.getCkqty() + "");

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
        @BindView(R.id.tv_ckreason)
        TextView tvCkreason;
        @BindView(R.id.out_bound_list_ll)
        LinearLayout outBoundListLl;
        @BindView(R.id.ll_staust)
        LinearLayout llStaust;
        @BindView(R.id.ll_ckreason)
        LinearLayout llCkreason;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
