package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 *
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 *      如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LRUCache {
    
    ListNode[] hash;
    int capacity;
    int size;
    ListNode head;
    ListNode tail;
    
    class ListNode{
        ListNode pre;
        ListNode next;
        int val;
        int key;
        
        ListNode(ListNode pre, ListNode next,int key, int val){
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.val = val;
        }
    }
    
    ListNode EMPTY = new ListNode(null, null, -1, -1);
    
    public LRUCache(int capacity){
        this.capacity = capacity;
        hash = new ListNode[10001];
        Arrays.fill(hash, EMPTY);
    }
    
    public void put(int key, int value){
    
        
        ListNode old;
        if ((old = hash[key]) == EMPTY) {
            ListNode newNode = new ListNode(tail, null, key, value);
            hash[key] = newNode;
            addLast(newNode);
            size++;
        } else {
            old.val = value;
            access(old);
        }
    
        if (size > capacity) {
            hash[head.key] = EMPTY;
            remove(head);
            size--;
        }
    
    
    }
    
    private void access(ListNode old) {
        
        if (old != tail) {
            remove(old);
            addLast(old);
        }
    }
    
    public int get(int key){
        ListNode node = hash[key];
        if (node != EMPTY) {
            access(node);
        }
        return node.val;
    }
    
    public void remove(ListNode old) {
        if (old != tail) {
            // 修改后一个节点的前驱
            old.next.pre = old.pre;
        }
        if (old != head) {
            // 修改前一个节点的后继
            old.pre.next = old.next;
        }
        
        if (old == head) {
            head = head.next;
        }
        
        if (old == tail) {
            tail = tail.pre;
        }
        
        old.pre = null;
        old.next = null;
    }
    
    public void addLast(ListNode node) {
        if (head == null) {
            head = tail = node;
            return;
        }
        
        tail.next = node;
        node.pre = tail;
        tail = node;
    }
    
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
        System.out.println(lruCache.get(2));
        lruCache.put(3,2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }

}
