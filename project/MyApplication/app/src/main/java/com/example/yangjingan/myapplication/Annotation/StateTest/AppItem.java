package com.example.yangjingan.myapplication.Annotation.StateTest;

import com.example.yangjingan.myapplication.Annotation.StateTest.State.ItemObject;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Key;

/**
 * Created by yangjingan on 17-10-17.
 */

public class AppItem {



    @Key("itemName")
    public String itemName;

    @Key("itemType")
    public String itemType;

    @Key("content")
    public String content;

    @ItemObject
    public MetaItem metaItem;

    public AppItem(String name,String type,String content){
        itemName = name ;
        itemType = type ;
        this.content = content ;
    }


}
