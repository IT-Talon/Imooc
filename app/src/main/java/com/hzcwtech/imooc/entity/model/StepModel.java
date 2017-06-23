package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/23.
 */

public class StepModel implements Serializable {
    private static final long serialVersionUID = 1530489401425812885L;

    /**
     * courselist : [{"course_time":"课程时长： 1小时59分","name":"HTML基础","short_description":"HTML是网页制作必备技能，在本课程主要介绍HTML概念、语法及常用基础标签","type":"1","type_id":"191"},{"course_time":"课程时长： 1小时26分","name":"HTML表格","short_description":"表格在网页中用于数据和排版，本课程介绍表格概念、语法、操作，并通过案例掌握知识。","type":"1","type_id":"194"},{"course_time":"课程时长： 1小时37分","name":"HTML表单","short_description":"表单用于收集用户信息，本课程介绍表单概念、语法及创建表单，并通过案例掌握知识。","type":"1","type_id":"193"},{"course_time":"课程时长： 0小时47分","name":"案例搭建网页HTML结构","short_description":"本课程带领大家一起搭建一个网页HTML结构，并掌握网布局相应知识与技巧。","type":"1","type_id":"173"},{"course_time":"测评题","exam_frequency":2,"exam_num":"20","name":"HTML基础测试","paper_time":"30","short_description":"HTML基础测试","title":"HTML基础测试","type":"2","type_id":"41"}]
     * name : HTML基础
     * seqid : 1
     */

    private String name;
    private String seqid;
    private List<CourseSummaryModel> courselist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public List<CourseSummaryModel> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<CourseSummaryModel> courselist) {
        this.courselist = courselist;
    }

}
