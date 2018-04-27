package com.mtecc.mmp.invoicing.activity.role.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.role.bean.RoleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/25.
 */

public class RoleListAdapter extends BaseAdapter {
    private Context mContext;
    private List<RoleBean.DataBean> mList;

    public RoleListAdapter(Context mContext, List<RoleBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        RoleViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.role_list_iteam, parent, false);
            holder = new RoleViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RoleViewHolder) convertView.getTag();
        }

        RoleBean.DataBean dataBean = mList.get(position);
        holder.roleTvName.setText(dataBean.getUsergpname());
        holder.roleTvIsActivity.setText("状态: " + dataBean.getIsactive());
        holder.roleTvDescription.setText("描述: " + dataBean.getUsergpdesc());
        return convertView;
    }

    static class RoleViewHolder {
        @BindView(R.id.role_tv_name)
        TextView roleTvName;
        @BindView(R.id.role_tv_description)
        TextView roleTvDescription;
        @BindView(R.id.role_tv_isactivity)
        TextView roleTvIsActivity;

        RoleViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
