package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.example.yjq.androidlearn.R;

/**
 * Created by yjq on 2016/4/16.
 */
public class SettinActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settin);
    }

    public static boolean getBgSound(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bgsound",true);
    }
}
