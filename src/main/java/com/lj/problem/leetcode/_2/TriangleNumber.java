package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 *
 * 示例 2:
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class TriangleNumber {

    @Test
    public void test() {
        System.out.println(triangleNumber(new int[]{2,2,3,4}));
        System.out.println(triangleNumber(new int[]{4,2,3,4}));
        // 3 19 22 24 35 82 84
        //
        System.out.println(triangleNumber(new int[]{24,3,82,22,35,84,19}));

        System.out.println(triangleNumber2(new int[]{2,2,3,4}));
        System.out.println(triangleNumber2(new int[]{4,2,3,4}));
        System.out.println(triangleNumber2(new int[]{24,3,82,22,35,84,19}));
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i > 1; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    ans += r - l;
                    r--;
                }else {
                    l++;
                }
            }
        }
        return ans;
    }

    /**
     * i < l < r
     * num[i] <= num[l] <= num[r]
     * num[i] + num[r] > num[l] 恒成立
     * num[l] + num[r] > num[i] 恒成立
     * num[i] + num[l] > num[r]
     * num[l] - num[i] < num[r] 恒成立
     * num[r] - num[i] < num[l]
     * num[r] - num[l] < num[i]
     *
     * 结论：
     * num[r] < num[i] + num[l]
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int l = i + 1; l < nums.length - 1; l++) {
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[r] >= nums[i] + nums[l]) {
                        r--;
                    }else {
                        ans += r - l;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
