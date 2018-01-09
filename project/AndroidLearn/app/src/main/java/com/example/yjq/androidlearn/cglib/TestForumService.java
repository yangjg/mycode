package com.example.yjq.androidlearn.cglib;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by yangjingan on 2016/9/21.
 */
public class TestForumService {
    public    void runTest(){

  /*      CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService =
        (ForumServiceImpl )proxy.getProxy(ForumServiceImpl.class, proxy.getClass().getClassLoader());
        forumService.removeForum(10);
        forumService.removeTopic(1023);*/
        /*ForumService service = new ForumServiceImpl();
        DProxy.PerformaceHandler handler = new DProxy.PerformaceHandler(service);

        ForumService ds = (ForumService) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),handler);
        ds.removeForum(10);
        ds.removeTopic(1048);*/

       /* Class clazz = ForumServiceImpl.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods){
            NeedTest nt = method.getAnnotation(NeedTest.class);
            if(null!=nt){
                if(nt.value()){
                    System.out.println(method.getName() + "()需要测试");
                }
                else{
                    System.out.println(method.getName() + "()不需要测试");
                }
            }
        }*/

        localeTest();
    }

    private  void localeTest(){
        Locale locale =  new Locale("zh","CN");
        Locale en = new Locale("en","US");

        numberFormatTest(locale);
        numberFormatTest(en);

        dateFormatTest(locale);
        dateFormatTest(en);

        messageFormatTest(locale);
        messageFormatTest(en);
    }

    private  void numberFormatTest(Locale locale){
        NumberFormat cur = NumberFormat.getCurrencyInstance(locale);
        double amt =123456.78;
        System.out.println(cur.format(amt));
    }

    private void dateFormatTest(Locale locale){
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,locale);
        System.out.println(df.format(date));
    }

    private void messageFormatTest(Locale locale){
       // String pattern1 ="{0},hello,you are at {1} save in {2} china bank";
        String pattern1 = "At {1,time,short} On{1,date,long}，{0} paid {2,number, currency}.";


        //②用于动态替换占位符的参数
        Object[] params = {"John", new GregorianCalendar().getTime(),1.0E3};

        String msg1 = MessageFormat.format(pattern1,params);

        MessageFormat mf = new MessageFormat(pattern1,locale);
        String msg2 = mf.format(params);


        System.out.println(msg1);
        System.out.println(msg2);

    }
}
