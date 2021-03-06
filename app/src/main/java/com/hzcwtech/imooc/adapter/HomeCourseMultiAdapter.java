package com.hzcwtech.imooc.adapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.entity.model.SkillsModel;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.GlideRoundTransform;
import com.hzcwtech.imooc.utils.ResourceUtil;

import java.util.List;


/**
 * Created by Talon on 2017/6/12.
 */

public class HomeCourseMultiAdapter extends BaseQuickAdapter<CourseDetailModel, BaseViewHolder> {

    private static final int COURSE_TYPE_ONE = 1;
    private static final int COURSE_TYPE_TWO = 2;
    private static final int COURSE_TYPE_THREE = 3;
    private static final int COURSE_TYPE_FOUR = 4;
    private static final int COURSE_TYPE_FIVE = 5;

    public HomeCourseMultiAdapter(List<CourseDetailModel> data) {
        super(data);
        //Step.1
        setMultiTypeDelegate(new MultiTypeDelegate<CourseDetailModel>() {
            @Override
            protected int getItemType(CourseDetailModel course) {
                //根据你的实体类来判断布局类型
                return course.getType();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(COURSE_TYPE_ONE, R.layout.layout_home_course_type1)
                .registerItemType(COURSE_TYPE_TWO, R.layout.layout_home_course_type4)
                .registerItemType(COURSE_TYPE_FOUR, R.layout.layout_home_course_type4);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CourseDetailModel course) {
        //Step.3
        switch (helper.getItemViewType()) {
            case COURSE_TYPE_ONE:
                // do something
                StringBuilder skills = new StringBuilder();
                for (SkillsModel skillsModel : course.getSkills()) {
                    skills.append(skillsModel.getName()).append("  ");
                }
                helper
                        .setText(R.id.course_name, course.getName())
                        .setText(R.id.course_short_description, course.getShort_description())
                        .setText(R.id.course_study_numbers, CommonUtil.fillString(mContext, R.string.format_study_number, course.getNumbers()));

                final ImageView imageView = helper.getView(R.id.course_name_bg);
                Glide.with(mContext).load(course.getPic()).apply(new RequestOptions().transform(new GlideRoundTransform(mContext, 20, 0, GlideRoundTransform.CornerType.TOP))).into(imageView);

                final TextView textView = helper.getView(R.id.course_skills);
//                final GradientDrawable bg = (GradientDrawable) textView.getBackground();
                GradientDrawable bg = new GradientDrawable();
                bg.setCornerRadii(new float[]{20, 20, 20, 20, 0, 0, 0, 0});
                bg.setColors(new int[]{ResourceUtil.toIntColor(course.getBgcolor_start()), ResourceUtil.toIntColor(course.getBgcolor_end())});
                bg.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                textView.setBackground(bg);
                textView.setText(skills);
                break;
            case COURSE_TYPE_TWO:
            case COURSE_TYPE_FOUR:
                // do something
                StringBuilder skills2 = new StringBuilder();
                for (SkillsModel skillsModel : course.getSkills()) {
                    skills2.append(skillsModel.getName()).append('\t');
                }
                helper.setText(R.id.course_skills, skills2.toString())
                        .setText(R.id.course_name, course.getName())
                        .setText(R.id.course_short_description, course.getShort_description())
                        .setText(R.id.course_price, CommonUtil.fillString(mContext, R.string.format_course_price, course.getPrice() / 100))
                        .setText(R.id.course_study_numbers, CommonUtil.fillString(mContext, R.string.format_course_study_number, course.getCourses(), course.getNumbers()));

                SimpleTarget<Drawable> target = new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        helper.getView(R.id.course_skills).setBackground(resource);
                    }
                };
                Glide.with(mContext)
                        .load(course.getPic())
                        .into(target);
                break;
        }
    }
}
