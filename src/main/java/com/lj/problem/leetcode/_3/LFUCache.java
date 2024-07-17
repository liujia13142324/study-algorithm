package com.lj.problem.leetcode._3;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * lfu 算法
 */
public class LFUCache {
    
    int capacity;
    int size;
    ListNode[] hash;
    
    // 二维链表，
    DoubleLinkTable[] fre;
    //
    int minFre = 1;
    ListNode EMPTY = new ListNode(-1,-1,-1);
    
    
    public LFUCache(int capacity){
        
        this.capacity = capacity;
        hash = new ListNode[100001];
        fre = new DoubleLinkTable[200000];

        Arrays.fill(hash, EMPTY);
    }
    
  
    
    public int get(int key)  {
        ListNode res = this.hash[key];
        if (res == EMPTY) {
            return -1;
        }
        access(res);
        return res.value;
    }
    
    public void put(int key, int value)  {
        ListNode curr = this.hash[key];
        if (curr == EMPTY) {
            curr = new ListNode(key, value, 0);
            this.hash[key] = curr;
            size++;
        }else {
            curr.value = value;
        }
    
        if (size > capacity) {
            hash[fre[minFre].head.key] = EMPTY;
            // 不用考虑 minFre 对应的链表空了怎么半，因为有一个 minFre 为 1 的新元素加入
            fre[minFre].removeHead();
            // 因为是新加入的节点才会导致 key 的剔除，新加入的节点的 fre 肯定为 1，在 access node 里面统一设置
//            minFre = 1;
            size--;
        }
        
        access(curr);
    }
    
    public void access(ListNode node) {
        //1、计数
        int originCount = node.count;
        node.count++;
        //2、计算 minFre
        minFre = Math.min(minFre, node.count);
        //3、修改链表
        //3.1 删除原来的链表的节点
        if (originCount >= 1) {
            fre[originCount].remove(node);
            // 如果链表空了，originCount 需要和当前的 minFre 做比较，如果相等，minFre++
            if (fre[originCount].isEmpty() && originCount == minFre) {
                minFre++;
            }
        }
        //3.2 添加到新的链表
        DoubleLinkTable linkTable = fre[node.count];
        if (linkTable == null) {
            linkTable = new DoubleLinkTable(node);
            fre[node.count] = linkTable;
        }else {
            linkTable.insertLast(node);
        }
    }
   

    
    // 只含头尾指针的双向链表
    public static class DoubleLinkTable{
        ListNode head;
        ListNode tail;
    
    
        DoubleLinkTable(){
        }
    
        DoubleLinkTable(ListNode head){
            this.head = tail = head;
        }
        
        public boolean isEmpty() {
            return head == null;
        }
    
        public void remove(ListNode node) {
            if (node != head) {
                node.pre.next = node.next;
            }else {
                head = head.next;
            }
            
            if (node != tail) {
                node.next.pre = node.pre;
            }else {
                tail = tail.pre;
            }
            
            node.next = null;
            node.pre = null;
        }
    
        public void removeHead() {
            remove(head);
        }
        
        public void insertLast(ListNode newNode) {
            insert(tail, newNode);
        }
    
        public void insertHead(ListNode newNode) {
            insert(null, newNode);
        }
    
        public void insert(ListNode pre, ListNode newNode) {
            if (head == null) {
                head = tail = newNode;
                return;
            }
            
            if (pre == null) {
                newNode.next = head;
                newNode.pre = pre;
                head.pre = newNode;
                head = newNode;
            } else {
                newNode.next = pre.next;
                newNode.pre = pre;
                pre.next = newNode;
                // 说明 pre 不是 tail
                if (newNode.next != null) {
                    newNode.next.pre = newNode;
                } else {
                    // 说明是 tail
                    tail = newNode;
                }
            }
        }
    }
    
    public static class ListNode{
        ListNode next;
        ListNode pre;
        int key;
        int value;
        int count;
        
        public ListNode(int key, int value, int count){
            this.key = key;
            this.value = value;
            this.count = count;
        }
    
        @Override
        public String toString() {
            return "key: " + key + ", value: " + value + ", count: " + count + ", pre: " + (pre!=null?pre.key:"null") + ", next: " + (next!=null?next.key:"null");
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
    
        /*DoubleLinkTable linkTable = new DoubleLinkTable();
        ListNode[] listNodes = new ListNode[10];
        for (int i = 0; i < 10; i++) {
            listNodes[i] = new ListNode(i,i,0);
            linkTable.insertLast(listNodes[i]);
        }
        linkTable.removeHead();
        linkTable.remove(listNodes[3]);
        linkTable.insertLast(listNodes[0]);
        linkTable.insert(listNodes[5], listNodes[3]);
        System.out.println();*/
    
    }
    
    
}
