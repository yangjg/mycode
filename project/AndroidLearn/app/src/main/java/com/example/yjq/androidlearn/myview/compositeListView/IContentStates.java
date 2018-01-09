package com.example.yjq.androidlearn.myview.compositeListView;

import android.support.v4.view.ViewPager;

public interface IContentStates {
    /**
     * 信息流界面能够处于的三种状态
     */
    public static final int HOME_STATE_HOME = 0x0000;
    public static final int HOME_STATE_MOVE = 0x0001;
    public static final int HOME_STATE_NEWS = 0x0002;

    /**
     * VIEW PAGER的三种状态
     */
    public static final int PAGE_STATE_IDLE = ViewPager.SCROLL_STATE_IDLE;
    public static final int PAGE_STATE_DRAGGING = ViewPager.SCROLL_STATE_DRAGGING;
    public static final int PAGE_STATE_SETTING = ViewPager.SCROLL_STATE_SETTLING;

    /**
     * NewsContentAdapter处于的三种状态
     */
    public static final int STATE_HIDE = 0x0000; // 隐藏在后面
    public static final int STATE_SHOW = 0x0001; // 移动在显示
    public static final int STATE_CURR = 0x0002; // 当前被选中

    public static final int UI_NEWS_UPDATE_NONE = 0x0000;
    public static final int UI_NEWS_UPDATE_HEAD = 0x0001;
    public static final int UI_NEWS_UPDATE_TAIL = 0x0002;
}
