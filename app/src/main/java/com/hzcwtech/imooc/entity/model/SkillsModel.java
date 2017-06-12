package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/12.
 */

public class SkillsModel implements Serializable {
    private static final long serialVersionUID = -2992278815019879947L;

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
