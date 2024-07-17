package com.lj.algorithm.sort;

import com.test.common.aop.annotation.PrintLog;
import com.test.common.utils.MyArrayUtil;
import com.test.common.utils.MyRandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {

    /**
     * @param num
     */
    @PrintLog
    public void sort(int[] num){
        int tmp;
        for(int i=0,i0=num.length-1 ; i<i0 ; i++){
            for(int j=0,j0=num.length-i-1; j<j0; j++){
                if(num[j]>num[j+1]){
                    tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
            }
        }
    }
    
    
    public void sort2(int[] num) {
        
        for (int i=0; i<num.length-1; i++) {
            
            for (int j=0; j<num.length-i-1; j++) {
                
                if (num[j] > num[j+1]) {
                    int tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
                
            }
        }
    }

    @Test
    public void test() {
        int[] randomArray = MyArrayUtil.getRandomArray(20);
        System.out.println(Arrays.toString(randomArray));
        sort2(randomArray);
        System.out.println(Arrays.toString(randomArray));
    }

}
