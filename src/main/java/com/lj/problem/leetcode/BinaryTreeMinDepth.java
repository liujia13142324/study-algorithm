package com.lj.problem.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 给定一个二叉树，找出其最小深度。
 
 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 
 说明: 叶子节点是指没有子节点的节点。
 
 示例:
 
 给定二叉树 [3,9,20,null,null,15,7],
 
 3
 / \
 9  20
 /  \
 15   7
 返回它的最小深度  2.
 
 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 
 
 思路：采用层序遍历的思路去处理这颗树，可以直接使用一个集合去进行层序遍历。
 1.把root放入集合
 2.开始循环，知道集合为空
  2.1 判断当前节点，如果为叶子节点直接返回当前深度，如果不是则继续下一步
  2.2 把当前节点的左右节点放入集合，过滤空的情况
 3.退出循环，返回结果
 
 
 
 */
public class BinaryTreeMinDepth {
   public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  public int minDepth(TreeNode root) {
    Queue<TreeNode> queue= new ArrayDeque<>();
    if(root==null){
      return 0;
    }
    queue.add(root);
    List<TreeNode> nodes = new ArrayList<>();
    int depth = 0;
    do{
      int i=0;
      nodes.clear();;
      while( !queue.isEmpty() ){
        nodes.add(  queue.poll() );
      }
      for( i=0 ;i<nodes.size() ; i++ ){
        if(nodes.get(i)==null) continue;
        if(nodes.get(i).left == null && nodes.get(i).right == null){
          return depth+1;
        }
        if(nodes.get(i).left!=null){
          queue.add( nodes.get(i).left );
        }
        if( nodes.get(i).right !=null ){
          queue.add( nodes.get(i).right );
        }
      }
      depth++;
    }while(!queue.isEmpty());
    
    return depth;
  }
  
  
  
  int getDepth(int cap) {
    int depth = 0;
    while(cap>0){
      cap = cap>>>1;
      depth++;
    }
    return depth;
  }
  
  @Test
  public void test(){
    /*System.out.println(getDepth(0));
    System.out.println(getDepth(1));
    System.out.println(getDepth(2));
    System.out.println(getDepth(3));
    System.out.println(getDepth(4));
    System.out.println(getDepth(5));
    System.out.println(getDepth(6));
    System.out.println(getDepth(7));
    System.out.println(getDepth(10));*/
    System.out.println((int)Math.pow( 2,1 ));
   }
  
 
  

}
