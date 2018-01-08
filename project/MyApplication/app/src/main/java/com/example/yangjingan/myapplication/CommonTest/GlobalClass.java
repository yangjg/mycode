package com.example.yangjingan.myapplication.CommonTest;

import android.os.Handler;
import android.util.Log;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjingan on 17-5-18.
 */
public class GlobalClass {


    private Handler handler ;//= new Handler();

    private static GlobalClass sIn = new GlobalClass();

    private GlobalClass(){

         handler = new Handler();
    }


    public  static GlobalClass getInstance(){
        return sIn;
    }

    public void dowork(){
        Log.e("classtest","few");
      /*  try{
            List<User> users = new ArrayList<>();
            users.add(new User("123")); //will throw NoClassDefFoundError
        }catch(Throwable t){
           // Log.e("test",t.getMessage());
            t.printStackTrace();
        }*/
       /* try{
            List<User> users = new ArrayList<>();
            users.add(new User("123")); //will throw NoClassDefFoundError
        }
        catch(Exception t){
            if(null != t){
                t.printStackTrace();
                //Log.e("test",t.printStackTrace());
            }else{
                Log.e("test","t is null");
            }
          //  t.printStackTrace();
        }
        catch (Throwable ee){

            if(null != ee){
                ee.printStackTrace();
               // Log.e("test",ee.printStackTrace());
            }else{
                Log.e("test","ee is null");
            }
        }
        finally {
            Log.e("test","ok");
        }*/

    }


}
