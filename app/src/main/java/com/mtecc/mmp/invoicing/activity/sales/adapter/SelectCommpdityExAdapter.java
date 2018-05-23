package com.mtecc.mmp.invoicing.activity.sales.adapter;

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
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityListBean;
import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/21.
 */

public class SelectCommpdityExAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CommodityListBean.DataBean> mSelectCommodityList;
    private OnDelListerner ondelListerner;
    private OnEditBtachListerner onEditBtachLIsterner;
    private OnDelBtachListerner onDelBtachLIsterner;

    public SelectCommpdityExAdapter(Context mContext, List<CommodityListBean.DataBean> mSelectCommodityList) {
        this.mContext = mContext;
        this.mSelectCommodityList = mSelectCommodityList;
    }

    @Override
    public int getGroupCount() {
        return mSelectCommodityList != null ? mSelectCommodityList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mSelectCommodityList.get(groupPosition).getmSelectMap() != null ? mSelectCommodityList.get(groupPosition).getmSelectMap().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mSelectCommodityList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSelectCommodityList.get(groupPosition).getmSelectMap().get(childPosition);
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
        final CommodityListBean.DataBean dataBean = mSelectCommodityList.get(groupPosition);

        groupViewHolder.commodityTvName.setText(dataBean.getProName() + "");
        groupViewHolder.commodityTvCoding.setText(dataBean.getProCode() + "");
        groupViewHolder.commodityTvNorm.setText(dataBean.getMeas() + "/" + dataBean.getMeaunit());
        groupViewHolder.commoditytvNum.setText(dataBean.getmSelectNum() + "");
        groupViewHolder.commodityTvTotalNum.setText(dataBean.getmSelectMoney() + "");
        groupViewHolder.commodityImgUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除点前商品
                LogUtils.i("删除当前" + dataBean.getProName() + "==" + groupPosition);
                if (ondelListerner != null) {
                    ondelListerner.onDelClick(groupPosition);
                }
            }
        });
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
        final SelectBatchBean batchBean = mSelectCommodityList.get(groupPosition).getmSelectMap().get(childPosition);
        childViewHolder.batchExTvName.setText(batchBean.getBatchnum());
        childViewHolder.batchExTvNum.setText("数量: " + batchBean.getNum());
        childViewHolder.batchExTvMoney.setText("进货价: " + batchBean.getEnterprice());
        String enterprice = batchBean.getEnterprice();
        double entermoney = 0.0;
        if (TextUtils.isEmpty(enterprice)) {
            entermoney = 0.0;
        } else {
            entermoney = Double.parseDouble(enterprice);
        }
        double selectNum = Double.parseDouble(String.valueOf(batchBean.getNum()));
        BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
        BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
        BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
        childViewHolder.batchExTvTotalMoney.setText("金额: " + multiply.doubleValue());
        childViewHolder.batchExExLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("点击批次" + batchBean.getBatchnum() + "==" + childPosition);
                if (onEditBtachLIsterner != null) {
                    onEditBtachLIsterner.onEditClick(groupPosition, childPosition);
                }
            }
        });
        childViewHolder.btachImgUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("点击批次" + batchBean.getBatchnum() + "==" + childPosition);
                if (onDelBtachLIsterner != null) {
                    onDelBtachLIsterner.onDelBtachClick(groupPosition, childPosition);
                }
            }
        });
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
