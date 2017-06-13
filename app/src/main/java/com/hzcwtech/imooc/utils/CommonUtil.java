package com.hzcwtech.imooc.utils;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.hzcwtech.imooc.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by 003 on 2016-12-08.
 */
public class CommonUtil
{
    private static final int DEFAULT_STATUS_BAR_HEIGHT_DP = 25;

    private static final String MOBILE_REGEX_CH = "1[3|4|5|7|8]\\d{9}";

    private static final String MOBILE_REGEX = "[1-9]\\d{4,}";

    private static int statusBarHeight;

    private static int screenWidth;

    public static int dp2px(Context context, float dpValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        float px = dpValue * scale;
        int pxInt = (int)px;
        return px == pxInt? pxInt: pxInt + 1;
    }

    public static int px2dp(Context context, float pxValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        float dp = pxValue / scale;
        int dpInt = (int)dp;
        return dp == dpInt? dpInt: dpInt + 1;
    }

    public static boolean isNetworkAvailable(Context context)
    {
        boolean available = false;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm != null)
        {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected())
            {
                available = true;
            }
        }

        return available;
    }

    /**
     * 获取应用通知状态
     */
    public static boolean isNotificationEnabled(Context context)
    {
        AppOpsManager appOps = (AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        try
        {
            Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getDeclaredMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            checkOpNoThrowMethod.setAccessible(true);
            Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
            opPostNotificationValue.setAccessible(true);
            int value = (int)opPostNotificationValue.get(Integer.class);
            return ((int)checkOpNoThrowMethod.invoke(appOps, value, uid, packageName) == AppOpsManager.MODE_ALLOWED);
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }

        return false;
    }

    public static void startSetting(Activity activity)
    {
        activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
    }


    @TargetApi(VERSION_CODES.KITKAT)
    public static boolean hideStatusBarIfSupported(Activity activity)
    {
        boolean hasHide = false;

        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT)
        {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            hasHide = true;
        }

        return hasHide;
    }

    @TargetApi(VERSION_CODES.KITKAT)
    public static void setStatusBarColorIfSupported(Activity activity, int color)
    {
        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT)
        {
            hideStatusBarIfSupported(activity);
            getContentView(activity).setFitsSystemWindows(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);
        }
    }

    public static void resetTopViewHeight(Context context, View topView)
    {
        int statusBarHeight = getStatusBarHeightKitkatOrHigh(context);
        topView.setPadding(topView.getPaddingLeft(), topView.getPaddingTop() + statusBarHeight, topView.getPaddingRight(), topView.getPaddingBottom());
        ViewGroup.LayoutParams lp = topView.getLayoutParams();

        if(lp != null && lp.height > 0)
        {
            lp.height += statusBarHeight;
        }
    }

    public static void resetSimulateStatusViewHeight (Context context, View simulateStatusView)
    {
        int statusBarHeight = getStatusBarHeightKitkatOrHigh(context);

        ViewGroup.LayoutParams layoutParams = simulateStatusView.getLayoutParams();

        if (layoutParams !=null)
        {
            layoutParams.height = statusBarHeight;
        }
    }

    public static int getStatusBarHeight(int defaultHeight)
    {
        if(statusBarHeight == 0)
        {
            Resources resources = Resources.getSystem();
            int resId = resources.getIdentifier("status_bar_height", "dimen", "android");

            if(resId > 0)
            {
                statusBarHeight = resources.getDimensionPixelSize(resId);
            }

            if(statusBarHeight == 0)
            {
                statusBarHeight = defaultHeight;
            }
        }

        return statusBarHeight;
    }

    public static int getStatusBarHeight(Context context)
    {
        return getStatusBarHeight(dp2px(context, DEFAULT_STATUS_BAR_HEIGHT_DP));
    }

    public static int getStatusBarHeightKitkatOrHigh(Context context)
    {
        return getStatusBarHeightKitkatOrHigh(dp2px(context, DEFAULT_STATUS_BAR_HEIGHT_DP));
    }

    @TargetApi(VERSION_CODES.KITKAT)
    public static int getStatusBarHeightKitkatOrHigh(int defaultHeight)
    {
        int height = 0;

        if(VERSION.SDK_INT >= VERSION_CODES.KITKAT)
        {
            height = getStatusBarHeight(defaultHeight);
        }

        return height;
    }

    public static View getContentView(Activity activity)
    {
        return ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static CharSequence fillString(Context context, int strResId, Object... args)
    {
        String str = ResourceUtil.getString(context, strResId);
        return fillString(str, args);
    }

    public static CharSequence fillHtmlString(Context context, int strResId, Object... args)
    {
        String str = ResourceUtil.getString(context, strResId);
        return fillHtmlString(str, args);
    }

    public static CharSequence fillString(String baseStr, Object... args)
    {
        return MessageFormat.format(baseStr, args);
    }

    @SuppressWarnings("deprecation")
    public static CharSequence fillHtmlString(String baseStr, Object... args)
    {
        CharSequence str = fillString(baseStr, args);
        str = Html.fromHtml(str.toString());
        return str;
    }

    public static String formatChatTime(Context context, long time)
    {
        String timeForShow;
        Calendar calendar = Calendar.getInstance();
        long currentMillis = calendar.getTimeInMillis();
        long deltaMillis = currentMillis - time;
        long deltaTime = deltaMillis / 1000;

        //1分钟内
        if(deltaTime < 60)
        {
            timeForShow = ResourceUtil.getString(context, R.string.just_now);
        }
        //1小时内
        else if((deltaTime /= 60) < 60)
        {
            timeForShow = CommonUtil.fillString(context, R.string.format_before_minutes, deltaTime).toString();
        }
        //24小时内
        else if((deltaTime /= 60) < 24)
        {
            timeForShow = CommonUtil.fillString(context, R.string.format_before_hours, deltaTime).toString();
        }
        //7天内
        else if((deltaTime /= 24) < 7)
        {
            timeForShow = CommonUtil.fillString(context, R.string.format_before_days, deltaTime).toString();
        }
        else
        {
            calendar.add(Calendar.YEAR, -1);
            Calendar source = Calendar.getInstance();
            source.setTimeInMillis(time);

            //1年内
            if(deltaMillis < currentMillis - calendar.getTimeInMillis())
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                timeForShow = dateFormat.format(source.getTime());
            }
            //超过1年
            else
            {
                timeForShow = CommonUtil.fillString(context, R.string.format_year, source.get(Calendar.YEAR)).toString();
            }
        }

        return timeForShow;
    }

    public static String fillDateTimeNum(int num)
    {
        return num < 10? "0" + num: String.valueOf(num);
    }

    public static MediaPlayer getMediaPlayer(Context context, File file)
    {
        MediaPlayer mediaPlayer = null;

        try
        {
            mediaPlayer = MediaPlayer.create(context, Uri.fromFile(file));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return mediaPlayer;
    }

    public static String getProcessName(Context context)
    {
        String processName = null;
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

        if(processInfos != null && !processInfos.isEmpty())
        {
            int pid = android.os.Process.myPid();

            for(RunningAppProcessInfo processInfo: processInfos)
            {
                if(processInfo.pid == pid)
                {
                    processName = processInfo.processName;
                    break;
                }
            }
        }

        return processName;
    }

    /**
     * 判断是否为主线程
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context)
    {
        String processName = getProcessName(context);
        return processName != null && processName.equals(context.getPackageName());
    }


    public static boolean validateMobile(String mobile, boolean chinese)
    {
        return mobile != null && mobile.matches(chinese? MOBILE_REGEX_CH: MOBILE_REGEX);
    }

    /**
     * 晃动View
     * @param view
     */
    public static void shakeView(View view)
    {
        view.clearAnimation();
        view.setTranslationX(0);
        int deltaX = dp2px(view.getContext(), 10);
        float[] values = {0, -deltaX, 0, deltaX, 0, -deltaX, 0, deltaX, 0};
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", values).setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    public static int getScreenWidth(Activity activity)
    {
        if(screenWidth == 0)
        {
            WindowManager wm = activity.getWindowManager();
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    /**
     * 格式化单位
     * @param byteSize
     * @return
     */
    public static String getFormatSize(double byteSize)
    {
        double kiloByte = byteSize / 1024;

        if(kiloByte == 0)
        {
            return "0.00MB";
        }

        if(kiloByte < 1)
        {
            return byteSize + "Byte";
        }

        double megaByte = kiloByte / 1024;

        if(megaByte < 1)
        {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;

        if(gigaByte < 1)
        {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;

        if(teraBytes < 1)
        {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }

        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 搜索关键字高亮
     */
    public static SpannableString matcherSearchText(int color, String text, String keyword)
    {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);

        while(matcher.find())
        {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return ss;
    }

    /**
     * 反射module
     * @param from
     * @param to
     * @param clazz
     * @param excludedFields
     */
    public static void copyProperties(Object from, Object to, Class<?> clazz, String... excludedFields)
    {
        if(from == null || to == null)
        {
            return;
        }

        Set<String> excludedSet = new HashSet<>();

        if(excludedFields != null && excludedFields.length > 0)
        {
            for(String field: excludedFields)
            {
                excludedSet.add(field);
            }
        }

        try
        {
            Field[] fields = clazz.getDeclaredFields();
            String fieldName, fieldName4Method, methodGetName, methodSetName;
            Method methodGet, methodSet;
            Object value;
            Class<?> type;

            for(Field field: fields)
            {
                fieldName = field.getName();

                if(Modifier.isStatic(field.getModifiers()) || excludedSet.contains(fieldName))
                {
                    continue;
                }

                type = field.getType();
                fieldName4Method = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

                if(type.getSimpleName().equalsIgnoreCase("boolean"))
                {
                    if(fieldName.startsWith("is"))
                    {
                        methodGetName = fieldName;
                    }
                    else
                    {
                        methodGetName = "is" + fieldName4Method;
                    }
                }
                else
                {
                    methodGetName = "get" + fieldName4Method;
                }

                methodSetName = "set" + fieldName4Method;
                methodGet = clazz.getDeclaredMethod(methodGetName);
                methodSet = clazz.getDeclaredMethod(methodSetName, type);
                value = methodGet.invoke(from);
                methodSet.invoke(to, value);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int parseInt(String str)
    {
        int value = 0;

        try
        {
            value = Integer.parseInt(str);
        }
        catch(Exception e)
        {}

        return value;
    }

    public static long parseLong(String str)
    {
        long value = 0;

        try
        {
            value = Long.parseLong(str);
        }
        catch(Exception e)
        {}

        return value;
    }

}