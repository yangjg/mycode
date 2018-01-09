package com.example.yjq.androidlearn.myview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yjq on 2016/5/17.
 */
public class MyHorizontalScrollView extends HorizontalScrollView implements ViewPager.OnPageChangeListener,View.OnClickListener {

    LinearLayout container;
    Paint mPaint;
    Paint mIndicatorPaint;
    int mDrawMaskW;
    int mIndicatorW;
    int mIndicatorH;
    int mIndicatorX;
    int mParentW;
    OnClickCallBack mCallBack;
    private  HorizontScrollViewAdapter adapter;
    public MyHorizontalScrollView(Context context) {
        this(context, null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       // initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();

    }

    private void initViews(){

        container  = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
   /*     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(20, 0, 20, 0);
        for (int i=0;i<40;i++) {
            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(params);
            tv.setText("test" + i);
            tv.setTextColor(Color.BLUE);
            container.addView(tv);
        }*/
        addView(container);


        mDrawMaskW = 50;
        mIndicatorH =2;
        mIndicatorW =300;
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setAntiAlias(true);
        mIndicatorPaint.setColor(Color.GREEN);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        updatePaint(Color.YELLOW);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mParentW = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawIndictor(canvas);
        float x =  getScrollX();
        float px =  getX();
        float py= getY();
        Rect r = new Rect();
        getLocalVisibleRect(r);

        int flag = canvas.save();
        canvas.translate(x, 0);
        canvas.drawRect(0, 0, mDrawMaskW, getHeight(), mPaint);
        canvas.restoreToCount(flag);
        flag = canvas.save();
     /*   Matrix matrix  =new Matrix();
        matrix.preScale(-1, 1);
        canvas.setMatrix(matrix);*/
        canvas.translate(x+getWidth(),0);
        canvas.scale(-1,1);
        canvas.drawRect(0,0,mDrawMaskW,getHeight(),mPaint);
        canvas.restoreToCount(flag);
    }

    private  void drawIndictor(Canvas canvas){
        int flag = canvas.save();
        canvas.translate(0,getHeight()-mIndicatorH);
        canvas.drawRect(mIndicatorX, 0, mIndicatorX + mIndicatorW, mIndicatorH, mIndicatorPaint);
        canvas.restoreToCount(flag);
    }

    private int getMaxScroll(){
        int dis = container.getWidth() - mParentW;
        return  dis;
    }

    private void scrollChild(int position,float offset){
        View lhs=container.getChildAt(position);
        View rhs=container.getChildAt(position+1);
        offset =regularOffset(offset);
        if(null ==lhs){
            return;
        }
        if(null == rhs){
            mIndicatorX = lhs.getLeft();
            mIndicatorW = lhs.getWidth();
        }
        else{
            mIndicatorX = computiValue(lhs.getLeft(),rhs.getLeft(),offset);
            mIndicatorW =computiValue(lhs.getWidth(),rhs.getWidth(),offset);
        }
        int scollX= mIndicatorX + mIndicatorW/2 -mParentW/2;
        scollX = Math.max(0,Math.min(scollX, getMaxScroll()));
        smoothScrollTo(scollX, 0);
        // scrollTo(scollX, 0);
        invalidate();
    }

    private  int computiValue(int v1,int v2,float offset){
        return (int)(v1+(v2-v1)*offset);
    }

    private float regularOffset(float offset){
        if(offset<0){
            return 0;
        }
        if(offset>1){
            offset=1;
        }
        return offset;
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    private void updatePaint(int color){
        mPaint.setShader(null);
        float[] positions = new float[]{0.0F, 0.6F, 1.0F};
        int[] colors = new int[]{color, color, color};
        colors[2] = color & 0x00FFFFFF;
        LinearGradient gradient = new LinearGradient(0, 0, mDrawMaskW, 0, colors, positions, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
    }

    private static final String TAG = "MyHorizontalScrollView";

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scrollChild(position,positionOffset);
        String msg =String.format("onPageScrolled,position==%s,offset==%s",position,positionOffset);
      //  Log.e(TAG,msg);
      //  selectItem(position);
    }

    @Override
    public void onPageSelected(int position) {
       selectItem(position);
        String msg =String.format("onPageSelected,position==%s",position);
        Log.e(TAG, msg);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        String msg =String.format("onPageScrollStateChanged,state==%s",state);
        Log.e(TAG, msg);
    }


    public void setAdapter(HorizontScrollViewAdapter adapter){
        this.adapter =adapter;
        notifyDataChange();

    }

    private void notifyDataChange(){
        updateView();
    }

    TextView mCurrent;
    private void selectItem(int position){
        if(mCurrent!=null){
            mCurrent.setSelected(false);
        }
        mCurrent = (TextView)container.getChildAt(position);
        mCurrent.setSelected(true);
    }

    private void updateView(){
        int adapterSize = null != adapter?adapter.getCount():0;
        int childCount = container.getChildCount();
        for (int i=0;i<adapterSize;i++){
            TextView child;
            if(i<childCount){
               child =  (TextView)adapter.getView(i,container.getChildAt(i),container);
            }
            else{
                child = (TextView)adapter.getView(i,null,container);
                container.addView(child);
            }
            bindView(child,i);
        }
        if(adapterSize<childCount){
            for (int i=childCount-1;i>=adapterSize;i--){
                View child = container.getChildAt(i);
                container.removeView(child);
            }
        }
        invalidate();
    }

    private void bindView(TextView tv,int position){
        if(position==0){
           LinearLayout.LayoutParams params =  ( LinearLayout.LayoutParams) tv.getLayoutParams();
            params.setMargins(mDrawMaskW,0,0,0);
        }
        if(position==adapter.getCount()-1){
            LinearLayout.LayoutParams params =  ( LinearLayout.LayoutParams) tv.getLayoutParams();
            params.setMargins(0,0,mDrawMaskW,0);
        }
        tv.setTag(position);
        tv.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
       int position = (int)v.getTag();
       if(null !=mCallBack){
           mCallBack.selectPosition(position);
       }
    }

    public void setOnClickCallBack(OnClickCallBack callBack){
        mCallBack = callBack;
    }

    public interface  OnClickCallBack{
        void selectPosition(int position);
    }





}
