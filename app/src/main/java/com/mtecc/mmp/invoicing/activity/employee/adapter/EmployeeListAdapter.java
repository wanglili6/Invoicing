package com.mtecc.mmp.invoicing.activity.employee.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeSeeActivity;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class EmployeeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<EmployeeListBean.DataBean> mList;


    public EmployeeListAdapter(Context mContext, List<EmployeeListBean.DataBean> mList) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.employee_list_iteam, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final EmployeeListBean.DataBean dataBean = mList.get(position);
        holder.employeeTvName.setText(dataBean.getUsername());
        holder.employeeTvCode.setText(dataBean.getCardnum());
        holder.employeeTvPhone.setText(dataBean.getTelphone());
        String empstate = dataBean.getEmpstate();
        if (empstate.equals("0")) {
            holder.employeeTvStatus.setText("正常");
        } else {
            holder.employeeTvStatus.setText("注销");
        }
        holder.employeeTvShop.setText(dataBean.getShopname());


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.employee_img)
        ImageView employeeImg;
        @BindView(R.id.employee_tv_name)
        TextView employeeTvName;
        @BindView(R.id.employee_tv_code)
        TextView employeeTvCode;
        @BindView(R.id.employee_tv_phone)
        TextView employeeTvPhone;
        @BindView(R.id.employee_tv_status)
        TextView employeeTvStatus;
        @BindView(R.id.employee_tv_shop)
        TextView employeeTvShop;
        @BindView(R.id.shop_list_ll)
        LinearLayout shopListLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
