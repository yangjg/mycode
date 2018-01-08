package com.example.yangjingan.myapplication.Annotation.StateTest;

import android.util.Log;

import com.example.yangjingan.myapplication.Annotation.FastJson.FastJsonTest;

import java.util.Map;

/**
 * Created by yangjingan on 17-10-17.
 */

public class TestState {
    private TestState(){

    }

    static  TestState sInstance ;

    public static TestState getInstance(){
        if(null == sInstance){
            sInstance = new TestState();
        }
        return sInstance ;
    }

    public void doTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        }).start();
    }


    private void doWork(){
        FastJsonTest.test();
      /*  ConcreteItemData itemData = new ConcreteItemData();
        itemData.appItem = new AppItem("yjg","state","yjgok");
        itemData.mTag ="tag";
        itemData.page ="page1";
        itemData.appItem.metaItem = new MetaItem(1,"doaction");
        StateParse parse = new StateParse(itemData);
        Map<String,String> map =  parse.getStateInfo() ;
        StringBuilder sb = new StringBuilder();
        sb.append("map result \n");
        if(null != map && !map.isEmpty()){
            for (String key:map.keySet()){
                String item = map.get(key);
                sb.append(key);
                sb.append(":");
                sb.append(item);
                sb.append("\n");
            }
        }
        Log.e("State",sb.toString());*/
    }

}
