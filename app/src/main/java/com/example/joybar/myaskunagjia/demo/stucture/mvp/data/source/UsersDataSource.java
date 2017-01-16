package com.example.joybar.myaskunagjia.demo.stucture.mvp.data.source;

import android.support.annotation.NonNull;

import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;

import java.util.ArrayList;

/**
 * Created by joybar on 04/01/2017.
 */

public interface UsersDataSource {

    interface LoadUsersCallback {

        void onUsersLoaded(ArrayList<User> users);

        void onUserNotAvailable();
    }

    interface GetUserCallback {

        void onUserLoaded(User user);

        void onUserNotAvailable();

    }


    void saveUser(@NonNull User user);
    void deleteAllUsers();
    void deleteUser(@NonNull String userId);
    void updateUser(@NonNull String userId);
    User getUser(@NonNull String userId);
    void getUser(@NonNull String userId,final @NonNull GetUserCallback callback);
    ArrayList<User> getUsers();
    void getUsers(final @NonNull LoadUsersCallback callback);
    void refreshUsers();
}
