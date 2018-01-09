package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjingan on 2017/5/14.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface IntAnnotation {

    public int[] value() default 0;
}
