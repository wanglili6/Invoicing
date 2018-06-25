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
import com.mtecc.mmp.invoicing.activity.transfer.bean.TranferListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/30.
 */

public class TransferListAdapter extends BaseAdapter {
    private Context mContext;
    private List<TranferListBean.DataBean> mList;
    private String stockType;

    public TransferListAdapter(Context mContext, List<TranferListBean.DataBean> mList, String stockType) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.out_bound_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TranferListBean.DataBean dataBean = mList.get(position);
        if (stockType.equals(InvoicingConstants.STOCK_Transfer)) {
            holder.outBoundTvText.setText("调拨数量:");
            holder.outBoundTvNum.setText(dataBean.getDbqty() + "");
        } else if (stockType.equals(InvoicingConstants.STOCK_OutBound)) {
            holder.outBoundTvText.setText("出库数量:");
            holder.outBoundTvNum.setText(dataBean.getCkqty() + "");
        } else if (stockType.equals(InvoicingConstants.STOCK_return_purchase)) {
            holder.outBoundTvText.setText("退货数量:");
            holder.outBoundTvNum.setText(dataBean.getCkqty() + "");
        } else if (stockType.equals(InvoicingConstants.STOCK_returns_sales)) {
            holder.outBoundTvText.setText("退货数量:");
            holder.outBoundTvNum.setText(dataBean.getCkqty() + "");
        }
        holder.outBoundTvName.setText(dataBean.getSid().getProId().getProName());
        String barcode = dataBean.getSid().getProId().getBarcode();
        String proCode = dataBean.getSid().getProId().getProCode();
        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(proCode)) {
            holder.outBoundTvCode.setText(proCode + "");
        } else if (TextUtils.isEmpty(proCode) && !TextUtils.isEmpty(barcode)) {
            holder.outBoundTvCode.setText(barcode + "");
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.out_bound_tv_name)
        TextView outBoundTvName;
        @BindView(R.id.out_bound_tv_code)
        TextView outBoundTvCode;

        @BindView(R.id.out_bound_tv_num)
        TextView outBoundTvNum;
        @BindView(R.id.textView12)
        TextView outBoundTvText;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.tv_check)
        TextView tvCheck;
        @BindView(R.id.img_out_bound)
        LinearLayout imgOutBound;
        @BindView(R.id.out_bound_list_ll)
        LinearLayout outBoundListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
