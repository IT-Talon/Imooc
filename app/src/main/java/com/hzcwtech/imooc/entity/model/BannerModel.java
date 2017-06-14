package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/14.
 */

public class BannerModel implements Serializable {

    private static final long serialVersionUID = 8055676461099716575L;
    /**
     * id : 865
     * links : http://coding.imooc.com/class/96.html
     * name : JAVA企业级
     * pic : http://img.mukewang.com/592e9c01000181cd07500250.jpg
     * type : 99
     * type_id : 0
     */

    private int id;
    private String links;
    private String name;
    private String pic;
    private int type;
    private int type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
