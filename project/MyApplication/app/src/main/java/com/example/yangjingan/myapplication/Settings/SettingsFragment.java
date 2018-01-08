package com.example.yangjingan.myapplication.Settings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangjingan.myapplication.R;

/**
 * Created by yangjingan on 16-12-26.
 */
public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";

    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        root =inflater.inflate(R.layout.pref_settings_fragments, container, false);

        return  root;
    }
}
