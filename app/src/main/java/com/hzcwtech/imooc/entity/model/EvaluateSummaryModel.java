package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/22.
 */

public class EvaluateSummaryModel implements Serializable {
    private static final long serialVersionUID = -145258686068383055L;

    /**
     * comment_score : 10.00
     * content : 什么时候出高级教程，现在只发现有入门和进阶，没有高级教程？希望高级教程尽快出来
     * create_time : 23分钟前
     * id : 730
     * img : http://img.mukewang.com/user/5944b61f0001b97c03000300-100-100.jpg
     * nickname : 慕桂英4215218
     * reply : 在前端工程师修炼这条路上，有着不同的阶段，刚开始是入门，之后是进阶，最后是高级。但是入门阶段是非常重要的，入门阶段一定要好好学，把基础打好，后面的进阶和高级就会学得比较轻松。另外，高级的路径我们会尽快的推出，请耐心等候。感谢您对慕课网的支持，祝学习愉快！
     * uid : 5393020
     */

    private String comment_score;
    private String content;
    private String create_time;
    private String id;
    private String img;
    private String nickname;
    private String reply;
    private String uid;

    public String getComment_score() {
        return comment_score;
    }

    public void setComment_score(String comment_score) {
        this.comment_score = comment_score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
