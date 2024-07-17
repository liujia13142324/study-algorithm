package com.lj.problem.hwod;


import java.util.Arrays;

/**
 * 题目描述:某学校举行运动会，学生们按编号(1、2、3.n)进行标识，现需要按照身高由低到高排列，
 * 对身高相同的人，按体重由轻到重排列:对于身高体重都相同的人维持原有的编号顺序关系。请输出排列后的学生编号。
 *
 * 输入描述:两个序列，每个序列由n个正整数组成(0<n<= 100)。
 * 第一个序列中的数值代表身高,
 * 第二个序列中的数值代表体重。
 *
 * 输出描述:排列结果，每个数值都是原始序列中的学生编号，编号从1开始
 *
 * 示例1
 * 输入:4
 * 100 100 120 130
 * 40 30 60 50
 * 输出:2 1 3 4
 * 说明:输出的第一个数字2表示此人原始编号为2,即身高为100，体重为30的这个人。由于他和编号为1的人身高一样，但体重更轻，因此要排在1前面,。
 *
 * 示例2
 * 输入:3
 * 90 110 90
 * 45 60 45
 * 输出:1 3 2
 * 说明:1和3的身高体重都相同，需要按照原有位置关系让1排在3前面，而不是312
 */
public class SortByHeightAndWeight {
    
    // 简单点，使用冒泡解决问题
    // 也可以使用归并
    public String solve(int[][] arr, int[] result){
        
        int[] tmp;
        int tmp2;
        for (int i = 0; i < arr.length - 1; i++) {
            
            for (int j = 0; j < arr.length - i -1; j++) {
                
                if (arr[j][0] > arr[j+1][0]) {
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
    
                    tmp2 = result[j];
                    result[j] = result[j+1];
                    result[j+1] = tmp2;
                    
                }else if (arr[j][0] == arr[j+1][0]){
                    
                    if (arr[j][1] > arr[j+1][1]) {
                        tmp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = tmp;
    
                        tmp2 = result[j];
                        result[j] = result[j+1];
                        result[j+1] = tmp2;
                    }
                    
                }
                
            }
            
        }
     
        return Arrays.toString(result);
        
    }
    
    public static void main(String[] args) {
    
        System.out.println(new SortByHeightAndWeight().solve(
                new int[][]{
                        new int[]{100,40}
                        ,new int[]{100,30}
                        ,new int[]{120,60}
                        ,new int[]{130,50}
                },
                new int[]{1,2,3,4}
        ));
    
    
        System.out.println(new SortByHeightAndWeight().solve(
                new int[][]{
                        new int[]{90,45}
                        ,new int[]{110,60}
                        ,new int[]{90,45}
                },
                new int[]{1,2,3}
        ));
        
    }
    
}
