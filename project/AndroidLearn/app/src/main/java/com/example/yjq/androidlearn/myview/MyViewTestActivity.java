package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by yjq on 2016/5/9.
 */
public class MyViewTestActivity extends Activity implements LinearAnimationView.CallBack{

    private static final String TAG = "MyViewTestActivity";
    SwitchCheckBox checkBox;
    ImageView imageView;
    LinearAnimationView linearAnimationView;
    FrameLayout fy;
    Button show;
    private int px=0;
    private int py=0;
    private MyEditViewInput edit;
    PopupWindow    popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview_test);
        checkBox = Views.findViewById(this, R.id.switchBox);
        fy = Views.findViewById(this, R.id.fy);
        imageView = Views.findViewById(this, R.id.image);
      //  imageView.setVisibility(View.INVISIBLE);
        fy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    doAnimation();
            }
        });
        linearAnimationView= new LinearAnimationView(getApplicationContext());
        linearAnimationView.addAnimatorFinish(this);
        show = Views.findViewById(this,R.id.showPopup);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View my = getLayoutInflater().inflate(R.layout.media_activity, null);
                my.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                popupWindow.setContentView(my);
                popupWindow.setTouchable(true);

                    popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shader));
                // popupWindow.setContentView(my);
                px =px-10;
                py =py-10;
                popupWindow.showAsDropDown(v,px,py);
            }
        });
        popupWindow  = new PopupWindow(null, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //  popupWindow.dismiss();
            }
        });

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.e(TAG, "popup touch");
                popupWindow.dismiss();
                return false;
            }
        });

        edit =Views.findViewById(this,R.id.edit);
       setViewSpan(edit);
        //edit.setText("ffewjj");
    }

    @Override
    public void onBackPressed() {
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
            return;
        }
        super.onBackPressed();
    }

    private void setViewSpan(TextView txtInfo){
        //android TextView、EditText对部分内容设置颜色、字体、超链接、图片;
        //这里是以一个TextView为例子，EditText的设置方法和TextView一样


        //文本内容
        SpannableString ss = new SpannableString("红色打电话粗体删除线绿色下划线图片:简直吊炸天了.");

        //设置0-2的字符颜色
        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //设置2-5的字符链接到电话簿，点击时触发拨号
        ss.setSpan(new URLSpan("tel:4155551212"), 2, 5,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //设置0-2的字符颜色
        ss.setSpan(new ForegroundColorSpan(Color.GRAY), 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //设置9-11的字符为网络链接，点击时打开页面
        ss.setSpan(new URLSpan("http://www.hao123.com"), 9, 11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置13-15的字符点击时，转到写短信的界面，发送对象为10086
        ss.setSpan(new URLSpan("sms:10086"), 13, 15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //粗体
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //删除线
        ss.setSpan(new StrikethroughSpan(), 7, 10,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

         ss.setSpan(new UnderlineSpan(), 10, 16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

         Drawable d = getResources().getDrawable(R.drawable.music);

        //设置图片大小
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());

        //插入的位置
       // ss.setSpan(new ImageSpan(d, ImageSpan.ALIGN_BASELINE), 18, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置文本内容到textView
        txtInfo.setText(ss);

        //不添加这一句，拨号，http，发短信的超链接不能执行.
        txtInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void  doAnimation(){
        int type =getType();
         linearAnimationView.setAnimationType(type);
        if(linearAnimationView.getParent() !=fy){
            fy.addView(linearAnimationView);
        }
        linearAnimationView.stop();
        linearAnimationView.startAnimator();

    }

    int animType = LinearAnimationView.ANIMATION_EXTENED;
    private  int getType(){
        return animType;
    }

    @Override
    public void animationfinish() {
        if(linearAnimationView.getAnimationType() ==LinearAnimationView.ANIMATION_EXTENED){
               // imageView.setVisibility(View.INVISIBLE);
            animType  =LinearAnimationView.ANIMATION_SHRINK;
        }
        else{
            fy.removeView(linearAnimationView);
            animType = LinearAnimationView.ANIMATION_EXTENED;
           // imageView.setVisibility(View.VISIBLE);
        }
    }
}
