package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchPicBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.mtecc.mmp.invoicing.utils.UseSystemUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class BatchListAdapter extends BaseAdapter {

    private Context mContext;
    private List<BatchPicBean> mList;
    private TextView commondityTvCommit;
    IBatchImgOnClickListerner iBatchImgOnClickListerner;
    IBatchDelImgOnClickListerner iBatchDelOnClickListerner;
    IAddBatchOnClickListerner iAddBatchOnClickListerner;
    private final UseSystemUtils userSystemutils;

    public BatchListAdapter(Context mContext, List<BatchPicBean> mList, TextView commondityTvCommit) {
        this.mContext = mContext;
        this.mList = mList;
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
        DisplayMetrics dm2 = mContext.getResources().getDisplayMetrics();
        LogUtils.d("width-display :" + dm2.widthPixels);
        if (dm2.widthPixels <= 1080) {
            holder.addBatchRecyclcerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        } else {
            holder.addBatchRecyclcerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        }

        List<String> imgUrlList = null;

        imgUrlList = mList.get(position).getImgUrl();
        holder.commodityDialogEtType.setTag(position);
        holder.commodityDialogEtCode.setTag(position);
        holder.commodityDialogEtTimer.setTag(position);
        final ViewHolder finalHolder = holder;
        holder.commodityDialogEtType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtType.getTag();
                mList.get(pos).setBatchcarType(s + "");
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
                mList.get(pos).setBatchcode(s + "");
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
                mList.get(pos).setBatchtimer(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        holder.commodityDialogEtType.setText(mList.get(position).getBatchcarType());
        holder.commodityDialogEtTimer.setText(mList.get(position).getBatchtimer());
        holder.commodityDialogEtCode.setText(mList.get(position).getBatchcode());
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

        ImgListAdapter imgListAdapter = new ImgListAdapter(mContext, imgUrlList);
        holder.addBatchRecyclcerView.setAdapter(imgListAdapter);
        imgListAdapter.notifyDataSetChanged();

        imgListAdapter.setiImgOnClickListerner(new ImgListAdapter.IImgOnClickListerner() {
            @Override
            public void onImgClick(int pos, String imgUrl) {
                LogUtils.d("点击图片" + pos + imgUrl);
                if (mList.get(position).getImgUrl() != null) {
                    LogUtils.d("点击图片" + mList.get(position).getImgUrl().size());
                }
                if (iBatchImgOnClickListerner != null) {
                    iBatchImgOnClickListerner.onBatchImgClick(position, pos, imgUrl, mList.get(position).getImgUrl(), mList);
                }
            }
        });

        imgListAdapter.setiIImgDelOnClickListerner(new ImgListAdapter.IImgDelOnClickListerner() {
            @Override
            public void onDelClick(int pos, String imgUrl) {
                if (iBatchDelOnClickListerner != null) {
                    iBatchDelOnClickListerner.onBatchDelClick(position, pos, imgUrl, mList.get(position).getImgUrl(), mList);
                }
            }
        });


        holder.batchImgAddIteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatchPicBean BatchPicBean = new BatchPicBean();
                BatchPicBean.setBatchcarType("");
                BatchPicBean.setBatchcode("");
                BatchPicBean.setBatchtimer("");
                List<String> imgUrlList = new ArrayList<>();
                imgUrlList.add("");
                BatchPicBean.setImgUrl(imgUrlList);
                mList.add(BatchPicBean);
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
                List<BatchPicBean> mBatchList = new ArrayList<BatchPicBean>();
                List<String> mBatchinimgList = new ArrayList<String>();
                for (int i = 0; i < mList.size(); i++) {
                    BatchPicBean BatchPicBean = mList.get(i);
                    List<String> imgUrl = BatchPicBean.getImgUrl();
                    for (int i1 = 0; i1 < imgUrl.size(); i1++) {
                        String str = imgUrl.get(i1);
                        if (!TextUtils.isEmpty(str) && !str.equals("")) {
                            mBatchinimgList.add(str);
                        }
                    }
                    BatchPicBean bean = new BatchPicBean();
                    bean.setBatchcarType(BatchPicBean.getBatchcarType());
                    bean.setBatchtimer(BatchPicBean.getBatchtimer());
                    bean.setBatchcode(BatchPicBean.getBatchcode());
                    bean.setImgUrl(mBatchinimgList);
                    mBatchList.add(bean);
                    if (TextUtils.isEmpty(BatchPicBean.getBatchtimer())) {
                        Toast.makeText(mContext, "不能提交批次日期为空的数据,请确认好数据!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                }
                if (iAddBatchOnClickListerner != null) {
                    iAddBatchOnClickListerner.onAddBatchClick(mBatchList);
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
     * @param commodityDialogEtType
     */
    private void selectClick(View selectView, final AlertDialog dialog, final EditText commodityDialogEtType) {
        TextView tvYingye = selectView.findViewById(R.id.dialog_tv_yy);
        TextView tvXk = selectView.findViewById(R.id.dialog_tv_xk);
        TextView tvExit = selectView.findViewById(R.id.dialog_tv_exit);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvYingye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                commodityDialogEtType.setText("营业执照");
            }
        });

        tvXk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                commodityDialogEtType.setText("许可证");
            }
        });
    }

    public static interface IBatchImgOnClickListerner {
        public void onBatchImgClick(int position, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchPicBean> mList);
    }

    public void setiImgOnClickListerner(IBatchImgOnClickListerner iBatchImgOnClickListerner) {
        this.iBatchImgOnClickListerner = iBatchImgOnClickListerner;
    }

    public static interface IBatchDelImgOnClickListerner {
        public void onBatchDelClick(int position, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchPicBean> mList);
    }

    public void setiDelOnClickListerner(IBatchDelImgOnClickListerner iBatchDelOnClickListerner) {
        this.iBatchDelOnClickListerner = iBatchDelOnClickListerner;
    }

    public IBatchDelImgOnClickListerner getiBatchDelOnClickListerner() {
        return iBatchDelOnClickListerner;
    }

    public static interface IAddBatchOnClickListerner {
        public void onAddBatchClick(List<BatchPicBean> mPicimgList);
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
        @BindView(R.id.add_batch_recyclcer_view)
        RecyclerView addBatchRecyclcerView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
