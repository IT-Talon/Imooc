package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/14.
 */

public class HomePicModel implements Serializable {
    private static final long serialVersionUID = -517893095933722853L;

    /**
     * pic : http://www.imooc.com/static/img/andriod/pic/path_day@1x.png
     * pic_night : http://www.imooc.com/static/img/andriod/pic/path_night@1x.png
     * type : 6
     */

    private String pic;
    private String pic_night;
    private int type;

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
}
