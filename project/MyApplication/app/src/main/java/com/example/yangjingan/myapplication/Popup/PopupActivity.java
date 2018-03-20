package com.example.yangjingan.myapplication.Popup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 18-3-19.
 */

public class PopupActivity extends AppCompatActivity {

    TextView mInfo;
    View  bottom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);

        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_popup);
      //  getSupportActionBar().hide();
        mInfo = Views.findViewById(this,R.id.info);
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReply();
            }
        });


        bottom =Views.findViewById(this,R.id.bottom_layout);
        bottom.setClickable(false);
      /*  bottom.setFocusable(false);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  test();
               // showReply();
            }
        });*/

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/

    CustomPopup customPopup;
    private void test(){

        if(null == customPopup){
            customPopup = new CustomPopup(this);
            customPopup.setCustomLayout(R.layout.custom_ui,null);
        }
       // customPopup.showAtLocation(mInfo, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,500);

    /*  if(null == customPopup){
          customPopup = new CustomPopup(this);
          customPopup.setCustomLayout(R.layout.custom_ui);
      }
      getSupportActionBar().hide();
      customPopup.show();*/
    }

    private  void showReply(){
        if(null == customPopup){
            customPopup = new CustomPopup(this);
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_reply_layout,customPopup.getContainer(),false);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelSize(R.dimen.bottom_height));
            layoutParams.gravity = Gravity.BOTTOM;
           // view.setLayoutParams(layoutParams);
            customPopup.setCustomView(view,layoutParams);

            EditText edit = Views.findViewById(view,R.id.reply_msg);
            edit.requestFocus();
           // customPopup.setCustomLayout();
        }
        customPopup.show();
    }
}
