package com.lj.datastructure.line.queue;

/**
 * 单向环形队列
 */
public class LoopArrayQueue2<T> {
    
    Object[] element;
    // 数组大小
    int capacity;
    // 对头索引
    int front;
    // 队尾索引
    int tail;
    
    public LoopArrayQueue2(int size) {
        capacity = size + 1;
        element = new Object[capacity];
        front = tail = 0;
    }
    
    
    /**
     * 以下三个方法 isEmpty isFull size 可通过画图分析可得
     */
    
    public boolean isEmpty() {
        return front == tail;
    }
    
    public boolean isFull() {
        return size() == capacity - 1;
    }
    
    public int size() {
        return (tail - front + capacity) % capacity;
    }
    
    public void add(T t) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        
        element[tail] = t;
        tail = next(tail);
    }
    
    private int next(int idx) {
        return (idx + 1) % capacity;
    }
    
    
    public T poll() {
        if (isEmpty()) {
            System.out.println("队列已空！");
            return null;
        }
        
        T r = (T) element[front];
    
        front = next(front);
        
        return r;
    }
    
    public void show() {
        
        int f = front;
        
        while (f != tail) {
            System.out.print(element[f] + " ");
            f = next(f);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        LoopArrayQueue2<Integer> intQueue = new LoopArrayQueue2<>(5);
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
        // 队列已满
        intQueue.add(11);
        
        // 6,7,8,9,10
        intQueue.show();
        
    }
    
}
