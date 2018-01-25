package com.meizu.jni;

/**
 * Created by yangjingan on 18-1-15.
 */

public class Hello {

    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("hello");
    }

    private static Hello sInstance;
    public static Hello getInstance(){
        if(null == sInstance){
            sInstance = new Hello();
        }
        return sInstance;
    }

    private Hello(){

    }


    public String test(){
        String res = sayHi();
        return res ;
    }

    public native String sayHi();


}
