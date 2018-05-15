package com.example.yangjingan.myapplication.Popup;
import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 18-4-3.
 */

public class FullScreenDialogManager {


    private Activity mActivity;
    Dialog mDialog;
    FrameLayout root;

    public FullScreenDialogManager(Activity activity){
        mActivity = activity ;
        init();
    }


    private void init(){
        mDialog = new Dialog(mActivity,R.style.MyDialog);
        root = (FrameLayout)LayoutInflater.from(mActivity).inflate(R.layout.popup_root,null);
        mDialog.setContentView(root);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height =  WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    public void setView(View view){
        setView(view,Gravity.CENTER);
    }

    public void setView(View view,int gravity){
        root.removeAllViews();
        root.addView(view);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = gravity;
    }

    public ViewGroup getRoot(){
        return root;
    }

    public void show(){
        mDialog.show();
    }

    public void dismiss(){
        mDialog.dismiss();
    }

    public void destroy(){
        mDialog.dismiss();
        root.removeAllViews();
        mActivity = null ;
        mDialog = null ;
    }

}
