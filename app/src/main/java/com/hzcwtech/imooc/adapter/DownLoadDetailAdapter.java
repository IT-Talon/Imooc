package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.entity.model.MainCourseModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/18.
 */

public class DownLoadDetailAdapter extends BaseQuickAdapter<MainCourseModel, BaseViewHolder> {
    public DownLoadDetailAdapter(@Nullable List<MainCourseModel> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainCourseModel item) {

    }
}
