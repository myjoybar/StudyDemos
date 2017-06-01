package com.example.joybar.myaskunagjia.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.testapplication.TestApplicationActivity;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by joybar on 1/11/16.
 */
public class MyApplication extends Application {

    private static String TAG  = "MyApplication";

    private static Context context;

    private static  RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initLeakCanary();
        registerActivityLifecycleCallbacks();
    }

    private void initContext() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
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

    private void registerActivityLifecycleCallbacks(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle bundle) {


                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
//                if (activity.findViewById(R.id.toolbar) != null) { //找到 Toolbar 并且替换 Actionbar
//                    if (activity instanceof TestApplicationActivity) {
//                        ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
//                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
//                    } else {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
//                            activity.getActionBar().setDisplayShowTitleEnabled(false);
//                        }
//                    }
//                }

                if (activity instanceof TestApplicationActivity) {
                    L.d(TAG,"TestApplicationActivity onActivityCreated");
                    ((TextView) activity.findViewById(R.id.toolbar_title)).setText("我是替换掉的");
                    //找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
                    activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            activity.onBackPressed();
                        }
                    });
                }







        }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

}
