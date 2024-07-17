package com.lj.problem.leetcode;

import com.lj.algorithm.AlgorithmUtil;

import java.util.HashSet;
import java.util.List;

public class TestHuiwenDelete {
  // 删除一个字符，就是在字符中选出一个位置。 即排列组合
  // ** 删除字符串中的任意字符，求出字符串中所有的回文串->暴力法，通过排列组合的思想算出所有的可能的串，逐一判断。
	public static void main(String[] args) {
		String input="pop-upu";
		print(input);
	}
	
	public static void print(String input){
		HashSet<String>result = new HashSet<String>();
		input = input.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "");
		int size = input.length();
		for(int i=size ; i>=2 ; i--){
			List<String> sub = AlgorithmUtil.getCnm2(i,input);
			for(int j=0 ; j<sub.size() ; j++){
				if(testIsHuiwen(sub.get(j))){
					result.add(sub.get(j));
				}
			}
			if(result.size()>0){
				break;
			}
		}
		System.out.println(result);
	}
	
	
	
	public static boolean testIsHuiwen(String s){
        int low = 0;
        int high = s.length()-1;
        char[]cs = s.toCharArray();
        
        while(high>low){
        	if(cs[low] != cs[high])
            {//如果对称位置的不相等
                return false;
            }
            low++;
            high--;
        }
        return true;
	}
}
