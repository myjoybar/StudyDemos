package me.joybar.dagger.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import me.joybar.dagger.R;
import me.joybar.dagger.demo1.module.DaggerMainComponent;
import me.joybar.dagger.demo1.module.MainComponent;
import me.joybar.dagger.demo1.module.MainModule;
import me.joybar.dagger.demo1.module.Person;

public class MainActivity extends AppCompatActivity {
   //http://blog.csdn.net/lisdye2/article/details/51942511
    private static final String TAG = "MainActivity";
    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test2();
    }

    private void test1(){
                // 构造桥梁对象
//        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule()).build();
//        //注入
//        component.inject(this);
//        Log.d(TAG,"person.getName()="+person.getName());
    }

    private void test2(){
// 构造桥梁对象
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        //注入
        component.inject(this);

    }

}
