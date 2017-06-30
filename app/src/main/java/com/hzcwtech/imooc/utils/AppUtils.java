package com.hzcwtech.imooc.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.hzcwtech.imooc.entity.model.CourseDetailModel;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Talon on 2017/6/20.
 */

public class AppUtils {
    /**
     * FaceChat App分享（邀请好友）
     */
    public static void shareApp(final Context context, final CourseDetailModel course, final String platformName) {

        OnekeyShare share = new OnekeyShare();
        share.setTitle(course.getName());
        share.setTitleUrl(course.getShare());
        share.setText(course.getShort_description());
        share.setUrl(course.getShare());
        share.setSite("imooc");
        share.setImageUrl(course.getPic());
        share.setSiteUrl(course.getShare());
        share.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "取消分享", Toast.LENGTH_SHORT).show();
            }
        });
        if (!TextUtils.isEmpty(platformName)) {
            share.setPlatform(platformName);
        }

        share.show(context);
    }
}
