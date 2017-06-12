package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/12.
 */

public class CourseModel implements Serializable {
    private static final long serialVersionUID = -1692352206457970198L;

    /**
     * bgcolor_end : #b3ff739b
     * bgcolor_start : #ffff739b
     * course_type : 1
     * id : 847
     * is_learn : 0
     * is_learned : 0
     * level : 中级
     * name : 使用java构建和维护接口自动化测试框架
     * numbers : 40
     * pic : http://www.imooc.com/courseimg/s/cover043_s.jpg
     * share : http://www.imooc.com/learn/847
     * short_description : 初识接口自动化框架
     * skills : [{"id":"220","name":"Java"},{"id":"1422","name":"测试"}]
     * type : 1
     */

    private String bgcolor_end;
    private String bgcolor_start;
    private int course_type;
    private String id;
    private int is_learn;
    private int is_learned;
    private String level;
    private String name;
    private String numbers;
    private String pic;
    private String share;
    private String short_description;
    private int type;
    private List<SkillsModel> skills;

    public String getBgcolor_end() {
        return bgcolor_end;
    }

    public void setBgcolor_end(String bgcolor_end) {
        this.bgcolor_end = bgcolor_end;
    }

    public String getBgcolor_start() {
        return bgcolor_start;
    }

    public void setBgcolor_start(String bgcolor_start) {
        this.bgcolor_start = bgcolor_start;
    }

    public int getCourse_type() {
        return course_type;
    }

    public void setCourse_type(int course_type) {
        this.course_type = course_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIs_learn() {
        return is_learn;
    }

    public void setIs_learn(int is_learn) {
        this.is_learn = is_learn;
    }

    public int getIs_learned() {
        return is_learned;
    }

    public void setIs_learned(int is_learned) {
        this.is_learned = is_learned;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SkillsModel> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsModel> skills) {
        this.skills = skills;
    }

    public static class SkillsBean {
        /**
         * id : 220
         * name : Java
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
