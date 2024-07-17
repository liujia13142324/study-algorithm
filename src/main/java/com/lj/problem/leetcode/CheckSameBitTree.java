package com.lj.problem.leetcode;

import org.junit.Test;

public class CheckSameBitTree {
  
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
  
  @Test
  public void test(){
  
  }
  
  //判断一颗二叉树是否相等
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if( p==null && q==null ){
      return true;
    }
    if( p==null || q==null ){
      return false;
    }
    if( p.val!=q.val ){
      return false;
    }
    boolean r1 = isSameTree(p.left,q.left);
    boolean r2 = isSameTree(p.right,q.right);
    return r1&&r2;
  }
  
  
}
