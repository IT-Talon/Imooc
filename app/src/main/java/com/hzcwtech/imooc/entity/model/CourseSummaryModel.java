package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/14.
 */

public class CourseSummaryModel implements Serializable {
    private static final long serialVersionUID = -4086596180851549002L;

    /**
     * course : [{"bgcolor_end":"#b3ff739b","bgcolor_start":"#ffff739b","course_type":1,"id":"847","is_learn":0,"is_learned":0,"level":"中级","name":"使用java构建和维护接口自动化测试框架","numbers":"40","pic":"http://www.imooc.com/courseimg/s/cover043_s.jpg","share":"http://www.imooc.com/learn/847","short_description":"初识接口自动化框架","skills":[{"id":"220","name":"Java"},{"id":"1422","name":"测试"}],"type":1},{"bgcolor_end":"#b3d273e6","bgcolor_start":"#ffd273e6","course_type":1,"id":"838","is_learn":0,"is_learned":0,"level":"初级","name":"UI框架foundation6入门（下）","numbers":"379","pic":"http://www.imooc.com/courseimg/s/cover048_s.jpg","share":"http://www.imooc.com/learn/838","short_description":"深入浅出的带你学习Foundation 6框架，结合代码进行讲解。","skills":[{"id":"44","name":"JavaScript"}],"type":1},{"bgcolor_end":"#b300b95a","bgcolor_start":"#ff00b95a","course_type":1,"id":"844","is_learn":0,"is_learned":0,"level":"初级","name":"带你开发类似Pokemon Go的AR游戏","numbers":"1071","pic":"http://www.imooc.com/courseimg/s/cover050_s.jpg","share":"http://www.imooc.com/learn/844","short_description":"用 Unity 3D开发AR游戏，分有卡和脱卡两种情况，并导出Android和iOS平台","skills":[{"id":"1281","name":"Unity 3D"},{"id":"223","name":"Android"},{"id":"955","name":"iOS"}],"type":1},{"bgcolor_end":"#b300b95a","bgcolor_start":"#ff00b95a","course_type":1,"id":"775","is_learn":1,"is_learned":1,"level":"中级","name":"自定义实现日历控件","numbers":"1593","pic":"http://www.imooc.com/courseimg/s/cover013_s.jpg","share":"http://www.imooc.com/learn/775","short_description":"轻松掌握Android 自定义控件实现","skills":[{"id":"223","name":"Android"}],"type":1}]
     * list_type : 1
     * name : 课程推荐
     * operate_type : 1
     * pic : http://www.imooc.com/static/img/andriod/icon/recommend@3x.png
     * pic_night : http://www.imooc.com/static/img/andriod/icon/recommend_night@3x.png
     * type : 1
     */

    private int list_type;
    private String name;
    private int operate_type;
    private String pic;
    private String pic_night;
    private int type;
    private List<CourseDetailModel> course;
    private int type_id;
    private String short_description;
    private String course_time;
    private int exam_frequency;
    private int exam_num;
    private int paper_time;
    private String title;

    public int getList_type() {
        return list_type;
    }

    public void setList_type(int list_type) {
        this.list_type = list_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(int operate_type) {
        this.operate_type = operate_type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic_night() {
        return pic_night;
    }

    public void setPic_night(String pic_night) {
        this.pic_night = pic_night;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<CourseDetailModel> getCourse() {
        return course;
    }

    public void setCourse(List<CourseDetailModel> course) {
        this.course = course;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public int getExam_frequency() {
        return exam_frequency;
    }

    public void setExam_frequency(int exam_frequency) {
        this.exam_frequency = exam_frequency;
    }

    public int getExam_num() {
        return exam_num;
    }

    public void setExam_num(int exam_num) {
        this.exam_num = exam_num;
    }

    public int getPaper_time() {
        return paper_time;
    }

    public void setPaper_time(int paper_time) {
        this.paper_time = paper_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
