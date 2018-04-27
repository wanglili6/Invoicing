package com.mtecc.mmp.invoicing.activity.employee.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.employee.EmployeeSeeActivity;
import com.mtecc.mmp.invoicing.activity.employee.bean.EmployeeListBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    private Context mContext;
    private List<EmployeeListBean.DataBean> mList;


    public EmployeeListAdapter(Context mContext, List<EmployeeListBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.employee_list_iteam, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeListAdapter.ViewHolder holder, int position) {
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

        holder.shopListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(InvoicingConstants.selectuserid, dataBean);
                bundle.putString(InvoicingConstants.Employee_List_TYPE, InvoicingConstants.companyEmployeeAdd);
                //TODO:跳转查看员工详情
                intent.setClass(mContext, EmployeeSeeActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
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
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
