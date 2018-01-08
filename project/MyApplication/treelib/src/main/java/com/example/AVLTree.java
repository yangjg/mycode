package com.example;

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
    }

    private int getNodeHeight(Node node){
        if(null != node){
            return  node.mHeight;
        }
        return -1;
    }



    public void insert(int key){
        insertNode(mRoot,new Node(null,null,null,key,null));
    }

    public void insert(Node node){
        insertNode(mRoot,node);
    }

    private void insertNode(Node parent, Node node){
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
            if( getNodeHeight(parent.mLeft)>getNodeHeight(parent.mRight)){
                //LL
                if(getNodeHeight(parent.mLeft.mLeft) > getNodeHeight(parent.mLeft.mRight)){
                    current =  LL(parent);
                }
                //LR
                else{
                    current =   LR(parent);
                }
            }
            else{
                //RR
                if(getNodeHeight(parent.mRight.mRight)>getNodeHeight(parent.mRight.mLeft)){
                    current =  RR(parent);
                }
                //RL
                else{
                    current =  RL(parent);

                }
            }
            return;
        }
        parent.mHeight = Math.max(getNodeHeight(parent.mLeft),getNodeHeight(parent.mRight))+1;
        return;
    }


    public void delete(int key){
        boolean ok =  delete(mRoot,key);
        if(ok){
            inSubTree();
        }
    }

    private boolean delete(Node node,int key){

        if(node == null)return  false;
        if(key == node.mKey){

            if(node.mLeft == null && null == node.mRight){
                deleteNode(node);
            }
            else if(node.mLeft == null && null != node.mRight){
                deleteNodeRight(node);
            }
            else if(null  != node.mLeft && null == node.mRight){
                deleteNodeLeft(node);
            }
            else{
                //删除的节点有左右子树的情况
                if(getNodeHeight(node.mLeft) > getNodeHeight(node.mRight)){
                    //左子树的高度大于右子树的高度，那么就找左边最大值。
                    Node prev = node.mLeft;
                    while (null != prev.mRight){
                        prev = prev.mRight;
                    }
                    node.mKey = prev.mKey;
                   return delete(node.mLeft,prev.mKey);
                }
                else{ //左子树的高度小于或等于右子树的高度，那么就找右边最小值。
                    Node suffix = node.mRight;
                    while (null != suffix.mLeft){
                        suffix = suffix.mLeft;
                    }
                    node.mKey = suffix.mKey;
                    return delete(node.mRight, suffix.mKey);
                }
            }
            return true;
        }
        if(key < node.mKey){ //遍历左子树去删除
           boolean ok = delete(node.mLeft,key);
            if(ok){
                if(getNodeHeight(node.mRight)-getNodeHeight(node.mLeft)==2){
                    //处理平衡
                    if(getNodeHeight(node.mRight.mRight) >= getNodeHeight(node.mRight.mLeft)){
                        //右子树的  右子树高度大于左子树的高度
                        RR(node);
                        //RR
                    }
                    else {
                        RL(node);
                        //RL
                    }
                }
            }
            return ok;

        }
        else {
            boolean ok = delete(node.mRight,key);
            if(ok){
                if(getNodeHeight(node.mLeft)-getNodeHeight(node.mRight)==2){
                    //处理平衡
                    if(getNodeHeight(node.mLeft.mLeft) >= getNodeHeight(node.mLeft.mRight)){

                        LL(node);

                    }
                    else {
                        LR(node);
                    }
                }
            }
            return ok;
        }
    }

    private void deleteNode(Node node){
        if(null != node){
            Node parent = node.mParent;
            if(parent==null)return;
            if(node == parent.mLeft){ // 是左孩子
                parent.mLeft = null;
            }
            else{
                parent.mRight = null;
            }
            node.mParent = null;
        }
    }

    private void deleteNodeRight(Node node){
        if(null != node){
            Node parent = node.mParent;
            if(parent==null)return;
            if(node == parent.mLeft){ // 是左孩子
                parent.mLeft = node.mRight;
            }
            else{
                parent.mRight = node.mRight;
            }
            node.mParent = null;
        }
    }

    private void deleteNodeLeft(Node node){
        if(null != node){
            Node parent = node.mParent;
            if(parent==null)return;
            if(node == parent.mLeft){ // 是左孩子
                parent.mLeft = node.mLeft;
            }
            else{
                parent.mRight = node.mLeft;
            }
            node.mParent = null;
        }
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
        return rotate_left(node);
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
        node .mHeight = Math.max(node.mLeft.mHeight,node.mRight.mHeight)+1;
        r.mHeight =  Math.max(r.mLeft.mHeight,r.mRight.mHeight)+1;
        return r;
    }

    private Node rotate_right(Node node){
        Node parent = node.mParent;
        if(null == parent){

        }
        Node l = node.mLeft;
        node.mLeft = l.mRight;
        l.mRight.mParent = node;
        l.mRight = node;
        l.mParent = node.mParent;
        node.mParent = l;
        node .mHeight = Math.max(node.mLeft.mHeight,node.mRight.mHeight)+1;
        l.mHeight =  Math.max(l.mLeft.mHeight,l.mRight.mHeight)+1;
        return l;
    }

    public void inSubTree(){
        inSubTree(mRoot);
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
