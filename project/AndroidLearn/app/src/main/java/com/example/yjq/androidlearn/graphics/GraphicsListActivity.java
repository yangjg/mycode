package com.example.yjq.androidlearn.graphics;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.myview.MyListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjq on 2016/7/7.
 */
public class GraphicsListActivity extends Activity {

    MyListAdapter myListAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist_activity);
        list = Views.findViewById(this, R.id.listView);
        initAdapter();

        ActionBar ac = getActionBar();
        ac.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }
   private final String action="android.intent.action.MAIN";
    private  final String prelable= "graphic";
    private void initAdapter(){
        setAdapter(list);
    }

    private void setAdapter(ListView listView) {
        if(null==myListAdapter){
            myListAdapter = new MyListAdapter(getListName());
        }
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               myListAdapter.processItemOnClick(position, GraphicsListActivity.this);
            }
        });
    }


    Map<Integer,Intent> map = new HashMap<>();

    private List<String> getListName() {
        List<String> nameList = new ArrayList<>();
        nameList.add("com.example.yjq.androidlearn.graphics.AnimationMeshActivity");
        nameList.add("com.example.yjq.androidlearn.graphics.PatternsActivity");
        nameList.add("com.example.yjq.androidlearn.graphics.AnyViewTestActivity");
        nameList.add("com.example.yjq.androidlearn.graphics.TouchPaintActivity");
        return  nameList;

    }
}
