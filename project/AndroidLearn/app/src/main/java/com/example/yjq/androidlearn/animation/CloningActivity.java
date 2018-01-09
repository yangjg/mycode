package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/2.
 */
public class CloningActivity extends Activity {

    Button run;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloning_activity);
        run  = Views.findViewById(this,R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     animationView.startAnimation();
            }
        });
        //
        animationView =new MyAnimationView(this);
        LinearLayout container =Views.findViewById(this, R.id.container);
        container.addView(animationView);

    }


    public class MyAnimationView extends View{


        private static final int RED = Color.RED;
        private static final int BLUE = Color.BLUE;
        private static final  int GRAY=Color.GRAY;
        private static final int PERPLE= 0xff0080ff;
        private static final int DURATION=1500;
        List<ShapeHolder> listShape = new ArrayList<>();
        private Animator animator;
        public MyAnimationView(Context context) {
            super(context);
            addBall(150, 200, RED);
            addBall(350, 200, BLUE);
            addBall(650, 200, GRAY);
            addBall(850,200,PERPLE);
        }

        private ShapeHolder addBall(int x,int y, int color){
            OvalShape shape = new OvalShape();
            shape.resize(200f,200f);
            ShapeDrawable drawable = new ShapeDrawable(shape);
            ShapeHolder holder = new ShapeHolder(drawable);
            holder.setX(x - 50);
            holder.setY(y - 50);
            holder.setColor(color);
            listShape.add(holder);
            return holder;
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
        }

        @Override
        public boolean onKeyLongPress(int keyCode, KeyEvent event) {
            return super.onKeyLongPress(keyCode, event);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        }


        public void startAnimation(){
            createAnimation();
            animator.start();
        }

        private void createAnimation(){
            if(null==animator){
                ShapeHolder shapeHolder = listShape.get(0);
                int startY = shapeHolder.getY();
                int endY = (int)(getHeight() -shapeHolder.getHeight());
                ObjectAnimator yanim0 = ObjectAnimator.ofInt(shapeHolder, "y", startY, endY);
                yanim0.setDuration(DURATION);


                ObjectAnimator yanim1= yanim0.clone();
                yanim1.setTarget(listShape.get(1));

                ObjectAnimator yanim2 = yanim0.clone();
                yanim2.setTarget(listShape.get(2));
                yanim2.setDuration( DURATION);
                yanim2.setInterpolator(new AccelerateInterpolator());
                yanim2.setRepeatCount(1);
                yanim2.setRepeatMode(ValueAnimator.REVERSE);

                ObjectAnimator yanim3 = yanim2.clone();
                yanim3.setTarget(listShape.get(3));

                ObjectAnimator timeAnim =  ObjectAnimator.ofInt(listShape.get(0),"time",0,1);
                timeAnim.setDuration(4*DURATION);
                timeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });


                AnimatorSet set = new AnimatorSet();
                set.play(yanim0).with(yanim1).with(yanim2).before(yanim3);

                AnimatorSet set2 = new AnimatorSet();

                set2.play(set).with(timeAnim);

                animator  =set2;
            }
        }



        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(listShape.size()>0){
                int flag =0;
                for (ShapeHolder s:listShape){
                     flag = canvas.save();
                     canvas.translate(s.getX(),s.getY());
                    s.getShape().draw(canvas);
                    canvas.restoreToCount(flag);
                }
            }
        }


    }
}
