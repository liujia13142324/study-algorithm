package com.lj.problem.leetcode;

import org.junit.Test;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compress-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringCompress {

    @Test
    public void test1(){
        System.out.println(compressString("aabcccccaaa"));
    }

    public String compressString(String S) {
        int i = 0 , j , l = S.length();
        char c [] = S.toCharArray();
        StringBuffer t = new StringBuffer();
        while(i<l){
            j = i+1;
            int count = 1;
            t.append(c[i]);
            while (j<l && c[i] == c[j]){
                j++;count++;
            }
            t.append(count);
            i=j;
        }
        if(t.length() < l){
            return t.toString();
        }
        return S;
    }
}
