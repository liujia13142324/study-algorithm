package com.lj.datastructure.notline.tree.abstracts;

import java.util.function.Consumer;

public interface HandleAble<T> {
    
    Consumer EMPTY_ACTION = o->{};
    
    default Consumer<T> getDefaultHandle(){
        return EMPTY_ACTION;
    }
    
}
