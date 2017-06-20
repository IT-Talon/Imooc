package com.hzcwtech.imooc.ui.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.ViewPagerFragmentAdapter;
import com.hzcwtech.imooc.base.BaseActivity;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.ui.main.CourseFragment;
import com.hzcwtech.imooc.ui.main.HomeFragment;
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
        initView();
    }

    private void initView() {
        initVideoPlayer();
        initTabs();
        tvPrice.setText(CommonUtil.fillString(this, R.string.format_course_price, mCourse.getPrice() / 100));
    }

    private void initTabs() {
        fragments = new ArrayList<>();
        fragments.add(CourseFragment.newInstance());
        fragments.add(CourseFragment.newInstance());
        fragments.add(CourseFragment.newInstance());
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
        mVideoPlayer.setUp(VIDEO_URL, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mCourse.getName());
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
        return ResourceUtil.getColor(this, R.color.transparent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_translate, R.anim.out_fade_topbottom);
    }
}
