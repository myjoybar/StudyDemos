package test.joybar.com.aop.login;

import android.content.Context;
import android.util.Log;
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
		Log.i(TAG, "checkLogin: ");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);
		if (checkLogin != null) {
			Context context = (Context) joinPoint.getThis();
			if (true) {
				Log.i(TAG, "checkLogin: 登录成功 ");
				return joinPoint.proceed();
			} else {
				Log.i(TAG, "checkLogin: 请登录");
				Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
				return null;
			}
		}
		return joinPoint.proceed();
	}
}
