package com.example.yangjingan.myapplication.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.MyBottomBar;
import com.ashokvarma.bottomnavigation.utils.Utils;
import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangjingan on 17-11-27.
 */

public class ActionBarFragment extends android.support.v4.app.Fragment {


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static  int STYLE = BottomNavigationBar.BACKGROUND_STYLE_STATIC;
    private static int MODE= BottomNavigationBar.MODE_FIXED;

    private String[] splites= new String[]{"1","2","3"};
    private List<Class<ChildFragment>> mList ;
    MyBottomBar mNavigationBar;
    BottomNavPageAdapter bottomNavPageAdapter;
    View root ;
    ViewPager  vp ;
    BottomNavigationItem tp ;
    BadgeItem numberBadgeItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_actionbar,container,false);
        TextView tv =Views.findViewById(root,R.id.red);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 appearRed();
            }
        });
        return  root ;
       // return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.removeAllTabs();
        initTab();

    }

    private void appearRed(){
        numberBadgeItem.setBorderWidth(2)
                .setText("o")
                .setTextColor(Color.RED)
                .setBorderColor(Color.RED)
                .setBackgroundColor(Color.RED).show(false);


    }

    private void initTab(){
        if(null != root){
            mList = new ArrayList<>();
            mList.add(ChildFragment.class);
            mList.add(ChildFragment.class);
            mList.add(ChildFragment.class);
            mList.add(ChildFragment.class);
            mList.add(ChildFragment.class);


            bottomNavPageAdapter = new BottomNavPageAdapter(mList,getChildFragmentManager());
            vp = Views.findViewById(root,R.id.base_pager);
            mNavigationBar = Views.findViewById(root,R.id.bottom_navigation_bar);
           // FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mNavigationBar.getLayoutParams();
            mNavigationBar.setPadding(50,0,50,0);

            ViewPagerChangeListener listener = new ViewPagerChangeListener(mNavigationBar,vp);

            vp.setAdapter(bottomNavPageAdapter);
            vp.addOnPageChangeListener(listener);

            STYLE = STYLE %3 ;

            mNavigationBar.setMode(MODE);
            mNavigationBar.setBackgroundStyle(STYLE);
           // mNavigationBar.setBackgroundColor(Color.GREEN);
           // mNavigationBar.setBarBackgroundColor()

            //COUNT++;
            if(STYLE ==3){
                //MODE ++;
                MODE = MODE%3 ;
            }
           /* public static final int BACKGROUND_STYLE_DEFAULT = 0;
            public static final int BACKGROUND_STYLE_STATIC = 1;*/
           // mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
            /**
             *添加标签的消息数量
             */
            numberBadgeItem = new BadgeItem()
                    .setBorderWidth(0)
                    .setBorderColor(Color.TRANSPARENT)
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setText(null)
                    .setTextColor(Color.TRANSPARENT)
                    .setHideOnSelect(true);
            numberBadgeItem.hide(false);
            BadgeItem numberBadgeItem2 = new BadgeItem()
                    .setBorderWidth(4)
                    .setBackgroundColor(Color.RED)
                    .setText("5")
                    .setHideOnSelect(true);
            /**
             *添加tab标签页
             */
         /*   mNavigationBar
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books").setActiveColorResource(R.color.ic_menu_toolbar_more_day).setBadgeItem(numberBadgeItem2))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music").setActiveColorResource(R.color.colorPrimary))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV").setActiveColorResource(R.color.ic_menu_toolbar_more_day))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games").setActiveColorResource(R.color.colorPrimary).setBadgeItem(numberBadgeItem))
                    .initialise();  */
      /*      mNavigationBar
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books").setBadgeItem(numberBadgeItem2))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music"))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music"))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV"))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games").setBadgeItem(numberBadgeItem))
                    .initialise();*/

            tp = new BottomNavigationItem(R.mipmap.ic_main_game, "Games").setBadgeItem(numberBadgeItem);
            mNavigationBar
                    .addItem(new BottomNavigationItem(R.mipmap.ic_main_app_select, "Books").setInactiveIconResource(R.mipmap.ic_main_app).setBadgeItem(numberBadgeItem2))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_main_feed_select, "Music").setInactiveIconResource(R.mipmap.ic_main_feed))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_main_discover, "Music"))
                    .addItem(new BottomNavigationItem(R.mipmap.ic_main_rank, "Movies & TV"))
                    .addItem(tp)
                    .initialise();
            /**;
             *首次进入不会主动回调选中页面的监听
             *所以这里自己调用一遍，初始化第一个页面
             */
            mNavigationBar.setTabSelectedListener(listener);

        }
    }


    protected ActionBar getActionBar(){
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    private static class ViewPagerChangeListener implements ViewPager.OnPageChangeListener,MyBottomBar.OnTabSelectedListener {

        private  MyBottomBar mBottomBar;
        private ViewPager mVp;

        public ViewPagerChangeListener(MyBottomBar navBar,ViewPager vp){
            mBottomBar = navBar ;
            mVp = vp ;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mBottomBar.selectTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onTabSelected(int position) {
            mVp.setCurrentItem(position);
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    }

}
