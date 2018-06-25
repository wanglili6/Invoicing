package com.mtecc.mmp.invoicing.activity.distributor.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.CoucumentListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.DicBean;
import com.mtecc.mmp.invoicing.activity.distributor.bean.DistributorBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class DoucumentListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DistributorBean.CardBean> mList;
    private List<DicBean.DataBean> mDiclist;
    private TextView commondityTvCommit;
    private String type;
    IBatchImgOnClickListerner iBatchImgOnClickListerner;
    IBatchSeeImgListerner iBatchSeeImgListerner;
    IAddBatchOnClickListerner iAddBatchOnClickListerner;
    private final UseSystemUtils userSystemutils;

    public DoucumentListAdapter(Context mContext, List<DistributorBean.CardBean> mList, TextView commondityTvCommit, List<DicBean.DataBean> mDiclist, String type) {
        this.mContext = mContext;
        this.mList = mList;
        this.mDiclist = mDiclist;
        this.type = type;
        this.commondityTvCommit = commondityTvCommit;
        userSystemutils = new UseSystemUtils(mContext);

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.add_batch_pic_list_itdream, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.commodityDialogEtType.setTag(position);
        holder.commodityDialogEtCode.setTag(position);
        holder.commodityDialogEtTimer.setTag(position);
        final ViewHolder finalHolder = holder;
        holder.commodityDialogEtType.setText(mList.get(position).getCardtypeName());
        holder.commodityDialogEtType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtType.getTag();
                int size = mDiclist.size();
                for (int i = 0; i < size; i++) {
                    if (s.toString().equals(mDiclist.get(i).getBUSINNAME())) {
                        mList.get(pos).setMercardtype(mDiclist.get(i).getBUSINID() + "");
                        mList.get(pos).setCardtypeName(mDiclist.get(i).getBUSINNAME() + "");

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtCode.getTag();
                mList.get(pos).setMercardnum(s + "");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtTimer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtTimer.getTag();
                mList.get(pos).setEnddate(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if (type.equals(InvoicingConstants.DOUCUMENT_EDIT)) {
            holder.batchImgAddIteam.setVisibility(View.GONE);
            holder.batchImgDelIteam.setVisibility(View.GONE);
        } else if (type.equals(InvoicingConstants.DOUCUMENT_ADD)) {
            holder.batchImgAddIteam.setVisibility(View.VISIBLE);
            holder.batchImgDelIteam.setVisibility(View.VISIBLE);
        }

        holder.commodityDialogEtTimer.setText(mList.get(position).getEnddate());
        holder.commodityDialogEtCode.setText(mList.get(position).getMercardnum());
        Glide.with(mContext)
                .load(mList.get(position).getImgUrl())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.imgZhengjian);

        holder.commodityDialogEtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View selectView = ShowDalogUtils.showCustomizeDialog(mContext, R.layout.selelct_type_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(mContext, false, selectView);
                selectClick(selectView, dialog, finalHolder.commodityDialogEtType);
            }
        });


        holder.commodityDialogEtTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSystemutils.useSystemTimeNoHms(finalHolder.commodityDialogEtTimer);
            }
        });


        holder.batchImgAddIteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DistributorBean.CardBean cardBean = new DistributorBean.CardBean();
                cardBean.setMercardnum("");
                cardBean.setMercardtype("");
                cardBean.setRemark("");
                cardBean.setEnddate("");
                mList.add(cardBean);
                notifyDataSetChanged();


            }
        });
        holder.batchImgDelIteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.size() == 1) {
                    Toast.makeText(mContext, "至少一条信息!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mList.remove(position);
                notifyDataSetChanged();


            }
        });

        commondityTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enddate = mList.get(position).getMercardnum();
                if (TextUtils.isEmpty(enddate)) {
                    Toast.makeText(mContext, "证件编号不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (iAddBatchOnClickListerner != null) {
                    iAddBatchOnClickListerner.onAddBatchClick(mList);
                }


            }
        });

        holder.tvSelxectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iBatchImgOnClickListerner != null) {
                    iBatchImgOnClickListerner.onBatchImgClick(position, mList.get(position).getImgUrl(), mList);
                }


            }
        });

        holder.imgZhengjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iBatchSeeImgListerner != null) {
                    iBatchSeeImgListerner.onBatchSee(position, mList.get(position).getImgUrl());
                }


            }
        });


        return convertView;
    }

    /**
     * 选择类型
     *
     * @param selectView
     * @param dialog
     */
    private void selectClick(View selectView, final AlertDialog dialog, final EditText commodityDialogEtType) {
        ListView listZhengjianView = selectView.findViewById(R.id.list_zhengjian_view);
        ImageView
                tvX = selectView.findViewById(R.id.img_x_dialog);
        tvX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        CoucumentListAdapter coucumentListAdapter = new CoucumentListAdapter(mContext, mDiclist);
        listZhengjianView.setAdapter(coucumentListAdapter);
        coucumentListAdapter.notifyDataSetChanged();
        listZhengjianView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                DicBean.DataBean dataBean = mDiclist.get(position);
                commodityDialogEtType.setText(dataBean.getBUSINNAME() + "");
//                mList.get(position).setCardtype(dataBean.getBUSINID() + "");
            }
        });


    }

    public static interface IBatchImgOnClickListerner {
        public void onBatchImgClick(int position, String imgUrl, List<DistributorBean.CardBean> mList);
    }

    public void setiImgOnClickListerner(IBatchImgOnClickListerner iBatchImgOnClickListerner) {
        this.iBatchImgOnClickListerner = iBatchImgOnClickListerner;
    }

    public static interface IBatchSeeImgListerner {
        public void onBatchSee(int position, String imgUrl);
    }

    public void setiSeeOnClickListerner(IBatchSeeImgListerner iBatchDelOnClickListerner) {
        this.iBatchSeeImgListerner = iBatchDelOnClickListerner;
    }

    public IBatchSeeImgListerner getiBatchSeeOnClickListerner() {
        return iBatchSeeImgListerner;
    }

    public static interface IAddBatchOnClickListerner {
        public void onAddBatchClick(List<DistributorBean.CardBean> mPicimgList);
    }

    public void setiAddBatchOnClickListerner(IAddBatchOnClickListerner iAddBatchOnClickListerner) {
        this.iAddBatchOnClickListerner = iAddBatchOnClickListerner;
    }

    public IAddBatchOnClickListerner getiAddBatchOnClickListerner() {
        return iAddBatchOnClickListerner;
    }

    static class ViewHolder {
        @BindView(R.id.batch_img_add_iteam)
        ImageView batchImgAddIteam;
        @BindView(R.id.batch_img_del_iteam)
        ImageView batchImgDelIteam;
        @BindView(R.id.commodity_dialog_et_type)
        EditText commodityDialogEtType;
        @BindView(R.id.commodity_dialog_et_code)
        EditText commodityDialogEtCode;
        @BindView(R.id.commodity_dialog_et_timer)
        EditText commodityDialogEtTimer;
        @BindView(R.id.tv_select_pic)
        TextView tvSelxectPic;
        @BindView(R.id.img_zhangjian)
        ImageView imgZhengjian;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
