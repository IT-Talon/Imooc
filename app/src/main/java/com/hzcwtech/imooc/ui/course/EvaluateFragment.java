package com.hzcwtech.imooc.ui.course;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.adapter.EvaluateAdapter;
import com.hzcwtech.imooc.base.BaseFragment;
import com.hzcwtech.imooc.entity.HttpEntity;
import com.hzcwtech.imooc.entity.model.EvaluateModel;
import com.hzcwtech.imooc.entity.model.PlanScoreModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends BaseFragment {


    @BindView(R.id.recyclerView_evaluate)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private EvaluateAdapter mAdapter;
    private List<EvaluateModel> data;
    private PlanScoreModel planScoreModel;

    public static EvaluateFragment newInstance() {

        Bundle args = new Bundle();

        EvaluateFragment fragment = new EvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        data = new ArrayList<>();
        String json = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"comment_score\": \"9.00\",\n" +
                "      \"content\": \"什么时候出高级教程，现在只发现有入门和进阶，没有高级教程？希望高级教程尽快出来\",\n" +
                "      \"create_time\": \"23分钟前\",\n" +
                "      \"id\": \"730\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/5944b61f0001b97c03000300-100-100.jpg\",\n" +
                "      \"nickname\": \"慕桂英4215218\",\n" +
                "      \"reply\": \"在前端工程师修炼这条路上，有着不同的阶段，刚开始是入门，之后是进阶，最后是高级。但是入门阶段是非常重要的，入门阶段一定要好好学，把基础打好，后面的进阶和高级就会学得比较轻松。另外，高级的路径我们会尽快的推出，请耐心等候。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"5393020\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"9.00\",\n" +
                "      \"content\": \"案例丰富老师讲解通俗易懂，非常适合零基础的小伙伴入门。\",\n" +
                "      \"create_time\": \"19小时前\",\n" +
                "      \"id\": \"728\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/545845c40001996c02200220-100-100.jpg\",\n" +
                "      \"nickname\": \"慕前端1547467\",\n" +
                "      \"reply\": \"课程不仅要有知识点的讲解，搭配案例也是非常重要的，别忘了做课下的习题，会有一个大的提升。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"5350411\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"8.00\",\n" +
                "      \"content\": \"挺满意,比外面找的实在,一步一步学,思路也比较清晰,内容就不多说了,毕竟标题就写了,前端小白入门,小白入门,对于一个0知识的,可以学到很多知识,现在才看到CSS,后面的JS,JQ就不谈了,还有就是慕课网老催我评价- -..  趁着刚看完总结,就来评价一下吧.\",\n" +
                "      \"create_time\": \"1天前\",\n" +
                "      \"id\": \"724\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/592e633c000163d606400640-100-100.jpg\",\n" +
                "      \"nickname\": \"雨落青衫213\",\n" +
                "      \"reply\": \"您的肯定是对我们最大的鼓励，我们会一如既往的为大家提供优质的课程。面对小白用户最重要的就是要思路清晰，讲解详细，再加上多加联系，并且养成良好的学习习惯和学习方法。关于您说到的老师催你评价，我想你是误会了哦。我们没有老师去催评价的哦，是平台自带的提醒功能，只是提醒你可以评价了，这个也是想让你分享一下自己的学习心得，让其他的同学也有参考的依据。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"4907215\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"7.00\",\n" +
                "      \"content\": \"非常满意，老师每个细节都很到位，\",\n" +
                "      \"create_time\": \"1天前\",\n" +
                "      \"id\": \"722\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/545865710001107102200220-100-100.jpg\",\n" +
                "      \"nickname\": \"爱前端的小学生\",\n" +
                "      \"reply\": \"您的肯定是对我们最大的鼓励，我们会一如既往的为大家制作高品质的课程，在学习的过程中遇到问题可以到问答区进行提问，助教老师会为你答疑解惑。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"4237072\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"已经学到CSS中段了，老师讲得非常好；\\n条理清晰，层层递进，让我这个小白很有动力和信心！\\n继续加油~\",\n" +
                "      \"create_time\": \"1天前\",\n" +
                "      \"id\": \"721\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/54584c5e0001491102200220-100-100.jpg\",\n" +
                "      \"nickname\": \"夏木清水\",\n" +
                "      \"reply\": \"因为是面向的小白用户，所以要求老师讲解思路清晰，层层递进，而且还要有引导性，这样才能让同学们很好的入门，你的分析很好，看来是个思路清晰的好学生，加油哦！\",\n" +
                "      \"uid\": \"3146705\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"挺满意的，老师给我们讲的是一些重点内容，具体掌握当然是要靠我们自己了。毕竟只看视频，不看文档API提升会小。\\n嗯，对了，很喜欢美女老师的声音哦~~  嘻嘻\",\n" +
                "      \"create_time\": \"3天前\",\n" +
                "      \"id\": \"716\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/5454a0d90001a0d501000100-100-100.jpg\",\n" +
                "      \"nickname\": \"生活不止柴米油盐\",\n" +
                "      \"reply\": \"老师的引导在学习的过程中固然重要，但是自己的努力也是很重要。我们的路径中不光有视频的讲解，还有教辅分档、ppt和源码，更有课下习题和步骤作业题，这些内容一定要好好的利用起来，每个环节都有它的作用。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"490105\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"比较满意\",\n" +
                "      \"create_time\": \"3天前\",\n" +
                "      \"id\": \"714\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/533e4c1500010baf02200220-100-100.jpg\",\n" +
                "      \"nickname\": \"3dricks\",\n" +
                "      \"reply\": \"感谢对我们课程的支持好关注，我们会一如既往的做好课，做优质的课，有什么问题可以在我们的问答区进行提问，我们的助教会针对你的问题给与帮助和解答，祝学习愉快!\",\n" +
                "      \"uid\": \"5017362\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"讲得很仔细，我做的题目老师给我打满分也给了我很大的信心学下去，谢谢老师们的鼓励\",\n" +
                "      \"create_time\": \"4天前\",\n" +
                "      \"id\": \"713\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/54584c910001b8d902200220-100-100.jpg\",\n" +
                "      \"nickname\": \"萌新请多指教\",\n" +
                "      \"reply\": \"本专题是我们针对前端的小白用户精心设计的，课程从视频到练习再到作业评测，都是为了更好的巩固大家的学习，毕竟前端的知识点还是比较多比较零散的。值得一提的是，作业都是老师手工一对一进行批改的，从代码的运行到代码的规范，老师都会详细去看，指出你的问题和不足并给出建议。学习有高潮也会有低估，希望你能一直坚持下去，我们也会配着大家一起努力，一起进步。感谢对我们课程的支持和关注，祝学习愉快！\",\n" +
                "      \"uid\": \"4870943\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"课程没问题，讲解什么的都挺好。ppt也还好。小建议就是编程题加量。学的时候很明白，用少了，就记不清楚了\",\n" +
                "      \"create_time\": \"4天前\",\n" +
                "      \"id\": \"710\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/58183a2900010dfc01000100-100-100.jpg\",\n" +
                "      \"nickname\": \"墨遥4328677\",\n" +
                "      \"reply\": \"我们的课程是基础知识+案例的讲解，每个课程中都有练习题供大家练习巩固，还有ppt、教辅、源码资料辅助大家学习，每个步骤的最后还有作业题，有老师一对一的批改。作业题就是非常综合的代码练习，会让大家对前面的知识点做一个很好的综合。本路径总共有400多道练习题。大家在学习的过程当中有不会的问题，可以到问答区进行提问，会有助教和老师帮助你解答，你也可以到问答区帮忙其他同学解决问题。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"4328677\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"comment_score\": \"10.00\",\n" +
                "      \"content\": \"比免费课程要好，更通俗易懂。认真学的话，收获还是很大的。\",\n" +
                "      \"create_time\": \"4天前\",\n" +
                "      \"id\": \"709\",\n" +
                "      \"img\": \"http://img.mukewang.com/user/58d366df0001dd1602000200-100-100.jpg\",\n" +
                "      \"nickname\": \"小猪o\",\n" +
                "      \"reply\": \"我们的收费路径肯定要做的比免费课的更完善，更全面，就像你说的，收获会非常大。小白路径作为前端的入门路径，所以我们的课程设计团队会考虑到用户的基础，要求老师用适合小白用户的讲解方式来讲课，课程的设计是非常重要的环节。感谢您对慕课网的支持，祝学习愉快！\",\n" +
                "      \"uid\": \"4657345\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497853485446\n" +
                "}";
        HttpEntity httpEntity = JSON.parseObject(json, HttpEntity.class);
        if (httpEntity.isSuccess()) {
            data.clear();
            data.addAll(httpEntity.getDataObject(new TypeReference<List<EvaluateModel>>() {
            }));
        }

        String scoreJson = "\n" +
                "{\n" +
                "  \"data\": {\n" +
                "    \"comment_score\": \"9.97\",\n" +
                "    \"score\": [\n" +
                "      {\n" +
                "        \"score\": \"10.0\",\n" +
                "        \"title\": \"内容实用\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"score\": \"10.0\",\n" +
                "        \"title\": \"通俗易懂\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"score\": \"9.9\",\n" +
                "        \"title\": \"逻辑清晰\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"errorCode\": 1000,\n" +
                "  \"errorDesc\": \"成功\",\n" +
                "  \"status\": 1,\n" +
                "  \"timestamp\": 1497853485394\n" +
                "}";

        HttpEntity scoreEntity = JSON.parseObject(scoreJson, HttpEntity.class);
        if (scoreEntity.isSuccess()) {
            planScoreModel = scoreEntity.getDataObject(PlanScoreModel.class);
        }

    }

    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.evaluate_headview, null);
        ((TextView) header.findViewById(R.id.tv_score)).setText(planScoreModel.getComment_score() + "");
        ((TextView) header.findViewById(R.id.tv_evaluate_1)).setText(planScoreModel.getScore().get(0).toString());
        ((TextView) header.findViewById(R.id.tv_evaluate_2)).setText(planScoreModel.getScore().get(1).toString());
        ((TextView) header.findViewById(R.id.tv_evaluate_3)).setText(planScoreModel.getScore().get(2).toString());
        mAdapter = new EvaluateAdapter(data);
        mAdapter.addHeaderView(header);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
