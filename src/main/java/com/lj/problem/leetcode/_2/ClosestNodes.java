package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2476. 二叉搜索树最近节点查询
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 *
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 返回数组 answer 。
 *
 *
 * 示例 1 ：
 * 输入：root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * 输出：[[2,2],[4,6],[15,-1]]
 * 解释：按下面的描述找出并返回查询的答案：
 * - 树中小于等于 2 的最大值是 2 ，且大于等于 2 的最小值也是 2 。所以第一个查询的答案是 [2,2] 。
 * - 树中小于等于 5 的最大值是 4 ，且大于等于 5 的最小值是 6 。所以第二个查询的答案是 [4,6] 。
 * - 树中小于等于 16 的最大值是 15 ，且大于等于 16 的最小值不存在。所以第三个查询的答案是 [15,-1] 。

 * 示例 2 ：
 * 输入：root = [4,null,9], queries = [3]
 * 输出：[[-1,4]]
 * 解释：树中不存在小于等于 3 的最大值，且大于等于 3 的最小值是 4 。所以查询的答案是 [-1,4] 。
 *
 *
 * 提示：
 * 树中节点的数目在范围 [2, 105] 内
 * 1 <= Node.val <= 106
 * n == queries.length
 * 1 <= n <= 105
 * 1 <= queries[i] <= 106
 */
public class ClosestNodes {

    @Test
    public void test() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(14);

        System.out.println(closestNodes(root, Arrays.asList(2,5,16)));
        System.out.println(closestNodes2(root, Arrays.asList(2,5,16)));
    }

    public List<List<Integer>> closestNodes4(TreeNode root, List<Integer> queries) {
        List<Integer> nums = new ArrayList<>();
        midOrder(root, nums);

        int[] array = new int[nums.size()];
        for (int i = 0;i < array.length;i++) {
            array[i] = nums.get(i);
        }

        List<List<Integer>> ans = new ArrayList<>(queries.size());
        int r = nums.size();
        for (int query: queries) {
            int idx = lowerBound2(array, query);
            if (idx == array.length) {
                ans.add(Arrays.asList(nums.get(r - 1), -1));
            }else if (nums.get(idx) == query) {
                ans.add(Arrays.asList(nums.get(idx), nums.get(idx)));
            }else if (idx == 0){
                ans.add(Arrays.asList(-1, nums.get(idx)));
            }else {
                ans.add(Arrays.asList(nums.get(idx - 1), nums.get(idx)));
            }
        }
        return ans;
    }

    private int lowerBound2(int[] nums, int target) {
        int start = -1;
        int end = nums.length;
        while (start < end - 1) {
            int mid = (start + end) >>> 1;
            if (nums[mid] >= target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        return end;
    }

    public List<List<Integer>> closestNodes2(TreeNode root, List<Integer> queries) {
        List<Integer> nums = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        midOrder(root, nums);
        int l = -1;
        int r = nums.size();
        for (int query: queries) {
            int idx = lowerBound(nums, query, l, r);
            if (idx == r) {
                ans.add(Arrays.asList(nums.get(r - 1), -1));
            }else if (nums.get(idx) == query) {
                ans.add(Arrays.asList(nums.get(idx), nums.get(idx)));
            }else if (idx == 0){
                ans.add(Arrays.asList(-1, nums.get(idx)));
            }else {
                ans.add(Arrays.asList(nums.get(idx - 1), nums.get(idx)));
            }
        }
        return ans;
    }

    private int lowerBound(List<Integer> nums, int target, int start, int end) {
        while (start < end - 1) {
            int mid = (start + end) >>> 1;
            if (nums.get(mid) >= target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        return end;
    }

    private void midOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        midOrder(root.left, nums);
        nums.add(root.val);
        midOrder(root.right, nums);
    }


    // 超出时间限制, 但是竟然是对的，比下面强点
    int[][] map = new int[1000000][2];
    int pre;
    public List<List<Integer>> closestNodes3(TreeNode root, List<Integer> queries) {
        Integer[] arr = queries.toArray(new Integer[0]);
        pre = -1;
        Arrays.sort(arr);
        int idx = dfs(root, arr, 0);
        while (idx < arr.length) {
            map[arr[idx++]] = new int[]{pre, -1};
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Integer query : queries) {
            result.add(Arrays.asList(map[query][0], map[query][1]));
        }
        return result;
    }

    private int dfs(TreeNode root, Integer[] arr, int idx) {
        if (root == null || idx == arr.length) return idx;
        idx = dfs(root.left, arr, idx);
        while (arr[idx] <= root.val) {
            if (arr[idx] == root.val) {
                map[arr[idx++]] = new int[]{root.val, root.val};
            }else {
                map[arr[idx++]] = new int[]{pre, root.val};
            }
        }
        pre = root.val;
        return dfs(root.right, arr, idx);
    }

    // 超出时间限制
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>(queries.size());
        for (int num: queries) {
            ans.add(Arrays.asList(findMax(root, num, -1), findMin(root, num, -1)));
        }
        return ans;
    }

    public int findMax(TreeNode root, int num, int pre){
        if (root == null) return pre;
        if (root.val > num) {
            return findMax(root.left, num, pre);
        }else if (root.val == num){
            return num;
        }else {
            return findMax(root.right, num, root.val);
        }
    }

    public int findMin(TreeNode root, int num, int pre){
        if (root == null) return pre;
        if (root.val < num) {
            return findMin(root.right, num, pre);
        }else if (root.val == num){
            return num;
        }else {
            return findMin(root.left, num, root.val);
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
