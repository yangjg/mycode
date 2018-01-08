package com.example.yangjingan.ndkresearch;

/**
 * Created by yangjingan on 18-1-8.
 */

public class JniUtil {
    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("jniutil");
    }
    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用
    public native String test();
}
