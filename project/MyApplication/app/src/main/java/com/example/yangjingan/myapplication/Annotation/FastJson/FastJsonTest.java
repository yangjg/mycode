package com.example.yangjingan.myapplication.Annotation.FastJson;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.*;
import com.example.yangjingan.myapplication.Annotation.StateTest.AppItem;
import com.example.yangjingan.myapplication.Annotation.StateTest.MetaItem;
import com.example.yangjingan.myapplication.Annotation.StateTest.TestItem;
import com.mz.statistic.tools.StatisticDataParseTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjingan on 17-10-26.
 */

public class FastJsonTest {

    public static void test(){
      /*  AppItem1 appItem1 = new AppItem1();
       *//* appItem1.classtype = AppItem.class ;
        appItem1.extras = new Bundle();
        appItem1.extras.putString("ok","app");*//*
        Object object = JSON.toJSON(appItem1);
        String json = object.toString();
       Map it = new HashMap<Integer,Integer>();
        it.put(1,2);
        tt("fe",it);
       AppItem2 item2 =  JSON.parseObject(json,AppItem2.class);
        StringBuilder sb = new StringBuilder();
        sb.append("AppItem2 filed:");
        sb.append("\n");
        sb.append("action:");
        sb.append(item2.acion);
        sb.append("\n");
        sb.append("value:");
         sb.append(item2.value);
        sb.append("\n");
        if(null != item2.classtype){
            sb.append("classType:");
            sb.append(item2.classtype.getName());
            sb.append("\n");
        }
        if(null != item2.extras){
            sb.append("ok:");
            sb.append(item2.extras.getString("ok"));
            sb.append("\n");
        }


        Log.e("Fast",sb.toString());*/

        TestItem testItem = new TestItem("yjg","few","llll");
        testItem.Id =5;
        testItem.metaItem = new MetaItem(20,"EWF");
       Map<String,String> res =  StatisticDataParseTool.parseDataToMap(testItem);
        if(null != res && !res.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("Begin res all filed:");
            sb.append("\n");
            for (String key:res.keySet()){
                sb.append(key);
                sb.append(":");
                sb.append(res.get(key));
                sb.append("\n");
            }
            sb.append("END");
            sb.append("\n");
            Log.e("Fast",sb.toString());
        }

    }

    private static void tt(String action, Map<String,String> map){
        if(null != action){
            try {
                Map<String,String> temp = new HashMap<>();
                temp.putAll(map);
                Map<String,String> res = new HashMap<>();
                for (String key:temp.keySet()){
                    String value = temp.get(key);
                    res.put(key,value);
                }
            }
            catch (Exception e){
                Log.e("TEST",e.getMessage());
            }

           // int size = map.size();
        }
    }
}
