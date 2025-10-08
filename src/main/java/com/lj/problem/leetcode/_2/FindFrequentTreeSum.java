package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 *
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 *
 *
 * 提示:
 * 节点数在 [1, 104] 范围内
 * -105 <= Node.val <= 105
 */
public class FindFrequentTreeSum {

    @Test
    public void test() {
        Map<Integer, Integer> countMap = new HashMap<>();
        System.out.println( countMap.compute(123, (i1, i2) -> i2 == null ? 1 : i2+1));
        System.out.println( countMap.compute(123, (i1, i2) -> i2 == null ? 1 : i2+1));
        System.out.println( countMap.compute(123, (i1, i2) -> i2 == null ? 1 : i2+1));
    }

    // TODO 看看别人怎么做的
    int maxCnt = 0;
    Map<Integer, Integer> countMap = new HashMap();
    int[] ans = new int[10000];
    int idx = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        if (idx == ans.length - 1) return ans;
        return Arrays.copyOf(ans, idx);
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int val = dfs(root.left) + dfs(root.right) + root.val;
        int cnt = countMap.compute(val, (k, v)-> v == null ? 1 : v + 1);
        if (cnt == maxCnt) {
            ans[idx++] = val;
        }else if (cnt > maxCnt) {
            idx = 0;
            ans[idx++] = val;
            maxCnt = cnt;
        }
        return val;
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
