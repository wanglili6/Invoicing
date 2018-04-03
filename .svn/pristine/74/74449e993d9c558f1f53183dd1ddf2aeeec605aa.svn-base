package com.mtecc.mmp.invoicing.base;

import android.app.Application;
import android.content.Context;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by phillip on 2018/4/2.
 */

public class MyApplication extends Application {
    protected static Context sContext;
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
