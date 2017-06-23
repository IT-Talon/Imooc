package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/23.
 */

public class ClassInfoSummaryModel implements Serializable {
    private static final long serialVersionUID = 7217978858108579277L;

    /**
     * comment_score : 9.97
     * commentcount : 157
     * coursecount : 33
     * id : 20
     * is_buy : 0
     * name : 前端小白入门
     * numbers : 925
     * pic : http://climg.mukewang.com/59030cc50001144806000338-300-170.jpg
     * price : 499
     * share : http://class.imooc.com/sc/20
     * short_description : 从一个不会编程的小白到一个老司机是需要过程的，首先得入门，学习基础知识，然后才能进阶，最后再到精通，本专题是你走进前端世界的不二选择！
     * tip :
     * total_time : 52
     */

    private String comment_score;
    private String commentcount;
    private String coursecount;
    private String id;
    private int is_buy;
    private String name;
    private String numbers;
    private String pic;
    private int price;
    private String share;
    private String short_description;
    private String tip;
    private String total_time;

    public String getComment_score() {
        return comment_score;
    }

    public void setComment_score(String comment_score) {
        this.comment_score = comment_score;
    }

    public String getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(String commentcount) {
        this.commentcount = commentcount;
    }

    public String getCoursecount() {
        return coursecount;
    }

    public void setCoursecount(String coursecount) {
        this.coursecount = coursecount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }
}
