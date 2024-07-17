package com.lj.algorithm.search;

import cn.hutool.core.util.RandomUtil;
import com.lj.algorithm.sort.BucketSort;
import com.test.common.aop.annotation.PrintLog;
import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author gwr
 * @Classname Searcher
 *
 * @Date 2022/4/29
 */
public class Searcher {

    @Test
    @PrintLog(count = 20)
    public void testSearch() throws Exception {
        int num[] = MyArrayUtil.getRandomArray(50,1,100);
        BucketSort.radix(num);
        System.out.println(Arrays.toString(num));
        int target = RandomUtil.randomInt(1,100);
        System.out.println("target:"+target);
        System.out.println("lineFind->"+lineFind(num,target));
        System.out.println("binaryFind->"+binarySearch(num,target));
        System.out.println("insertFind->"+insertSearch(num,target));
        List<Integer> result1 = lineFindAll(num, target);
        result1.sort(Comparator.comparingInt(i -> i));
        System.out.println("lineFindAll->"+result1);
        List<Integer> result2 = binarySearchAll(num, target);
        result2.sort(Comparator.comparingInt(i -> i));
        System.out.println("binaryFindAll->"+result2);
        List<Integer> result3 = binarySearchAll(num, target);
        result3.sort(Comparator.comparingInt(i -> i));
        System.out.println("insertFindAll->"+result3);
        System.out.println();
    }
    
    /**
     * 线性查找所有
     * @param num
     * @param value
     * @return
     */
    public List<Integer> lineFindAll(int[] num, int value){
        List result = new ArrayList();
        for(int i=0 ; i<num.length ; i++){
            if(num[i] == value){
                result.add(i);
            }
        }
        return result;
    }
    
    /**
     * 线性查找
     * @param num
     * @param value
     * @return
     */
    public int lineFind(int[] num,int value){
        for(int i=0 ; i<num.length ; i++){
            if(num[i] == value){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 二分查找，找到所有匹配值的索引
     * @param num
     * @param value
     * @return
     */
    public List<Integer> binarySearchAll(int[]num , int value){
        int end = num.length-1;
        int start = 0;
        int mid;
        List<Integer> result = new ArrayList<>();
        while( end>=start ){
            mid = (start+end) / 2;
            if(value == num[mid]){
                result.add(mid);
                // 左右两边搜索相同的值
                for(int k=mid+1; k < num.length && num[k]==value; result.add(k),k++);
                for(int k=mid-1; k >= 0 && num[k]==value; result.add(k),k--);
                break;
            }else if(value < num[mid] ){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return result;
    }
    
    /**
     * 二分查找，找到一个便返回
     * @param num
     * @param value
     * @return
     */
    public int binarySearch(int[] num,int value){
        int end = num.length-1;
        int start = 0;
        int mid;
        while( end>=start ){
            mid = (start+end) / 2;
            if(value == num[mid]){
                return mid;
            }else if(value < num[mid] ){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }

    /**
     * 插值查找，类似于二分，自适应二分
     * 二分查找: next = (low+high)/2 = low+1/2(high-low)
     * 插值自适应next = low + [(key-a[low])/(a[high]-a[low])]*(high-low)
     * @param num
     * @param value
     * @return
     */
    public int insertSearch(int num[],int value){
        int end = num.length-1;
        int start = 0;
        int next;
        while( start <= end ){
            if(start >= end){
                next = start;
            }else{
                next = start + (end-start)*(value-num[start])/(num[end]-num[start]);
            }
            if(next < start){
                next = start;
            }else if(next > end){
                next = end;
            }
            if(value == num[next]){
                return next;
            }else if(value < num[next] ){
                end=next-1;
            }else{
                start=next+1;
            }
        }

        return -1;
    }
    
    /**
     * 插值查找找到所有的匹配值的下标
     * @param num
     * @param value
     * @return
     */
    public List<Integer> insertSearchAll(int num[],int value){
        List<Integer> result = new ArrayList();
        int end = num.length-1;
        int start = 0;
        int next;
        while( end>=start ){
            if(start >= end){
                next = start;
            }else{
                next = start + (end-start)*(value-num[start])/(num[end]-num[start]);
            }
            if(next < start){
                next = start;
            }else if(next > end){
                next = end;
            }
            if(value == num[next]){
                result.add(next);
                for(int k=next+1; k < num.length && num[k]==value; result.add(k),k++);
                for(int k=next-1; k >= 0 && num[k]==value; result.add(k),k--);
                break;
            }else if(value < num[next] ){
                end=next-1;
            }else{
                start=next+1;
            }
        }

        return result;
    }

}
