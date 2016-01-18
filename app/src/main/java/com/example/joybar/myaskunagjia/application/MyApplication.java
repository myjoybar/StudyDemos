package com.example.joybar.myaskunagjia.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by joybar on 1/11/16.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
    }

    private void initContext() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
