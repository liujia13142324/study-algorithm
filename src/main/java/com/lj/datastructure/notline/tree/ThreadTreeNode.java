package com.lj.datastructure.notline.tree;

import lombok.Data;

@Data
public class ThreadTreeNode<T> extends TreeNode<T> {
    int leftType;
    int rightType;
    
    public ThreadTreeNode(T value) {
        super(value);
    }
    
    public ThreadTreeNode() {
    }
}
