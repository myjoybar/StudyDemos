package me.joybarannotation.annotation2;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by joybar on 2017/8/17.
 */

@Target(ElementType.METHOD)   //注解应用于其他注解上
@Retention(RetentionPolicy.RUNTIME)
//这个注解需要注意,对应的参数在上面注解的 btn_login 点击事件里对应找即可
@BaseEvent(setListener = "setOnClickListener",//setOnClickListener为View.setOnClickListener
		listenerType = View.OnClickListener.class,//监听的类型为点击事件
		listenerCallback = "onClick")//这个onClick回调，即为setOnClickListener后回调的onClick
public @interface InjectOnclick {
	int[] value();//因为一个方法可能与多个控件绑定
}
