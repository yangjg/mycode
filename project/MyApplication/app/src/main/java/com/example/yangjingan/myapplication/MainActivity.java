package com.example.yangjingan.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import com.example.yangjingan.myapplication.Annotation.AnnotationTest;
import com.example.yangjingan.myapplication.Annotation.StateTest.TestState;
import com.example.yangjingan.myapplication.CommonTest.ClassTest;
import com.example.yangjingan.myapplication.NdkTest.NdkActivity;
import com.example.yangjingan.myapplication.RSATest.RSAUtils;
import com.example.yangjingan.myapplication.RSATest.RsaActivity;
import com.example.yangjingan.myapplication.Webview.WebviewActivity;

import junit.framework.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private static Context gContext;
    public static Context getContext(){
        return gContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gContext = getApplicationContext();
        super.onCreate(savedInstanceState);
     //   getLoaderManager();
       // android.support.v4.app.LoaderManager.enableDebugLogging(true);
     //   LoaderManager.enableDebugLogging(true);
        Activity_R.setStatusBarDarkIcon(this,true);
        Activity_R.setStatusBarDarkIcon(this,false);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dowork();
            }
        });


      //  Activity_R.setStatusBarDarkIcon(this,true);
       /* HandlerThread ht  = new HandlerThread("test");
        ht.start();
        final Handler th = new Handler(ht.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MSG_BEGIN:
                       // printMsg();
                        msg.getTarget().sendEmptyMessageDelayed(MSG_BEGIN,500);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        th.sendEmptyMessageDelayed(MSG_BEGIN,500);*/

    }

    private static final int MSG_BEGIN =1;


    private  void printMsg(){
        String  activitys = getLauncherTopApp(this); // getLollipopRecentTask(); //getActivePackages();
        StringBuilder sb = new StringBuilder();
        sb.append(activitys);
       /* if(null != activitys){

            for (int i=0;i<activitys.length;i++){
                sb.append(activitys[i]);
                sb.append(",");
            }
        }*/
        Log.w(TAG,String.format("top:%s",sb.toString()));
       /* String top = getRunningTopActivityName();
        String base =getRunningTaskBaseActivityName();
        Log.w("TestTask",String.format("top:%s",top));
        Log.w("TestTask",String.format("base:%s",base));*/
    }

    private int mId =100001;
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
     /*   LoaderManager.LoaderCallbacks<Vector<Integer>> callback = new LoaderManager.LoaderCallbacks<Vector<Integer>>() {

            @Override
            public Loader<Vector<Integer>> onCreateLoader(int loadId, Bundle arg1) {
                AsyncTaskLoader<Vector<Integer>> loader = new DataLoader<Vector<Integer>>(getApplicationContext()) {
                    @Override
                    public Vector<Integer> loadInBackground() {
                        return new Vector<Integer>();
                    }
                };
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Vector<Integer>> loader, Vector<Integer> data) {
                getLoaderManager().destroyLoader(mId);
            }

            @Override
            public void onLoaderReset(Loader<Vector<Integer>> loader) {
                getLoaderMa

                nager().destroyLoader(mId);
            }
        };
       getLoaderManager().restartLoader(mId,null,callback);*/
    }

    private  void dowork(){

            Intent intent = new Intent();
        intent.setClass(this,NdkActivity.class);
        startActivity(intent);
      /*  try {
            for (int i=0;i<100;){
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       /* Intent intent = new Intent();
        intent.setClass(this,RsaActivity.class);
        startActivity(intent);*/
       // AnnotationTest.sInstance.doTest();
     /*   Intent intent = new Intent();
        intent.setClass(this,ActonBarActivity.class);
        startActivity(intent);*/

/*        AarTest test = new AarTest();

        test.mTag = 123;*/


      /*  Intent intent = new Intent();
        intent.setClass(this,AnimActivity.class);*/
      /*  Intent intent = creaeIntent();
        startActivity(intent);*/
     /*   intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://m.yizhibo.com/"));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
       /* ComponentName cn=new ComponentName("com.example.yangjingan.myapplication",
                "com.example.yangjingan.myapplication.CrossProcess.TestActivity");
        intent.setComponent(cn);*/
       // startActivity(intent);
        /*ClassTest ct = new ClassTest();
        Interpolator it = ct.getInterpolator();*/

     /*       Point point = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(point);
            Log.d(TAG,"the screen size is "+point.toString());*/
      //  TestState.getInstance().doTest();
       // AnnotationTest.sInstance.doTest();
    /*    Intent intent = new Intent(Intent.ACTION_INSERT,ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);*/
       /* Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        startActivity(intent);*/

/*        Intent addExistIntent = new Intent(Intent.ACTION_INSERT,ContactsContract.Contacts.CONTENT_URI);
        addExistIntent.putExtra(ContactsContract.Intents.Insert.PHONE, Uri.decode("15917526458"));
       // addExistIntent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        addExistIntent.putExtra(
                "com.android.contacts.extra.SHOW_CREATE_NEW_CONTACT_BUTTON"
					*//*ContactsContract.Intents.UI.SHOW_CREATE_NEW_CONTACT_BUTTON*//*,
                false);
        startActivity(addExistIntent);*/

     /*   Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
     //   intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME,"tew");

        intent.putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME, "few");

        startActivity(intent);*/

    /*    Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);*/
        /*Intent intent = new Intent();
        intent.setClass(getApplicationContext(), WebviewActivity.class);
        startActivity(intent);*/
      /*  Thread th = Thread.currentThread();
        String msg = String.format(" Method=%s, ThreadName = %s, Thread id = %s","dowork",th.getName(),th.getId());
        Log.e(TAG,msg);
        webView.loadUrl(DEFAULT_URL);*/
      /*  try {
            //String te=null;
           // boolean weew= te.equals("few");
            // Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public  String getRunningTopActivityName() {
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> la = activityManager.getAppTasks();
        ComponentName cp = activityManager.getRunningTasks(1).get(0).topActivity;
        String pm = cp.getPackageName();
        String runningActivity = cp.getClassName();

        String topActivityPm = String.format("pm=%s,activity=%s", pm, runningActivity);

        return topActivityPm;
    }

    public  String getRunningTaskBaseActivityName() {
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> la = activityManager.getAppTasks();
        ComponentName cp = activityManager.getRunningTasks(1).get(0).baseActivity;
        String pm = cp.getPackageName();
        String baseActivity = cp.getClassName();
        String baseActivityPm = String.format("pm=%s,activity=%s", pm, baseActivity);
        return baseActivityPm;
    }

    public Intent creaeIntent(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.baidu.com/"));
        Bundle bundle =  new Bundle();
        bundle.putString("_src_app_sdk_","com.test.yjg");
        bundle.putString("_src_page_sdk_","mainactivity");
        intent.putExtras(bundle);
        return intent;
    }


    private String[] getActivePackages() {

        final Set<String> activePackages= new HashSet<String>();

        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);


        final List<ActivityManager.RunningAppProcessInfo> processInfos =activityManager

                .getRunningAppProcesses();

       // activityManager.getRunningTasks()

       // int  processState = ActivityManager.RunningAppProcessInfo.getField("processState");
        Class clz = ActivityManager.RunningAppProcessInfo.class;
        Field field =null;
        try {
             field = clz.getField("processState");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        for(ActivityManager.RunningAppProcessInfo processInfo : processInfos) {


            int ps = 0;
            try {
                 ps = (int)field.get(processInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (processInfo.importance ==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND

                    &&ps ==2){

                activePackages.addAll(Arrays.asList(processInfo.pkgList));

                Log.d(TAG,"====processInfo.processState "

                        +ps + processInfo.processName);

            }

        }
        return activePackages.toArray(new String[activePackages.size()]);

    }

    private String getLollipopRecentTask() {
        final int PROCESS_STATE_TOP = 2;
        try {
            Field processStateField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            List<ActivityManager.RunningAppProcessInfo> processes = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo process : processes) {
                if (process.importance <= ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && process.importanceReasonCode == 0) {
                    int state = processStateField.getInt(process);
                    if (state == PROCESS_STATE_TOP) {
                        String[] packname = process.pkgList;
                        return packname[0];
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    private static UsageStatsManager sUsageStatsManager;
    public static String getLauncherTopApp(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List <ActivityManager.RunningTaskInfo> appTasks = activityManager.getRunningTasks(1);
            if (null != appTasks && !appTasks.isEmpty()) {
                return appTasks.get(0).topActivity.getPackageName();
            }
        } else {
            long endTime = System.currentTimeMillis();
            long beginTime = endTime - 10000;
            if (sUsageStatsManager == null) {
                sUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            }
            String result = "";
            UsageEvents.Event event = new UsageEvents.Event();
            UsageEvents usageEvents = sUsageStatsManager.queryEvents(beginTime, endTime);
            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event);
                if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                    result = event.getPackageName();
                }
            }
            if (!android.text.TextUtils.isEmpty(result)) {
                return result;
            }
        }
        return "";
    }


    // 此方法用来判断当前应用的辅助功能服务是否开启
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i(TAG, e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }

        return false;
    }

}
