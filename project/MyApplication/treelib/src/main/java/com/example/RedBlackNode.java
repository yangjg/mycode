package com.example;

/**
 * Created by yangjingan on 2017/5/14.
 */
public class RedBlackNode extends Node {



    public final static int RED = 1;
    public final static int BLACK =0;

    public @ColorAnnotation int mColor;

    public RedBlackNode(Node parent,Node left,Node right,@ColorAnnotation int color,int key,Object value){
        super(parent,left,right,key,value);
        mColor = color;
    }
}
