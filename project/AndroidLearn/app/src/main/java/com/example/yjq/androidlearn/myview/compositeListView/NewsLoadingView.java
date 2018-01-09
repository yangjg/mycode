package com.example.yjq.androidlearn.myview.compositeListView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import com.example.yjq.androidlearn.R;


/**
 * Created by 80106959 on 2016-03-11.
 */
public class NewsLoadingView extends View {

    private final static int ROTATE_TIME = 2000;
    private final static int HALF_ROTATE_TIME = ROTATE_TIME / 2;
    private final static int ROTATE_SCALE = 720;
    private static final long SCALE = 70;
    private static final float INIT_RADIAN = 160;
    private static final int ROTATION_HALF = 180;
    private float mRotationX;
    private float mRotationY;
    private float mInitAngle = 0.0f;
    private int mStrokeWidth = 0;
    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;
    private float mStartRotateAngle;
    private boolean mFirstOnDraw;
    private final Interpolator PATHINTERPOLATOR = PathInterpolatorCompat.create(0.2f, 0.0f, 0.3f, 1.0f);

    public NewsLoadingView(Context context) {
        this(context, null);
    }

    public NewsLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mFirstOnDraw = true;
        mStrokeWidth = context.getResources()
                .getDimensionPixelSize(R.dimen.iflow_list_bottom_view_progressbar_stroke_width);
        final int paintColor = context.getResources()
                .getColor(R.color.iflow_list_bottom_view_progressbar_color_nightmd);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(paintColor);
        mStartRotateAngle = 0;
        mPath = new Path();
    }

    private void makeLoadRect(int width, int height) {
        int rectLeft = mStrokeWidth;
        int rectTop = mStrokeWidth;
        int rectRight = width - mStrokeWidth;
        int rectBottom = height - mStrokeWidth;
        mRectF = new RectF(rectLeft, rectTop, rectRight, rectBottom);
        mRotationX = (float) (rectRight - rectLeft) / 2.0f + rectLeft;
        mRotationY = (float) (rectBottom - rectTop) / 2.0f + rectTop;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mFirstOnDraw) {
            makeLoadRect(getWidth(), getHeight());
            mFirstOnDraw = false;
        }
        prepareAnglePath();
        canvas.rotate(mStartRotateAngle, mRotationX, mRotationY);
        canvas.drawPath(mPath, mPaint);
        canvas.rotate(ROTATION_HALF, mRotationX, mRotationY);
        canvas.drawPath(mPath, mPaint);
        invalidate();
    }

    private void prepareAnglePath() {
        long time = System.currentTimeMillis() % ROTATE_TIME;
        float phase = constrain(0, 1, time * 1.0f / ROTATE_TIME);
        mStartRotateAngle = PATHINTERPOLATOR.getInterpolation(phase) * ROTATE_SCALE;
        float rotateRadian;
        if (time <= HALF_ROTATE_TIME) {
            phase = constrain(0, 1, time * 1.0f / HALF_ROTATE_TIME);
            phase = 1 - phase;
        }
        else {
            time = time - HALF_ROTATE_TIME;
            phase = constrain(0, 1, time * 1.0f / HALF_ROTATE_TIME);
        }
        rotateRadian = PATHINTERPOLATOR.getInterpolation(phase) * SCALE;
        mPath.reset();
        mPath.arcTo(mRectF, mInitAngle + rotateRadian, INIT_RADIAN - 2 * rotateRadian);
    }

    public float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
