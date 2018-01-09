package com.example.yjq.androidlearn.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.myview.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/6/2.
 */
public class AnimationActivity extends Activity {

    MyListAdapter myListAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main_activity);
        list = Views.findViewById(this,R.id.list);
        initAdapter();

    }

    private void initAdapter(){
        setAdapter(list);
    }

    private void setAdapter(ListView listView) {
        // listView.setAdapter();
        if(null==myListAdapter){
            myListAdapter = new MyListAdapter(getListName());
        }
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myListAdapter.processItemOnClick(position, AnimationActivity.this);
            }
        });
    }

    private List<String> getListName() {
        List<String> nameList = new ArrayList<>();
        nameList.add("com.example.yjq.androidlearn.animation.BouncingBallActivity");
        nameList.add("com.example.yjq.androidlearn.animation.CloningActivity");
        nameList.add("com.example.yjq.androidlearn.animation.CustomEvaluatorActivity");
        nameList.add("com.example.yjq.androidlearn.animation.DefaultLayoutAnimationActivity");
        nameList.add("com.example.yjq.androidlearn.animation.AniationEventActivity");
        nameList.add("com.example.yjq.androidlearn.animation.HideShowAnimationActivity");
        nameList.add("com.example.yjq.androidlearn.animation.LayoutAnimationActivity");
        nameList.add("com.example.yjq.androidlearn.animation.LoadingActivity");
        nameList.add("com.example.yjq.androidlearn.animation.MultiplePropertiesActivity");
        nameList.add("com.example.yjq.androidlearn.animation.ReversingActivity");
        nameList.add("com.example.yjq.androidlearn.animation.SeekingActivity");
        nameList.add("com.example.yjq.androidlearn.animation.FlipActivity");



        return  nameList;
    }
}
