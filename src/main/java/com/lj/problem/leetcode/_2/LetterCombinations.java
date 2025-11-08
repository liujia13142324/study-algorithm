package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

 * 示例 2：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 提示：
 *
 * 1 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LetterCombinations {

    @Test
    public void test() {
        System.out.println(letterCombinations2("2"));
        System.out.println(letterCombinations2("23"));
        System.out.println(letterCombinations2("234"));
    }

    static char[][] chars = new char[][]{
            {},{},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    public List<String> letterCombinations3(String digits) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[digits.length()];
        dfs(0, ans, digits, path);
        return ans;
    }

    private void dfs(int i, List<String> ans, String digits, char[] path) {
        if (i == digits.length()) {
            ans.add(new String(path));
            return;
        }
        for (char c: chars[digits.charAt(i) - '0']) {
            path[i] = c;
            dfs(i + 1, ans, digits, path);
        }
    }


    String[][] strs = new String[][]{
            {},{},
            {"a", "b", "c"},
            {"d", "e", "f"},
            {"g", "h", "i"},
            {"j", "k", "l"},
            {"m", "n", "o"},
            {"p", "q", "r", "s"},
            {"t", "u", "v"},
            {"w", "x", "y", "z"},
    };


    public List<String> letterCombinations2(String digits) {
        List<String> ans = new ArrayList<>();
        dfs("", digits, 0, ans);
        return ans;
    }

    private void dfs(String pre, String digits, int index, List<String> ans) {
        if (index >= digits.length()) {
            ans.add(pre);
            return;
        }
        for (String s: strs[digits.charAt(index) - '0']) {
            dfs(pre + s, digits, index+1, ans);
        }
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        ans.addLast("");
        for (char c: digits.toCharArray()) {
            int size = ans.size();
            while (size --> 0) {
                String pre = ans.pollFirst();
                for (String s: strs[c - '0']) {
                    ans.addLast(pre + s);
                }
            }
        }
        return ans;
    }
}
