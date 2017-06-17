package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.SkillsModel;
import com.hzcwtech.imooc.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by Talon on 2017/6/17.
 */

public class SkillAdapter extends BaseQuickAdapter<SkillsModel, BaseViewHolder> {
    public SkillAdapter(@Nullable List<SkillsModel> data) {
        super(R.layout.item_recyclerview_skills, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SkillsModel item) {
        helper.setText(R.id.skill_name, item.getName());
        Glide.with(mContext).load(item.getPic()).apply(new RequestOptions().transform(new GlideCircleTransform(mContext))).into((ImageView) helper.getView(R.id.skill_pic));
    }
}
