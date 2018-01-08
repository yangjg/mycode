package com.example.yangjingan.myapplication.tools;

import android.app.Activity;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;

/**
 * Created by yangjingan on 2016/9/26.
 */
public class Views {

    public static <T> T findViewById(View view, int id) {
        if (null == view) return null;
        return (T) view.findViewById(id);
    }

    public static <T> T findViewById(Activity activity, int id) {
        if (null == activity) return null;
        return (T) activity.findViewById(id);
    }

    /**
     * 计算文本高度
     * @param paint
     * @return
     */
    public static float getTextHeight(Paint paint){
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (Math.abs(fm.ascent) + Math.abs(fm.descent));
    }

    /**
     * 计算文本宽度
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(TextPaint paint , String str){
        int x = (int)paint.measureText(str);
        return x;
    }

    public static float computeHeightWidthFactor(int height, int width, float defaultfactor) {
        float res = defaultfactor;
        //TODO目前不使用动态计算,需要的时候打开
        /*if (height > 0 && width > 0) {
            res = height * 1.0f / width;
        }*/
        return res;

    }
}
