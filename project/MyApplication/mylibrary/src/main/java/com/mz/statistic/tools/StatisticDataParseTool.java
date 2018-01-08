package com.mz.statistic.tools;

import android.text.TextUtils;
import android.util.Log;


import com.mz.statistic.annotations.*;
import com.mz.statistic.common.Constants;
import com.mz.statistic.statisticbean.IStatisticData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjingan on 17-10-17.
 */

public class StatisticDataParseTool {


    private static final String TAG = "StatisticDataParseTool";

    public static Map<String, String> parseDataToMap(IStatisticData data) {
        if (checkDataCanStatistic(data)) {
            Map<String, String> map = new HashMap<>();
            parseData(data, map);
            if (null != data.getExtrasInfo()) {
                map.putAll(data.getExtrasInfo());
            }
            return map;
        }
        return null;
    }


    private static void parseData(IStatisticData data, Map<String, String> map) {
        Annotation annotation = data.getClass().getAnnotation(DefaultAllField.class);
        if (null != annotation) {
            // 走DefaultAllField统计所有字段，字段名字就是Key的名字；
            parseDefaultAllField(data, map);
        } else {
            parseExtractField(data, map);
        }
    }

    private static void parseDefaultAllField(IStatisticData data, Map<String, String> map) {
        Field[] fields = data.getClass().getFields();
        int fieldCount = fields.length;
        for (int i = 0; i < fieldCount; i++) {
            Field field = fields[i];
            Annotation annotation = field.getAnnotation(DisableField.class);
            if (null != annotation) {
                //如果标识这个字段则标识不需要统计这个属性;
                continue;
            }
            annotation = field.getAnnotation(ClassType.class);
            if (null != annotation) {
                //如果有这个注解，标识这个是一个对象，不是一个基本类型的属性，需要走对象处理流程
                parseClassTypeField(field, data, map);
                continue;
            }

            annotation = field.getAnnotation(StatisticsKey.class);
            if (null != annotation) {
                //如果有这个注解，标识这个字段需要单独使用自定义的Key来统计;
                parseStatisticsKeyField(field, (StatisticsKey) annotation, data, map);
                continue;
            }

            // 以下就是处理默认情况，没有其他注解的情况；
            parseDefaultField(field, data, map);
        }
    }

    private static void parseExtractField(IStatisticData data, Map<String, String> map) {
        Field[] fields = data.getClass().getFields();
        int fieldCount = fields.length;
        for (int i = 0; i < fieldCount; i++) {
            Field field = fields[i];
            Annotation annotation = field.getAnnotation(ClassType.class);
            if (null != annotation) {
                parseClassTypeField(field, data, map);
                continue;
            }

            annotation = field.getAnnotation(StatisticsKey.class);
            if (null != annotation) {
                //如果有这个注解，标识这个字段需要单独使用自定义的Key来统计;
                parseStatisticsKeyField(field, (StatisticsKey) annotation, data, map);
                continue;
            }
        }
    }


    private static void parseClassTypeField(Field field, IStatisticData data, Map<String, String> map) {
        try {
            Object object = field.get(data);
            if (object instanceof IStatisticData) {
                IStatisticData temp = (IStatisticData) object;
                if (null != temp) {
                    parseData(temp, map);
                    Map<String, String> extras = temp.getExtrasInfo();
                    if (null != extras && !extras.isEmpty()) {
                        map.putAll(extras);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, e.getMessage());
        }
    }

    private static void parseStatisticsKeyField(Field field, StatisticsKey annotation, IStatisticData data, Map<String, String> map) {
        try {
            String value = convertObjectToString(field.get(data), annotation.minValue());
            if (!TextUtils.isEmpty(value)) {
                map.put(annotation.value(), value);
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, e.getMessage());
        }
    }

    private static void parseDefaultField(Field field, IStatisticData data, Map<String, String> map) {
        Annotation annotation = field.getAnnotation(InvalidValue.class);
        int invalidValue = Constants.AnnotationValue.DEFAULT_INVALID_INT;
        if (null != annotation) {
            invalidValue = ((InvalidValue) annotation).minValue();
        }
        try {
            String value = convertObjectToString(field.get(data), invalidValue);
            if (!TextUtils.isEmpty(value)) {
                map.put(field.getName(), value);
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, e.getMessage());
        }
    }


    private static boolean checkDataCanStatistic(IStatisticData data) {
        return null != data;
    }

    private static String convertObjectToString(Object object, int minValue) {
        String res = null;
        if (object instanceof String) {
            res = String.valueOf(object);
        } else if (object instanceof Integer) {
            res = handlerIntegerToString((Integer) object, minValue);
        } else if (object instanceof Float) {
            res = handlerFloatToString((Float) object, minValue);
        } else if (object instanceof Double) {
            res = handlerDoubleToString((Double) object, minValue);
        } else if (object instanceof Short) {
            res = handlerShortToString((Short) object, minValue);
        } else if (object instanceof Long) {
            res = handlerLongToString((Long) object, minValue);
        } else if (object instanceof Byte) {
            res = handlerByteToString((Byte) object, minValue);
        } else if (object instanceof Collection) {
            res = object.toString();
        }
        return res;
    }

    private static String handlerIntegerToString(Integer value, int minValue) {

        if (value.intValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }

    private static String handlerFloatToString(Float value, int minValue) {
        if (value.floatValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }

    private static String handlerDoubleToString(Double value, int minValue) {
        if (value.doubleValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }

    private static String handlerShortToString(Short value, int minValue) {
        if (value.shortValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }

    private static String handlerLongToString(Long value, int minValue) {
        if (value.longValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }

    private static String handlerByteToString(Byte value, int minValue) {
        if (value.byteValue() >= minValue) {
            return String.valueOf(value.intValue());
        }
        return null;
    }


}
