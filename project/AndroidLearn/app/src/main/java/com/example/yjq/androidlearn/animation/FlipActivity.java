package com.example.yjq.androidlearn.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

/**
 * Created by yjq on 2016/6/8.
 */
public class FlipActivity extends Activity {

    Button run;
    Button reverse;
    LinearLayout root;
    ListView enList;
    ListView cnList;
    String[] en = new String[]{"1","2","3","4","5"};
    String[] cn = new String[]{"一","二","三","四","五"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflip_activity);
        root = Views.findViewById(this, R.id.container);
        run = Views.findViewById(this,R.id.run);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration =1500;
                final ListView invisbleList;
                final ListView visbileList;
                if(enList.getVisibility() ==View.GONE){
                    invisbleList = enList;
                    visbileList =cnList;
                }
                else{
                    invisbleList =cnList;
                    visbileList =enList;
                }
                enList.setCameraDistance(4000);
                cnList.setCameraDistance(4000);
                ObjectAnimator vianim = ObjectAnimator.ofFloat(visbileList, "rotationY",0,90f);
                vianim.setDuration(duration);

                final ObjectAnimator inanim = ObjectAnimator.ofFloat(invisbleList,"rotationY",-90f,0);
                inanim.setDuration(duration);

               // AnimatorSet set = new AnimatorSet();

                vianim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        visbileList.setVisibility(View.GONE);
                        inanim.start();
                        invisbleList.setVisibility(View.VISIBLE);

                    }
                });

                vianim.start();

            }
        });

        enList =Views.findViewById(this,R.id.enlist);
        cnList =Views.findViewById(this,R.id.cnlist);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,en);
        ArrayAdapter<String> cnAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cn);

        enList.setFooterDividersEnabled(true);
        cnList.setFooterDividersEnabled(true);
        enList.setAdapter(adapter);
        cnList.setAdapter(cnAdapter);



    }
}
