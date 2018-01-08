package com.example.yangjingan.myapplication.Webview;

import android.webkit.JavascriptInterface;

/**
 * Created by yangjingan on 17-1-20.
 */
public class JsInterface {


    public static final String JSNAME= "test";





    public JsInterface(){

    }


    //used by app center
    @JavascriptInterface
    public String getVersion(){
        return  "4.0";
    }

}
