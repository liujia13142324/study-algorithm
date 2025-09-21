package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2517. 礼盒的最大甜蜜度
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 *
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 *
 * 返回礼盒的 最大 甜蜜度。
 *
 * 示例 1：
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 *
 * 示例 2：
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 *
 * 示例 3：
 * 输入：price = [7,7,7,7], k = 2
 * 输出：0
 * 解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
 *
 *
 * 提示：
 * 2 <= k <= price.length <= 105
 * 1 <= price[i] <= 109
 */
public class MaximumTastiness {

    @Test
    public void test() {
        System.out.println(maximumTastiness(new int[]{13,5,1,8,21,2}, 3));
        System.out.println(maximumTastiness(new int[]{1,3,1}, 2));
        System.out.println(maximumTastiness(new int[]{7,7,7,7}, 2));
        System.out.println(maximumTastiness(new int[]{63,85,135,16,200,168,159,28}, 6));
        // 19
        System.out.println(maximumTastiness(new int[]{34,116,83,15,150,56,69,42,26}, 6));
        System.out.println(maximumTastiness(new int[]{173,11,34,64,28,84,92,129,85,17,150,152,49,191,92,169,37,58}, 18));
    }

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        if (k == 2) {return price[price.length - 1] - price[0];}
        int max = (price[price.length - 1] - price[0]) / (k - 1) + 1;
        int min = -1;

        while (min + 1 < max) {
            int mid = (min + max) >>> 1;
//            if (check(price, mid, k)) {
            if (getCnt(price, mid) >= k) {
                min = mid;
            }else {
                max = mid;
            }
        }
        return min;
    }


    // 这个更优
    private int getCnt(int[] price, int testVal) {
        int cnt = 1;
        int pre = price[0];
        for (int p: price) {
            if (p - pre >= testVal) {
                cnt++;
                pre = p;
            }
        }
        return cnt;
    }


    private boolean check(int[] price, int testVal, int k) {
        int l = 0;
        for (int i = 0; i < k - 1 && l < price.length; i++) {
            int target = price[l] + testVal;
            int r = price.length - 1;
            // l = r + 1 退出循环
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (price[mid] >= target) {
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }
        }
        return l < price.length;
    }
}
