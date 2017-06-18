package com.hzcwtech.imooc.ui.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.AllCourseModel;
import com.hzcwtech.imooc.entity.model.UserDetailModel;
import com.hzcwtech.imooc.utils.CommonUtil;
import com.hzcwtech.imooc.utils.GlideCircleTransform;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    private static final String TAG = MineFragment.class.getSimpleName();

    @BindView(R.id.toolbar_name)
    TextView toolbarName;
    @BindView(R.id.toolbar_search)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar_email)
    ImageView toolbarEmail;
    @BindView(R.id.toolbar_shopping_cart)
    ImageView toolbarShoppingCart;
    Unbinder unbinder;
    @BindView(R.id.img_user_pic)
    ImageView imgUserPic;
    @BindView(R.id.tv_study_time)
    TextView tvStudyTime;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_user_follows)
    TextView tvUserFollows;
    @BindView(R.id.tv_user_fans)
    TextView tvUserFans;
    @BindView(R.id.tv_user_integral)
    TextView tvUserIntegral;
    @BindView(R.id.receive_new_msg)
    SwitchButton receiveNewMsg;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private void initView() {
        toolbarName.setText("我的");
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
                tvStudyTime.setText(CommonUtil.fillString(getContext(), R.string.format_learn_time, learnTime));
                tvUserFollows.setText(CommonUtil.fillString(getContext(), R.string.format_follows, userDetail.getFollows()));
                tvUserFans.setText(CommonUtil.fillString(getContext(), R.string.format_fans, userDetail.getFans()));
                tvUserIntegral.setText(CommonUtil.fillString(getContext(), R.string.format_integral, userDetail.getIntegral()));
                Glide.with(getContext()).load(userDetail.getPic()).apply(new RequestOptions().transform(new GlideCircleTransform(getContext()))).into(imgUserPic);
            }
        }

    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();
    }
}
