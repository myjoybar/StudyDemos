package com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2;

import com.example.joybar.myaskunagjia.demo.Retrofit.Retrofit2.Model.ImageShowMode;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.Model2;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.Model3;
import com.example.joybar.myaskunagjia.demo.Retrofit.demoTest.ModelBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by joybar on 1/9/16.
 */
public interface GitHubClient {
    @GET("show")
    Call<ImageShowMode> getpicByID(
            @Query("id") int id
    );

    @GET("users/check/{login_name}")
    Call<ModelBean> setError(
            @Path("login_name") String login_name
    );

    @POST("users/login")
    Call<Model2> setLoginService(
            @Query("login_name") String login_name,
            @Query("password") String password,
            @Query("client") String client
    );

    @POST("users/tokenget")
    Call<Model3> melinkInfoService(
            @Query("login_name") String login_name,
            @Query("app_to") String app_to
    );
}