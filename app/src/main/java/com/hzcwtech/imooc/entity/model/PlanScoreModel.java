package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/22.
 */

public class PlanScoreModel implements Serializable {
    private static final long serialVersionUID = -4283102660459924165L;

    /**
     * comment_score : 9.97
     * score : [{"score":"10.0","title":"内容实用"},{"score":"10.0","title":"通俗易懂"},{"score":"9.9","title":"逻辑清晰"}]
     */

    private String comment_score;
    private List<ScoreModel> score;

    public String getComment_score() {
        return comment_score;
    }

    public void setComment_score(String comment_score) {
        this.comment_score = comment_score;
    }

    public List<ScoreModel> getScore() {
        return score;
    }

    public void setScore(List<ScoreModel> score) {
        this.score = score;
    }

}
