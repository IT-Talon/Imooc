package com.hzcwtech.imooc.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.HomeTabRecyclerViewAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.model.HomeTabModel;
import com.hzcwtech.imooc.other.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.toolbar_name)
    TextView toolbarName;
    @BindView(R.id.toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar_email)
    ImageView toolbarEmail;
    @BindView(R.id.toolbar_shopping_cart)
    ImageView toolbarShoppingCart;
    @BindView(R.id.banner_home)
    Banner mBanner;
    @BindView(R.id.tab_recyclerView)
    RecyclerView tabRecyclerView;
    Unbinder unbinder;

    private Integer[] images = {R.mipmap.banner_img1, R.mipmap.banner_img2, R.mipmap.banner_img3, R.mipmap.banner_img4};
    private List<HomeTabModel> homeTabData;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        toolbarName.setText("首页");
        initBanner();
        initRecyclerView();

    }

    private void initRecyclerView() {
        tabRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()) {
                                             @Override
                                             public boolean canScrollVertically() {
                                                 return false;
                                             }
                                         }
        );
        homeTabData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HomeTabModel model = new HomeTabModel();
            model.setTabName("实战" + i);
            model.setImgUrl("http://img.mukewang.com/563aff300001f47400900090.jpg");
            homeTabData.add(new HomeTabModel());
        }
        tabRecyclerView.setAdapter(new HomeTabRecyclerViewAdapter(homeTabData));
    }

    private void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<Integer> imgList = new ArrayList<>();
        Collections.addAll(imgList, images);
        mBanner.setImages(imgList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        mBanner.stopAutoPlay();
        super.onStop();
    }
}
