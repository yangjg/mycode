package com.example.yangjingan.myapplication.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yangjingan on 16-12-26.
 */
public class PrefrenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onBuildHeaders(List<Header> target) {
        //super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.pref_header,target);

        if(target.size()>0){
            //TODO 设置HEADER的样式。
          //  setListAdapter(new HeaderAdapter(target,LayoutInflater.from(getApplicationContext())));
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return  true;//super.isValidFragment(fragmentName);
    }

    private static class HeaderAdapter extends BaseAdapter{

        private  List<Header> mTarget;
        private LayoutInflater mInflater;
        public HeaderAdapter(List<Header> target, LayoutInflater inflater){
            mTarget = target;
            mInflater =inflater;
        }


        @Override
        public int getCount() {
            return mTarget.size();
        }

        @Override
        public Object getItem(int position) {
            return mTarget.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return createView(position,convertView,parent);
        }


        private View createView(int position, View convertView, ViewGroup parent){

            Header header  = mTarget.get(position);
            View view ;
            if(null == convertView){
                  view = mInflater.inflate(R.layout.header_layout,parent);

            }
            else{
                view  = convertView;
            }

            TextView tv = (TextView) view.findViewById(R.id.header);
            tv.setText("HAHA");

            return view;

        }
    }
}
