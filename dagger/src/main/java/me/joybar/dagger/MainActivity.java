package me.joybar.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import me.joybar.dagger.module.DaggerMainComponent;
import me.joybar.dagger.module.MainComponent;
import me.joybar.dagger.module.MainModule;
import me.joybar.dagger.module.Person;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 构造桥梁对象
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        //注入
        component.inject(this);

        Log.d(TAG,"person.getName()="+person.getName());


    }
}
