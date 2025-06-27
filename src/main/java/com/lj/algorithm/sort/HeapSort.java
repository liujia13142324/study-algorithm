package com.lj.algorithm.sort;

import com.lj.study.common.aop.annotation.PrintLog;
import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

public class HeapSort {
    
    /**
     * https://www.cnblogs.com/chengxiao/p/6129630.html 堆排序讲解
     */
    
//    @PrintLog(count = 20)
    @Test
    public void test() {
        int[] num = MyArrayUtil.getRandomArray(30, 1, 100);
        MyArrayUtil.printArray(num);
        sort(num);
        System.out.println(MyArrayUtil.checkArrayOrder(num,true));
        MyArrayUtil.printArray(num);
    }
    
    @PrintLog
    public void sortForSpeed(int[] num){
        sort2(num);
    }
    
    public void sort(int num[]) {
        // 首先要构建一个大顶堆，从第一个非叶子节点开始构建 index = length/2 - 1 ;
        for (int i = num.length / 2 - 1; i >= 0; i--) {
            adjustHeap(num, i, num.length);
        }
        
        // 把第一个元素和最后一个元素交换位置，再调整大顶堆
        for (int i = num.length - 1; i > 0; i--) {
            swap(num, 0, i);
            //在原来大顶堆的条件下(除了根节点和最后节点，每个节点的根都大于左右孩子，即都是大顶堆)，后来大顶堆的构建直接从0,root开始调整即可
            adjustHeap(num, 0, i);
        }
        
    }
    
    private void adjustHeap(int[] num, int parent, int length) {
        int temp = num[parent];
        for (int i = 2 * parent + 1; i < length; i = 2 * i + 1) {
            //比较左右孩子最大的那个
            if (i + 1 < length && num[i] < num[i + 1]) {
                i++;
            }
            //如果孩子节点比当前节点大，把孩子放上去，在继续循环孩子的堆判断
            if (num[i] > temp) {
                num[parent] = num[i];
                parent = i;
            } else {
                break;
            }
        }
        num[parent] = temp;
    }
    
    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    
    public void sort2(int num[]) {
        
        // 只需要遍历一遍，构建一个大顶堆
        for( int i=num.length/2-1; i>=0; i-- ){
            adjustHeap2(num,i,num.length);
        }
        
        // 调节大顶堆，从第1个元素开始
        for( int n=num.length-1; n>0; n--){
            // 交换(n+1)个元素时候的调节结果
            swap(num,0,n);
            // 调节大顶堆，要调整的数组长度为 n
            adjustHeap2(num,0,n);
        }
        
    }
    
    private void adjustHeap2(int[] num, int i, int length) {
        int k = i;
        int j = i;
        // 从左孩子开始处理
        while( (k=2*k+1) < length ){
            if( k+1 < length && num[k+1] > num[k] ){
                k++;
            }
            if( num[k] > num[j] ){
                swap(num , k , j);
                j = k;
            }else{
                break;
            }
        }
    }
    
}
