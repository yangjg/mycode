package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/8.
 */
public class MultiplePropertiesActivity extends Activity {

    Button run;
    LinearLayout root;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_properties_activity);
        root = Views.findViewById(this,R.id.container);
        run = Views.findViewById(this,R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.startAnimation();
            }
        });
        animationView = new MyAnimationView(this);
        root.addView(animationView);
    }


    public class MyAnimationView extends View{

        List<ShapeHolder> balls = new ArrayList<>();
        private Animator animator;
        private final int B_WIDTH=100;
        private final int B_HIEGHT=100;
        public MyAnimationView(Context context) {
            super(context);
            addBalls(150, 50);
            addBalls(250, 50);
            addBalls(350, 50);
            addBalls(450,50);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int flag =0;
            for (ShapeHolder item:balls){
                flag = canvas.save();
                canvas.translate(item.getX(),item.getY());
                item.getShape().draw(canvas);
                canvas.restoreToCount(flag);
            }
        }

        private void addBalls(int x,int y){
            int red = (int)(Math.random()*255);
            int green =  (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            int color = 0xff000000 | red<<16 | green<<8|blue;
            int darkColor =  0xff000000 | red/4 << 16 | green/4 << 8| blue/4;
            ShapeHolder holder =  ShapeHolder.createBall(x, y, B_WIDTH, B_HIEGHT, darkColor, color);
            balls.add(holder);
        }

        public void startAnimation(){
            createAnimation();
            animator.cancel();
            animator.start();
        }

        private void createAnimation(){
            if(null==animator){
                 final  int scalWidth = 200;
                final int scaleHeight =200;
                int startY = balls.get(0).getY();
                int endY = (int)(getHeight() -balls.get(0).getHeight());
                float startWidth =balls.get(0).getWith();
                float endWith =startWidth +scalWidth;
                float startHeight=balls.get(0).getHeight();
                float endHeight=startHeight+scaleHeight;
                int  stx =balls.get(2).getX();
                int sty =balls.get(2).getY();
                int etx =stx - 100;
                int ety =sty -100;

                int stranX = balls.get(3).getX();
                int etranX = stranX +200;
                int duration = 3000;
                PropertyValuesHolder yHolder = PropertyValuesHolder.ofInt("y", startY, endY);
                PropertyValuesHolder yrHolder = PropertyValuesHolder.ofInt("y", startY, endY);
                PropertyValuesHolder aHolder = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
                PropertyValuesHolder wHolder =PropertyValuesHolder.ofFloat("with", startWidth, endWith);
                PropertyValuesHolder hHolder =PropertyValuesHolder.ofFloat("height", startHeight, endHeight);
                PropertyValuesHolder xtHolder = PropertyValuesHolder.ofInt("x", stx, etx);
                PropertyValuesHolder ytHolder = PropertyValuesHolder.ofInt("y", sty, ety);
                PropertyValuesHolder tranxHolder =PropertyValuesHolder.ofInt("x", stranX, etranX,stranX);

                ObjectAnimator ball1 = ObjectAnimator.ofPropertyValuesHolder(balls.get(0), yHolder);
                ball1.setInterpolator(new BounceInterpolator());
               ball1.setDuration(duration);

                ObjectAnimator ball2 = ObjectAnimator.ofPropertyValuesHolder(balls.get(1),aHolder,yrHolder);
                ball2.setInterpolator(new AccelerateInterpolator());
               ball2.setRepeatMode(ValueAnimator.REVERSE);
                ball2.setRepeatCount(1);
                ball2.setDuration(duration);

                ObjectAnimator ball3 = ObjectAnimator.ofPropertyValuesHolder(balls.get(2),wHolder,hHolder,xtHolder,ytHolder);
                ball3.setRepeatMode(ValueAnimator.REVERSE);
                ball3.setRepeatCount(1);
                ball3.setDuration(duration);

                ObjectAnimator ball4 =ObjectAnimator.ofPropertyValuesHolder(balls.get(3),tranxHolder,yrHolder);
                ball4.setRepeatMode(ValueAnimator.REVERSE);
                ball4.setRepeatCount(1);
                ball4.setDuration(duration);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(ball1,ball2,ball3,ball4);

                ball2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });


              //  set.setDuration(3000);

                animator = set;


            }
        }
    }
}
