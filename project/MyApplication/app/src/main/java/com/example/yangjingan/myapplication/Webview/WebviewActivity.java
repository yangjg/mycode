package com.example.yangjingan.myapplication.Webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.yangjingan.myapplication.BroadCast.SendUserChangeBroadCast;
import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 17-1-20.
 */
public class WebviewActivity extends Activity {

    private WebView webView;
    boolean isOK = false;

    private static final String T2="file:///android_asset/html/t2.html";
    private static final String DEFAULT_URL = "https://act-app.mzres.com/resources/activity/2017-party/index.html?qunarhotelbeta=1"; //"http://appff.meizu.com/front/example/dialog/index.html"; //"http://www.baidu.com/";  //"file:///android_asset/html/flashtest.html";//"http://www.baidu.com/";// "https://y.mzres.com/resources/activity/test/index.html";//"http://www.baidu.com/";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_laout);
        webView = (WebView) findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                isOK = false;
                Thread th = Thread.currentThread();
                String msg = String.format(" Method=%s, ThreadName = %s, Thread id = %s", "shouldOverrideUrlLoading", th.getName(), th.getId());
                Log.e(TAG, msg);
                //Log.e(TAG,"shouldOverrideUrlLoading");
                return isOK;//super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //  Log.e(TAG,"onPageStarted");
                Thread th = Thread.currentThread();
                String msg = String.format(" Method=%s, ThreadName = %s, Thread id = %s", "onPageStarted", th.getName(), th.getId());
                Log.e(TAG, msg);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
              //  removeJs(view);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

        });
        WebSettings settings = webView.getSettings();
        //settings.setJavaScriptEnabled(true);
        configWebsettings(settings);
        Button btn = (Button) findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dowork();
            }
        });
        insertJs(webView);
    }

    private void dowork() {
        webView.loadUrl(DEFAULT_URL);
    }

    private JsInterface mJsObject;
    private void insertJs(WebView webView){
        if(null == mJsObject){
            mJsObject = new JsInterface();
        }
        webView.addJavascriptInterface(mJsObject,JsInterface.JSNAME);
       // webView.loadUrl("javascript:fwefweetes('1')");
       // webView.evaluateJavascript("javascript:fwefweetes('1')",null);
    }


    private void removeJs(WebView webView){
      webView.removeJavascriptInterface(JsInterface.JSNAME);
    }

    protected void configWebsettings(WebSettings settings) {
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setAppCacheMaxSize(1024 * 1024 * 5);//设置缓冲大小5M
        String appCacheDir = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCacheDir);
        settings.setAppCacheEnabled(true);

        settings.setTextZoom(100);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
    }
}
