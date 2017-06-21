package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.ConsultModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/20.
 */

public class ConsultAdapter extends BaseQuickAdapter<ConsultModel, BaseViewHolder> {
    private List<ConsultModel> consults;

    public ConsultAdapter(@Nullable List<ConsultModel> data) {
        super(R.layout.item_recyclerview_consult, data);
        consults = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultModel item) {
        helper.setText(R.id.tv_consult_content, item.getContent())
                .setText(R.id.tv_consult_answer, item.getAnswer())
                .setText(R.id.tv_consult_time, item.getCreate_time())
                .setText(R.id.tv_praise, item.getPraise());
        ImageView img = helper.getView(R.id.img_praise_by_me);
        img.setImageResource(item.getMypraise() == 1 ? R.drawable.ic_praised : R.drawable.ic_no_praise);

    }
}
