package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mtecc.mmp.invoicing.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class ImgListAdapter extends RecyclerView.Adapter<ImgListAdapter.ShopViewHolder> {
    private Context mContext;
    private List<String> mList;
    IImgOnClickListerner iImgOnClickListerner;
    IImgDelOnClickListerner iImgDelOnClickListerner;

    public ImgListAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ImgListAdapter.ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.img_list_iteam, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgListAdapter.ShopViewHolder holder, final int position) {
        final String imgUrl = mList.get(position);
        if (TextUtils.isEmpty(imgUrl)) {
            holder.imgAdd.setImageResource(R.mipmap.add_img);
            holder.imgDel.setVisibility(View.GONE);

        } else {
            holder.imgDel.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(imgUrl)
                    .centerCrop()
                    .into(holder.imgAdd);


        }
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iImgOnClickListerner != null) {
                    iImgOnClickListerner.onImgClick(position, imgUrl);
                }
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iImgDelOnClickListerner != null) {
                    iImgDelOnClickListerner.onDelClick(position, imgUrl);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    static class ShopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_del)
        RelativeLayout imgDel;
        @BindView(R.id.img_add)
        ImageView imgAdd;

        ShopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static interface IImgOnClickListerner {
        public void onImgClick(int position, String imgUrl);

    }

    public void setiImgOnClickListerner(IImgOnClickListerner iImgOnClickListerner) {
        this.iImgOnClickListerner = iImgOnClickListerner;
    }

    public IImgOnClickListerner getiImgOnClickListerner() {
        return iImgOnClickListerner;
    }

    public static interface IImgDelOnClickListerner {
        public void onDelClick(int position, String imgUrl);

    }

    public void setiIImgDelOnClickListerner(IImgDelOnClickListerner iImgDelOnClickListerner) {
        this.iImgOnClickListerner = iImgOnClickListerner;
    }

    public IImgDelOnClickListerner getiImgDelOnClickListerner() {
        return iImgDelOnClickListerner;
    }
}
