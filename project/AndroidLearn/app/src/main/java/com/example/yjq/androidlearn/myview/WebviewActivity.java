package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.com.DimenUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yjq on 2016/4/25.
 */
public class WebviewActivity extends Activity {

    WebView webView;
    Button begin;
    EditText inputUrl;
    ImageView forward;
    ImageView backWard;
    ImageView home;
    ImageView menu;
    View toolbar;
    LinearLayout header;
    InputMethodManager inputMethodManager;
    FrameLayout root;
    private static final String DEFUALT_URL = "http://www.baidu.com/";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        //   setContentView(R.layout.webview_alone_layout);
        // webView =Views.findViewById(this,R.id.webview);
        initView();
        initWebViewSetting();


        // webView.loadUrl(DEFUALT_URL);
    }



    private void initView() {
        root =Views.findViewById(this,R.id.root);
        webView = Views.findViewById(this, R.id.webview);
        begin = Views.findViewById(this, R.id.begin);
        inputUrl = Views.findViewById(this, R.id.urlEdit);
        toolbar =Views.findViewById(this,R.id.toolbar);
        forward = Views.findViewById(this, R.id.forward);
        backWard = Views.findViewById(this, R.id.backward);
        home = Views.findViewById(this, R.id.home);
        menu = Views.findViewById(this, R.id.menu);
        header =Views.findViewById(this,R.id.header);
        inputMethodManager =(InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        inputUrl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //  onEnterEditMode();
                        break;
                    case MotionEvent.ACTION_UP:
                        // onLeaveEditMode();
                        break;

                }
                return false;
            }
        });
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // loadData();
            //    inputUrl.clearFocus();
              //  inputUrl.setVisibility(View.GONE);
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(WebviewActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                 goUrl(DEFUALT_URL);
              //  inputUrl.setVisibility(View.VISIBLE);
                //  onLeaveEditMode();


                //goUrl(inputUrl.getText().toString());
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }
            }
        });

        backWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearHistory();
                goUrl(DEFUALT_URL);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLastBottom = getResources().getDisplayMetrics().heightPixels;
       /* if(inputMethodManager.isActive()){
            onEnterEditMode();
        }*/

        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(null == mOrigine){
                    mOrigine = (FrameLayout.LayoutParams)root.getLayoutParams();
                }
                Rect r =new Rect();
                View view = getWindow().getDecorView();
                view.getWindowVisibleDisplayFrame(r);
                int bottom = r.bottom;
                if(bottom==mLastBottom){
                    return;
                }

                //键盘弹出来了.
                if(bottom<mLastBottom){
                    onEnterEditMode();
                }
                //键盘收起来了.
                else{
                    onLeaveEditMode();
                }
                mLastBottom =bottom;
            }
        });
    }

    private FrameLayout.LayoutParams mOrigine;
    private int mLastBottom;
    private  int mBottomWhenKeyboardHidden;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      //  onLeaveEditMode();
    }

    private void onEnterEditMode() {
          toolbar.setVisibility(View.GONE);

        ViewGroup.MarginLayoutParams params =(ViewGroup.MarginLayoutParams)header.getLayoutParams();
        params.bottomMargin =0;
    }

    private void onLeaveEditMode() {
        toolbar.setVisibility(View.VISIBLE);
        ViewGroup.MarginLayoutParams params =(ViewGroup.MarginLayoutParams)header.getLayoutParams();
        params.bottomMargin = DimenUtils.dp2px(this, 30.0f);
      //  inputUrl.clearFocus();
       // header.clearFocus();
       // inputUrl.setHint("ok");
     /*   inputUrl.setText(" ");
        inputUrl.clearFocus();*/
      //  header.clearFocus();
       /* if(getWindow().getAttributes().softInputMode== WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED)
        {
            //隐藏软键盘
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
           // getWindow().getAttributes().softInputMode=WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
        }*/
    }

    private void initWebViewSetting() {

        //设置支持webweiv缩放功能
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //设置支持javaScript功能
        webView.getSettings().setJavaScriptEnabled(true);

        //设置支持脚本弹出对话框，这个要单独设置
        webView.setWebChromeClient(new WebChromeClient());

        //如果不添加这一句，那么会调用系统默认的内置浏览器打开网页
        webView.setWebViewClient(new WebViewClient());

        webView.setInitialScale(50);
    }

    private void goUrl(String url) {
        webView.loadUrl(DEFUALT_URL);
     /*   URL uri ;
        String urlString;
        try {
            url =checkUrl(url);
             uri = new URL(url);
            urlString =uri.toString();
        } catch (MalformedURLException e) {
            urlString =DEFUALT_URL;
            e.printStackTrace();
        }
        webView.loadUrl(urlString);*/
        //webView.loadUrl(url);
        // webView.loadUrl("http://www.baidu.com/");

    }

    private String checkUrl(String url) {
        if (TextUtils.isEmpty(url)) return url;
        if (url.startsWith("https://") || url.startsWith("http://")) {
            return url;
        }
        url = "http://" + url;
        return url;
    }


    private void loadData() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div> 选择选项，然后从以下选项中进行选择：</div>");
        sb.append("<ul>");
        sb.append("<li>编辑内容：用于增加、移动和删除功能</li>");
        sb.append("<li>编辑内容：用于增加、移动和删除功能</li>");
        sb.append("<li>编辑内容：用于增加、移动和删除功能</li>");
        sb.append("</ul>");
        sb.append("<script>window.alert('hello');</script>");
        //webView.loadData(sb.toString(),"text/html","utf-8");
        webView.loadDataWithBaseURL(null, sb.toString(), null, "utf-8", null);
    }
}
