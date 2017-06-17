package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.MainCourseModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/17.
 */

public class HomeCourseAadapter extends BaseQuickAdapter<MainCourseModel, BaseViewHolder> {

    public HomeCourseAadapter(@Nullable List<MainCourseModel> data) {
        super(R.layout.item_main_course, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainCourseModel item) {
        helper.setText(R.id.tv_category_name, item.getName());
        RecyclerView recyclerView = helper.getView(R.id.recyclerView_skills);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setAdapter(new SkillAdapter(item.getSkills()));
    }
}
