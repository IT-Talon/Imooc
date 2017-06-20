package com.hzcwtech.imooc.ui.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.base.BaseActivity;
import com.hzcwtech.imooc.entity.model.CourseDetailModel;
import com.hzcwtech.imooc.utils.AppUtils;
import com.hzcwtech.imooc.utils.ResourceUtil;
import com.hzcwtech.imooc.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.tencent.qq.QQ;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CourseDetailActivity extends BaseActivity {

    private static final String COURSE = "course";
    private static final String IMG_URL = "http://climg.imooc.com/59030cc50001144806000338.jpg";
    private static final String VIDEO_URL = "http://clvideo.mukewang.com/58c20168e520e59d7f8b459b/H.mp4";

    @BindView(R.id.videoPlayer)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen mVideoPlayer;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private CourseDetailModel mCourse;

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
        initView();
        mCourse = (CourseDetailModel) getIntent().getSerializableExtra(COURSE);
    }

    private void initView() {
        JCVideoPlayerStandard.ACTION_BAR_EXIST = false;
        JCVideoPlayerStandard.TOOL_BAR_EXIST = false;
        mVideoPlayer.setUp(VIDEO_URL, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "前端小白入门");
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
