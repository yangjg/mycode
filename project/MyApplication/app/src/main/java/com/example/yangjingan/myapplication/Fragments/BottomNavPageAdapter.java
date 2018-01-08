package com.example.yangjingan.myapplication.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangjingan on 17-11-27.
 */

public class BottomNavPageAdapter<T extends Fragment> extends FragmentPagerAdapter {


    final List<Class<T>> mFragmentNames;


    public BottomNavPageAdapter(List<Class<T>> listName, FragmentManager fm) {
        super(fm);
        mFragmentNames = listName ;
        if(null == listName || listName.isEmpty()){
            throw new IllegalArgumentException("fragments name error");
        }
    }

    @Override
    public Fragment getItem(int position) {
        Class<T>  fragment = mFragmentNames.get(position);
        Fragment ft =null ;
        try {
             ft = fragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ft ;
    }

    @Override
    public int getCount() {
        return mFragmentNames.size();
    }
}
