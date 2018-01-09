package com.example.yjq.androidlearn.servicce;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;
/**
 * Created by yjq on 2016/4/20.
 */
public class MyRemoveService extends Service {

    private static final String TAG = "MyRemoveService";
    final Random generator = new Random();
    final Messenger messenger = new Messenger(new MsgHandle());
    public MyRemoveService(){
        // super("MyService");
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
        Log.e(TAG,  "Pid:"+String.valueOf( android.os.Process.myPid()));
        return messenger.getBinder();
    }


    public int getRandomNumber(){
        return  generator.nextInt(100);
    }

    public final static int HELLO_WORLD=1;
    class MsgHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HELLO_WORLD:
                    try {
                        Thread.currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG,"handleMessage time=="+ System.currentTimeMillis());
                    Log.e(TAG,"hello world");
                    Log.e(TAG, "Pid:"+String.valueOf(android.os.Process.myPid()));
                    Messenger messenger = msg.replyTo;
                    Message newMsg =Message.obtain(null,2,0,0);
                    try {
                        messenger.send(newMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Log.e(TAG,e.getMessage());
                    }
                    break;
                default:super.handleMessage(msg);
            }
        }
    }




}
