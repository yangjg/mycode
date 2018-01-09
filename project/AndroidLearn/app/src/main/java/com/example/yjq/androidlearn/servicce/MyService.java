package com.example.yjq.androidlearn.servicce;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.Log;


import java.util.Random;

/**
 * Created by yjq on 2016/4/20.
 */
public class MyService extends Service {

    private static final String TAG = "MyService";
    final Binder binder=new LocalBinder();
    final Random generator = new Random();
    public MyService(){
       // super("MyService");
      //  IMyAidlInterface.Stub
       // forAcitivty.Stub
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }
/*
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle data = intent.getExtras();
        if(null!=data){
            int count = data.getInt("count");
            Log.e(TAG,String.valueOf(count));
        }
        Log.e(TAG, "onHandleIntent finish");
         // intent.getExtras();
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle data = intent.getExtras();
        if(null!=data){
            int count = data.getInt("count");
            Log.e(TAG,String.valueOf(count));
        }
       // Log.e(TAG, "onHandleIntent finish");
        Log.e(TAG, "onStartCommand:" + startId);

        Log.e(TAG, String.valueOf( android.os.Process.myPid()));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return binder;
    }


    public int getRandomNumber(){
        return  generator.nextInt(100);
    }

    public class LocalBinder extends Binder{

       public    MyService getService(){
              return  MyService.this;
          }
    }


}
