package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yangjingan on 17-3-29.
 */
public class ItemStack {

    private String mStartFlag;
    private String mEndFlag;
    private static final String LineTag="\r\n";

    private Stack<StackItem> stackItemList  = new Stack<>();

    public ItemStack(String startFlag,String endFlag){
        mStartFlag = startFlag;
        mEndFlag = endFlag;
    }


    public DataItem pushStack(LineDataItem item) {
        if(null == item || ""==item.toString() || item.toString().length()<1)return  null ;
        DataItem res=null;
        if ( item.getLast().equals(mEndFlag) && !stackItemList.empty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder originSb = new StringBuilder();
            List<String> list = new ArrayList<>();
            List<String> originList = new ArrayList<>();


            originList.add(LineTag);
            originList.add(item.toString());

            list.add(LineTag);
            list.add(item.getLast().toString());
            boolean canBreak =false;
            while (!canBreak){
                try {
                    StackItem stackItem = stackItemList.pop();
                    if(stackItem.mIsStartFlag){
                        canBreak =true;
                    }
                    list.add(LineTag);
                    list.add(stackItem.getItem().getLast());

                    originList.add(LineTag);
                    originList.add(stackItem.getItem().toString());
                }
                catch (Exception e){
                    canBreak =true;
                }
            }
            sb.append(LineTag);
            originSb.append(LineTag);
            for (int i=list.size()-1;i>=0;i--){
                sb.append(list.get(i));
            }
            for (int i = originList.size()-1;i>=0;i--){
                originSb.append(originList.get(i));
            }
            res = new DataItem(sb.toString(),originSb.toString(),true);
        } else {
            if(item.getLast().equals(mStartFlag) || !stackItemList.empty() ){
                StackItem stackItem = new StackItem(item, item.getLast().equals(mStartFlag));
                stackItemList.push(stackItem);
            }
            else{
                res = new DataItem(item.toString()+LineTag,item.toString()+LineTag,false);
            }
        }
        return res;
    }

    public  void clear(){
        stackItemList.clear();
    }


    private static class  StackItem{

        public LineDataItem mItem;
        public boolean mIsStartFlag;

        public StackItem(LineDataItem item){
            this(item,false);
        }

        public StackItem(LineDataItem item,boolean isStartFLag){
            mItem = item;
            mIsStartFlag =isStartFLag;
        }

        public LineDataItem getItem(){
            return mItem;
        }
        public boolean IsStartFlag(){
            return  mIsStartFlag;
        }
    }
}
