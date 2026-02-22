package com.lj.problem.leetcode._3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1187. 使数组严格递增
 * 困难
 * 提示
 * 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
 * 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
 * 如果无法让 arr1 严格递增，请返回 -1。
 *
 * 示例 1：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * 输出：1
 * 解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。

 * 示例 2：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * 输出：2
 * 解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。

 * 示例 3：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * 输出：-1
 * 解释：无法使 arr1 严格递增。
 *
 * 提示：
 *
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 */
public class MakeArrayIncreasing {

    private static int MAX = 1000000001;

    public int makeArrayIncreasing_(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        Map<Integer, Integer>[] cache = new HashMap[arr1.length];
        Arrays.setAll(cache, e -> new HashMap<>());
        int ans = dfs_(arr1.length - 1, MAX, arr1, arr2, cache);
        return ans >= MAX ? -1 : ans;
    }

    private int dfs_(int i, int pre, int[] arr1, int[] arr2, Map<Integer, Integer>[] cache) {
        if (i < 0) {
            return 0;
        }
        if (cache[i].containsKey(pre)) {
            return cache[i].get(pre);
        }
        int val = MAX;
        // 可以不选, && arr1[i] >= i
        if (arr1[i] < pre) {
            val = dfs_(i - 1, arr1[i], arr1, arr2, cache);
        }
        // 从 arr2 选择 第一个小于 pre 的值进行替换
        int idx = lowerBound(arr2, pre) - 1;
        if (idx >= 0) {
            val = Math.min(val, dfs_(i - 1, arr2[idx], arr1, arr2, cache) + 1);
        }
        cache[i].put(pre, val);
        return val;
    }


    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int ans = dfs(arr1.length - 1, MAX, arr1, arr2);
        return ans >= MAX ? -1 : ans;
    }

    private int dfs(int i, int pre, int[] arr1, int[] arr2) {
        if (i < 0) {
            return 0;
        }
        int val = MAX;
        // 可以不选, && arr1[i] >= i
        if (arr1[i] < pre) {
            val = dfs(i - 1, arr1[i], arr1, arr2);
        }
        // 从 arr2 选择 第一个小于 pre 的值进行替换
        int idx = lowerBound(arr2, pre) - 1;
        if (idx >= 0) {
            val = Math.min(val, dfs(i - 1, arr2[idx], arr1, arr2) + 1);
        }
        return val;
    }

    private int lowerBound(int[] arr, int target) {
        int l = -1;
        int r = arr.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }
}
