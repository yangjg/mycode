package com.example.yjq.androidlearn.myview;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjq on 2016/4/11.
 */
public class MyListAdapter implements ListAdapter {

    private List<String> nameList = new ArrayList<>();

    public MyListAdapter(List<String> listName) {
        nameList =listName;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return CreateView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private View CreateView(int postion, View convertView, ViewGroup parent) {
        TextView textView;
        if (null == convertView) {
            textView = new TextView(parent.getContext());
            textView.setHeight(100);

        } else {
            textView = (TextView) convertView;
        }
        textView.setPadding(0,10,0,10);
        textView.setText(getShortName(nameList.get(postion)));
        textView.setTextColor(parent.getResources().getColor(R.color.textColor));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        return textView;
    }

    public void processItemOnClick(int position, Context context) {
        String className = nameList.get(position);
        Intent intent = new Intent();
        intent.setClassName(context, className);
        context.startActivity(intent);
    }

    private String getShortName(String fullName) {
        if (!TextUtils.isEmpty(fullName)) {
            int lastIndex = fullName.lastIndexOf('.');
            if (-1 != lastIndex) {
                return fullName.substring(lastIndex);
            }
        }
        return fullName;
    }
}
