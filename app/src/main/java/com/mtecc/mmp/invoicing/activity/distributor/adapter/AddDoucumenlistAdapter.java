package com.mtecc.mmp.invoicing.activity.distributor.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.SeeImgActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicBean;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicListBean;
import com.mtecc.mmp.invoicing.activity.distributor.AddDistributorActivity;
import com.mtecc.mmp.invoicing.activity.distributor.AddDocumentActivity;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributorBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/7.
 */

public class AddDoucumenlistAdapter extends BaseAdapter {
    private Context mContext;
    private List<DistributorBean.CardBean> mList;
    private String type;

    public AddDoucumenlistAdapter(Context mContext, List<DistributorBean.CardBean> mList, String type, int editPiostion) {
        this.mContext = mContext;
        this.mList = mList;
        this.type = type;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.add_dcoucument_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final DistributorBean.CardBean cardBean = mList.get(position);
        holder.tvDoucumentType.setText(cardBean.getCardtypeName());
        holder.tvDoucumentNum.setText(cardBean.getMercardnum());
        holder.tvDoucumentTimer.setText(cardBean.getEnddate());
        if (type.equals("see")) {
            holder.tvDoucumentEdit.setVisibility(View.GONE);
            holder.tvDoucumentDel.setVisibility(View.GONE);
        } else {
            holder.tvDoucumentEdit.setVisibility(View.VISIBLE);
            holder.tvDoucumentDel.setVisibility(View.VISIBLE);
        }

        holder.tvDoucumentSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imgUrl = new ArrayList<String>();
                imgUrl.add(0, cardBean.getImgUrl());
                Intent intent = new Intent();
                intent.setClass(mContext, SeeImgActivity.class);
                intent.putStringArrayListExtra("imgurl", imgUrl);
                mContext.startActivity(intent);
            }
        });

        holder.tvDoucumentDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                View exitView = ShowDalogUtils.showCustomizeDialog(mContext, R.layout.exit_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(mContext, false, exitView);
                exitClick(exitView, dialog, cardBean);
            }
        });

        holder.tvDoucumentEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDistributorActivity addDistributorActivity = (AddDistributorActivity) mContext;
                Intent doucumentintent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(InvoicingConstants.DOUCUMENT_TYPE, InvoicingConstants.DOUCUMENT_EDIT);
                bundle.putInt("postion", position);
                bundle.putSerializable("cardBean", (Serializable) mList.get(position));
                doucumentintent.putExtras(bundle);
                doucumentintent.setClass(mContext, AddDocumentActivity.class);
                addDistributorActivity.startActivityForResult(doucumentintent, 1);
            }
        });
        return convertView;
    }

    private void exitClick(View exitView, final AlertDialog dialog, final DistributorBean.CardBean batchPicBean) {

        TextView contactTV = (TextView) exitView.findViewById(R.id.dialog_tv_contant);
        TextView dissTV = (TextView) exitView.findViewById(R.id.tv_diss);
        TextView sureTV = (TextView) exitView.findViewById(R.id.tv_sure);
        contactTV.setText("是否删除当前证件?");
        dissTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mList.remove(batchPicBean);
                notifyDataSetChanged();
            }
        });
    }

    static class ViewHolder {
        @BindView(R.id.tv_doucument_type)
        TextView tvDoucumentType;
        @BindView(R.id.tv_doucument_num)
        TextView tvDoucumentNum;
        @BindView(R.id.tv_doucument_timer)
        TextView tvDoucumentTimer;
        @BindView(R.id.tv_doucument_see)
        TextView tvDoucumentSee;
        @BindView(R.id.tv_doucument_edit)
        TextView tvDoucumentEdit;
        @BindView(R.id.tv_doucument_del)
        TextView tvDoucumentDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
