package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.bean.BatchBean;
import com.mtecc.mmp.invoicing.base.InvoicingConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/20.
 */

public class BatchListAdapter extends BaseAdapter {

    private Context mContext;
    private List<BatchBean> mList;
    private TextView commondityTvCommit;
    private String batchType;
    IBatchImgOnClickListerner iBatchImgOnClickListerner;
    IBatchDelImgOnClickListerner iBatchDelOnClickListerner;
    IAddBatchOnClickListerner iAddBatchOnClickListerner;

    public BatchListAdapter(Context mContext, List<BatchBean> mList, TextView commondityTvCommit, String batchType) {
        this.mContext = mContext;
        this.mList = mList;
        this.batchType = batchType;
        this.commondityTvCommit = commondityTvCommit;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.add_batch_list_iteam, parent, false);
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

        Object tag = holder.addBatchRecyclcerView.getTag();
        List<String> imgUrlList = null;

        imgUrlList = mList.get(position).getImgUrl();
        holder.commodityDialogEtName.setTag(position);
        holder.commodityDialogEtNum.setTag(position);
        holder.commodityDialogEtLshoujia.setTag(position);
        holder.commodityDialogEtJhuojia.setTag(position);
        holder.commodityDialogEtPfjia.setTag(position);
        holder.commodityDialogEtType.setTag(position);
        holder.commodityDialogEtCode.setTag(position);
        holder.commodityDialogEtTimer.setTag(position);
        final ViewHolder finalHolder = holder;
        holder.commodityDialogEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtName.getTag();
                mList.get(pos).setBatchstartTimer(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtNum.getTag();
                mList.get(pos).setBatchnum(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtLshoujia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtLshoujia.getTag();
                mList.get(pos).setBatchlShouji(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtJhuojia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtJhuojia.getTag();
                mList.get(pos).setBatchjHuojia(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.commodityDialogEtPfjia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int pos = (int) finalHolder.commodityDialogEtPfjia.getTag();
                mList.get(pos).setBatchpfajia(s + "");
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


        holder.commodityDialogEtName.setText(mList.get(position).getBatchstartTimer());
        holder.commodityDialogEtTimer.setText(mList.get(position).getBatchtimer());
        holder.commodityDialogEtNum.setText(mList.get(position).getBatchnum());
        holder.commodityDialogEtLshoujia.setText(mList.get(position).getBatchlShouji());
        holder.commodityDialogEtJhuojia.setText(mList.get(position).getBatchjHuojia());
        holder.commodityDialogEtPfjia.setText(mList.get(position).getBatchpfajia());
        holder.commodityDialogEtCode.setText(mList.get(position).getBatchcode());
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
                BatchBean batchBean = new BatchBean();
                batchBean.setBatchstartTimer("");
                List<String> imgUrlList = new ArrayList<>();
                imgUrlList.add("");
                batchBean.setImgUrl(imgUrlList);
                mList.add(batchBean);
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
                List<BatchBean> mBatchList = new ArrayList<BatchBean>();
                List<String> mBatchinimgList = new ArrayList<String>();
                for (int i = 0; i < mList.size(); i++) {
                    BatchBean batchBean = mList.get(i);
                    List<String> imgUrl = batchBean.getImgUrl();
                    for (int i1 = 0; i1 < imgUrl.size(); i1++) {
                        String str = imgUrl.get(i1);
                        if (!TextUtils.isEmpty(str) && !str.equals("")) {
                            mBatchinimgList.add(str);
                        }
                    }
                    BatchBean bean = new BatchBean();
                    bean.setBatchstartTimer(batchBean.getBatchstartTimer());
                    bean.setImgUrl(mBatchinimgList);
                    mBatchList.add(bean);
                    if (TextUtils.isEmpty(batchBean.getBatchstartTimer())) {
                        Toast.makeText(mContext, "不能提交批次日期为空的数据,请确认好数据!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                }
                if (batchType.equals(InvoicingConstants.BATCH_ADD)) {
                    //添加商品的时候添加
                    if (iAddBatchOnClickListerner != null) {
                        iAddBatchOnClickListerner.onAddBatchClick(mBatchList);
                    }

                } else if (batchType.equals(InvoicingConstants.BATCH_LIST)) {
                    //从列表添加
                    Gson gson = new Gson();
                    String batchJson = gson.toJson(mList);
                    LogUtils.d("批次管理" + batchJson);
                }

            }
        });
        return convertView;
    }


    public static interface IBatchImgOnClickListerner {
        public void onBatchImgClick(int position, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchBean> mList);
    }

    public void setiImgOnClickListerner(IBatchImgOnClickListerner iBatchImgOnClickListerner) {
        this.iBatchImgOnClickListerner = iBatchImgOnClickListerner;
    }

    public static interface IBatchDelImgOnClickListerner {
        public void onBatchDelClick(int position, int imgposition, String imgUrl, List<String> finalImgUrlList, List<BatchBean> mList);
    }

    public void setiDelOnClickListerner(IBatchDelImgOnClickListerner iBatchDelOnClickListerner) {
        this.iBatchDelOnClickListerner = iBatchDelOnClickListerner;
    }

    public IBatchDelImgOnClickListerner getiBatchDelOnClickListerner() {
        return iBatchDelOnClickListerner;
    }

    public static interface IAddBatchOnClickListerner {
        public void onAddBatchClick(List<BatchBean> mList);
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
        @BindView(R.id.commodity_dialog_et_name)
        EditText commodityDialogEtName;
        @BindView(R.id.commodity_dialog_et_num)
        EditText commodityDialogEtNum;
        @BindView(R.id.commodity_dialog_et_lshoujia)
        EditText commodityDialogEtLshoujia;
        @BindView(R.id.commodity_dialog_et_jhuojia)
        EditText commodityDialogEtJhuojia;
        @BindView(R.id.commodity_dialog_et_pfjia)
        EditText commodityDialogEtPfjia;
        @BindView(R.id.commodity_dialog_et_type)
        Spinner commodityDialogEtType;
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
