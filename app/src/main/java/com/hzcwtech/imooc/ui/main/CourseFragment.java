package com.hzcwtech.imooc.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.HomeCourseAadapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.AllCourseModel;
import com.hzcwtech.imooc.entity.model.BannerAdvertModel;
import com.hzcwtech.imooc.entity.model.MainCourseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseFragment {

    @BindView(R.id.toolbar_name)
    TextView toolbarName;
    @BindView(R.id.toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar_email)
    ImageView toolbarEmail;
    @BindView(R.id.toolbar_shopping_cart)
    ImageView toolbarShoppingCart;
    @BindView(R.id.recyclerView_main_course)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private List<MainCourseModel> data;

    public static CourseFragment newInstance() {

        Bundle args = new Bundle();

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        getDada();
        toolbarName.setText("课程");
        initRecyclerView();
    }

    private void getDada() {
        data = new ArrayList<>();
        String jsonData = "{\n" +
                "  \"data\": {\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"1\",\n" +
                "        \"marking\": \"fe\",\n" +
                "        \"name\": \"前端开发\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 7,\n" +
                "            \"name\": \"HTML/CSS\",\n" +
                "            \"numbers\": 41,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2670001146402400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 44,\n" +
                "            \"name\": \"JavaScript\",\n" +
                "            \"numbers\": 84,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2720001296402400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 219,\n" +
                "            \"name\": \"jQuery\",\n" +
                "            \"numbers\": 19,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec27d0001738602400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 221,\n" +
                "            \"name\": \"Html5\",\n" +
                "            \"numbers\": 29,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec24d00015d1702400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 222,\n" +
                "            \"name\": \"Node.js\",\n" +
                "            \"numbers\": 13,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec25a0001094402400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1123,\n" +
                "            \"name\": \"AngularJS\",\n" +
                "            \"numbers\": 8,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2220001272802400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1260,\n" +
                "            \"name\": \"WebApp\",\n" +
                "            \"numbers\": 13,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1e10001860802400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1261,\n" +
                "            \"name\": \"前端工具\",\n" +
                "            \"numbers\": 13,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1d4000103f702400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1262,\n" +
                "            \"name\": \"CSS3\",\n" +
                "            \"numbers\": 26,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1c60001407602400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1263,\n" +
                "            \"name\": \"Bootstrap\",\n" +
                "            \"numbers\": 5,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1ac0001f4ea02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1374,\n" +
                "            \"name\": \"React.JS\",\n" +
                "            \"numbers\": 5,\n" +
                "            \"pic\": \"http://img.mukewang.com/579f2dbd000130cc02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1423,\n" +
                "            \"name\": \"Vue.js\",\n" +
                "            \"numbers\": 3,\n" +
                "            \"pic\": \"http://img.mukewang.com/57b2d0d2000136e702400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1429,\n" +
                "            \"name\": \"Sass/Less\",\n" +
                "            \"numbers\": 6,\n" +
                "            \"pic\": \"http://img.mukewang.com/57b4492a0001223302400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"2\",\n" +
                "        \"marking\": \"be\",\n" +
                "        \"name\": \"后端开发\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"PHP\",\n" +
                "            \"numbers\": 82,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1f00001739002400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 220,\n" +
                "            \"name\": \"Java\",\n" +
                "            \"numbers\": 91,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2370001bebd02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1118,\n" +
                "            \"name\": \"Python\",\n" +
                "            \"numbers\": 20,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec218000165b502400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1273,\n" +
                "            \"name\": \"C\",\n" +
                "            \"numbers\": 4,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1990001b8ad02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1331,\n" +
                "            \"name\": \"C++\",\n" +
                "            \"numbers\": 14,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1720001449902400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1358,\n" +
                "            \"name\": \"Go\",\n" +
                "            \"numbers\": 4,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1570001251d02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1362,\n" +
                "            \"name\": \"C#\",\n" +
                "            \"numbers\": 3,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec0e70001ae1702400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 3629,\n" +
                "            \"name\": \"Ruby\",\n" +
                "            \"numbers\": 1,\n" +
                "            \"pic\": \"http://img.mukewang.com/58453e110001204302400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"3\",\n" +
                "        \"marking\": \"mobile\",\n" +
                "        \"name\": \"移动开发\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 223,\n" +
                "            \"name\": \"Android\",\n" +
                "            \"numbers\": 118,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec1fe0001ab9f02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 955,\n" +
                "            \"name\": \"iOS\",\n" +
                "            \"numbers\": 56,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec22c000166b602400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1281,\n" +
                "            \"name\": \"Unity 3D\",\n" +
                "            \"numbers\": 11,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec18d0001422902400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1361,\n" +
                "            \"name\": \"Cocos2d-x\",\n" +
                "            \"numbers\": 13,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec0fa0001bafb02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"4\",\n" +
                "        \"marking\": \"data\",\n" +
                "        \"name\": \"数据库\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 952,\n" +
                "            \"name\": \"MySQL\",\n" +
                "            \"numbers\": 10,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec28c00016afc02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 958,\n" +
                "            \"name\": \"MongoDB\",\n" +
                "            \"numbers\": 18,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2a400019b1502400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1309,\n" +
                "            \"name\": \"Oracle\",\n" +
                "            \"numbers\": 8,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec17f0001a9a302400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1366,\n" +
                "            \"name\": \"SQL Server\",\n" +
                "            \"numbers\": 1,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec0b700018f8902400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"6\",\n" +
                "        \"marking\": \"cb\",\n" +
                "        \"name\": \"云计算&大数据\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 1122,\n" +
                "            \"name\": \"云计算\",\n" +
                "            \"numbers\": 10,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec2b00001dc0902400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1359,\n" +
                "            \"name\": \"大数据\",\n" +
                "            \"numbers\": 18,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec12d0001749b02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"7\",\n" +
                "        \"marking\": \"op\",\n" +
                "        \"name\": \"运维&测试\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 468,\n" +
                "            \"name\": \"Linux\",\n" +
                "            \"numbers\": 29,\n" +
                "            \"pic\": \"http://img.mukewang.com/565ec29900019ddd02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 1422,\n" +
                "            \"name\": \"测试\",\n" +
                "            \"numbers\": 12,\n" +
                "            \"pic\": \"http://img.mukewang.com/57aa954b0001397802400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"haverelatepath\": 0,\n" +
                "        \"id\": \"5\",\n" +
                "        \"marking\": \"photo\",\n" +
                "        \"name\": \"UI设计\",\n" +
                "        \"relate_path_pic\": \"\",\n" +
                "        \"skills\": [\n" +
                "          {\n" +
                "            \"id\": 4732,\n" +
                "            \"name\": \"设计基础\",\n" +
                "            \"numbers\": 1,\n" +
                "            \"pic\": \"http://img.mukewang.com/58af9ee40001269602400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 4733,\n" +
                "            \"name\": \"设计工具\",\n" +
                "            \"numbers\": 8,\n" +
                "            \"pic\": \"http://img.mukewang.com/58af9f8b0001e56802400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 4734,\n" +
                "            \"name\": \"APPUI设计\",\n" +
                "            \"numbers\": 1,\n" +
                "            \"pic\": \"http://img.mukewang.com/58afa039000194df02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          },\n" +
                "          {\n" +
                "            \"id\": 4735,\n" +
                "            \"name\": \"动画动效\",\n" +
                "            \"numbers\": 6,\n" +
                "            \"pic\": \"http://img.mukewang.com/58afa0d10001a39f02400240-240-240.jpg\",\n" +
                "            \"typestate\": 0\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"全部课程\",\n" +
                "    \"numbers\": 725,\n" +
                "    \"pic\": \"http://static.mukewang.com/static/img/all.png\"\n" +
                "  },\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497247862397\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(jsonData, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            AllCourseModel allCourseModel = httpEntity.getDataObject(AllCourseModel.class);
            data.clear();
            data.addAll(allCourseModel.getCategories());
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new HomeCourseAadapter(data));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
