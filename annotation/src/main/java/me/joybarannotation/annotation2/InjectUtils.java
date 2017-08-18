package me.joybarannotation.annotation2;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joybar on 2017/8/17.
 */
//http://blog.csdn.net/qq_31168123/article/details/52469652
public class InjectUtils {


	public InjectUtils() {
	}


	public static InjectUtils getInstance(){
		return InjectUtilsHolder.INSTANCE;
	}



	public static  class InjectUtilsHolder{
		private static  InjectUtils INSTANCE = new InjectUtils();
	}


	public  void InjectAll(Activity activity) {
		InjectContentView(activity);
		InjectViews(activity);
		OnClick(activity);
	}



	public   void OnClick(Activity activity) {  //点击事件的注解处理
		//获取MainActivity
		Class<? extends Activity> clazz = activity.getClass();
		//获取MainActivity中所有方法
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
				// 获取方法上对应的@IOnclick的注解
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				//通过annotationType获取注解@BaseEvent
				Class<? extends Annotation> annotationType = annotation.annotationType();

				//需要判断是否为null
				if (annotationType != null) {

					//获取@IOnclick注解上的BaseEvent注解
					BaseEvent baseEvent = annotationType.getAnnotation(BaseEvent.class);

					//需要判断是否为null，因为有的注解没有@BaseEvent
					if (baseEvent != null) {

						//获取@BaseEvent的三个value
						String callback = baseEvent.listenerCallback();
						Class type = baseEvent.listenerType();
						String setListener = baseEvent.setListener();
						try {
							//通过反射获取方法，@IOnclick里的int[] id()不需要传参，所以参数省略
							Method declaredMethod = annotationType.getDeclaredMethod("id");
							//调用方法，获取到@IOnclick的value，即两个button的id，参数省略
							int[] valuesIds = (int[]) declaredMethod.invoke(annotation);
							// 这个类稍后会给出代码，目的是拦截方法
							InjectInvocationHandler handler = new InjectInvocationHandler(activity);
							// 添加到拦截列表
							handler.add(callback, method);
							// 得到监听的代理对象
							Proxy proxy = (Proxy) Proxy.newProxyInstance(type.getClassLoader(),
									new Class[]{type}, handler);
							//遍历所有button的id
							for (int valuesId : valuesIds) {
								View view = activity.findViewById(valuesId);
								//  通过反射获取方法
								Method listener = view.getClass().getMethod(setListener, type);
								//执行方法
								listener.invoke(view, proxy);
							}
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	public class InjectInvocationHandler implements InvocationHandler {

		//    拦截的方法名列表
		private Map<String, Method> map = new HashMap<>();
		//    在这里实际上是MainActivity
		private Object target;

		public InjectInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (target != null) {
				// 获取方法名
				String name = method.getName();
				Method m = map.get(name);
				if (m != null) {//如果不存在与拦截列表，就执行
					return m.invoke(target, args);
				}
			}
			return null;
		}

		/**
		 * 向拦截列表里添加拦截的方法
		 */
		public void add(String name, Method method) {
			map.put(name, method);
		}
	}






	public  void InjectContentView(Activity activity) {  //setContentView的注解处理
		//获取MainActivity类
		Class<? extends Activity> clazz = activity.getClass();

		//获取MainActivity类上的注解，传入的是InjectContentView.class，注意返回的是InjectContentView
		InjectContentView contentView = clazz.getAnnotation(InjectContentView.class);

		//获取注解里的参数，也就是那个int的布局文件（R.layout.XXX）
		int value = contentView.value();

		try {
			//通过反射获取Activity里的setContentView方法，参数是int型的布局文件id
			Method method = clazz.getMethod("setContentView", int.class);
			//调用反射获取到的方法，value为上面通过注解获取到的layoutID
			method.invoke(activity, value);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public  void InjectViews(Activity activity) {  //findViewById的注解处理
//获取MainActivity类
		Class<? extends Activity> clazz = activity.getClass();

		//获取全部的类中生命的的成员变量
		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field declaredField : declaredFields) {

			//获取对应的注解，传入的是InjectViews.class，注意返回的是InjectViews
			InjectViews annotation = declaredField.getAnnotation(InjectViews.class);

			//这里要判断下注解是否为null，因为比如声明一个 public int i 时，是没有注解的
			if (annotation != null) {
				//当注解不为null，获取其参数
				int value = annotation.value();

				try {
					//通过反射获取findViewById方法
					Method method = clazz.getMethod("findViewById", int.class);
					//调用方法
					Object view = method.invoke(activity, value);
					//这里要注意，类中的成员变量为private,故必须进行此操作，否则无法给控件赋值（即初始化的捆绑）
					declaredField.setAccessible(true);
					//将初始化后的控件赋值到MainActivity里的对应控件上
					declaredField.set(activity, view);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
