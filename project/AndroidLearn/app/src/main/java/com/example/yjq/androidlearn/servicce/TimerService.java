package com.example.yjq.androidlearn.servicce;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.yjq.androidlearn.MainActivity;
import com.example.yjq.androidlearn.myview.CameraActivity;
import com.example.yjq.androidlearn.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yjq on 2016/4/22.
 */
public class TimerService extends Service {


    Timer timer;
    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager nm =(NotificationManager)getSystemService(ns);
                CharSequence title = "title";
                CharSequence text ="text";
                Intent intent1 =new Intent(TimerService.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(TimerService.this,0,intent1,PendingIntent.FLAG_CANCEL_CURRENT);

             //   Notification.Action actionBuider = new Notification.Action(R.drawable.music,"appactivity",pendingIntent);
               // Notification.Action.Builder actionBuider  =  //new Notification.Action.Builder(R.drawable.music,"appactivity",pendingIntent);
                Notification.Builder builder = new Notification.Builder(TimerService.this);
                builder.setSmallIcon(R.drawable.music);
                builder.setTicker("tickerText");
                builder.setWhen(System.currentTimeMillis());
                builder.setContentTitle(title);
                builder.setContentText(text);
                builder.setContentIntent(pendingIntent);
   /*             builder.addAction(R.drawable.music, "appactivity", pendingIntent);
                builder.addAction(R.drawable.music,"appactivity",pendingIntent);
                builder.addAction(R.drawable.music,"appactivity",pendingIntent);*/
           /*     builder.addAction(actionBuider.clone());
                builder.addAction(actionBuider.clone());
                builder.addAction(actionBuider.clone());*/
                Notification notification = builder.build();
                notification.flags = Notification.FLAG_NO_CLEAR|Notification.FLAG_ONGOING_EVENT;
                nm.notify(0,notification);
                TimerService.this.stopSelf();

            }
        },6000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
