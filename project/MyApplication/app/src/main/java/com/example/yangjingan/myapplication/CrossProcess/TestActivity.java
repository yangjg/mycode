package com.example.yangjingan.myapplication.CrossProcess;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 17-6-6.
 */
public class TestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.e("Test","TestActivity");
        super.onCreate(savedInstanceState);
      /*  setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.test);
        btn.setText("Process Btn");*/
        LinearLayout root = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setLayoutParams(params);

        LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i =0;i<5;i++){
            TextView tv = new TextView(this);
            tv.setText("Processs " + String.valueOf(i));
            tv.setLayoutParams(ly);
            root.addView(tv);
        }

        setContentView(root);
    }
}
