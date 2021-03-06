package com.hzcwtech.imooc.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.entity.model.HomePicModel;
import com.hzcwtech.imooc.entity.model.HomeTabModel;

import java.util.List;

/**
 * Created by Talon on 2017/6/9.
 */

public class HomeTabRecAdapter extends BaseQuickAdapter<HomePicModel, BaseViewHolder> {

    public HomeTabRecAdapter(@Nullable List<HomePicModel> data) {
        super(R.layout.item_home_tab, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePicModel item) {
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.img_home_tab));
    }
}
