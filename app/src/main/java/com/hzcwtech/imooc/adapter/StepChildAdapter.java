package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.CourseSummaryModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/23.
 */

public class StepChildAdapter extends BaseQuickAdapter<CourseSummaryModel, BaseViewHolder> {
    public StepChildAdapter(@Nullable List<CourseSummaryModel> data) {
        super(R.layout.item_recyclerview_step_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseSummaryModel item) {
        helper.setText(R.id.step_child_title, item.getName())
                .setText(R.id.step_child_content, item.getShort_description())
                .setText(R.id.step_child_time, item.getCourse_time());

    }
}
