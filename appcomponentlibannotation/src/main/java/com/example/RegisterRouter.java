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
    String patten() default "";
    String scheme() default "";

}
