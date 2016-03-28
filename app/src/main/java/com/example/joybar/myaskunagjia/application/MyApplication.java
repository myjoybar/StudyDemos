package com.example.joybar.myaskunagjia.application;

import android.app.Application;
import android.content.Context;

import com.example.joybar.myaskunagjia.utils.AppManagerUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by joybar on 1/11/16.
 */
public class MyApplication extends Application {

    private static Context mContext;

    private static  RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initLeakCanary();
        addShortcut();
    }

    private void initContext() {
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initLeakCanary(){
        //http://blog.csdn.net/watermusicyes/article/details/46333925
        LeakCanary.install(this);

        refWatcher = installLeakCanary();
        //refWatcher = LeakCanary.install(this);
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }

    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }


    private void addShortcut(){
        AppManagerUtils.addShortcutByPackageName(mContext,AppManagerUtils.getAppName(mContext));
    }

}
