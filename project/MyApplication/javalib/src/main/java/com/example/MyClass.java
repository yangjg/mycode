package com.example;

public class MyClass {


    private static final String DIR="/home/yangjingan/work/temp/test/tool/"; //这个是你要分析的文件的文件目录
    private static String fileName = "browserlog.txt";
    private static String  newFileName = "rr_"+fileName; //这个是分析文件后生产的新文件名字
    private static final String StartFlag = "at java.lang.Class.getDeclaredMethodInternal!(Native method)"; //"Stack start";
    private static final String EndFlag = "at com.android.browser.k.o$d$1.run(RequestQueue.java:275)"; //"Stack end";
    private static final String SPLITE= "( 8597):";//"Perf_IO :";

    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("您调用main方法时没有指定任何参数,请传入四个参数，第一个是文件的全路径名字，第二个是StartFlag，第三个是EndFlag，第四个解析行的分割字符串");
            return;
        }
        if(args.length != 4){
            System.out.println("您调用main方法指的参数有误,请传入四个参数，第一个是文件的全路径名字，第二个是StartFlag，第三个是EndFlag，第四个解析行的分割字符串");
        }
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                 ParseFile.getInStance().processFile(args[0],args[0]+"r_r",args[1],args[2],args[3]);
            }
        });
        th.start();
        /*Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ParseFile.getInStance().processFile(DIR+fileName,DIR+newFileName,StartFlag,EndFlag,SPLITE);
            }
        });
        th.start();*/
    }
}
