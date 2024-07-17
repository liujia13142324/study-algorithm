package com.lj.algorithm.sort;

import com.test.common.aop.annotation.PrintLog;
import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;

public class BucketSort {
    
    public static int[] sort(int[] arr) throws Exception {
        return bucketSort(arr, 5);
    }
    
    public static int[] sort(int[] arr, int bucketSize) throws Exception {
        return bucketSort(arr, bucketSize);
    }
    
    public static int[] radix(int[] arr) throws Exception {
        return radixSort(arr);
    }
    
    @Test
    public void test1(){
    
        int[][] ints = new int[2][0];
        ints[0] = new int[]{1,2};
        ints[1] = new int[]{3,4};
        MyArrayUtil.printArrayOf2(ints, ", ");
    }
    
    private static int[] bucketSort(int[] arr, int bucketSize) throws Exception {
        if (arr.length == 0) {
            return arr;
        }
        
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        int[][] buckets = new int[bucketCount][0];
//    Map<Integer,ArrayList> buckets = new HashMap<>(bucketCount);
        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);
            buckets[index] = arrAppend(buckets[index], arr[i]);
    /*  if( buckets.get(index)==null ){
        buckets.put( index , new ArrayList<>(10000) );
      }
      buckets.get(index).add(arr[i]);*/
        }
        
        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 对每个桶进行排序
            if (bucketSize > 1) {
                bucket = MergeSort.sort(bucket);
            }
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }
   /* Set<Integer> ks = buckets.keySet();
    for (Integer k : ks) {
      List bucket = buckets.get(k);
      if (bucket.size() <= 0) {
        continue;
      }
      // 对每个桶进行排序
      if( bucketSize>1 ){
//        bucket = MergeSort.sort(bucket);
      }
      for (Object value : bucket) {
        arr[arrIndex++] = (int)value;
      }
    }*/
        return arr;
    }
    
    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private static int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
    
    
    /**
     * 基数排序，桶排序的扩展 （非负数）
     * TODO 扩展负数的基数排序
     */
    public static int[] radixSort(int[] num) {
        // 整个数组中最长的数字
        return radixSort(num,null);
    }
    
    public static int[] radixSort(int[] num, Integer maxLength) {
        // 0~9 每位数字的桶
        int [][] buckets = new int[10][num.length];
        // 记录每个桶实际存储的数据个数
        int [] bucketCounts = new int[10];
        // 数组中最长的数字
        if(maxLength == null){
            int max = Integer.MIN_VALUE;
            for(int n:num){
                if(n>max){
                    max = n;
                }
            }
            maxLength = (max+"").length();
        }
        
        //开始排序
        for(int i=1,a=0; a<maxLength ;a++,i*=10){
            
            // 放入桶中
            for(int j=0; j<num.length ; j++){
                int indexOfBucket = num[j] % (10*i) / i;
                buckets[indexOfBucket][bucketCounts[indexOfBucket]++] = num[j];
            }
            
            // 从桶中依次拿出来
            int k=0;
            for(int j=0 ; j<buckets.length ;j++){
                for(int h=0 ; h<bucketCounts[j] ; h++){
                    num[k++] = buckets[j][h];
                }
                bucketCounts[j] = 0;
            }
        }
        return num;
    }
    
    @Test
    @PrintLog(count = 10)
    public void testRadix(){
        int[] randomArray = MyArrayUtil.getRandomArray(10, 1, 50);
        System.out.println(Arrays.toString(randomArray));
        radixSort(randomArray);
        System.out.println(Arrays.toString(randomArray));
        System.out.println();
    }
    
}
