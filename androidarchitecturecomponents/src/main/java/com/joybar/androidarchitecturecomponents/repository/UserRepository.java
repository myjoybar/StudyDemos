package com.joybar.androidarchitecturecomponents.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.joybar.androidarchitecturecomponents.data.User;
import com.joybar.androidarchitecturecomponents.data.UserDao;

import java.util.concurrent.Executor;

/**
 * Created by joybar on 2018/1/22.
 */

public class UserRepository {

	private final UserDao userDao;
	private final Executor executor;

	public UserRepository(UserDao userDao, Executor executor) {
		this.userDao = userDao;
		this.executor = executor;
	}
	// ...
//	public LiveData<User> getUser(int userId) {
//		// This is not an optimal implementation, we'll fix it below（这不是最好的实现，以后会修复的）
//		final MutableLiveData<User> data = new MutableLiveData<>();
//		data.setValue(new User("1","AAA"));
//		return data;
//	}


	public LiveData<User> getUser(String userId) {
		refreshUser(userId);
		// return a LiveData directly from the database.(从数据库中直接返回LiveData)
		return userDao.load(userId);
	}

	private void refreshUser(final String userId) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				final MutableLiveData<User> data = new MutableLiveData<>();
				User user = new User("1", "AAA");
				data.setValue(user);
				userDao.save(user);
			}
		});
	}


}
