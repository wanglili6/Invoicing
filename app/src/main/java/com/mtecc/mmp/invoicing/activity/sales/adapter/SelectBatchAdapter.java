package com.mtecc.mmp.invoicing.activity.sales.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 批次列表的适配器
 */

public class SelectBatchAdapter extends BaseAdapter {
    private Context mContext;
    private List<BatchBean.DataBean> batchBeenList;
    private TextView tvSelectNames;
    private TextView imgSelectNum;

    public SelectBatchAdapter(Context mContext, List<BatchBean.DataBean> batchBeenList, TextView tvSelectNames, TextView imgSelectNum) {
        this.mContext = mContext;
        this.batchBeenList = batchBeenList;
        this.tvSelectNames = tvSelectNames;
        this.imgSelectNum = imgSelectNum;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.batch_msg_iterm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.selectCommodityLl.setVisibility(View.VISIBLE);
        holder.tvSee.setVisibility(View.GONE);
        final BatchBean.DataBean dataBean = batchBeenList.get(position);
        holder.etNum.setTag(position);
        final ViewHolder finalHolder = holder;
        holder.etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int pos = (int) finalHolder.etNum.getTag();
                if (!TextUtils.isEmpty(s.toString())) {
                    batchBeenList.get(pos).setNum(Integer.parseInt(s.toString()));
                    setNumMoney();
                }
            }
        });
        holder.etNum.setText(dataBean.getNum() + "");
        String num = dataBean.getNum() + "";


        holder.batchMsgTvName.setText(dataBean.getBatchnum());
        holder.batchMsgTvTimer.setText(dataBean.getBatchdateStr());
        String enterprice = dataBean.getEnterprice();
        String saleprice = dataBean.getSaleprice();
        String sellprice = dataBean.getSellprice();
        if (TextUtils.isEmpty(enterprice)){
            enterprice="0.0";
        } if (TextUtils.isEmpty(saleprice)){
            saleprice="0.0";
        } if (TextUtils.isEmpty(sellprice)){
            sellprice="0.0";
        }
        holder.batchMsgTvJiage.setText("进货价: " + enterprice + "\r\n" + "批发价价: " + saleprice + "\r\n" + "零售价: " + sellprice);
        holder.imgAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = dataBean.getNum();
                num++;
                finalHolder.etNum.setText(num + "");
                setNumMoney();
            }
        });


        holder.imgJianNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = dataBean.getNum();
                num--;
                if (num < 0) {
                    finalHolder.etNum.setText("0");
                    Toast.makeText(mContext, "不能低于0!", Toast.LENGTH_SHORT).show();
                    return;
                }
                finalHolder.etNum.setText(num + "");
                setNumMoney();
            }
        });

        return convertView;
    }

    /**
     * 设置个数和钱数
     */
    private void setNumMoney() {
        int totalNum = 0;
        double totalmoney = 0.0;
        for (int i = 0; i < batchBeenList.size(); i++) {
            BatchBean.DataBean value = batchBeenList.get(i);
            String nums = value.getNum() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                totalNum = totalNum + value.getNum();
                String enterprice = value.getEnterprice();
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(value.getEnterprice());
                    double selectNum = Double.parseDouble(String.valueOf(value.getNum()));
                    BigDecimal enterBigDecimal = new BigDecimal(Double.toString(entermoney));
                    BigDecimal selectNumBigDecimal = new BigDecimal(Double.toString(selectNum));
                    BigDecimal totalmoneyBigDecimal = new BigDecimal(Double.toString(totalmoney));
                    BigDecimal multiply = enterBigDecimal.multiply(selectNumBigDecimal);
                    BigDecimal add = totalmoneyBigDecimal.add(multiply);
                    totalmoney = add.doubleValue();
                } else {
                    totalmoney = totalmoney + value.getNum() * 0.0;
                }
            }
        }
        imgSelectNum.setText(totalNum + "");
        tvSelectNames.setText(totalmoney + "");
        LogUtils.i("个数===" + totalNum + "钱数----" + totalmoney);
    }


    static class ViewHolder {
        @BindView(R.id.batch_msg_tv_name)
        TextView batchMsgTvName;
        @BindView(R.id.batch_msg_tv_timer)
        TextView batchMsgTvTimer;
        @BindView(R.id.batch_msg_tv_jiage)
        TextView batchMsgTvJiage;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.img_jian_num)
        ImageView imgJianNum;
        @BindView(R.id.et_num)
        EditText etNum;
        @BindView(R.id.img_add_num)
        ImageView imgAddNum;
        @BindView(R.id.select_commodity_ll)
        LinearLayout selectCommodityLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
