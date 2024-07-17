package com.lj.problem;


import com.lj.datastructure.notline.list.MyLoopLinkedList;
import com.lj.datastructure.notline.list.Node;

import java.util.Arrays;

/**
 * @author gwr
 * @Classname Joseph
 * @Description
 * 约瑟夫问题：有 N 个人围成一圈，每个人都有一个编号，编号由入圈的顺序决定，第一个入圈的人编号为 1，
 * 最后一个为 N，从第 k (1<=k<=N)个人开始报数，数到 m (1<=m<=N)的人将出圈，然后下一个人继续从 1
 * 开始报数，直至所有人全部出圈，求依次出圈的编号。
 * @Date 2022/4/21
 */
public class Joseph {

    MyLoopLinkedList<Integer> myLinkedList;
    int k;
    int m;
    
    public Joseph(int k , int m ,Integer[] numbers) {
        this.k = k;
        this.m = m;
        this.myLinkedList = new MyLoopLinkedList<>();
        for(Integer i:numbers){
            myLinkedList.addLast(i);
        }
        myLinkedList.show();
    }
    
    public Integer[] getOutArray(){
        Node<Integer> h = myLinkedList.getHead();
        int i=0;
        for( ; i<k ; i++){
            h = h.getNext();
        }
        
        Integer[] array = new Integer[myLinkedList.getSize()];
        int index=0;
        while(myLinkedList.getSize()>0){
    
            for(i=0 ; i<m-1 ; i++){
                h = h.getNext();
            }
            
            Integer v = h.getNext().getV();
            //[29, 49, 69, 89, 109, 4, 24, 45, 66, 87, 108, 5, 26, 48, 71, 93, 115, 12, 35, 58, 81, 104, 2, 27, 52, 76, 100, 124, 23, 51, 77, 102, 3, 31, 57, 84, 112, 14, 41, 70, 98, 1, 33, 62, 92, 121, 28, 60, 91, 122, 32, 64, 97, 8, 40, 75, 111, 19, 56, 95, 9, 44, 83, 120, 38, 79, 118, 37, 80, 123, 43, 88, 11, 55, 105, 22, 74, 6, 54, 107, 36, 94, 20, 82, 16, 73, 15, 78, 18, 90, 34, 106, 53, 125, 85, 42, 117, 86, 50, 17, 114, 99, 67, 61, 47, 46, 59, 65, 96, 113, 13, 68, 7, 101, 39, 30, 72, 119, 110, 10, 116, 103, 25, 21, 63]
            //[29, 49, 69, 89, 109, 4, 24, 45, 66, 87, 108, 5, 26, 48, 71, 93, 115, 12, 35, 58, 81, 104, 2, 27, 52, 76, 100, 124, 23, 51, 77, 102, 3, 31, 57, 84, 112, 14, 41, 70, 98, 1, 33, 62, 92, 121, 28, 60, 91, 122, 32, 64, 97, 8, 40, 75, 111, 19, 56, 95, 9, 44, 83, 120, 38, 79, 118, 37, 80, 123, 43, 88, 11, 55, 105, 22, 74, 6, 54, 107, 36, 94, 20, 82, 16, 73, 15, 78, 18, 90, 34, 106, 53, 125, 85, 42, 117, 86, 50, 17, 114, 99, 67, 61, 47, 46, 59, 65, 96, 113, 13, 68, 7, 101, 39, 30, 72, 119, 110, 10, 116, 103, 25, 21, 63]
            //[29, 49, 69, 89, 109, 4, 24, 45, 66, 87, 108, 5, 26, 48, 71, 93, 115, 12, 35, 58, 81, 104, 2, 27, 52, 76, 100, 124, 23, 51, 77, 102, 3, 31, 57, 84, 112, 14, 41, 70, 98, 1, 33, 62, 92, 121, 28, 60, 91, 122, 32, 64, 97, 8, 40, 75, 111, 19, 56, 95, 9, 44, 83, 120, 38, 79, 118, 37, 80, 123, 43, 88, 11, 55, 105, 22, 74, 6, 54, 107, 36, 94, 20, 82, 16, 73, 15, 78, 18, 90, 34, 106, 53, 125, 85, 42, 117, 86, 50, 17, 114, 99, 67, 61, 47, 46, 59, 65, 96, 113, 13, 68, 7, 101, 39, 30, 72, 119, 110, 10, 116, 103, 25, 21, 63]
            // 从头开始删
//            myLinkedList.delete(v);
            // 从前一个开始删
//            myLinkedList.delete(h,v);
            // 直接删除该节点的下一个节点
            myLinkedList.deleteNextNode(h);
            array[index++] = v;
        }
    
        return array;
    }
    
    public static void main(String[] args) {
        int size = 6;
        Integer []data = new Integer[size];
        for(int i=0 ; i<size; i++){
            data[i] = i+1;
        }
        Joseph joseph = new Joseph(0,4, data);
        System.out.println(Arrays.toString(joseph.getOutArray()));
        
        
        
        
    }
    
}
