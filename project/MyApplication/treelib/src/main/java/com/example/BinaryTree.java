package com.example;


/**
 * Created by yangjingan on 2017/5/14.
 */
public class BinaryTree {

    Node mRoot;

    public BinaryTree(){
        this(null);
    }


    public BinaryTree(Node root){
        mRoot = root;
    }



    protected Node findBrother(Node node){
        if(null != node && null != node.mParent){
            if(node == node.mParent.mRight){
                return node.mParent.mLeft;
            }
            else {
                return node.mParent.mRight;
            }
        }
        return null;
    }

    protected Node findUncle(Node node){
        if(null != node && null != node.mParent){
            return findBrother(node.mParent);
        }
        return null;
    }

    protected Node findGrandPa(Node node){
        if(null != node && null != node.mParent){
            return node.mParent.mParent;
        }
        return null;
    }


    public Node searchNode(int key){
        return  searchNode(key,mRoot);
    }

    public Node searchNode(int key,Node root){
        Node res=null;
        if(null != root){
            if(root.mKey == key)return root;
            if(root.mKey>key){
                return searchNode(key,root.mLeft);
            }
            if(root.mKey<key){
                return searchNode(key,root.mRight);
            }
        }
        return  res ;
    }

    protected void rotateLeft(Node node){
        if(null != node){
            Node r = node.mRight;
            if(null != r){
                r.mParent = node.mParent;
                node.mRight = r.mLeft;
                r.mLeft = node ;
            }
        }
    }

    protected void rotateRight(Node node){
        if(null != node){
            Node l = node.mLeft;
            if(null != l){
                l.mParent = node.mParent;
                node.mLeft = l.mRight;
                l.mRight = node ;
            }
        }
    }

}
