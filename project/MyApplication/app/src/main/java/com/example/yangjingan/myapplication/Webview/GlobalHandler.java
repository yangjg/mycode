/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.yangjingan.myapplication.Webview;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalHandler {


    private final static int MSG_POST_DELAY = 1001;
    private static Handler sMainHandler;
    private static ExecutorService sCachedThreadPool;

    static {
        sMainHandler = new MyHandler(Looper.getMainLooper());
        sCachedThreadPool = Executors.newCachedThreadPool(new PriorityThreadFactory("globalhandler-trd",(android.os.Process.THREAD_PRIORITY_BACKGROUND+android.os
                .Process.THREAD_PRIORITY_FOREGROUND)/2));
    }

    /**
     * 传入需要运行的runnable
     * @param runnable
     */
    public static void post(Runnable runnable) {
        if (null != runnable) {
            sCachedThreadPool.execute(runnable);
        }
    }

    /**
     * delayMillis 参数是延迟发送的时间,这个runnable是在线程池中执行的
     * @param runnable
     * @param delayMillis
     */
    public static void post(Runnable runnable, long delayMillis) {
        Message msg = new Message();
        msg.obj = runnable;
        msg.what = MSG_POST_DELAY;
        sMainHandler.sendMessageAtTime(msg, delayMillis);
    }


    /**
     *
     * @param runnable
     */
    public static void postMainThread(Runnable runnable) {
        sMainHandler.post(runnable);
    }

    /**
     *  delayMillis 参数是延迟发送的时间
     * @param runnable
     * @param delayMillis
     */
    public static void postMainThread(Runnable runnable, long delayMillis) {
        sMainHandler.postDelayed(runnable, delayMillis);
    }

    /**
     * callback是需要删除的runnable
     * @param callback
     */
    public static void removeCallBacks(Runnable callback) {
        sMainHandler.removeCallbacks(callback);
    }

    private GlobalHandler() {
    }

    private static class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_POST_DELAY:
                    Runnable runnable = (Runnable) msg.obj;
                    GlobalHandler.post(runnable);
                    break;
                default:
                    break;
            }
        }
    }
}
