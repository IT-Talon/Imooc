package com.hzcwtech.imooc.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.CourseRecommendAdapter;
import com.hzcwtech.imooc.adapter.DownLoadDetailAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.BannerAdvertModel;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.entity.model.MainCourseModel;
import com.hzcwtech.imooc.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends BaseFragment {


    @BindView(R.id.toolbar_name)
    TextView toolbarName;
    @BindView(R.id.toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar_email)
    ImageView toolbarEmail;
    @BindView(R.id.toolbar_shopping_cart)
    ImageView toolbarShoppingCart;
    @BindView(R.id.tv_cache)
    TextView mTvCache;
    @BindView(R.id.memory_progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.rec_download_detail)
    RecyclerView mRecDownloadDetail;
    @BindView(R.id.rec_course_recommend)
    RecyclerView mRecCouseRecommend;
    Unbinder unbinder;

    private DownLoadDetailAdapter mDownLoadDetailAdapter;
    private CourseRecommendAdapter mCourseRecommendAdapter;

    public static DownloadFragment newInstance() {

        Bundle args = new Bundle();

        DownloadFragment fragment = new DownloadFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        toolbarName.setText("下载");
        mTvCache.setText(CommonUtil.fillString(getContext(), R.string.format_available_memory, CommonUtil.getAvailMemory(getContext(), true)));
        mProgressBar.setProgress(CommonUtil.getUsedPercentValue(getContext()));
        initRecyclerView();
        getData();
    }

    private void getData() {
        String jsonData = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"course_type\": 2,\n" +
                "      \"id\": \"108\",\n" +
                "      \"is_buy\": 0,\n" +
                "      \"is_learn\": 0,\n" +
                "      \"is_learned\": 0,\n" +
                "      \"name\": \"Kotlin系统入门与进阶\",\n" +
                "      \"numbers\": \"275\",\n" +
                "      \"order_type\": 2,\n" +
                "      \"pic\": \"http://szimg.mukewang.com/59313618000198ed05400300-360-202.jpg\",\n" +
                "      \"price\": 9900,\n" +
                "      \"share\": \"http://www.imooc.com/m/wap/shizhan/classindex.html?cid=108\",\n" +
                "      \"short_description\": \"2017 Google I/O 大会正式认定的Android一级开发语言，现在不学，等待何时\",\n" +
                "      \"type\": 2,\n" +
                "      \"wap\": \"http://coding.imooc.com/wap/classindex/cid/108\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"course_type\": 2,\n" +
                "      \"id\": \"106\",\n" +
                "      \"is_buy\": 0,\n" +
                "      \"is_learn\": 0,\n" +
                "      \"is_learned\": 0,\n" +
                "      \"name\": \"插件化与热修复App的原理及实战\",\n" +
                "      \"numbers\": \"60\",\n" +
                "      \"order_type\": 2,\n" +
                "      \"pic\": \"http://szimg.mukewang.com/5938b0f100016a9e05400300-360-202.jpg\",\n" +
                "      \"price\": 24800,\n" +
                "      \"share\": \"http://www.imooc.com/m/wap/shizhan/classindex.html?cid=106\",\n" +
                "      \"short_description\": \"掌握当下Android最热门的技术，动态更新、插件化、热修复综合实战应用\",\n" +
                "      \"type\": 2,\n" +
                "      \"wap\": \"http://coding.imooc.com/wap/classindex/cid/106\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"course_type\": 2,\n" +
                "      \"id\": \"101\",\n" +
                "      \"is_buy\": 0,\n" +
                "      \"is_learn\": 0,\n" +
                "      \"is_learned\": 0,\n" +
                "      \"name\": \"BAT大牛经验之谈 全面系统解析Android面试\",\n" +
                "      \"numbers\": \"429\",\n" +
                "      \"order_type\": 2,\n" +
                "      \"pic\": \"http://szimg.mukewang.com/5909a1250001197e05400300-360-202.jpg\",\n" +
                "      \"price\": 16800,\n" +
                "      \"share\": \"http://www.imooc.com/m/wap/shizhan/classindex.html?cid=101\",\n" +
                "      \"short_description\": \"2017最全面的Android面试课程，赢取称心offer的不二之选\",\n" +
                "      \"type\": 2,\n" +
                "      \"wap\": \"http://coding.imooc.com/wap/classindex/cid/101\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"course_type\": 2,\n" +
                "      \"id\": \"100\",\n" +
                "      \"is_buy\": 0,\n" +
                "      \"is_learn\": 0,\n" +
                "      \"is_learned\": 0,\n" +
                "      \"name\": \"带后台的IM即时通讯App 全程MVP手把手打造\",\n" +
                "      \"numbers\": \"138\",\n" +
                "      \"order_type\": 2,\n" +
                "      \"pic\": \"http://szimg.mukewang.com/59118b92000147bf05400300-360-202.jpg\",\n" +
                "      \"price\": 46600,\n" +
                "      \"share\": \"http://www.imooc.com/m/wap/shizhan/classindex.html?cid=100\",\n" +
                "      \"short_description\": \"采用Android最热门MVP设计模式，完成一整套IM即时通讯APP实现前后台技术\",\n" +
                "      \"type\": 2,\n" +
                "      \"wap\": \"http://coding.imooc.com/wap/classindex/cid/100\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497251308959\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(jsonData, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            List<CourseDetailModel> recommendData = httpEntity.getDataObject(new TypeReference<List<CourseDetailModel>>() {
            });
            mCourseRecommendAdapter.setNewData(recommendData);
        }
    }

    private void initRecyclerView() {
        mDownLoadDetailAdapter = new DownLoadDetailAdapter(new ArrayList<MainCourseModel>());
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null);
        mDownLoadDetailAdapter.setEmptyView(emptyView);
        mRecDownloadDetail.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecDownloadDetail.setAdapter(mDownLoadDetailAdapter);

        mCourseRecommendAdapter = new CourseRecommendAdapter(new ArrayList<CourseDetailModel>());
        mRecCouseRecommend.setLayoutManager(new GridLayoutManager(getContext(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecCouseRecommend.setAdapter(mCourseRecommendAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
