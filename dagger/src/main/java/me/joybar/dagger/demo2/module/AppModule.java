package me.joybar.dagger.demo2.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joybar on 2018/1/24.
 */

@Module
public class AppModule {

	private Context mContext;

	public AppModule(Context context){
		mContext = context;
	}

	@Provides
	Context providesContext(){
		// 提供Context对象　
		return mContext;
	}

}


