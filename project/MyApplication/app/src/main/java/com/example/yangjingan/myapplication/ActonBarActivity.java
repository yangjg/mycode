package com.example.yangjingan.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.yangjingan.myapplication.Fragments.ActionBarFragment;

/**
 * Created by yangjingan on 17-11-27.
 */

public class ActonBarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main_activity);
        //setContentView(R.layout.activity_actionbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ActionBarFragment appMainPagerFragment = new ActionBarFragment();
        appMainPagerFragment.setArguments(getIntent().getExtras());
        fragmentTransaction.replace(R.id.main_container, appMainPagerFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }






}
