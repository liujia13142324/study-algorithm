package com.lj.problem.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 验证二叉搜索树
 */
public class CheckIsBST {
  
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  
  static class Solution {
  
    public boolean helper(TreeNode node, Integer lower, Integer upper) {
      if (node == null) return true;
    
      int val = node.val;
      if (lower != null && val <= lower) return false;
      if (upper != null && val >= upper) return false;
    
      if (! helper(node.right, val, upper)) return false;
      if (! helper(node.left, lower, val)) return false;
      return true;
    }
  
    /**
     * 递归思路
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
      return helper(root, null, null);
    }
  
  
    /*
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public boolean isValidBST2(TreeNode root) {
      Stack<TreeNode> stack = new Stack();
      double inorder = - Double.MAX_VALUE;
    
      while (!stack.isEmpty() || root != null) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
        if (root.val <= inorder) return false;
        inorder = root.val;
        root = root.right;
      }
      return true;
    }
  
    
    public boolean isValidBST(TreeNode root) {
      List<Integer> valList = new ArrayList();
      middleTravel(root,valList);
      for(int i=1; i<valList.size() ; i++){
        if( valList.get(i) <=valList.get(i-1) ){
          return false;
        }
      }
      return true;
    }
  
    // 中序遍历输出
    public void middleTravel(TreeNode root, List valList) {
      if(root == null){
        return;
      }
      middleTravel(root.left , valList);
      valList.add( root.val );
      middleTravel(root.right , valList);
    }
  
  
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(15);
    root.right.left = new TreeNode(11);
    root.right.right = new TreeNode(20);
    root.right.right.left = new TreeNode(16);
    root.right.right.right = new TreeNode(21);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(8);
    root.left.left.left = new TreeNode(2);
    root.left.left.right = new TreeNode(4);
    boolean r = s.isValidBST(root);
    System.out.println(r);
  }

}
