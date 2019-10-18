package com.shsy.tubebaby.network.api;


import java.util.List;

import io.reactivex.Observable;

import com.shsy.tubebaby.bean.UserBean;
import com.shsy.tubebaby.network.ResponseRetrofit;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */

public interface ApiService {


    /**
     * 登录 测试
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("tubebaby-web/user/login")
    Observable<JSONObject> login(@Body UserBean userBean);


    /**
     * 获取验证码
     * @return
     */
    @GET("tubebaby-web/user/send-code")
    Observable<JSONObject> sendCode(@Query("phoneNumber") String phoneNumber);
}
