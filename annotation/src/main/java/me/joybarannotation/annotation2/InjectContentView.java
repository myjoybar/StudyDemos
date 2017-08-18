package me.joybarannotation.annotation2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by joybar on 2017/8/17.
 */
@Target(ElementType.TYPE)//因为要在类上使用
@Retention(RetentionPolicy.RUNTIME)//之前已经说过，要编译到.class文件里
public @interface InjectContentView {

	int value();  //声明一个int参数
}
