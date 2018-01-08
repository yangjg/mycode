package com.example;

/**
 * Created by yangjingan on 17-4-5.
 */
public class LineDataItem {


    private String mFirst;
    private String mLast;

    public LineDataItem(String firstText, String secondText) {
        if(null == firstText){
            firstText="";
        }
        if(null==secondText){
            secondText="";
        }
        mFirst = firstText;
        mLast = secondText;
    }


    public String getFirst(){
        return  mFirst;
    }

    public String getLast(){
        return  mLast;
    }

    public String toString(){
        return mFirst + mLast;
    }

}
