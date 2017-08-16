package me.joybar.loaderapp.loaderclass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.joybar.loaderapp.loaderclass.classloader.MyClassLoader;

/**
 * Created by joybar on 2017/8/15.
 */

public class main {

	public static void main(String[] args)  {

		String path ="/Users/joybar/Documents/WorkSpaces/AndroidStudio/GitTest/StudyDemos/loaderapp/src/main/java/me/joybar/loaderapp/loaderclass/remote";
		//String path ="./src/main/java/me/joybar/loaderapp/loaderclass/remote";
		MyClassLoader classLoader = new MyClassLoader(path);
		Class<?> cls = classLoader.findClass("me.joybar.loaderapp.loaderclass.remote.RemoteClass");
		Object object = null;
		try {
			object = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = cls.getDeclaredMethod("catched", null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			method.invoke(object, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
