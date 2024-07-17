package com.lj.datastructure.notline.tree;

import lombok.Data;

@Data
public class PostOrderThreadTreeNode<T> extends ThreadTreeNode<T> {
    
    TreeNode<T> parent;
    
    public PostOrderThreadTreeNode(T value) {
        super(value);
    }
    
    public PostOrderThreadTreeNode() {
    }
}
