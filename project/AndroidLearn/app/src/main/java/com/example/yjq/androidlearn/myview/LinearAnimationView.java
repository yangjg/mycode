package com.example.yjq.androidlearn.myview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.example.yjq.androidlearn.R;

import org.w3c.dom.ProcessingInstruction;

import java.net.PortUnreachableException;

/**
 * Created by yjq on 2016/5/10.
 */
public class LinearAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

    private Bitmap linerBitmap;
    private Bitmap leftBitmap;
    private Bitmap rightBitmap;
    private int animationType;
    private ValueAnimator animator;
    private static final Float MAX_PERCENT = 1.0F;
    private static final Float MAX_DEGREES = 45.0F;
    public static final int ANIMATION_EXTENED = 1;
    public static final int ANIMATION_SHRINK = 0;
    private static final int MAX_DISTANCE = 40;
    private static final int DURATION=1000;
    private float mDegrees;
    private final PaintFlagsDrawFilter DRAW_FILTER = new PaintFlagsDrawFilter(0,
            Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private final Matrix matrix = new Matrix();
    private final Paint LINE1_PAINT = new Paint();
    boolean finished = false;

    public LinearAnimationView(Context context) {
        this(context, null);
    }


    public LinearAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        linerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_line);// ((BitmapDrawable) (getResources().getDrawable(R.drawable.ic_line))).getBitmap();
        leftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_line_left);//((BitmapDrawable) (getResources().getDrawable(R.drawable.ic_line_left))).getBitmap();
        rightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_line_right); //((BitmapDrawable) (getResources().getDrawable(R.drawable.ic_line_right))).getBitmap();
        LINE1_PAINT.setAntiAlias(true);
        LINE1_PAINT.setStyle(Paint.Style.FILL);
        LINE1_PAINT.setColor(Color.YELLOW);
    }

    public static FrameLayout.LayoutParams makeLayout() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return layoutParams;
    }


    private CallBack mCallBack;

    public void addAnimatorFinish(CallBack callBack) {

        mCallBack = callBack;
    }

    public void startAnimator() {
        finished = false;
        float fx = MAX_PERCENT;
        float tx = 0;
        if (animationType == ANIMATION_EXTENED) {
            fx = 0;
            tx = MAX_PERCENT;
        }
        animator = ObjectAnimator.ofFloat(fx, tx);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(DURATION);
        animator.addUpdateListener(this);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finished = true;
                finishAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void finishAnimation() {
        if (null != mCallBack) {
            mCallBack.animationfinish();
        }
    }

    public void setAnimationType(int type) {
        animationType = type;
        if (animationType > ANIMATION_EXTENED) {
            animationType = ANIMATION_EXTENED;
        }
        if (animationType < ANIMATION_SHRINK) {
            animationType = ANIMATION_SHRINK;
        }
    }

    public void stop() {
        finished = true;
        if (null != animator && animator.isRunning()) {
            animator.cancel();
        }
    }

    public int getAnimationType(){
        return animationType;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawBitmap(canvas);
    }

    private void onDrawBitmap(Canvas canvas) {
        if (mDegrees <= 0.5) {
            onDrawLine(canvas);
        } else {
            onDrawRotateLine(canvas);
        }
    }


    private void onDrawLine(Canvas canvas) {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        int centerX = w / 2;
        int centerY = h / 2;
        int lh = linerBitmap.getHeight();
        int lw = linerBitmap.getWidth();
        int t = (h - lh) / 2;
        int l = (w - lw) / 2;
        int b = lh;

        int factor = (int) ((1.0 - mDegrees) * MAX_DISTANCE);

        canvas.setDrawFilter(DRAW_FILTER);
        matrix.reset();
        matrix.preTranslate(l, t);
        canvas.drawBitmap(linerBitmap, matrix, LINE1_PAINT);
        matrix.reset();
        matrix.preTranslate(l, t - factor);
        canvas.drawBitmap(linerBitmap, matrix, LINE1_PAINT);
        matrix.reset();
        matrix.preTranslate(l, t + factor);
        canvas.drawBitmap(linerBitmap, matrix, LINE1_PAINT);
    }

    private void onDrawRotateLine(Canvas canvas) {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        int centerX = w / 2;
        int centerY = h / 2;
        int lh = leftBitmap.getHeight();
        int lw = leftBitmap.getWidth();
        int t = (h - lh) / 2;
        int l = (w - lw) / 2;
        int b = lh;
        float degree = -180.0f + (mDegrees - 0.5f) * 90;
        matrix.reset();
       // matrix.setRotate(degree,centerX,centerY+lh);
        matrix.setTranslate(centerX, centerY+lh);

        matrix.preRotate(degree);
      //  matrix.preTranslate(-centerX, -centerY);
        canvas.drawBitmap(leftBitmap, matrix, LINE1_PAINT);
        matrix.reset();

         matrix.setTranslate(centerX, centerY);
        degree = -(mDegrees - 0.5f) * 90;
        matrix.postTranslate(0,lh);
        matrix.preRotate(degree);
        matrix.preTranslate(0,-lh);
       // matrix.preTranslate(-centerX,-centerY);
        canvas.drawBitmap(rightBitmap, matrix, LINE1_PAINT);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (!finished) {
            float v = ((Float) (animation.getAnimatedValue())).floatValue();
            mDegrees = v;
            invalidate();
        }

    }

    public interface CallBack {

        void animationfinish();
    }
}
