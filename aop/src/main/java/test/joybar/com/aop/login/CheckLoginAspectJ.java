package test.joybar.com.aop.login;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by joybar on 2018/4/13.
 */
@Aspect
public class CheckLoginAspectJ {
	private static final String TAG = "CheckLoginAspectJ";

	/**
	 * 找到处理的切点
	 * * *(..)  可以处理CheckLogin这个类所有的方法
	 */
	@Pointcut("execution(@test.joybar.com.aop.login.CheckLogin  * *(..))")
	public void executionCheckLogin() {
	}

	/**
	 * 处理切面
	 *
	 * @param joinPoint
	 * @return
	 */
	@Around("executionCheckLogin()")
	public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
		Log.i(TAG, "start checkLogin: ");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);
		if (checkLogin != null) {
			Context context = getContext(joinPoint.getThis()) ;
			java.util.Random random=new java.util.Random();
			int result=random.nextInt(2)+1;// 返回[0,2)
			if (result==1) {
				Log.i(TAG, "checkLogin: 登录成功 ");
				Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();;
				return joinPoint.proceed();
			} else {
				Log.i(TAG, "checkLogin: 请登录");
				Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
				return null;
			}
		}
		return joinPoint.proceed();
	}

	/**
	 * 通过对象获取上下文
	 *
	 * @param object
	 * @return
	 */
	private Context getContext(Object object) {
		if (object instanceof Activity) {
			return (Activity) object;
		}else if (object instanceof Application) {
			return (Application) object;
		} else if (object instanceof Fragment) {
			android.app.Fragment fragment = (android.app.Fragment) object;
			return fragment.getActivity();
		}  else if (object instanceof Fragment) {
			android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) object;
			return fragment.getActivity();
		}else if (object instanceof View) {
			View view = (View) object;
			return view.getContext();
		}
		return null;
	}
}
