package com.example.yjq.androidlearn.myview.compositeListView;

import android.app.IntentService;
import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

import com.example.yjq.androidlearn.R;

public class RefreshableListView extends ListView {
    public static final String TAG = "RefreshableListView";

    /**
     * 默认状态
     */
    private static final int STATE_NORMAL = 0;
    private static final int STATE_READY = 1;
    private static final int STATE_PULL = 2;
    private static final int STATE_UPDATING = 3;
    private static final int INVALID_POINTER_ID = -1;

    private static final int UP_STATE_READY = 4;
    private static final int UP_STATE_PULL = 5;

    private static final int PULL_STATE_NONE = 0x0000; // 默认状态
    private static final int PULL_STATE_STARTED = 0x0001; // 启动下拉
    private static final int PULL_STATE_RUNNING = 0x0002; // 后台任务
    private static final int PULL_STATE_STOPPED = 0x0003; // 任务结束
    private static final int PULL_STATE_FINISH = 0x0004; // 结束状体
    public static final int PULL_SLOP = 5;// 临界值

    protected ListHeaderView mListHeaderView;
    protected ListBottomView mListBottomView;
    private PullUpdateTask mPullHeaderTask;
    private PullUpdateTask mPullBottomTask;

    private int mActivePointerId;
    private PointF mLastPoint = new PointF();
    private int mState;

    private boolean mIsPullTailEnabled = true;
    private int mTouchSlop;

    public RefreshableListView(Context context) {
        this(context, null);
    }

    public RefreshableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    /**
     * Set the bootom content view. and open this feature.
     *
     * @param id
     */
    public void setBottomContentView(int id) {
        mIsPullTailEnabled = true;
        final View view = LayoutInflater.from(getContext()).inflate(id, mListBottomView, false);
        mListBottomView.addView(view);
        addFooterView(mListBottomView, null, false);
    }

    public void setBottomContentView(View v) {
        mListBottomView.addView(v);
    }

    public void setContentView(View v) {
        mListHeaderView.addView(v);
    }

    public ListHeaderView getListHeaderView() {
        return mListHeaderView;
    }

    public void setPullTailEnabled(boolean isEnabled) {
        this.mIsPullTailEnabled = isEnabled;
    }

    /**
     * 自动刷新的入口
     */
    public void startHeadUpdateImmediate() {
        if (mState == STATE_UPDATING) {
            return;
        }
        setSelectionFromTop(0, 0);
        pullHeaderUpdate(true);
    }

    public void startTailUpdateImmediate() {
        if (mState == STATE_UPDATING) {
            return;
        }
        pullBottomUpdate(true);
    }

    /**
     * 恢复成正常状态
     */
    public void reset() {
        if (mPullHeaderTask != null) {
            mPullHeaderTask.cancel();
            mPullHeaderTask = null;
        }
        if (mPullBottomTask != null) {
            mPullBottomTask.cancel();
            mPullBottomTask = null;
        }
        if (mListHeaderView != null) {
            mListHeaderView.reset();
        }
        if (mListBottomView != null) {
            mListBottomView.reset();
        }
        mState = STATE_NORMAL;
    }

