package com.lj.datastructure.notline.tree;

import java.util.List;

public class AVLBinarySearchTree<T> extends BinarySearchTree<T> {
    
    
    AVLBinarySearchTree(List<TreeNode<Comparable>> nodes) {
        super(nodes);
    }
    
    @Override
    public void add(TreeNode<Comparable> n) {
        super.add(n);
        // 判断并且旋转 -> 只对根旋转
        int hLeft = layer(root.left);
        int hRight = layer(root.right);
        if(hLeft - hRight > 1){
            // 左子树的高度比右子树的高度大于1，开始右旋
            rightRotate(root);
        }else if(hRight - hLeft > 1){
            leftRotate(root);
        }
    }
    
    private void leftRotate(TreeNode<Comparable> node) {
        TreeNode<Comparable> right = node.getRight();
        if(right == null ){
            return;
        }
        // 还需要判断是否需要双旋转来保证
        if(layer(right.getLeft()) > layer(right.getRight())){
            // 右旋转降低右节点的左子树高度
            rightRotate(right);
        }
        
        // 开始左旋转
        TreeNode newNode = new TreeNode(node.value);
        newNode.left = node.left;
        newNode.right = right.left;
        node.value = right.value;
        node.right = right.right;
        node.left = newNode;
       
      /* node.right = right.left;
       right.left = node;
       root = right;*/
       
    }
    
    private void rightRotate(TreeNode<Comparable> node) {
        TreeNode<Comparable> left = node.getLeft();
        if(left == null){
            return;
        }
        
        //同样需要判断是否需要双旋转
        if( layer(left.getRight()) > layer(left.getLeft()) ){
            // 左旋转降低左节点的右子树的高度
            leftRotate(left);
        }
        
        // 开始旋转
        TreeNode<Comparable> newNode = new TreeNode<>(node.value);
        newNode.right = node.right;
        newNode.left = left.right;
        node.value = left.value;
        node.left = left.left;
        node.right = newNode;
    }
    
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("左旋转测试=========>");
        test(new Integer[]{4, 3, 6, 5, 7, 8});
        System.out.println("右旋转测试=========>");
        test(new Integer[]{10,12, 8, 9, 7, 6});
        System.out.println("左-右旋转=========>");
        test(new Integer[]{10, 11, 7, 6, 8, 9});
        System.out.println("右-左旋转=========>");
        test(new Integer[]{2,1,6,5,7,3});
    }
    
    private static void test(Integer[] nodeValues) throws InstantiationException, IllegalAccessException {
        System.out.println("普通二叉：");
        BinarySearchTree normal1 = new BinarySearchTree(TreeNode.parse(nodeValues,TreeNode.class));
        normal1.prettyShow(1);
    
        System.out.println("平衡二叉：");
        AVLBinarySearchTree avl1 = new AVLBinarySearchTree(TreeNode.parse(nodeValues,TreeNode.class));
        avl1.prettyShow(1);
    }
    
}
