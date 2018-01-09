package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.io.File;
import java.io.IOException;

/**
 * Created by yjq on 2016/4/15.
 */
public class VideoActivity extends Activity implements SurfaceHolder.Callback {

    Button play;
    Button stop;
    Button pause;
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    SurfaceHolder holder;
    AudioManager am;
    SeekBar seekBar;
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        am = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        seekBar = Views.findViewById(this,R.id.seekbar);
        seekBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        progress = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(progress);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        play = Views.findViewById(this, R.id.play);
        stop = Views.findViewById(this, R.id.stop);
        pause =Views.findViewById(this,R.id.pause);
        surfaceView =Views.findViewById(this,R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.start();

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mediaPlayer.stop();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_PLAY_SOUND);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
       // initVideo();
    }

    private void initVideo(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path = path + File.separator +"vd";
        path =path + File.separator +"myvideo.mp4";
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.setDisplay(holder);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* MediaController mc = new MediaController(getApplicationContext());
        File file = new File(path);
        if(file.exists()){
            videoView.setVideoPath(path);
            videoView.setMediaController(mc);
            try {
                videoView.requestFocus();
                videoView.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
*/
       // videoView.setVideoPath();

    }

    boolean holderCreate=false;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        holderCreate =true;
        initVideo();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
       holderCreate =false;
    }
}
