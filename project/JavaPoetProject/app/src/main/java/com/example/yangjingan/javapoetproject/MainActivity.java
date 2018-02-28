package com.example.yangjingan.javapoetproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.HelloAnnotation;
import com.example.HelloWorld;

@HelloAnnotation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelloWorld.main(null);
    }
}
