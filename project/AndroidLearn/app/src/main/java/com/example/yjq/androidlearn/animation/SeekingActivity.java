package com.example.yjq.androidlearn.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/8.
 */
public class SeekingActivity extends Activity {

    final int DURATION=3000;
    Button run;
    SeekBar seekBar;
    LinearLayout root;
    MyAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeking_activity);
        root = Views.findViewById(this, R.id.container);
        run = Views.findViewById(this,R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.startAnimation();
            }
        });

        seekBar = Views.findViewById(this,R.id.seekbar);
        seekBar.setMax(DURATION);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 animationView.setSeekPlayTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        animationView = new MyAnimationView(this);
        root.addView(animationView);
    }

    public class MyAnimationView extends View {

        List<ShapeHolder> balls = new ArrayList<>();
        private ObjectAnimator animator;
        private final int B_WIDTH=100;
        private final int B_HIEGHT=100;
        public MyAnimationView(Context context) {
            super(context);
            addBalls(150, 50);
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


        public void setSeekPlayTime(int progress){
            createAnimation();
            animator.setCurrentPlayTime(progress);
        }

        private void createAnimation(){
            if(null==animator){
                int startY = balls.get(0).getY();
                int endY = (int)(getHeight() -balls.get(0).getHeight());
                ObjectAnimator yAnima = ObjectAnimator.ofInt(balls.get(0), "y",startY,endY);
                yAnima.setDuration(DURATION);
                yAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });
                animator = yAnima;

            }
        }
    }
}
