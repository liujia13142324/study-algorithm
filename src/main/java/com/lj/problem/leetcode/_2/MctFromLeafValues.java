package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Stack;

/**
 * 1130. 叶值的最小代价生成树
 * 中等
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 *
 * 示例 1：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。
 *
 * 示例 2：
 * 输入：arr = [4,11]
 * 输出：44
 *
 * 提示：
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 231 。
 */
public class MctFromLeafValues {

    @Test
    public void test() {
        System.out.println(mctFromLeafValues3(new int[]{6,15,5,2}));
//        System.out.println(mctFromLeafValues(new int[]{4, 11}));
//        System.out.println(mctFromLeafValues(new int[]{7, 12, 8, 10}));
    }

    /**
     * 单调栈
     * @param arr
     * @return
     */
    public int mctFromLeafValues3(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);

        int ans = 0;
        for (int num: arr) {
            while (num > stack.peek()) {
                ans += stack.pop() * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }

        while (stack.size() > 2) {
            ans += stack.pop() * stack.peek();
        }

        return ans;
    }

    public int mctFromLeafValues2(int[] arr) {
        int[][] helper = new int[arr.length][arr.length];
        int[][] cache = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            helper[i][i] = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                helper[i][j] = Math.max(helper[i][j-1], arr[j]);
            }
        }
        return dfs2(0, arr.length - 1, arr, helper, cache);
    }

    private int dfs2(int i, int j, int[] arr, int[][] helper, int[][] cache) {
        if (i == j) {
            return 0;
        }
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        if (i + 1 == j) {
            return cache[i][j] = arr[i] * arr[j];
        }
        cache[i][j] = Integer.MAX_VALUE / 2;
        for (int k = i; k < j; k++) {
            cache[i][j] = Math.min(cache[i][j], dfs2(i, k, arr, helper, cache) + dfs2(k + 1, j, arr, helper, cache) + helper[i][k] * helper[k + 1][j]);
        }
        return cache[i][j];
    }


    public int mctFromLeafValues(int[] arr) {
        return dfs(0, arr.length - 1, arr, new boolean[arr.length]);
    }

    private int dfs(int i, int j, int[] arr, boolean[] disabled) {
        int ans = Integer.MAX_VALUE / 2;
        for (int k = i; k < j; k++) {
            if (disabled[k]) continue;
            int tmp = k;
            while (k + 1 <= j && disabled[k + 1]) {
                k++;
            }
            if (k + 1 <= j) {
                int idx;
                if (arr[tmp] < arr[k + 1]) {
                    idx = tmp;
                } else {
                    idx = k + 1;
                }
                disabled[idx] = true;
                ans = Math.min(ans, dfs(i, j, arr, disabled) + arr[tmp] * arr[k+1]);
                disabled[idx] = false;
            }
        }
        return ans == Integer.MAX_VALUE / 2 ? 0 : ans;

    }
}
