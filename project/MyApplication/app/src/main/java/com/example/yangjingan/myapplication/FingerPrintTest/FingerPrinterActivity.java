package com.example.yangjingan.myapplication.FingerPrintTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 18-1-25.
 */

public class FingerPrinterActivity extends AppCompatActivity {

    Button test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_printer);
        test = Views.findViewById(this,R.id.test);
    }
}
