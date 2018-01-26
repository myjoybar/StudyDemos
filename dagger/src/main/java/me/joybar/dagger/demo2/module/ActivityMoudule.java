package me.joybar.dagger.demo2.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.joybar.dagger.demo1.module.Person;

/**
 * Created by joybar on 2018/1/24.
 */

@Module
public class ActivityMoudule {

	@Provides
	Person providePerson(Context context){
		//　此方法需要Context 对象
		return new Person(context);
	}
}
