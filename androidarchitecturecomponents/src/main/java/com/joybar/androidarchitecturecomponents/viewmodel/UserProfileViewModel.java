package com.joybar.androidarchitecturecomponents.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.joybar.androidarchitecturecomponents.data.User;

/**
 * Created by joybar on 2018/1/22.
 */

public class UserProfileViewModel extends ViewModel {

	private String userId;
	private User user;

	//初始化传递uid进来
	public void init(String userId) {
		this.userId = userId;
	}
	//提供完整的用户信息
	public User getUser() {
		return user;
	}

}
