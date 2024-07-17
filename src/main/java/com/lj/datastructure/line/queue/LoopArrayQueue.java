package com.lj.datastructure.line.queue;

/**
 * 单向环形队列
 */
public class LoopArrayQueue<T> {

    Object[] element;
    // 数组大小
    int capacity;
    // 对头索引
    int front;
    // 队尾索引+1;
    int tail;
    
    public LoopArrayQueue (int size){
        this.capacity = size + 1 ;
        element = new Object[capacity];
        front = 0;
        tail = 0;
    }
    
    
    /**以下三个方法 isEmpty isFull size 可通过画图分析可得*/
    
    public boolean isEmpty(){
        return tail == front;
    }

    public boolean isFull(){
        return (tail+1)%capacity == front;
    }
    
    public int size(){
        return (tail - front + capacity) % capacity;
    }
    
    public void add(T t) {
        if(isFull()){
            System.out.println("队列已满！");
            return;
        }
        element[tail] = t;
        tail = next();
    }
    
    private int next() {
        return (tail + 1) % capacity;
    }
    
    public T poll(){
        if(isEmpty()){
            System.out.println("队列已空！");
            return null;
        }
        T result = (T) element[front];
        front = (front + 1) % capacity;
        return result;
    }
    
    public void show(){
        if(isEmpty()){
            System.out.println("队列为空！");
        }
        int f = front;
        int size = size();
        String str = "";
        for( int i=0 ; i<size ; i++ ){
            str += element[f]+",";
            f = (f+1) % capacity;
        }
        System.out.println(str);
    }
    
    public static void main(String[] args)  {
        LoopArrayQueue<Integer> intQueue = new LoopArrayQueue<>(5);
        intQueue.add(1);
        intQueue.add(2);
        // 1,2,
        intQueue.show();
        intQueue.add(3);
        intQueue.add(4);
        intQueue.add(5);
        // 1,2,3,4,5,
        intQueue.show();
        // 队列已满
        intQueue.add(6);
        System.out.println(intQueue.poll());
        System.out.println(intQueue.poll());
        // 3,4,5
        intQueue.show();
        System.out.println(intQueue.poll());
        System.out.println(intQueue.poll());
        System.out.println(intQueue.poll());
        // 队列已空
        intQueue.poll();
        intQueue.add(6);
        intQueue.add(7);
        intQueue.add(8);
        intQueue.add(9);
        intQueue.add(10);
        intQueue.add(11);
        intQueue.show();
    
    }
    
}
