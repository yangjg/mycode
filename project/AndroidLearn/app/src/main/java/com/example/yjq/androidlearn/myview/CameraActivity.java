package com.example.yjq.androidlearn.myview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yjq on 2016/4/16.
 */
public class CameraActivity extends Activity implements SurfaceHolder.Callback,Camera.PictureCallback {

    Button preview;
    Button take;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    private Camera camera;
    private boolean sdCardOk = false;
    private boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.camera_activity);

        preview = Views.findViewById(this, R.id.preview);
        take = Views.findViewById(this, R.id.take);
        surfaceView = Views.findViewById(this, R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPreview) {
                    camera = Camera.open();
                }
                try {
                    camera.setPreviewDisplay(holder);
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setPreviewSize(640, 480);
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    parameters.set("jpeg-quality", 80);
                    parameters.setPictureSize(640, 480);
                    camera.setParameters(parameters);
                    camera.startPreview();
                    camera.autoFocus(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(null!=camera){
                     camera.takePicture(null,null,CameraActivity.this);
                 }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "no sd card", Toast.LENGTH_LONG).show();
            sdCardOk = false;
            //  return;
        } else {
            sdCardOk = true;
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        final Bitmap bm = BitmapFactory.decodeByteArray(data,0,data.length);
        View saveView = getLayoutInflater().inflate(R.layout.save_picture, null);
        final EditText photoName = Views.findViewById(saveView, R.id.phoneName);
        ImageView show = Views.findViewById(saveView,R.id.show);
        show.setImageBitmap(bm);
        camera.stopPreview();
        isPreview =false;

        new AlertDialog.Builder(CameraActivity.this).setView(saveView).setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+File.separator+"Test");

                    if(!file.exists()){
                        file.mkdirs();
                    }

                try {
                    File tempFile = new File(file.getAbsolutePath()+File.separator+photoName.getText().toString()+".jpg");
                    tempFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    bm.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    fos.flush();
                    fos.close();
                    isPreview=true;
                    startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isPreview = true;
                startPreview();

            }
        }).show();
    }

    private  void startPreview(){
        if(isPreview){
            camera.startPreview();
        }
    }

    @Override
    protected void onPause() {
        if(null!=camera){
            camera.stopPreview();
            camera.release();
            isPreview =false;
            camera=null;
        }
        super.onPause();
    }
}
