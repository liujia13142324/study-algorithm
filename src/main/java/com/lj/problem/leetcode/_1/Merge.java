package com.lj.problem.leetcode._1;

import org.junit.Test;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 *
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 *
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 *
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 *
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 */
public class Merge {


    @Test
    public void test() {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{4,0,0,0,0,0};
        merge(nums1, 1, new int[]{1,2,3,5,6}, 5);
        System.out.println(Arrays.toString(nums1));
    }

    // TODO 有空看看别的怎么写的
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        int i = 0, j = 0, idx = m;
        // 先写 num1 的 m ~ m+n
        while (i < m && j < n && idx < len) {
            if (nums1[i] < nums2[j]) {
                nums1[idx++] = nums1[i++];
            }else {
                nums1[idx++] = nums2[j++];
            }
        }

        // 如果不事先判断这两种情况，后面合并的情况会更加复杂，
        // 比如要是 i == m 情况，合并中止，此时 nums1 的 idx 并不会走到最后（右边还存在间隙），合并成功的长度就不会是 n，而且在后面合并步骤的第0、1步后，把 nums2 的 0 ~ idx 并入 nums1 的 part2 ~ m 时，由于最右边还有间隙，所以这里的长度会超出m之后，导致错误的覆盖或者说越界
        // demo 可以参考用例 4,0,0,0,0,0
        if (j == n) {
            System.arraycopy(nums1, 0, nums1, n, m);
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }else if (i == m) {
            System.arraycopy(nums1, m, nums1, 0, idx - m);
            System.arraycopy(nums2, j, nums1, idx - m, n - j);
            return;
        }

        // 再写 num1 的 0 ~ i
        idx = 0;
        while (i < m && j < n && idx < i) {
            if (nums1[i] < nums2[j]) {
                nums1[idx++] = nums1[i++];
            }else {
                nums1[idx++] = nums2[j++];
            }
        }
        int part2 = idx;

        // 最后 num2 的 0 ~ i
        idx = 0;
        while (i < m && j < n && idx < j) {
            if (nums1[i] < nums2[j]) {
                nums2[idx++] = nums1[i++];
            }else {
                nums2[idx++] = nums2[j++];
            }
        }

        // 开始合并
        // 0. 把 nums2 的 j ~ n，接到 idx 后面
        System.arraycopy(nums2, j, nums2, idx, nums2.length - j);
        idx += nums2.length - j;
        // 1. 先把 nums2 的 0 ~ idx 并入 nums1 的 part2 ~ m
        System.arraycopy(nums2, 0, nums1, part2, idx);
        // 2. 把 m ~ m + n 移动到 nums2
        System.arraycopy(nums1, m, nums2, 0, n);
        // 3. 把 0 ~ m 移动到 n ~ n+m
        System.arraycopy(nums1, 0, nums1, n, m);
        // 4. 把 0 ~ n 移动到 0 ~ n
        System.arraycopy(nums2, 0, nums1, 0, n);
    }

}
