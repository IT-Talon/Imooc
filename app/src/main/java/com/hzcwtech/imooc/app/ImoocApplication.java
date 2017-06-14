package com.hzcwtech.imooc.app;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
