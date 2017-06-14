package com.hzcwtech.imooc.app.http;

import com.hzcwtech.imooc.entity.HttpEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Talon on 2017/6/14.
 */

public interface HttpApi {
    @GET(HttpPath.HOME_COURSE)
    Call<HttpEntity> getHomeCourse();

}
