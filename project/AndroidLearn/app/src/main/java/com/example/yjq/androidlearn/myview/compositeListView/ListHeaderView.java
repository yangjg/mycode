package com.example.yjq.androidlearn.myview.compositeListView;

import android.content.Context;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;


public class ListHeaderView extends ViewGroup implements RefreshableListView.IPullUpdateUI, RefreshableListView.OnHeaderViewChangedListener {
    public static final String TAG = "ListHeaderView";

    private static final int MAX_PULL_HEIGHT_DP = 200;
    private static final int MAX_DURATION = 350;
    /**
     * 显示更新解决的时间
     */
    private static final long SHOW_UPDATE_RESULT_DELTA = 400L;

    /**
     * 默认状态
     */
    private static final int STATUS_IDLE = 0;
    /**
     * 准备刷新
     */
    private static final int STATUS_READY = 1;
    /**
     * 运行状态
     */
    private static final int STATUS_RUNN = 2;
    /**
     * 结束状态
     */
    private static final int STATUS_STOP = 3;

    /**
     * Set the height of the list header
     */
    private int mHeight;

    /**
     * The height when user release can trigger update.
     */
    private int mUpdateHeight;

    protected RefreshableListView mListView;
    private RefreshableListView.PullUpdateTask mUpdateTask;

    /**
     * The header update status control the header view
     */
    private int mStatus = STATUS_IDLE;

    /**
     * Max pull height.
     */
    private int mMaxPullHeight;
    private boolean mCanUpdate;

    private CloseTimer mCloseTimer;

    public ListHeaderView(Context context) {
        this(context, null);
    }

