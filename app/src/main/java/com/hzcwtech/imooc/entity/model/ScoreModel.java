package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/22.
 */

public class ScoreModel implements Serializable {
    private static final long serialVersionUID = 9147532306898954241L;

    /**
     * score : 10.0
     * title : 通俗易懂
     */

    private String score;
    private String title;
    private int id;
    private int sort;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getScore();
    }
}
