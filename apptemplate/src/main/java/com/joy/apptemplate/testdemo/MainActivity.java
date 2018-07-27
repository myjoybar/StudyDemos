package com.joy.apptemplate.testdemo;

import android.os.Bundle;
import android.widget.Toast;

import com.joy.apptemplate.R;
import com.joy.apptemplate.mvpbase.base.BaseActivity;

public class MainActivity extends BaseActivity<TestPresenter> implements TestView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initPresenter() {
		presenter = new TestPresenter();
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.loadData();
	}

	@Override
	public void showToast(String msg) {
		Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
	}
}
