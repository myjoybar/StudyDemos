package com.joy.apptemplate.mvpbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by joybar on 2018/7/25.
 */

public abstract class BaseActivity<Presenter extends BasePresenter> extends ToolbarActivity implements BaseView {
	protected Presenter presenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initPresenter();
		presenter.bindView(this);
	}

	protected abstract void initPresenter();

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != presenter) {
			presenter.unbindView();

		}
	}
}
