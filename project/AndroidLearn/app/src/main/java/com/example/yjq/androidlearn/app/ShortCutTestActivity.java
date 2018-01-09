package com.example.yjq.androidlearn.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/7/4.
 */
public class ShortCutTestActivity extends Activity {

    private static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shortcut_activity);
        Button btn = Views.findViewById(this,R.id.create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 createShortcut();
            }
        });
    }

    private void createShortcut(){
        Intent start = new Intent("android.intent.action.TestShort");
        Intent intent =  new Intent(ACTION_INSTALL_SHORTCUT);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,start);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(getApplicationContext(),R.drawable.music));
        intent.putExtra("duplicate",true);
        getApplicationContext().sendBroadcast(intent);
    }

}
