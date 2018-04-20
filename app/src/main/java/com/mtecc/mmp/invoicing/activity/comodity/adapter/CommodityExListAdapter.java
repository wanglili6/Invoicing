package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class CommodityExListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CommodityBean> mList;

    public CommodityExListAdapter(Context mContext, List<CommodityBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getGroupCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getNameList() != null ? mList.get(groupPosition).getNameList().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition).getTitle();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getNameList();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.commodity_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.commodityTvTypeName.setText(mList.get(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.commodity_list_iteam, parent, false);
            viewHolder = new ChildViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.commodityTvName.setText(mList.get(groupPosition).getNameList().get(childPosition));
        viewHolder.commodityListImgBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mList.get(groupPosition).getNameList().get(childPosition), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(mContext, AddBatchActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ViewHolder {
        @BindView(R.id.commodity_tv_type_name)
        TextView commodityTvTypeName;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.commodity_img)
        ImageView commodityImg;
        @BindView(R.id.commodity_tv_name)
        TextView commodityTvName;
        @BindView(R.id.commodity_tv_norm)
        TextView commodityTvNorm;
        @BindView(R.id.commodity_tv_brands)
        TextView commodityTvBrands;
        @BindView(R.id.commodity_tv_price)
        TextView commodityTvPrice;
        @BindView(R.id.commodity_list_img_batch)
        ImageView commodityListImgBatch;
        @BindView(R.id.commodity_list_ll)
        LinearLayout commodityListLl;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
