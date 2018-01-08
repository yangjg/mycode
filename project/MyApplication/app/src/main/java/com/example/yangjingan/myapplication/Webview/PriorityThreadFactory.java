package com.example.yangjingan.myapplication.Webview;

import android.os.Process;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangjingan on 17-5-23.
 */

public final class PriorityThreadFactory implements ThreadFactory {
    private final int mPriority;
    private final AtomicInteger mNumber = new AtomicInteger();
    private final String mName;
    private final static String mBaseName ="AppCenter";

    public PriorityThreadFactory(String name, int priority) {
        mName = name;
        mPriority = priority;
    }

    public Thread newThread(Runnable r) {
        return new Thread(r, mBaseName +"-" + mName + '-' + mNumber.getAndIncrement()) {
            @Override
            public void run() {
                Process.setThreadPriority(mPriority);
                super.run();
            }
        };
    }
}