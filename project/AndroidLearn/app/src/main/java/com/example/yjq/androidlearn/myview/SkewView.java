package com.example.yjq.androidlearn.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjq.androidlearn.R;

/**
 * Created by yjq on 2016/4/11.
 */
public class SkewView extends View {


    Paint paint;
    Bitmap bitmap;
    Matrix matrix;
    float kx = 0.0f;
    float ky = 0.0f;
    float px = 0.0f;
    float py = 0.0f;

    public SkewView(Context context) {
        this(context, null);
    }

    public SkewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        matrix = new Matrix();
    }

    public void setSkewParam(float kx, float ky) {
        setSkewParam(kx,ky,px,py);
    }

    public void setSkewParam(float kx, float ky, float px, float py) {
        this.kx = kx;
        this.ky = ky;
        this.px = px;
        this.py = py;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        matrix.setSkew(kx, ky,px,py);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.restoreToCount(Canvas.ALL_SAVE_FLAG);
    }
}
