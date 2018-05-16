package com.mtecc.mmp.invoicing.activity.distributor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributionListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/7.
 */

public class DistributorlistAdapter extends BaseAdapter {
    private Context mContext;
    private List<DistributionListBean.DataBean> mList;
    private String merchants_Distributor_type;

    public DistributorlistAdapter(Context mContext, List<DistributionListBean.DataBean> mList, String merchants_Distributor_type) {
        this.mContext = mContext;
        this.mList = mList;
        this.merchants_Distributor_type = merchants_Distributor_type;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.distributor_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (merchants_Distributor_type.equals(InvoicingConstants.Merchants_TYPE)) {
            holder.imgCode.setBackgroundResource(R.mipmap.code_merchants_icon);
            holder.imgPerson.setBackgroundResource(R.mipmap.person_merchants);
            holder.imgPhone.setBackgroundResource(R.mipmap.phone_merchants);
            holder.imgAddress.setBackgroundResource(R.mipmap.address_merchants_icon);
        }

        DistributionListBean.DataBean dataBean = mList.get(position);
        holder.distributionTvName.setText(dataBean.getMername() + "");
        holder.distributionTvCode.setText(dataBean.getEntregno() + "");
        holder.distributionTvFaren.setText(dataBean.getContacts() + "");
        holder.distributionTvPhone.setText(dataBean.getContactstel() + "");
        holder.distributionTvAddress.setText(dataBean.getAddress() + "");

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.distribution_tv_name)
        TextView distributionTvName;
        @BindView(R.id.img_code)
        ImageView imgCode;
        @BindView(R.id.distribution_tv_code)
        TextView distributionTvCode;
        @BindView(R.id.img_person)
        ImageView imgPerson;
        @BindView(R.id.tv_person)
        TextView tvPerson;
        @BindView(R.id.distribution_tv_faren)
        TextView distributionTvFaren;
        @BindView(R.id.img_phone)
        ImageView imgPhone;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.distribution_tv_phone)
        TextView distributionTvPhone;
        @BindView(R.id.img_address)
        ImageView imgAddress;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.distribution_tv_address)
        TextView distributionTvAddress;
        @BindView(R.id.distribution_list_ll)
        LinearLayout distributionListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
