package me.joybar.dagger.demo1.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joybar on 23/12/2017.
 */

@Module   //提供依赖对象的实例
public class MainModule {

	// 关键字，标明该方法提供依赖对象
//    @Provides
//    Person providerPerson(){
//        //提供Person对象
//        return new Person();
//    }


	private Context mContext;

	public MainModule(Context context){
		mContext = context;
	}

	@Provides
	Context providesContext(){
		// 提供上下文对象
		return mContext;
	}

	@Provides // 关键字，标明该方法提供依赖对象
	Person providerPerson(Context context){

		return new Person(context);
	}

}
