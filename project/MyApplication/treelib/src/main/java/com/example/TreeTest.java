package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeTest {

    public static void main(String[] args){


        int end =-111;
        System.out.print("请输入N个数去初始化呢一颗树,用空格分割，按回车键结束输入");
        Scanner sc = new Scanner(System.in);
        List<Integer> array = new ArrayList<>();
        String nums=null;
        while (array.size()==0){
            //end = sc.nextInt();
            nums = sc.nextLine();
            String[] list = nums.split(" ");
            for (int i=0;i<list.length;i++){
                if( null == list[i].trim() || list[i].trim().length()==0 ){
                    continue;
                }
                try {
                    int num = Integer.parseInt(list[i]);
                    array.add(num);
                }
                catch (Exception e){

                }
            }
        }

        //TODO 去构造一颗树

        Node root = new Node(null,null,null,array.get(0),null);
        AVLTree avlTree = new AVLTree(root);
        for (int i=1;i<array.size();i++){
            avlTree.insert(array.get(i));
        }

        avlTree.inSubTree();

        int select = 0;
        while (select != -1){
            System.out.print("请输入以下选项功能选项 1.插入一个节点 ,2.删除一个节点 ，3打印树, -1结束程序");
            select = sc.nextInt();
            switch (select){
                case 1:
                    int insert = sc.nextInt();
                    avlTree.insert(insert);
                    avlTree.inSubTree();
                    break;
                case 2:
                    int delete = sc.nextInt();
                    avlTree.delete(delete);
                    break;
                case 3:
                    avlTree.inSubTree();
                    break;
                default:
                    break;
            }
        }


    }
}
