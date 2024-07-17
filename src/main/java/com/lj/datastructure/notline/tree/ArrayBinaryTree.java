package com.lj.datastructure.notline.tree;

import com.lj.datastructure.notline.tree.abstracts.NormalBinaryTree;
import com.lj.datastructure.notline.tree.abstracts.NormalBinaryTree;

/**
 * 顺序存储的二叉树 ===> 一般针对完全二叉
 */
public class ArrayBinaryTree<T> extends NormalBinaryTree<Integer> {

    T[] nodes;
    int len;
    
    public ArrayBinaryTree(T[] nodes) {
        this.nodes = nodes;
        if(!isEmpty(nodes)){
            this.len = nodes.length;
        }
    }
    
    @Override
    public Object getNodeVal(Integer i) {
        if(i>=nodes.length || i<0){
            return null;
        }
        return nodes[i];
    }
    
    @Override
    public boolean isEmpty() {
        return isEmpty(nodes);
    }
    
    private boolean isEmpty(T[] nodes) {
        return nodes == null || nodes.length == 0;
    }
    
    private boolean isOver(int idx){
        if(isEmpty()){
            return true;
        }
        return idx>=len || idx<0;
    }
    
    @Override
    public Integer getRoot() {
        Integer root = 0;
        if(isEmpty()){
            root = null;
        }
        return root;
    }
    
    @Override
    public Integer getRightNode(Integer nodeIndex) {
        if(isOver(nodeIndex)){return null;}
        int result = nodeIndex * 2 + 2;
        if(isOver(result)){return null;}
        return nodeIndex*2+2;
    }
    
    @Override
    public Integer getLeftNode(Integer nodeIndex) {
        if(isOver(nodeIndex)){return null;}
        int result = nodeIndex * 2 + 1;
        if(isOver(result)){return null;}
        return result;
    }
    
    
    public static void main(String[] args) {
        ArrayBinaryTree arrayTree = new ArrayBinaryTree(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        arrayTree.preOrderPrint();
        arrayTree.inOrderPrint();
        arrayTree.postOrderPrint();
    }
    
    
}
