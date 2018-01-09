package com.example.yjq.androidlearn.animation;

import android.animation.LayoutTransition;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/3.
 */
public class DefaultLayoutAnimationActivity extends Activity {

    LinearLayout container;
    Button addBtn;
    GridLayout gridLayout;
    private  int colum =4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_layout_animation);
        container  = Views.findViewById(this, R.id.container);
        addBtn  =Views.findViewById(this,R.id.add);

        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(colum);
        gridLayout.setLayoutTransition(new LayoutTransition());
        container.addView(gridLayout);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button textView = new Button(getApplicationContext());
                textView.setText(String.valueOf(gridLayout.getChildCount() + 1));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridLayout.removeView(v);
                    }
                });
                gridLayout.addView(textView, Math.min(1,gridLayout.getChildCount()));
            }
        });


    }
}
