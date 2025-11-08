package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 306. 累加数
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
 *
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 *
 * 说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 *
 *
 * 示例 1：
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * 示例 2：
 * 输入："199100199"
 * 输入："1 99 100 199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *
 *
 * 提示：
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 *
 * 进阶：你计划如何处理由过大的整数输入导致的溢出?
 */
public class IsAdditiveNumber {

    @Test
    public void test() {
        System.out.println(isAdditiveNumber("000"));
        System.out.println(isAdditiveNumber2("000"));

        System.out.println(isAdditiveNumber("011112"));
        System.out.println(isAdditiveNumber2("011112"));

        System.out.println(isAdditiveNumber("111122335588143"));
        System.out.println(isAdditiveNumber2("111122335588143"));
        // 9999999999999999999
        System.out.println(isAdditiveNumber2("999999999999999999999999"));
    }


    //TODO 看看别人怎么做的
    public boolean isAdditiveNumber2(String num) {
        int length = num.length();
        if (length < 3) {
            return false;
        }
        // 第一个数
        for (int i = 0, len1 = (int) (Math.ceil((double) length / 2) - 1); i < len1; i++) {
            int zz = 0;
            // 第二个数
            for (int j = i + 1; length - j - 1 >= Math.max(i + 1, j - i); j++) {
                long v1 = Long.parseLong(num.substring(0, i+1));
                long v2 = Long.parseLong(num.substring(i+1, j+1));
                int start = j + 1;
                do {
                    String v3 = (v1 + v2) + "";
                    String next = num.substring(start, Math.min(start + v3.length(), length));
                    if (!next.equals(v3)) {
                       break;
                    }
                    v1 = v2;
                    v2 = Long.parseLong(v3);
                    start += v3.length();
                }while(start < length);
                if (start == length) return true;
                if (num.charAt(i + 1) == '0') break;
            }
            if (num.charAt(0) == '0') break;
        }
        return false;
    }

    @Test
    public void testNormal() {
        String num = "111122335588143";
        int length = num.length();
        int i = 1;
        int j = 3;
        long v1 = Long.parseLong(num.substring(0, i+1));
        // 第二个数
            long v2 = Long.parseLong(num.substring(i+1, j+1));
            int start = j + 1;
            do {
                String v3 = (v1 + v2) + "";
                String next = num.substring(start, Math.min(start + v3.length(), length));
                if (!next.equals(v3)) {
                    break;
                }
                v1 = v2;
                v2 = Long.parseLong(v3);
                start += v3.length();
            }while(start < length);
            if (start == length) {
                System.out.println("success");
            }
    }



    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }
        for (int i = 3, len = num.length(); i <= len; i++) {
            if (check(num, i)) {
                return true;
            }
        }
        return false;
    }


    public boolean check(String str, int n) {
        BigDecimal[] path = new BigDecimal[n];
        return check(0, 0, str, n, path);
    }

    private boolean check(int i, int j, String str, int n, BigDecimal[] path) {

        if (n == 1) {
            if (str.charAt(i) == '0' && i != str.length() - 1) {
                return false;
            }
            path[path.length - n] = new BigDecimal(str.substring(i));
            for (int k = 2; k < path.length; k++) {
                if (!path[k-2].add(path[k-1]).equals(path[k])) {
                    return false;
                }
            }
            return true;
        }

        // 选
        path[path.length - n] = new BigDecimal(str.substring(i, j + 1));
        boolean ans = check(j + 1, j + 1, str, n - 1, path);

        // 不选
        if (n < str.length() - j && str.charAt(i) != '0') {
            ans |= check(i, j + 1, str, n, path);
        }
        return ans;
    }

    @Test
    public void testCheck() {
        System.out.println(check("112358", 6));
        System.out.println(check("112358", 5));
        System.out.println(check("112358", 4));
        System.out.println(check("112358", 3));
    }


    @Test
    public void testSplit() {
        System.out.println(split2("12045", 3));
        System.out.println(split2_1("12045", 3));
        System.out.println(split2("1204", 3));
        System.out.println(split2_1("1204", 3));
        System.out.println(split2("000", 3));
        System.out.println(split2_1("000", 3));
    }


    public List<String> split3(String str,  int n) {
        String[] path = new String[n];
        List<String> ans = new ArrayList<>();
        dfs3(0, 0, str, ans, n, path);
        return ans;
    }

    private void dfs3(int i, int j, String str, List<String> ans, int n, String[] path) {

        if (n == 1) {
            if (str.charAt(i) == '0' && i != str.length() - 1) {
                return;
            }
            path[path.length - n] = str.substring(i);
            ans.add(String.join(",", path));
            return;
        }

        // 选
        path[path.length - n] = str.substring(i, j + 1);
        dfs3(j + 1, j + 1, str, ans, n - 1, path);

        // 不选
        if (n < str.length() - j && str.charAt(i) != '0') {
            dfs3(i, j + 1, str, ans, n, path);
        }
    }



    // 枚举结束的位置
    public List<String> split2_1(String str,  int n) {
        String[] path = new String[n];
        List<String> ans = new ArrayList<>();
        dfs2_1(0, str, n, path, ans);
        return ans;
    }

    private void dfs2_1(int idx, String str, int n, String[] path, List<String> ans) {
        if (n == 0) {
            ans.add(String.join(",", path));
            return;
        }

        for (int i = idx, len = str.length() - n; i <= len; i++) {
            if (n > 1 || i == len) {
                path[path.length - n] = str.substring(idx, i + 1);
                dfs2_1(i + 1, str, n - 1, path, ans);
            }
        }
    }

    public List<String> split2(String str,  int n) {
        String[] path = new String[n];
        List<String> ans = new ArrayList<>();
        dfs2(0, str, n, path, ans);
        return ans;
    }

    private void dfs2(int idx, String str, int n, String[] path, List<String> ans) {
        if (n == 1) {
            path[path.length - n] = str.substring(idx);
            ans.add(String.join(",", path));
            return;
        }

        for (int i = idx, len = str.length() - n; i <= len; i++) {
            path[path.length - n] = str.substring(idx, i + 1);
            dfs2(i + 1, str, n - 1, path, ans);
        }
    }

    // 选或不选
    public List<String> split(String str,  int n) {
        String[] path = new String[n];
        List<String> ans = new ArrayList<>();
        dfs(0, 0, str, ans, n, path);
        return ans;
    }

    private void dfs(int i, int j, String str, List<String> ans, int n, String[] path) {

        if (n == 1) {
            path[path.length - n] = str.substring(i);
            ans.add(String.join(",", path));
            return;
        }
        // 选
        path[path.length - n] = str.substring(i, j + 1);
        dfs(j + 1, j + 1, str, ans, n - 1, path);

        // 不选
        if (n < str.length() - j) {
            dfs(i, j + 1, str, ans, n, path);
        }
    }
}
