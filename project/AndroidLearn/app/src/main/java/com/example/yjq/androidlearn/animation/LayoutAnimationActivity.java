package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/8.
 */
public class LayoutAnimationActivity extends Activity {

    LinearLayout root;
    Button add;
    CheckBox cus;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animation_activity);
        root = Views.findViewById(this,R.id.container);
        add  = Views.findViewById(this,R.id.add);
        cus =Views.findViewById(this,R.id.custom);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = new Button(LayoutAnimationActivity.this);
                button.setText(String.valueOf(gridLayout.getChildCount()+1));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      gridLayout.removeView(v);
                    }
                });
                gridLayout.addView(button,gridLayout.getChildCount()>0?1:0);
            }
        });

        cus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     setCustomTransition();
                 }
                else {
                     resetLayoutTransition();
                 }
            }
        });

        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(10);


        root.addView(gridLayout);

        resetLayoutTransition();

    }

    private  void resetLayoutTransition(){
        gridLayout.setLayoutTransition(new LayoutTransition());
    }

    private final int DURATION=1500;
    private void setCustomTransition(){
        resetLayoutTransition();
        LayoutTransition layoutTransition = gridLayout.getLayoutTransition();

        ObjectAnimator appA =ObjectAnimator.ofFloat(this, "rotationY", 90f, 0);
        appA.setDuration(DURATION);
        appA.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setRotationY(0);
            }
        });

        ObjectAnimator disAnim = ObjectAnimator.ofFloat(this, "rotationX", 0, 90f);
        disAnim.setDuration(DURATION);
        disAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // super.onAnimationEnd(animation);
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setRotationX(0);
            }
        });
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, disAnim);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, appA);





        PropertyValuesHolder th= PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder lh =PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder rh =PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder bh =PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder sx =PropertyValuesHolder.ofFloat("scaleX", 1, 0, 1);
        PropertyValuesHolder sy =PropertyValuesHolder.ofFloat("scaleY",1,0,1);
        ObjectAnimator appAnim = ObjectAnimator.ofPropertyValuesHolder(this, th, lh, rh, bh, sx, sy);
        appAnim.setDuration(DURATION);
        appAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // super.onAnimationEnd(animation);

                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1);
                view.setScaleY(1);
            }
        });


        Keyframe keyframe = Keyframe.ofFloat(0, 0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.9f,360f);
        Keyframe keyframe2 =Keyframe.ofFloat(1f,0);

        PropertyValuesHolder ph = PropertyValuesHolder.ofKeyframe("rotation", keyframe, keyframe1, keyframe2);
        ObjectAnimator disAnima = ObjectAnimator.ofPropertyValuesHolder(this, th, lh, rh, bh,ph);
        disAnima.setDuration(DURATION);
        disAnima.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // super.onAnimationEnd(animation);

                  View view = (View) ((ObjectAnimator) animation).getTarget();
                 view.setRotation(0);
            }
        });

        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, appAnim);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,disAnima);

  /*      PropertyValuesHolder rxh = PropertyValuesHolder.ofFloat("rotationX", 0, 90f);
        PropertyValuesHolder txh= PropertyValuesHolder.ofInt("top", 0, 50);
        PropertyValuesHolder lxh =PropertyValuesHolder.ofFloat("left", 0, 100);
        PropertyValuesHolder xhr =PropertyValuesHolder.ofFloat("right",200,100);
        PropertyValuesHolder bxh =PropertyValuesHolder.ofFloat("bottom",100,50);*/




        //PropertyValuesHolder.ofKeyframe("rotation",)
      /*  ObjectAnimator changeAppAnim = ObjectAnimator.ofFloat(this,"rotation",0,360f);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,changeAppAnim);*/
        layoutTransition.setDuration(DURATION);
    }
}
