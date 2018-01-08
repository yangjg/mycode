package com.example.yangjingan.myapplication.Services;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by yangjingan on 17-3-14.
 */
public class DetectService extends AccessibilityService {



    final static String TAG = "DetectService";

    public static String foregroundPackageName;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0; // 根据需要返回不同的语义值
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            /*
             * 如果 与 DetectionService 相同进程，直接比较 foregroundPackageName 的值即可
             * 如果在不同进程，可以利用 Intent 或 bind service 进行通信
             */
            foregroundPackageName = event.getPackageName().toString();

            /*
             * 基于以下还可以做很多事情，比如判断当前界面是否是 Activity，是否系统应用等，
             * 与主题无关就不再展开。
             */
           /* ComponentName cName = new ComponentName(event.getPackageName().toString(),
                    event.getClassName().toString());*/
        }

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected  void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 方法6：使用 Android AccessibilityService 探测窗口变化，跟据系统回传的参数获取 前台对象 的包名与类名
     *
     * @param packageName 需要检查是否位于栈顶的App的包名
     */
    public static boolean isForegroundPkgViaDetectionService(String packageName) {
        return packageName.equals(DetectService.foregroundPackageName);
    }



}
