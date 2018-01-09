package com.example.yjq.androidlearn.myview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;

import java.security.PrivilegedAction;
import java.util.List;

/**
 * Created by yjq on 2016/5/18.
 */
public class HorizontScrollViewAdapter implements Adapter {


        List<String> listTitle;
        ColorStateList colorStateList;
        public  HorizontScrollViewAdapter(List<String> list){
            listTitle =list;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return listTitle.size();
        }

        @Override
        public Object getItem(int position) {
            return listTitle.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return createView(position,convertView,parent);
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

        private View createView(int position, View convertView, ViewGroup parent){
            TextView tv;
            if(null ==convertView){
                tv = new TextView(parent.getContext());
            }
            else{
                tv =(TextView)convertView;
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity= Gravity.CENTER_VERTICAL;
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(20, 0, 20, 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,48);
            tv.setTextColor(createColorList(tv.getResources()));
            tv.setText(listTitle.get(position));
            tv.setLayoutParams(params);
            return tv;
        }

    private ColorStateList createColorList(Resources res){
        if(colorStateList==null){
            colorStateList = res.getColorStateList(R.color.news_tab_header_text_color_default);
          /*  int[][] states = new int[2][];
            states[0]=new int[]{android.R.attr.selectable};
            states[1]=new int[]{};
            int[] colors= new int[]{Color.BLUE,Color.BLACK};
             colorStateList = new ColorStateList(states,colors);*/
        }
        return colorStateList;
    }

}
