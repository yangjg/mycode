package com.example.yangjingan.myapplication.Annotation.StateTest;

import com.example.yangjingan.myapplication.Annotation.StateTest.State.ItemObject;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Key;
import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.DefaultAllField;
import com.mz.statistic.annotations.DisableField;
import com.mz.statistic.annotations.InvalidValue;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.statisticbean.BaseBean;

/**
 * Created by yangjingan on 17-11-23.
 */

public class TestItem extends BaseBean {


    public String itemName;


    public String itemType;

    @StatisticsKey("ct")
    public String content;

    @InvalidValue(minValue = 3)
    public int Id;

    @ClassType
    public MetaItem metaItem;

    public TestItem(String name,String type,String content){
        itemName = name ;
        itemType = type ;
        this.content = content ;
    }


}
