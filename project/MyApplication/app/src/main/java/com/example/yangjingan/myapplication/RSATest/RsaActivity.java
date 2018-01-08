package com.example.yangjingan.myapplication.RSATest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

/**
 * Created by yangjingan on 17-12-28.
 */

public class RsaActivity  extends AppCompatActivity {

    ViewHolder mHolder;
    RSAUtils.Cert cert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        initHolder();
    }

    private void initHolder(){
        mHolder = new ViewHolder();
        mHolder.com_key = Views.findViewById(this,R.id.com_key);
        mHolder.private_key = Views.findViewById(this,R.id.private_key);
        mHolder.source_msg = Views.findViewById(this,R.id.source_msg);
        mHolder.encode_msg  =  Views.findViewById(this,R.id.encode_msg);
        mHolder.encode_data = Views.findViewById(this,R.id.encode_data);
        mHolder.decode_data =  Views.findViewById(this,R.id.decode_data);
        mHolder.encode_btn = Views.findViewById(this,R.id.encode);
        mHolder.decode_btn = Views.findViewById(this,R.id.decode);
        mHolder.reset = Views.findViewById(this,R.id.reset);
        cert = RSAUtils.getCert();
        mHolder.com_key.setText(getString(cert.n,cert.com_key));
        mHolder.private_key.setText(getString(cert.n,cert.private_key));

        mHolder.encode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int msg = Integer.parseInt(mHolder.source_msg.getText().toString());
                     mHolder.encode_msg.setText(String.valueOf(encode(msg)));
                }
                catch (Exception e){

                }

            }
        });
        mHolder.decode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int msg = Integer.parseInt(mHolder.encode_data.getText().toString());
                    mHolder.decode_data.setText(String.valueOf(decode(msg)));
                }
                catch (Exception e){

                }
            }
        });

        mHolder.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHolder.source_msg.setText("");
                mHolder.encode_msg.setText("");
                mHolder.encode_data.setText("");
                mHolder.decode_data.setText("");
            }
        });
    }

    private int encode(int msg){
        return RSAUtils.rsa(cert.n,cert.com_key,msg);
    }

    private int decode(int msg){
        return RSAUtils.rsa(cert.n,cert.private_key,msg);
    }

    private String getString(int n,int key){
        return String.format("(%d,%d)",n,key);
    }


    private static class ViewHolder{
        TextView com_key;
        TextView private_key;
        TextView decode_data;
        TextView encode_msg;
        EditText source_msg;
        EditText encode_data;
        Button encode_btn;
        Button decode_btn;
        Button reset;
    }
}
