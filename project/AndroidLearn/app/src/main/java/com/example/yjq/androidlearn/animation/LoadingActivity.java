package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/8.
 */
public class LoadingActivity extends Activity {

    Button run;
    LinearLayout root;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
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
            addBalls(50, 150);
            addBalls(200, 150);
            addBalls(350, 150);
            addBalls(500,150, Color.GREEN);
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

        private  void addBalls(int x,int y,int color){
           ShapeHolder holder =  ShapeHolder.createBall(x, y, B_WIDTH, B_HIEGHT, color);
            balls.add(holder);
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
            int flag =0;
            for (ShapeHolder item:balls){
                flag = canvas.save();
                canvas.translate(item.getX(),item.getY());
                item.getShape().draw(canvas);
                canvas.restoreToCount(flag);
            }
        }

        public  void  startAnimation(){
            createAnimation();
            animator.cancel();
            animator.start();
        }

        private void createAnimation(){
            if(animator==null){
                final int startY =balls.get(0).getY();
                int endY = startY + 300;
                final int startX = balls.get(2).getX();
                int endX = startX -300;
             /*   ObjectAnimator yAnim = ObjectAnimator.ofInt(balls.get(0), "y", startY, endY);
                yAnim.setRepeatMode(ValueAnimator.REVERSE);
                yAnim.setRepeatCount(1);
                ObjectAnimator aAnim = ObjectAnimator.ofFloat(balls.get(1), "alpha", 1f, 0f);
                aAnim.setRepeatMode(ValueAnimator.REVERSE);
                aAnim.setRepeatCount(1);
                ObjectAnimator xAnim =ObjectAnimator.ofInt(balls.get(2), "x", startX, endX);
                xAnim.setRepeatMode(ValueAnimator.REVERSE);
                xAnim.setRepeatCount(1);
                ObjectAnimator y2Anim =ObjectAnimator.ofInt(balls.get(2), "y", startY, endY);
                y2Anim.setRepeatMode(ValueAnimator.REVERSE);
                y2Anim.setRepeatCount(1);

                ObjectAnimator colorAnima = ObjectAnimator.ofInt(balls.get(3), "color", Color.GREEN, Color.BLUE);
                colorAnima.setRepeatMode(ValueAnimator.REVERSE);
                colorAnima.setRepeatCount(1);

                colorAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });*/
            /*    PropertyValuesHolder yHolder =  PropertyValuesHolder.ofInt("y",startY,endY,startY);
                PropertyValuesHolder xHolder =  PropertyValuesHolder.ofInt("x", startX, endX, startX);
                PropertyValuesHolder aHolder = PropertyValuesHolder.ofFloat("alpha", 1, 0, 1);
                PropertyValuesHolder cHolder =PropertyValuesHolder.ofInt("color", Color.GREEN, Color.BLUE, Color.GREEN);

                ObjectAnimator ball1 = ObjectAnimator.ofPropertyValuesHolder(balls.get(0), yHolder);
                ObjectAnimator ball2 = ObjectAnimator.ofPropertyValuesHolder(balls.get(1),aHolder);
                ObjectAnimator ball3 = ObjectAnimator.ofPropertyValuesHolder(balls.get(2),xHolder,yHolder);
                ObjectAnimator ball4 = ObjectAnimator.ofPropertyValuesHolder(balls.get(3),cHolder);

                ball1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ShapeHolder holder = (ShapeHolder)((ObjectAnimator)(animation)).getTarget();
                        holder.setY(startY);
                    }
                });

                ball2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ShapeHolder holder = (ShapeHolder)((ObjectAnimator)(animation)).getTarget();
                        holder.setAlpha(1f);
                    }
                });
                ball3.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ShapeHolder holder = (ShapeHolder)((ObjectAnimator)(animation)).getTarget();
                        holder.setY(startY);
                        holder.setX(startX);
                    }
                });
                ball4.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ShapeHolder holder = (ShapeHolder)((ObjectAnimator)(animation)).getTarget();
                        holder.setColor(Color.GREEN);
                    }
                });
                ball4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });*/

                ObjectAnimator ball1 = (ObjectAnimator)(AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.y_animatior));
                ValueAnimator ball2 = (ValueAnimator)(AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.alpha_animator));
                AnimatorSet ball3 =(AnimatorSet)(AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.x_animator));
                ValueAnimator ball4= (ValueAnimator)(AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.color_animator));

                ball1.setTarget(balls.get(0));
                ball3.setTarget(balls.get(2));

                ball2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                         float  alpha = ((Float)animation.getAnimatedValue()).floatValue();
                        balls.get(1).setAlpha(alpha);
                    }
                });

                ball4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int  color = ((Integer)animation.getAnimatedValue()).intValue();
                        balls.get(3).setColor(color);
                        invalidate();
                    }
                });


                AnimatorSet set = new AnimatorSet();
                set.playTogether(ball1,ball2,ball3,ball4);
               // set.setDuration(3000);
              //  set.playTogether(yAnim,aAnim,xAnim,y2Anim,colorAnima);
                animator = set;

            }
        }

    }
}
