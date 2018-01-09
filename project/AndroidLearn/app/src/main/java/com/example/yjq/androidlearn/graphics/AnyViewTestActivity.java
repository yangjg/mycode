package com.example.yjq.androidlearn.graphics;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.yjq.androidlearn.R;
import com.example.yjq.androidlearn.Views;
import com.example.yjq.androidlearn.myview.VideoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjq on 2016/8/25.
 */
public class AnyViewTestActivity extends Activity {

    Button  mShowHide;
    boolean mShow=false;
    ActionBar mActionBar;
    ListView mList;
    private View mMultiSelectionbarView;

    private TextView mSelectedItemCount;

    private boolean allCheckedMode;
    private boolean mCheckedMode;
    private MyListAdapter mAdatper;
    private List<String> mListString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(new AnyView(this));

        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.anyview_activity);
       // getWindow().addFlags(Window.FEATURE_ACTION_BAR_OVERLAY);
    /*    mActionBar =  getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/
      //  mActionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initListString();
        mAdatper =  new MyListAdapter(this,mListString);
        mList  = Views.findViewById(this,R.id.list);
        mList.setAdapter(mAdatper);
        mList.setFooterDividersEnabled(false);
        mList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        mList.setMultiChoiceModeListener(mMultiChoiceModeListener);
   //     mList.setDividerHeight(10);
      ///  mList.setDivider(getResources().getDrawable(R.drawable.divider,null));
       // mList.setDividerHeight(6);
       /* requestWindowFeature(Window.FEATURE_LEFT_ICON);

        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.music);*/


 /*       mShow =true;
        mShowHide = Views.findViewById(this,R.id.showhide);
        mShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(mShow){
                     mActionBar.hide();
                 }
                else {
                     mActionBar.show();
                 }
                mShow =!mShow;
            }
        });

        mActionBar =  getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

   *//*     SpinnerAdapter adapter = ArrayAdapter.createFromResource(this,R.array.student,R.layout.actionbar_spiner);
        mActionBar.setListNavigationCallbacks(adapter,new DropDownListener());*//*
        ActionBar.Tab tab1 =    mActionBar.newTab().setText("First").setTabListener(new MyTabListener());
        ActionBar.Tab tab2 =    mActionBar.newTab().setText("Seconde").setTabListener(new MyTabListener());
        ActionBar.Tab tab3 =    mActionBar.newTab().setText("Third").setTabListener(new MyTabListener());
        mActionBar.addTab(tab1);
        mActionBar.addTab(tab2);
        mActionBar.addTab(tab3);
       // mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.show();*/

    }

    private void initListString(){
        mListString = new ArrayList<>();
        for (int i=0;i<50;i++){
            mListString.add(i,String.valueOf(i));
        }
    }

    private  AbsListView.MultiChoiceModeListener mMultiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            int checkedCount = 0;
            if (allCheckedMode) {
                if (checked) {
                    mAdatper.getItemState()[position] = 0;
                } else {
                    mAdatper.getItemState()[position] = 1;
                }
                checkedCount = mAdatper.getCheckedItemCount();
            } else {
                if (checked) {
                    mAdatper.getItemState()[position] = 1;
                } else {
                    mAdatper.getItemState()[position] = 0;
                }
                checkedCount = mAdatper.getCheckedItemCount();
            }
            mSelectedItemCount.setText(checkedCount + "");
            mAdatper.notifyDataSetChanged();
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            allCheckedMode  =false;
            mCheckedMode =true;
            getMenuInflater().inflate(R.menu.menu_multi_choice,menu);
            if(mMultiSelectionbarView ==null){
                mMultiSelectionbarView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_view,null,false);
                mSelectedItemCount = Views.findViewById(mMultiSelectionbarView,R.id.selected_item_count);
            }
            TextView title = Views.findViewById(mMultiSelectionbarView,R.id.title);
            title.setText("选择项目");
            mSelectedItemCount.setText(mList.getCheckedItemCount()+" ");
            mode.setCustomView(mMultiSelectionbarView);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    mAdatper.deleteSelectedItems();
                    mode.finish();
                    break;
                case R.id.open:
                    if (mAdatper.isAllChecked()) {
                        mAdatper.unCheckAll();
                        mList.clearChoices();
                        mode.finish();
                    } else {
                        mAdatper.checkAll();
                        for (int i = 0; i < mAdatper.getCount(); i++) {
                            mList.setSelection(i);
                        }
                        allCheckedMode = true;
                    }
                    mAdatper.notifyDataSetChanged();
                    mSelectedItemCount.setText(mAdatper.getCheckedItemCount() + "");
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            allCheckedMode = false;
            mCheckedMode=false;
            mAdatper.unCheckAll();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static class  ColorFramgment extends Fragment{
        int mColor;

        public ColorFramgment(){
            super();
        }

        @Override
        public void setArguments(Bundle args) {
            mColor = args.getInt("color");
            super.setArguments(args);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout colorLayout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            colorLayout.setBackgroundColor(mColor);
            colorLayout.setLayoutParams(ll);
            return colorLayout;
        }
    }

    private ColorFramgment red = new ColorFramgment();

    private ColorFramgment blue = new ColorFramgment();
    private ColorFramgment gray = new ColorFramgment();
    class  MyTabListener implements ActionBar.TabListener{
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ColorFramgment framgment=new ColorFramgment();
            Bundle bundle = new Bundle();
            String key ="color";
              switch (tab.getPosition()){
                  case 0:
                     // framgment =red;
                      bundle.putInt(key,Color.RED);
                      framgment.setArguments(bundle);
                      break;
                  case 1:
                   //   framgment =blue;
                      bundle.putInt(key,Color.BLUE);
                      framgment.setArguments(bundle);
                      break;
                  case 2:
                  //    framgment = gray;
                      bundle.putInt(key,Color.GRAY);
                      framgment.setArguments(bundle);
                      break;
                  default:
                      break;
              }
            if(null != framgment){
                ft.add(R.id.frag_container,framgment);
                //ft.show(framgment);
              //  ft.replace(R.id.frag_container,framgment);
               // ft.commit();
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            switch (tab.getPosition()){
                case 0:
                    ft.remove(red);
                    break;
                case 1:
                    ft.remove(blue);
                    break;
                case 2:
                    ft.remove(gray);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    class DropDownListener implements ActionBar.OnNavigationListener{
        @Override
        public boolean onNavigationItemSelected(int itemPosition, long itemId) {
            return false;
        }
    }

    class MyListAdapter extends BaseAdapter {


        private List<String> mList;

        private Context mContext;
        /**
         * 用来记录item的状态，1表示选中，0表示未选中
         */
        private int[] mItemState;
        private boolean isCachedBackground = false;
        private Drawable mBackground;
        private boolean mActionModeStarted = false;

        public MyListAdapter(Context context, List<String> list) {
            mList = list;
            mContext = context;
            mItemState = new int[mList.size()];
            for (int i = 0; i < mItemState.length; i++) {
                mItemState[i] = 0;
            }

        }

        public int[] getItemState() {
            return mItemState;
        }


        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public String getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
                holder.mTv = (TextView) convertView.findViewById(R.id.tv);
                holder.mContainer = Views.findViewById(convertView,R.id.checkbox_container);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //首先缓存原来的background
            if (!isCachedBackground) {
                isCachedBackground = true;
                mBackground = convertView.getBackground();
            }
            updateBackground(position, convertView);
            holder.mTv.setText(getItem(position));
            holder.mContainer.setVisibility(mCheckedMode ?View.VISIBLE:View.GONE);
            return convertView;
        }

        public int getCheckedItemCount() {
            int count = 0;
            for (int i : mItemState) {
                if (i == 1) count++;
            }
            return count;
        }

        private void updateBackground(int position,View convertView) {
            if (getItemState()[position] == 1) {
                convertView.setBackgroundColor(0xFFDFDFDF);
            } else if (getItemState()[position] == 0){
               // convertView.setBackgroundColor(0xFFDFDFDF);
                convertView.setBackgroundDrawable(mBackground);
            }
        }

        public void checkAll() {
            for (int i = 0; i < mItemState.length; i++) {
                mItemState[i] = 1;
            }
        }

        public void unCheckAll() {
            for (int i = 0; i < mItemState.length; i++) {
                mItemState[i] = 0;
            }
        }

        public boolean isAllChecked() {
            for (int i : mItemState) {
                if (i == 0) {
                    return false;
                }
            }
            return true;
        }

        public void setActionModeState(boolean flag) {
            mActionModeStarted = flag;
        }

        public boolean isActionModeStarted() {
            return mActionModeStarted;
        }

        public void deleteSelectedItems() {
            for (int i = mItemState.length - 1; i >= 0; i--) {
                if (mItemState[i] == 1) {
                    mList.remove(i);
                }
            }
            notifyDataSetChanged();
            mItemState = new int[mList.size()];
            for (int i : mItemState) {
                i = 0;
            }
        }

        public boolean isActionModeStart() {
            return mActionModeStarted;
        }


    }
    static class ViewHolder{

        TextView mTv;
        LinearLayout mContainer;

    }
    private static class AnyView extends View {

        public AnyView(Context context) {
            this(context, null);
        }

        public AnyView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public AnyView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private final int MAX_X = 500;
        private final int MAX_Y = 50;
        private final int factor = 20;


        private final int Number = 25;
        private float[] pts;

        final int X = 0;
        final int Y = 1;

        private void init() {
          //   makePath();
            initAll();
        }
        int[] srcRect = new int[]{
                80,40,Color.RED,
                40,80,Color.GREEN,
                40,40,Color.BLUE,
                80,80,Color.BLACK
        };

        Matrix.ScaleToFit[] scaleFits= new Matrix.ScaleToFit[]{
                Matrix.ScaleToFit.START,
                Matrix.ScaleToFit.CENTER,
                Matrix.ScaleToFit.FILL,
                Matrix.ScaleToFit.END,
        };

        RectF srcF = new RectF();
        RectF disF=  new RectF(0,0,110,110);


        private   void  setRect( int index){
            int w = srcRect[index*3+0];
            int h =srcRect[index*3+1];
             srcF.set(0,0,w,h);
        }

        private void drawDst(Canvas canvas,int index ,Matrix.ScaleToFit type){
            canvas.save();
            Matrix matrix = new Matrix();
            matrix.setRectToRect(srcF,disF,type);
            canvas.concat(matrix);
            drawSrc(canvas, index);
            canvas.restore();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            canvas.drawRect(disF, paint);
        }

        private void drawSrc(Canvas canvas,int index){
            int color = srcRect[index*3+2];
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawOval(srcF, paint);
        }

        SweepGradient gd;

        RectF dst;
        float cx=160;
        float cy =80;
        float r =50;
        private void initDrawable(){
            gd = new SweepGradient(cx,cy,new int[]{Color.GREEN,Color.RED,Color.BLUE,Color.GREEN},null);
        }

        private  void initRect(){
            dst = new RectF();
            dst.set(0, 0, 100, 100);
        }

        private void initAll(){
           /* initDrawable();
            initRect();
            initPaint();
            initPath();*/

            initVertic();

        }

        Paint  pt = new Paint();
        private void initPaint(){
          //  initDrawable();
            pt.setFlags(Paint.ANTI_ALIAS_FLAG);
            pt.setStyle(Paint.Style.FILL);
        }


        Path mPath;
        private  void initPath(){
            mPath = new Path();
            mPath.moveTo(10, 0);
          mPath.rCubicTo(100, -10, 200, 50, 300, 0);
        }



        float[] mVerts = new float[10];
        float[] mTexs = new float[10];
        int[] mColor = new int[]{Color.RED,Color.GREEN,Color.BLUE};
        private final short[] mIndices = { 0, 1, 2, 3, 4, 1 };
        private final Matrix mMatrix = new Matrix();
        private void makePath(){
            r1 = new Rect(100,20,200,100);
            r2 = new Rect(150,60,250,140);
            makeGradientDrawable();
        }
        private final Matrix mInverse = new Matrix();

        private void initVertic(){


            setFocusable(true);
            Bitmap bm = BitmapFactory.decodeResource(getResources(),
                    R.drawable.beach);
            Shader s = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setShader(s);
            int w=  bm.getWidth();
            int h = bm.getHeight();
            //init mVerts corrdinatl
          /*  setXY(mVerts,0,0,0);
            setXY(mVerts,1,w,0);
            setXY(mVerts,2,w,h);
            setXY(mVerts,3,0,h);
            setXY(mVerts,4,w/2,h/2);
            //init mTexs;
            setXY(mTexs,0,0,0);
            setXY(mTexs,1,w,0);
            setXY(mTexs,2,w,h);
            setXY(mTexs,3,0,h);
            setXY(mTexs,4,w/2,h/2);*/


            setXY(mTexs, 0, w/2, h/2);
            setXY(mTexs, 1, 0, 0);
            setXY(mTexs, 2, w, 0);
            setXY(mTexs, 3, w, h);
            setXY(mTexs, 4, 0, h);

            setXY(mVerts, 0, w/2, h/2);
            setXY(mVerts, 1, 0, 0);
            setXY(mVerts, 2, w, 0);
            setXY(mVerts, 3, w, h);
            setXY(mVerts, 4, 0, h);

            mMatrix.setScale(0.8f, 0.8f);
            mMatrix.preTranslate(20, 20);
            mMatrix.invert(mInverse);

             /*for (int i=0;i<mVerts.length;i++){
                // mVerts[i] =
             }*/

        }

        private void setXY(float[] array,int index,float x, float y){
            array[index*2+0]= x;
            array[index*2+1]=y;
        }
        int dg;



        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        //    canvas.drawColor(Color.WHITE);

            canvas.drawColor(0xFFCCCCCC);
            canvas.save();
            canvas.drawVertices(Canvas.VertexMode.TRIANGLE_FAN, 10, mVerts, 0,
                    mTexs, 0, null, 0, null, 0, 0, paint);
            canvas.restore();
         /*   Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(30);
            p.setColor(Color.RED);
            p.setStrokeWidth(2);
            p.setStyle(Paint.Style.STROKE);
            p.setTypeface(Typeface.create("",Typeface.NORMAL));
            int cx =  canvas.getWidth()/2;
            int cy = canvas.getHeight()/2;
            canvas.drawLine(cx, 0, cx, cy, p);

            p.setStrokeWidth(0);
            p.setColor(Color.BLACK);
            p.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Left", cx, 40, p);

            canvas.translate(0, 80);
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Center", cx, 0, p);

            canvas.translate(0, 80);
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Right",cx,0,p);

            String pos= "Positioned";
            float[] width =  new float[pos.length()];
            p.getTextWidths(pos, width);
            p.setTextAlign(Paint.Align.LEFT);
            canvas.translate(0, 80);
            canvas.drawText(pos, 60, 0, p);
            canvas.drawText(pos,(60-width[0]/2),60,p);
            canvas.drawText(pos,60-width[0],120,p);

            p.setStrokeWidth(1);
            float[] pt = new float[width.length*4];
            int delta=60;
            for (int i=0;i<width.length;i++){
                pt[i*4 + 0] =delta;
                pt[i*4+1] = -20;

                pt[i*4+2] = delta;
                pt[i*4+3] =200;
                delta +=width[i];
            }
            p.setColor(Color.GREEN);
            canvas.drawLines(pt, p);

            p.setColor(Color.BLACK);
            canvas.translate(0, 80);
            String ap ="Along a path";
            p.setColor(Color.RED);
            canvas.drawTextOnPath(ap, mPath, 0, 0, p);
            canvas.drawPath(mPath, p);

            canvas.translate(0, 80);
            canvas.drawTextOnPath(ap, mPath, 20, 10, p);
            canvas.drawPath(mPath, p);



            canvas.translate(0,80);
            canvas.drawTextOnPath(ap, mPath, 40,20, p);
            canvas.drawPath(mPath,p);


          // canvas.drawTextOnPath();
            canvas.restore();*/


  /*          canvas.save();
            dg+=5;

            Matrix mt = new Matrix();
            mt.setRotate(dg,cx,cy);
            gd.setLocalMatrix(mt);
            paint.setShader(gd);
            canvas.drawCircle(cx,cy,r,paint);

            canvas.restore();

            invalidate();*/

         /*   int N = srcRect.length/3;
            for (int i=0;i<N;i++){
                canvas.save();
                canvas.translate((i + 1) * 150, 0);
                setRect(i);
                drawSrc(canvas, i);
                canvas.restore();
            }

            int M = scaleFits.length;

            for (int i=0;i<M;i++){
                canvas.save();
                canvas.translate(0,(i+1)*150);
                for (int j=0;j<N;j++){
                    canvas.save();
                    canvas.translate((j + 1) * 150, 0);
                    setRect(j);
                    drawDst(canvas, j,scaleFits[i]);
                    canvas.restore();
                }
                canvas.restore();
            }*/


          /*  *//**
             * Shape is a rectangle, possibly with rounded corners
             *//*
            public static final int RECTANGLE = 0;

            *//**
             * Shape is an ellipse
             *//*
            public static final int OVAL = 1;

            *//**
             * Shape is a line
             *//*
            public static final int LINE = 2;

            *//**
             * Shape is a ring.
             *//*
            public static final int RING = 3;

            *//**
             * Gradient is linear (default.)
             *//*
            public static final int LINEAR_GRADIENT = 0;

            *//**
             * Gradient is circular.
             *//*
            public static final int RADIAL_GRADIENT = 1;

            *//**
             * Gradient is a sweep.
             *//*
            public static final int SWEEP_GRADIENT  = 2;

            *//** Radius is in pixels. *//*
            private static final int RADIUS_TYPE_PIXELS = 0;

            *//** Radius is a fraction of the base size. *//*
            private static final int RADIUS_TYPE_FRACTION = 1;

            *//** Radius is a fraction of the bounds size. *//*
            private static final int RADIUS_TYPE_FRACTION_PARENT = 2;*/
/*            drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            drawit(canvas, 15, 15, 0, 0);
            canvas.restore();

            canvas.save();
            drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
            canvas.translate(0, 200);
            drawit(canvas, 15, 15, 0, 0);
            canvas.restore();

            canvas.save();
            drawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
            canvas.translate(200, 200);
            drawit(canvas, 15, 15, 0, 0);
            canvas.restore();*/




          /*  canvas.save();
            drawable.setGradientType(GradientDrawable.RING);
            canvas.translate(0, 400);
            drawit(canvas, 15, 15, 0, 0);
            canvas.restore();

            canvas.save();
            drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            canvas.translate(200,400);
            drawit(canvas,15,15,0,0);
            canvas.restore();


            canvas.save();
            drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
            canvas.translate(0,600);
            drawit(canvas,15,15,0,0);
            canvas.restore();

            canvas.save();
            drawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
            canvas.translate(200,600);
            drawit(canvas,15,15,0,0);
            canvas.restore();

            canvas.save();
            drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            canvas.translate(0,800);
            drawit(canvas,15,15,0,0);
            canvas.restore();*/


      /*      paint.reset();
            int[] colors = new int[]{
                    Color.RED,
                    0x5500FF00,
                    Color.GREEN,
                    0x5500FF00,
                    Color.BLUE
            };

            float[] position=new float[]{
                    0,0.25f,0.5f,0.75f,1
            };
            Shader sharder = new LinearGradient(0,0,100,100,colors,position, Shader.TileMode.CLAMP);
            paint.setShader(sharder);
            canvas.drawRoundRect(new RectF(0,0,100,100),15,15,paint);
*/

           //   paint.setShader()

     /*       int[] colors = new int[]{
                    Color.RED,
                    0x5500FF00,
                    Color.GREEN,
                    0x5500FF00,
                    Color.BLUE
            };

            float[] position=new float[]{
                    0,0.25f,0.5f,0.75f,1
            };
            Shader sharder = new LinearGradient(0,0,100,100,colors,position, Shader.TileMode.CLAMP);
           // paint.setShader(sharder);
            RectShape shape = new RoundRectShape(new float[]{15,15,15,25,0,0,0,0},null,null);
            drawRoundRect(canvas,shape,sharder);*/

         /*   canvas.save();
            drawOrigin(canvas);
            canvas.restore();

            canvas.save();
            canvas.translate(10,200);
            drawRegion(canvas, Color.RED, Region.Op.UNION);
            canvas.restore();

            canvas.save();
            canvas.translate(200,200);
            drawRegion(canvas, Color.RED, Region.Op.DIFFERENCE);
            canvas.restore();

            canvas.save();
            canvas.translate(200,400);
            drawRegion(canvas,Color.RED,Region.Op.XOR);
            canvas.restore();

            canvas.save();
            canvas.translate(400,400);
            drawRegion(canvas,Color.RED,Region.Op.INTERSECT);
            canvas.restore();*/
           // drawRegion(canvas, Color.RED,Re);

        }
        private void drawRect(Canvas canvas,Paint paint1,Paint paint2){
            canvas.drawRect(r1, paint);
            canvas.drawRect(r2, paint2);

        }
        Rect r1;

        Rect r2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        private void drawOrigin(Canvas canvas){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(6);
            paint.setColor(Color.RED);
            drawCenter(canvas, paint, r1);
            paint.setColor(Color.BLUE);
            drawCenter(canvas, paint, r2);
            paint.setStyle(Paint.Style.FILL);

        }

        GradientDrawable drawable ;
        private  void drawRoundRect(Canvas canvas,Shape shape,Shader shader){
            ShapeDrawable drw =  new ShapeDrawable(shape);
            drw.getPaint().setShader(shader);


            drw.setBounds(10,10,110,110);
            drw.draw(canvas);
            String bububububububububububububububububuniubi ="bububububububububububububububububuniubidehen";
        }
        private void makeGradientDrawable(){
            drawable =  new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{Color.RED,Color.GREEN,Color.BLUE});
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            drawable.setGradientRadius(50);
        }
        private  void  drawit(Canvas canvas, float r1,float r2,float r3,float r4){
            float[] rs = new float[]{r1,r1,r2,r2,r3,r3,r4,r4};
            drawable.setCornerRadii(rs);
            drawable.setBounds(10,10,100,100);
            drawable.draw(canvas);
        }


        private void drawRegion(Canvas canvas,int color,Region.Op op){
            Region rg = new Region();
            rg.set(r1);

            rg.op(r2, op);
            RegionIterator iterator = new RegionIterator(rg);
            Rect r = new Rect();
            while (iterator.next(r)){
                canvas.drawRect(r,paint);
            }
            drawOrigin(canvas);
        }

        private void drawCenter(Canvas canvas,Paint p,Rect r){
            float inset =  p.getStrokeWidth()*0.5f;
            canvas.drawRect(r.left + inset, r.top + inset, r.right - inset, r.bottom - inset, paint);
        }
        Rect  orig ;

        private void makeRect(){
            orig = new Rect(10,10,110,110);
        }


        private void keytest(){
            Activity myacti =null;
            String bububububububububububububububububuniubi ="bububububububububububububububububuniubidehen";
                 /* String bububububububububububububububububuniubife ="bububububububububububububububububuniubidehenfew";*/

        }

    }
}
