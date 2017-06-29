package com.hzcwtech.imooc.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.base.BaseActivity;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.UserDetailModel;
import com.hzcwtech.imooc.ui.main.CourseFragment;
import com.hzcwtech.imooc.ui.main.DownloadFragment;
import com.hzcwtech.imooc.ui.main.HomeFragment;
import com.hzcwtech.imooc.ui.main.MineFragment;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.GlideCircleTransform;
import com.hzcwtech.imooc.utils.ResourceUtil;
import com.hzcwtech.imooc.view.TabFragmentHost;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.tabContent)
    FrameLayout tabContent;
    @BindView(R.id.tabHost)
    TabFragmentHost mTabHost;


    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, CourseFragment.class, DownloadFragment.class, MineFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_course_btn, R.drawable.tab_download_btn,
            R.drawable.tab_mine_btn};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "课程", "下载", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSlidingMenu();
        initView();
        initData();
    }

    private void initSlidingMenu() {
        SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);  //Menu所在位置
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); //边缘滑动有效
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);    //menu边缘阴影宽度
        slidingMenu.setShadowDrawable(R.drawable.shadow);   //阴影图片
        slidingMenu.setFadeDegree(0.8f);   //menu褪色程度
        slidingMenu.setBehindOffsetRes(R.dimen.behind_width);   //滑动后内容部分还剩余的宽度
//        slidingMenu.setBehindWidthRes(R.dimen.behind_width);    //滑动后菜单显示宽度
        slidingMenu.setBehindScrollScale(0.5f);    //菜单滚动速度比内容滚动速度。。。
        slidingMenu.setMenu(R.layout.fragment_mine); //设置菜单部分布局
        RecyclerView recyclerView = new RecyclerView(this);
        slidingMenu.setSelectedView(recyclerView);
//        slidingMenu.setSelectorDrawable(R.drawable.avatar);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);     //滑动部分包括ActionBar
//                SlidingMenu.SLIDING_CONTENT);   //不包括ActionBar

        TextView tvUserNickname = (TextView) slidingMenu.getMenu().findViewById(R.id.tv_user_nickname);
        TextView tvStudyTime = (TextView) slidingMenu.getMenu().findViewById(R.id.tv_study_time);
        TextView tvUserFollows = (TextView) slidingMenu.getMenu().findViewById(R.id.tv_user_follows);
        TextView tvUserFans = (TextView) slidingMenu.getMenu().findViewById(R.id.tv_user_fans);
        TextView tvUserIntegral = (TextView) slidingMenu.getMenu().findViewById(R.id.tv_user_integral);
        ImageView imgUserPic = (ImageView) slidingMenu.getMenu().findViewById(R.id.img_user_pic);

        String jsonData = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"about_me\": \"在这偌大的城市里，梦想变得遥不可及。\",\n" +
                "      \"admin_type\": 0,\n" +
                "      \"admin_url\": \"\",\n" +
                "      \"author\": 0,\n" +
                "      \"bbsnotice\": 0,\n" +
                "      \"city\": \"\",\n" +
                "      \"company_id\": 0,\n" +
                "      \"coursetypesum\": 0,\n" +
                "      \"courtype\": 0,\n" +
                "      \"credit\": 0,\n" +
                "      \"email\": \"731838568@qq.com\",\n" +
                "      \"fans\": 2,\n" +
                "      \"follows\": 5,\n" +
                "      \"integral\": 1,\n" +
                "      \"is_v\": 0,\n" +
                "      \"job\": \"学生\",\n" +
                "      \"learn\": \"45小时26分\",\n" +
                "      \"mp\": 1987,\n" +
                "      \"nickname\": \"T_alon\",\n" +
                "      \"notice\": 0,\n" +
                "      \"phone\": \"\",\n" +
                "      \"pic\": \"http://img.mukewang.com/55dc87c70001d7f001000100-370-370.jpg\",\n" +
                "      \"realname\": \"\",\n" +
                "      \"school\": \"\",\n" +
                "      \"sex\": 1,\n" +
                "      \"shield\": 0,\n" +
                "      \"tag\": \"这位童鞋很懒，什么也没有留下～～！\",\n" +
                "      \"uid\": 2288824,\n" +
                "      \"uuid\": \"28915fb80011305ade18ef7757052d64\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497247861806\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(jsonData, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            List<UserDetailModel> userDetailModelList = httpEntity.getDataObject(new TypeReference<List<UserDetailModel>>() {
            });
            UserDetailModel userDetail = userDetailModelList.get(0);
            if (userDetail != null) {
                String learnTime = userDetail.getLearn().substring(0, userDetail.getLearn().indexOf('时') + 1);
                tvUserNickname.setText(userDetail.getNickname());
                tvStudyTime.setText(CommonUtil.fillString(this, R.string.format_learn_time, learnTime));
                tvUserFollows.setText(CommonUtil.fillString(this, R.string.format_follows, userDetail.getFollows()));
                tvUserFans.setText(CommonUtil.fillString(this, R.string.format_fans, userDetail.getFans()));
                tvUserIntegral.setText(CommonUtil.fillString(this, R.string.format_integral, userDetail.getIntegral()));
                Glide.with(this).load(userDetail.getPic()).apply(new RequestOptions().transform(new GlideCircleTransform(this))).into(imgUserPic);
            }
        }

//        slidingMenu.showMenu();

    }

    private void initView() {
        //得到fragment的个数
        int count = fragmentArray.length;
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fragment_content);
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
//            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }

    }

    private void initData() {
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_tab);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.text_tab);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    @Override
    protected Integer getStatusBarColor() {
        return ResourceUtil.getColor(this, R.color.toolbar_background);
    }
}
