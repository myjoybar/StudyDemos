package me.joybarannotation.annotation1;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by joybar on 2017/8/17.
 */
//http://blog.csdn.net/czhpxl007/article/details/50677112
public class ActionLongClickHandler {
	public static final String TAG = "ActionClickHandler";
	public static void processAnnotations(final Activity activity) {
		Class<? extends Activity> clientClass = activity.getClass();
		for (final Method m : clientClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(ActionLongClick.class)) { // 判断是否被 @Invoke 修饰
				//获取指定Annotation对象
				ActionLongClick actionClick2 = m.getAnnotation(ActionLongClick.class);
				if (actionClick2 != null) {
					try {
						//遍历所有button的id
						for (int valueId : actionClick2.value()) {
							Log.d(TAG,"valueId="+valueId);
							final View view = activity.findViewById(valueId);
							if(view==null){
								Log.d(TAG,"view==null");
							}
//
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

				Log.d(TAG,"OnLongClickListener1111");
				Object result = m.invoke(client);
				Log.d(TAG,"OnLongClickListener22222");
			//	return result;
				return false;

			}
		};
		//Object onClickListenr = Proxy.newProxyInstance(null, new Class[]{View.OnLongClickListener.class}, handler);
		Object onClickListenr = Proxy.newProxyInstance(View.OnLongClickListener.class.getClassLoader(), new Class[]{View.OnLongClickListener.class}, handler);
		Method setOnClickListenerMethod = focusView.getClass().getMethod("setOnLongClickListener", View.OnLongClickListener.class);
		setOnClickListenerMethod.invoke(focusView, onClickListenr);

	}

}
