package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/2.
 */
public class BouncingBallActivity extends Activity {


    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_bball_activity);
        container = Views.findViewById(this,R.id.container);
        MyBallAnimationView view = new MyBallAnimationView(this);
        container.addView(view);

    }



    public class MyBallAnimationView extends View {

        private static final int RED = 0xffFF8080;
        private static final int BLUE = 0xff8080FF;
        static final int SEM_DURATION=1500;
        List<ShapeHolder> listShape = new ArrayList<>();
        public MyBallAnimationView(Context context) {
            super(context);
            ValueAnimator animator = ObjectAnimator.ofInt(this,"backgroundColor",RED,BLUE);
            animator.setDuration(3000);
            animator.setEvaluator(new ArgbEvaluator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.start();

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

           int action =  event.getAction();
            int x=(int)event.getX();
            int y = (int)event.getY();
         //   if(listShape.size()>=1)return true;
            ShapeHolder shapeHolder =  addBall(x, y);
            Animator animator =createAnimation(shapeHolder);
            animator.start();;
            return  true;
        }

        private ShapeHolder addBall(int x,int y){
            OvalShape s = new OvalShape();
            s.resize(50f,50f);
            ShapeDrawable drawable = new ShapeDrawable(s);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x - 25);
            shapeHolder.setY(y-25);
            int r = (int)(Math.random()*255);
            int g = (int)(Math.random()*255);
            int b = (int)(Math.random()*255);
            int color =0xff000000| r<<16|g<<8|b;
            int darkColor = 0xff000000| r/4 << 16| g/4 << 8| b/4;
            RadialGradient shader = new RadialGradient(37.5f,12.5f,50f,darkColor,color, Shader.TileMode.CLAMP);
            shapeHolder.getPaint().setShader(shader);
            listShape.add(shapeHolder);
            return shapeHolder;
        }

        final int bh=50;
        private Animator createAnimation(ShapeHolder holder){

            int moveduration;
            int duration;
            int ht =getHeight();
            int y =holder.getY();
            duration =moveduration =  (int)(SEM_DURATION*((ht-y)*1.0f/(ht-bh)));
            ObjectAnimator yAnim = ObjectAnimator.ofInt(holder,"y",holder.getY(),getHeight()-bh);
            yAnim.setInterpolator(new AccelerateInterpolator());
            yAnim.setDuration(moveduration);

            ObjectAnimator yRAnim = ObjectAnimator.ofInt(holder,"y",getHeight()-bh,holder.getY());
            yRAnim.setInterpolator(new DecelerateInterpolator());
            yRAnim.setDuration(moveduration);

            duration =(int)(SEM_DURATION*0.25);
            ObjectAnimator pYAnim =ObjectAnimator.ofFloat(holder, "height", holder.getHeight(), holder.getHeight() - 25f);
            pYAnim.setDuration(duration);
            pYAnim.setRepeatCount(1);
            pYAnim.setRepeatMode(ValueAnimator.REVERSE);


            ObjectAnimator pXanim = ObjectAnimator.ofFloat(holder, "with", holder.getWith(), holder.getWith() + 50f);
            pXanim.setDuration(duration);
            pXanim.setRepeatCount(1);
            pXanim.setRepeatMode(ValueAnimator.REVERSE);

            ObjectAnimator xtran =ObjectAnimator.ofInt(holder,"x",holder.getX(),holder.getX()-25);
            xtran.setDuration(duration);
            xtran.setRepeatCount(1);
            xtran.setRepeatMode(ValueAnimator.REVERSE);

            ObjectAnimator ytran =ObjectAnimator.ofInt(holder,"y",getHeight()-bh,getHeight()-bh+25);
            ytran.setDuration(duration);
            ytran.setRepeatCount(1);
            ytran.setRepeatMode(ValueAnimator.REVERSE);




            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(pYAnim, pXanim, xtran, ytran);
            AnimatorSet.Builder builder = animatorSet.play(yAnim);
            builder.before(pYAnim);
            builder = animatorSet.play(yRAnim);
            builder.after(pYAnim);

            yRAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ObjectAnimator animator =  (ObjectAnimator)animation;
                    ShapeHolder shapeHolder =(ShapeHolder) animator.getTarget();
                    listShape.remove(shapeHolder);
                }
            });
            return  animatorSet;
        }



        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        public void setBackgroundColor(int color) {
            super.setBackgroundColor(color);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(null  != listShape && listShape.size()>0){
                int flag =0;
                for (ShapeHolder s : listShape){
                   flag  = canvas.save();
                    canvas.translate(s.getX(),s.getY());
                    s.getShape().draw(canvas);
                    canvas.restoreToCount(flag);
                }
            }
        }
    }

}
