package com.hzcwtech.imooc.base;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.manager.ActivityManager;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.ResourceUtil;

public class BaseActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityManager.addActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        resetStatusBar();
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ActivityManager.removeActivity(this);
        super.onDestroy();
    }

    protected final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper()) {
                public void handleMessage(Message msg) {
                    BaseActivity.this.handleMessage(msg);
                }
            };
        }

        return handler;
    }

    protected void handleMessage(Message msg) {
    }

    protected final void sendEmptyMessage(int what) {
        getHandler().sendEmptyMessage(what);
    }

    protected final void sendEmptyMessageDelayed(int what, long delayMillis) {
        getHandler().sendEmptyMessageDelayed(what, delayMillis);
    }

    protected final void sendMessage(Message msg) {
        getHandler().sendMessage(msg);
    }

    protected final void sendMessageDelayed(Message msg, long delayMillis) {
        getHandler().sendMessageDelayed(msg, delayMillis);
    }


    protected Integer getStatusBarColor()
    {
        return ResourceUtil.getColor(this, R.color.colorPrimaryDark);
    }

    private void resetStatusBar()
    {
        Integer statusBarColor = this.getStatusBarColor();

        if(statusBarColor != null)
        {
            if(statusBarColor == 0)
            {
                CommonUtil.hideStatusBarIfSupported(this);
            }
            else
            {
                CommonUtil.setStatusBarColorIfSupported(this, statusBarColor);
            }
        }
    }


}
