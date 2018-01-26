package com.joybar.androidarchitecturecomponents.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by joybar on 2018/1/24.
 */

@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

	public abstract UserDao userDao();
}