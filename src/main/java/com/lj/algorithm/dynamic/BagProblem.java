package com.lj.algorithm.dynamic;

import lombok.ToString;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  背包问题：有一个背包，容量为 n 磅 ,有物品 1,2,3,4,5,6,7,8，求能装入背包的总价值最大
 *
 * 利用动态规划来解决。每次遍历到的第i个物品，根据w[i]和value[i]来确定是否需要将该物品放入背包中。即对于给定的n个物品，
 * 设v[i]、w[i]分别为第i个物品的价值和重量，C为背包的容量。再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值。
 * 则我们有下面的结果：
 */
public class BagProblem {
    
    private String[] things;
    private int value[];
    private int weight[];
    private int capacity;

    
    BagProblem(int capacity, String[] things, int value[], int weight[]){
        this.capacity = capacity;
        this.things = things;
        this.value = value;
        this.weight = weight;
    }
    
    public BagProblemResult solve(){
        int[][] v = new int[things.length+1][capacity+1];
        // 存储 i,j 放入背包中的物品结果
        int[][][] selected = new int[things.length+1][capacity+1][];
        
        int maxI = 0;
        int maxJ = 0;
        
        for( int i=1 ; i<=things.length ; i++ ){
            
            for( int j=1 ; j<=capacity ; j++ ){
                
                if(j >= weight[i-1]){
                    // 判断前 i-1 个的最大容量和 当前物品+v[i-1][背包剩余容量] 谁的价值大
                    int remainWeight = j - weight[i - 1];
                    v[i][j] = Math.max( v[i-1][j] , value[i-1]+v[i-1][remainWeight] );
                    // 设置 selected
                    if(v[i][j] == v[i-1][j]){
                        // 说明没变,共享一份内存
                        selected[i][j] = selected[i-1][j];
                    }else if( selected[i-1][remainWeight] == null ){
                        // 说明之前背包是空的
                        selected[i][j] = new int[]{i - 1};
                        maxI = i;
                        maxJ = j;
                    }else{
                        // 说明之前背包有东西
                        int oldLen = selected[i-1][remainWeight].length;
                        int[] newSelected = Arrays.copyOf(selected[i-1][remainWeight], oldLen + 1);
                        newSelected[oldLen] = i-1;
                        selected[i][j] = newSelected;
                        maxI = i;
                        maxJ = j;
                    }
                }else{
                    v[i][j] = v[i-1][j];
                    selected[i][j] = selected[i-1][j];
                }
                
            }
            
        }
    
        BagProblemResult result = null;
        if(selected[maxI][maxJ] != null){
            result = new BagProblemResult();
            int totalWeight = 0;
            int totalValue = 0;
            List<String> things = new ArrayList<>();
            for(int i : selected[maxI][maxJ]){
                totalWeight += weight[i];
                totalValue += value[i];
                things.add(this.things[i]);
            }
            Assert.assertEquals("val不相等！",totalValue,v[maxI][maxJ]);
            result.totalWeight = totalWeight;
            result.value = totalValue;
            result.things = things;
        }
        
        return result;
    }
    
    
    @ToString
    public class BagProblemResult{
        int value;
        int totalWeight;
        List<String> things;
    }
    
    public static void main(String[] args) {
        BagProblem bagProblem = new BagProblem(
                7
                ,new String[]{"吉他","音响","电脑","板砖","黄金","书本"}
                ,new int[]{1500,3000,2000,100,4000,1000}
                ,new int[]{1,4,3,5,3,4}
        );
        System.out.println(bagProblem.solve());
    }
    
}
