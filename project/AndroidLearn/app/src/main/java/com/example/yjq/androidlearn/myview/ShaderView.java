package com.example.yjq.androidlearn.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.yjq.androidlearn.MainActivity;
import com.example.yjq.androidlearn.R;

/**
 * Created by yjq on 2016/4/11.
 */
public class ShaderView extends View {


    private ShapeDrawable shapeDrawable;
    private final  int RADIUS=300;
    private final  int FACTOR=2;
    private Matrix matrix = new Matrix();
    private Bitmap bitmap_magnifier;
    private Bitmap source_bitmap;
    private int m_left;
    private int m_top;
    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        source_bitmap = MainActivity.getViewShot(); // BitmapFactory.decodeResource(getResources(),R.drawable.scale);
        if(source_bitmap==null){
            source_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.music);
        }
        BitmapShader shader =null;

             shader = new BitmapShader(Bitmap.createScaledBitmap(source_bitmap,source_bitmap.getWidth()*FACTOR,source_bitmap.getHeight()*FACTOR,true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            shapeDrawable  = new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setShader(shader);
            shapeDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);


       // bitmap_magnifier =BitmapFactory.decodeResource(getResources(),R.drawable.shader);

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
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
   //     canvas.drawBitmap(source_bitmap, 0, 0, null);
      /*  canvas.translate(300, 300);
        canvas.drawCircle(300.0f,300.0f,RADIUS+5.0f,paint);*/
        shapeDrawable.draw(canvas);
      /*  Bitmap bitmap =  BitmapFactory.decodeResource(getResources(),R.drawable.back);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.shader);
        BitmapShader shader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        paint.setShader(shader1);
        RectF rectF = new RectF(0,0,1000,1500);
        canvas.translate(40,50);
        canvas.drawOval(rectF,paint);*/
      //  super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x =(int)event.getX();
        final int y =(int)event.getY();
        matrix.setTranslate(RADIUS-x*FACTOR,RADIUS-y*FACTOR);
        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        shapeDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();
        return true;
       // return super.onTouchEvent(event);
    }
}
