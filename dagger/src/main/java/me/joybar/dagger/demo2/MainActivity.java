package me.joybar.dagger.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import me.joybar.dagger.R;
import me.joybar.dagger.demo1.module.Person;
import me.joybar.dagger.demo2.module.ActivityMoudule;
import me.joybar.dagger.demo2.module.AppComponent;
import me.joybar.dagger.demo2.module.AppModule;
import me.joybar.dagger.demo2.module.DaggerActivityComponent;
import me.joybar.dagger.demo2.module.DaggerAppComponent;

public class MainActivity extends AppCompatActivity {
   //http://blog.csdn.net/lisdye2/article/details/51942511
    private static final String TAG = "MainActivity";
    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
    }

    private void test1(){
//        // 依赖对象　Component
        AppComponent appCom = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        // 子类依赖对象 ，并注入
        DaggerActivityComponent.builder()
                .appComponent(appCom)
                .activityMoudule(new ActivityMoudule())
                .build()
                .inject(this);
    }



}
