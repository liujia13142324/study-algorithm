package com.lj.problem;

import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 中文转数字
 */
public class String2Number {

    @Test
    public void test(){
        System.out.println(transfer("九亿二千二百七十四万零一百四十九"));
        System.out.println(transfer("五百三十万六千零三"));
        System.out.println(transfer("十万"));
        System.out.println(transfer("一十万"));
        System.out.println(transfer("一千万"));
        System.out.println(transfer("十亿"));
        
        Random rd = new Random();
        for (int i = 0; i < 100; i++) {
            int in = rd.nextInt(Integer.MAX_VALUE);
            String s = NumberChineseFormatter.format(in, false);
            System.out.println(s + "--->" + transfer(s) + "---->" + in);
        }
        
       
    }
    
    int b = 100000000;
    int w = 10000;
    int t = 1000;
    int h = 100;
    int ten = 10;
    
    // 金额，正数，做好检查
    public int transfer(String str) {
        // 万、千、百、十
        int last = Integer.MAX_VALUE;
        // 1，2，3，4，5，6，7，8，0

        // 万之前可以递增，万之后不能
        
        int result = 0;
        int middle = 0;
        int lastNumber = -99;
        int next = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            next = getNumber(c);
            
            if (next == -1) {
                // 是单位
                next = getNext(c);
                
                if (next > last) {
                    // 如果单位递增的  --> 乘法
                    if (next < w) {
                        throw new IllegalArgumentException("数字异常！");
                    }
                    result += (middle * next);
                    last = Integer.MAX_VALUE;
                    middle = 0;
                    next = 0;
                }else {
                    // 十、十万、十亿的情况
                    if (next == ten && lastNumber < 0) {lastNumber = 1;}
                    if (lastNumber < 0) {
                        throw new IllegalArgumentException("数字异常！");
                    }
                    // 如果单位是递减的,或者为初始状态 --> 加法和乘法
                    middle += (lastNumber * next);
                    last = next;
                    next = 0;
                }
    
            }else {
                lastNumber = next;
            }
        }
        
        if (middle > 0) {
            result += middle;
        }
        
        if (next > 0) {
            result += next;
        }
        
        return result;
    }
    
    private int getNumber(char c) {
        int num = -1;
        if (c == '一') {
            num = 1;
        }else if (c == '二') {
            num = 2;
        }else if (c == '三') {
            num = 3;
        }else if (c == '四') {
            num = 4;
        }else if (c == '五') {
            num = 5;
        }else if (c == '六') {
            num = 6;
        }else if (c == '七') {
            num = 7;
        }else if (c == '八') {
            num = 8;
        }else if (c == '九') {
            num = 9;
        }else if (c == '零') {
            num = 0;
        }/*else if (c == '十') {
            num = 10;
        }*/
        
        return num;
    }
    
    private int getNext(char c) {
        if (c == '亿') {
            return b;
        }else if (c == '万') {
            return w;
        }else if (c == '千') {
            return t;
        }else if (c == '百'){
            return h;
        }else {
            return ten;
        }
    }
    
}
