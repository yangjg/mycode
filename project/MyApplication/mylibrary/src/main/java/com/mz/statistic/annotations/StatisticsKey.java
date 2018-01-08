package com.mz.statistic.annotations;


import com.mz.statistic.common.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjingan on 17-10-17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StatisticsKey {
    String value() default Constants.AnnotationValue.DEFAULT_INVALID_STRING;

    int minValue() default Constants.AnnotationValue.DEFAULT_INVALID_INT;
}
