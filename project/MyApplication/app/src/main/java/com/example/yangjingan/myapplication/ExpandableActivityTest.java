package com.example.yangjingan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.base.ExpandableTextView;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 18-4-25.
 */

public class ExpandableActivityTest extends AppCompatActivity {

    ExpandableTextView view;

    public static String  msg= "fwefewfwefgwegewgewgewgewgwegewgwegwegwegewgwegwegwegwegwegwegewweggwegw" +
            "fwefewfwefgwegewgewgewgewgwegewgwegwegwegewgwegwegwegwegwegwegewweggwegwe" +
            "fwefewfwefgwegewgewgewgewgwegewgwegwegwegewgwegwegwegwegwegwegewweggwegwee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        view = Views.findViewById(this,R.id.expand_text_view);
        view.setText(msg);
        //setContentView(R.layout.activity_actionbar);

    }
}
