package me.joybarannotation.annotation1;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by joybar on 2017/8/17.
 */
//http://blog.csdn.net/czhpxl007/article/details/50677112
public class ActionIdHandler {
	public static final String TAG = "ActionClickHandler";
	public static void processAnnotations(final Activity activity) {
		Class<? extends Activity> clientClass = activity.getClass();
		Field[]fields=clientClass.getDeclaredFields();
		for(Field field :fields)
			if (field.isAnnotationPresent(ActionId.class)) { // 判断是否被 @ActionId 修饰
				//获取指定Annotation对象
				ActionId actionClick2 = field.getAnnotation(ActionId.class);
				if (actionClick2 != null) {

					int id = actionClick2.id();
					if(id>0)
					{
						Log.d(TAG,"id="+id);
						//1==========================
//						//反射访问私有成员，必须加上这句
//						field.setAccessible(true);
//						//然后对这个属性复制
//						try {
//							field.set(activity, activity.findViewById(id));
//						} catch (IllegalAccessException e) {
//							e.printStackTrace();
//						}


						//2==========================
//						//获取findViewById（）
						field.setAccessible(true);
						Method m = null;
						try {
							m = clientClass.getMethod("findViewById", int.class);
							Log.d(TAG,"getName="+m.getName());
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}

						//调用findViewById（）返回Object
						Object ob = null;
						try {
							ob = m.invoke(activity,id);
							Log.d(TAG,"ob="+ob.toString());
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
//



					}




				}
			}


		}





}
