package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PrichaseIncomeBean;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.PurchaseListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class CheckListAdapter extends BaseAdapter {
    private Context mContext;
    private List<PrichaseIncomeBean.DataBean> mList;
    private String checktype;
    private String state;

    public CheckListAdapter(Context mContext, List<PrichaseIncomeBean.DataBean> mList, String type, String state) {
        this.mContext = mContext;
        this.mList = mList;
        this.checktype = type;
        this.state = state;
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
        PrichaseIncomeBean.DataBean dataBean = mList.get(position);
        holder.purchaseTvName.setText(dataBean.getHdid() + "");
        holder.purchaseTvMoney.setText(dataBean.getSum() + "");
        holder.purchaseTvjinhuoshang.setText(dataBean.getMername() + "");
        holder.purchaseTvTimer.setText(dataBean.getHddate() + "");
        String state = dataBean.getState();
        if (checktype.equals(InvoicingConstants.PURCHASE)) {
            holder.tvMoneyText.setText("采购金额:");
            holder.tvUserText.setText("进货商:");
            if (state.equals("0")) {
                holder.purchaseImg.setBackgroundResource(R.mipmap.up_jin_icon);
            } else if (state.equals("1")) {
                holder.purchaseImg.setBackgroundResource(R.mipmap.tui_icon);
            }
        } else if (checktype.equals(InvoicingConstants.SALES)) {
            holder.tvMoneyText.setText("销售金额:");
            holder.tvUserText.setText("分销商:");
            if (state.equals("0")) {
                holder.purchaseImg.setBackgroundResource(R.mipmap.out_xiao_icon);
            } else if (state.equals("1")) {
                holder.purchaseImg.setBackgroundResource(R.mipmap.tui_icon);
            }
        }
        holder.llPurchaseStates.setVisibility(View.VISIBLE);
        if (state.equals("2")) {
            holder.imgCheck.setBackgroundResource(R.mipmap.dai_shenhe_icon);
            holder.tvCheck.setText("待审核");
            holder.tvCheck.setTextColor(mContext.getResources().getColor(R.color.check_yellow_color));
        } else if (state.equals("3")) {
            holder.imgCheck.setBackgroundResource(R.mipmap.shenhe_no_icon);
            holder.tvCheck.setText("未通过");
            holder.tvCheck.setTextColor(mContext.getResources().getColor(R.color.check_red_color));

        } else if (state.equals("1")) {
            holder.imgCheck.setBackgroundResource(R.mipmap.shenhe_tongguo_icon);
            holder.tvCheck.setText("已完成");
            holder.tvCheck.setTextColor(mContext.getResources().getColor(R.color.check_green_color));
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.purchase_img)
        ImageView purchaseImg;
        @BindView(R.id.purchase_tv_name)
        TextView purchaseTvName;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.tv_check)
        TextView tvCheck;
        @BindView(R.id.purchase_tv_money)
        TextView purchaseTvMoney;
        @BindView(R.id.purchase_tv_jinhuoshang)
        TextView purchaseTvjinhuoshang;
        @BindView(R.id.purchase_tv_timer)
        TextView purchaseTvTimer;
        @BindView(R.id.tv_user_text)
        TextView tvUserText;
        @BindView(R.id.tv_money_text)
        TextView tvMoneyText;
        @BindView(R.id.img_purchase)
        LinearLayout imgPurchase;
        @BindView(R.id.purchase_list_ll)
        LinearLayout purchaseListLl;
        @BindView(R.id.ll_purchase_states)
        LinearLayout llPurchaseStates;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
