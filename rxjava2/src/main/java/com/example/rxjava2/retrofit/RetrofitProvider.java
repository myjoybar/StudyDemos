package com.example.rxjava2.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joybar on 2017/6/1.
 */

public class RetrofitProvider {
	private static final String ENDPOINT = "http://www.tngou.net/tnfs/api/";

	public static Retrofit get() {
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
		builder.readTimeout(10, TimeUnit.SECONDS);
		builder.connectTimeout(9, TimeUnit.SECONDS);

		return new Retrofit.Builder().baseUrl(ENDPOINT)
				.client(builder.build())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}
}
