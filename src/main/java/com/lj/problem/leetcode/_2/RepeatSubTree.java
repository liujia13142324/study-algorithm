package com.lj.problem.leetcode._2;

import com.test.common.bean.Pair;
import org.junit.Test;

import java.util.*;

/**
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * https://leetcode.cn/problems/find-duplicate-subtrees/comments/
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 *
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 *
 */
public class RepeatSubTree {
    
    /**
     * 错误的 demo
     *
     * [0,0,0,0,null,null,0,null,null,null,0]
     *
     *   0   和   0  这两颗树，无论前中后序遍历都是  00, 但是这是两个不同的树
     *  0          0
     *
     */
    
    @Test
    public void testRearOrder(){
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);*/
    
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right.right = new TreeNode(0);
        root.right.right.right = new TreeNode(0);
        
        
        Map<TreeNode,String> sub1 = new HashMap<>();
        Map<TreeNode,String> sub2 = new HashMap<>();
        rearTraverse(root.left,new StringBuilder(),sub1);
        rearTraverse(root.right,new StringBuilder(),sub2);
        System.out.println();
        System.out.println(sub1);
        System.out.println(sub2);
    
        List<TreeNode> result = new ArrayList<>();
    
        if(sub1.size()<sub2.size()){
            for(TreeNode node:sub1.keySet()){
                if(sub2.containsValue(sub1.get(node))){
                    result.add(node);
                }
            }
        }else{
            for(TreeNode node:sub2.keySet()){
                if(sub1.containsValue(sub2.get(node))){
                    result.add(node);
                }
            }
        }
    
        System.out.println(result);
    }
    
    /**
     * 后序遍历，有问题
     * @param node
     * @param preOrderStr
     * @param allSubTrees
     */
    public void rearTraverse(TreeNode node,StringBuilder preOrderStr,Map<TreeNode,String>allSubTrees){
        if(node == null){preOrderStr.append("N");return;}
        rearTraverse(node.left,preOrderStr,allSubTrees);
        
        rearTraverse(node.right,preOrderStr,allSubTrees);
        String tmp = preOrderStr.append(node.val).toString();
        if(!allSubTrees.containsValue(tmp)){
            allSubTrees.put(node,tmp);
        }
    }
    
    
    
    @Test
    public void testDfs(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);
        System.out.println(testDfs(root));
    }
    
    // 树递归其实就是深度遍历
    private String testDfs(TreeNode node){
        if(node == null) return "";
        String s = testDfs(node.left) +  node.val + testDfs(node.right);
        return s;
    }
    
    
    /**
     * 方法1
     */
    
   /* Map<String,Integer> map = new HashMap<>();
    List<TreeNode> list = new ArrayList<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        //字符串序列化
        dfs(root,(byte)0);
        return list;
    }
    //f表示左右子树
    private String dfs(TreeNode node, byte f){
        if(node == null) return ""+f;
        String s = dfs(node.left,(byte)0) +  node.val + dfs(node.right,(byte)1);
        if(map.getOrDefault(s,0) == 1) list.add(node);
        map.put(s,map.getOrDefault(s,0)+1);
        return s+f;
    }
    */
    
    
    /**
     * 方法2
     */
    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<>();
    int idx = 0;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }
    
    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String hash = Arrays.toString(tri);
        if (seen.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = seen.get(hash);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen.put(hash, new Pair<TreeNode, Integer>(node, ++idx));
            return idx;
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
    
        @Override
        public String toString() {
            return val+"";
        }
    }
}
