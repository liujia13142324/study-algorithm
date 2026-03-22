package com.lj.problem.leetcode._3;

import org.junit.Test;

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
 *
 */
public class MakeArrayIncreasing {

    @Test
    public void test() {
//        System.out.println(makeArrayIncreasing4(new int[]{4,5,6,7,8}, new int[]{9,10}));
//        System.out.println(makeArrayIncreasing4(new int[]{1,5,3,6,7}, new int[]{4,3,1}));
        System.out.println(makeArrayIncreasing4(new int[]{1,5,3,6,7}, new int[]{1,3,2,4}));
        System.out.println(makeArrayIncreasing4(new int[]{1,5,3,6,7}, new int[]{4,3,1}));
        System.out.println(makeArrayIncreasing4(new int[]{1,2,3,4}, new int[]{0,0,0,0}));

//        System.out.println(Arrays.toString(test2(new int[]{1,2,3,3,3,4,5,5,6,7,7})));
    }

    public int[] test2(int[] arr2) {
        int r = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[r - 1] != arr2[i]) {
                arr2[r++] = arr2[i];
            }
        }
        System.out.println(r);
        return arr2;
    }


    public int makeArrayIncreasing4(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int r = 0;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[r] != arr2[i]) {
                arr2[++r] = arr2[i];
            }
        }
        int[] cache = new int[arr1.length + 1];
        int max = dfs4(arr1.length, r + 1, arr1, arr2, cache);

        return max < 0 ? -1 : arr1.length - max + 1;
    }

    /**
     * arr1 中前 i-1 最大保留数 (第 i 位设为保留数 -> 成功1，失败且可以替换0，失败且无法替换 -无穷)
     * j+1 ~ i-1  -> i - j - 1
     * k = lowBound(b, a[i])
     * j = i - 2
     * a[j] < b[k - (i - j - 1)] -> 替换ok -> dfs(j)
     * (j >= i - k - 1)
     *
     * @param i
     * @param arr1
     * @param arr2
     * @param cache
     * @return
     */
    private int dfs4(int i, int len1, int[] arr1, int[] arr2, int[] cache) {
        if (cache[i] != 0) return cache[i];
        int x = i == arr1.length ? Integer.MAX_VALUE : arr1[i];
        int idx = lowerBound(arr2, len1, x);
        int ans = idx >= i ? 0 : Integer.MIN_VALUE;
        if (i > 0 && arr1[i - 1] < x) {
            ans = Integer.max(ans, dfs4(i - 1, len1, arr1, arr2, cache));
        }

        for (int j = i - 2; j >= 0 && j >= i - idx - 1; j--) {
            if (arr1[j] < arr2[idx - (i - j - 1)]) {
                ans = Integer.max(ans, dfs4(j, len1, arr1, arr2, cache));
            }
        }
        cache[i] = ans + 1;
        return cache[i];
    }












    private int[] a, b, memo;
    private int m;

    public int makeArrayIncreasing2(int[] a, int[] b) {
        this.a = a;
        this.b = b;
        Arrays.sort(b);
        for (int i = 1; i < b.length; ++i)
            if (b[m] != b[i])
                b[++m] = b[i]; // 原地去重
        ++m;
        int n = a.length;
        memo = new int[n + 1]; // 0 表示还没有计算过
        int ans = dfs(n);
        return ans < 0 ? -1 : n + 1 - ans;
    }

    private int dfs(int i) {
        if (memo[i] != 0) return memo[i]; // 之前计算过了
        int x = i < a.length ? a[i] : Integer.MAX_VALUE;
        int k = lowerBound(b, m, x);
        int res = k < i ? Integer.MIN_VALUE : 0; // 小于 a[i] 的数全部替换
        if (i > 0 && a[i - 1] < x) // 无替换
            res = Math.max(res, dfs(i - 1));
        for (int j = i - 2; j >= i - k - 1 && j >= 0; --j)
            if (b[k - (i - j - 1)] > a[j])
                // a[j+1] 到 a[i-1] 替换成 b[k-(i-j-1)] 到 b[k-1]
                res = Math.max(res, dfs(j));
        return memo[i] = ++res; // 把 +1 移到这里，表示 a[i] 不替换
    }

    private int lowerBound(int[] nums, int right, int target) {
        int left = -1; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (nums[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right;
    }



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

    public int makeArrayIncreasing3(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int ans = dfs3(arr1.length - 1, Integer.MAX_VALUE, arr1, arr2, new HashMap());
        return ans >= 100000 ? -1 : ans;
    }


    private int dfs3(int i, int pre, int[] arr1, int[] arr2, Map<String, Integer> cache) {
        if (i < 0) {
            return 0;
        }
        if (cache.get(i + "_" + pre) != null) {
            return cache.get(i + "_" + pre);
        }
        int ans = 100000;
        if (arr1[i] < pre) {
            ans = dfs3(i - 1, arr1[i], arr1, arr2, cache);
        }
        int j = lowerBound(arr2, pre) - 1;
        if (j >= 0) {
            ans = Math.min(dfs3(i - 1, arr2[j], arr1, arr2, cache) + 1, ans);
        }

        cache.put(i + "_" + pre, ans);
        return ans;
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
        // 从 arr2 选择 第一个小于 pre 的值进行替换，为什么取第一个？ -> 第二个满足，第一个肯定也满足
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
