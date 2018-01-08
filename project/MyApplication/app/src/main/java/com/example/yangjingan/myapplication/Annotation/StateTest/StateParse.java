package com.example.yangjingan.myapplication.Annotation.StateTest;

import com.example.yangjingan.myapplication.Annotation.StateTest.State.CanState;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.ItemObject;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Key;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Page;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjingan on 17-10-17.
 */

public class StateParse {

    public String mPage ;
    public BaseItemData mData;
    public StateParse(BaseItemData data){
        mData = data ;
    }

    public Map<String,String> getStateInfo(){
        Map<String,String> map = new HashMap<>();
        //TODO 分析mData的所有注解，可能会递归注解，最后通过注解和item生出map
        if(checkItemCanState()){
           map = analyseData(mData);
        }
        return map;
    }

    private boolean checkItemCanState(){
        boolean mCanState = false ;
        Class<?> cls = mData.getClass();
        Annotation[] annotations =  cls.getAnnotations();
        if(null != annotations){
            int count = annotations.length ;
            for (int i = 0 ;i<count ;i++){
                if(annotations[i] instanceof CanState){
                    //如果有这个注解标识 这个类能有统计。
                    mCanState = true ;
                    break;
                }
            }
        }
        return  mCanState ;
    }

    private Map<String,String> analyseData(Object object){
        Class<?> cls = object.getClass();
        Field[] fields =  cls.getFields();
        int size =  fields.length ;
        Map<String ,String> map = new HashMap<>();
        for (int i =0;i<size;i++){
            Field fd = fields[i];
            Annotation[] fannots = fd.getAnnotations();
            try {
                int flen = fannots.length ;
                for (int j =0 ;j< flen ;j++){
                    Annotation an = fannots[j];
                    if(an instanceof Key){
                        String item = String.valueOf(fd.get(object));
                        Key key = (Key)an;
                        map.put(key.value(),item);
                    }
                    else if(an instanceof Page){
                        mPage = ((Page) an).value();
                    }
                    else if(an instanceof ItemObject){
                        Map<String,String> tempMap;
                        Object  concrete= fd.get(object);
                        tempMap = analyseData(concrete);
                        map.putAll(tempMap);
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  map ;
    }
}
