package com.example.yjq.androidlearn.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;

import com.example.yjq.androidlearn.R;

import org.w3c.dom.ProcessingInstruction;

/**
 * Created by yjq on 2016/7/7.
 */
public class AnimationMeshActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(new SimpleView(this));

    }


    public static class SimpleView extends View{
        private static int WIDTH =20;
        private static int HEIGHT =20;
        private static final int COUNT=(WIDTH+1)*(HEIGHT+1);
        private static final  int K=10000;
        private float[] mVerse = new float[COUNT*2];
        private float[] mOrig =new float[COUNT*2];
        Bitmap bm;
        private final Matrix mMatrix = new Matrix();
        private final Matrix mInverse = new Matrix();

        private void setPXY(float[] array,int index,float px,float py){
            array[index*2+0]=px;
            array[index*2+1]=py;
        }

        public SimpleView(Context context) {
            super(context);
            setFocusable(true);
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.beach);
            float h = bm.getHeight();
            float w =bm.getWidth();
            int index=0;
            for (int i=0;i<=HEIGHT;i++)
            {   float py = h*i/HEIGHT;
                for (int j =0;j<=WIDTH;j++){
                    float px = w*j/WIDTH;
                    setPXY(mVerse,index,px,py);
                    setPXY(mOrig,index,px,py);
                    index++;

                }
            }
            mMatrix.setTranslate(10, 10);
            mMatrix.invert(mInverse);
        }

        float mLastWarpX=-9999;
        float mLastWarpY;
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float px = event.getX();
            float py =event.getY();
            float[] pts =new float[]{px,py};
        //    mInverse.mapPoints(pts);
            int x = (int)pts[0];
            int y = (int)pts[1];
            if (mLastWarpX != x || mLastWarpY != y) {
                mLastWarpX = x;
                mLastWarpY = y;
                wrap(pts[0], pts[1]);
                invalidate();
            }
           return  true;
        }

        private void wrap(float px,float py){
            float[] orig = mOrig;
            float[] dst =mVerse;

            for (int i=0;i<COUNT;i++){
                float X = orig[2*i+0];
                float Y =orig[2*i+1];
                float dx =px-X;
                float dy =py-Y;
                float dd = dx*dx +dy*dy;
                float d = (float)Math.sqrt(dd);
                float pull = K / (dd + 0.000001f);

                pull /= (d + 0.000001f);
                //   android.util.Log.d("skia", "index " + i + " dist=" + d + " pull=" + pull);

                if (pull >= 1) {
                    dst[2*i+0] = px;
                    dst[2*i+1] = py;
                } else {
                    dst[2*i+0] = X + dx * pull;
                    dst[2*i+1] = Y + dy * pull;
                }
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            canvas.drawColor(0xFFCCCCCC);
          //  canvas.drawBitmap(bm,0,0,null);
            canvas.drawBitmapMesh(bm,WIDTH,HEIGHT,mVerse,0,null,0,null);
        }
    }
}
