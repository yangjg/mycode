package com.example.yangjingan.myapplication;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by yangjingan on 17-6-22.
 */
public class AnimActivity  extends AppCompatActivity {


    Button btn;
    ImageButton imageButton;
    AnimatedVectorDrawable first;
    AnimatedVectorDrawable second;
    private AnimatedVectorDrawable mAnimatedVectorDrawable;



    ImageButton imageButton2;
    AnimatedVectorDrawable first2;
    AnimatedVectorDrawable second2;
    private AnimatedVectorDrawable mAnimatedVectorDrawable2;

    ImageButton imageButton3;
    AnimatedVectorDrawable first3;
    AnimatedVectorDrawable second3;
    private AnimatedVectorDrawable mAnimatedVectorDrawable3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        btn = (Button)findViewById(R.id.btn);
        first = (AnimatedVectorDrawable) getDrawable(R.drawable.animate_vector);
        second  = (AnimatedVectorDrawable) getDrawable(R.drawable.animate_vector_second);
        imageButton = (ImageButton)findViewById(R.id.imageBtn);
        //imageButton.setBackgroundResource(R.drawable.ic_bg);
        imageButton.setBackgroundColor(Color.RED);
        imageButton.setImageDrawable(first);
        //imageButton.setImageResource(R.drawable.vector_drawable_more);
        //imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
        mAnimatedVectorDrawable =(AnimatedVectorDrawable)imageButton.getDrawable();



        first2 = (AnimatedVectorDrawable) getDrawable(R.drawable.animate_vector_horizontal);
        second2  = (AnimatedVectorDrawable) getDrawable(R.drawable.animate_vector_second_horizontal);
        imageButton2 = (ImageButton)findViewById(R.id.imageBtn2);
        //imageButton.setBackgroundResource(R.drawable.ic_bg);
        imageButton2.setBackgroundColor(Color.RED);
        imageButton2.setImageDrawable(first2);
        //imageButton.setImageResource(R.drawable.vector_drawable_more);
        //imageButton2.setScaleType(ImageView.ScaleType.FIT_XY);
        mAnimatedVectorDrawable2 =(AnimatedVectorDrawable)imageButton2.getDrawable();


        first3 = (AnimatedVectorDrawable) getDrawable(R.drawable.animate_vector_test);
        second3  = (AnimatedVectorDrawable) getDrawable(R.drawable
                .animate_vector_test);
        imageButton3 = (ImageButton)findViewById(R.id.imageBtn3);
        //imageButton.setBackgroundResource(R.drawable.ic_bg);
        imageButton3.setBackgroundColor(Color.WHITE);
        imageButton3.setImageDrawable(first3);
        //imageButton.setImageResource(R.drawable.vector_drawable_more);
        //imageButton2.setScaleType(ImageView.ScaleType.FIT_XY);
        mAnimatedVectorDrawable3 =(AnimatedVectorDrawable)imageButton3.getDrawable();




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dowork();
            }
        });
       // mAnimatedVectorDrawable.registerAnimationCallback(new MyAnimationCallback());

    }

    int count = 0;
    private boolean mFinish = false ;
    private void dowork(){

       // mAnimatedVectorDrawable.start();
        if(count%2==0){
            //mAnimatedVectorDrawable.start();

             imageButton.setImageDrawable(first);
            imageButton2.setImageDrawable(first2);
            imageButton3.setImageDrawable(first3);
        }
        else {
            imageButton.setImageDrawable(second);
            imageButton2.setImageDrawable(second2);
            imageButton3.setImageDrawable(second3);
           // mAnimatedVectorDrawable.reset();
        }
        mAnimatedVectorDrawable =(AnimatedVectorDrawable)imageButton.getDrawable();
       // mAnimatedVectorDrawable.registerAnimationCallback(new MyAnimationCallback());
        mAnimatedVectorDrawable.start();

        mAnimatedVectorDrawable2 =(AnimatedVectorDrawable)imageButton2.getDrawable();
        mAnimatedVectorDrawable2.start();


        mAnimatedVectorDrawable3 =(AnimatedVectorDrawable)imageButton3.getDrawable();
        mAnimatedVectorDrawable3.start();


        count++;
        /*Drawable drawable = imageButton.getDrawable();
        Rect rect = drawable.getBounds();

        int centerX = rect.centerX();
        int centerY = rect.centerY();*/

    }

    int updateCount = 0 ;
    private void updateImageResource(){
        /*if(updateCount %2 ==0){
            imageButton.setImageDrawable(second);
            testBtn.setBackground(first);
            AnimatedVectorDrawable test = (AnimatedVectorDrawable)testBtn.getBackground();
            test.reset();
           // first.reset();
           // imageButton.setImageResource(R.drawable.animate_vector_second);
        }
        else{
            imageButton.setImageDrawable(first);
            testBtn.setBackground(second);
            AnimatedVectorDrawable test = (AnimatedVectorDrawable)testBtn.getBackground();
            test.reset();
            //second.reset();
            //imageButton.setImageResource(R.drawable.animate_vector);
        }
        mAnimatedVectorDrawable = (AnimatedVectorDrawable)imageButton.getDrawable();
        updateCount++;*/
    }

    public  class MyAnimationCallback extends Animatable2.AnimationCallback {
        @Override
        public void onAnimationStart(Drawable drawable) {
           // super.onAnimationStart(drawable);
        }

        @Override
        public void onAnimationEnd(Drawable drawable) {
          /*  mAnimatedVectorDrawable.unregisterAnimationCallback(MyAnimationCallback.this);
            count++;
            if(count %2 ==0){
                imageButton.setImageDrawable(first);
                mAnimatedVectorDrawable = first;
            }
            else{
                imageButton.setImageDrawable(second);
                mAnimatedVectorDrawable = second;
            }
            mFinish = true;
         (AnimatedVectorDrawable)imageButton.getDrawable();
            mAnimatedVectorDrawable.registerAnimationCallback(MyAnimationCallback.this);*/
            //super.onAnimationEnd(drawable);
           // mAnimatedVectorDrawable.reset();
           // mAnimatedVectorDrawable.unregisterAnimationCallback(MyAnimationCallback.this);
           // mAnimatedVectorDrawable.reset();
          //  updateImageResource();

          //  mAnimatedVectorDrawable.registerAnimationCallback(MyAnimationCallback.this);
          /*  AnimActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });*/



        }
    }
}
