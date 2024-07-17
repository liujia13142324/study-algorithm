package com.lj.algorithm.search;

import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 * 令 f 为斐波那契数列 , f[k] = f[k-1] + f[k-2]
 * 当数组的元素是 f[k]-1 个的时候，
 * 有 f[k]-1 = f[k-1]-1 + f[k-2]-1 + 1
 * 数组可以分成 f[k-1]-1，f[k-2]-1 两部分，分割点为 low + f[k-1]-1
 */
public class FibonacciSearch {
    
    @Test
    public void test() {
        int[] source = new int[]{2, 3, 23, 12, 323, 1, 34, 53, 453, 231, 412, 41};
        Arrays.sort(source);
        System.out.println(source.length + "\t" + Arrays.toString(source));
        System.out.printf("result:%d\n", search(source, 12));
        System.out.printf("result:%d\n", search(source, 2));
        System.out.printf("result:%d\n", search(source, 3));
        System.out.printf("result:%d\n", search(source, 41));
        System.out.printf("result:%d\n", search(source, 23));
        System.out.printf("result:%d\n", search(source, 323));
        System.out.printf("result:%d\n", search(source, 1));
        System.out.printf("result:%d\n", search(source, 34));
        System.out.printf("result:%d\n", search(source, 53));
        System.out.printf("result:%d\n", search(source, 453));
        System.out.printf("result:%d\n", search(source, 231));
        System.out.printf("result:%d\n", search(source, 412));
        System.out.printf("result:%d\n", search(source, 41));
        System.out.printf("result:%d\n", search(source, 12312));
        System.out.printf("result:%d\n", search(source, 22));
    
        System.out.println();
    
        System.out.printf("result:%d\n", search2(source, 12));
        System.out.printf("result:%d\n", search2(source, 2));
        System.out.printf("result:%d\n", search2(source, 3));
        System.out.printf("result:%d\n", search2(source, 41));
        System.out.printf("result:%d\n", search2(source, 23));
        System.out.printf("result:%d\n", search2(source, 323));
        System.out.printf("result:%d\n", search2(source, 1));
        System.out.printf("result:%d\n", search2(source, 34));
        System.out.printf("result:%d\n", search2(source, 53));
        System.out.printf("result:%d\n", search2(source, 453));
        System.out.printf("result:%d\n", search2(source, 231));
        System.out.printf("result:%d\n", search2(source, 412));
        System.out.printf("result:%d\n", search2(source, 41));
        System.out.printf("result:%d\n", search2(source, 12312));
        System.out.printf("result:%d\n", search2(source, 22));
    }
    
    private int search(int[] source, int val) {
        int[] f = generateFibonacciArray(20);
//        MyArrayUtil.printArray(f);
        
        // 填充数组，将 source 长度填充到斐波那契数列的 f(k)-1 的数量
        // 1、寻找k
        int k = 0;
        int sourceLen = source.length;
        while (sourceLen > f[k] - 1) {
            k++;
        }
        // 2、数组扩容
        int[] sourceCopy = Arrays.copyOf(source, f[k] - 1);
        // 3、数组填充，填充为数组的最后一个元素
        for (int i = sourceLen, j = f[k] - 1; i < j; i++) {
            sourceCopy[i] = sourceCopy[sourceLen - 1];
        }
        MyArrayUtil.printArray(sourceCopy);
        
        // 开始搜索
        int low = 0;
        int high = sourceCopy.length - 1;
        int mid;
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (val == sourceCopy[mid]) {
                // 因为后面的元素都是填充的
                return Math.min(mid, sourceLen - 1);
            } else if (val < sourceCopy[mid]) {
                high = mid - 1;
                // 左边是 f(k-1)-1，继续往左遍历
                k--;
            } else if (val > sourceCopy[mid]) {
                low = mid + 1;
                // 右边是 f(k-2)-1，继续往右边遍历
                k -= 2;
            }
        }
        
        return -1;
    }
    
    
    private int search2(int[] source, int val) {
        int[] f = generateFibonacciArray(20);
//        MyArrayUtil.printArray(f);
        
        // 填充数组，将 source 长度填充到斐波那契数列的 f(k) 的数量
        // 1、寻找k
        int k = 0;
        int sourceLen = source.length;
        while (sourceLen > f[k]) {
            k++;
        }
        // 2、数组扩容
        int[] sourceCopy = Arrays.copyOf(source, f[k]);
        // 3、数组填充，填充为数组的最后一个元素
        for (int i = sourceLen, j = f[k]; i < j; i++) {
            sourceCopy[i] = sourceCopy[sourceLen - 1];
        }
        MyArrayUtil.printArray(sourceCopy);
        
        // 开始搜索
        int low = 0;
        int high = sourceCopy.length - 1;
        int mid;
        while (low <= high && k > 0) {
            mid = low + f[k - 1] - 1;
            if (val == sourceCopy[mid]) {
                // 因为后面的元素都是填充的
                return Math.min(mid, sourceLen - 1);
            } else if (val < sourceCopy[mid]) {
                high = mid - 1;
                // 左边是 f(k-1)-1，继续往左遍历
                k--;
            } else if (val > sourceCopy[mid]) {
                low = mid + 1;
                // 右边是 f(k-2)-1，继续往右边遍历
                k -= 2;
            }
        }
        
        return -1;
    }
    
    /**
     * 生成斐波那契数列
     *
     * @return
     */
    int[] generateFibonacciArray(int size) {
        size = Math.max(size, 3);
        int[] result = new int[size];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < size; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        
        return result;
    }
    
    
    @Test
    public void testGetFibonacci() {
        System.out.println(Arrays.toString(generateFibonacciArray(20)));
    }
    
}
