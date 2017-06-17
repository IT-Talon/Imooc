package com.hzcwtech.imooc.ui.main;


import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.HomeCourseMultiAdapter;
import com.hzcwtech.imooc.adapter.HomeTabRecAdapter;
import com.hzcwtech.imooc.app.http.HttpApi;
import com.hzcwtech.imooc.app.http.RetrofitManager;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.BannerAdvertModel;
import com.hzcwtech.imooc.entity.model.BannerModel;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.entity.model.CourseSummaryModel;
import com.hzcwtech.imooc.entity.model.HomePicModel;
import com.hzcwtech.imooc.entity.model.HomeTabModel;
import com.hzcwtech.imooc.utils.GlideImageLoader;
import com.hzcwtech.imooc.utils.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private static final String TAG = HomeFragment.class.getSimpleName();

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
    @BindView(R.id.recyclerView_career_path)
    RecyclerView careerPathRecyclerView;
    @BindView(R.id.swipe_home_fragment)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;

    private HomeCourseMultiAdapter mRecomAadapter;
    private HomeCourseMultiAdapter mCareerPathAdapter;
    private HomeTabRecAdapter mTabPicAadapter;
    private List<HomeTabModel> homeTabData;
    private List<CourseDetailModel> homeCourseData;
    private List<CourseDetailModel> careerPathData;
    private List<Uri> bannerData;

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
        LogUtil.d(TAG, "onCreateView: ");

        return view;
    }

    private void getdata() {
        Call<HttpEntity> call = RetrofitManager.getInstance().create(HttpApi.class).getHomeCourse();
        call.enqueue(new Callback<HttpEntity>() {
            @Override
            public void onResponse(Call<HttpEntity> call, Response<HttpEntity> response) {
                Toast.makeText(getContext(), "网络请求成功", Toast.LENGTH_SHORT).show();
                List<CourseSummaryModel> allCourseData = response.body().getDataObject(new TypeReference<List<CourseSummaryModel>>() {
                });
                for (CourseSummaryModel couserSummary : allCourseData) {
                    // type 1 课程推荐 2 实战推荐 3 新课上路 4 5 慕课精英名师推荐 6 banner广告 7 职业路径
                    switch (couserSummary.getType()) {
                        case 1:
                            mRecomAadapter.setNewData(couserSummary.getCourse());
                            break;
                        case 7:
                            mCareerPathAdapter.setNewData(couserSummary.getCourse());
                            break;
                    }
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<HttpEntity> call, Throwable t) {
                Toast.makeText(getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void initView() {
        initSwipeRefresh();
        initRecyclerView();
        getBannerData();
        getdata();
        toolbarName.setText("首页");
    }

    private void initRecyclerView() {
        mTabPicAadapter = new HomeTabRecAdapter(new ArrayList<HomePicModel>());
        tabRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        tabRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = 20;
                outRect.bottom = 10;
                outRect.top = 10;
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
                if (parent.getChildLayoutPosition(view) % 5 == 0) {
                    outRect.left = 0;
                }
            }
        });
        tabRecyclerView.setAdapter(mTabPicAadapter);

        mRecomAadapter = new HomeCourseMultiAdapter(new ArrayList<CourseDetailModel>());
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
        courseRecomRecyclerView.setAdapter(mRecomAadapter);

        mCareerPathAdapter = new HomeCourseMultiAdapter(new ArrayList<CourseDetailModel>());
        careerPathRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        careerPathRecyclerView.setAdapter(mCareerPathAdapter);
    }

    private void initSwipeRefresh() {
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBannerData();
                getdata();
            }
        });
    }

    private void getBannerData() {
        bannerData = new ArrayList<>();
        String bannerJson = "{\n" +
                "  \"data\": {\n" +
                "    \"banner\": [\n" +
                "      {\n" +
                "        \"id\": 865,\n" +
                "        \"links\": \"http://coding.imooc.com/class/96.html\",\n" +
                "        \"name\": \"JAVA企业级\",\n" +
                "        \"pic\": \"http://img.mukewang.com/592e9c01000181cd07500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1178,\n" +
                "        \"links\": \"http://coding.imooc.com/class/106.html\",\n" +
                "        \"name\": \"热修复与插件化APP的原理及实战\",\n" +
                "        \"pic\": \"http://img.mukewang.com/5938bbbc00019e3c07500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 889,\n" +
                "        \"links\": \"http://coding.imooc.com/class/107.html\",\n" +
                "        \"name\": \"vue音乐APP\",\n" +
                "        \"pic\": \"http://img.mukewang.com/592e43720001213d07500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1171,\n" +
                "        \"links\": \"http://coding.imooc.com/class/109.html\",\n" +
                "        \"name\": \"手把手从0打造电商平台-前端开发\",\n" +
                "        \"pic\": \"http://img.mukewang.com/5934cfe60001290007500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 755,\n" +
                "        \"links\": \"http://coding.imooc.com/class/108.html\",\n" +
                "        \"name\": \"Kotlin系统入门与进阶\",\n" +
                "        \"pic\": \"http://img.mukewang.com/59361adf000182d607500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 918,\n" +
                "        \"links\": \"http://www.imooc.com/wenda/detail/351239 \",\n" +
                "        \"name\": \"花式填坑第7期\",\n" +
                "        \"pic\": \"http://img.mukewang.com/5938ae570001909307500250.jpg\",\n" +
                "        \"type\": 99,\n" +
                "        \"type_id\": 0\n" +
                "      }\n" +
                "    ],\n" +
                "    \"pic\": [\n" +
                "      {\n" +
                "        \"pic\": \"http://www.imooc.com/static/img/andriod/pic/actual_day@1x.png\",\n" +
                "        \"pic_night\": \"http://www.imooc.com/static/img/andriod/pic/actual_night@1x.png\",\n" +
                "        \"type\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"pic\": \"http://www.imooc.com/static/img/andriod/pic/path_day@1x.png\",\n" +
                "        \"pic_night\": \"http://www.imooc.com/static/img/andriod/pic/path_night@1x.png\",\n" +
                "        \"type\": 6\n" +
                "      },\n" +
                "      {\n" +
                "        \"pic\": \"http://www.imooc.com/static/img/andriod/pic/question_day@1x.png\",\n" +
                "        \"pic_night\": \"http://www.imooc.com/static/img/andriod/pic/question_night@1x.png\",\n" +
                "        \"type\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"pic\": \"http://www.imooc.com/static/img/andriod/pic/note_day@1x.png\",\n" +
                "        \"pic_night\": \"http://www.imooc.com/static/img/andriod/pic/note_night@1x.png\",\n" +
                "        \"type\": 4\n" +
                "      },\n" +
                "      {\n" +
                "        \"pic\": \"http://www.imooc.com/static/img/andriod/pic/discover_day@1x.png\",\n" +
                "        \"pic_night\": \"http://www.imooc.com/static/img/andriod/pic/discover_night@1x.png\",\n" +
                "        \"type\": 5\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 0,\n" +
                "  \"timestamp\": 1497247861869\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(bannerJson, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            BannerAdvertModel bannerAdvertModel = httpEntity.getDataObject(BannerAdvertModel.class);
            initBanner(bannerAdvertModel.getBanner());
            mTabPicAadapter.setNewData(bannerAdvertModel.getPic());
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        LogUtil.d(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, "onDetach: ");
        super.onDetach();
    }

    private void initBanner(List<BannerModel> banner) {
        for (BannerModel data : banner) {
            bannerData.add(Uri.parse(data.getPic()));
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerData);
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
        LogUtil.d(TAG, "onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        LogUtil.d(TAG, "onStart: ");
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, "onStop: ");
        mBanner.stopAutoPlay();
        super.onStop();
    }
}
