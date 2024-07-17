package com.lj.algorithm.sort;

import com.test.common.aop.annotation.PrintLog;
import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;

public class MergeSort2 {

    @Test
    @PrintLog(count = 10)
    public void testSort(){
        MergeSort2 mergeSort2 = new MergeSort2();
        int[] randomArray = MyArrayUtil.getRandomArray(20, 1, 100);
        System.out.println(Arrays.toString(randomArray));
        mergeSort2.sort(randomArray);
        System.out.println(Arrays.toString(randomArray));
        System.out.println();
    }
    
    public void sort(int []num){
        mergeSort(num,0, num.length-1,new int[num.length]);
    }
    
    private void mergeSort(int[] num, int low, int high, int[] temp){
        
        if(low < high){
            int mid = (low + high)/2;
            mergeSort(num,low,mid,temp);
            mergeSort(num,mid+1,high,temp);
            merge(num,low,mid,high,temp);
        }
        
    }
    
    private void merge(int[] num, int low, int mid, int high, int[] temp) {
        // 第一个序列开始索引
        int i = low;
        // 第二个序列开始索引
        int j = mid+1;
        // tmp的索引
        int k = 0;
        
        while( i<=mid && j<=high ){
            if( num[i] > num[j] ){
                temp[k++] = num[j++];
            }else{
                temp[k++] = num[i++];
            }
        }
        
        // 第一个序列还有残余
        while( i <= mid ){
            temp[k++] = num[i++];
        }
        
        while( j <= high ){
            temp[k++] = num[j++];
        }
        
        // 复制
        k=0;
        while(low<=high){
            num[low++] = temp[k++];
        }
        
    }
    
}
