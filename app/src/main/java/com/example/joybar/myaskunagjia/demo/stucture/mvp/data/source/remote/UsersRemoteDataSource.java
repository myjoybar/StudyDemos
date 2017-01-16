package com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source.UsersDataSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersRemoteDataSource implements UsersDataSource {


    private static UsersRemoteDataSource INSTANCE;

    private final static Map<String, User> USERS_SERVICE_DATA;

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

    static {
        USERS_SERVICE_DATA = new LinkedHashMap<>(2);

        addTask("1", "Tom_remote", "Ground looks good, no foundation work required.");
        addTask("2", "Jim_remote", "Found awesome girders at half the cost!");
        addTask("3", "Marina_remote", "Found awesome girders at half the cost!");
    }

    private static void addTask(String id, String name, String description) {
        User newTask = new User(id, name, description);
        USERS_SERVICE_DATA.put(newTask.getUserId(), newTask);
    }


    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void saveUser(@NonNull User user) {
        USERS_SERVICE_DATA.put(user.getUserId(), user);
    }

    @Override
    public void deleteAllUsers() {
        USERS_SERVICE_DATA.clear();
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        USERS_SERVICE_DATA.remove(userId);
    }

    @Override
    public void updateUser(@NonNull String userId) {

    }

    @Override
    public User getUser(@NonNull String userId) {
        User user = USERS_SERVICE_DATA.get(userId);
        return user;
    }

    @Override
    public void getUser(@NonNull String userId, @NonNull final GetUserCallback callback) {

        final User user = USERS_SERVICE_DATA.get(userId);

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onUserLoaded(user);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }


    @Override
    public ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<User>();

        for(Iterator it = USERS_SERVICE_DATA.entrySet().iterator(); it.hasNext();){
            Map.Entry entry_date = (Map.Entry)it.next();
            User item = (User)entry_date.getValue(); //  StatItem 是我自己定义的一个简单的JAVABEAN 对象类型
            users.add(item);
        }

      // callback.onUsersLoaded(users);
        return users;
    }

    @Override
    public void getUsers(@NonNull final LoadUsersCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> users = new ArrayList<User>();

                for(Iterator it = USERS_SERVICE_DATA.entrySet().iterator(); it.hasNext();){
                    Map.Entry entry_date = (Map.Entry)it.next();
                    User item = (User)entry_date.getValue(); //  StatItem 是我自己定义的一个简单的JAVABEAN 对象类型
                    users.add(item);
                }

                callback.onUsersLoaded(users);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void refreshUsers() {

    }
}
