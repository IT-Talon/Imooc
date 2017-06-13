package com.hzcwtech.imooc.utils;

import android.util.Log;

import com.hzcwtech.imooc.BuildConfig;

public class LogUtil
{
    private static final boolean SHOW_LOG = BuildConfig.DEBUG;

    public static void i(String tag, String msg)
    {
        if(SHOW_LOG)
        {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable t)
    {
        if(SHOW_LOG)
        {
            Log.i(tag, msg, t);
        }
    }

    public static void d(String tag, String msg)
    {
        if(SHOW_LOG)
        {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable t)
    {
        if(SHOW_LOG)
        {
            Log.d(tag, msg, t);
        }
    }

    public static void e(String tag, String msg)
    {
        if(SHOW_LOG)
        {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable t)
    {
        if(SHOW_LOG)
        {
            Log.e(tag, msg, t);
        }
    }

    public static void v(String tag, String msg)
    {
        if(SHOW_LOG)
        {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable t)
    {
        if(SHOW_LOG)
        {
            Log.v(tag, msg, t);
        }
    }
}
