package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by joybar on 3/9/16.
 */
public interface RetrofitServiceApi {

    @GET("news")
    Call<Model> setGetId(
            @Query("id") int id
    );
    @POST("news")
    Call<Model> setPostId(
            @Query("id") int id
    );
}
