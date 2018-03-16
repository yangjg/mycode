package com.othershe.jnitest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.meizu.jni.Constants;
import com.meizu.jni.JniUtil;


public class MainActivity extends AppCompatActivity {
    private TextView textView;


    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gContext = getApplicationContext();
        super.onCreate(savedInstanceState);
       // JniUtil.init(this);
       // jniUtil = new JniUtil();
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.test);
       // textView.setText(jniUtil.test());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt();
            }
        });

        data = (TextView) findViewById(R.id.encry);
    }

    private void encrypt(){

        JSONObject jsonObject = new JSONObject(3);
        jsonObject.put("imei","861643030054824");
        jsonObject.put("sn","U10AFBP4222JB");
        jsonObject.put("uid","113516747");
        String content =jsonObject.toJSONString();    //"fweewhwerhwhwehwehwehwehw";

        String res = JniUtil.getInstance().getEncrypt(content);
        String dec = JniUtil.getInstance().getDecrypt(res);
       // String res = jniUtil.getEncodeString(content);
        /*String decrystr0 = AESUtils.decrypt(content);
        String encrystr = AESUtils.encrypt(content);
        String decrystr = AESUtils.decrypt(encrystr);
        String res =  AESUtils.encrypt(content);  */
        String appcode = JniUtil.getInstance().getAppCodeSignKey();
        String uploadKey = JniUtil.getInstance().getUploadSignKey();
        String gamcode = JniUtil.getInstance().getGameCodeSignKey();
        String appKey = JniUtil.getInstance().getAppKeySignKey();
        String gameKey = JniUtil.getInstance().getGameKeySignKey();
        String aesprivatekey = JniUtil.getInstance().getAESPrivateKey();
        String aesivkey = JniUtil.getInstance().getAESIVKey();

        Constants.test();
        data.setText(res);

    }

    private static Context gContext;
    public static Context getContext(){
        return gContext;
    }
}
