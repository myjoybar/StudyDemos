package com.example.rxjava2.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joybar on 2017/6/1.
 */

public class RetrofitProvider2 {
	private static final String ENDPOINT = "https://www.baidu.com/";



	public static Retrofit get() {

		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//		builder.readTimeout(10, TimeUnit.SECONDS);
//		builder.connectTimeout(9, TimeUnit.SECONDS);
		builder.addInterceptor(loggingInterceptor);



		return new Retrofit.Builder().baseUrl(ENDPOINT)

				.client(builder.build())
				.addConverterFactory(GsonConverterFactory.create())
				//.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}
}
