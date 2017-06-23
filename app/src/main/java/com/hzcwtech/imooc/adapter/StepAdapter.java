package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.StepModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/23.
 */

public class StepAdapter extends BaseQuickAdapter<StepModel, BaseViewHolder> {

    public StepAdapter(@Nullable List<StepModel> data) {
        super(R.layout.item_recyclerview_step, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StepModel item) {
        helper.setText(R.id.step_name, item.getName());
        RecyclerView recyclerView = helper.getView(R.id.rec_classInfo_step_child);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new StepChildAdapter(item.getCourselist()));
    }
}
