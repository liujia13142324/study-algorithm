package com.lj.algorithm;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.lj.study.common.aop.annotation.PrintLog;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KmpMatch {
    
    public static int match(String origin, String target, int[] matchTable) {
        char[] chars = origin.toCharArray();
        char[] targetChars = target.toCharArray();
        int targetOffset = 0;
        int sourceOffset = 0;
        int fromIndex = 0;
        int targetCount = targetChars.length;
        char first = targetChars[targetOffset];
        int j;
        int end;
        int k;
        int max = chars.length - targetCount ;
        
        // Remark: 在for循环里面，i++的位置在语法内做比在循环体做效率要高？
        for (int i = sourceOffset + fromIndex; i < max; ) {
            
            // 感觉是一种贪心优化，因为和目标串首字符能匹配是少数
            if (chars[i] != first) {
                while (++i < max && chars[i] != first);
            }
    
            if (i < max) {
                // 从第二个开始匹配
                j = i + 1;
                end = j + targetCount - 1;
                for (k = targetOffset + 1; j < end && chars[j] == targetChars[k]; j++, k++);
        
                if (j == end) {
                    return i - sourceOffset;
                }else if (matchTable != null ) {
                    // kpm 主要逻辑在这 往后移动， 匹配的位数-部分匹配表[匹配的位数下标]，减1是因为外部循环体要i++
//                    i = i + (k - matchTable[k-1]) - 1;
                    i = i + (k - matchTable[k-1]) ;
                }else {
                    i++;
                }
            }
            
           /*
           // 我写的效率低的代码 ，这里的控制i的移动，在外层循环不控制（模拟jdk在外层控制i后一位即i++）
           for (j = 0; j < targetCount; j++, i++) {
                if (chars[i] != targetChars[j]) {
                    break;
                }
            }
            if (j == targetCount) {
                idx = i - targetCount;
                break;
            } else if (matchTable != null && j > 0) {
                // kpm 主要逻辑在这 往后移动， 匹配的位数-部分匹配表[匹配的位数下标]
                i = i - j + (j - matchTable[j-1]);
            } else {
                // 前移j位，然后再往后移1位
                i = i - j + 1;
            }
            
            */
        }
        
        return -1;
    }
    
    public static int kmp(String origin, String target) {
        // 根据 target 生成部分匹配表
        int[] matchTable = generateMatchTable(target);
        return match(origin,target,matchTable);
    }
    
    /**
     * 生成部分匹配表：根据目标串的前缀和后缀集合最长公共元素的长度
     * ”A”的前缀和后缀都为空集，共有元素的长度为0；
     * ”AB”的前缀为[A]，后缀为[B]，共有元素的长度为0；
     * ”ABC”的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     * ”ABCD”的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     * ”ABCDA”的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为”A”，长度为1；
     * ”ABCDAB”的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为”AB”，长度为2；
     * ”ABCDABD”的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     *
     * @param target
     * @return
     */
    private static int[] generateMatchTable(String target) {
        int len = target.length();
        int result[] = new int[len];
        
        for (int i = 1; i < len; i++) {
            Set<String> s1 = new HashSet<>(i);
            Set<String> s2 = new HashSet<>(i);
            for (int j = 1; j <= i; j++) {
                s1.add(target.substring(0, j));
                s2.add(target.substring(j, i + 1));
            }
            s1.retainAll(s2);
            if (!s1.isEmpty()) {
                int maxLen = 0;
                for (String s : s1) {
                    maxLen = Math.max(s.length(), maxLen);
                }
                result[i] = maxLen;
            }
        }
        
        return result;
    }
    
    @Test
    public void testGenerateMatchTable() {
        System.out.println(Arrays.toString(generateMatchTable("ABCDABD")));
        String randomString = StringUtils.upperCase(RandomUtil.randomString(10));
        System.out.println(randomString);
        System.out.println(Arrays.toString(generateMatchTable(randomString)));
    }
    
    public static void main(String[] args) {
        String origin = IoUtil.read(KmpMatch.class.getResourceAsStream("/kpmTest"), StandardCharsets.UTF_8);
        String target = "ABCDABD";
        System.out.println("indexOf:" + indexOfTest(origin,target));
        System.out.println("kpm:" + kpmTest(origin,target));
        System.out.println("normal:" + normalTest(origin,target));
    }
    
    @PrintLog
    private static int indexOfTest(String origin, String target) {
        return origin.indexOf(target);
    }
    
    @PrintLog
    private static int normalTest(String origin, String target) {
        return KmpMatch.match(origin, target, null);
    }
    
    @PrintLog
    private static int kpmTest(String origin, String target) {
        return KmpMatch.kmp(origin,target);
    }
    
}
