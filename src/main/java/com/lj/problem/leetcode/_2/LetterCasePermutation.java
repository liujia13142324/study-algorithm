package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * tmp
 * 示例 1：
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]

 * 示例 2:
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 *
 * 提示:
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 *
 */
public class LetterCasePermutation {

    @Test
    public void test() {
        System.out.println(letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation2("a1b2"));
    }

    public List<String> letterCasePermutation2(String s) {
        List<String> ans = new ArrayList<>();
        dfs2(0, s.toCharArray(), ans);
        return ans;
    }

    private void dfs2(int i, char[] paths, List<String> ans) {
        if (i == paths.length) {
            ans.add(new String(paths));
            return;
        }
        dfs2(i + 1, paths, ans);

        if (Character.isAlphabetic(paths[i])) {
            // 不记录也都可以
//            char c = paths[i];
            paths[i] = reverseCase(paths[i]);
            dfs2(i + 1, paths, ans);
//            paths[i] = c;
        }
    }


    public List<String> letterCasePermutation(String s) {
        char[] paths = new char[s.length()];
        List<String> ans = new ArrayList<>();
        dfs(s, 0, paths, ans);
        return ans;
    }

    private void dfs(String s, int i, char[] paths, List<String> ans) {
        if (i == s.length()) {
            ans.add(new String(paths));
            return;
        }
        char c = s.charAt(i);
        paths[i] = c;
        dfs(s, i + 1, paths, ans);

        if (Character.isAlphabetic(c)) {
            paths[i] = reverseCase(c);
            dfs(s, i + 1, paths, ans);
        }
    }


    private char reverseCase(char c) {
        if (c < 'a') {
            return (char) (c + 32);
        }else {
            return (char) (c - 32);
        }
    }

}
