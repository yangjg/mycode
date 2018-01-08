package com.example.yangjingan.myapplication.BroadCast;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yangjingan on 17-1-4.
 */
public class SendUserChangeBroadCast {

    private final static String ACTION ="android.accounts.LOGIN_ACCOUNTS_CHANGED";

    public static   void  sendBroadCast(Context context){
        Intent intent  = new Intent();
        intent.setAction(ACTION);
        context.sendBroadcast(intent);

    }


}
