package com.hzcwtech.imooc.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Talon on 2017/6/9.
 */

public class GlideImageLoader extends ImageLoader {

    private static final long serialVersionUID = -5744242419284444831L;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
