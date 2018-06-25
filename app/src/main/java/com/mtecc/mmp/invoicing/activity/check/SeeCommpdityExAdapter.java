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

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean.SeePurchaserBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/21.
 */

public class SeeCommpdityExAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<SeePurchaserBean.DataBean> mSelectCommodityList;
    private OnDelListerner ondelListerner;
    private OnEditBtachListerner onEditBtachLIsterner;
    private OnDelBtachListerner onDelBtachLIsterner;
    private String checkType;

    public SeeCommpdityExAdapter(Context mContext, List<SeePurchaserBean.DataBean> mSelectCommodityList, String checkType) {
        this.mContext = mContext;
        this.mSelectCommodityList = mSelectCommodityList;
        this.checkType = checkType;
    }

    @Override
    public int getGroupCount() {
        return mSelectCommodityList != null ? mSelectCommodityList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mSelectCommodityList.get(groupPosition).getPclist() != null ? mSelectCommodityList.get(groupPosition).getPclist().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mSelectCommodityList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSelectCommodityList.get(groupPosition).getPclist().get(childPosition);
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
        SeePurchaserBean.DataBean dataBean = mSelectCommodityList.get(groupPosition);

        groupViewHolder.commodityTvName.setText(dataBean.getGood().getProName() + "");
        groupViewHolder.commodityTvCoding.setText(dataBean.getGood().getProCode() + "");
        groupViewHolder.commodityTvNorm.setText(dataBean.getGood().getMeas() + "/" + dataBean.getGood().getMeaunit());
        List<SeePurchaserBean.DataBean.PclistBean> pclist = dataBean.getPclist();
        int size = pclist.size();
        int selelctNum = 0;
        double totalmoney = 0.0;
        for (int i = 0; i < size; i++) {
            SeePurchaserBean.DataBean.PclistBean pclistBean = pclist.get(i);
            String enterprice = pclistBean.getEnterprice();
            int enterqty = Integer.parseInt(pclistBean.getEnterqty());
            selelctNum += enterqty;
            if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                double entermoney = Double.parseDouble(enterprice);
                double selectNum = Double.parseDouble(String.valueOf(pclistBean.getEnterqty()));
                BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
                BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                BigDecimal selectNum_Money = enterBigDecimal.multiply(selectNumBigDecimal);
                BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                BigDecimal add = totalmoneyBigDecimal.add(selectNum_Money);
                totalmoney = add.doubleValue();
            } else {
                totalmoney = totalmoney + enterqty * 0.0;
            }
        }
        groupViewHolder.commoditytvNum.setText(selelctNum + "");
        groupViewHolder.commodityTvTotalNum.setText(totalmoney + "");
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
        SeePurchaserBean.DataBean.PclistBean pclistBean = mSelectCommodityList.get(groupPosition).getPclist().get(childPosition);
        childViewHolder.batchExTvName.setText(pclistBean.getPbatchid().getBatchdateStr());
        childViewHolder.batchExTvNum.setText("数量: " + pclistBean.getEnterqty());
        if (checkType.equals(InvoicingConstants.check_purchases)) {
            childViewHolder.batchExTvMoney.setText("进货价: " + pclistBean.getEnterprice());
        } else if (checkType.equals(InvoicingConstants.check_sales)) {
            childViewHolder.batchExTvMoney.setText("零售价: " + pclistBean.getEnterprice());
        }
        String enterprice = pclistBean.getEnterprice();
        double entermoney = 0.0;
        if (TextUtils.isEmpty(enterprice)) {
            entermoney = 0.0;
        } else {
            entermoney = Double.parseDouble(enterprice);
        }
        double selectNum = Double.parseDouble(String.valueOf(pclistBean.getEnterqty()));
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
