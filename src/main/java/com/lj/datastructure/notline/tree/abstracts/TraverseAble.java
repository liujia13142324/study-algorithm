package com.lj.datastructure.notline.tree.abstracts;

import com.lj.datastructure.notline.tree.enums.TraverseType;

import java.util.function.Consumer;

public interface TraverseAble<T> extends HandleAble<T> {
    
    default void traversePrint(TraverseType traverseType){
        traverse(traverseType);
        System.out.println();
    }
    default void traverse(TraverseType traverseType){
        traverse(traverseType,getDefaultHandle());
    }
    default void traverse(TraverseType traverseType, Consumer<T> handle){
        traverse(traverseType,getFirst(),handle);
    }

    void traverse(TraverseType traverseType, T node, Consumer<T> handle);
    
    T getFirst();
}
