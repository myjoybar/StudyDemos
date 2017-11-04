package com.example.joybar.myaskunagjia.demo.Retrofit.demoTest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joybar on 1/9/16.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://ky.melink.cn/server/";

    private Retrofit retrofit;
    // Define the interceptor, add authentication headers
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder().addHeader("token", " oY/aqc46rEuBgnSyvcj8SFhuZLxHgvJkL7Y+acfSQjr7BrKrYRtawJ1te1DEOFh6aORBOr+ezWX3b3/d/AiwtoB45GF1BCiygNnhy9bFxJoDsrK8bnr744Z+qVJ1zeO7tu4phiR897vNHV2rO3HCVnLw1HScXs4KThpHbRJIJsAtAJ0pzQzUaJsuJWtBJCQVeyHGldSe23l9hj85+pmReg==").build();
            return chain.proceed(newRequest);
        }
    };


    private OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

  // public  OkHttpClient httpClient = new OkHttpClient();


   // httpClient.interceptors().add(interceptor);


    public <S> S createService(Class<S> serviceClass) {
        init();
        return retrofit.create(serviceClass);
    }

    public void initOkHeader(){
      //  httpClient.Builder();

    }


    private void init() {
        if (null == retrofit) {
            retrofit =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
        }
    }

}
