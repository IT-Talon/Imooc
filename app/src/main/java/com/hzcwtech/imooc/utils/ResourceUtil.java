package com.hzcwtech.imooc.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ResourceUtil
{
    public static String getString(Context context, int id)
    {
        return context.getResources().getString(id);
    }

    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context context, int id)
    {
        Drawable drawable = null;

        if(id > 0)
        {
            try
            {
                drawable = context.getResources().getDrawable(id);
            }
            catch(Throwable t)
            {
                t.printStackTrace();
            }
        }

        return drawable;
    }

    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id)
    {
        return context.getResources().getColor(id);
    }

    public static String getMetaValue(Context context, String metaKey)
    {
        String metaValue = null;

        if(context != null && metaKey != null)
        {
            try
            {
                Bundle metaData = null;
                ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

                if(info != null)
                {
                    metaData = info.metaData;
                }

                Object value = metaData == null? null: metaData.get(metaKey);

                if(value != null)
                {
                    if(value instanceof String)
                    {
                        metaValue = (String)value;
                    }
                    else
                    {
                        metaValue = value.toString();
                    }
                }
            }
            catch(Throwable t)
            {
                t.printStackTrace();
            }
        }

        return metaValue;
    }

    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        Bitmap bitmap = null;
        Rect bounds = drawable.getBounds();
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        if(width > 0 && height > 0)
        {
            if(drawable instanceof BitmapDrawable)
            {
                bitmap = ((BitmapDrawable)drawable).getBitmap();
            }
            else
            {
                try
                {
                    bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
                    Canvas canvas = new Canvas(bitmap);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                }
                catch(Throwable t)
                {
                    t.printStackTrace();
                }
            }
        }

        return bitmap;
    }



    public static int toIntColor(String color)
    {
        int intColor = 0;

        if(color != null && !color.isEmpty())
        {
            color = color.toLowerCase();
            color = color.replaceFirst("#", "");

            if(color.length() < 8)
            {
                color = "ff" + color;
            }

            intColor = (int)Long.parseLong(color, 16);
        }

        return intColor;
    }

    public static String toStringColor(int color, boolean includeAlpha)
    {
        if(color == 0)
        {
            return includeAlpha? "#00000000": "#000000";
        }
        else
        {
            StringBuilder colorStr = new StringBuilder(Integer.toHexString(color));
            int neededLength = includeAlpha? 8: 6;
            int realLength = colorStr.length();

            if(realLength > neededLength)
            {
                colorStr.replace(0, realLength - neededLength, "");
            }
            else
            {
                while(realLength < neededLength)
                {
                    colorStr.insert(0, 0);
                }
            }

            return '#' + colorStr.toString();
        }
    }

    public static int getGradientColor(float fraction, int startARGB, int endARGB)
    {
        int startA = (startARGB >> 24) & 0xFF;
        int startR = (startARGB >> 16) & 0xFF;
        int startG = (startARGB >> 8) & 0xFF;
        int startB = startARGB & 0xFF;
        int endA = (endARGB >> 24) & 0xFF;
        int endR = (endARGB >> 16) & 0xFF;
        int endG = (endARGB >> 8) & 0xFF;
        int endB = endARGB & 0xFF;
        int a = (startA + (int)(fraction * (endA - startA))) << 24;
        int r = (startR + (int)(fraction * (endR - startR))) << 16;
        int g = (startG + (int)(fraction * (endG - startG))) << 8;
        int b = startB + (int)(fraction * (endB - startB));
        return a | r | g | b;
    }

    /**
     * 截屏
     */
    public static Bitmap createScreenCapture(View view)
    {
        return createScreenCapture(view, 0, false);
    }

    public static Bitmap createScreenCapture(View view, int bgColor, boolean transparent)
    {
        Bitmap bitmap = null;

        try
        {
            Config config = transparent? Config.ARGB_4444: Config.RGB_565;
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), config);
            Canvas canvas = new Canvas(bitmap);

            if(bgColor != 0)
            {
                canvas.drawColor(bgColor);
            }

            view.setDrawingCacheEnabled(true);
            view.draw(canvas);
            view.setDrawingCacheEnabled(false);
        }
        catch(Throwable t)
        {}

        return bitmap;
    }

    public static Rect getVideoBounds(String path)
    {
        MediaMetadataRetriever metadata = new MediaMetadataRetriever();
        metadata.setDataSource(path);
        int videoWidth = Integer.parseInt(metadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
        int videoHeight = Integer.parseInt(metadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
        return new Rect(0, 0, videoWidth, videoHeight);
    }

    public static Rect getBitmapBounds(String path)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inScaled = false;
        BitmapFactory.decodeFile(path, options);
        return new Rect(0, 0, options.outWidth, options.outHeight);
    }

    public static int calculateInSampleSize(BitmapFactory.Options op, int reqWidth, int reqheight)
    {
        int originalWidth = op.outWidth;
        int originalHeight = op.outHeight;
        int inSampleSize = 1;

        if(originalWidth > reqWidth || originalHeight > reqheight)
        {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;

            while((halfWidth / inSampleSize > reqWidth) && (halfHeight / inSampleSize > reqheight))
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Uri getAssetURI(String assetPath)
    {
        return Uri.parse("asset:///" + assetPath);
    }

    public static Uri getDrawableURI(Context context, int drawableId)
    {
        String url = "res://" + context.getPackageName() + "/" + drawableId;
        return Uri.parse(url);
    }

    public static Uri getFileURI(String path)
    {
        return Uri.parse("file://" + path);
    }
}