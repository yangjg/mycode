package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/5/23.
 */
public class MyJNIActivity extends Activity {

    TextView jni;
    private static final String TAG = "MyJNIActivity";

    public MyJNIActivity(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jni_activity);
        jni = Views.findViewById(this,R.id.jni);
        jni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e(TAG,msg);
            }
        });
    }

    static {
        System.loadLibrary("HelloWorldJni");
    }

}
