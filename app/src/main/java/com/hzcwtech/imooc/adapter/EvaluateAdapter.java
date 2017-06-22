package com.hzcwtech.imooc.adapter;

import android.graphics.Color;
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
import com.hzcwtech.imooc.entity.model.EvaluateModel;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.GlideCircleTransform;
import com.hzcwtech.imooc.utils.ResourceUtil;

import java.util.List;

/**
 * Created by Talon on 2017/6/22.
 */

public class EvaluateAdapter extends BaseQuickAdapter<EvaluateModel, BaseViewHolder> {
    public EvaluateAdapter(@Nullable List<EvaluateModel> data) {
        super(R.layout.item_reyclerview_evaluate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluateModel item) {
        Glide.with(mContext).load(item.getImg()).apply(new RequestOptions().transform(new GlideCircleTransform(mContext))).into((ImageView) helper.getView(R.id.img_user_pic));
        helper.setText(R.id.tv_user_nickname, item.getNickname())
                .setText(R.id.tv_time, item.getCreate_time())
                .setText(R.id.tv_content, item.getContent());
        RatingBar ratingBar = helper.getView(R.id.ratingBar_score);
        ratingBar.setRating(Float.valueOf(item.getComment_score()) / 2);
        String reply = "讲师回复: " + item.getReply();
        TextView tvReply = helper.getView(R.id.tv_reply);

        SpannableStringBuilder builder = new SpannableStringBuilder(reply);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(ResourceUtil.getColor(mContext, R.color.text_orange));
        builder.setSpan(redSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvReply.setText(builder);
    }
}
