package com.lj.datastructure.notline.tree;

import com.lj.datastructure.notline.tree.abstracts.AbstractBinaryTree;
import com.lj.datastructure.notline.tree.enums.TraverseType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 线索二叉搜索树，只有一种遍历方式
 * 左子数->前驱
 * 右子树->后继
 */
public class ThreadBinaryTree<T> extends AbstractBinaryTree<ThreadTreeNode <T>> {
    
    private ThreadBinaryTree(){}
    
    private ThreadTreeNode<T> root ;
    
    private TraverseType traverseType;
    
    @Override
    public Object getNodeVal(ThreadTreeNode<T> node) {
        if(node == null){
            return null;
        }
        return node.value;
    }
    
    public void traversePrint(){
        traverse();
        System.out.println();
    }
    
    public void traverse(){
        traverse(root);
    }
    
    public void traverse(ThreadTreeNode node){
        traverse(node, getDefaultHandle());
    }
    
    public void traverse(ThreadTreeNode node , Consumer<ThreadTreeNode<T>> handle){
        traverse(traverseType,node,handle);
    }
    
    /**
     * 得用递归 或者 扩展父节点
     * @param node
     * @param handle
     */
    protected void postOrder(ThreadTreeNode<T> node, Consumer<ThreadTreeNode<T>> handle) {
        if (traverseType != TraverseType.POST_ODER) {
            throw new IllegalArgumentException("不支持的遍历方式：postOrder ==> 支持的遍历方式为：" + traverseType);
        }
    
        if (node instanceof PostOrderThreadTreeNode) {
            postOrder2((PostOrderThreadTreeNode) node, handle);
        }else {
            postOrderRecursive(node, handle);
        }
    }
    
    /**
     * 遍历下一个节点的时候，处理上一个节点
     * @param node
     * @param handle
     */
    private void postOrder2(PostOrderThreadTreeNode node, Consumer<ThreadTreeNode<T>> handle) {
        
        PostOrderThreadTreeNode pre = null;
        while(node != null) {
    
            while ((node.left != pre && node.leftType == 0)) {
                node = (PostOrderThreadTreeNode) node.left;
            }
    
            
            while (node != null && node.rightType == 1) {
                // 遍历下一个节点的时候，处理上一个节点
                handle.accept(node);
                pre = node;
                node = (PostOrderThreadTreeNode) node.right;
            }
            
            if (node == root) {
                // 遍历下一个节点的时候，处理上一个节点
                handle.accept(node);
                return;
            }
            
            while (node != null && node.right == pre) {
                // 遍历下一个节点的时候，处理上一个节点
                handle.accept(node);
                pre = node;
                node = (PostOrderThreadTreeNode) node.parent;
            }
            
            if (node != null && node.rightType == 0) {
                node = (PostOrderThreadTreeNode) node.right;
            }
            
        }
    }
    
    private void postOrderRecursive(ThreadTreeNode<T> node, Consumer<ThreadTreeNode<T>> handle) {
        if (node.leftType == 0) {
            postOrderRecursive((ThreadTreeNode<T>) node.left, handle);
        }
    
        if (node.rightType == 0) {
            postOrderRecursive((ThreadTreeNode<T>) node.right, handle);
        }
    
        handle.accept(node);
    }
    
    protected void inOrder(ThreadTreeNode<T> node, Consumer<ThreadTreeNode<T>> handle) {
        
        if (traverseType != TraverseType.IN_ORDER) {
            throw new IllegalArgumentException("不支持的遍历方式：inOrder ==> 支持的遍历方式为：" + traverseType);
        }
        
        while(node != null){
            
            while(node.leftType == 0){
                node = (ThreadTreeNode) node.left;
            }
            
            handle.accept(node);
            
            while( node.rightType == 1 ){
                node = (ThreadTreeNode) node.right;
                handle.accept(node);
            }
            
            node = (ThreadTreeNode) node.right;
        }
    }
    
