package com.lj.problem.leetcode._2;

import com.lj.study.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.*;

/**
 * 2583. 二叉树中的第 K 大层和
 * 给你一棵二叉树的根节点 root 和一个正整数 k 。
 * 树中的 层和 是指 同一层 上节点值的总和。
 * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
 *
 * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
 *
 * 示例 1：
 * 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
 * 输出：13
 * 解释：树中每一层的层和分别是：
 * - Level 1: 5
 * - Level 2: 8 + 9 = 17
 * - Level 3: 2 + 1 + 3 + 7 = 13
 * - Level 4: 4 + 6 = 10
 * 第 2 大的层和等于 13 。
 *
 * 示例 2：
 * 输入：root = [1,2,null,3], k = 1
 * 输出：3
 * 解释：最大的层和是 3 。
 *
 *
 * 提示：
 * 树中的节点数为 n
 * 2 <= n <= 105
 * 1 <= Node.val <= 106
 * 1 <= k <= n
 */
public class KthLargestLevelSum {

    public long kthLargestLevelSum3(TreeNode root, int k) {
        // 小顶堆
        PriorityQueue<Long> pq = new PriorityQueue<>(k);
        // BFS（广度优先）
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            long sum = 0L;
            for (int i = que.size(); i > 0; i--) {
                TreeNode cur = que.poll();
                sum += cur.val;
                if (cur.left != null) {
                    que.offer(cur.left);
                }
                if (cur.right != null) {
                    que.offer(cur.right);
                }
            }
            if (pq.size() < k || sum > pq.peek()) {
                if (pq.size() == k) {
                    pq.poll();
                }
                pq.offer(sum);
            }
        }
        return pq.size() == k ? pq.peek() : -1;
    }


    public long kthLargestLevelSum(TreeNode root, int k) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        List<Long> sums = new ArrayList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            while (size --> 0) {
                TreeNode n = queue.pollFirst();
                sum+=n.val;
                if (n.left != null) queue.addLast(n.left);
                if (n.right != null) queue.addLast(n.right);
            }
            sums.add(sum);
        }
        if (sums.size() < k) return -1;
        sums.sort(Long::compare);
        return sums.get(sums.size()-k);
    }

    /**
     * 速度非常慢
     * @param root
     * @param k
     * @return
     */
    public long kthLargestLevelSum2(TreeNode root, int k) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        long[] sums = new long[k];
        int idx = 0;
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            while (size --> 0) {
                TreeNode n = queue.pollFirst();
                sum+=n.val;
                if (n.left != null) queue.addLast(n.left);
                if (n.right != null) queue.addLast(n.right);
            }
            idx = add(sums, sum, idx);
        }
        if (idx < k) return -1;
        return sums[k-1];
    }

    // 倒序插入排序
    private int add(long[] sums, long sum, int idx) {
        int l = -1;
        int r = idx;
        while (l < r-1) {
            int mid = (l + r) >>> 1;
            if (sums[mid] <= sum) {
                r = mid;
            }else {
                l = mid;
            }
        }

        if (r >= sums.length) return idx;

        System.arraycopy(sums, r, sums, r+1, idx < sums.length ? idx++ - r : idx - r - 1);
        sums[r] = sum;
        return idx;
    }

    @Test
    public void testAdd() {
        int[] randomArray = MyArrayUtil.getRandomArray();
        System.out.println(Arrays.toString(randomArray));

        long[] sums = new long[3];
        int idx = 0;
        for (int i: randomArray) {
            idx = add(sums, i, idx);
            System.out.println(Arrays.toString(sums));
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
