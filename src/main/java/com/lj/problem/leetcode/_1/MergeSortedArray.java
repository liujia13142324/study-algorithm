package com.lj.problem.leetcode._1;

/**
 * 合并两个有序数组
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 */
public class MergeSortedArray {
    
    // 逆向双指针
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int i = m + n - 1;
        m--;n--;
        while ( m >= 0 && n >= 0) {
            if (nums1[m] < nums2[n]) {
                nums1[i--] = nums2[n--];
            }else {
                nums1[i--] = nums1[m--];
            }
        }
        
        if (m < 0) {
            System.arraycopy(nums2, 0, nums1, 0, n + 1);
        }
       
    }
    
    
    /*public void merge(int[] nums1, int m, int[] nums2, int n) {

        *//*int idx2 = 0;
        int tmp;
    
        for (int idx1 = 0; idx1 < m; idx1++) {
            if (nums1[idx1] > nums2[idx2]) {
                tmp = nums1[idx1];
                nums1[idx1] = nums2[idx2];
                nums2[idx2] = tmp;
            }
        }*//*
        
        int[] tmp = new int[n + m];
        int idx1 = 0, idx2 = 0, i =0;
        
        while (idx1 < m && idx2 < n){
            if (nums1[idx1] < nums2[idx2]) {
                tmp[i++] = nums1[idx1++];
            }else {
                tmp[i++] = nums2[idx2++];
            }
        }
        
        if (idx1 < m - 1) {
            System.arraycopy(nums1, idx1, tmp, i, m - idx1);
        }else if (idx2 < n - 1) {
            System.arraycopy(nums2, idx2, tmp, i, n - idx2);
        }
        
        System.arraycopy(tmp, 0, nums1, 0, n+m);
    }*/
    
}
