package com.joy.apptemplate.testdemo;

import android.util.Log;

import com.joy.apptemplate.mvpbase.base.BasePresenter;

/**
 * Created by joybar on 2018/7/26.
 */

public class TestPresenter extends BasePresenter<TestView> {

	public void loadData() {
		String data = "Test";
		Log.d(TAG,"00000000");
		getView().showToast(data);
	}

}
