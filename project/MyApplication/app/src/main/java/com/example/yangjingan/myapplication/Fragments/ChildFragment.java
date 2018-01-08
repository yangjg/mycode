package com.example.yangjingan.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yangjingan.myapplication.Levitated.LevitatedContainer;
import com.example.yangjingan.myapplication.Levitated.LevitatedFactory;
import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 17-11-27.
 */

public class ChildFragment  extends Fragment {

    FrameLayout fy;
    boolean isShow =false ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_child,container,false);
         fy = Views.findViewById(root,R.id.root_fy);
        TextView tv = Views.findViewById(root,R.id.childFragment);
        tv.setText("Fragment"+hashCode());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlowView();
            }
        });
        return  root ;
    }


    private void showFlowView(){
        if(!isShow){
            isShow = true ;
            LevitatedFactory.LevitatedInfo info = new LevitatedFactory.LevitatedInfo();
            info.hasColose = true ;
            info.with = 400;
            info.height =300 ;
            info.left_margin =10;
            info.bottom_margin =10;
            info.right_margin =10;
            info.top_margin =10;
            info.gravity = Gravity.RIGHT | Gravity.BOTTOM ;
            LevitatedContainer container =  LevitatedFactory.makeLevitated(getActivity(),info);
            //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams()
          //  container.setLayoutParams();
            fy.addView(container);

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
