package com.example.yjq.androidlearn;

import android.app.Activity;
import android.view.View;

/**
 * Created by yjq on 2016/4/11.
 */
public class Views {

    public static <T> T findViewById(View view,int id){
        return  (T)view.findViewById(id);
    }

    public static <T> T findViewById(Activity view,int id){
        return  (T)view.findViewById(id);
    }
}
