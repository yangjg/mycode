package com.mz.statistic.annotations;

import com.mz.statistic.common.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjingan on 17-11-23.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidValue {
    int minValue() default Constants.AnnotationValue.DEFAULT_INVALID_INT;
}
