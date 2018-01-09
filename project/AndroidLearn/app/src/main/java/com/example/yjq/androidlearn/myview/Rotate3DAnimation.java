package com.example.yjq.androidlearn.myview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by yjq on 2016/4/12.
 */
public class Rotate3DAnimation extends Animation {

    private static final String TAG = "Rotate3DAnimation";
    private float mFromDegree;
    float mToDegree;
    float mCenterX;
    float mCenterY;
    float mDepthZ;
    boolean mReverse;
    private Camera mCamera;

    public Rotate3DAnimation(float fromDegree, float toDegree, float centerX, float centerY, float depthZ, boolean reverse) {
        mFromDegree = fromDegree;
        mToDegree = toDegree;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Log.e(TAG, String.valueOf(interpolatedTime));
        float degree =mFromDegree + (mToDegree-mFromDegree)*interpolatedTime;
        Matrix matrix = t.getMatrix();
        printMatrix(matrix);
        final  Camera camera= mCamera;
        camera.save();

        camera.translate(0.0f,0.0f,0);
     //   camera.rotateX(degree);
        camera.rotateY(degree);
        camera.getMatrix(matrix);
        camera.restore();
        printMatrix(matrix);
     /*   float[] point = new float[]{-1,0,300,0,1,0,0,0,1};
        matrix.setValues(point);
        matrix.preRotate(degree);
        printMatrix(matrix);*/
        matrix.preTranslate(-mCenterX, -mCenterY);
        printMatrix(matrix);
      //  matrix.preRotate(degree);

        matrix.postTranslate(mCenterX,mCenterY);
        printMatrix(matrix);
       // matrix.setRotate(degree, mCenterX, mCenterY);
    //    printMatrix(matrix);


      //  matrix.setSkew();
       // matrix.preTranslate(-mCenterX,-mCenterY);


    }

    public void doTestMatrix(){
        Matrix test = new Matrix();
        float[] point =new float[]{
                1.0f,0.5f,200.0f,0.5f,1.0f,200.0f,0.0f,0.0f,1.0f
        };
        test.setValues(point);
        printMatrix(test);
        test.preTranslate(30, 30);
        printMatrix(test);
        test.postTranslate(-30, -30);
        printMatrix(test);
      /*  test.setRotate(30);
        printMatrix(test);
        test.setScale(2,2);
        printMatrix(test);
        test.setSkew(1,-1);
        printMatrix(test);

        test.setTranslate(100,100);
        printMatrix(test);*/
    }

    void printMatrix(Matrix matrix){
        if(null==matrix)return;

        float[] point=new float[9];
        matrix.getValues(point);
        for (int i=0;i<9;i+=3){
            System.out.println(String.format("%f, %f, %f", point[i], point[i + 1], point[i + 2]));
        }



    }
}
