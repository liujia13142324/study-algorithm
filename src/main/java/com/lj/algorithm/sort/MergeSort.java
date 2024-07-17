package com.lj.algorithm.sort;

import com.test.common.utils.MyArrayUtil;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    
    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(20,1, 1000);
        System.out.println(Arrays.toString(randomArray));
        sort(randomArray);
        System.out.println(Arrays.toString(randomArray));
    }
    
    public static int[] sort(int[] num) {
        mergeSort(num, 0, num.length - 1, new int[num.length]);
        return num;
    }
    
    // TODO 迭代实现
    
    public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        
        int i = 0;
        
        int j = low, k = mid + 1;  //左边序列和右边序列起始索引
        
        while (j <= mid && k <= high) {
            if (arr[j] < arr[k]) {
                tmp[i++] = arr[j++];
            } else {
                tmp[i++] = arr[k++];
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while (j <= mid) {
            tmp[i++] = arr[j++];
        }
        
        while (k <= high) {
            tmp[i++] = arr[k++];
        }
        
        // tmp数组复制到源数组
        /*for (int t = 0; t < i; t++) {
            arr[low + t] = tmp[t];
        }*/
        
        System.arraycopy( tmp, 0, arr, low, i);
    }
    
    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = (low + high) / 2;
            //对左边拆分
            mergeSort(arr, low, mid, tmp);
            //对右边拆分
            mergeSort(arr, mid + 1, high, tmp);
            //合并
            merge(arr, low, mid, high, tmp);
        }
    }
    
}
