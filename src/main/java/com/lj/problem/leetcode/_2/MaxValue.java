package com.lj.problem.leetcode._2;

import org.junit.Test;

/**
 * LCP 34. 二叉树染色
 * 中等
 * 小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，模型的每个结点有一个 val 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，求所有染成蓝色的结点价值总和最大是多少？
 *
 * 示例 1：
 * 输入：root = [5,2,3,4], k = 2
 * 输出：12
 * 解释：结点 5、3、4 染成蓝色，获得最大的价值 5+3+4=12image.png
 *
 * 示例 2：
 * 输入：root = [4,1,3,9,null,null,2], k = 2
 * 输出：16
 * 解释：结点 4、3、9 染成蓝色，获得最大的价值 4+3+9=16image.png
 *
 * 提示：
 *
 * 1 <= k <= 10
 * 1 <= val <= 10000
 * 1 <= 结点数量 <= 10000
 */
public class MaxValue {

    @Test
    public void test() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(9);
        root.right.left = new TreeNode(9);
        root.left.left.left = new TreeNode(9);
        root.left.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(8);
        System.out.println(maxValue(root, 2));

        root = new TreeNode(1);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(8);
        root.left.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(3);
        root.left.left.right.left = new TreeNode(7);
        root.left.left.right.right = new TreeNode(10);
        root.left.right.left.left = new TreeNode(5);
        root.left.right.left.right = new TreeNode(8);
        System.out.println(maxValue(root, 10));
    }

    class Pair<K,V> {
        K k;
        V v;
        Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public int maxValue(TreeNode root, int k) {
        Pair<int[], Integer> ans = dfs(root, k);
        return ans.v;
    }

    private Pair<int[], Integer> dfs(TreeNode node, int k) {
        if (node == null) {
            return new Pair<>(new int[k + 1], 0);
        }
        Pair<int[], Integer> l = dfs(node.left, k);
        Pair<int[], Integer> r = dfs(node.right, k);

        int[] ans = new int[k + 1];
        ans[0] =  l.v + r.v;
        int max = ans[0];
        for (int i = 0; i < k; i++) {
            int val = 0;
            for (int j = 0; j <= i; j++) {
                val = Math.max(val, l.k[j] + r.k[i - j]);
            }
            ans[i + 1] = val + node.val;
            max = Math.max(max, ans[i + 1]);
        }

        return new Pair<>(ans, max);
    }

    int nextId;
    public class TreeNode {
        int id;
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; id = ++ nextId;}
    }

}
