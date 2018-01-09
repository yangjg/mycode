package com.example.yjq.androidlearn.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangjingan on 2016/9/21.
 */
public class DProxy {

   public static class PerformaceHandler implements InvocationHandler{

       private  Object mTarget;
       public PerformaceHandler(Object target){
           mTarget = target;
       }

       @Override
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

           PerformanceMonitor.begin(mTarget.getClass().getName()+"."+method.getName());
           Object res= method.invoke(mTarget,args);
           PerformanceMonitor.end();
           return res;
       }
   }
}
