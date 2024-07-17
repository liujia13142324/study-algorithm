package com.lj.datastructure.notline.list;

public class MyLinkedList<T> {
    
    Node head;
    
    public MyLinkedList() {
        head = new Node();
    }

    public boolean isEmpty(){
        return head.next == whatAfterLast();
    }
    
    public Node<T> addLast(T val){
        Node newNode = new Node();
        newNode.v = val;
        Node h = head;
        while(h.next != whatAfterLast()){
            h = h.next;
        }
        h.next = newNode;
        return newNode;
    }

    public Node<T> add(int index,T val){
        Node h = head;
        for(int i=0;i<index&&h!=whatAfterLast();i++){
            h = h.next;
        }
        if(h == null){
            return null;
        }
        Node newNode = new Node(val, h.next);
        h.next = newNode;
        return newNode;
    }

    public Node<T> delete(T val){
        Node h = head;
        Node<T> deleted = null;
        while(h.next != whatAfterLast()){
            if(h.next.v == val){
                deleted = h.next;
                h.next = h.next.next;
                break;
            }
            h = h.next;
        }
        return deleted;
    }
    
    public void show(){
        if(isEmpty()){
            System.out.println("链表为空...");
            return;
        }
        Node h = head;
        String str = "";
        while(h.next != whatAfterLast()){
            str += h.next.v+",";
            h = h.next;
        }
        System.out.println(str);
    }
    
    public void reverseShow(){
        if(isEmpty()){
            System.out.println("链表为空...");
            return;
        }
        reverseShow(head);
        System.out.println();
    }
    
    private void reverseShow(Node head) {
        Node n = head.next;
        if(n != whatAfterLast()){
            reverseShow(n);
            System.out.print(n.v+",");
        }
    }

    
    /**
     * 没有优化循环
     * 以当前节点为参照系
     */
    public void reverse(){
        
        if(head.next == null || head.next.next == null){
            return;
        }
        
        Node reverseHead = head;
        Node current=reverseHead.next;
        Node next;
        while(current!= null){
            // next先移动,不然无法再移动
            next = current.next;
            // 如果是第一个节点，next置空
            if(current == reverseHead.next){
                current.next = null;
            }else{
                current.next = reverseHead.next ;
            }
            reverseHead.next = current;
            // 当前指针移动
            current = next;
        }
        head.next = reverseHead.next;
    }
    
    /**
     * 优化循环里面的判断
     * 以当前节点为参照系
     */
    public void reverse2(){
        
        if(head.next == null || head.next.next == null){
            return;
        }
        Node reverseHead = head;
        // 获取第一个节点
        Node current=reverseHead.next;
        // 将r头的next置空，方便循环里面的逻辑，即第一个元素的next指向空
        reverseHead.next = null;
        Node next;
        while(current!= null){
            // next先移动
            next = current.next;
            current.next = reverseHead.next ;
            reverseHead.next = current;
            // 当前指针移动
            current = next;
        }
        head.next = reverseHead.next;
    }
    
    
    /**
     * 优化循环里面的判断
     * 有一种以前驱为参照系的感觉
     */
    public void reverse3(){
        
        if(head.next == null || head.next.next == null){
            return;
        }
        
        Node reverseHead = head;
        Node next=reverseHead.next;
        reverseHead.next = null;
        Node current;
        while(next!= null){
            // 获取当前节点（移动当前指针）
            current = next;
            // next后移
            next = current.next;
            current.next = reverseHead.next ;
            reverseHead.next = current;
        }
        head.next = reverseHead.next;
    }


    public Node<T> whatAfterLast(){
        return (isLoop() ? head : null);
    }

    public boolean isLoop(){
        return false;
    }





    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.show();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.add(1,111);
        list.show();
        // 不会破环结构
        list.reverseShow();
        list.show();
        list.reverse3();
        list.show();
        list.reverse2();
        list.show();
        list.reverse();
        list.show();
        
    }
    
}
