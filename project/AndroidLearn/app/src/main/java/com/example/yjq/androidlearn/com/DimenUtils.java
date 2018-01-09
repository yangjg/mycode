package com.example.yjq.androidlearn.com;

import android.content.Context;

/**
 * Created by yjq on 2016/4/25.
 */

public class DimenUtils {


        public static int dp2px(Context context, float dp) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return Math.round(dp * scale);
        }

        public static int sp2px(Context context, float spValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return Math.round(spValue * fontScale);
        }

        public static int px2sp(Context context, float pxValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return Math.round(pxValue / fontScale);
        }

}
