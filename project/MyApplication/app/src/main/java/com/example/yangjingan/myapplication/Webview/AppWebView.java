package com.example.yangjingan.myapplication.Webview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Created by yangjingan on 17-9-20.
 */

public class AppWebView extends WebView {


    public AppWebView(Context context) {
        super(context);
        initField();
    }

    public AppWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initField();
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initField();
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initField();
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        initField();
    }

    private Looper mThreadLooper;
    private Handler mWebViewHandler;
    private boolean mIsLoad = false;
    private Semaphore mLoadSem;

    private void initField() {
        mWebViewHandler = new Handler(mThreadLooper = Looper.myLooper());
        mIsLoad = false;
        mLoadSem = new Semaphore(0);
    }

    @Override
    public void evaluateJavascript(final String script, final ValueCallback<String> resultCallback) {

        if (checkThread()) {
            if (mIsLoad) {
                super.evaluateJavascript(script, resultCallback);
            } else {
                //如果没有加载过loadurl,则先跳转到线程等待信号量，等待成功后在回到webview线程执行脚本
                asyncAwaitSemEvaluateJavascript(script, resultCallback);
            }
        } else {
            if (mIsLoad) {
                mWebViewHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AppWebView.super.evaluateJavascript(script, resultCallback);
                    }
                });
            } else {
                //没有加载过loadurl，本身也不是webview线程调用的,则直接同步去等待信号量，等待成功后在回到webview线程执行脚本
                awaitLoadSem(script, resultCallback);
            }
        }

    }

    @Override
    public void loadUrl(final String url) {
        if (checkThread()) {
            super.loadUrl(url);
            releaseLoadSem();
        } else {
            mWebViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    AppWebView.super.loadUrl(url);
                    releaseLoadSem();
                }
            });
        }
    }

    @Override
    public void loadUrl(final String url, final Map<String, String> additionalHttpHeaders) {
        if (checkThread()) {
            super.loadUrl(url, additionalHttpHeaders);
            releaseLoadSem();
        } else {
            mWebViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    AppWebView.super.loadUrl(url, additionalHttpHeaders);
                    releaseLoadSem();
                }
            });
        }
    }

    @Override
    public void loadData(final String data, final String mimeType, final String encoding) {
        if (checkThread()) {
            super.loadData(data, mimeType, encoding);
            releaseLoadSem();
        } else {
            mWebViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    AppWebView.super.loadData(data, mimeType, encoding);
                    releaseLoadSem();
                }
            });
        }
    }

    @Override
    public void loadDataWithBaseURL(final String baseUrl, final String data, final String mimeType, final String encoding, final String historyUrl) {
        if (checkThread()) {
            super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
            releaseLoadSem();
        } else {
            mWebViewHandler.post(new Runnable() {
                @Override
                public void run() {
                    AppWebView.super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
                    releaseLoadSem();
                }
            });
        }
    }

    private boolean checkThread() {
        return mThreadLooper == Looper.myLooper();
    }

    private void releaseLoadSem() {
        mLoadSem.release();
        mIsLoad = true;
    }

    private void asyncAwaitSemEvaluateJavascript(final String script, final ValueCallback<String> resultCallback) {
        GlobalHandler.post(new Runnable() {
            @Override
            public void run() {
                awaitLoadSem(script, resultCallback);
            }
        });
    }

    private void awaitLoadSem(final String script, final ValueCallback<String> resultCallback) {
        acquireLoadSem();
        //线程中获取信号量后，在去执行脚本；
        mWebViewHandler.post(new Runnable() {
            @Override
            public void run() {
                AppWebView.super.evaluateJavascript(script, resultCallback);
            }
        });
    }

    private void acquireLoadSem() {
        if (!mIsLoad) {
            try {
                mLoadSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
