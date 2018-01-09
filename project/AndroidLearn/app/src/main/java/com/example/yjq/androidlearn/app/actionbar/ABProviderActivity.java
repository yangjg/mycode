package com.example.yjq.androidlearn.app.actionbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ShareActionProvider;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Inflater;

/**
 * Created by yjq on 2016/6/15.
 */
public class ABProviderActivity extends Activity {

    /** An intent for launching the system settings. */
    private static final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
    private static final String DEFAULT_FILE_NAME="share.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_ACTION_BAR);
        initDefaultFileName();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.providemenu, menu);

        MenuItem setting = menu.findItem(R.id.setting);

        MenuItem share =menu.findItem(R.id.share);
        ShareActionProvider shareActionProvider =(ShareActionProvider)share.getActionProvider();
        shareActionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        shareActionProvider.setShareIntent(createShareIntent());
        return  true;
      //  return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        Uri uri = Uri.fromFile(getFileStreamPath(DEFAULT_FILE_NAME));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return shareIntent;
    }

    private void initDefaultFileName(){

        new Thread(){
            @Override
            public void run() {
                InputStream inputStream= getResources().openRawResource(R.raw.robot);
                OutputStream outputStream=null;
                try {
                     outputStream = openFileOutput(DEFAULT_FILE_NAME,Context.MODE_WORLD_READABLE|Context.MODE_APPEND);
                     byte[] buf = new byte[1024];
                    int count;
                    while (  (count = inputStream.read(buf))>0){
                        outputStream.write(buf,0,count);
                    }
                }
                catch (Exception e){

                }
                finally {
                    if(null !=inputStream){
                        try {
                            inputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if(null!=outputStream){
                        try {
                            outputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }.start();


    }


    public static class SettingProvider extends ActionProvider{

        private  Context mContext;
        /**
         * Creates a new instance. ActionProvider classes should always implement a
         * constructor that takes a single Context parameter for inflating from menu XML.
         *
         * @param context Context for accessing resources.
         */
        public SettingProvider(Context context) {
            super(context);
            mContext =context;
        }

        @Override
        public View onCreateActionView() {
           LayoutInflater inflater =    LayoutInflater.from(mContext);
            View root = inflater.inflate(R.layout.action_bar_settings_action_provider,null);
            ImageButton view = Views.findViewById(root,R.id.button);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startToSetting();

                }
            });
            return root;
        }

        @Override
        public boolean onPerformDefaultAction() {
            startToSetting();
            return true;
        }

        private void startToSetting(){
            mContext.startActivity(sSettingsIntent);
        }
    }

}
