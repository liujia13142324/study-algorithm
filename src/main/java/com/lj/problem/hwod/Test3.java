package com.lj.problem.hwod;

import java.util.Arrays;

public class Test3 {
    
    
    public int solve(String str) {
        int max = 0;
        int left = 0;
        int[] hash = new int[128];
        Arrays.fill(hash, -1);
        char c = '_';
        int i = 0;
        for (int j = str.length(); i < j; i++) {
            c = str.charAt(i);
            if (hash[c] != -1) {
                max = Math.max(i - left, max);
                if (hash[c] >= left) {
                    left = hash[c] + 1;
                }
            }
            
            hash[c] = i;
        }
        
        return Math.max(i - left, max);
    }
    
    public static void main(String[] args){
        System.out.println(new Test3().solve("abcabcbb"));
        System.out.println(new Test3().solve("bbbbbb"));
        System.out.println(new Test3().solve("pwwkew"));
    }
}
