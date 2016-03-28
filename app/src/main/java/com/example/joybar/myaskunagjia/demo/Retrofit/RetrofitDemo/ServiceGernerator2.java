package com.example.joybar.myaskunagjia.demo.Retrofit.RetrofitDemo;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by joybar on 3/9/16.
 */
public class ServiceGernerator2 extends  BaseServiceGenerator {

    private static final String BASE_REST_URL = "http://www.tngou.net/tnfs/api/";
    @Override
    protected String getBaseUrl() {
        return BASE_REST_URL;
    }



    @Override
    protected OkHttpClient getOkHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("token", " oY/aqc46rEuBgnSyvcj8SFhuZLxHgvJkL7Y+acfSQjr7BrKrYRtawJ1te1DEOFh6aORBOr+ezWX3b3/d/AiwtoB45GF1BCiygNnhy9bFxJoDsrK8bnr744Z+qVJ1zeO7tu4phiR897vNHV2rO3HCVnLw1HScXs4KThpHbRJIJsAtAJ0pzQzUaJsuJWtBJCQVeyHGldSe23l9hj85+pmReg==").build();
                return chain.proceed(newRequest);
            }
        };

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        MyHttpLoggingInterceptor httpLoggingInterceptor = new MyHttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(MyHttpLoggingInterceptor.Level.BODY);


          OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(httpLoggingInterceptor).build();
          return  httpClient;

    }

    public RetrofitServiceApi createRestApi() {
        return baseRetrofit().create(RetrofitServiceApi.class);
    }

//    public Class<T> createRestApi(Class<T> service) {
//        return baseRetrofit().create(service.getClass());
//    }

}
