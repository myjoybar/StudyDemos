package me.joybar.dagger.module;

import dagger.Component;
import me.joybar.dagger.MainActivity;

/**
 * Created by joybar on 23/12/2017.
 */


@Component(modules = MainModule.class)  // 作为桥梁，沟通调用者和依赖对象库
public interface MainComponent {

    //定义注入的方法
    void inject(MainActivity activity);
}
