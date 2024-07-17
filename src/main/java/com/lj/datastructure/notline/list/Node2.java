package com.lj.datastructure.notline.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node2<T> {
    T v;
    Node2 next;
    Node2 pre;
}
