package com.lj.problem.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 有序链表转平衡二叉
 */
public class SortedList2BalanceTree {
  
  @Test
  public void test() {
  
  }
  
  public TreeNode sortedListToBST(ListNode head) {
    List<Integer> list = list2Arr( head );
    TreeNode root = new TreeNode();
    return generateBST( root , 0 , list.size()-1 , list);
  }
  //[-10,-3,0,5,9]
  private TreeNode generateBST(TreeNode root, int startPos, int endPos, List<Integer> list) {
    if( startPos==endPos ){
      return new TreeNode(list.get(endPos));
    }
    if(startPos>endPos){
      return null;
    }
    int mid = (startPos+endPos+1)/2;
    root.val = list.get( mid );
    root.left = generateBST( new TreeNode() , startPos , mid-1 , list );
    root.right = generateBST( new TreeNode() , mid+1 , endPos , list );
    return root;
  }
  
  private List<Integer>list2Arr (ListNode head) {
    List<Integer> list = new ArrayList<>();
    while( head!=null ){
      list.add( head.val );
      head = head.next;
    }
    return list;
  }
  
  
  public class ListNode {
    int val;
    ListNode next;
    
    ListNode() {
    }
    
    ListNode(int val) {
      this.val = val;
    }
    
    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
  
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {
    }
    
    TreeNode(int val) {
      this.val = val;
    }
    
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
  
  
}
