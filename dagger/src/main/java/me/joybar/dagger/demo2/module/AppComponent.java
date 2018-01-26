package me.joybar.dagger.demo2.module;

import android.content.Context;

import dagger.Component;

/**
 * Created by joybar on 2018/1/24.
 */
//全局的Component 组件
@Component(modules = AppModule.class)
public interface AppComponent {

	// 向其下层提供Context 对象
	Context proContext();
}