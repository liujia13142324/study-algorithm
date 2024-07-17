package com.lj.problem.hwod;

import cn.hutool.core.math.MathUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述:有一个字符串数组words和一个字符串chars。
 * 假如可以用chars中的字母拼写出words中的某个“单词”(字符串)，那么我们就认为你掌握了这个单词。words的字符仅由 a-z英文小写字母组成。例如: abcchars 由 a-z 英文小写字母和 ”?”组成。其中英文问号"?”表示万能字符,能够在拼写时当做任意一个英文字母。 例如:"?"可以当做“a"等字母注意:每次拼写时，chars中的每个字母和万能字符都只能使用一次。输出词汇表words中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
 * 输入描述:第1行输入数组words的个数，记为N。从第2行开始到第N+1行依次输入数组words的每个字符串元素，第N+2行输入字符串chars.
 * 输出描述:输出一个整数，表示词汇表words中你掌握的单词个数。
 * 补充说明:注意:
 * 1<= words.length <= 100
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母、英文问号
 *
 * 示例1
 * 输入:4
 * cat
 * bt
 * hat
 * tree
 * atach??
 * 输出:3
 * 说明:可以拼写字符串"cat”、"bt”和"hat
 *
 * 示例2
 * 输入:3
 * hello
 * world
 * cloud
 * welldonehoneyr
 * 输出:2
 * 说明:可以拼写字符串"hello"和"world
 *
 * 示例3
 * 输入:3
 * apple
 * car
 * window
 * welldoneapplec?
 * 输出:2
 * 说明:可以拼写字符串"apple”和'car”
 */
public class StringSpell {
    
    public int solve(String[] input) {
        int i,count;
        String str[];
        for (i = 0, count = Integer.valueOf(input[0]), str = new String[count]; i < count; i++) {
            str[i] = input[i+1];
        }
        
        int commonCount = 0;
    
        char[] tmp = input[i + 1].toCharArray();
    
        Map<Character, Integer> map = new HashMap<>(Math.min(tmp.length/2, 1));
        
        for (char c: tmp) {
            if ('?' == c) {
                commonCount++;
            }
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        Map<Character,Integer> remainCount = new HashMap<Character,Integer>(Math.min(count/2, 1));
        int result = 0;
        
        for (i = 0; i < str.length; i++) {
            remainCount.clear();
            char[] chars = str[i].toCharArray();
            int matchCount = 0;
            for (int j = 0; j < chars.length; j++) {
                if (map.get(chars[j]) != null) {
                    if (remainCount.get(chars[j])  == null) {
                        matchCount++;
                        remainCount.put(chars[j], map.get(chars[j]) - 1);
                    }else if (remainCount.get(chars[j]) > 0) {
                        matchCount++;
                        remainCount.put(chars[j], remainCount.get(chars[j]) - 1);
                    }
                }
                
                if (matchCount + commonCount >= chars.length) {
                    result++;
                    break;
                }
            }
            
        }
        
        return result;
    }
    
    public static void main(String[] args) {
     
    
        System.out.println(new StringSpell().solve(
                new String[]{
                        "4"
                        ,"cat"
                        ,"bt"
                        ,"hat"
                        ,"tree"
                        ,"atach??"
                }
        ));
    
        System.out.println(new StringSpell().solve(
                new String[]{
                        "3"
                        ,"hello"
                        ,"world"
                        ,"cloud"
                        ,"welldonehoneyr"
                }
        ));
    
        System.out.println(new StringSpell().solve(
                new String[]{
                        "3"
                        ,"apple"
                        ,"car"
                        ,"window"
                        ,"welldoneapplec?"
                }
        ));
    
    }
    
    
   
    
    
    @Test
    public void test3() {
        int [] ints = new int [] {1,2,3,4,5};
        while (true) {
            int post = ints.length - 1;
            int pre = post - 1;
            while (pre >= 0 && ints[pre] > ints[post]) {pre--; post--;}
            
            if (pre < 0) {
                break;
            }
            int i = ints.length - 1;
            for (; ints[i] < ints[pre]; i--) { }
            
            int tmp = ints[pre];
            ints[pre] = ints[i];
            ints[i] = tmp;
            Arrays.sort(ints, pre+1, ints.length );
            System.out.println(Arrays.toString(ints));
        }
        
    }
    
    
}
