package com.example.rxjava2.service;

import com.example.rxjava2.data.PictureCategoryList;
import com.example.rxjava2.data.PictureDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joybar on 2017/6/1.
 */

public interface Api {

	@GET("list/")
	Observable<PictureCategoryList> getPictureCategoryList();
	@GET("show/")
	Observable<PictureDetail> getPictureDetail(@Query("id") int id);

}
