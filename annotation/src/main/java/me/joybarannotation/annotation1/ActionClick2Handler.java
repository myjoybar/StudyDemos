package me.joybarannotation.annotation1;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by joybar on 2017/8/17.
 */
//http://blog.csdn.net/czhpxl007/article/details/50677112
public class ActionClick2Handler {
	public static final String TAG = "ActionClickHandler";
	public static void processAnnotations(Activity activity) {
		Class<? extends Activity> clientClass = activity.getClass();
		for (Method m : clientClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(ActionClick2.class)) { // 判断是否被 @Invoke 修饰
				//获取指定Annotation对象
				ActionClick2 actionClick2 = m.getAnnotation(ActionClick2.class);
				if (actionClick2 != null) {
					try {
						//遍历所有button的id
						for (int valueId : actionClick2.value()) {
							View view = activity.findViewById(valueId);
							//addListenr函数添加监听，当click事件发生时，调用 onBtnClick() 函数
							addListenr(view, activity, m);
						}

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
	//	Object onClickListenr = Proxy.newProxyInstance(null, new Class[]{View.OnClickListener.class}, handler);
		Object onClickListenr = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{View.OnClickListener.class}, handler);
		Method setOnClickListenerMethod = focusView.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
		setOnClickListenerMethod.invoke(focusView, onClickListenr);

	}

}
