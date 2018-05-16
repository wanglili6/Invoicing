package com.mtecc.mmp.invoicing.activity.comodity;

import android.app.ActivityOptions;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.views.HackyViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 显示大图--进行滑动
 */
public class SwipeActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    HackyViewPager vp;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_left_select)
    TextView tvLeftSelect;
    @BindView(R.id.img_left_select)
    ImageButton imgLeftSelect;
    @BindView(R.id.rl_right_left)
    RelativeLayout rlRightLeft;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    private ViewPager viewPager;
    private ArrayList<String> imagelist = new ArrayList<>();
    private HomePicturePagerAdapter swipeAdapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setStatusBarColor(Color.BLACK);
        } catch (Exception e) {

        }

        setContentView(R.layout.activity_swipe);
        ButterKnife.bind(this);

        imagelist = (ArrayList<String>) getIntent().getSerializableExtra("imagelist");

        final String position = getIntent().getStringExtra("position");

        //初始化
        initData(position);
        //设置设配器
        swipeAdapter = new HomePicturePagerAdapter();
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(Integer.parseInt(position));

        //设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int positionq) {
                tvTitle.setText(positionq + 1 + "/" + imagelist.size());


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置点击事件
        initEvent();

    }

    private void initData(String position) {
        viewPager = (HackyViewPager) findViewById(R.id.vp);
        ivBack.setVisibility(View.VISIBLE);
        rlTitleBg.setBackgroundColor(Color.parseColor("#22000000"));
        tvTitle.setText(Integer.parseInt(position) + "/" + imagelist.size());
    }


    private void initEvent() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityCompat.finishAfterTransition(SwipeActivity.this);
                } else {
                    finish();
                }

            }
        });


    }


    /**
     * viewPager的适配器
     */
    private class HomePicturePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (imagelist != null) {
                return imagelist.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView iv = new PhotoView(SwipeActivity.this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setFocusable(true);
            iv.setClickable(true);
            //data
            String url = imagelist.get(position);
            //data+view
            Glide.with(SwipeActivity.this).load(url).skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);

            iv.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityCompat.finishAfterTransition(SwipeActivity.this);
                    } else {
                        finish();
                    }
                }
            });
            //把view加入到容器中
            container.addView(iv);

            //返回具体的view
            return iv;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


}
