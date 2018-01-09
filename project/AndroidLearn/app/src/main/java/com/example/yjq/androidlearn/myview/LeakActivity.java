package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.yjq.androidlearn.R;

/**
 * Created by yjq on 2016/5/24.
 */
public class LeakActivity extends Activity {

    static int count=0;
    private static final String TAG = "LeakActivity";
    byte[] buf;

    public LeakActivity(){
        super();
        Log.e(TAG,"LeakActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jni_activity);
        buf = new byte[1024*1024];
        LeakClass lk= new LeakClass(count++);
        lk.start();
    }

    class LeakClass extends Thread{
        int mId;
        public LeakClass(int id){
            mId = id;
        }
        @Override
        public void run() {
            while (true){
                try {
                    Log.e(TAG,"sleep Thread Id =="+mId);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
