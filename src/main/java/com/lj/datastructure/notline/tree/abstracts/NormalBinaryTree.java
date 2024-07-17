package com.lj.datastructure.notline.tree.abstracts;

import java.util.List;
import java.util.function.Consumer;

/**
 * 正常的二叉树，左右子树都是正常的孩子
 * @param <T>
 */
public abstract class NormalBinaryTree<T> extends AbstractBinaryTree<T> {
    
    
    public void preOrderPrint(){
        preOrder();
        System.out.println();
    }
    
    public void inOrderPrint(){
        inOrder();
        System.out.println();
    }
    
    public void postOrderPrint(){
        postOrder();
        System.out.println();
    }
    
    
    public void preOrder(){
        preOrder(getRoot(),getDefaultHandle());
    }
    
    protected void preOrder(T node, Consumer<T> handle){
        preOrderRecursive(node,handle);
    }
    
    protected void preOrderRecursive(T node, Consumer<T> handle) {
        if(node==null){
            return;
        }
        handle.accept(node);
        preOrderRecursive(getLeftNode(node),handle);
        preOrderRecursive(getRightNode(node),handle);
    }
    
    public void inOrder(){
        inOrder(getRoot(),getDefaultHandle());
    }
    
    protected void inOrder(T node, Consumer<T> handle){
        inOrderRecursive(node,handle);
    }
    
    protected void inOrderRecursive(T node, Consumer<T> handle) {
        if(node==null){
            return;
        }
        inOrderRecursive(getLeftNode(node),handle);
        handle.accept(node);
        inOrderRecursive(getRightNode(node),handle);
    }
    
    
    public void postOrder(){
        postOrder(getRoot(),getDefaultHandle());
    }
    
    protected void postOrder(T node, Consumer<T> handle){
        postOrderRecursive(node,handle);
    }
    
    protected void postOrderRecursive(T node, Consumer<T> handle) {
        if(node==null){
            return;
        }
        postOrderRecursive(getLeftNode(node),handle);
        postOrderRecursive(getRightNode(node),handle);
        handle.accept(node);
    }
}
