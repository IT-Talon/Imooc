package com.hzcwtech.imooc.app;

import android.app.Application;
import android.content.Context;

import c.b.BP;

/**
 * Created by Talon on 2017/6/9.
 */

public class ImoocApplication extends Application {

    private static final String TAG = ImoocApplication.class.getSimpleName();

    private static ImoocApplication application;

    public static ImoocApplication getInstance() {
        if (application == null) {
            application = new ImoocApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        initBmob();
    }

    private void initBmob() {
        // 初始化BmobPay对象,可以在支付时再初始化
        BP.init("a859b8bcc9f0cd5e8f4c47bd6285c42c");
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
