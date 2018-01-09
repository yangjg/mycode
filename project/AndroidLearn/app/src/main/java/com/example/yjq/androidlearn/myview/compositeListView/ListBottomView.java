package com.example.yjq.androidlearn.myview.compositeListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ListBottomView extends ListHeaderView {

    public ListBottomView(Context context) {
        super(context);
    }

    public ListBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListBottomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final View childView = getChildView();
        if (childView == null) {
            return;
        }

        // final int childViewWidth = childView.getMeasuredWidth();
        // final int childViewHeight = childView.getMeasuredHeight();
        // final int measuredHeight = getMeasuredHeight();
        // childView.layout(0, measuredHeight - childViewHeight, childViewWidth,
        // measuredHeight);
        final int childViewW = childView.getMeasuredWidth();
        final int childViewH = childView.getMeasuredHeight();
        childView.layout(0, 0, childViewW, childViewH);
    }

    public void setBottomHeight(int height) {
        Log.e(TAG,"setBottomHeight=="+height);
        setHeaderHeight(height);
        mListView.setSelection(mListView.getAdapter().getCount() - 1);
    }

    @Override
    public void setHeaderHeight(int height) {
        super.setHeaderHeight(height);
    }

}
