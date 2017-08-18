package me.joybarannotation.annotation3;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import me.joybarannotation.annotation3.annotation.ContentViewInject;
import me.joybarannotation.annotation3.annotation.ViewClickInject;
import me.joybarannotation.annotation3.annotation.ViewIdInject;
import me.joybarannotation.annotation3.annotation.ViewLongClickInject;
import me.joybarannotation.annotation3.interfaces.ViewInjector;

/**
 * Created by joybar on 2017/8/18.
 */

public class ViewInjectorIml<T> implements ViewInjector {

	public static final String TAG = "ViewInjectorIml";

	public static ViewInjectorIml getInstance() {
		return ViewInjectorImlHolder.INSTANCE;
	}


	private static class ViewInjectorImlHolder {
		private static ViewInjectorIml INSTANCE = new ViewInjectorIml();
	}

	public void initInject(T t) {
		if (t == null) {
			return;
		}
		if (t instanceof ViewGroup || t instanceof Activity || t instanceof android.app.Fragment || t instanceof android.support.v4.app.Fragment) {

			WeakReference<T> softReference = new WeakReference<>(t);
			T tClient = softReference.get();
			if (tClient == null) {
				throw new RuntimeException("init object cannot be null");
			} else {
				injectViewContent(tClient);
				injectViewId(tClient);
				injectOnclick(tClient);
				injectOnLongClick(tClient);
			}
		}

	}

	private void injectViewContent(T client) {
		Class<?> clientClass = client.getClass();
		//获取注解里的参数，也就是那个int的布局文件（R.layout.XXX）
		ContentViewInject contentView = clientClass.getAnnotation(ContentViewInject.class);
		if(null!=contentView){
			int value = contentView.id();
			try {
				//通过反射获取Activity里的setContentView方法，参数是int型的布局文件id
				Method method = clientClass.getMethod("setContentView", int.class);
				//调用反射获取到的方法，value为上面通过注解获取到的layoutID
				method.invoke(client, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void injectViewId(T client) {
		Class<?> clientClass = client.getClass();
		Field[] fields = clientClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ViewIdInject.class)) { // 判断是否被 @ViewIdInject 修饰
				//获取指定Annotation对象
				ViewIdInject viewIdInject = field.getAnnotation(ViewIdInject.class);
				if (viewIdInject != null) {
					int id = viewIdInject.id();
					if (id > 0) {

						if(client instanceof Activity){
							Activity activity = (Activity) client;

							Log.d(TAG,"viewIdInject.id()="+id);
							//1==========================
						//反射访问私有成员，必须加上这句
//							field.setAccessible(true);
//							//然后对这个属性复制
//							try {
//								field.set(activity, activity.findViewById(id));
//							} catch (IllegalAccessException e) {
//								e.printStackTrace();
//							}

							//2==========================
						//获取findViewById（）
							field.setAccessible(true);
							Method m = null;
							try {
								m = clientClass.getMethod("findViewById", int.class);
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}

							//调用findViewById（）返回Object
							Object ob = null;
							try {
								ob = m.invoke(activity, id);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							//初始化字段（所属的对象，值）
							try {
								field.set(activity, ob);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}


	private  void injectOnclick(T client){
		Class<?> clientClass = client.getClass();
		for (Method m : clientClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(ViewClickInject.class)) { // 判断是否被 @ViewClickInject 修饰
				//获取指定Annotation对象
				ViewClickInject viewClickInject = m.getAnnotation(ViewClickInject.class);
				if (viewClickInject != null) {
					try {
						//遍历所有button的id
						for (int valueId : viewClickInject.id()) {
							if(client instanceof View){
								View parentView = (View) client;
								View view = parentView.findViewById(valueId);
								//addListenr函数添加监听，当click事件发生时，调用 onBtnClick() 函数
								addOnClickListenr(view, parentView, m);
							}else if(client instanceof Activity){
								Activity activity = (Activity) client;
								View view = activity.findViewById(valueId);
								//addListenr函数添加监听，当click事件发生时，调用 onBtnClick() 函数
								addOnClickListenr(view, activity, m);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

	}


	private  void injectOnLongClick(T client) {
		Class<?> clientClass = client.getClass();
		for (Method m : clientClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(ViewLongClickInject.class)) { // 判断是否被 @ViewLongClickInject 修饰
				//获取指定Annotation对象
				ViewLongClickInject viewLongClickInject = m.getAnnotation(ViewLongClickInject.class);
				if (viewLongClickInject != null) {
					try {
						//遍历所有button的id
						for (int valueId : viewLongClickInject.id()) {
							if(client instanceof View){
								View parentView = (View) client;
								View view = parentView.findViewById(valueId);
								addOnLongListenr(view, parentView, m, viewLongClickInject.isIntercepted());
							}else if(client instanceof Activity){
								Activity activity = (Activity) client;
								View view = activity.findViewById(valueId);
								addOnLongListenr(view, activity, m,viewLongClickInject.isIntercepted());
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

	}



	private static void addOnClickListenr(final Object focusView, final Object client, final Method m) throws Exception {

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


	private static void addOnLongListenr(final Object focusView, final Object client, final Method m, final boolean isIntercepted) throws Exception {

		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//场景类调用 onBtnClick() 方法
				Object result = m.invoke(client);
				return isIntercepted;

			}
		};
		//Object onClickListenr = Proxy.newProxyInstance(null, new Class[]{View.OnLongClickListener.class}, handler);
		Object onClickListenr = Proxy.newProxyInstance(View.OnLongClickListener.class.getClassLoader(), new Class[]{View.OnLongClickListener.class}, handler);
		Method setOnClickListenerMethod = focusView.getClass().getMethod("setOnLongClickListener", View.OnLongClickListener.class);
		setOnClickListenerMethod.invoke(focusView, onClickListenr);

	}



}



