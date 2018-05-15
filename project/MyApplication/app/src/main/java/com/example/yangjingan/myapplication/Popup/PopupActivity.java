package com.example.yangjingan.myapplication.Popup;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.Webview.GlobalHandler;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 18-3-19.
 */

public class PopupActivity extends AppCompatActivity {

    TextView mInfo;
    View  bottom;
    int count = 0 ;
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
                //initBottom();
               // return;
                showDialog();
               /* if(count%2 ==0){
                   // showPopupWindow(R.layout.bottom_reply_layout);
                    showPopupWindow(R.layout.bottom_reply_layout);
                }
                else {
                    showPopupWindow(R.layout.bottom_top_layout);
                }
                count++;*/
               // showPopupWindow(R.layout.bottom_reply_layout);
                //showPopupWindow();
                //showDialog();
            }
        });


        bottom =Views.findViewById(this,R.id.bottom_layout);
        bottom.setClickable(false);



      /*  final ViewGroup rl = (ViewGroup) getWindow().getDecorView();

        rl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rl.getRootView().getHeight() - rl.getHeight();
                if (heightDiff > dpToPx(getApplicationContext(), 200)) { // if more than 200 dp, it's probably a keyboard...
                    // ... do something here
                    Log.d("TAG","aaaa");//显示
                }else {
                    if(null != popupWindow && popupWindow.isShowing()){
                       // popupWindow.dismiss();
                    }
                    Log.d("TAG","bbbb");//消失

                }
            }
        });*/
      /*  bottom.setFocusable(false);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  test();
               // showReply();
            }
        });*/
      //initBottom();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //initBottom();
    }

    View mRootView;
    EditText mEditBottom;
    private void  initBottom(){
        mRootView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_layout,null,false);
        mEditBottom = Views.findViewById(mRootView,R.id.reply_msg);

       ViewGroup vp =  (ViewGroup) getWindow().getDecorView();

        /*WindowManager.LayoutParams mParams  = new WindowManager.LayoutParams();
        mParams.height =(int)getResources().getDimension(R.dimen.bottom_height); //WindowManager.LayoutParams.MATCH_PARENT;//;
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.gravity = Gravity.BOTTOM;*/
        FrameLayout.LayoutParams mParams = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
        mParams.gravity = Gravity.BOTTOM;
      //  mRootView.setForegroundGravity(Gravity.BOTTOM);
        vp.addView(mRootView,mParams);


      /*  WindowManager  mWManager =(WindowManager) getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams mParams  = new WindowManager.LayoutParams();
        mParams.height =(int)getResources().getDimension(R.dimen.bottom_height); //WindowManager.LayoutParams.MATCH_PARENT;//;
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.gravity = Gravity.BOTTOM;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mWManager.addView(mRootView,mParams);
        mEditBottom.requestFocus();
        showInput();*/
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


    CustomFullScreenPopupWindow popupWindow;
    private void showPopupWindow(int layoutId){
        initPopupWindow();
        popupWindow.setView(layoutId);
        popupWindow.show();
        showInput();
    }

    EditText mEdit;
    private void showPopupWindow(){
        initPopupWindow();
        mRootView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_reply_layout,popupWindow.getRootView(),false);
       /* FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        params.gravity =Gravity.BOTTOM ;
        mRootView.setLayoutParams(params);*/
        mEditBottom = Views.findViewById(mRootView,R.id.reply_msg);
        popupWindow.setView(mRootView);
        popupWindow.show();
      //  mEditBottom.requestFocus();
      //  showInput();

    }

    private void initPopupWindow (){
        if(null == popupWindow){
            popupWindow = new CustomFullScreenPopupWindow(this);
        }
    }




    FullScreenDialogManager manager;
    private void showDialog(){
        if(null == manager){
            manager = new FullScreenDialogManager(this);
        }
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_window_layout,manager.getRoot(),false);
        manager.setView(view,Gravity.TOP);
        manager.show();

   /*     Dialog dialog = new Dialog(this,R.style.MyDialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_window_layout,null);
        //builder.setView(view);
        *//*builder.setView(view,0,0,0,0);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        AlertDialog dialog = builder.create();*//*
        dialog.setContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
       // dialog.setCanceledOnTouchOutside(true);
        dialog.show();*/
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    private void  showInput(){
        GlobalHandler.postMainThread(new Runnable() {
                                         @Override
                                         public void run() {
                                            // mEdit.requestFocus();
                                             InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Service.INPUT_METHOD_SERVICE);
                                             imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);//强制弹出键盘
                                         }
                                     }
                ,10);
    }

}
