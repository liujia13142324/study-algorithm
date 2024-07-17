package com.lj.datastructure.notline.tree;

import com.lj.datastructure.notline.tree.abstracts.NormalBinaryTree;
import com.lj.datastructure.notline.tree.enums.TraverseType;

import java.util.List;

/**
 * 链式存储的tree
 * @param <T>
 */
public class LinkedBinaryTree<T> extends NormalBinaryTree<TreeNode<T>> {
    
    TreeNode<T> root;
    
    public LinkedBinaryTree(){
        super();
        root = new TreeNode();
    }
    
    @Override
    public Object getNodeVal(TreeNode<T> node) {
        if(node == null){
            return null;
        }
        return node.value;
    }
    
    public LinkedBinaryTree(TreeNode<T> root) {
        super();
        this.root = root;
    }
    
    public LinkedBinaryTree(List<? extends TreeNode<T>> nodes) {
        super();
        this.root = TreeNode.parseTree(nodes);
    }
        
    
    public LinkedBinaryTree(T v){
        this();
        root.value = v;
    }
    
    @Override
    public TreeNode<T> getRoot() {
        return root;
    }
    
    
    @Override
    public TreeNode<T> getRightNode(TreeNode<T> node) {
        return node.right;
    }
    
    @Override
    public TreeNode<T> getLeftNode(TreeNode<T> node) {
        return node.left;
    }
    
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        LinkedBinaryTree<Integer> normalTree = new LinkedBinaryTree<>(3);
        TreeNode<Integer> node1 = new TreeNode<>(4);
        TreeNode<Integer> node2 = new TreeNode<>(8);
        TreeNode<Integer> node3 = new TreeNode<>(9);
        TreeNode<Integer> node4 = new TreeNode<>(7);
        normalTree.root.left = node1;
        normalTree.root.right = node4;
        node1.left = node2;
        node1.right = node3;
        
        normalTree.preOrder();
        System.out.println();
        normalTree.inOrder();
        System.out.println();
        normalTree.postOrder();
        System.out.println();
        System.out.println();
    
        normalTree = new LinkedBinaryTree(TreeNode.
                parseTree(new Integer[]{1,23,3,4,5,6,null,8,12,10}, TreeNode.class));
        
        normalTree.preOrderPrint();
        normalTree.traversePrint(TraverseType.PRE_ORDER);
        normalTree.inOrderPrint();
        normalTree.traversePrint(TraverseType.IN_ORDER);
        normalTree.postOrderPrint();
        normalTree.traversePrint(TraverseType.POST_ODER);
        System.out.println(normalTree.layer());
    
        System.out.println();
        normalTree.prettyShow(6);
    }
    
}
