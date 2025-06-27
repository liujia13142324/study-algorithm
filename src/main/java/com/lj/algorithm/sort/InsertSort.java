package com.lj.algorithm.sort;

import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 查询排序
 */
public class InsertSort {

    @Test
    public void test(){
        int[] randomArray = MyArrayUtil.getRandomArray(20);
        System.out.println(Arrays.toString(sort(randomArray.clone())));
        System.out.println(Arrays.toString(sort2(randomArray.clone())));
        System.out.println(Arrays.toString(shell(randomArray.clone())));
        System.out.println(Arrays.toString(shellByExchange(randomArray.clone())));
        System.out.println(Arrays.toString(shell2(randomArray.clone())));
        System.out.println(Arrays.toString(randomArray));
    }
    
    /**
     * 简单插入排序
     * @param nums
     * @return
     */
    public int[] sort(int[] nums){
        outer:
        for(int i=1 ; i<nums.length; i++ ){
            // 从前遍历判断是否需要插入，如果没有，说明节点i就应该排到最后，也就是现在的位置。无需插入
            for(int j=0 ; j<i ; j++){
                // 插入条件
                if(nums[j] > nums[i] ){
                    // 插入条件满足，准备插入
                    int tmp = nums[i];
                    // 后移动
                    for(int k=i; k>j; k--){
                        nums[k]=nums[k-1];
                    }
                    // 插入
                    nums[j] = tmp;
                    continue outer;
                }
            }
        }
        return nums;
    }
    
    /**
     * while循环发，从后面遍历依次比较、并且后移
     * @param nums
     */
    public int[] sort2(int[] nums){
        
        for(int i=1 ; i<nums.length ; i++){
            int last = i-1;
            int insertVal = nums[i];
            while( last>=0 && nums[last]>insertVal ){
                nums[last+1]=nums[last];
                last--;
            }
            nums[last+1] = insertVal;
        }
        return nums;
    }
    
    
    /**
     * shell排序交换法
     * @return
     */
    public int[] shellByExchange(int arr[]){
        
        // 分组，不断减少分组间隔
        for(int gap=arr.length/2 ; gap>0 ; gap/=2){
            // 遍历每组
            for( int i = gap ; i<arr.length ; i++ ){
                // 交换法，不断的和有序序列一个个比较，小于就交换，否则退出，即插入完成
                int last = i-gap;
                int val = arr[i];
                while( last>=0 && arr[last] > val){
                    arr[last+gap] = arr[last];
                    last -= gap;
                }
                arr[last + gap] = val;
            }
        }
        return arr;
    }
    
    /**
     * 希尔排序(移位发)，插入排序的有一种优化
     * @return
     */
    public int[] shell(int[] arr){
        int length = arr.length;
        int groupCount = length/2;

        while(groupCount >= 1){
            for(int i=0 ; i<groupCount ; i++){
                // 循环，从无序第一个开始
                for(int j=i+groupCount; j<length ; j+=groupCount){
                    // 找到每轮的每组有序集合的最后一个 ==> j-groupCount
                    int lastIndex = j-groupCount;
                    // 找到每轮的每组无序集合的第一个 ==> j-groupCount
                    int insertVal = arr[j];
                    while( lastIndex>=0 && arr[lastIndex]>insertVal ){
                        arr[lastIndex+groupCount] = arr[lastIndex];
                        lastIndex -= groupCount;

                    }
                    arr[lastIndex+groupCount] = insertVal;
                }
            }
            groupCount/=2;
        }

        return arr;
    }


    /**
     * 其实这个逻辑和上面这个差不多
     * @param arr
     * @return
     */
    public int[] shell2 ( int arr[] ){
        int j ;
        //第一层循环  计算需要 排序的小数组
        for (int inteval = arr.length / 2; inteval > 0; inteval /= 2) {

            //第二层循环 比较  当前小组累加间隔
            for (int i = inteval; i < arr.length; i++) {
                Integer temp = arr[i];
                //第三层循环，进行插入操作
                for (j = i; j >= inteval && temp.compareTo(arr[j - inteval]) < 0; j -= inteval) {
                    arr[j] = arr[j - inteval];
                }
                arr[j] = temp;
            }
        }
        return arr;
    }
    
}
