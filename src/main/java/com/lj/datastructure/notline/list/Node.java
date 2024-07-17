package com.lj.datastructure.notline.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gwr
 * @Classname Node
 *
 * @Date 2022/4/20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Node<T> {
    T v;
    Node<T> next;
    
    @Override
    public String toString() {
        return v+"";
    }
}
