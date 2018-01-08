package com.example.yangjingan.myapplication.Annotation;

import android.util.Log;

import com.example.yangjingan.myapplication.RSATest.RSAUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangjingan on 17-5-8.
 * https://www.cnblogs.com/fangxupeng/p/4128990.html
 */
@MyDefineAnnatation.ItemType(value = {0,1,2})
public class AnnotationTest implements InvocationHandler {

    private  AnnotationTest(){

    }

    public static AnnotationTest sInstance = new AnnotationTest();

    public  void doTest(int msg){

        RSAUtils.Cert cert = RSAUtils.getCert();
        //加密后的数据
        int encodeMsg = RSAUtils.rsa(cert.n, cert.com_key, msg);
        //解密后的数据
        int decodeMsg =  RSAUtils.rsa(cert.n, cert.private_key, encodeMsg);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
