package com.mtecc.mmp.invoicing.activity.employee.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.mtecc.mmp.invoicing.activity.shop.ShopSeeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {
    private Context mContext;
    private List<EmployeeListBean.DataBean> mList;

    public EmployeeListAdapter(Context mContext, List<EmployeeListBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public EmployeeListAdapter.EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.employee_list_iteam, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeListAdapter.EmployeeViewHolder holder, int position) {
        EmployeeListBean.DataBean dataBean = mList.get(position);
        holder.employeeTvName.setText(dataBean.getUsername() + "(" + dataBean.getRole() + ")");
        holder.employeeTvCode.setText(dataBean.getCardnum());
        holder.employeeTvPhone.setText(dataBean.getTelphone());
        holder.employeeTvStatus.setText(dataBean.getEmpstate());
        holder.employeeTvShop.setText(dataBean.getEmpstate());
        holder.shopListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:跳转查看店铺详情
                Intent intent = new Intent();
                intent.setClass(mContext, EmployeeSeeActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
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

        EmployeeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
