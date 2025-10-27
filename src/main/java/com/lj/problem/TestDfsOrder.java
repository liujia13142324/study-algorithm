package com.lj.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestDfsOrder {
    static int newListCount1 = 0;
    static int newListCount2 = 0;

    public static void main(String[] args) {
        /**
         */
        String s = "aansbsnbbabcddcbabbsbs"; // 你可以改成更长，比如 "aaaaaaaaaa"
        long t1 = System.currentTimeMillis();
        List<List<String>> res1 = partition2_1(s);
        long t2 = System.currentTimeMillis();

        long t3 = System.currentTimeMillis();
        List<List<String>> res2 = partition3(s);
        long t4 = System.currentTimeMillis();

        System.out.println("DFS + 缓存：new ArrayList 次数 = " + newListCount1 + ", 时间 = " + (t2 - t1) + " ms");
        System.out.println("循环 DP：new ArrayList 次数 = " + newListCount2 + ", 时间 = " + (t4 - t3) + " ms");
        System.out.println("结果数量对比: " + res1.size() + " vs " + res2.size());
    }

    // 第一段：递归 + 缓存
    public static List<List<String>> partition2_1(String s) {
        char[] chars = s.toCharArray();
        return dfs(chars, chars.length - 1, new ArrayList[s.length()]);
    }

    private static List<List<String>> dfs(char[] chars, int r, List<List<String>>[] cache) {
        if (r < 0) {
            List<List<String>> ans = newArrayList1();
            ans.add(new ArrayList<>());
            return ans;
        }
        if (cache[r] != null) return cache[r];

        List<List<String>> ans = newArrayList1();
        cache[r] = ans;

        for (int i = r; i >= 0; i--) {
            if (isHuiWen(chars, i, r)) {
                String s = new String(Arrays.copyOfRange(chars, i, r + 1));
                for (List<String> partition : dfs(chars, i - 1, cache)) {
                    List<String> newList = newArrayList1(partition);
                    newList.add(s);
                    ans.add(newList);
                }
            }
        }
        return ans;
    }

    // 第二段：循环 DP
    public static List<List<String>> partition3(String s) {
        List<List<String>>[] dfs = new ArrayList[s.length() + 1];
        dfs[0] = newArrayList2();
        dfs[0].add(new ArrayList<>());
        char[] chars = s.toCharArray();

        for (int i = 1; i <= chars.length; i++) {
            dfs[i] = newArrayList2();
            for (int j = i - 1; j >= 0; j--) {
                if (isHuiWen(chars, j, i - 1)) {
                    String tmp = new String(Arrays.copyOfRange(chars, j, i));
                    for (List<String> list : dfs[j]) {
                        List<String> newList = newArrayList2(list);
                        newList.add(tmp);
                        dfs[i].add(newList);
                    }
                }
            }
        }
        return dfs[s.length()];
    }

    private static boolean isHuiWen(char[] chars, int l, int r) {
        while (l < r) if (chars[l++] != chars[r--]) return false;
        return true;
    }

    // 计数工具
    private static <T> List<T> newArrayList1() {
        newListCount1++;
        return new ArrayList<>();
    }
    private static <T> List<T> newArrayList1(Collection<? extends T> c) {
        newListCount1++;
        return new ArrayList<>(c);
    }
    private static <T> List<T> newArrayList2() {
        newListCount2++;
        return new ArrayList<>();
    }
    private static <T> List<T> newArrayList2(Collection<? extends T> c) {
        newListCount2++;
        return new ArrayList<>(c);
    }

}
