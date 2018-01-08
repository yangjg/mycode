package com.example;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by yangjingan on 17-3-29.
 */
public class DataItem {

    private int mId;
    private String mOriginString;
    private String mItemString;
    private boolean mShouldRemoveRepeat;

    private static  int sId=0;

    public DataItem(String text,String originString,boolean removeRepeat){
        mId = sId++;
        mItemString =text;
        mOriginString  = originString;
        mShouldRemoveRepeat = removeRepeat;
    }


    public boolean getShouldRemoveRepeat(){
        return mShouldRemoveRepeat;
    }

    public String getText(){
        return  mItemString;
    }

    public String getOriginString(){
        return  mOriginString;
    }

    public int getId(){
        return  mId;
    }

    public void setId(int id){
        mId = id;
    }

    public String toString(){
        if(null != mOriginString || ""==mOriginString){
           return mOriginString;
            //return  String.valueOf(mId) +"." + mOriginString;
        }
        return  mItemString;//String.valueOf(mId) +"." + mItemString;
    }


}
