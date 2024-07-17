package com.lj.problem.leetcode;

import org.junit.Test;

/**
 * 检测是不是平衡二叉树：树的任意节点的深度不差值不超过1
 *
 * 思路：从根节点开始，判断当前左右子树的深度之差不超过1，然后递归处理左右子树
 *
 */

public class CheckBalanceTree {
  
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  
  @Test
  public void test1(){
    TreeNode root = new TreeNode(1);
    root.left  = new TreeNode(1);
    root.right  = new TreeNode(1);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(1);
    System.out.println(isBalanced(root));
  }
  
  
  public boolean isBalanced(TreeNode root) {
    if( root==null ){
      return true;
    }
    int d = getDepth(root.left );
    int d2 = getDepth(root.right );
    return Math.abs(d-d2)<=1 && isBalanced(root.left) && isBalanced(root.right);
  }
  
  /**
   *  获取以 node 为根的树的深度
   */
  
  public int getDepth( TreeNode node ){
    if( node==null ){
      return 0;
    }
    return 1+Math.max( getDepth(node.left) , getDepth(node.right) );
  }
  
}
