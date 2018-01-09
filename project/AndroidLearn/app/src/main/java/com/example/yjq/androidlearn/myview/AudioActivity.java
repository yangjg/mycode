package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.io.IOException;

/**
 * Created by yjq on 2016/4/15.
 */
public class AudioActivity extends Activity {

    Button play;
    Button stop;
    Button pause;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_activity);
        initMedia();
        initMedia2();
        play = Views.findViewById(this, R.id.play);
        stop = Views.findViewById(this, R.id.stop);
        pause =Views.findViewById(this,R.id.pause);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mediaPlayer2.start();
              //  mediaPlayer.reset();
                boolean ok =  SettinActivity.getBgSound(getApplicationContext());
                Intent intent = new Intent();
                intent.setClass(AudioActivity.this, SettinActivity.class);
                startActivity(intent);

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  mediaPlayer.stop();
                mediaPlayer2.stop();;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                mediaPlayer2.pause();
            }
        });


    }

    private void initMedia() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.live);
    }

    private void initMedia2(){
        mediaPlayer2= MediaPlayer.create(getApplicationContext(),R.raw.seconde);
    }

    private void initMedia(String path) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
        } catch (Exception e) {
            e.printStackTrace();
            // e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
