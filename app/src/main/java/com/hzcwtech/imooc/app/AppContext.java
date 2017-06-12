package com.hzcwtech.imooc.app;

import android.app.Application;

/**
 * Created by Talon on 2017/6/9.
 */

public class AppContext extends Application {

    private static final String TAG = AppContext.class.getSimpleName();

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
