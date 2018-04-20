package com.mtecc.mmp.invoicing.base;

import android.app.Application;
import android.content.Context;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by phillip on 2018/4/2.
 */

public class MyApplication extends Application {
    protected static Context sContext;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.bg_color, android.R.color.black);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setTextSizeTime(16);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //初始化OKhttpUtils
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("打印log")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configShowBorders(true)
//                .configMethodOffset(1)
                .configLevel(LogLevel.TYPE_VERBOSE);
        LogUtils.getLog2FileConfig().configLog2FileEnable(true)
                .configLog2FilePath("/sdcard/LogUtils/Invocinglogs/")
                .configLog2FileNameFormat("Hi-%d{yyyyMMdd}-2.txt")
                .configLog2FileLevel(LogLevel.TYPE_VERBOSE)
                .configLogFileEngine(new LogFileEngineFactory());
        context();
        //初始化zxinger二维码
        ZXingLibrary.initDisplayOpinion(this);
    }


    /**
     * 全局context
     *
     * @return context
     */
    public static synchronized MyApplication context() {
        return (MyApplication) sContext;
    }
}
