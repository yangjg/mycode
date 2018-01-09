package com.example.yjq.androidlearn.myview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;

import com.example.yjq.androidlearn.R;

import java.net.PortUnreachableException;

/**
 * Created by yjq on 2016/5/9.
 */
public class SwitchCheckBox extends View implements Checkable {

    private static final String TAG = "SwitchCheckBox";
    private boolean mChecked = false;
    SwitchAnimator switchAnimator;
    private float mProgress = 0;
    private Drawable toggleDrawble;
    private Drawable toggleOn;
    private Drawable toggleOff;
    private Drawable toggleBackground;

    public SwitchCheckBox(Context context) {
        super(context);
        init(context);
    }

    public SwitchCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        toggleOn = getResources().getDrawable(R.drawable.switch_check_box_back_on);
        toggleOff = getResources().getDrawable(R.drawable.switch_check_box_back_off);
        toggleDrawble = getResources().getDrawable(R.drawable.switch_check_box_forground);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

    }

    @Override
    public void setChecked(boolean isChecked) {
        mChecked = isChecked;

        if (switchAnimator != null) {
            switchAnimator.cancel();
        }
      /*  toggleBackground =toggleOn;
        if(!mChecked){
            toggleBackground =toggleOff;
        }*/

        switchAnimator = new SwitchAnimator(mChecked);
        switchAnimator.start();
    }

    public void setToggleDrawable(Drawable drawable) {
              toggleDrawble =drawable;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
        //    setChecked();
        // super.toggle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawImpl(canvas, canvas.getHeight(), canvas.getWidth());
    }


    private float mFormA =0.0f;
    private float mToA=1.0f;
    private void drawImpl(Canvas canvas, int height, int width) {
         float alpha = mProgress;
         if(mProgress>0.5f){
             alpha =(mToA-mFormA)*(mProgress-0.5f)/0.5f;
             toggleBackground=toggleOn;
         }
        else{
             alpha =(mToA-mFormA)*(0.5f-mProgress)/0.5f;
             toggleBackground =toggleOff;
         }

        toggleBackground.setAlpha((int)(alpha*255));
        toggleBackground.setBounds(0, 0, width, height);
        toggleBackground.draw(canvas);

        int toggleWidth = width/3;
        int l = (int)(mProgress*(width - toggleWidth));
        int r = l+toggleWidth;
        toggleDrawble.setBounds(l, 0, r, height);
        toggleDrawble.draw(canvas);
        Log.e(TAG,String.format("l==%s,r==%s,progress==%s",l,r,mProgress));

    }


    public class SwitchAnimator extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        private boolean isChecked;
        private long times = 10000;
        private ValueAnimator mAnimator;

        boolean mFinished = false;

        public SwitchAnimator(boolean isChecked) {
            this.isChecked = isChecked;
            mAnimator = getAnimator();

        }

        private ValueAnimator getAnimator() {
            float fx = 1.0f;
            float tx = 0.0f;
            if (isChecked) {
                fx = 0.0f;
                tx = 1.0f;
            }
            ValueAnimator animator = ObjectAnimator.ofFloat(fx, tx);
            animator.setDuration(times);
            animator.addUpdateListener(this);
            animator.addListener(this);
            return animator;
        }

        public void start() {
            mAnimator.start();
            mFinished = false;
        }

        public void cancel() {
            mAnimator.cancel();
            mFinished = true;
        }

        @Override
        public void onAnimationStart(android.animation.Animator animation) {
            super.onAnimationStart(animation);
            mFinished = false;
        }

        @Override
        public void onAnimationCancel(android.animation.Animator animation) {
            super.onAnimationCancel(animation);
            mFinished = true;
        }

        @Override
        public void onAnimationPause(android.animation.Animator animation) {
            super.onAnimationPause(animation);
            mFinished = false;
        }

        @Override
        public void onAnimationRepeat(android.animation.Animator animation) {
            super.onAnimationRepeat(animation);
            mFinished = false;
        }

        @Override
        public void onAnimationResume(android.animation.Animator animation) {
            super.onAnimationResume(animation);
            mFinished = false;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            mFinished = true;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float v = ((Float) animation.getAnimatedValue()).floatValue();
            if (!mFinished) {
                mProgress = Math.min(1.0f, Math.max(0, v));
                postInvalidate();
            }
        }
    }


}
