package com.example.yjq.androidlearn.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.yjq.androidlearn.cglib.TestForumService;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by yjq on 2016/9/15.
 */
public class TouchPaintActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TouchPaintView(this));
    }


    private  class TouchPaintView extends View{

        public TouchPaintView(Context context) {
            super(context);
            init();
        }

        private void init(){
            setFocusable(true);
            setFocusableInTouchMode(true);
            initPath();
            initPaint();
            initCanvas();
        }


      TestForumService testForumService = new TestForumService();

        private int ALPHA =0X86;
        private final int MAX_STEP =256;
        private int ALPHA_STEP= MAX_STEP/ALPHA +4;

        private Path mPath;

        private PathEffect mPathEffect;

        private Bitmap mBitmap;

        private Paint mPathPaint;

        private Canvas mPathCanvas;

        private final int MSG_REFRESH =30;
        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MSG_REFRESH:
                        refreshFade();
                        sendMsgDeplayRefresh();

                        break;
                    default:
                        break;
                }
            }
        };

        private void sendMsgDeplayRefresh(){
           // mHandler.sendEmptyMessageAtTime(MSG_REFRESH,2000);
            mHandler.sendEmptyMessageDelayed(MSG_REFRESH,500);
        }

        private  int mStep;

        private Paint mDrawPaint;
        public  void refreshFade(){
            if(mStep<MAX_STEP && mStarterTouched){
                mPathCanvas.drawPaint(mDrawPaint);
                mStep += ALPHA_STEP;
                invalidate();
                testForumService.runTest();
            }
            if(mStep>=MAX_STEP && mStarterTouched){
                mStarterTouched =false;
            }
        }
        boolean mStarterTouched=false;
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();


            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    float pre = event.getPressure();
                    paint(x,y,pre,pre,mPathPaint);
              /*      mPath.reset();
                    prex = x;
                    prey =y;
                    mPath.moveTo(x,y);
                    invalidate();*/
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int Size = event.getHistorySize();
                    int P = event.getPointerCount();
                    for (int i=0;i<Size;i++){
                        for(int j=0;j<P;j++){
                            float cx= event.getHistoricalX(j, i);
                            float cy = event.getHistoricalY(j, i);
                            float pressure = event.getHistoricalPressure(j, i);
                            float majorAXIS= event.getHistoricalToolMajor(j, i);
                            float minorAXIS= event.getHistoricalToolMinor(j,i);
                            paint(cx,cy,majorAXIS/5,minorAXIS/5,mPathPaint);
                        }
                    }
                   /* updatePath(x,y);
                    invalidate();*/
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    invalidate();
                   /* updatePath(x,y);
                    invalidate();*/
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                default:
                    break;
            }
            if(!mStarterTouched){
                mStarterTouched =true;
                sendMsgDeplayRefresh();
            }
            return true;
        }


        private void initCanvas(){
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            mBitmap = Bitmap.createBitmap(dm.widthPixels,dm.heightPixels, Bitmap.Config.ARGB_8888);
            mPathCanvas = new Canvas(mBitmap);
        }

        private  void initPaint(){
            mPathEffect = new DashPathEffect(new float[]{5,3},20);
            mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPathPaint.setStrokeWidth(2);
            mPathPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPathPaint.setColor(Color.GRAY);
        //    mPathPaint.setPathEffect(mPathEffect);

            mDrawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mDrawPaint.setColor(Color.WHITE);
            mDrawPaint.setAlpha(ALPHA);


        }
        private void initPath(){
            mPath =  new Path();
            mPath.moveTo(0, 0);
        }
        private void drawAll(Canvas canvas){
            canvas.save();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(mBitmap, 0, 0, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.restore();
        }


        private float prex=0;
        private float prey=0;
        private void updatePath(float x,float y){
            if(null!=mPath){
                float cx = (prex+x)/2;
                float cy =(prey+y)/2;
                if(x>prex){
                    cx +=1;
                }
                else {
                    cx +=-1;
                }
                float dy = Math.abs(prey-y);
                cy +=dy/4;
                mPath.quadTo(cx,cy,x,y);
                prex =x;
                prey =y;
                mPathCanvas.drawPath(mPath,mPathPaint);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
             drawAll(canvas);
        }

        private void paint(float x,float y,float majorAxis,float minorAxis ,Paint paint){
         //   mPathCanvas.drawOval(x-majorAxis/2,y-minorAxis/2,x+majorAxis/2,x+minorAxis/2,paint);
            mPathCanvas.drawOval(new RectF(x-majorAxis/2,y-minorAxis/2,x+majorAxis/2,y+minorAxis/2),paint);
            mStep =0;
           // invalidate();
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
        }
    }



}
