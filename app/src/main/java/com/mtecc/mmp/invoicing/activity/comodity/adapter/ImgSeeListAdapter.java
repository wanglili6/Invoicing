package com.mtecc.mmp.invoicing.activity.comodity.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.comodity.AddBatchActivity;
import com.mtecc.mmp.invoicing.activity.comodity.SeeImgActivity;
import com.mtecc.mmp.invoicing.activity.comodity.SwipeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wll on 2018/4/18.
 */

public class ImgSeeListAdapter extends RecyclerView.Adapter<ImgSeeListAdapter.ShopViewHolder> {
    private Context mContext;
    private List<String> mList;


    public ImgSeeListAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ImgSeeListAdapter.ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.img_list_iteam, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgSeeListAdapter.ShopViewHolder holder, final int position) {
        final String imgUrl = mList.get(position);
        holder.imgDel.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .into(holder.imgAdd);
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeImgActivity seeImgActivity = (SeeImgActivity) mContext;
                Intent intent = new Intent();
                intent.setClass(mContext, SwipeActivity.class);
                intent.putStringArrayListExtra("imagelist", (ArrayList<String>) mList);
                intent.putExtra("position", position + "");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(seeImgActivity).toBundle());
                } else {
                    mContext.startActivity(intent);
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


}
