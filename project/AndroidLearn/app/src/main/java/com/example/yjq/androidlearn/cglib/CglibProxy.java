package com.example.yjq.androidlearn.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yangjingan on 2016/9/21.
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer =  new Enhancer(); // 这个目前初始化有问题，估计是jar包和jdk或者sdk中的Method类适配不好

    public Object getProxy(Class clazz,ClassLoader loader){
        enhancer.setClassLoader(loader);
        enhancer.setSuperclass(clazz);//设置需要创建子类的类，就是超类;
        enhancer.setCallback(this);
        return  enhancer.create(); //通过字节码技术动态创建子类的实例
    }

    //拦截超类的所有方法调用
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
       PerformanceMonitor.begin(objects.getClass().getName()+"."+method.getName());
        Object result = methodProxy.invokeSuper(o,objects);
        PerformanceMonitor.end();
        return result;
    }
}
