package com.example.yjq.androidlearn;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yjq.androidlearn.myview.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    MyListAdapter myListAdapter;
    ImageView imageView;
    static Bitmap viewShot;
    View root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = Views.findViewById(this,R.id.root);
        listView = Views.findViewById(this, R.id.list_item);
        setAdapter(listView);

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
                myListAdapter.processItemOnClick(position, MainActivity.this);
            }
        });
    }

    private List<String> getListName() {
        List<String> nameList = new ArrayList<>();
        nameList.add("com.example.yjq.androidlearn.myview.SkewActivity");
        nameList.add("com.example.yjq.androidlearn.myview.ShaderActivity");
        nameList.add("com.example.yjq.androidlearn.myview.AnimationActivity");
        nameList.add("com.example.yjq.androidlearn.myview.AudioActivity");
        nameList.add("com.example.yjq.androidlearn.myview.VideoActivity");
        nameList.add("com.example.yjq.androidlearn.myview.CameraActivity");
        nameList.add("com.example.yjq.androidlearn.myview.RetrieveDataActivity");
        nameList.add("com.example.yjq.androidlearn.myview.ContactActivity");
        nameList.add("com.example.yjq.androidlearn.myview.ServiceActivity");
        nameList.add("com.example.yjq.androidlearn.myview.WebviewActivity");
        nameList.add("com.example.yjq.androidlearn.myview.MyViewTestActivity");
        nameList.add("com.example.yjq.androidlearn.myview.ViewPageActivity");
        nameList.add("com.example.yjq.androidlearn.myview.MyJNIActivity");
        nameList.add("com.example.yjq.androidlearn.myview.LeakActivity");
        nameList.add("com.example.yjq.androidlearn.animation.AnimationActivity");
        nameList.add("com.example.yjq.androidlearn.app.AppListActivity");
        nameList.add("com.example.yjq.androidlearn.graphics.GraphicsListActivity");
        return  nameList;
    }

    private void ControlShot(View view){
        if(null!=view){
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();;
            viewShot = view.getDrawingCache();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       // root.setBackgroundResource(R.anim.animation);
    }

    public static Bitmap getViewShot(){
        return  viewShot;
    }

}
