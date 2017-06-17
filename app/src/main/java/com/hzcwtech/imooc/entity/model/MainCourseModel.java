package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/17.
 */

public class MainCourseModel implements Serializable {
    private static final long serialVersionUID = -6874738332916043230L;


    /**
     * haverelatepath : 0
     * id : 1
     * marking : fe
     * name : 前端开发
     * relate_path_pic :
     * skills : [{"id":7,"name":"HTML/CSS","numbers":41,"pic":"http://img.mukewang.com/565ec2670001146402400240-240-240.jpg","typestate":0},{"id":44,"name":"JavaScript","numbers":84,"pic":"http://img.mukewang.com/565ec2720001296402400240-240-240.jpg","typestate":0},{"id":219,"name":"jQuery","numbers":19,"pic":"http://img.mukewang.com/565ec27d0001738602400240-240-240.jpg","typestate":0},{"id":221,"name":"Html5","numbers":29,"pic":"http://img.mukewang.com/565ec24d00015d1702400240-240-240.jpg","typestate":0},{"id":222,"name":"Node.js","numbers":13,"pic":"http://img.mukewang.com/565ec25a0001094402400240-240-240.jpg","typestate":0},{"id":1123,"name":"AngularJS","numbers":8,"pic":"http://img.mukewang.com/565ec2220001272802400240-240-240.jpg","typestate":0},{"id":1260,"name":"WebApp","numbers":13,"pic":"http://img.mukewang.com/565ec1e10001860802400240-240-240.jpg","typestate":0},{"id":1261,"name":"前端工具","numbers":13,"pic":"http://img.mukewang.com/565ec1d4000103f702400240-240-240.jpg","typestate":0},{"id":1262,"name":"CSS3","numbers":26,"pic":"http://img.mukewang.com/565ec1c60001407602400240-240-240.jpg","typestate":0},{"id":1263,"name":"Bootstrap","numbers":5,"pic":"http://img.mukewang.com/565ec1ac0001f4ea02400240-240-240.jpg","typestate":0},{"id":1374,"name":"React.JS","numbers":5,"pic":"http://img.mukewang.com/579f2dbd000130cc02400240-240-240.jpg","typestate":0},{"id":1423,"name":"Vue.js","numbers":3,"pic":"http://img.mukewang.com/57b2d0d2000136e702400240-240-240.jpg","typestate":0},{"id":1429,"name":"Sass/Less","numbers":6,"pic":"http://img.mukewang.com/57b4492a0001223302400240-240-240.jpg","typestate":0}]
     */

    private int haverelatepath;
    private String id;
    private String marking;
    private String name;
    private String relate_path_pic;
    private List<SkillsModel> skills;

    public int getHaverelatepath() {
        return haverelatepath;
    }

    public void setHaverelatepath(int haverelatepath) {
        this.haverelatepath = haverelatepath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelate_path_pic() {
        return relate_path_pic;
    }

    public void setRelate_path_pic(String relate_path_pic) {
        this.relate_path_pic = relate_path_pic;
    }

    public List<SkillsModel> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsModel> skills) {
        this.skills = skills;
    }

}
