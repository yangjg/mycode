package com.example.yjq.androidlearn.app.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/14.
 */
public class ABTabsActivity extends Activity {
    Button add;
    Button removeLast;
    Button toggle;
    Button removeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.abtabs_activity);
        add = Views.findViewById(this,R.id.addtabs);
        removeLast =Views.findViewById(this,R.id.removeLast);
        toggle = Views.findViewById(this,R.id.toggle);
        removeAll =Views.findViewById(this,R.id.removeall);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar bar = getActionBar();
                String text = "Tab" + bar.getTabCount();
                 bar.addTab(
                         bar.newTab().
                         setText(text).
                         setTabListener(new MyTabListener(new TabFragmentContent(text))));
            }
        });

        removeLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar bar = getActionBar();
                if(bar.getTabCount()>0){
                    bar.removeTabAt(bar.getTabCount()-1);
                }
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ActionBar bar = getActionBar();

                if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
                    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
                } else {
                    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                    bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
                }
            }
        });

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar bar = getActionBar();
                bar.removeAllTabs();
            }
        });
    }


    private class MyTabListener implements ActionBar.TabListener{

        TabFragmentContent  mFragment;
        public MyTabListener(TabFragmentContent fragment){
            mFragment  =fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.add(R.id.frag_container,mFragment,mFragment.getText());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }


    private   class TabFragmentContent extends Fragment{
        String mText;
         public TabFragmentContent(String text){
             mText = text;
         }

        public String getText(){
            return  mText;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View fragView = inflater.inflate(R.layout.action_bar_tab_content, container, false);

            TextView text = (TextView) fragView.findViewById(R.id.text);
            text.setText(mText);

            return fragView;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
