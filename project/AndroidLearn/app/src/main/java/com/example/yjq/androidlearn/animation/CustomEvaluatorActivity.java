package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/3.
 */
public class CustomEvaluatorActivity extends Activity {
    Button run;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_evaluator_activity);
        run = Views.findViewById(this,R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animationView.startAnimation();
            }
        });
      animationView  = new MyAnimationView(this);
        ViewGroup con = Views.findViewById(this,R.id.container);
        con.addView(animationView);


    }

    public class MyPointEvaluator implements TypeEvaluator<Point>{
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            Point res = new Point();

            res.x =  (int)(startValue.x + fraction*(endValue.x-startValue.x));
            res.y =  (int)(startValue.y + fraction*(endValue.y-startValue.y));
            return res;
        }

        /*@Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startP = (Point)startValue;
            Point endP =(Point)endValue;
            Point res = new Point();

            res.x =  (int)(startP.x + fraction*(endP.x-startP.x));
            res.y =  (int)(startP.y + fraction*(endP.y-startP.y));
            return res;

        }*/
    }

    public  class MyAnimationView extends  View{

        ShapeHolder shapeHolder;
        Animator animator;
        public MyAnimationView(Context context) {
            super(context);
            shapeHolder  =ShapeHolder.createBall(100,100,50,50,Color.GREEN & 0x44FFFFFF,  Color.GREEN);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        }

        public  void startAnimation(){
            createAnimator();
            animator.start();
        }

        private void createAnimator(){
            if(null == animator){
                Point startP = new Point(shapeHolder.getX(),shapeHolder.getY());
                Point endP = new Point(shapeHolder.getX()+400,shapeHolder.getY()+600);
                ValueAnimator v1 =ValueAnimator.ofObject(new MyPointEvaluator(),startP,endP);
                v1.setDuration(5000);
                v1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Point ani = (Point) animation.getAnimatedValue();

                        shapeHolder.setX(ani.x);
                        shapeHolder.setY(ani.y);
                        invalidate();
                    }
                });
                animator = v1;

            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(null  != shapeHolder){
                int flag = canvas.save();
                canvas.translate(shapeHolder.getX(),shapeHolder.getY());
                shapeHolder.getShape().draw(canvas);
                canvas.restoreToCount(flag);
            }
        }
    }
}
