package com.mtecc.mmp.invoicing.activity.check;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.check.bean.SeeOrdersBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/21.
 */

public class SeeCommpdityExAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<SeeOrdersBean.MSelectCommodityMapBean> mSelectCommodityList;
    private OnDelListerner ondelListerner;
    private OnEditBtachListerner onEditBtachLIsterner;
    private OnDelBtachListerner onDelBtachLIsterner;

    public SeeCommpdityExAdapter(Context mContext, List<SeeOrdersBean.MSelectCommodityMapBean> mSelectCommodityList) {
        this.mContext = mContext;
        this.mSelectCommodityList = mSelectCommodityList;
    }

    @Override
    public int getGroupCount() {
        return mSelectCommodityList != null ? mSelectCommodityList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mSelectCommodityList.get(groupPosition).getMSelectMap() != null ? mSelectCommodityList.get(groupPosition).getMSelectMap().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mSelectCommodityList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSelectCommodityList.get(groupPosition).getMSelectMap().get(childPosition);
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.select_commodity_group, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final SeeOrdersBean.MSelectCommodityMapBean dataBean = mSelectCommodityList.get(groupPosition);

        groupViewHolder.commodityTvName.setText(dataBean.getProName() + "");
        groupViewHolder.commodityTvCoding.setText(dataBean.getProCode() + "");
        groupViewHolder.commodityTvNorm.setText(dataBean.getMeas() + "/" + dataBean.getMeaunit());
        groupViewHolder.commoditytvNum.setText(dataBean.getmSelectNum() + "");
        groupViewHolder.commodityTvTotalNum.setText(dataBean.getmSelectMoney() + "");
        groupViewHolder.commodityImgUpDown.setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.select_commodity_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final SeeOrdersBean.MSelectCommodityMapBean.MSelectMapBean mSelectMapBean = mSelectCommodityList.get(groupPosition).getMSelectMap().get(childPosition);
        childViewHolder.batchExTvName.setText(mSelectMapBean.getBatchnum());
        childViewHolder.batchExTvNum.setText("数量: " + mSelectMapBean.getNum());
        childViewHolder.batchExTvMoney.setText("进货价: " + mSelectMapBean.getEnterprice());
        String enterprice = mSelectMapBean.getEnterprice();
        double entermoney = 0.0;
        if (TextUtils.isEmpty(enterprice)) {
            entermoney = 0.0;
        } else {
            entermoney = Double.parseDouble(enterprice);
        }
        double selectNum = Double.parseDouble(String.valueOf(mSelectMapBean.getNum()));
        BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
        BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
        BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
        childViewHolder.batchExTvTotalMoney.setText("金额: " + multiply.doubleValue());
        childViewHolder.btachImgUpDown.setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @BindView(R.id.commodity_img)
        ImageView commodityImg;
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.commodity_img_num)
        TextView commodityImgNum;
        @BindView(R.id.commodity_tv_coding)
        TextView commodityTvCoding;
        @BindView(R.id.commodity_tv_norm)
        TextView commodityTvNorm;
        @BindView(R.id.commodity_tv_total_num)
        TextView commodityTvTotalNum;
        @BindView(R.id.commodity_tv_num)
        TextView commoditytvNum;
        @BindView(R.id.commodity_img_up_down)
        ImageView commodityImgUpDown;
        @BindView(R.id.commodity_ex_ll)
        LinearLayout commodityExLl;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.batch_ex_tv_name)
        TextView batchExTvName;
        @BindView(R.id.batch_ex_tv_num)
        TextView batchExTvNum;
        @BindView(R.id.batch_ex_tv_money)
        TextView batchExTvMoney;
        @BindView(R.id.btach_img_up_down)
        ImageView btachImgUpDown;
        @BindView(R.id.batch_ex_tv_total_money)
        TextView batchExTvTotalMoney;
        @BindView(R.id.batch_ex_ex_ll)
        LinearLayout batchExExLl;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public static interface OnDelListerner {
        void onDelClick(int groupPostion);
    }

    public OnDelListerner getOndelListerner() {
        return ondelListerner;
    }

    public void setOndelListerner(OnDelListerner ondelListerner) {
        this.ondelListerner = ondelListerner;
    }

    public static interface OnEditBtachListerner {
        void onEditClick(int groupPostion, int childPostion);
    }

    public void setOnEditBtachLIsterner(OnEditBtachListerner onEditBtachLIsterner) {
        this.onEditBtachLIsterner = onEditBtachLIsterner;
    }

    public OnEditBtachListerner getOnEditBtachLIsterner() {
        return onEditBtachLIsterner;
    }

    public static interface OnDelBtachListerner {
        void onDelBtachClick(int groupPostion, int childPostion);
    }

    public OnDelBtachListerner getOnDelBtachLIsterner() {
        return onDelBtachLIsterner;
    }

    public void setOnDelBtachLIsterner(OnDelBtachListerner onDelBtachLIsterner) {
        this.onDelBtachLIsterner = onDelBtachLIsterner;
    }
}
