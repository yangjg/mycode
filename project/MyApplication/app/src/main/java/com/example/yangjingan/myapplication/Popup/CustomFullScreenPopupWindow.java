package com.example.yangjingan.myapplication.Popup;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 18-3-19.
 */

public class CustomFullScreenPopupWindow {


    Context mContext;
    PopupWindow mPopupWindow;
    View mLocationView;
    FrameLayout mRootView;
    View mCustomView;

    public CustomFullScreenPopupWindow(Activity activity) {
        mContext = activity.getApplicationContext();
        mLocationView = activity.getWindow().getDecorView();
        init(mContext);
    }

    private void init(Context context) {
        mRootView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.custom_pop_root, null);
        mPopupWindow = new PopupWindow(mRootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void setView(View view) {
        View old = mCustomView;
        mCustomView = view;
        if (null != old && old.getParent() == mRootView) {
            mRootView.removeView(old);
        }
        if (null != view) {
            mRootView.addView(view);
        }
    }

    public ViewGroup getRootView(){
        return mRootView;
    }

    public void setView(int layoutId) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, mRootView,false);
        setView(view);
    }


    public void show() {
        show(mLocationView, Gravity.TOP | Gravity.LEFT, 0, 0);
    }

    public void show(View locationView, int gravity, int x, int y) {
        mPopupWindow.showAtLocation(locationView, gravity, x, y);
    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }


    public void destroy() {
        setView(null);
        mRootView = null;
        mPopupWindow = null;
        mLocationView = null;
        mContext = null;
    }


}
