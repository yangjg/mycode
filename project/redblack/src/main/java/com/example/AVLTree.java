package com.example;

import java.io.Console;
import java.util.logging.Logger;

/**
 * Created by yangjingan on 2017/5/14.
 */
public class AVLTree extends BinaryTree {

    private Node mRoot;
    public AVLTree() {
        this(null);
    }


    public AVLTree(Node root) {
        super(root);
        mRoot = root;
        mRoot.mHeight = 0 ;
    }

    private int getNodeHeight(Node node){
        if(null != node){
            return  node.mHeight;
        }
        return -1;
    }

    public void insert(int key){
        insertNode(mRoot,new Node(null,null,null,key,null));
        inSubTree(mRoot);
    }

    public void insert(Node node){
        insertNode(mRoot,node);
    }

    private void insertNode(Node parent, Node node){
       /* if(parent.mLeft == null ){
            AVLNode n = new AVLNode(parent,null,null,key,value);
            return n;
        }*/
        if(parent.mKey>node.mKey){
            if(null != parent.mLeft){
                insertNode(parent.mLeft,node);
            }
            else {
                parent.mLeft = node;
                node.mParent = parent;
            }
        }
        else if(parent.mKey<node.mKey){
            if(null != parent.mRight) {
                insertNode(parent.mRight, node);

            }
            else{
                parent.mRight = node;
                node.mParent = parent;
            }

        }
        else{
            return ;
        }

        if( 2==Math.abs(getNodeHeight(parent.mRight)- getNodeHeight(parent.mLeft))){
            Node current =null;
            if(parent.mKey>node.mKey){
                //LL
                if(parent.mLeft.mKey > node.mKey){
                    current =  LL(parent);
                }
                //LR
                else{
                    current =   LR(parent);
                }
            }
            else{
                //RR
                if(parent.mRight.mKey < node.mKey){
                    current =  RR(parent);
                }
                //RL
                else{
                    current =  RL(parent);

                }
            }
            return;
        }
        parent.mHeight = Math.max(parent.mLeft.mHeight,parent.mRight.mHeight);
        return;
    }

    private Node LL(Node node) {

        return rotate_right(node);
    }

    private Node LR(Node node){

        Node ll = rotate_left(node.mLeft);
        node.mLeft = ll;

        return rotate_right(node);

    }

    private Node RR(Node node){
        return rotate_right(node);
    }


    private Node RL(Node node){
       Node rr = rotate_right(node.mRight);
        node.mRight = rr;
        return  rotate_left(node);
    }

    private Node  rotate_left(Node node){
        Node r = node.mRight;
        node.mRight = r.mLeft;
        r.mLeft.mParent = node;
        r.mLeft = node;
        r.mParent = node.mParent;
        node.mParent = r;
        node .mHeight = Math.max(node.mLeft.mHeight,node.mRight.mHeight);
        r.mHeight =  Math.max(r.mLeft.mHeight,r.mRight.mHeight);
        return r;
    }

    private Node rotate_right(Node node){
        Node l = node.mLeft;
        node.mLeft = l.mRight;
        l.mRight.mParent = node;
        l.mRight = node;
        l.mParent = node.mParent;
        node.mParent = l;
        node .mHeight = Math.max(node.mLeft.mHeight,node.mRight.mHeight);
        l.mHeight =  Math.max(l.mLeft.mHeight,l.mRight.mHeight);
        return l;
    }

    public  void inSubTree(Node node){
        if(null == node)return;
        inSubTree(node.mLeft);
        System.out.print(makeString(node));
       inSubTree(node.mRight);

    }

    private String makeString(Node node){
        int h = node.mHeight;
        StringBuilder sb =  new StringBuilder();
        for(int i=-1;i<h;i++){
            sb.append(" ");
        }
        sb.append(node.mKey);
        return  sb.toString();
    }




}
