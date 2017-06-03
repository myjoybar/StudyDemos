package com.example.rxjava2.service;

import com.example.rxjava2.data.PictureCategory;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by joybar on 2017/6/1.
 */

public interface Api {

	@GET(".")
	Observable<PictureCategory> getPictureCategoryInfo();

}
