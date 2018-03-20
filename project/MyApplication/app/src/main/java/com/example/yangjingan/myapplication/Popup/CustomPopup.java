package com.example.yangjingan.myapplication.Popup;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

import java.util.zip.Inflater;

/**
 * Created by yangjingan on 18-3-19.
 */

public class CustomPopup {


    public ViewGroup root;

    public ViewGroup mContainer;

    public View mChildView;

    private boolean mShow =false ;

    private Drawable backColor;

    public CustomPopup(Activity activity){
        root = (ViewGroup) activity.getWindow().getDecorView(); // Views.findViewById(activity,android.R.id.content);
        init(root);
    }

    public CustomPopup(ViewGroup container){
        root = container ;
        init(root);
    }

    private void init(ViewGroup container){
        mContainer = new FrameLayout(container.getContext());
        root.addView(mContainer,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        backColor = container.getBackground();
    }


    public void setCustomView(View customView,ViewGroup.LayoutParams params){
        View old = mChildView ;
        mChildView = customView;
        if(null != old && old.getParent()==mContainer){
            mContainer.removeView(old);
        }
        mChildView.setVisibility(View.GONE);
     //   mContainer.addView(mChildView);
        mContainer.addView(mChildView,params);
    }

    public void setCustomLayout(int layoutId, ViewGroup.LayoutParams params){
        LayoutInflater inflater = LayoutInflater.from(mContainer.getContext());
        View view = inflater.inflate(layoutId,mContainer,false);
        setCustomView(view,params);
    }

    public void show(){
        if(!mShow && null !=mChildView){
            mShow = true ;
            mChildView.setVisibility(View.VISIBLE);
            mContainer.setBackgroundColor( mContainer.getResources().getColor(R.color.half_transparent));
        }

    }

    public void hide(){
        if(mShow && null != mChildView){
            mShow = false ;
            mChildView.setVisibility(View.GONE);
            mContainer.setBackground(backColor);
        }
    }

    public void destroy(){
        if(null != mChildView && mChildView.getParent() == mContainer){
            mContainer.removeView(mChildView);
        }
        mChildView = null ;
        if(mContainer.getParent() == root){
            root.removeView(mContainer);
        }

    }

    public ViewGroup getContainer(){
        return  mContainer ;
    }
}
