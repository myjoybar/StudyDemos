package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by joybar on 3/9/16.
 */
public abstract class BaseServiceGenerator {


    protected abstract String getBaseUrl();
    //protected abstract Class getServiceClass();
    protected abstract OkHttpClient getOkHttpClient();
    protected Retrofit baseRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
