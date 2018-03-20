package com.example.yangjingan.myapplication.Popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 18-3-19.
 */

public class CustomPopupWindow extends PopupWindow {

    private ViewGroup mPopView;
    private View mCustomView;
    public CustomPopupWindow(Context context){
        super(context);
        init(context);
        setPopupWindow();
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = (ViewGroup) inflater.inflate(R.layout.popup_root, null);
    }

    private void setPopupWindow(){
        this.setContentView(mPopView);// 设置View
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        mPopView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
               /* int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }*/
                return true;
            }
        });
    }

    public void setCustomView(View customView){
        //;
        View old = mCustomView ;
        mCustomView = customView ;
        if(null!=old && old.getParent() == mPopView){
            mPopView.removeView(old);
        }
        mPopView.addView(mCustomView);
    }

    public void setCustomView(int layoutId){
        LayoutInflater inflater = LayoutInflater.from(mPopView.getContext());
        View view = inflater.inflate(layoutId,mPopView,false);
        setCustomView(view);
    }

}
