package com.lj.problem.leetcode._2;

import java.util.List;

/**
 * lru cache 使用带两个头节点的双链表实现
 */
public class LruCache2 {

    int capacity;
    ListNode[] hash;
    int size;
    ListNode head;
    ListNode tail;
    
    public static class Entry{
        public int key;
        public int val;
        public Entry(int key, int val){
            this.key = key;
            this.val = val;
        }
    
        @Override
        public String toString() {
            return "key: " + key + "  val:" +val;
        }
    }
    
    static class ListNode{
        ListNode next;
        ListNode pre;
        Entry entry;
        
        public ListNode(){}
        
        public ListNode(Entry entry){
            this.entry = entry;
        }
    }
    
    public LruCache2(int capacity){
        this.capacity = capacity;
        head = new ListNode();
        tail = new ListNode();
        hash = new ListNode[10001];
        head.next = tail;
        tail.pre = head;
    }
    
    
    public void put(int key, int val) {
        ListNode res = hash[key];
        
        if (res == null) {
            res = new ListNode(new Entry(key, val));
            hash[key] = res;
            addLast(res);
            size++;
        }else {
            res.entry.val = val;
        }
        
        access(res);
        
        if (capacity < size) {
            ListNode removed = head.next;
            remove(removed);
            hash[removed.entry.key] = null;
            size--;
        }
    }
    
    public int get(int key) {
        ListNode res = hash[key];
        if (res == null) {
            return -1;
        }
        
        access(res);
        
        return res.entry.val;
    }
    
    public void access(ListNode node){
        remove(node);
        addLast(node);
    }
    
    public boolean isEmpty() {
        return head.next == tail;
    }
    
    public void remove(ListNode node) {
        if (isEmpty()) {
            return;
        }
        
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }
    
    public void addLast(ListNode node) {
        node.pre = tail.pre;
        node.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }
    
}
