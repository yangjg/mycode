package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import org.w3c.dom.Text;

/**
 * Created by yjq on 2016/4/12.
 */
public class AnimationActivity extends Activity {

    TextView tv;
    Rotate3DAnimation animation;
    AnimationDrawable animationDrawable;
    View root;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity);
        root =Views.findViewById(this,R.id.root);
        tv = Views.findViewById(this, R.id.textView);
        animation =  new Rotate3DAnimation(0,360,100,100,5,false); // AnimationUtils.loadAnimation(this, R.anim.second_anim);
        animation.doTestMatrix();
        animation.setDuration(5000);
        animation.setFillAfter(false);

        animationDrawable = (AnimationDrawable)root.getBackground();
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.startAnimation(animation);
              /*  if(flag){
                    animationDrawable.start();
                }
                else{
                    animationDrawable.stop();
                }
                flag =!flag;*/
            }
        });
        //tv.setAnimation(R.xml.second_anim);

    }

    @Override
    protected void onResume() {

        super.onResume();
    //    tv.startAnimation(animation);
    }

    private void createAnimation() {

    }
}
