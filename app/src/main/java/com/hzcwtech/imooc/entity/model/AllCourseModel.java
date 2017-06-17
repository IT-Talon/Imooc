package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/17.
 */

public class AllCourseModel implements Serializable {
    private static final long serialVersionUID = -5706825600415726494L;

    /**
     * categories : [{"haverelatepath":0,"id":"1","marking":"fe","name":"前端开发","relate_path_pic":"","skills":[{"id":7,"name":"HTML/CSS","numbers":41,"pic":"http://img.mukewang.com/565ec2670001146402400240-240-240.jpg","typestate":0},{"id":44,"name":"JavaScript","numbers":84,"pic":"http://img.mukewang.com/565ec2720001296402400240-240-240.jpg","typestate":0},{"id":219,"name":"jQuery","numbers":19,"pic":"http://img.mukewang.com/565ec27d0001738602400240-240-240.jpg","typestate":0},{"id":221,"name":"Html5","numbers":29,"pic":"http://img.mukewang.com/565ec24d00015d1702400240-240-240.jpg","typestate":0},{"id":222,"name":"Node.js","numbers":13,"pic":"http://img.mukewang.com/565ec25a0001094402400240-240-240.jpg","typestate":0},{"id":1123,"name":"AngularJS","numbers":8,"pic":"http://img.mukewang.com/565ec2220001272802400240-240-240.jpg","typestate":0},{"id":1260,"name":"WebApp","numbers":13,"pic":"http://img.mukewang.com/565ec1e10001860802400240-240-240.jpg","typestate":0},{"id":1261,"name":"前端工具","numbers":13,"pic":"http://img.mukewang.com/565ec1d4000103f702400240-240-240.jpg","typestate":0},{"id":1262,"name":"CSS3","numbers":26,"pic":"http://img.mukewang.com/565ec1c60001407602400240-240-240.jpg","typestate":0},{"id":1263,"name":"Bootstrap","numbers":5,"pic":"http://img.mukewang.com/565ec1ac0001f4ea02400240-240-240.jpg","typestate":0},{"id":1374,"name":"React.JS","numbers":5,"pic":"http://img.mukewang.com/579f2dbd000130cc02400240-240-240.jpg","typestate":0},{"id":1423,"name":"Vue.js","numbers":3,"pic":"http://img.mukewang.com/57b2d0d2000136e702400240-240-240.jpg","typestate":0},{"id":1429,"name":"Sass/Less","numbers":6,"pic":"http://img.mukewang.com/57b4492a0001223302400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"2","marking":"be","name":"后端开发","relate_path_pic":"","skills":[{"id":1,"name":"PHP","numbers":82,"pic":"http://img.mukewang.com/565ec1f00001739002400240-240-240.jpg","typestate":0},{"id":220,"name":"Java","numbers":91,"pic":"http://img.mukewang.com/565ec2370001bebd02400240-240-240.jpg","typestate":0},{"id":1118,"name":"Python","numbers":20,"pic":"http://img.mukewang.com/565ec218000165b502400240-240-240.jpg","typestate":0},{"id":1273,"name":"C","numbers":4,"pic":"http://img.mukewang.com/565ec1990001b8ad02400240-240-240.jpg","typestate":0},{"id":1331,"name":"C++","numbers":14,"pic":"http://img.mukewang.com/565ec1720001449902400240-240-240.jpg","typestate":0},{"id":1358,"name":"Go","numbers":4,"pic":"http://img.mukewang.com/565ec1570001251d02400240-240-240.jpg","typestate":0},{"id":1362,"name":"C#","numbers":3,"pic":"http://img.mukewang.com/565ec0e70001ae1702400240-240-240.jpg","typestate":0},{"id":3629,"name":"Ruby","numbers":1,"pic":"http://img.mukewang.com/58453e110001204302400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"3","marking":"mobile","name":"移动开发","relate_path_pic":"","skills":[{"id":223,"name":"Android","numbers":118,"pic":"http://img.mukewang.com/565ec1fe0001ab9f02400240-240-240.jpg","typestate":0},{"id":955,"name":"iOS","numbers":56,"pic":"http://img.mukewang.com/565ec22c000166b602400240-240-240.jpg","typestate":0},{"id":1281,"name":"Unity 3D","numbers":11,"pic":"http://img.mukewang.com/565ec18d0001422902400240-240-240.jpg","typestate":0},{"id":1361,"name":"Cocos2d-x","numbers":13,"pic":"http://img.mukewang.com/565ec0fa0001bafb02400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"4","marking":"data","name":"数据库","relate_path_pic":"","skills":[{"id":952,"name":"MySQL","numbers":10,"pic":"http://img.mukewang.com/565ec28c00016afc02400240-240-240.jpg","typestate":0},{"id":958,"name":"MongoDB","numbers":18,"pic":"http://img.mukewang.com/565ec2a400019b1502400240-240-240.jpg","typestate":0},{"id":1309,"name":"Oracle","numbers":8,"pic":"http://img.mukewang.com/565ec17f0001a9a302400240-240-240.jpg","typestate":0},{"id":1366,"name":"SQL Server","numbers":1,"pic":"http://img.mukewang.com/565ec0b700018f8902400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"6","marking":"cb","name":"云计算&大数据","relate_path_pic":"","skills":[{"id":1122,"name":"云计算","numbers":10,"pic":"http://img.mukewang.com/565ec2b00001dc0902400240-240-240.jpg","typestate":0},{"id":1359,"name":"大数据","numbers":18,"pic":"http://img.mukewang.com/565ec12d0001749b02400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"7","marking":"op","name":"运维&测试","relate_path_pic":"","skills":[{"id":468,"name":"Linux","numbers":29,"pic":"http://img.mukewang.com/565ec29900019ddd02400240-240-240.jpg","typestate":0},{"id":1422,"name":"测试","numbers":12,"pic":"http://img.mukewang.com/57aa954b0001397802400240-240-240.jpg","typestate":0}]},{"haverelatepath":0,"id":"5","marking":"photo","name":"UI设计","relate_path_pic":"","skills":[{"id":4732,"name":"设计基础","numbers":1,"pic":"http://img.mukewang.com/58af9ee40001269602400240-240-240.jpg","typestate":0},{"id":4733,"name":"设计工具","numbers":8,"pic":"http://img.mukewang.com/58af9f8b0001e56802400240-240-240.jpg","typestate":0},{"id":4734,"name":"APPUI设计","numbers":1,"pic":"http://img.mukewang.com/58afa039000194df02400240-240-240.jpg","typestate":0},{"id":4735,"name":"动画动效","numbers":6,"pic":"http://img.mukewang.com/58afa0d10001a39f02400240-240-240.jpg","typestate":0}]}]
     * id : 0
     * name : 全部课程
     * numbers : 725
     * pic : http://static.mukewang.com/static/img/all.png
     */

    private int id;
    private String name;
    private int numbers;
    private String pic;
    private java.util.List<MainCourseModel> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<MainCourseModel> getCategories() {
        return categories;
    }

    public void setCategories(List<MainCourseModel> categories) {
        this.categories = categories;
    }
}
