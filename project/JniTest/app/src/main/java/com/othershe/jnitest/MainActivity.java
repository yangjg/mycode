package com.othershe.jnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    private JniUtil jniUtil;

    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jniUtil = new JniUtil();
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.test);
        textView.setText(jniUtil.test());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt();
            }
        });

        data = (TextView) findViewById(R.id.encry);
    }

    private void encrypt(){
        String content ="fweewhwerhwhwehwehwehwehw";
        String res = jniUtil.AesEncrypt(content);
        data.setText(res);

    }
}
