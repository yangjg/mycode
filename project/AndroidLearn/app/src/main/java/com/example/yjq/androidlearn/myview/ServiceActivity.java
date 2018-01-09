package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.forActivity;
import com.example.yjq.androidlearn.forService;
import com.example.yjq.androidlearn.servicce.MyAidlService;
import com.example.yjq.androidlearn.servicce.MyRemoveService;
import com.example.yjq.androidlearn.servicce.MyService;
import com.example.yjq.androidlearn.servicce.TimerService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yjq on 2016/4/20.
 */
public class ServiceActivity extends Activity implements ServiceConnection {

    private static final String TAG = "MyAidlService";//"ServiceActivity";
    Button start;
    Button stop;
    Button send;
    Intent intent = new Intent();
    Messenger mySender ;
    forService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        mySender = new Messenger(myHandler);
        start = Views.findViewById(this, R.id.start);
        stop = Views.findViewById(this, R.id.stop);
        send =Views.findViewById(this,R.id.send);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Log.e(TAG, "Pid:"+String.valueOf(android.os.Process.myPid()));
                Bundle bundle = new Bundle();
                bundle.putInt("count", 200);

                intent.setClass(ServiceActivity.this, MyAidlService.class);
             //   intent.setClass(ServiceActivity.this, MyRemoveService.class);
                //intent.setClass(ServiceActivity.this, MyService.class);
                intent.putExtras(bundle);

               boolean ok = bindService(intent, ServiceActivity.this, BIND_AUTO_CREATE);
             //   Log.e(TAG, "bind service success?" + ok);
                startService(intent);
                bounded =true;

           /*     Intent intent1 = new Intent(ServiceActivity.this, TimerService.class);
                startService(intent1);*/
                //startActivity(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bounded) {
                 //   stopService(intent);
                    stopService(intent);
                    unbindService(ServiceActivity.this);

                    messenger =null;
                    bounded=false;

                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(null!=service){
                try {
                    service.invokCallBack();
                    Log.e(TAG, "send service");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.e(TAG,e.getMessage());
                }
             /*   int a =8;
                int b =a*8;
                if(b>25){
                    Log.e(TAG, "b=="+b);
                }*/
              /*  int count = 0;
                try {
                    count = service.getLog();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/

            }
               /* if(null!=messenger){
                    Log.e(TAG," send time=="+ System.currentTimeMillis());
                    Message msg = Message.obtain(null,MyRemoveService.HELLO_WORLD,0,0);
                    try {
                        msg.replyTo = mySender;
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Log.e(TAG,e.getMessage());
                    }
                }

                Log.e(TAG," end send time=="+ System.currentTimeMillis());*/
            }
        });
    }

    boolean bounded = false;


    Messenger messenger;
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e(TAG, "connected name:" + name);

        this.service = com.example.yjq.androidlearn.forService.Stub.asInterface(service);
        try {
            this.service.registCall(mCallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG,e.getMessage());
        }
        int count = 0;
        try {
            count = this.service.getLog();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
      Log.e(TAG,"onServiceConnected TagCount=="+ String.valueOf(count));



      //  messenger = new Messenger(service);

       //  MyService service1 =(MyService)service;

        bounded = true;

        Log.e(TAG,"onServiceConnected time=="+ System.currentTimeMillis());
        Log.e(TAG,"onServiceConnected pid:"+String.valueOf(android.os.Process.myPid()));

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        messenger =null;
        bounded=false;
        service=null;
        ReentrantLock lock = new ReentrantLock();
       // lock.lockInterruptibly();
      //  lock.hasQueuedThreads()
        Log.e(TAG, "disconnected name:" + name);

    }

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    Log.e(TAG," myHandler time=="+ System.currentTimeMillis());
                    Log.e(TAG,"myHandler Ok");
                    Log.e(TAG, "MyHandler Pid:"+String.valueOf(android.os.Process.myPid()));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }

        }
    };

    private forActivity mCallBack =  new forActivity.Stub() {

        @Override
        public void perferm() throws RemoteException {
            int count = 0;
            try {
                count = service.getLog();
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
      /**/  Log.e(TAG,"perferm TagCount=="+ String.valueOf(count));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
            Log.e(TAG,"ServiceAcitivy perferm pid:"+String.valueOf(android.os.Process.myPid()));
            Toast.makeText(getApplicationContext(),"this forActivity perferm,pid:"+String.valueOf(android.os.Process.myPid()),Toast.LENGTH_LONG).show();
        }
    };
}