    public ListHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mMaxPullHeight = (int) (getResources().getDisplayMetrics().density * MAX_PULL_HEIGHT_DP + 0.5f);
    }

    public void setListView(RefreshableListView view) {
        this.mListView = view;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG,"onLayout");
        final View childView = getChildView();
        if (childView == null) {
            return;
        }

        final int childViewW = childView.getMeasuredWidth();
        final int childViewH = childView.getMeasuredHeight();
        childView.layout(0, 0, childViewW, childViewH);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG,"onMeasure");
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mHeight < 0) {
            mHeight = 0;
        }
        setMeasuredDimension(width, mHeight);

        final View childView = getChildView();
        if (childView != null) {
            childView.measure(widthMeasureSpec, heightMeasureSpec);
            mUpdateHeight = childView.getMeasuredHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG,"onDraw");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e(TAG,"dispatchDraw");
        super.dispatchDraw(canvas);
    }

    public void reset() {
        setHeaderHeight(0);
        if (mCloseTimer != null) {
            mCloseTimer.cancel();
            mCloseTimer = null;
        }
        mStatus = STATUS_IDLE;
    }

    /**
     * 开启回拉操作
     *
     * @param runnable
     */
    @Override
    public void startUpdate(RefreshableListView.PullUpdateTask task) {
        mStatus = STATUS_READY;
        this.onViewUpdating(this);

        if (mCloseTimer != null) {
            mCloseTimer.cancel();
            mCloseTimer = null;
        }

        final int fm = mHeight;
        final int to = mUpdateHeight;
        long duration = Math.abs(to - fm) * 3;
        duration = Math.min(MAX_DURATION, Math.max(100, duration));
        mUpdateTask = task;
        mCloseTimer = new CloseTimer(duration, fm, to);
        mCloseTimer.startTimer();
    }

    /**
     * 设置为正在更新
     *
     * @param task
     */
    public void setUpdate(RefreshableListView.PullUpdateTask task) {
        if (mCloseTimer != null) {
            mCloseTimer.cancel();
            mCloseTimer = null;
        }
        mStatus = STATUS_RUNN;
        task.onPullUpdateStart();
        setHeaderHeight(mUpdateHeight);
        this.onViewUpdating(this);
    }

    /**
     * 从刷新状态结束
     *
     * @param nextState
     * @return
     */
    @Override
    public void closeUpdate(RefreshableListView.PullUpdateTask task) {
        final int oldStatus = mStatus;
        mStatus = STATUS_STOP;
        mCanUpdate = false;
        mUpdateTask = task;

        /**
         * 先结束回弹
         */
        if (mCloseTimer != null) {
            mCloseTimer.cancel();
            mCloseTimer = null;
        }
        long delay = 0;
        if (oldStatus == STATUS_RUNN) {
            this.onViewUpdateFinish(this);
            delay = SHOW_UPDATE_RESULT_DELTA;
        }
        final Message msg = mHandler.obtainMessage(MSG_START_CLOSE_UPATE);
        mHandler.sendMessageDelayed(msg, delay);
    }

    private void handleStartCloseUpdate() {
        if (mStatus != STATUS_STOP && mCloseTimer == null) {
            return;
        }
        final int fm = mHeight;
        final int to = 0;
        long duration = Math.abs(to - fm) * 3;
        duration = Math.min(MAX_DURATION, Math.max(100, duration));
        mCloseTimer = new CloseTimer(duration, fm, to);
        mCloseTimer.startTimer();
    }

    private void onCloseTimerFinish() {
        if (mStatus == STATUS_READY) {
            /**
             * 切换为正在刷新刷新的状态
             */
            mStatus = STATUS_RUNN;
            if (mUpdateTask != null) {
                mUpdateTask.onPullUpdateStart();
            }
        }
        else if (mStatus == STATUS_STOP) {
            /**
             * 从完成状态切换成IDLE状态
             */
            mStatus = STATUS_IDLE;
            onViewChanged(this, false);
            if (mUpdateTask != null) {
                mUpdateTask.onPullUpdateStop();
            }
        }
    }

    public boolean isUpdateNeeded() {
        return mHeight >= mUpdateHeight;
    }

    public void moveToUpdateHeight() {
        setHeaderHeight(mUpdateHeight);
    }

    private class CloseTimer extends CountDownTimer {
        private static final int COUNT_DOWN_INTERVAL = 15;

        private final int mFmHeight;
        private final int mToHeight;

        private long mDuration;
        private long mStart;

        public CloseTimer(long millisInFuture, int fm, int to) {
            super(millisInFuture, COUNT_DOWN_INTERVAL);
            mDuration = millisInFuture;
            mFmHeight = fm;
            mToHeight = to;
        }

        public void startTimer() {
            mStart = AnimationUtils.currentAnimationTimeMillis();
            start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = AnimationUtils.currentAnimationTimeMillis() - mStart;
            millis = Math.min(mDuration, Math.max(millis, 0));
            final float time = sInterpolator.getInterpolation(millis * 1.0F / mDuration);
            final int height = (int) (mFmHeight + (mToHeight - mFmHeight) * time);
            setHeaderHeight(height);
        }

        @Override
        public void onFinish() {
            if (mCloseTimer == this) {
                mCloseTimer = null;
            }
            setHeaderHeight(mToHeight);
            onCloseTimerFinish();
        }
    }

    /**
     * Checked that there is one child in the children.
     *
     * @return
     */
    protected View getChildView() {
        final int childCount = getChildCount();
        if (childCount != 1) {
            return null;
        }
        return getChildAt(0);
    }

    @Override
    public void addView(View child) {
        final int childCount = getChildCount();
        if (childCount > 0) {
            throw new IllegalStateException("ListHeaderView can only have one child view");
        }
        super.addView(child);
    }

    public void setHeaderHeight(int height) {
        if (mHeight == height && height == 0) {
            return;
        }
        if (height > mMaxPullHeight) {
            return;
        }
        final int updateHeight = mUpdateHeight;
        mHeight = height;
        if (mStatus == STATUS_IDLE) {
            if ((height < updateHeight) && mCanUpdate) {
                this.onViewChanged(this, false);
                mCanUpdate = false;
            }
            else if ((height >= updateHeight) && !mCanUpdate) {
                this.onViewChanged(this, true);
                mCanUpdate = true;
            }
        }
        requestLayout();
    }

    public void setAddHeight(int step){
        mHeight +=step;
        if(mHeight<0)mHeight =0;
        requestLayout();
    }

    @Override
    public void onViewChanged(View v, boolean canUpdate) {
    }

    @Override
    public void onViewUpdating(View v) {
    }

    @Override
    public void onViewUpdateFinish(View v) {
    }

    /**
     * Interpolator
     */
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t + 1.0f;
        }
    };

    private static final int MSG_START_CLOSE_UPATE = 0x0000;

    /**
     * 主消息循环
     */
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_START_CLOSE_UPATE: {
                    handleStartCloseUpdate();
                    break;
                }
                default: {
                    super.handleMessage(msg);
                    break;
                }
            }
        }
    };

}