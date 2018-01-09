package com.example.yjq.androidlearn.myview.compositeListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;


public class ListHeaderViewImpl extends ListHeaderView {

    private TextView mTitle;
    private NewsLoadingView mLoading;
    private RelativeLayout mRoot;
    private int mResultColor;

    public ListHeaderViewImpl(Context context) {
        super(context);
    }

    public ListHeaderViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListHeaderViewImpl(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRoot = Views.findViewById(this, R.id.root);
        this.mTitle = Views.findViewById(this, R.id.refresh_text);
        this.mLoading = Views.findViewById(this, R.id.refresh_loading);
    }


    @Override
    public void onViewChanged(View view, boolean canUpdate) {
        if (canUpdate) {
            mTitle.setText(R.string.refresh_release);
        }
        else {
            mTitle.setText(R.string.refresh_pull_down);
        }
        setTitleVisible();
    }

    /**
     * 正在更新
     */
    @Override
    public void onViewUpdating(View view) {
        mTitle.setText(R.string.loading);
        setTitleInvisible();
    }

    @Override
    public void onViewUpdateFinish(View view) {
        CharSequence text = null;

            text = getResources().getString(R.string.refresh_pull_down);

        mTitle.setText(text);
        setTitleVisible();
    }

    private void setTitleVisible() {
        mTitle.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.INVISIBLE);
    }

    private void setTitleInvisible() {
        mTitle.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }


    @Override
    public void setHeaderHeight(int height) {
        Log.e(TAG, "setHeaderHeight==" + height);
        super.setHeaderHeight(height);
    }
}
