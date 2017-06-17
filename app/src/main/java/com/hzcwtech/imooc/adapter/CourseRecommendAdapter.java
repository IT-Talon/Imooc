package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.utils.CommonUtil;

import java.util.List;

/**
 * Created by Talon on 2017/6/18.
 */

public class CourseRecommendAdapter extends BaseQuickAdapter<CourseDetailModel, BaseViewHolder> {
    public CourseRecommendAdapter(@Nullable List<CourseDetailModel> data) {
        super(R.layout.item_course_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetailModel item) {
        helper.setText(R.id.tv_courser_name, item.getName())
                .setText(R.id.tv_course_price, CommonUtil.fillString(mContext, R.string.format_course_price, item.getPrice() / 100))
                .setText(R.id.tv_course_number, CommonUtil.fillString(mContext, R.string.format_study_number, item.getNumbers()));
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.img_course_pic));
    }
}
