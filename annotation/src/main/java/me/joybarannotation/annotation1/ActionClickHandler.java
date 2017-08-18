package me.joybarannotation.annotation1;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by joybar on 2017/8/17.
 */
//http://blog.csdn.net/czhpxl007/article/details/50677112
public class ActionClickHandler {
	public static final String TAG = "ActionClickHandler";
	public static void processAnnotations(Object client) {
		Class<?> clientClass = client.getClass();
		Log.d(TAG,"processAnnotations");
		for (Method m : clientClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(ActionClick.class)) { // 判断是否被 @Invoke 修饰
				//获取指定Annotation对象
				ActionClick listener = m.getAnnotation(ActionClick.class);
				if (listener != null) {
					Log.d(TAG,"listener != null");
					try {
						Log.d(TAG,"listener.source()="+listener.source());
						Field f = clientClass.getDeclaredField(listener.source());
						f.setAccessible(true);
						//控件对象
						Object focusView = f.get(client);
						//addListenr函数添加监听，当click事件发生时，调用 onBtnClick() 函数
						addListenr(focusView, client, m);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}


		}


	}

	private static void addListenr(final Object focusView, final Object client, final Method m) throws Exception {

		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//场景类调用 onBtnClick() 方法
				return m.invoke(client);
			}
		};
		Object onClickListenr = Proxy.newProxyInstance(null, new Class[]{View.OnClickListener.class}, handler);
		Method setOnClickListenerMethod = focusView.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
		setOnClickListenerMethod.invoke(focusView, onClickListenr);

	}

}
