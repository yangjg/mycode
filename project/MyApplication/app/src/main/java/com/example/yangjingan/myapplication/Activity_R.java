package com.example.yangjingan.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Activity_R {
    private static final String TAG = "Activity_R";
    private static final String ERROR_TAG = "ReflectError " + TAG;
    private static Method sMethod_setStatusBarDarkIcon;
    private static Method sMethod_setStatusBarDarkIconColor;
    private static Field sField_Activity_mHandler;

    static {
        if ((Build.VERSION.SDK_INT >= 20) && (Build.VERSION.SDK_INT <= 22)) {
            try {
                sMethod_setStatusBarDarkIcon =
                        Activity.class.getDeclaredMethod("setStatusBarDarkIcon", boolean.class);

            } catch (Exception e) {
                Log.d(ERROR_TAG, "", e);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            try {
                sMethod_setStatusBarDarkIconColor =
                        Activity.class.getDeclaredMethod("setStatusBarDarkIcon", int.class);
            } catch (Exception e) {
                Log.d(ERROR_TAG, "", e);
            }
        }
    }



    public static void setStatusBarDarkIcon(Activity a, boolean dark) {
        if (a == null || a.getWindow() == null) {
            return;
        }

        if (Build.VERSION.SDK_INT <= 19) {
            try {
                WindowManager.LayoutParams lp = a.getWindow().getAttributes();
                int value = WindowManager_R.getMeizuFlags(lp);
                boolean hasDarkStatus = ((value & WindowManager_R.MEIZU_FLAG_DARK_STATUS_BAR_ICON)
                        == WindowManager_R.MEIZU_FLAG_DARK_STATUS_BAR_ICON);
                if (dark == hasDarkStatus) {
                    return;
                }

                if (dark) {
                    value |= 1 << 9;
                } else {
                    value &= ~(1 << 9);
                }
                value |= 1 << 10;// 输入法；

                WindowManager_R.setMeizuFlags(lp, value);
                a.getWindow().setAttributes(lp);

            } catch (Exception e) {
                Log.d(ERROR_TAG, "", e);
            }

        } else if (Build.VERSION.SDK_INT <= 22) {
            int meizuFlags = WindowManager_R.getMeizuFlags(a.getWindow().getAttributes());
            boolean hasDarkStatus = ((meizuFlags & WindowManager_R.MEIZU_FLAG_DARK_STATUS_BAR_ICON)
                    == WindowManager_R.MEIZU_FLAG_DARK_STATUS_BAR_ICON);
            if (dark == hasDarkStatus) {
                return;
            }

            if (null != sMethod_setStatusBarDarkIcon) {
                try {
                    sMethod_setStatusBarDarkIcon.invoke(a, dark);
                } catch (Exception e) {
                    Log.d(ERROR_TAG, "", e);
                }
            }

        } else {
            if (null != sMethod_setStatusBarDarkIconColor) {
                try {
                    boolean isNightMode = false;
                    if(isNightMode){
                        sMethod_setStatusBarDarkIconColor.invoke(a, 0x73ffffff);
                    }else {
                        sMethod_setStatusBarDarkIconColor.invoke(a, dark ? 0xff000000:0xffffffff);
                    }
                } catch (Exception e) {
                    Log.d(ERROR_TAG, "", e);
                }
            }
        }

 /*       <color name="content_bg">@color/white</color>
        <color name="content_bg_night">#201f26</color>*/
    }

    public static void setupActionBar_nightMode(Object activity,
                                                boolean isNightMode) {
        ActionBar ac = null;
        Resources res = null;
        Window w = null;

        if (activity instanceof AppCompatActivity) {
            ac = ((AppCompatActivity)activity).getSupportActionBar();
            res = ((AppCompatActivity)activity).getResources();
            w = ((AppCompatActivity)activity).getWindow();
        }

        if(null != w) {
            w.setBackgroundDrawable(isNightMode ? new ColorDrawable(0xff201f26) : new
                    ColorDrawable(0xffffffff));
        }
//        if(activity instanceof Activity)
//        	setDarkTitleBar((Activity)activity, !isNightMode);
    }


}