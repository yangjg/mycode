package com.example;

import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;

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

    public boolean insertChild(Node child){
        if(null==child){
            return  false;
        }
        Node f = searchNode(child.mKey);
        if(f!=null){
            f.mValue = child.mValue;
            return true;
        }
        if(null == mRoot){
            mRoot =child;
            return true;
        }
        return insertChild(child,mRoot);
    }

    private boolean insertChild(Node child, Node root) {
        if (null == root) {
            root = child;
            return true;
        }
        if (child.mKey < root.mKey) {
            return insertChild(child, root.mLeft);
        } else {
            return insertChild(child, root.mRight);
        }
    }

    public boolean deleteChild(int key) {
        Node f = searchNode(key);
        if (f != null) {
            //deleteChild(f);
            if (f == mRoot) {
                mRoot = null;
                return true;
            }
            Node maxLeftChild = findLeftTreeMaxChild(f);
            f.mKey = maxLeftChild.mKey;
            f.mValue = maxLeftChild.mValue;
            if (maxLeftChild.mParent.mLeft == maxLeftChild) {
                maxLeftChild.mParent.mLeft = null;
            } else {
                maxLeftChild.mParent.mRight = null;
            }
            maxLeftChild.mParent = null;
            return true;
        }
        return false;
    }

    public boolean deleteChild(Node child){
        if(null != child){
          return   deleteChild(child.mKey);
        }
        return false;
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

    public Node findLeftTreeMaxChild(Node node){
        if(null != node.mLeft){
            return findLeftChild(node.mLeft.mRight);
        }
        return  node;

    }

    private Node findLeftChild(Node node){
        if(null != node.mLeft){
            return findLeftChild(node.mLeft);
        }
        return  node;
    }

    public Node findRightTreeMinChild(Node node){
        if(null != node.mRight){
            return findRightChild(node.mRight
                    .mLeft);
        }
        return  node;
    }

    private Node findRightChild(Node node){
        if(null != node.mRight){
            return findRightChild(node.mRight);
        }
        return  node;
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
