package com.lj.datastructure.notline.tree;

import com.lj.datastructure.notline.tree.abstracts.NormalBinaryTree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class BinarySearchTree<T> extends NormalBinaryTree<TreeNode<Comparable>> {

    TreeNode<Comparable> root;
    
    BinarySearchTree(List<TreeNode<Comparable>> nodes){
        for(TreeNode<Comparable> n : nodes){
            this.add(n);
        }
    
//        nodes.sort(Comparator.comparing(TreeNode::getValue));
        // 二分搜索方式构建
//        initRecursive(nodes, 0, nodes.size()-1 );
    }
    
    private void initRecursive(List<TreeNode<Comparable>> nodes, int l, int r) {

        if (l > r) {
            return;
        }
        
        int mid = (l+r)/2;
        add(nodes.get(mid));
        initRecursive(nodes, l, mid-1);
        initRecursive(nodes, mid+1, r);
    }
    
    
    public TreeNode<Comparable> search(Comparable value){
        TreeNode<Comparable> node = root;
        while(node != null){
            int compare = node.getValue().compareTo(value);
            if(compare == 0){
                return node;
            }else if(compare > 0){
                node = node.getLeft();
            }else {
                node = node.getRight();
            }
        }
        return null;
    }
    
    public void add(Comparable v) {
        add(new TreeNode<>(v));
    }
    
    public void add(TreeNode<Comparable> n) {
        if(root == null){
            root = n;
            return;
        }
        
        add(root,n);
    }
    
    /*private void add(TreeNode<Comparable> node, TreeNode<Comparable> target) {
        if(target == null || node == null){
            return;
        }
        int compareResult = node.getValue().compareTo(target.getValue());
        if(compareResult>0){
            if(node.getLeft() == null){
                node.setLeft(target);
            }else{
                add(node.getLeft(),target);
            }
        }else{
            if(node.getRight() == null){
                node.setRight(target);
            }else{
                add(node.getRight(),target);
            }
        }
    }*/
    
    // 非递归方式
    private void add(TreeNode<Comparable> node, TreeNode<Comparable> target) {
        
        while(node != null) {
            
            int compareResult = node.getValue().compareTo(target.getValue());
    
            if(compareResult>0){
                if(node.getLeft() == null){
                    node.setLeft(target);
                    break;
                }else{
                    node = node.getLeft();
                }
            }else{
                if(node.getRight() == null){
                    node.setRight(target);
                    break;
                }else{
                    node = node.getRight();
                }
            }
        }
    }
    
    public TreeNode<Comparable> del(Comparable<T> v) {
        return del(new TreeNode<>(v));
    }
    
    public TreeNode<Comparable> del(TreeNode<Comparable> node){
        if(node == null){
            return null;
        }
        return del(root,null,node);
    }
    
    // 中序递归删除节点
    private TreeNode<Comparable> del(TreeNode<Comparable> node, TreeNode<Comparable> parent, TreeNode<Comparable> target) {
        if(node == null || target == null){
            return null;
        }
        int compareResult = node.getValue().compareTo(target.getValue());
        // 即要删除当前节点
        TreeNode<Comparable> result;
        if(compareResult == 0){
            // 具体删除逻辑
            result = executeDel(node,parent);
        }else if(compareResult > 0){
            // 当前节点的值要比目标节点大，去当前节点左子树递归
            result = del(node.getLeft(),node,target);
        }else{
            result = del(node.getRight(),node,target);
        }
        return result;
    }
    
    private TreeNode<Comparable> executeDel(TreeNode<Comparable> node, TreeNode<Comparable> parent) {
        if(parent == null){
            // 说明是根节点
            // 叶子节点
            if(node.isLeaf()){
                root = null;
                // 左孩子
            }else if(node.getLeft() != null && node.getRight() == null){
                root = node.getLeft();
                // 右孩子
            }else if(node.getLeft() == null && node.getRight() != null){
                root = node.getRight();
                // 两个子树
            }else{
                // 左子树接任
                TreeNode left = node.getLeft();
                TreeNode right = node.getRight();
                // 左子树的右孩子插入到node右子树中
                add(node.getRight(),left.getRight());
                // 设置左节点的新右孩子
                
                left.setRight(right);
                // 左节点升级为新的root
                root = left;
            }
        }else{
            
            // 叶子节点
            if(node.isLeaf()){
                If.of(()->parent.getLeft() == node)
                        .then(()-> parent.setLeft(null))
                        .orElse(()-> parent.setRight(null))
                        .execute();
            // 左孩子
            }else if(node.getLeft() != null && node.getRight() == null){
                If.of(()->parent.getLeft() == node)
                        .then(()-> parent.setLeft(node.getLeft()))
                        .orElse(()-> parent.setRight(node.getLeft()))
                        .execute();
                // 右孩子
            }else if(node.getLeft() == null && node.getRight() != null){
                If.of(()->parent.getLeft() == node)
                        .then(()-> parent.setLeft(node.getRight()))
                        .orElse(()-> parent.setRight(node.getRight()))
                        .execute();
                // 两个子树
            }else{
                // 左节点接任
                TreeNode left = node.getLeft();
                TreeNode right = node.getRight();
                // 左节点的右孩子插入到node右子树中
                add(node.getRight(),left.getRight());
                // 设置左节点的新右孩子
                left.setRight(right);
                // 左节点挂到parent下面
                If.of(()->parent.getLeft() == node)
                        .then(()-> parent.setLeft(left))
                        .orElse(()-> parent.setRight(left))
                        .execute();
            }
        }
        
        return node;
    }
    
    public void preOrderWithParent(){
        preOrderWithParent(root,null);
    }
    
    private void preOrderWithParent(TreeNode<Comparable> node, TreeNode<Comparable> parent) {
        if(node == null){
            return;
        }
        System.out.println("node->"+node.getValue()+" parent->"+(parent == null ? "null" : parent.getValue()));
        preOrderWithParent(node.getLeft(),node);
        preOrderWithParent(node.getRight(),node);
    }
    
    @Override
    public TreeNode<Comparable> getRoot() {
        return root;
    }
    
    @Override
    public TreeNode<Comparable> getRightNode(TreeNode<Comparable> node) {
        return node.getRight();
    }
    
    @Override
    public TreeNode<Comparable> getLeftNode(TreeNode<Comparable> node) {
        return node.getLeft();
    }
    
    @Override
    public Object getNodeVal(TreeNode<Comparable> tTreeNode) {
        return tTreeNode.getValue();
    }
    
    
    static class If{
        Supplier<Boolean> expression;
        Runnable then;
        Runnable orElse;
        public static If of(Supplier<Boolean> expression){
            If i = new If();
            i.expression = expression;
            return i;
        }
        public If then(Runnable then){
            this.then = then;
            return this;
        }
        public If orElse(Runnable orElse){
            this.orElse = orElse;
            return this;
        }
        public void execute(){
            if(expression.get()){
                if(then != null){
                    then.run();
                }
            }else{
                if(orElse != null){
                    orElse.run();
                }
            }
        }
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Integer[] arr = {1, 9, 7, 3, 10, 12, 5};
//        Arrays.sort(arr);
        List<TreeNode> nodes = TreeNode.parse(
//                new Integer[]{7,3,10,6,5,9,12,2} , TreeNode.class
            arr, TreeNode.class
        );
        BinarySearchTree binarySearchTree = new BinarySearchTree(nodes);
        binarySearchTree.prettyShow(1);
    
        TreeNode search = binarySearchTree.search(3);
        System.out.println("search(3):"+search);
        search = binarySearchTree.search(6);
        System.out.println("search(6):"+search);
    
        System.out.print("前序遍历：");
        binarySearchTree.preOrderPrint();
    
        // 删除叶子节点
        System.out.println("删除叶子节点 12：");
        binarySearchTree.del(12);
        binarySearchTree.prettyShow(1);
        // 删除单孩子节点
        System.out.println("删除单孩子节点 10：");
        binarySearchTree.del(10);
        binarySearchTree.prettyShow(1);
        // 删除双孩子节点
        System.out.println("删除双孩子节点 3：");
        binarySearchTree.del(3);
        binarySearchTree.prettyShow(1);
        // 删除根节点
        System.out.println("删除根节点：");
        binarySearchTree.del(7);
        binarySearchTree.prettyShow(1);
    }
    
 
}
