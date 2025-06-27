package com.lj.algorithm.sort;

import com.lj.study.common.aop.annotation.PrintLog;
import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;

public class SelectSort<T>{

	
	private void selectSort(Comparable<T>[] num) {
		for(int i=0 ; i<num.length ; i++){
			for(int j=i+1 ; j<num.length ; j++){
				if(num[i].compareTo( (T) num[j]) > 0){
					Comparable<T> temp = num[i];
					num[i] = num[j];
					num[j]=temp;
				}
			}
		}		
	}
	
	@PrintLog
	public static void selectSort(int[] num) {
		for(int i=0 ; i<num.length ; i++){
			int min=i;
			for(int j=i+1 ; j<num.length ; j++){
				if(num[min]>num[j]){
					min = j;
				}
			}
			if( min!=i ){
				int tmp = num[i];
				num[i] = num[min];
				num[min] = tmp;
			}
		}		
	}
    
    
    @PrintLog
    public static void selectSort2(int num[]){
        for(int i=0 ; i<num.length-1 ;i++){
            int minIndex=i;
            for(int j=i+1; j<num.length ; j++){
                if(num[minIndex]>num[j]){
                    minIndex=j;
                }
            }
            if(minIndex != i){
                int tmp = num[minIndex];
                num[minIndex] = num[i];
                num[i] = tmp;
            }
        }
    }
    
    /**
     * 选择带交换
     * @param c
     */
    @PrintLog
    public void sort2( int [] c){
        int length = c.length;
        for(int i=0 ; i<length-1 ; i++){
            for(int j=i+1 ; j<length ; j++){
                if(c[i] > c[j] ) {
                    int t = c[i];
                    c[i] = c[j];
                    c[j] = t;
                }
            }
        }
    }
    
    
    
    public void sort3(int[] arr) {
        
        for (int i=0; i<arr.length-1; i++) {
            for (int j=i+1; j<arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        
    }
    
    @Test
    public void test3() {
        int[] randomArray = MyArrayUtil.getRandomArray(20);
        System.out.println(Arrays.toString(randomArray));
        sort3(randomArray);
        System.out.println(Arrays.toString(randomArray));
    }

}
