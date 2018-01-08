package com.example.yangjingan.myapplication.NdkTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;
import com.example.yangjingan.ndkresearch.JniUtil;

/**
 * Created by yangjingan on 18-1-8.
 */

public class NdkActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        final TextView textView = Views.findViewById(this,R.id.data);
        Button btn = Views.findViewById(this, R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JniUtil util = new JniUtil();
                textView.setText(util.test());
            }
        });
    }
}
