package com.mtecc.mmp.invoicing.activity.purchaseOrSales.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.ReturnListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/30.
 * 退货记录的适配器
 */

public class ReturnsExListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ReturnListBean.DataBean> mGroupList;
    private String stockType;


    public ReturnsExListAdapter(Context mContext, List<ReturnListBean.DataBean> mGroupList, String stockType) {
        this.mContext = mContext;
        this.mGroupList = mGroupList;
        this.stockType = stockType;
    }

    @Override
    public int getGroupCount() {
        return mGroupList != null ? mGroupList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupList.get(groupPosition).getGoodsBacth() != null ? mGroupList.get(groupPosition).getGoodsBacth().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition) != null ? mGroupList.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupList.get(groupPosition).getGoodsBacth() != null ? mGroupList.get(groupPosition).getGoodsBacth().get(childPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.return_group_iteatm, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        ReturnListBean.DataBean dataBean = mGroupList.get(groupPosition);
        //商品名字
        groupViewHolder.commodityTvName.setText(dataBean.getProname() + "");
        //商品编号:TODO
        String barcode = dataBean.getBarcode();
        String procode = dataBean.getProcode();
        if (TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(procode)) {
            groupViewHolder.returnProCode.setText(procode + "");
        }
        if (TextUtils.isEmpty(procode) && !TextUtils.isEmpty(barcode)) {
            groupViewHolder.returnProCode.setText(barcode + "");
        }

        //商品数量
        groupViewHolder.returnsNum.setText(dataBean.getSumqty() + "");
        //商品总额
        groupViewHolder.returnMoney.setText(dataBean.getSumprice() + "");
        //订单编号
        groupViewHolder.returnTvCode.setText(dataBean.getHdid() + "");
        //退货原因
//        groupViewHolder.returnReason.setText(dataBean.getThreason() + "");
        if (stockType.equals(InvoicingConstants.PURCHASE)) {
            groupViewHolder.returnTvUserName.setText("供货商:" + dataBean.getMername() + "");
        } else if (stockType.equals(InvoicingConstants.SALES)) {
            groupViewHolder.returnTvUserName.setText("分销商:" + dataBean.getMername() + "");

        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.return_child_itdream, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.returnChildReason.setVisibility(View.VISIBLE);
        ReturnListBean.DataBean.GoodsBacthBean goodsBacthBean = mGroupList.get(groupPosition).getGoodsBacth().get(childPosition);
        //商品名字
        childViewHolder.commodityTvName.setText("批次: " + goodsBacthBean.getBatchdate() + "");
        //商品编号:TODO
        //原因
        childViewHolder.returnChildReason.setText("原因: " + goodsBacthBean.getThreason() + "");
        childViewHolder.returnChildCode.setText("时间: " + goodsBacthBean.getThdate() + "");
        //商品数量
        childViewHolder.returnsChildNum.setText("数量: " + goodsBacthBean.getThqty() + "");
        //商品总额
        childViewHolder.returnChildMoney.setText("价格: " + goodsBacthBean.getThprice() + "");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.return_pro_code)
        TextView returnProCode;
        @BindView(R.id.returns_num)
        TextView returnsNum;
        @BindView(R.id.return_money)
        TextView returnMoney;
        @BindView(R.id.ll_money_text)
        LinearLayout llMoneyText;
        @BindView(R.id.return_tv_code)
        TextView returnTvCode;
        @BindView(R.id.return_reason)
        TextView returnReason;
        @BindView(R.id.return_tv_user_name)
        TextView returnTvUserName;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    static class ChildViewHolder {
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.return_child_code)
        TextView returnChildCode;
        @BindView(R.id.return_child_reason)
        TextView returnChildReason;
        @BindView(R.id.returns_child_num)
        TextView returnsChildNum;
        @BindView(R.id.return_child_money)
        TextView returnChildMoney;
        @BindView(R.id.batch_ex_ex_ll)
        LinearLayout batchExExLl;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
