package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 批次列表的适配器
 */

public class CoucumentListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DicBean.DataBean> batchBeenList;

    public CoucumentListAdapter(Context mContext, List<DicBean.DataBean> batchBeenList) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;
    }

    @Override
    public int getCount() {
        return batchBeenList != null ? batchBeenList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return batchBeenList != null ? batchBeenList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.zhengjian_msg_iterm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DicBean.DataBean dataBean = batchBeenList.get(position);
        holder.dialogTvYy.setText(dataBean.getBUSINNAME());
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.dialog_tv_yy)
        TextView dialogTvYy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
