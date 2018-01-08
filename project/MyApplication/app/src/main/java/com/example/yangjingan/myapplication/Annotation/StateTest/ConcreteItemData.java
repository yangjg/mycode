package com.example.yangjingan.myapplication.Annotation.StateTest;

import com.example.yangjingan.myapplication.Annotation.StateTest.State.CanState;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.ItemObject;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Key;
import com.example.yangjingan.myapplication.Annotation.StateTest.State.Page;

/**
 * Created by yangjingan on 17-10-17.
 */
@CanState
public class ConcreteItemData extends BaseItemData {

    @Key("mTag")
    public String mTag;

    @ItemObject
    public AppItem appItem;

    @Page("testPage")
    public String page;
}
