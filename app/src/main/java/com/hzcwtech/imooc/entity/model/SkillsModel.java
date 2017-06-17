package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/12.
 */

public class SkillsModel implements Serializable {
    private static final long serialVersionUID = -2992278815019879947L;


    /**
     * id : 7
     * name : HTML/CSS
     * numbers : 41
     * pic : http://img.mukewang.com/565ec2670001146402400240-240-240.jpg
     * typestate : 0
     */

    private int id;
    private String name;
    private int numbers;
    private String pic;
    private int typestate;

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

    public int getTypestate() {
        return typestate;
    }

    public void setTypestate(int typestate) {
        this.typestate = typestate;
    }
}
