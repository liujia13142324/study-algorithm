package com.lj.datastructure.notline.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author gwr
 * @Classname DoubleLinkedList
 * @Description 带头双向链表
 * @Date 2022/4/20
 */
public class DoubleLinkedList<T> {

    Node2 head;
    
    public DoubleLinkedList(){
        head = new Node2();
    }
    
    public boolean isEmpty(){
        return head.next == null;
    }
    
    public void addLast(T t){
        Node2 h = head;
        while(h.next!=null){
            h = h.next;
        }
        if(h == head){
            h.next = new Node2(t,null,null);
        }else{
            h.next = new Node2(t,null,h);
        }
    }
    
    public boolean add(int index , T t){
        Node2 h = head;
        for(int i=0 ; i<index ; i++){
            if(h == null){
                return false;
            }
            h = h.next;
        }
        Node2 newNode2 = new Node2();
        newNode2.v = t;
        newNode2.next = h.next;
        h.next = newNode2;
        newNode2.pre = h;
        if(newNode2.next != null){
            newNode2.next.pre = newNode2;
        }
        return true;
    }
    
    public boolean delete(T v){
        Node2 h = head;
        while((h=h.next)!=null){
            if(h.v.equals(v)){
                h.pre.next = h.next;
                h.next.pre = h.pre;
                return true;
            }
        }
        return false;
    }
    public void show(){
        if(isEmpty()){
            System.out.println("链表为空！");
        }
        Node2 h = head;
        while((h=h.next)!=null){
            System.out.print(h.v+",");
        }
        System.out.println();
    }
    public void reverseShow(){
        if(isEmpty()){
            System.out.println("链表为空！");
        }
        Node2 h = head;
        reverseShow(h.next);
        System.out.println();
    }
    
    private void reverseShow(Node2 h) {
        if(h.next != null){
            reverseShow(h.next);
        }
        System.out.print(h.v+",");
    }
    
    /**
     * 反转链表，遍历交换每个节点的前驱和后记
     */
    private void reverse(){
        
        if(isEmpty() || head.next.next == null){
            return;
        }
        
        Node2 h = head.next;
        Node2 tmp;
        while(h.next!=null){
            // 交换每个节点的前驱和后继
            tmp = h.next;
            h.next = h.pre;
            h.pre = tmp;
            h = tmp;
        }
        // 处理最后一个节点
        h.next = h.pre;
        h.pre = null;
        head.next = h;
    }
    
    /**
     * 反转2，头插法
     */
    public void reverse2(){
        if(isEmpty() || head.next.next == null){
            return;
        }
        
        Node2 reverseHead = head;
        // 直接从第二个节点处理
        Node2 current = reverseHead.next.next;
        Node2 next ;
        
        while(current != null){
            next = current.next;
            current.next = reverseHead.next;
            current.next.pre = current;
            reverseHead.next = current;
            current = next;
        }
        
        head.next = reverseHead.next;
    }
    
    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.show();
        doubleLinkedList.addLast(1);
        doubleLinkedList.addLast(2);
        doubleLinkedList.addLast(3);
        doubleLinkedList.addLast(4);
        doubleLinkedList.show();
        doubleLinkedList.reverseShow();
        doubleLinkedList.delete(2);
        doubleLinkedList.show();
        doubleLinkedList.add(1,111);
        doubleLinkedList.show();
        doubleLinkedList.delete(111);
        doubleLinkedList.show();
        doubleLinkedList.reverse();
        doubleLinkedList.show();
        doubleLinkedList.reverse();
        doubleLinkedList.show();
    }
    
    
   
    
    
}
