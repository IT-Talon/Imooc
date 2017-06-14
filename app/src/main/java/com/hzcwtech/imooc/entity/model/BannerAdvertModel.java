package com.hzcwtech.imooc.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Talon on 2017/6/14.
 */

public class BannerAdvertModel implements Serializable {
    private static final long serialVersionUID = -3096987079574763134L;

    private List<BannerModel> banner;
    private List<HomePicModel> pic;

    public List<BannerModel> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerModel> banner) {
        this.banner = banner;
    }

    public List<HomePicModel> getPic() {
        return pic;
    }

    public void setPic(List<HomePicModel> pic) {
        this.pic = pic;
    }

}
