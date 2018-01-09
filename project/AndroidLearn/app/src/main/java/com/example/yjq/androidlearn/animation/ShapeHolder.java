package com.example.yjq.androidlearn.animation;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.Size;

/**
 * Created by yjq on 2016/6/2.
 */
public class ShapeHolder {

    ShapeDrawable shape;
    int x;
    int y;
    float with;
    float height;
    public  ShapeHolder(ShapeDrawable shape){
        this.shape = shape;
        with = shape.getShape().getWidth();
        height = shape.getShape().getHeight();
    }

    public ShapeDrawable getShape(){
        return shape;
    }

    public void setX(int x){
        this.x =x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public Paint getPaint(){
        return shape.getPaint();
    }

    public void setAlpha(float alpha){
        shape.setAlpha((int)(alpha*255));
    }

    public void setColor(int color){
        shape.getPaint().setColor(color);
    }

    public void setWith(float with){
        this.with =with;
        Shape s = shape.getShape();
        s.resize(with, s.getHeight());
       // shape.setBounds();
    }

    public void setHeight(float height){
        this.height =height;
        Shape s = shape.getShape();
        s.resize(s.getWidth(),height);
    }

    public float getHeight(){
        return  height;
    }
    public float getWith(){
       return with;
    }

    public static ShapeHolder createBall(int x,int y,int width,int height,int color){
        OvalShape shape = new OvalShape();
        shape.resize(width,height);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        ShapeHolder holder = new ShapeHolder(drawable);
        holder.setX(x - width/2);
        holder.setY(y - height/2);
        holder.setColor(color);
        return holder;
    }

    public static ShapeHolder createBall(int x,int y,int width,int height,int startcolor, int endColor){
        OvalShape shape = new OvalShape();
        shape.resize(width,height);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        ShapeHolder holder = new ShapeHolder(drawable);
        holder.setX(x - width / 2);
        holder.setY(y - height/2);
        RadialGradient gradient = new RadialGradient(width/2+width/4,height/2-height/4,width/2,startcolor,endColor, Shader.TileMode.CLAMP);
        holder.getPaint().setShader(gradient);
        return holder;
    }
}
