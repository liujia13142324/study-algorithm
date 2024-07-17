package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 给定数字的范围是 [0, 108]
 */
public class MaxExchange {

    @Test
    public void testMaxSwamp(){
        System.out.println(maximumSwap(2736));
    }

    class CompareDesc implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }

    CompareDesc compareDesc = new CompareDesc();

    public int maximumSwap(int num) {
        String nums[] = (num+"").split("");
        String nums2[] = nums.clone();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.sort(nums2,compareDesc );

        // 把最大值放到前面去
        String max = null;
        String tmp = null;
        int k = 0;
        int j=0;
        int i=0;
        for(; i<nums2.length; i++){
            if( max == null ){
                if(nums[i].compareTo(nums2[i])!=0){
                    max = nums2[i];
                    tmp = nums[i];
                    nums[i] = max;
                    stringBuilder.append(max);
                    k++;
                    j=i+1;
                }else{
                    stringBuilder.append(nums[i]);
                }
            }else if(max.compareTo(nums2[i]) == 0){
                k++;
            }else{
                break;
            }
        }

        if(max == null){
            return num;
        }

        // 交换
        for(; j<nums.length; j++){
            if(tmp != null && max.equals(nums[j])){
                if( --k != 0 ){
                    stringBuilder.append(nums[j]);
                    continue;
                }
                stringBuilder.append(tmp);
                tmp = null;
                continue;
            }
            stringBuilder.append(nums[j]);
        }

        return Integer.parseInt(stringBuilder.toString());
    }

}