    protected void preOrder(ThreadTreeNode<T> node, Consumer<ThreadTreeNode<T>> handle) {
    
        
        if (traverseType != TraverseType.PRE_ORDER) {
            throw new IllegalArgumentException("不支持的遍历方式：preOrder ==> 支持的遍历方式为：" + traverseType);
        }
        
        while( node != null){
            
            handle.accept(node);
            
            while(node.leftType == 0){
                node = (ThreadTreeNode) node.left;
                handle.accept(node);
            }
            
            while(node.rightType == 1){
                node = (ThreadTreeNode) node.right;
                handle.accept(node);
            }
            
            if(node.leftType == 0){
                node = (ThreadTreeNode) node.left;
            }else {
                node = (ThreadTreeNode) node.right;
            }
        }
    }
    
    @Override
    public ThreadTreeNode<T> getRoot() {
        return root;
    }
    
    @Override
    public ThreadTreeNode<T> getRightNode(ThreadTreeNode<T> node) {
        return (ThreadTreeNode<T>) node.left;
    }
    
    @Override
    public ThreadTreeNode<T> getLeftNode(ThreadTreeNode<T> node) {
        return (ThreadTreeNode<T>) node.right;
    }
    
    
    // 先遍历，后创建
    static class TraverseBuilder<T>{
        
        public ThreadBinaryTree<T> build(List<ThreadTreeNode> nodes, TraverseType traverseType) {
            ThreadBinaryTree<T> result = new ThreadBinaryTree<>();
            LinkedBinaryTree tree = new LinkedBinaryTree(nodes);
            result.traverseType = traverseType;
            if(nodes.size()>0){
                result.root = nodes.get(0);
                List<ThreadTreeNode> sequence = new ArrayList<>(nodes.size());
                tree.traverse(traverseType, (n)->sequence.add((ThreadTreeNode) n));
                for(int i=0 ; i<sequence.size(); i++){
                    ThreadTreeNode node = sequence.get(i);
                    if(node.left != null){
                        node.leftType = 0;
                    }
                    if(node.right != null){
                        node.rightType = 0;
                    }
                    // 为了遍历需要，第一个节点的前驱为null，也要leftType设置为1，代表是指向前驱
                    if(node.left == null){
                        node.leftType = 1;
                        if(i-1>=0){
                            node.left = sequence.get(i-1);
                        }
                    }
                    // 还是为了遍历需要，最后一个节点标识为 0 ，代表指向孩子
                    if(node.right == null && i+1 <sequence.size()){
                        node.rightType = 1;
                        node.right = sequence.get(i+1);
                    }
                }
            }
            return result;
        }
    }
    
    // 递归遍历的时候就创建
    static class RecursiveBuilder<T>{
    
        ThreadTreeNode pre;
    
        public ThreadBinaryTree<T> build(List<ThreadTreeNode> nodes, TraverseType traverseType) {
            ThreadBinaryTree<T> result = new ThreadBinaryTree();
            ThreadTreeNode<T> root = TreeNode.parseTree(nodes);
            if(root != null){
                result.root = root;
                result.traverseType = traverseType;
                recursiveBuild(result.root,traverseType);
            }
            return result;
        }
    
        public ThreadBinaryTree<T> buildPostOrderThreadTree(List<PostOrderThreadTreeNode> nodes) {
            ThreadBinaryTree<T> result = new ThreadBinaryTree();
            PostOrderThreadTreeNode<T> root = TreeNode.parseTree(nodes);
            if(root != null){
                result.root = root;
                result.traverseType = TraverseType.POST_ODER;
                recursiveBuild(result.root,result.traverseType);
            }
            return result;
        }
    
        private void recursiveBuild(ThreadTreeNode<T> root, TraverseType traverseType) {
            pre = null;
            switch (traverseType){
                case IN_ORDER:
                    inOrderBuild(root);
                    break;
                case PRE_ORDER:
                    preOrderBuild(root);
                    break;
                    
                case POST_ODER:
                    if (root instanceof PostOrderThreadTreeNode) {
                        postOrderBuild((PostOrderThreadTreeNode) root, null);
                    }else {
                        postOrderBuild(root);
                    }
                    break;
            }
        }
    
        private void postOrderBuild(PostOrderThreadTreeNode<T> node, PostOrderThreadTreeNode<T> parent) {
            if(node == null){
                return;
            }
            postOrderBuild((PostOrderThreadTreeNode<T>) node.left, node);
            postOrderBuild((PostOrderThreadTreeNode<T>) node.right, node);
            handleThisLeftPreRight(node, pre);
            pre = node;
            if (node.parent == null) {
                node.parent = parent;
            }
        }
        
