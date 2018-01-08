package com.example.yangjingan.myapplication.Annotation.StateTest;

import com.example.yangjingan.myapplication.Annotation.StateTest.State.Key;
import com.mz.statistic.annotations.DefaultAllField;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.statisticbean.BaseBean;

/**
 * Created by yangjingan on 17-10-17.
 */
@DefaultAllField
public class MetaItem extends BaseBean {

    public MetaItem(int id,String action){
        mId = id ;
        mAction = action ;
    }

    @Key("id")
    @StatisticsKey(value = "metaid",minValue = 1)
    public int mId;

    @Key("action")
    public String mAction;
}
