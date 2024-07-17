package com.lj.algorithm;

/**
 * 整数反转
 */
public class Test1 {
    
    public static void main(String[] args) {
        System.out.println(new Test1().reverse("-123456"));
    }
    
    
    /**
     * 对任意长度整数进行反转
     *
     * @param intString 整数字符串
     * @return 反转后的整数字符串
     */
    public String reverse(String intString) {
        if (!intString.matches("-?\\d+")) {
            return null;
        }
        if (intString.startsWith("-")) {
            return "-" + new StringBuilder(intString.substring(1)).reverse().toString();
        }else {
            return new StringBuilder(intString).reverse().toString();
        }
    }
    
    
  
    
}
