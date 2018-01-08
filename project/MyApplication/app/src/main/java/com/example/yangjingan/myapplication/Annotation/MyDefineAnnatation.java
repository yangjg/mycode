package com.example.yangjingan.myapplication.Annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjingan on 17-5-8.
 */
public class MyDefineAnnatation {

    public final static int RED=1;
    public final static int RED2=2;
    public final static int RED3=3;

    @IntDef({RED,RED2,RED3})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ItemType{
         long[] value() default 0;
    }

}
