package com.lj.algorithm.sort;

import com.test.common.utils.MyArrayUtil;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

public class TestSortAndSearch {
    
    @Test
    public void test() {
        int[] arrays = MyArrayUtil.getRandomArray(500, 1, 50000);
        int[] arrays1 = Arrays.copyOfRange(arrays, 0, arrays.length);
        int[] arrays2 = Arrays.copyOfRange(arrays, 0, arrays.length);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arrays);
        QuickSort quickSort = new QuickSort();
        Comparable[] result = quickSort.sort2(arrays1);
        Arrays.sort(arrays2);
        MyArrayUtil.printArray(arrays);
        MyArrayUtil.printArray(result);
        MyArrayUtil.printArray(arrays2);
    }
    
    @Test
    public void testSearch() {
        int[] arrays = MyArrayUtil.getRandomArray(50000, 1, 50000);
        Arrays.sort(arrays);
        MyArrayUtil.printArray(arrays);
        for (int i = 0; i < 20; i++) {
            int key = RandomUtils.nextInt(1, 50000);
            int index = Arrays.binarySearch(arrays, key);
            int index2 = binarySearch(arrays, key);
            if (index2 > 0 && index > 0) {
                System.out.println(index + " " + index2 + " " + arrays[index] + " " + arrays[index2] + " " + key);
            } else {
                System.out.println("key:" + key + " not in arrays");
            }
        }
    }
    
    private int binarySearch(int[] arrays, int i) {
        int start = 0;
        int end = arrays.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (arrays[middle] > i) {
                end = middle - 1;
            } else if (arrays[middle] < i) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        
        return -1;
    }
    
    
    @Test
    public void testSelectSort() {
        int[] nums = MyArrayUtil.getRandomArray(100, 1, 1000);
        SelectSort.selectSort(nums);
        MyArrayUtil.printArray(nums);
    }
    
    @Test
    public void testMergeSort() {
        int[] nums = MyArrayUtil.getRandomArray(100, 1, 1000);
        MergeSort.sort(nums);
        MyArrayUtil.printArray(nums);
    }
    
    @Test
    public void testRadix() throws Exception {
        int[] nums = MyArrayUtil.getRandomArray(100000, -10, 10000000);
        RadisSort.sort(nums);
        System.out.println(MyArrayUtil.checkArrayOrder(nums,true));
    }
    
    @Test
    public void testSortSpeed() throws Exception {
        int nums[] = MyArrayUtil.getRandomArray(8000000, 100001, 999999);
        QuickSort quickSort = new QuickSort();
        InsertSort insertSort = new InsertSort();
        BubbleSort bubbleSort = new BubbleSort();
        SelectSort selectSort = new SelectSort();
        HeapSort heapSort = new HeapSort();
        int[] copyOfNums = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums2 = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums3 = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums4 = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums5 = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums6 = Arrays.copyOf(nums, nums.length);
        int[] copyOfNums7 = Arrays.copyOf(nums, nums.length);
        long t1 = System.currentTimeMillis();
        BucketSort.sort(nums, 10);
        long t2 = System.currentTimeMillis();
        System.out.println("桶排序花费 : " + (t2 - t1) + "ms");
        BucketSort.radix(copyOfNums7);
        long t9 = System.currentTimeMillis();
        System.out.println("基数排序花费 : " + (t9 - t2) + "ms");
        quickSort.sort(copyOfNums);
        long t3 = System.currentTimeMillis();
        System.out.println("快排花费 : " + (t3 - t9) + "ms");
        MergeSort.sort(copyOfNums2);
        long t4 = System.currentTimeMillis();
        System.out.println("归并花费 : " + (t4 - t3) + "ms");
        insertSort.shell2(copyOfNums3);
        long t5 = System.currentTimeMillis();
        System.out.println("shell排序花费 : " + (t5 - t4) + "ms");
        Arrays.sort(copyOfNums4);
        long t6 = System.currentTimeMillis();
        System.out.println("Arrays的快排 : " + (t6 - t5) + "ms");
        heapSort.sort2(copyOfNums5);
        long t7 = System.currentTimeMillis();
        System.out.println("堆排: "+(t7-t6)+"ms");
        selectSort.sort2(copyOfNums6);
        long t8 = System.currentTimeMillis();
        System.out.println("选择 : " + (t8 - t7) + "ms");


//    MyArrayUtil.printArray(nums);
    }
    
    @Test
    public void testShell() {
        InsertSort insertSort = new InsertSort();
        int[] nums = MyArrayUtil.getRandomArray(1000, 1, 100000);
        insertSort.shell2(nums);
        MyArrayUtil.printArray(nums);
    }
    
}
