package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;

/**
 * Created by Talon on 2017/6/9.
 */

public class HomeTabModel implements Serializable {
    private static final long serialVersionUID = 8951704691237859196L;
    private String imgUrl;
    private String tabName;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
