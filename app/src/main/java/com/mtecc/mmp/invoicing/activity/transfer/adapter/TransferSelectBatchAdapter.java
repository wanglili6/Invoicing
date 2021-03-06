package com.mtecc.mmp.invoicing.activity.transfer.adapter;

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
import com.mtecc.mmp.invoicing.activity.transfer.bean.TransferGoodsBatchBean;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/5/3.
 * 批次列表的适配器
 */

public class TransferSelectBatchAdapter extends BaseAdapter {
    private Context mContext;
    private List<TransferGoodsBatchBean.DataBean> batchBeenList;
    private TextView tvSelectNames;
    private TextView imgSelectNum;

    public TransferSelectBatchAdapter(Context mContext, List<TransferGoodsBatchBean.DataBean> batchBeenList, TextView tvSelectNames, TextView imgSelectNum) {
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
        holder.llSnum.setVisibility(View.VISIBLE);
        holder.tvSee.setVisibility(View.GONE);
        final TransferGoodsBatchBean.DataBean dataBean = batchBeenList.get(position);
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
                //TODO:判断调出量是否多于剩余库存
                if (!TextUtils.isEmpty(s.toString())) {
                    int num = Integer.parseInt(s.toString());
                    int kcsum = dataBean.getKcsum();
                    if (kcsum < num) {
                        batchBeenList.get(pos).setNum(0);
                        Toast.makeText(mContext, "不能高于剩余库存数!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    batchBeenList.get(pos).setNum(num);
                    setNumMoney();
                }
            }
        });
        holder.etNum.setText(dataBean.getNum() + "");
        //剩余库存
        holder.batchMsgTvSnum.setText(dataBean.getKcsum() + "");
        holder.batchMsgTvTimer.setText(dataBean.getPbatchid().getBatchnum());
        holder.batchMsgTvName.setText(dataBean.getPbatchid().getBatchdateStr());
        String enterprice = dataBean.getPbatchid().getEnterprice();
        String saleprice = dataBean.getPbatchid().getSaleprice();
        String sellprice = dataBean.getPbatchid().getSellprice();
        if (TextUtils.isEmpty(enterprice)) {
            enterprice = "0.0";
        }
        if (TextUtils.isEmpty(saleprice)) {
            saleprice = "0.0";
        }
        if (TextUtils.isEmpty(sellprice)) {
            sellprice = "0.0";
        }
        holder.batchMsgTvJiage.setText("进货价: " + enterprice + "\r\n" + "批发价价: " + saleprice + "\r\n" + "零售价: " + sellprice);
        holder.imgAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = dataBean.getNum();
                num++;
                //TODO:判断调出量是否多于剩余库存
                int kcsum = dataBean.getKcsum();
                if (kcsum < num) {
                    finalHolder.etNum.setText("0");
                    Toast.makeText(mContext, "不能高于剩余库存数!", Toast.LENGTH_SHORT).show();
                    return;
                }
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
            TransferGoodsBatchBean.DataBean value = batchBeenList.get(i);
            String nums = value.getNum() + "";
            if (!TextUtils.isEmpty(nums) && !nums.equals("0")) {
                totalNum = totalNum + value.getNum();
                String enterprice = value.getPbatchid().getSellprice();
                if (!TextUtils.isEmpty(enterprice) && !enterprice.equals("0")) {
                    double entermoney = Double.parseDouble(value.getPbatchid().getSellprice());
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
        if (totalNum > 99) {
            imgSelectNum.setText("99+");
        } else {
            imgSelectNum.setText(totalNum + "");

        }
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
        @BindView(R.id.batch_msg_tv_snum)
        TextView batchMsgTvSnum;
        @BindView(R.id.img_jian_num)
        ImageView imgJianNum;
        @BindView(R.id.et_num)
        EditText etNum;
        @BindView(R.id.img_add_num)
        ImageView imgAddNum;
        @BindView(R.id.select_commodity_ll)
        LinearLayout selectCommodityLl;
        @BindView(R.id.ll_snum)
        LinearLayout llSnum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
