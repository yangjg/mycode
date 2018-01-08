package com.example.yangjingan.myapplication.CommonTest;

import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/**
 * Created by yangjingan on 17-5-18.
 */
public class ClassTest {


    public void doTest(){
        Thread th  =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GlobalClass.getInstance().dowork();
                }
                catch (Throwable t){
                    t.printStackTrace();
                }
                finally {

                }

            }
        });
        th.start();
    }


    public Interpolator getInterpolator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new PathInterpolator(0.1f, 0.0f, 0.1f, 1.0f);
        } else {
            return new AnimInterpolator();
        }
    }

    private class AnimInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return (float) (1.0f - Math.pow((1.0f - input), 2));
        }
    }

}
