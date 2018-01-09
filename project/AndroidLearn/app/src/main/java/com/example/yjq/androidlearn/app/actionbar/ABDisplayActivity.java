package com.example.yjq.androidlearn.app.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/15.
 */
public class ABDisplayActivity extends Activity implements View.OnClickListener ,ActionBar.TabListener{

    Button homeAsUp;
    Button showHome;
    Button useLog;
    Button showTitle;
    Button showCustom;
    Button navigation;
    Button customGravity;
     View mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.action_bar_display_options);
        mCustomView = getLayoutInflater().inflate(R.layout.action_bar_display_options_custom, null);
        // Configure several action bar elements that will be toggled by display options.
        final ActionBar bar = getActionBar();
        bar.setCustomView(mCustomView,
                new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        initTab();
        homeAsUp = Views.findViewById(this,R.id.toggle_home_as_up);
        showHome =Views.findViewById(this,R.id.toggle_show_home);
        useLog = Views.findViewById(this,R.id.toggle_use_logo);
        showTitle =Views.findViewById(this,R.id.toggle_show_title);
        showCustom = Views.findViewById(this,R.id.toggle_show_custom);
        navigation =Views.findViewById(this,R.id.toggle_navigation);
        customGravity =Views.findViewById(this,R.id.cycle_custom_gravity);

        homeAsUp.setOnClickListener(this);
        showHome.setOnClickListener(this);
        useLog.setOnClickListener(this);
        showTitle.setOnClickListener(this);
        showCustom.setOnClickListener(this);
        navigation.setOnClickListener(this);
        customGravity.setOnClickListener(this);

    }
    public void onClick(View v) {
        final ActionBar bar = getActionBar();
        int flags = 0;
        switch (v.getId()) {
            case R.id.toggle_home_as_up:
                flags = ActionBar.DISPLAY_HOME_AS_UP;
                break;
            case R.id.toggle_show_home:
                flags = ActionBar.DISPLAY_SHOW_HOME;
                break;
            case R.id.toggle_use_logo:
                flags = ActionBar.DISPLAY_USE_LOGO;
                break;
            case R.id.toggle_show_title:
                flags = ActionBar.DISPLAY_SHOW_TITLE;
                break;
            case R.id.toggle_show_custom:
                flags = ActionBar.DISPLAY_SHOW_CUSTOM;
                break;

            case R.id.toggle_navigation:
                bar.setNavigationMode(
                        bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD
                                ? ActionBar.NAVIGATION_MODE_TABS
                                : ActionBar.NAVIGATION_MODE_STANDARD);
                return;
            case R.id.cycle_custom_gravity:
                ActionBar.LayoutParams lp = (ActionBar.LayoutParams) mCustomView.getLayoutParams();
                int newGravity = 0;
                switch (lp.gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
                    case Gravity.START:
                        newGravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case Gravity.CENTER_HORIZONTAL:
                        newGravity = Gravity.END;
                        break;
                    case Gravity.END:
                        newGravity = Gravity.START;
                        break;
                }
                lp.gravity = lp.gravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK | newGravity;
                bar.setCustomView(mCustomView, lp);
                return;
        }

        int change = bar.getDisplayOptions() ^ flags;
        bar.setDisplayOptions(change, flags);
    }

    private void initTab(){
        ActionBar bar = getActionBar();
        for (int i=0;i<3;i++){
            String text = "Tab" + bar.getTabCount();
            bar.addTab(
                    bar.newTab().setText(text).setTabListener(this));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