    private void initialize() {
        final Context context = getContext();
        mListHeaderView = onInitHeaderView(context);
        mListHeaderView.setListView(this);
        mListBottomView = onInitBottomView(context);
        mListBottomView.setListView(this);
        addHeaderView(mListHeaderView);
        addFooterView(mListBottomView);
        mIsPullTailEnabled = true;

        setHeaderDividersEnabled(false);
        setFooterDividersEnabled(false);
        mState = STATE_NORMAL;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    protected ListHeaderView onInitHeaderView(Context context) {
        return (ListHeaderViewImpl) inflate(context, R.layout.list_header_view_impl, null);
    }

    protected ListBottomView onInitBottomView(Context context) {
        return ((ListBottomViewImpl) inflate(context, R.layout.list_bottom_view_impl, null));
        //ListBottomView(context);
    }

    private void pullHeaderUpdate(boolean isForced) {
        if (mPullHeaderTask != null) {
            mPullHeaderTask.cancel();
        }
        mPullHeaderTask = new PullUpdateTask(mListHeaderView, true);
        mPullHeaderTask.mPullState = PULL_STATE_STARTED;
        if (isForced || mListHeaderView.isUpdateNeeded()) {
            mState = STATE_UPDATING;
            mListHeaderView.startUpdate(mPullHeaderTask);
        } else {
            mListHeaderView.closeUpdate(mPullHeaderTask);
        }
    }

    private void pullBottomUpdate(boolean isForced) {
        if (mPullBottomTask != null) {
            mPullBottomTask.cancel();
        }
        mPullBottomTask = new PullUpdateTask(mListBottomView, false);
        if (mListBottomView.isUpdateNeeded() || isForced) {
            mState = STATE_UPDATING;
            mListBottomView.startUpdate(mPullBottomTask);
        } else {
            mListBottomView.closeUpdate(mPullBottomTask);
        }
    }

    public void onPullHeaderTaskFinish() {
        if (mPullHeaderTask != null && mPullHeaderTask.mPullState == PULL_STATE_RUNNING) {
            mPullHeaderTask.onPullTaskFinish();
        }
    }

    public void onPullBottomTaskFinish() {
        if (mPullBottomTask != null && mPullBottomTask.mPullState == PULL_STATE_RUNNING) {
            mPullBottomTask.onPullTaskFinish();
        }
    }

    /**
     * 恢复状态
     *
     * @param isUpdating
     * @param isHeader
     */
    public void restoreState(boolean isUpdating, boolean isHeader) {
        restoreHeaderUpdateState(isUpdating && isHeader);
        restoreBottomUpdateState(isUpdating && !isHeader);
        if (!isUpdating) {
            mState = STATE_NORMAL;
        }
    }

    /**
     * Get the current list view's UI state.
     *
     * @return
     * @see IContentStates#UI_NEWS_UPDATE_NONE
     * @see IContentStates#UI_NEWS_UPDATE_HEAD
     * @see IContentStates#UI_NEWS_UPDATE_TAIL
     */
    public int getCurrentUIState() {
        if (mState != STATE_UPDATING) {
            return IContentStates.UI_NEWS_UPDATE_NONE;
        } else if (mPullHeaderTask != null) {
            return IContentStates.UI_NEWS_UPDATE_HEAD;
        } else {
            return IContentStates.UI_NEWS_UPDATE_TAIL;
        }
    }

    public void setStateFromUIStatus(int state) {
        boolean isUpdate = state != IContentStates.UI_NEWS_UPDATE_NONE;
        boolean isHeader = state == IContentStates.UI_NEWS_UPDATE_HEAD;
        restoreState(isUpdate, isHeader);
    }

    private void restoreHeaderUpdateState(boolean isUpdatingHeader) {
        if (isUpdatingHeader) {
            mState = STATE_UPDATING;
            mPullHeaderTask = new PullUpdateTask(mListHeaderView, true);
            mListHeaderView.setUpdate(mPullHeaderTask);
        } else {
            if (mPullHeaderTask != null) {
                mPullHeaderTask.cancel();
                mPullHeaderTask = null;
            }
            mListHeaderView.reset();
        }
    }

    private void restoreBottomUpdateState(boolean isUpdatingBottom) {
        if (isUpdatingBottom) {
            mState = STATE_UPDATING;
            mPullBottomTask = new PullUpdateTask(mListBottomView, false);
            mListBottomView.setUpdate(mPullBottomTask);
        } else {
            if (mPullBottomTask != null) {
                mPullBottomTask.cancel();
                mPullBottomTask = null;
            }
            mListBottomView.reset();
        }
    }

    int hh=0;
    int bh=0;
    boolean isFirstTouch =true;

    public void setHeader(){
       if(hh>550){
           hh=0;
       }
        hh +=25;
        setHeaderHeight(hh);
        setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }

    private void resetHeader(){
        setHeaderHeight(0);
    }

    float lastY;
    float lastX;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final  int action = ev.getAction();
        float y = ev.getY();
        float x =ev.getX();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"ACTION_DOWN");
                 lastY = y;
                lastX =x;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x-lastX;
                float dy =y-lastY;
                if(Math.abs(dy)>2){
                    lastX =x;
                    lastY =y;
                    setAddByHeight((int) (dy * 5 / 9));
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"ACTION_UP");
                resetHeader();
                break;
            default:
                Log.e(TAG,"DEFAULT");
                resetHeader();
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
       /* mState = STATE_UPDATING;
        if (mState == STATE_UPDATING) {
            super.dispatchTouchEvent(ev);
            return  true;
            //return super.dispatchTouchEvent(ev);
        }
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mActivePointerId = ev.getPointerId(0);
                mLastPoint.set(ev.getX(), ev.getY());
                isFirstViewTop();
                isLastViewBottom();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (mActivePointerId == INVALID_POINTER_ID) {
                    break;
                }
                if (mState == STATE_NORMAL) {
                    isFirstViewTop();
                    isLastViewBottom();
                }
                if (mState == STATE_READY) {
                    final float y = ev.getY();
                    final int deltaY = (int) (y - mLastPoint.y);
                    final int deltaX = (int) (ev.getX() - mLastPoint.x);
                    mLastPoint.set(ev.getX(), y);
                    // 向下滑动且Y方向变化量大于X方向变化量才认为是下拉刷新
                    boolean isPull = deltaY > 0 && Math.abs(deltaY) > mTouchSlop
                            && Math.abs(deltaY) > Math.abs(deltaX);
                    if (!isPull) {
                        mState = STATE_NORMAL;
                    } else {
                        mState = STATE_PULL;
                        // ev.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(ev);
                    }
                } else if (mState == UP_STATE_READY) {
                    final float y = ev.getY();
                    final int deltaY = (int) (y - mLastPoint.y);
                    mLastPoint.set(ev.getX(), y);
                    boolean isUPPull = deltaY < 0 && Math.abs(deltaY) > mTouchSlop;
                    if (!isUPPull) {
                        mState = STATE_NORMAL;
                    } else {
                        mState = UP_STATE_PULL;
                        // ev.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(ev);
                    }
                }

                if (mState == STATE_PULL) {

                *//*     * 更新mListHeaderView的高度*//*

                    final float y = ev.getY();
                    final int deltaY = (int) (y - mLastPoint.y);
                    mLastPoint.set(ev.getX(), y);
                    final int headerHeight = mListHeaderView.getHeight();
                    setHeaderHeight(headerHeight + deltaY * 5 / 9);
                    return true;
                } else if (mState == UP_STATE_PULL) {
                   *//* *
                     * 更新mListBottomView的高度*//*

                    final float y = ev.getY();
                    final int deltaY = (int) (y - mLastPoint.y);
                    mLastPoint.set(ev.getX(), y);
                    final int headerHeight = mListBottomView.getHeight();
                    setBottomHeight(headerHeight - deltaY * 5 / 9);
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                if (mState == STATE_PULL) {
                    pullHeaderUpdate(false);
                } else if (mState == UP_STATE_PULL) {
                    pullBottomUpdate(false);
                }
                break;
            }
        }
        boolean res = super.dispatchTouchEvent(ev);
        Log.e(TAG,"dispatchTouchEvent=="+res);
        return  res;*/
       // return super.dispatchTouchEvent(ev);
    }

    void setState(int state) {
        mState = state;
    }

    private void setHeaderHeight(int height) {
        mListHeaderView.setHeaderHeight(height);
    }

    private void setBottomHeight(int height) {
        mListBottomView.setBottomHeight(height);
    }

    private void setAddByHeight(int step){
        mListHeaderView.setAddHeight(step);
    }

    /**
     * Convert mState to {@link #UP_STATE_READY}
     *
     * @return
     */
    private boolean isLastViewBottom() {
        final int count = getChildCount();
        if (count == 0 || !mIsPullTailEnabled) {
            return false;
        }

        final int lastVisiblePosition = getLastVisiblePosition();
        boolean needs = (lastVisiblePosition == (getAdapter().getCount() - getHeaderViewsCount()))
                && (getChildAt(getChildCount() - 1).getBottom() == (getBottom() - getTop()));
        if (needs) {
            mState = UP_STATE_READY;
        }
        return needs;
    }

    /**
     * Convert mState to {@link #STATE_READY} with check state.
     *
     * @return
     */
    private boolean isFirstViewTop() {
        final int count = getChildCount();
        if (count == 0) {
            return true;
        }
        final int position = this.getFirstVisiblePosition();
        final View view = getChildAt(0);
        boolean needs = view.getTop() == 0 && (position == 0);
        if (needs) {
            mState = STATE_READY;
        }
        return needs;
    }

    /**
     * When use custom List header view
     */
    public static interface OnHeaderViewChangedListener {
        /**
         * 在拖拽的过程中canUpdate变化的时候修改相应的UI和动画
         *
         * @param v
         * @param canUpdate
         */
        public void onViewChanged(View v, boolean canUpdate);

        /**
         * 更新开始的回调接口
         *
         * @param v the list view header
         */
        public void onViewUpdating(View v);

        /**
         * 更新完成
         *
         * @param v
         */
        public void onViewUpdateFinish(View v);
    }

    public final class PullUpdateTask {

        final boolean mIsFromHead;
        int mPullState;
        private volatile boolean mIsCancelled;
        private final IPullUpdateUI mPullUi;

        public PullUpdateTask(IPullUpdateUI ui, boolean isHead) {
            super();
            this.mIsCancelled = false;
            this.mIsFromHead = isHead;
            mPullUi = ui;
            mPullState = PULL_STATE_NONE;
        }

        /**
         * 请求启动后台更新任务
         */
        public void onPullUpdateStart() {
            mPullState = PULL_STATE_RUNNING;
            if (mIsFromHead) {
                onPullHeadRequest();
            } else {
                onPullFootRequest();
            }

            onPullTaskFinish();
        }

        public void onPullTaskFinish() {
            mPullState = PULL_STATE_STOPPED;
            mPullUi.closeUpdate(this);
        }

        public void onPullUpdateStop() {
            if (!mIsCancelled) {
                mState = STATE_NORMAL;
                this.mPullState = PULL_STATE_FINISH;
                if (mPullHeaderTask == this) {
                    mPullHeaderTask = null;
                } else if (mPullBottomTask == this) {
                    mPullBottomTask = null;
                }
            }
        }

        public void cancel() {
            this.mIsCancelled = true;
        }
    }

    protected void onPullHeadRequest() {
    }

    protected void onPullFootRequest() {
    }

    public interface IPullUpdateUI {
        public void startUpdate(PullUpdateTask task);

        public void closeUpdate(PullUpdateTask task);
    }
}
