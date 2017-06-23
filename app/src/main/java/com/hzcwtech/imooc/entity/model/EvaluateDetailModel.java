package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/23.
 */

public class EvaluateDetailModel implements Serializable {
    private static final long serialVersionUID = -7080747652633678775L;

    /**
     * commentcount : 157
     * consult : 这是有时间限制的吗？
     * evaluate : [{"comment_score":"10.00","content":"很好的，感觉很适合入门，之前没有接触过前端，认真听完之后，基本的前端就可以做出来了。课程配了助教，提问之后助教老师都会及时的回复，而且感觉特别棒的地方是作业，学完一块之后就会有布置相应的作业，作业提交之后会有老师批改打分，批改的特别认真，在课程介绍里面有一个蓝绿色主题的页面，那个就是作业，在我提交的时候，有一个闭合标签忘记删除了，但是不影响整体外观，老师在批改的时候都给我指出来了。","create_time":"1495298523","img":"http://img.mukewang.com/user/5458634b0001120b02200220-100-100.jpg","nickname":"Danci_cc","uid":"3179941"},{"comment_sco4care":"10.00","content":"不听不知道，一听吓一跳，自己的基础真是差呀!本来第一次学的是html5与css3实现动态网页,但是中途发现自己基础薄弱，于是再次缴费这个课程，这次想好好的把基础打牢，这个课程很清晰，也很容易懂，特别适合小白入门，感觉还是非常满意的，继续努力加油!期待慕课网的js进阶课程尽快出来，然后学完找个好工作，就大功告成了!嘻嘻","create_time":"1494997348","img":"http://img.mukewang.com/user/591284b80001672606400640-100-100.jpg","nickname":"ZJDreaming","uid":"1990604"}]
     * score : 9.97
     * weidu : [{"id":1,"score":"10.0","sort":1,"title":"内容实用"},{"id":2,"score":"10.0","sort":2,"title":"通俗易懂"},{"id":3,"score":"9.9","sort":3,"title":"逻辑清晰"}]
     */

    private String commentcount;
    private String consult;
    private String score;
    private List<EvaluateSummaryModel> evaluate;
    private List<ScoreModel> weidu;

    public String getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(String commentcount) {
        this.commentcount = commentcount;
    }

    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<EvaluateSummaryModel> getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(List<EvaluateSummaryModel> evaluate) {
        this.evaluate = evaluate;
    }

    public List<ScoreModel> getWeidu() {
        return weidu;
    }

    public void setWeidu(List<ScoreModel> weidu) {
        this.weidu = weidu;
    }
}
