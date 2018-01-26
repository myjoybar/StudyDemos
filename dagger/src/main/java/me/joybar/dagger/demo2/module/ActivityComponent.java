package me.joybar.dagger.demo2.module;

import dagger.Component;
import me.joybar.dagger.demo2.MainActivity;

/**
 * Created by joybar on 2018/1/24.
 */

@Component(dependencies = AppComponent.class,modules = ActivityMoudule.class)
public interface ActivityComponent {

	// 注入
	void inject(MainActivity activity);
}