package com.example.yjq.androidlearn.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yjq on 2016/8/24.
 */
public class PatternsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PatternView(this));
    }

    private static class PatternView extends View {

        public PatternView(Context context) {
            this(context, null);
        }

        public PatternView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public PatternView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private Paint mPaint;
        private Shader mBottomShader;
        private Shader mTopShader;
        private final float TOUCH_DISTANCE = 5.0f;
        private DrawFilter mFastDF;
        private DrawFilter mDF=null;
        private float moveDx;
        private float moveDy;
        private float TouchPx;
        private float TouchPy;
        private float mOffsetDx=0;
        private float mOffsetDy=0;
        private final float MOVE_DISTANCE=5;
        private void init() {

            mBottomShader = new BitmapShader(makeBottomBitmap(), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            mTopShader = new BitmapShader(makeTopBitmap(), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix matrix = new Matrix();
            matrix.setRotate(30);
            mTopShader.setLocalMatrix(matrix);
            mFastDF = new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG|Paint.DITHER_FLAG);
            mPaint = new Paint();//new Paint(Paint.ANTI_ALIAS_FLAG);

            setFocusable(true);
            setFocusableInTouchMode(true);

        }

        private static Bitmap makeBottomBitmap() {
            Bitmap bm = Bitmap.createBitmap(40, 40, Bitmap.Config.RGB_565);//每一个像素用16bit表示
            Paint p = new Paint(0);
            p.setColor(Color.BLUE);
            Canvas c = new Canvas(bm);//创建画布，所有在画布上面位置的都绘制到bm上。
            c.drawColor(Color.RED);
            c.drawRect(new Rect(5, 5, 35, 35), p);
            return bm;
        }

        private static Bitmap makeTopBitmap() {
            Bitmap bm = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
            Paint p = new Paint(0);
            Canvas c = new Canvas(bm);//创建画布，所有在画布上面位置的都绘制到bm上。
            p.setColor(Color.GREEN);
            p.setAlpha(0XCC);
            c.drawCircle(32, 32, 27, p);

            return bm;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // super.onDraw(canvas);

            canvas.setDrawFilter(mDF);

            mPaint.setShader(mBottomShader);
            canvas.drawPaint(mPaint);

            canvas.save();
            mOffsetDx +=moveDx;
            mOffsetDy  +=moveDy;
            canvas.translate(mOffsetDx,mOffsetDy);
             canvas.clipRect(0, 0, 500, 500);
            mPaint.setShader(mTopShader);
            canvas.drawPaint(mPaint);
            canvas.restore();
        }

        private static final String TAG = "PatternView";

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    moveDx = event.getX() - TouchPx;
                    moveDy = event.getY() -TouchPy;
                    mDF =null;
                    invalidate();
                    Log.e(TAG, "ACTION_UP");
                    break;
                case MotionEvent.ACTION_DOWN:
                    TouchPx = event.getX();
                    TouchPy = event.getY();
                    moveDx =0;
                    moveDy =0;
                    mDF =mFastDF;
                    invalidate();
                    Log.e(TAG, "ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.e(TAG, "ACTION_CANCEL");
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveDx = event.getX() - TouchPx;
                    moveDy = event.getY() -TouchPy;
                  //  if(Math.abs(moveDx)>MOVE_DISTANCE | Math.abs(moveDy)>MOVE_DISTANCE){
                        invalidate();
                        TouchPx = event.getX();
                        TouchPy = event.getY();
                   // }
                    Log.e(TAG, "ACTION_MOVE");
                    break;
                default:
                    Log.e(TAG, "ACTION_OTHER");
                    break;
            }
            return true;//super.onTouchEvent(event);
        }
    }
}
