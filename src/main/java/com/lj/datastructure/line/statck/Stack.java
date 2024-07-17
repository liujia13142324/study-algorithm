package com.lj.datastructure.line.statck;

public class Stack<T> {

    Object[] stack;
    int capacity;
    int top;

    public Stack(int capacity) {
        this.capacity = capacity;
        stack = new Object[capacity];
        top = -1;
    }

    public void clear(){
        top = -1;
    }

    public boolean isEmpty(){
        return top < 0;
    }

    public boolean isFull(){
        return top == capacity-1;
    }

    public T push(T t){
        if(isFull()){
            return null;
        }
        stack[++top] = t;
        return t;
    }

    public T pop(){
        if(isEmpty()){
            return null;
        }

        return (T) stack[top--];
    }

    public T peek(){
        if(isEmpty()){
            return null;
        }
        return (T) stack[top];
    }

    public void show(){
        if(isEmpty()){
            System.out.println("栈为空！");
            return;
        }
        for(int i=top ; i>=0; i--){
            System.out.print(stack[i]+",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(3);
        stack.show();
        System.out.println(stack.push(1));
        System.out.println(stack.push(2));
        System.out.println(stack.push(3));
        System.out.println(stack.push(4));
        stack.show();
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.show();
    }

}
