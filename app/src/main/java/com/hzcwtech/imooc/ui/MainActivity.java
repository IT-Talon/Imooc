package com.hzcwtech.imooc.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.base.BaseAvtivity;
import com.hzcwtech.imooc.fragment.CourseFragment;
import com.hzcwtech.imooc.fragment.DownloadFragment;
import com.hzcwtech.imooc.fragment.HomeFragment;
import com.hzcwtech.imooc.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAvtivity {

    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.tabContent)
    FrameLayout tabContent;
    @BindView(R.id.tabHost)
    FragmentTabHost mTabHost;

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
        initView();
        initData();
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
}
