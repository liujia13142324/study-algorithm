package com.lj.problem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 汉诺塔，经典递归回溯算法
 */
public class Hanoi {
    
    @Test
    public void test(){
        
        // 初始化一个 n 层的汉诺塔
        int n = 10;
        PathLinkedList<Integer> data = new PathLinkedList<>("source");
        for(int i=n; i>0; i--){
            data.push(i);
        }
        PathLinkedList<Integer> tmp = new PathLinkedList<>("tmp");
        PathLinkedList<Integer> target = new PathLinkedList<>("target");
        System.out.println(data);
        // 开始挪动
        move(data,data.size(),tmp,target);
        
        System.out.println(data);
        System.out.println(target);
        System.out.println(paths.size());
        System.out.println(paths);
    }
    
    private void move(PathLinkedList<Integer> from
            , int fromSize
            , PathLinkedList<Integer> tmp
            , PathLinkedList<Integer> target) {
        
        if(fromSize <= 0){
            return;
        }
        
        // 把前 n-1 个元素丢到临时空间
        move(from,fromSize-1,target,tmp);
        
        // 把第n个丢到目标空间;
        target.push(from.pop(),from.name);
        
        // 把前 n-1 个 丢到目标空间
        move(tmp,fromSize-1,from,target);
    }
    
    
    /*private void move(LinkedList<Integer> from
            , LinkedList<Integer> tmp
            , LinkedList<Integer> target) {
        
        if(from.isEmpty()){
            return;
        }
        
        // 把前 n-1 个元素丢到临时空间
        LinkedList<Integer> firstPart = new LinkedList();
        int preSize = from.size()-1;
        for(int i=0;i<preSize;i++){
            firstPart.add(i,from.pop());
        }
        move(firstPart,target,tmp);
        
        // 把第n个丢到目标空间;
        target.push(from.pop());
        
        // 把前 n-1 个 丢到目标空间
        LinkedList<Integer> firstPartOfTmp = new LinkedList();
        for(int i=0;i<preSize;i++){
            firstPartOfTmp.add(i,tmp.pop());
        }
        move(firstPartOfTmp,from,target);
    }*/
    
    List<String> paths = new ArrayList<>();
    
    class PathLinkedList<T> extends LinkedList<T>{
        String name;
        public PathLinkedList(String name) {
            this.name = name;
        }
    
        public void push(T t,String name) {
            paths.add("从 "+name+" 移动 "+t+" 到 "+this.name);
            super.push(t);
        }
    }
    
    @Test
    public void testSublist(){
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.subList(0,3));
        System.out.println(stack);
        // 抛异常
        System.out.println(stack.pop());
        // 返回空
        System.out.println(stack.poll());
        System.out.println(stack);
        System.out.println(stack.pollLast());
        System.out.println(stack);
    }

    
    public void hanoi(PathLinkedList source, int end, PathLinkedList tmp, PathLinkedList target) {
        
        if (end < 0) {
            return;
        }
    
        hanoi(source, end - 1, target, tmp);
        target.push(source.pop(), source.name);
        hanoi(tmp, end - 1, source, target);
    }

    
    @Test
    public void testHanoi() {
    
        PathLinkedList source = new PathLinkedList("source");
        PathLinkedList tmp = new PathLinkedList("tmp");
        PathLinkedList target = new PathLinkedList("target");
        int layers = 10;
        
        
        for (int i = layers; i > 0; i--) {
            source.push(i);
        }
    
     
        
        hanoi(source, source.size()-1, tmp, target);
        
        System.out.println(source);
        System.out.println(tmp);
        System.out.println(target);
    
        System.out.println(paths.toString().replace(",", "\n"));
        System.out.println(paths.size());
    }
    
}
