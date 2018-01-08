package com.example.yangjingan.myapplication.tools;

import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by yangjingan on 17-3-3.
 */
public class LooperUtils {

    public static HandlerThread mThread;

    private static final String BROWSER_THREAD="browser_common_thread";

    static {
        mThread = new HandlerThread(BROWSER_THREAD);
        mThread.start();
    }
    /**
     *
     * 描述：获取线程的looper，作者： yangjingan 创建时间： 2017-03-03上午09:41:0
     * @return 返回线程的looper
     *
     */
    public static Looper getThreadLooper(){
        return mThread.getLooper();
    }
    /**
     *
     * 描述：获取主线程的looper，作者： yangjingan 创建时间： 2017-03-03上午09:41:0
     * @return 返回主线程的looper
     *
     */
    public static Looper getMainThreadLooper(){
        return Looper.getMainLooper();
    }


}
