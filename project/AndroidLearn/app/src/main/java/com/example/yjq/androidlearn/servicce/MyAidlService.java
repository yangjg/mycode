package com.example.yjq.androidlearn.servicce;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yjq.androidlearn.forActivity;
import com.example.yjq.androidlearn.forService;

/**
 * Created by yjq on 2016/4/22.
 */
public class MyAidlService extends Service {


    public static int tagCount =0;
    private forService.Stub mBinder;
    private static final String TAG = "MyAidlService";
    public MyAidlService(){
        mBinder = new MyBinder();
    }

    @Override
    public void onCreate() {
        tagCount =0;
        Log.e(TAG,"onCreate");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind TagCount=="+String.valueOf(++MyAidlService.tagCount));
        Log.e(TAG,"onBind");
        Log.e(TAG,"onBind: pid:"+String.valueOf(android.os.Process.myPid()));
        return mBinder;


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends forService.Stub{
        forActivity callBack;
        @Override
        public void registCall(forActivity cb) throws RemoteException {
                callBack =cb;
        }

        @Override
        public void invokCallBack() throws RemoteException {
            Log.e(TAG,"invokCallBack: pid:"+String.valueOf(android.os.Process.myPid()));
             if(null!=callBack){
                 callBack.perferm();
             }
            Log.e(TAG,"invokCallBack TagCount=="+String.valueOf(++MyAidlService.tagCount));
           // Log.e(TAG,"invokCallBack: pid:"+String.valueOf(android.os.Process.myPid()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }

            Log.e(TAG," time=="+ System.currentTimeMillis());
        }

        @Override
        public int getLog() throws RemoteException {
            int m = ++MyAidlService.tagCount;
           // MyAidlService.tagCount++;
            return m;
        }


    }
}
