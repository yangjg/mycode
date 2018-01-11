package com.othershe.jnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JniUtil.init(this);
       // jniUtil = new JniUtil();
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.test);
       // textView.setText(jniUtil.test());
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
     /*   String res = jniUtil.StrEncrypt(content);
        String source = jniUtil.StrEncrypt(res);*/
       // String res = jniUtil.getEncodeString(content);
        String res = JniUtil.getInstance().getHexEncrpt(content);
        data.setText(res);

    }
}
