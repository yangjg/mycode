package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.app.IntentService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yjq on 2016/4/11.
 */
public class SkewActivity extends Activity {

    private float dx, dy;
    private float px, py;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skew_activity);
        final SkewView skewView = Views.findViewById(this, R.id.skew);
        Button skewBtn = Views.findViewById(this, R.id.skewbtn);
        Button skewPtn = Views.findViewById(this, R.id.skewPtn);
        Button skewRtn = Views.findViewById(this, R.id.skewRtn);
        skewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dx += 0.1f;
                //dy += 0.1f;
                if (dx > v.getWidth() || dy > v.getHeight()) {
                    dx = 0;
                    dy = 0;
                }
                skewView.setSkewParam(dx, dy);


            }
        });
        skewPtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  px +=v.getWidth()/2;
              //  py +=v.getHeight()/2;
               /* if (px > v.getWidth() || py > v.getHeight()) {
                    px = 0;
                    py = 0;
                }*/
                skewView.setSkewParam(dx, dy, px, py);

            }
        });
        skewRtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dx = 0;
                px = 0;
                dy = 0;
                py = 0;
                skewView.setSkewParam(dx, dy, px, py);
                Looper.myLooper().quit();
               // ReentrantReadWriteLock FEW
              //  new InterThread(mythread).start();
            }
        });

    }



}
