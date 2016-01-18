package com.example.joybar.myaskunagjia.demo.Retrofit.API;

import com.example.joybar.myaskunagjia.demo.Retrofit.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by joybar on 1/8/16.
 */

public interface GitHubService {
    @GET("/users/{username}")
    Call<User> getUser(
            @Path("username") String username
    );
}
