package com.lj.problem.leetcode;

import com.lj.algorithm.AlgorithmUtil;
import com.lj.study.common.aop.annotation.PrintLog;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 练习10: (5) 吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到，而这对数字
 * 各包含乘积的一半位数的数字，其中从最初的数字中选取的数字可以任意排序。以两个0结尾的
 * 数字是不允许的，例如，下列数字都是“吸血鬼”数字;
 *
 * 1260=21*60
 *
 * 1827=21*87
 *
 * 2187=27*81                                                                                       ?
 *
 * 写一个程序，找出4位数的所有吸血鬼数字 (Dan Forhan推荐) 。
 */
public class EviData {
    
    @AllArgsConstructor
    class EviDataEntity {
        int num;
        String description;
    
        @Override
        public String toString() {
            return num+"=>"+description;
        }
    }
    
    /**
     * 找到所有4位的吸血鬼数字
     */
    public List<EviDataEntity> findAllNBitsEviData(int bits){
    
        Assert.assertEquals(bits%2 , 0);
        
        int start = (int) Math.pow(10,bits-1);
        int end = (int) Math.pow(10,bits);
        int half = bits/2;
        char[] chars;
        List<List<String>> firstPart;
        List<List<String>> secondPart;
        String [] remain;
        String firstNumStr;
        int firstNum;
        String secondNumStr;
        
        List<EviDataEntity> result = new ArrayList<>();
        String [] indexArray = new String[bits];
        for(int i=0 ; i<bits ; i++){
            indexArray[i] = i+"";
        }
    
        eviStart:
        for(int i=start ; i<end ; i++){
            if(i % 100 == 0){
                continue;
            }
            chars = (i+"").toCharArray();
            
            firstPart = AlgorithmUtil.getAnm(half,indexArray.clone());
            for(List<String> first : firstPart){
                if('0' == chars[Integer.parseInt(first.get(0))]){
                    continue ;
                }
                remain = new String[half];
                int index = 0;
                for(int j=0 ; j<bits ; j++){
                    if(first.indexOf(indexArray[j])<0){
                        remain[index++] = indexArray[j];
                    }
                }
                secondPart = AlgorithmUtil.getAnm(half,remain);
                firstNumStr = "";
                for(int j=0 ; j<first.size() ; j++){
                    firstNumStr += chars[Integer.parseInt(first.get(j))];
                }
                firstNum = Integer.parseInt(firstNumStr);
                
                for(List<String> second : secondPart){
                    if('0' == chars[Integer.parseInt(second.get(0))]){
                        continue;
                    }
                    
                    secondNumStr = "";
                    for(int j=0 ; j<second.size() ; j++){
                        secondNumStr += chars[Integer.parseInt(second.get(j))];
                    }
                    
                    if(firstNum * Integer.parseInt(secondNumStr) == i){
                        result.add(new EviDataEntity(i,firstNumStr+"*"+secondNumStr+"="+i));
                        continue eviStart;
                    }
                }
            }
        }
        
        return result;
    }
    
    @Test
    public void test(){
        List<EviDataEntity> list = test(4);
        System.out.println(list);
        list = test(6);
        System.out.println(list);
    }
    
    @PrintLog
    public List<EviDataEntity> test(int bits){
        return findAllNBitsEviData(bits);
    }
    
}
