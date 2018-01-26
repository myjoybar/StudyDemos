package com.joybar.androidarchitecturecomponents.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by joybar on 2018/1/24.
 */

@Dao
public interface UserDao {
	@Insert(onConflict = REPLACE)
	void save(User user);
	@Query("SELECT * FROM user WHERE id = :userId")
	LiveData<User> load(String userId);
}

