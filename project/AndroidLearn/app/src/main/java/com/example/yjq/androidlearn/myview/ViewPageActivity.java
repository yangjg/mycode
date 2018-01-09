package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.myview.compositeListView.RefreshableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by yjq on 2016/5/16.
 */
public class ViewPageActivity extends Activity implements ViewPager.OnPageChangeListener,MyHorizontalScrollView.OnClickCallBack {

    ViewPager pager;
    PagerTabStrip pagerTabStrip;
    PagerTitleStrip pagerTitleStrip;
    MyHorizontalScrollView hscroll;
    private List<View> viewList;
    private List<String> tilteList;
    private  LinearLayout view1,view2,view3;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page_activity);
        initView();
    }


    private void initView(){
        pager = Views.findViewById(this,R.id.pager);
        pager.setOnPageChangeListener(this);
        hscroll = Views.findViewById(this,R.id.hscroll);

        viewList = new ArrayList<>();
        int itemCount =10;
        MyListAdapter listAdapter = new MyListAdapter(getListName());
        for(int i=0;i<itemCount;i++){
            LinearLayout  v = (LinearLayout)getLayoutInflater().inflate(R.layout.list_view,null);
            ListView lv = Views.findViewById(v,R.id.listView);
            lv.setAdapter(listAdapter);
           /* lv.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    return false;
                }
            });*/
            viewList.add(v);
        }
        tilteList = new ArrayList<>();
        for(int i=0;i<viewList.size();i++){
            tilteList.add("厉害"+i);
        }

        hscroll.setAdapter(new HorizontScrollViewAdapter(tilteList));
        hscroll.setOnClickCallBack(this);

        pagerAdapter = new MyPagerAdapter();
        pager.setAdapter(pagerAdapter);

        Button set = Views.findViewById(this,R.id.setting);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = pager.getCurrentItem();
                LinearLayout ly = (LinearLayout)viewList.get(id);
                RefreshableListView rlv = Views.findViewById(ly,R.id.listView);
                rlv.setHeader();
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
        return  nameList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
         hscroll.onPageScrolled(position,positionOffset,positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        hscroll.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        hscroll.onPageScrollStateChanged(state);
    }

    @Override
    public void selectPosition(int position) {
        pager.setCurrentItem(position);
    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
              // super.destroyItem(container, position, object);
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
           // return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //
            return  tilteList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }
}
