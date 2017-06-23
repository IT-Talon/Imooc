package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.EvaluateSummaryModel;
import com.hzcwtech.imooc.utils.GlideCircleTransform;
import com.hzcwtech.imooc.utils.ResourceUtil;

import java.util.List;

/**
 * Created by Talon on 2017/6/22.
 */

public class EvaluateSummaryAdapter extends BaseQuickAdapter<EvaluateSummaryModel, BaseViewHolder> {
    public EvaluateSummaryAdapter(@Nullable List<EvaluateSummaryModel> data) {
        super(R.layout.item_reyclerview_evaluate_summary, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluateSummaryModel item) {
        Glide.with(mContext).load(item.getImg()).apply(new RequestOptions().transform(new GlideCircleTransform(mContext))).into((ImageView) helper.getView(R.id.img_user_pic));
        RatingBar ratingBar = helper.getView(R.id.ratingBar_score);
        helper.setText(R.id.tv_user_nickname, item.getNickname())
                .setText(R.id.tv_content, item.getContent());
        ratingBar.setRating(Float.valueOf(item.getComment_score()) / 2);
    }
}
