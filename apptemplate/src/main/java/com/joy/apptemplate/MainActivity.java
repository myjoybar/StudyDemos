package com.joy.apptemplate;

import android.os.Bundle;

import com.joy.apptemplate.mvpbase.base.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initPresenter() {

	}
}
