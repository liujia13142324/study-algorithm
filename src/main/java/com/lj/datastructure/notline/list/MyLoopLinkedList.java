package com.lj.datastructure.notline.list;

/**
 * @author gwr
 * @Classname MyLoopLinkedList
 * @Description 带头，循环列表，含头尾指针
 * @Date 2022/4/21
 */
public class MyLoopLinkedList<T> extends MyLinkedList<T> {

    Node<T> tail;
    int size;

    public MyLoopLinkedList(){
        super();
        tail = head;
    }

    @Override
    public boolean isLoop() {
        return true;
    }
    
    public Node<T> getTail() {
        return tail;
    }
    
    public Node<T> getHead(){
        return head;
    }
    
    @Override
    public Node<T> addLast(T val) {
    
        Node newNode = new Node();
        newNode.v = val;
        
        tail.next = newNode;
        newNode.next = head.next;
        tail = newNode;
        size++;
        return newNode;
    }

    @Override
    public Node<T> add(int index, T val) {

        Node h = head;
        int i=0;
        for(; i<index&&h!=tail ;i++){
            h = h.next;
        }
        if( i<index ){
            return null;
        }
        Node newNode = new Node(val, h.next);
        h.next = newNode;
        
        if(newNode.next == head.next){
            tail = newNode;
        }else if(newNode == head.next){
            tail.next = newNode;
        }
        size++;
        return newNode;
    }
    
    /**
     * 从start的下一个开始，删除值为val的节点
     * @param start
     * @param val
     * @return
     */
    public Node<T> delete(Node<T> start , T val){
        if(isEmpty()){
            return null;
        }
        if(size == 1){
            start = head;
        }
        // 如果开始是从头，会要多走一步进入第一个节点
        if(start == head){
            // 快速判断是否是第一个节点（不然要遍历一圈才能回到第一个节点）
            if(start.next.getV().equals(val)){
                return deleteNextNode(start);
            }
            start = start.next;
        }
        
        for(int i=0;i<size-1;i++,start = start.next){
            if(start.next.getV().equals(val)){
                return deleteNextNode(start);
            }
        }
        
        return null;
    }
    
    /**
     * 从头开始删除 v=val的节点
     * @param val
     * @return
     */
    @Override
    public Node<T> delete(T val) {
        if(isEmpty()){
            return null;
        }
       
        Node h = head;
        do{
            if(h.next.v.equals(val)){
                return deleteNextNode(h);
            }
            h = h.next;
        }while( h != tail);
        
        return null;
    }
    
    /**
     * 删除 h 节点的下一个节点
     * @param h
     * @return
     */
    public Node<T> deleteNextNode(Node h) {
        Node<T> deleted = h.next;
        h.next = h.next.next;
        // 有3种情况删除了首尾节点 1、删除最后一个尾节点 2、通过头节点删除第一个节点 3、通过尾节点删除第一个节点
        if(deleted == tail){
            tail = h;
        }else if(h == head){
            tail.next = h.next;
        }else if(h == tail){
            head.next = h.next;
        }
        
        size--;
        return deleted;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void show() {
        if(isEmpty()){
            System.out.println("链表为空");
            return;
        }
        Node h = head;
        do{
            h = h.next;
            System.out.print(h.v+",");
        }while(h != tail);
        System.out.println();
    }
    
    @Override
    public void reverseShow() {
        // TODO
        System.out.println("暂不支持...");
    }

    @Override
    public void reverse() {
        // TODO
        System.out.println("暂不支持...");
    }

    @Override
    public void reverse2() {
        // TODO
        System.out.println("暂不支持...");
    }

    @Override
    public void reverse3() {
        // TODO
        System.out.println("暂不支持...");
    }

    public static void main(String[] args) {
        MyLoopLinkedList<Integer> loopLinkedList = new MyLoopLinkedList();
        loopLinkedList.show();
        loopLinkedList.addLast(1);
        loopLinkedList.addLast(2);
        loopLinkedList.addLast(3);
        System.out.println(loopLinkedList.tail.next == loopLinkedList.head.next);
        loopLinkedList.addLast(4);
        loopLinkedList.addLast(5);
        loopLinkedList.add(0,0);
        loopLinkedList.show();
        loopLinkedList.add(1,111);
        loopLinkedList.add(2,222);
        System.out.println(loopLinkedList.add(11,1));
        System.out.println(loopLinkedList.tail.next == loopLinkedList.head.next);
        loopLinkedList.show();
        System.out.println(loopLinkedList.tail.next == loopLinkedList.head.next);
        System.out.println(loopLinkedList.delete(222));;
        loopLinkedList.show();
        System.out.println(loopLinkedList.delete(0));;
        System.out.println(loopLinkedList.delete(5));;
        loopLinkedList.show();
        System.out.println(loopLinkedList.tail.next == loopLinkedList.head.next);
        System.out.println(loopLinkedList.size);
    }
    
    public int getSize() {
        return size;
    }
}
