package com.hzcwtech.imooc.ui.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.ViewPagerFragmentAdapter;
import com.hzcwtech.imooc.base.BaseActivity;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.ConsultModel;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.utils.AppUtils;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.ResourceUtil;
import com.hzcwtech.imooc.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CourseDetailActivity extends BaseActivity {

    private static final String COURSE = "course";
    private static final String IMG_URL = "http://climg.imooc.com/59030cc50001144806000338.jpg";
    private static final String VIDEO_URL = "http://clvideo.mukewang.com/58c20168e520e59d7f8b459b/H.mp4";

    @BindView(R.id.videoPlayer)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen mVideoPlayer;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.tv_course_price)
    TextView tvPrice;
    @BindView(R.id.tv_buy)
    TextView tvBuy;

    private CourseDetailModel mCourse;
    private List<Fragment> fragments;
    private List<String> titles;
    private ArrayList<ConsultModel> consultlist;

    public static void start(Activity activity, CourseDetailModel course) {
        Intent start = new Intent(activity, CourseDetailActivity.class);
        start.putExtra(COURSE, course);
        activity.startActivity(start);
        activity.overridePendingTransition(R.anim.in_fade_bottomtop, R.anim.no_translate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_course_detail);
        ButterKnife.bind(this);
        mCourse = (CourseDetailModel) getIntent().getSerializableExtra(COURSE);
        initData();
        initView();
    }

    private void initData() {
        consultlist = new ArrayList<>();
        String jsonData = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"answer\": \"有的，150天的有效期，还有两次的免费延期机会，每次各延一个月。也就是说你最多可以有210天的学习时间哦。赶快行动吧！\",\n" +
                "      \"content\": \"这是有时间限制的吗？\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2144\",\n" +
                "      \"mypraise\": 1,\n" +
                "      \"praise\": \"35\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"html5是html的升级。对于初学者，一般建议先学html，然后再学html5。因为html是最基础的知识，只有学会了基础才能更好的进阶。《前端小白入门系列课程》路径中是从最基础的讲起，讲的是html，没有html5的新增内容、而《HTML5与CSS3实现动态网页系列课程》讲的是html5相对于html新增的内容，没有html的基础知识。\",\n" +
                "      \"content\": \"和那个599的前端区别在哪里的啊\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2143\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"17\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"您好，想成为一名合格的PHP工程师，前端是基本功，建议先修炼前端入门知识后，再继续学习PHP相关内容！\",\n" +
                "      \"content\": \"打算以PHP工程师为目标，学习前端有用吗？\",\n" +
                "      \"create_time\": \"2017-03-23\",\n" +
                "      \"id\": \"2479\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"12\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"路径课程以视频方式配合小案例，详细、系统的讲解，配有丰富的练习题，做到学与练结合，同时还有上传作业题，有专业的老师进行点评并给予修改建议。另外，不会的内容还可以在问答区进行提问，助教老师会帮你解答。\",\n" +
                "      \"content\": \"这个课程和免费的html/css课程有什么区别吗\",\n" +
                "      \"create_time\": \"2017-03-11\",\n" +
                "      \"id\": \"2152\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"11\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"可以的哦，咱们每个步骤都有一个案例来练习和讲解，最后的实战是实现的大项目。\",\n" +
                "      \"content\": \"学完的话，能做项目吗？\",\n" +
                "      \"create_time\": \"2017-03-12\",\n" +
                "      \"id\": \"2168\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"10\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"您好~首先感谢您对慕课网免费课程的关注。这套职业路径课程和慕课网的免费课程是不一样的。这套课程是我们精心从0打造的专为小白用户入门前端开发而设计的。课程是依托练习，编程题而建立的。并且我们也有专业的助教团队进行辅导和1v1的作业批改，这些都是免费课没有的哦！慕课网祝您学习愉快\",\n" +
                "      \"content\": \"这些课程跟那些免费的课程有没有重复的？因为觉得课程有点贵，想谨慎一点\",\n" +
                "      \"create_time\": \"2017-03-24\",\n" +
                "      \"id\": \"2517\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"8\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"《前端小白入门》路径和《HTML5与CSS3实现动态网页》都是基础的路径，但是面向的用户群不一样，如果你没有任何基础，请购买《前端小白入门》，如果你有html和css基础，那么请购买《HTML5与CSS3实现动态网页》。针对两个基础路径后期我们都会退出承接这两个路径的不同的进阶路径， 亲要根据自己的实际情况，看清内容再购买哦，详情可以查看我们的宣传页。http://class.imooc.com/sc/2和http://class.imooc.com/sc/20\",\n" +
                "      \"content\": \"这个课程和那个599的课程内容是否有重叠的地方\",\n" +
                "      \"create_time\": \"2017-04-28\",\n" +
                "      \"id\": \"3703\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"6\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"主要有html、css、js、jQuery和实战课程，详情可见http://class.imooc.com/sc/20。此路径偏向基础，想学好前端这个都是必备的基础，实习生的程度应该是没有问题的。另外后期还有进阶和高级专题推出，欢迎来学习。\",\n" +
                "      \"content\": \"学完这套课程能到什么程度？\",\n" +
                "      \"create_time\": \"2017-03-23\",\n" +
                "      \"id\": \"2483\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"总共33门课，每门课中有若干个章节，每个章节里面有若干个视频节，每个小视频节的平均时长在十分钟左右，总时长为52个小时。详细的内容可以参考宣传页。http://class.imooc.com/sc/20\",\n" +
                "      \"content\": \"每节课多长时间啊\",\n" +
                "      \"create_time\": \"2017-06-11\",\n" +
                "      \"id\": \"5665\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"answer\": \"专题视频是全新制作，并不是慕课网以往免费视频的组合。\",\n" +
                "      \"content\": \"这个是放往期视频？\",\n" +
                "      \"create_time\": \"2017-04-12\",\n" +
                "      \"id\": \"3070\",\n" +
                "      \"mypraise\": 0,\n" +
                "      \"praise\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497853485419\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(jsonData, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            List<ConsultModel> consultData = httpEntity.getDataObject(new TypeReference<List<ConsultModel>>() {
            });
            if (consultData != null && !consultData.isEmpty()) {
                consultlist.addAll(consultData);
            }
        }
    }

    private void initView() {
        initVideoPlayer();
        initTabs();
        tvPrice.setText(CommonUtil.fillString(this, R.string.format_course_price, mCourse.getPrice() / 100));
    }

    private void initTabs() {
        fragments = new ArrayList<>();
        fragments.add(ConsultFragment.newInstance(consultlist));
        fragments.add(EvaluateFragment.newInstance());
        fragments.add(ConsultFragment.newInstance(consultlist));
        titles = new ArrayList<>();
        titles.add("介绍");
        titles.add("评价");
        titles.add("咨询");
        viewpager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, titles));
        tabs.setViewPager(viewpager);
    }

    private void initVideoPlayer() {
        JCVideoPlayerStandard.ACTION_BAR_EXIST = false;
        JCVideoPlayerStandard.TOOL_BAR_EXIST = false;
        mVideoPlayer.setUp(VIDEO_URL, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        Glide.with(this).load(IMG_URL).into(mVideoPlayer.thumbImageView);
        mVideoPlayer.setClickListener(new JCVideoPlayerStandardShowShareButtonAfterFullscreen.ButtonClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.clear:
                        onBackPressed();
                        break;
                    case R.id.share:
                        AppUtils.shareApp(CourseDetailActivity.this, mCourse, null);
                        break;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected Integer getStatusBarColor() {
        return ResourceUtil.getColor(this, R.color.toolbar_background);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_translate, R.anim.out_fade_topbottom);
    }
}
