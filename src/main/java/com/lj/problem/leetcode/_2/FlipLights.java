package com.lj.problem.leetcode._2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * <p>
 * 这 4 个开关各自都具有不同的功能，其中：
 * <p>
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 * <p>
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 * <p>
 * 输入：n = 1, presses = 1
 * 输出：2
 * 解释：状态可以是：
 * - 按压开关 1 ，[关]
 * - 按压开关 2 ，[开]
 * <p>
 * 输入：n = 2, presses = 1
 * 输出：3
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关]
 * - 按压开关 2 ，[开, 关]
 * - 按压开关 3 ，[关, 开]
 */
public class FlipLights {
    
    
    @Test
    public void testFlipLights() {
    
        LightBitMapUtil lightBitMap = new LightBitMapUtil(32);
        System.out.println(Arrays.toString(lightBitMap.switch1(lightBitMap.ints)));
        lightBitMap.print();
    
        lightBitMap = new LightBitMapUtil(33);
        System.out.println(Arrays.toString(lightBitMap.switch1(lightBitMap.ints)));
        lightBitMap.print();
    
        lightBitMap = new LightBitMapUtil(34);
        System.out.println(Arrays.toString(lightBitMap.switch1(lightBitMap.ints)));
        lightBitMap.print();
    }
    
    
   static int INT_30_BIT = (1<<30) - 1;
    
    /**
     * 位图
     *
     * @param n
     * @param presses
     * @return
     */
    public int flipLights(int n, int presses) {
        Set<Integer> resultSet = new HashSet();
        LightBitMapUtil bitMapUtil = new LightBitMapUtil(n);
        resultSet.add(bitMapUtil.ints.hashCode());
        
        press(resultSet,bitMapUtil.ints,bitMapUtil,1,presses);
        return resultSet.size();
    }
    
    private void press(Set<Integer> resultSet,int[] ints, LightBitMapUtil bitMapUtil, int k, int total) {
        if(k>total){
            return;
        }
        int tmp[] = bitMapUtil.switch1(ints);
        if(!resultSet.contains(tmp.hashCode())){
            k++;
            press(resultSet,tmp,bitMapUtil,k,total);
        }
        tmp = bitMapUtil.switch2(ints);
        
    }
    
    
    // 每个数只用31位，符号位不能用
    public static class LightBitMapUtil {
        
        int ints[];
        // 只记住最高位，101010....
        int num2 = 1;
        // 010101....
        int num3;
        
        // 01010101010101010101010101010101
        // 00101010101010101010101010101010
        static int EVEN = Integer.parseInt("01010101010101010101010101010101", 2);
        
//        static int ODD = Integer.parseInt("00101010101010101010101010101010", 2);
        
        // 11011011011011011011011011011011
        // 10110110110110110110110110110110
        
        // 000110110110110110110110110110110
        static int NUM4 =  Integer.parseInt("00011011011011011011011011011011", 2);
        
        int remains;
        int arraySize;
        
        /**
         * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
         */
        // 0 12 3 45 6 78 9
        // 0 11 0 11 0 11 0
        int num4;
        int size;
        
        public LightBitMapUtil(int size) {
            arraySize = (size / 32) + 1;
            remains = size & (32 - 1);
            ints = new int[arraySize];
            
            // 1 -> 10 -> 101 -> 1010 -> 10101
            // 处理 1010
            for (int i = 0; i < remains-1 ; i++) {
                num2 <<= 1;
                if ((i & 1) == 1) {
                    num2++;
                }
            }
            num3 = num2 >> 1;
            
            // 处理 011011011  3k+1为0
            int k = 1;
            for (int i = 1; i < remains; i++) {
                if (i % (3 * k) == 0) {
                    num4 <<= 1;
                } else {
                    num4 = (num4 << 1) + 1;
                }
            }
            
            this.size = size;
        }
    
    
        public int[] switch1(int[] ints){
            int[] result = ints.clone();
            for(int i=0; i<ints.length; i++){
                result[i] = (~ints[i]) & Integer.MAX_VALUE;
            }
            return result;
        }
    
        public int[] switch2(int[] ints) {
            int[] result = ints.clone();
            for(int i=0; i<ints.length-1; i++){
                result[i] = (ints[i]^EVEN) & Integer.MAX_VALUE;
            }
            result[ints.length-1] =  (ints[ints.length-1] ^ num2) & Integer.MAX_VALUE;
            return ints;
        }
    
        public int[] switch3(int[] ints) {
            int[] result = ints.clone();
            for(int i=0; i<ints.length-1; i++){
                result[i] = (ints[i]^EVEN) & Integer.MAX_VALUE;
            }
            result[ints.length-1] =  (ints[ints.length-1] ^ num3) & Integer.MAX_VALUE;
            return ints;
        }
    
        public int[] switch4(int[] ints) {
            int[] result = ints.clone();
            for(int i=0; i<ints.length-1; i++){
                result[i] = (ints[i]^EVEN) & Integer.MAX_VALUE;
            }
            result[ints.length-1] =  (ints[ints.length-1] ^ num3) & INT_30_BIT;
            return ints;
        }
        
        
        
        // 高位打印
        public void print() {
            StringBuilder intStr = new StringBuilder();
            StringBuilder num23TailStr = new StringBuilder();
            StringBuilder num4Str = new StringBuilder();
            int arraySize = (size / 32) + 1;
            int remains = size & (32 - 1);
            intStr.append(Integer.toBinaryString(((1 << remains) - 1) & ints[arraySize - 1]));
            for (int i = arraySize - 2; i >= 0; i--) {
                intStr.append(" ").append(withFullBinary(ints[i]));
            }
            
            num4Str.append(Integer.toBinaryString(((1 << remains) - 1) & num4));
            for(int i=0; i<arraySize-1; i++){
                num4Str.append(" ").append(withFullBinary(NUM4));
                num23TailStr.append(" ").append(withFullBinary(EVEN));
            }
    
            System.out.println("ints:"+intStr.toString());
            System.out.println("num2:"+ withFullBinary(num2)+num23TailStr );
            System.out.println("num3:"+  withFullBinary(num3)+num23TailStr );
            System.out.println("num4:"+num4Str.toString());
    
        }
        
        private String withFullBinary(int num) {
            
            if(num == 1 || num == 0){
                return "000000000000000000000000000000"+num;
            }
            StringBuilder stringBuilder = new StringBuilder(Integer.toBinaryString(num));
            
            while (stringBuilder.length() < 31) {
                stringBuilder.insert(0, 0);
            }
            
            if(stringBuilder.length() > 31){
               return stringBuilder.substring(1);
            }
            
            return stringBuilder.toString();
        }
    
        private String withFullBinary2(int num) {
        
            if(num < 4){
                return "000000000000000000000000000000"+num;
            }
            StringBuilder stringBuilder = new StringBuilder(Integer.toBinaryString(num).substring(2));
        
            while (stringBuilder.length() < 31) {
                stringBuilder.insert(0, 0);
            }
            return stringBuilder.toString();
        }
    
    
      
    }
    
    
}
