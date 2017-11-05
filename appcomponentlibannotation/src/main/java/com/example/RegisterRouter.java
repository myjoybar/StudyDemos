package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by joybar on 04/11/2017.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface RegisterRouter {
    String module() default "";
    String patten() default "activity"; //activity,service ,broadcast
    String scheme() default "";

}
