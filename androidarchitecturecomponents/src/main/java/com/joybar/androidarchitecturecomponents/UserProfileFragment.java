package com.joybar.androidarchitecturecomponents;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joybar.androidarchitecturecomponents.data.User;
import com.joybar.androidarchitecturecomponents.viewmodel.UserProfileViewModel2;

/**
 * Created by joybar on 2018/1/22.
 */

public class UserProfileFragment  extends Fragment {

	private static final String UID_KEY = "uid";
	private UserProfileViewModel2 viewModel;
	private static final String TAG = "UserProfileFragment";

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//通过Arguments获取uid
		String userId = getArguments().getString(UID_KEY);
		//创建ViewModel，不必太在意ViewModel的创建形式，这个之后会做详细的分析。现在只需要明白是在哪里生成的就行。
//		viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
//		viewModel.init(userId);

		viewModel.getUser().observe(this, new Observer<User>() {
			@Override
			public void onChanged(@Nullable User user) {
				//update UI
				//UserProfileViewModel中的user发生改变，那么Fragment中就在onChanged()会收到相应的改变，并根据改变更新相应的UI。
				Log.d(TAG,"update UI");
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.user_profile, container, false);
	}


}
