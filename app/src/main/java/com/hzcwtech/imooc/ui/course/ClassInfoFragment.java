package com.hzcwtech.imooc.ui.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.EvaluateSummaryAdapter;
import com.hzcwtech.imooc.adapter.StepAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.ClassInfoDetailModel;
import com.hzcwtech.imooc.entity.model.ClassInfoSummaryModel;
import com.hzcwtech.imooc.entity.model.EvaluateDetailModel;
import com.hzcwtech.imooc.entity.model.StepModel;
import com.hzcwtech.imooc.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassInfoFragment extends BaseFragment {

    @BindView(R.id.recyclerView_class_evaluate)
    RecyclerView recyclerViewClassEvaluate;
    @BindView(R.id.tv_consult)
    TextView tvConsult;
    @BindView(R.id.rec_classInfo_step)
    RecyclerView recClassInfoStep;
    Unbinder unbinder;
    @BindView(R.id.tv_class_courseCount)
    TextView tvClassCourseCount;
    @BindView(R.id.tv_class_total_time)
    TextView tvClassTotalTime;
    @BindView(R.id.tv_all_course)
    TextView tvAallCourse;
    @BindView(R.id.tv_class_number)
    TextView tvClassNumber;
    @BindView(R.id.tv_check_all_course)
    TextView tvCheckAllCourse;
    @BindView(R.id.img_pic_1)
    ImageView imgPic1;
    @BindView(R.id.img_pic_2)
    ImageView imgPic2;
    @BindView(R.id.img_pic_3)
    ImageView imgPic3;
    private ClassInfoDetailModel infoDetail;
    private EvaluateDetailModel evaluate;
    private ClassInfoSummaryModel info;
    private List<String> pic_info;
    private List<StepModel> steplist;
    private EvaluateSummaryAdapter evaluateAdapter;
    private StepAdapter stepAdapter;


    public static ClassInfoFragment newInstance() {

        Bundle args = new Bundle();

        ClassInfoFragment fragment = new ClassInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
//        evaluate = infoDetail.getEvaluate();
//        info = infoDetail.getInfo();
//        pic_info = infoDetail.getPic_info();
//        steplist = infoDetail.getSteplist();
        initInfoView();
        initEvaluateView();
        initStepView();
        initPicView();

    }

    private void initPicView() {
        Glide.with(getContext()).load(pic_info.get(0)).into(imgPic1);
        Glide.with(getContext()).load(pic_info.get(1)).into(imgPic2);
        Glide.with(getContext()).load(pic_info.get(2)).into(imgPic3);
    }

    private void initStepView() {
        stepAdapter = new StepAdapter(new ArrayList<StepModel>());
        if (steplist.get(0).getCourselist().size() > 3) {
            List<StepModel> stepData = new ArrayList<>();
            StepModel model = steplist.get(0);
            model.setCourselist(model.getCourselist().subList(0, 3));
            stepData.add(model);
            stepAdapter.setNewData(stepData);
        } else {
            stepAdapter.setNewData(steplist);
        }

        recClassInfoStep.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recClassInfoStep.setAdapter(stepAdapter);
    }

    private void initEvaluateView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.evaluate_headview, null);
        ((TextView) header.findViewById(R.id.tv_score)).setText(evaluate.getScore() + "");
        ((TextView) header.findViewById(R.id.tv_evaluate_1)).setText(evaluate.getWeidu().get(0).toString());
        ((TextView) header.findViewById(R.id.tv_evaluate_2)).setText(evaluate.getWeidu().get(1).toString());
        ((TextView) header.findViewById(R.id.tv_evaluate_3)).setText(evaluate.getWeidu().get(2).toString());
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.evaluate_footerview, null);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(1);
            }
        });
        ((TextView) footer.findViewById(R.id.tv_all_evaluate)).setText(CommonUtil.fillString(getContext(), R.string.format_check_all_evaluate, evaluate.getCommentcount()));
        evaluateAdapter = new EvaluateSummaryAdapter(evaluate.getEvaluate());
        evaluateAdapter.addHeaderView(header);
        evaluateAdapter.addFooterView(footer);
        recyclerViewClassEvaluate.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerViewClassEvaluate.setAdapter(evaluateAdapter);
    }

    private void initInfoView() {

        tvClassCourseCount.setText(Html.fromHtml("<big>" + info.getCoursecount() + "</big> 门"));
        tvClassTotalTime.setText(Html.fromHtml("<big>" + info.getTotal_time() + "</big> h"));
        tvClassNumber.setText(info.getNumbers());
        tvAallCourse.setText("全部课程（" + info.getCoursecount() + "门）");
        tvConsult.setText(evaluate.getConsult());
        tvConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });
        if (steplist.get(0).getCourselist().size() > 3) {
            tvCheckAllCourse.setVisibility(View.VISIBLE);
        } else {
            tvCheckAllCourse.setVisibility(View.GONE);
        }
        tvCheckAllCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepAdapter.setNewData(steplist);
                tvCheckAllCourse.setVisibility(View.GONE);
            }
        });

    }

    private void initData() {
        String json = "{\n" +
                "  \"data\": {\n" +
                "    \"evaluate\": {\n" +
                "      \"commentcount\": \"157\",\n" +
                "      \"consult\": \"这是有时间限制的吗？\",\n" +
                "      \"evaluate\": [\n" +
                "        {\n" +
                "          \"comment_score\": \"10.00\",\n" +
                "          \"content\": \"很好的，感觉很适合入门，之前没有接触过前端，认真听完之后，基本的前端就可以做出来了。课程配了助教，提问之后助教老师都会及时的回复，而且感觉特别棒的地方是作业，学完一块之后就会有布置相应的作业，作业提交之后会有老师批改打分，批改的特别认真，在课程介绍里面有一个蓝绿色主题的页面，那个就是作业，在我提交的时候，有一个闭合标签忘记删除了，但是不影响整体外观，老师在批改的时候都给我指出来了。\",\n" +
                "          \"create_time\": \"1495298523\",\n" +
                "          \"img\": \"http://img.mukewang.com/user/5458634b0001120b02200220-100-100.jpg\",\n" +
                "          \"nickname\": \"Danci_cc\",\n" +
                "          \"uid\": \"3179941\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"comment_score\": \"10.00\",\n" +
                "          \"content\": \"不听不知道，一听吓一跳，自己的基础真是差呀!本来第一次学的是html5与css3实现动态网页,但是中途发现自己基础薄弱，于是再次缴费这个课程，这次想好好的把基础打牢，这个课程很清晰，也很容易懂，特别适合小白入门，感觉还是非常满意的，继续努力加油!期待慕课网的js进阶课程尽快出来，然后学完找个好工作，就大功告成了!嘻嘻\",\n" +
                "          \"create_time\": \"1494997348\",\n" +
                "          \"img\": \"http://img.mukewang.com/user/591284b80001672606400640-100-100.jpg\",\n" +
                "          \"nickname\": \"ZJDreaming\",\n" +
                "          \"uid\": \"1990604\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"score\": \"9.97\",\n" +
                "      \"weidu\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"score\": \"10.0\",\n" +
                "          \"sort\": 1,\n" +
                "          \"title\": \"内容实用\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"score\": \"10.0\",\n" +
                "          \"sort\": 2,\n" +
                "          \"title\": \"通俗易懂\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"score\": \"9.9\",\n" +
                "          \"sort\": 3,\n" +
                "          \"title\": \"逻辑清晰\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"info\": {\n" +
                "      \"comment_score\": \"9.97\",\n" +
                "      \"commentcount\": \"157\",\n" +
                "      \"coursecount\": \"33\",\n" +
                "      \"id\": \"20\",\n" +
                "      \"is_buy\": 0,\n" +
                "      \"name\": \"前端小白入门\",\n" +
                "      \"numbers\": \"925\",\n" +
                "      \"pic\": \"http://climg.mukewang.com/59030cc50001144806000338-300-170.jpg\",\n" +
                "      \"price\": 499,\n" +
                "      \"share\": \"http://class.imooc.com/sc/20\",\n" +
                "      \"short_description\": \"从一个不会编程的小白到一个老司机是需要过程的，首先得入门，学习基础知识，然后才能进阶，最后再到精通，本专题是你走进前端世界的不二选择！\",\n" +
                "      \"tip\": \"\",\n" +
                "      \"total_time\": \"52\"\n" +
                "    },\n" +
                "    \"pic_info\": [\n" +
                "      \"http://climg.mukewang.com/59030e9d000112e607501204.jpg\",\n" +
                "      \"http://climg.mukewang.com/59030ed300018b5007501012.jpg\",\n" +
                "      \"http://climg.mukewang.com/58bfb3f20001f06607502615.jpg\"\n" +
                "    ],\n" +
                "    \"steplist\": [\n" +
                "      {\n" +
                "        \"courselist\": [\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时59分\",\n" +
                "            \"name\": \"HTML基础\",\n" +
                "            \"short_description\": \"HTML是网页制作必备技能，在本课程主要介绍HTML概念、语法及常用基础标签\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"191\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时26分\",\n" +
                "            \"name\": \"HTML表格\",\n" +
                "            \"short_description\": \"表格在网页中用于数据和排版，本课程介绍表格概念、语法、操作，并通过案例掌握知识。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"194\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时37分\",\n" +
                "            \"name\": \"HTML表单\",\n" +
                "            \"short_description\": \"表单用于收集用户信息，本课程介绍表单概念、语法及创建表单，并通过案例掌握知识。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"193\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时47分\",\n" +
                "            \"name\": \"案例搭建网页HTML结构\",\n" +
                "            \"short_description\": \"本课程带领大家一起搭建一个网页HTML结构，并掌握网布局相应知识与技巧。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"173\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"测评题\",\n" +
                "            \"exam_frequency\": 2,\n" +
                "            \"exam_num\": \"20\",\n" +
                "            \"name\": \"HTML基础测试\",\n" +
                "            \"paper_time\": \"30\",\n" +
                "            \"short_description\": \"HTML基础测试\",\n" +
                "            \"title\": \"HTML基础测试\",\n" +
                "            \"type\": \"2\",\n" +
                "            \"type_id\": \"41\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"HTML基础\",\n" +
                "        \"seqid\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"courselist\": [\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 2小时27分\",\n" +
                "            \"name\": \"CSS选择的艺术\",\n" +
                "            \"short_description\": \"本课程主要介绍CSS语法、选择器、继承、层叠及优先级基础内容。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"184\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时38分\",\n" +
                "            \"name\": \"CSS文本样式\",\n" +
                "            \"short_description\": \"CSS为字体、文本提供了大量的样式属性，可以使我们的页面式更加丰富的多彩。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"185\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时1分\",\n" +
                "            \"name\": \"CSS背景和列表\",\n" +
                "            \"short_description\": \"本课程带领大家了解一下背景颜色、背景图片的相关知识，以及列表的多种样式。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"187\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时34分\",\n" +
                "            \"name\": \"盒子模型\",\n" +
                "            \"short_description\": \"我们就来学习一下css的盒子模型，理解了盒子模型，才能更好的排版，进行页面布局。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"188\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时36分\",\n" +
                "            \"name\": \"float浮动\",\n" +
                "            \"short_description\": \"本课程主要介绍了浮动的作用，带领大家用更好的方式来布局页面。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"189\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时20分\",\n" +
                "            \"name\": \"CSS定位（position）\",\n" +
                "            \"short_description\": \"本课程，将带领大家了解一下定位的知识，教大家如何通过定位来进行布局。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"190\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时57分\",\n" +
                "            \"name\": \"CSS网页布局基础\",\n" +
                "            \"short_description\": \"本课程，我们我们就针对CSS中的基础布局以及经典的布局展开详细的讲解。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"192\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时51分\",\n" +
                "            \"name\": \"网页布局案例\",\n" +
                "            \"short_description\": \"本课程通过实战的讲解，带领大家体验真实开发中的的布局样式。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"195\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"测评题\",\n" +
                "            \"exam_frequency\": 2,\n" +
                "            \"exam_num\": \"20\",\n" +
                "            \"name\": \"CSS基础测试\",\n" +
                "            \"paper_time\": \"30\",\n" +
                "            \"short_description\": \"CSS基础测试\",\n" +
                "            \"title\": \"CSS基础测试\",\n" +
                "            \"type\": \"2\",\n" +
                "            \"type_id\": \"48\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"CSS基础\",\n" +
                "        \"seqid\": \"2\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"courselist\": [\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时46分\",\n" +
                "            \"name\": \"JavaScript语法\",\n" +
                "            \"short_description\": \"初步了解JavaScript语言，掌握它的语法、数据类型、基本算数和逻辑运算操作\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"206\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时24分\",\n" +
                "            \"name\": \"JavaScript流程控制语句\",\n" +
                "            \"short_description\": \"掌握JavaScript中条件分支语句和循环语句的使用，用简洁的代码实现强大功能\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"203\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时36分\",\n" +
                "            \"name\": \"JavaScript函数\",\n" +
                "            \"short_description\": \"掌握函数的使用，学习函数的封装，体会代码复用的过程和它带来的便利\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"204\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 2小时48分\",\n" +
                "            \"name\": \"JavaScript内置对象\",\n" +
                "            \"short_description\": \"学习内置对象的常用属性和方法，方便我们开发中直接调用，进而实现更多功能\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"205\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时46分\",\n" +
                "            \"name\": \"JavaScript DOM基础\",\n" +
                "            \"short_description\": \"DOM的方法和属性既可以获取网页中的元素，也可以设置元素的内容、样式及效果\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"211\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时34分\",\n" +
                "            \"name\": \"JavaScript DOM事件\",\n" +
                "            \"short_description\": \"为页面中的元素绑定键盘或鼠标事件，从而可以触发和实现我们想要的交互效果\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"202\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时40分\",\n" +
                "            \"name\": \"JavaScript BOM基础\",\n" +
                "            \"short_description\": \"学习浏览器对象模型“BOM”，可以对浏览器窗口进行访问和操作，与浏览器“对话”\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"207\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 2小时40分\",\n" +
                "            \"name\": \"JavaScript实现轮播特效\",\n" +
                "            \"short_description\": \"综合运用JavaScript知识，做出轮播图、tab页切换等实用特效\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"200\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"测评题\",\n" +
                "            \"exam_frequency\": 2,\n" +
                "            \"exam_num\": \"25\",\n" +
                "            \"name\": \"JavaScript基础测试\",\n" +
                "            \"paper_time\": \"37\",\n" +
                "            \"short_description\": \"JavaScript基础测试\",\n" +
                "            \"title\": \"JavaScript基础测试\",\n" +
                "            \"type\": \"2\",\n" +
                "            \"type_id\": \"45\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"JavaScript基础\",\n" +
                "        \"seqid\": \"3\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"courselist\": [\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时43分\",\n" +
                "            \"name\": \"jQuery选择的艺术\",\n" +
                "            \"short_description\": \"课程主要介绍jQuery的概念、版本、选择器，筛选器、以及与js的区别。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"174\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 3小时11分\",\n" +
                "            \"name\": \"jQuery DOM操作\",\n" +
                "            \"short_description\": \"本课程主要讲解jQuery中查找元素、修改元素样式和内容以及其它操作方法。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"182\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时3分\",\n" +
                "            \"name\": \"jQuery事件\",\n" +
                "            \"short_description\": \"本课程主要介绍了jQuery对象中的0级事件、2级事件，以及事件的添加、移除、触发、自定义事件、命名空间。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"183\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时54分\",\n" +
                "            \"name\": \"jQuery插件\",\n" +
                "            \"short_description\": \"本课程主要介绍了如何寻找自己需要的插件，如何使用插件，几个常用插件的介绍、以及如何自定义插件。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"196\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时43分\",\n" +
                "            \"name\": \"jQuery弹出层案例\",\n" +
                "            \"short_description\": \"通过弹出层案例的讲解，让大家更灵活的使用jQuery的属性和方法以及函数的封装。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"198\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 1小时47分\",\n" +
                "            \"name\": \"瀑布流布局案例\",\n" +
                "            \"short_description\": \"瀑布流布局，是目前比较流行的一种网页布局方式，它会随着页面滚动条的滚动而不断加载内容。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"199\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"测评题\",\n" +
                "            \"exam_frequency\": 2,\n" +
                "            \"exam_num\": \"20\",\n" +
                "            \"name\": \"jQuery基础测试\",\n" +
                "            \"paper_time\": \"30\",\n" +
                "            \"short_description\": \"jQuery基础测试\",\n" +
                "            \"title\": \"jQuery基础测试\",\n" +
                "            \"type\": \"2\",\n" +
                "            \"type_id\": \"44\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"jQuery基础\",\n" +
                "        \"seqid\": \"4\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"courselist\": [\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 0小时39分\",\n" +
                "            \"name\": \"Photoshop辅助工具\",\n" +
                "            \"short_description\": \"Photoshop是图像处理的软件，本课程主要介绍了它是如何对图形图像进行编辑和色彩处理的。\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"186\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"课程时长： 4小时11分\",\n" +
                "            \"name\": \"统一挂号平台案例\",\n" +
                "            \"short_description\": \"结合所学的HTML、CSS、jQuery知识，完成整站开发\",\n" +
                "            \"type\": \"1\",\n" +
                "            \"type_id\": \"197\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"course_time\": \"测评题\",\n" +
                "            \"exam_frequency\": 2,\n" +
                "            \"exam_num\": \"20\",\n" +
                "            \"name\": \"实战测试\",\n" +
                "            \"paper_time\": \"30\",\n" +
                "            \"short_description\": \"实战测试\",\n" +
                "            \"title\": \"实战测试\",\n" +
                "            \"type\": \"2\",\n" +
                "            \"type_id\": \"47\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"实战案例\",\n" +
                "        \"seqid\": \"5\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497853485778\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(json, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            infoDetail = httpEntity.getDataObject(ClassInfoDetailModel.class);
            evaluate = infoDetail.getEvaluate();
            info = infoDetail.getInfo();
            pic_info = infoDetail.getPic_info();
            steplist = infoDetail.getSteplist();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
