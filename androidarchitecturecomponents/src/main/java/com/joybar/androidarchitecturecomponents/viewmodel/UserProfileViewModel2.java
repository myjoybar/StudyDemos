package com.joybar.androidarchitecturecomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.joybar.androidarchitecturecomponents.data.User;
import com.joybar.androidarchitecturecomponents.repository.UserRepository;

/**
 * Created by joybar on 2018/1/22.
 */

public class UserProfileViewModel2 extends ViewModel {
	private int userId;
	//private User user;
	private LiveData<User> user;

	private UserRepository userRepo;

	public UserProfileViewModel2(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	//初始化传递uid进来
	public void init(int userId) {
		this.userId = userId;
		user = userRepo.getUser(userId);
	}

	public LiveData<User> getUser() {
		return user;
	}



}
