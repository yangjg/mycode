package com.example;

import java.awt.Color;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjingan on 2017/5/14.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ColorAnnotation {


    public final static int RED = 1;
    public final static int BLACK =0;


    @IntAnnotation({RED,BLACK})
    public int color() default BLACK;
}