        private void postOrderBuild(ThreadTreeNode<T> node) {
            if(node == null){
                return;
            }
            postOrderBuild((ThreadTreeNode<T>) node.left);
            postOrderBuild((ThreadTreeNode<T>) node.right);
            handleThisLeftPreRight(node, pre);
            pre = node;
        }
        
        
        private void inOrderBuild(ThreadTreeNode<T> node) {
            if(node == null){
                return;
            }
            inOrderBuild((ThreadTreeNode<T>) node.left);
            handleThisLeftPreRight(node, pre);
            pre = node;
            // 这里不用判断，上一步中节点处理后.0只会生成左节点后继，不影响中节点递归右子树
            inOrderBuild((ThreadTreeNode<T>) node.right);
        }
    
    
        private void preOrderBuild(ThreadTreeNode<T> node) {
            if(node == null ){
                return;
            }
            handleThisLeftPreRight(node, pre);
            pre = node;
            // 这个判断，主要是看上一步后，中节点是否会产生新的前驱引用（left），产生前驱引用在遍历左孩子之前，就会死递归
            if(node.leftType == 0){
                preOrderBuild((ThreadTreeNode<T>) node.left);
            }
            /**
             * 这个和上面类似，如果上一步递归完左子树后，中节点产生新的后继引用（right），如果这时候，中节点还没开始递归右
             * 右子树，这个时候，就会出现死递归
             */
            if(node.rightType == 0){
                preOrderBuild((ThreadTreeNode<T>) node.right);
            }
        }
    
        /**
         * 处理当前节点的前驱，前驱节点的后继
         * @param node
         * @param pre
         */
        private void handleThisLeftPreRight(ThreadTreeNode<T> node, ThreadTreeNode pre) {
            if(node.left == null){
                node.left = pre;
                node.leftType = 1;
            }
            if(pre != null && pre.right == null){
                pre.right = node;
                pre.rightType = 1;
            }
        }
    
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Integer vals[] = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        
        // 前序线索树 by TraverseBuilder
        ThreadBinaryTree treadBinaryTree = new TraverseBuilder()
                .build(TreeNode.parse(vals, ThreadTreeNode.class), TraverseType.PRE_ORDER);
        ThreadBinaryTree treadBinaryTree2 = new RecursiveBuilder<>()
                .build(TreeNode.parse(vals, ThreadTreeNode.class), TraverseType.PRE_ORDER);
        LinkedBinaryTree normalTree = new LinkedBinaryTree(TreeNode.parseTree(vals,TreeNode.class));
    
//        treadBinaryTree.inOrder(treadBinaryTree.root, treadBinaryTree.getDefaultHandle());
        treadBinaryTree.traversePrint();
        treadBinaryTree2.traversePrint();
        normalTree.preOrderPrint();
        System.out.println();
        
        // 中序线索树 by RecursiveBuilder
        treadBinaryTree = new RecursiveBuilder<>()
                .build(TreeNode.parse(vals,ThreadTreeNode.class),TraverseType.IN_ORDER);
        treadBinaryTree2 = new RecursiveBuilder<>()
                .build(TreeNode.parse(vals, ThreadTreeNode.class), TraverseType.IN_ORDER);
        treadBinaryTree.traversePrint();
        treadBinaryTree2.traversePrint();
        normalTree.inOrderPrint();
        System.out.println();
    
    
        // 后序线索树 by RecursiveBuilder
        treadBinaryTree = new RecursiveBuilder<>()
                .build(TreeNode.parse(vals,ThreadTreeNode.class),TraverseType.POST_ODER);
        treadBinaryTree.traversePrint();
    
        
        // 后序线索树2，包含父亲节点
        treadBinaryTree = new RecursiveBuilder<>()
                .buildPostOrderThreadTree(TreeNode.parse(vals,PostOrderThreadTreeNode.class));
        treadBinaryTree.traversePrint();
        
        normalTree.postOrder();
        System.out.println();
    
    }
}
