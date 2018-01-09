package com.example.yjq.androidlearn.myview.compositeListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

public class ListBottomViewImpl extends ListBottomView  {
    public static final String TAG = "ListBottomViewImpl";
    private int mResultColor;
    private TextView mTitle;
    private NewsLoadingView mLoading;
    private RelativeLayout mRoot;

    public ListBottomViewImpl(Context context) {
        super(context);
    }

    public ListBottomViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListBottomViewImpl(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRoot = Views.findViewById(this, R.id.root);
        this.mTitle = Views.findViewById(this, R.id.refresh_text);
        this.mLoading = Views.findViewById(this, R.id.refresh_loading);
        updateFromThemeMode();
    }

    public void updateFromThemeMode() {
        mRoot.setBackgroundResource(R.color.iflow_news_reload_root_background_color_default);
        mTitle.setTextColor(getResources().getColor(R.color.secondary_text));
        mResultColor = getResources().getColor(R.color.iflow_list_view__count_color_default);
    }


    @Override
    public void onViewChanged(View view, boolean canUpdate) {
        if (canUpdate) {
            mTitle.setText(R.string.refresh_release);
        }
        else {
            mTitle.setText(R.string.refresh_pull_up);
        }
        setTitleVisible();
    }

    @Override
    public void onViewUpdating(View view) {
        mTitle.setText(R.string.loading);
        setTitleInvisible();
    }

    @Override
    public void onViewUpdateFinish(View view) {
        CharSequence text = null;
        text ="正在刷新";
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

}
