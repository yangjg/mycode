package com.example;

/**
 * Created by yangjingan on 2017/5/14.
 */
public class Node {

    public Node mParent;
    public Node mLeft;
    public Node mRight;
    public Object mValue;
    public int mKey;
    public int mHeight;

    public Node(Node parent,Node left,Node right,int key,Object value){
        mParent = parent ;
        mLeft = left;
        mRight = right;
        mKey = key;
        mValue = value;
    }
}
