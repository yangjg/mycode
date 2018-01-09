package com.example.yjq.androidlearn.app.actionbar;

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
 * Created by yjq on 2016/6/14.
 */
public class ActionBarListActivity extends Activity {
    MyListAdapter myListAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist_activity);
        list = Views.findViewById(this, R.id.listView);
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
                myListAdapter.processItemOnClick(position, ActionBarListActivity.this);
            }
        });
    }

    private List<String> getListName() {
        List<String> nameList = new ArrayList<>();
        nameList.add("com.example.yjq.androidlearn.app.actionbar.ABMechanicsActivity");
        nameList.add("com.example.yjq.androidlearn.app.actionbar.ABTabsActivity");
        nameList.add("com.example.yjq.androidlearn.app.actionbar.ABUsageActivity");
        nameList.add("com.example.yjq.androidlearn.app.actionbar.ABProviderActivity");
        nameList.add("com.example.yjq.androidlearn.app.actionbar.ABDisplayActivity");


        return  nameList;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
