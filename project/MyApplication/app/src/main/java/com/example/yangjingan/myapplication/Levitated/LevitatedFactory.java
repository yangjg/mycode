package com.example.yangjingan.myapplication.Levitated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 17-12-4.
 */

public class LevitatedFactory {



    public static LevitatedContainer makeLevitated(Context context, LevitatedInfo levitatedInfo){

        LevitatedContainer container = new LevitatedContainer(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(levitatedInfo.with,levitatedInfo.height);
        params.gravity = levitatedInfo.gravity ;
        params.leftMargin = levitatedInfo.left_margin ;
        params.rightMargin = levitatedInfo.right_margin ;
        params.topMargin = levitatedInfo.top_margin ;
        params.bottomMargin = levitatedInfo.bottom_margin ;
        container.setLayoutParams(params);

        View  child = LayoutInflater.from(context).inflate(R.layout.levitated_view1,container);
        if(levitatedInfo.hasColose){
             View close = Views.findViewById(child,R.id.close);
            close.setVisibility(View.VISIBLE);
        }

      //  container.setLayoutParams(new ViewGroup.LayoutParams());
        return  container ;
    }




    public static class LevitatedInfo{
        public int gravity;//0 RIGHT_BOTTON ,1 LEFT_BOTTON,2 LEFT_TOP,3 RIGHT_TOP;
        public int left_margin;//距离底部或者顶部的margin，根据gravity而定
        public int right_margin;//距离左边或者右边的margin，根据gravity而定
        public int top_margin;
        public int bottom_margin;
        public int with;
        public int height;
        public boolean hasColose;


    }
}
