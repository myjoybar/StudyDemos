package com.example.joybar.myaskunagjia.demo.stucture.mvp.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by joybar on 04/01/2017.
 */

public  class User {

    @NonNull
    private String userId;

    @Nullable
    private  String name;

    @Nullable
    private  String description;

    public User(@NonNull String userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }
}
