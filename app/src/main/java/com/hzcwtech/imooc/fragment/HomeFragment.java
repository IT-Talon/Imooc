package com.hzcwtech.imooc.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.HomeCourseMultiAdapter;
import com.hzcwtech.imooc.adapter.HomeTabRecAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.model.CourseModel;
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
    @BindView(R.id.recyclerView_course_recommend)
    RecyclerView courseRecomRecyclerView;
    Unbinder unbinder;

    private Integer[] images = {R.mipmap.banner_img1, R.mipmap.banner_img2, R.mipmap.banner_img3, R.mipmap.banner_img4};
    private List<HomeTabModel> homeTabData;
    private List<CourseModel> homeCourseData;

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
        tabRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        tabRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = 20;
                outRect.bottom = 20;
                outRect.top = 20;
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
                if (parent.getChildLayoutPosition(view) % 5 == 0) {
                    outRect.left = 0;
                }
            }
        });
        homeTabData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeTabModel model = new HomeTabModel();
            model.setTabName("实战" + i);
            model.setImgUrl("http://img.mukewang.com/563aff300001f47400900090.jpg");
            homeTabData.add(model);
        }
        tabRecyclerView.setAdapter(new HomeTabRecAdapter(homeTabData));

        courseRecomRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        courseRecomRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = 22;
                outRect.bottom = 22;
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.left = 0;
                }
            }
        });
        homeCourseData = new ArrayList<>();
        String jsonData = "{\n" +
                "          \"bgcolor_end\": \"#b3ff739b\",\n" +
                "          \"bgcolor_start\": \"#ffff739b\",\n" +
                "          \"course_type\": 1,\n" +
                "          \"id\": \"847\",\n" +
                "          \"is_learn\": 0,\n" +
                "          \"is_learned\": 0,\n" +
                "          \"level\": \"中级\",\n" +
                "          \"name\": \"使用java构建和维护接口自动化测试框架\",\n" +
                "          \"numbers\": \"40\",\n" +
                "          \"pic\": \"http://www.imooc.com/courseimg/s/cover043_s.jpg\",\n" +
                "          \"share\": \"http://www.imooc.com/learn/847\",\n" +
                "          \"short_description\": \"初识接口自动化框架\",\n" +
                "          \"skills\": [\n" +
                "            {\n" +
                "              \"id\": \"220\",\n" +
                "              \"name\": \"Java\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"1422\",\n" +
                "              \"name\": \"测试\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"type\": 1\n" +
                "        }";
        Gson gson = new Gson();
        CourseModel courseModel = gson.fromJson(jsonData, CourseModel.class);
        for (int i = 0; i < 6; i++) {
            homeCourseData.add(courseModel);
        }

        courseRecomRecyclerView.setAdapter(new HomeCourseMultiAdapter(homeCourseData));
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
