package com.hzcwtech.imooc.adapter;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.CourseModel;
import com.hzcwtech.imooc.entity.model.SkillsModel;
import com.hzcwtech.imooc.utils.GlideCircleTransform;
import com.hzcwtech.imooc.utils.GlideRoundTransform;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Talon on 2017/6/12.
 */

public class HomeCourseMultiAdapter extends BaseQuickAdapter<CourseModel, BaseViewHolder> {

    private static final int COURSE_TYPE_ONE = 1;
    private static final int COURSE_TYPE_TWO = 2;
    private static final int COURSE_TYPE_THREE = 3;
    private static final int COURSE_TYPE_FOUR = 4;
    private static final int COURSE_TYPE_FIVE = 5;

    public HomeCourseMultiAdapter(List<CourseModel> data) {
        super(data);
        //Step.1
        setMultiTypeDelegate(new MultiTypeDelegate<CourseModel>() {
            @Override
            protected int getItemType(CourseModel course) {
                //根据你的实体类来判断布局类型
                return course.getType();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(COURSE_TYPE_ONE, R.layout.layout_home_course_type1)
                .registerItemType(COURSE_TYPE_TWO, R.layout.layout_home_course_type2);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseModel course) {
        //Step.3
        switch (helper.getItemViewType()) {
            case COURSE_TYPE_ONE:
                // do something
                StringBuilder skills = new StringBuilder();
                for (SkillsModel skillsModel : course.getSkills()) {
                    skills.append(skillsModel.getName()).append("  ");
                }
                helper.setText(R.id.course_skills, skills)
                        .setText(R.id.course_name, course.getName())
                        .setText(R.id.course_short_description, course.getShort_description())
                        .setText(R.id.course_study_numbers, course.getNumbers());
                final ImageView imageView = helper.getView(R.id.course_name_bg);
//                Glide.with(mContext).load(course.getPic()).into(new BitmapImageViewTarget(imageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        imageView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
//                Glide.with(mContext).load(course.getPic()).apply(new RequestOptions().transform(new RoundedCornersTransformation(mContext, 5,0,RoundedCornersTransformation.CornerType.TOP))).into(imageView);

                Glide.with(mContext).load(course.getPic()).apply(new RequestOptions().transform(new RoundedCornersTransformation(mContext, 5, 0, RoundedCornersTransformation.CornerType.OTHER_BOTTOM_LEFT))).into(imageView);
                break;
            case COURSE_TYPE_TWO:
                // do something
                break;
        }
    }
}
