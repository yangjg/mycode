package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/4.
 */
public class AniationEventActivity extends Activity {
    Button play,cancel,end;
    CheckBox  endCheck;
    LinearLayout container;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_event_activity);
        container = Views.findViewById(this,R.id.container);
        play = Views.findViewById(this,R.id.play);
        cancel =Views.findViewById(this,R.id.cancel);
        end =Views.findViewById(this,R.id.end);
        endCheck  =Views.findViewById(this,R.id.checkbox);
        animationView = new MyAnimationView(this);
        container.addView(animationView);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.startAnimation();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.cancleAnimation();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.endAnimation();
            }
        });
    }


    public  class MyAnimationView extends View{


        ShapeHolder ball;
        Animator animator;
        private static final int DURATION=1500;
        public MyAnimationView(Context context) {
            super(context);
            ball =ShapeHolder.createBall(100,100,100,100, Color.GREEN&0x22ffffff,Color.GREEN);
        }

        private void  createAnimation(){
            if(null == animator){
                ObjectAnimator xanim  = ObjectAnimator.ofInt(ball,"x",ball.getX(),ball.getX()+300);
                xanim.setDuration(DURATION);
                ObjectAnimator yani =ObjectAnimator.ofInt(ball,"y",ball.getY(),(int)(getHeight()-ball.getHeight()));
                yani.setDuration(2*DURATION);
                yani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });
                AnimatorSet set = new AnimatorSet();
                set.play(xanim).with(yani);
                animator = set;
            }
        }

        public  void  startAnimation(){
            createAnimation();
            animator.start();
            if(endCheck.isChecked()){
                animator.end();
            }
        }

        public void cancleAnimation(){
            createAnimation();
            animator.cancel();
        }

        public void endAnimation(){
            createAnimation();
            animator.end();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int flag = canvas.save();
            canvas.translate(ball.getX(),ball.getY());
            ball.getShape().draw(canvas);
            canvas.restoreToCount(flag);
        }

    }
}
