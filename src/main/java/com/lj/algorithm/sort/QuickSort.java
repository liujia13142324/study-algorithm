package com.lj.algorithm.sort;

import com.test.common.aop.annotation.PrintLog;
import com.test.common.utils.MyArrayUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 记住挖坑填坑法就行
 * 1、最灵活，base 取等或不等都能满足，其他方法都要有特殊的条件（要求等于、或者不等于）
 * 2、最稳定
 * @param <T>
 */

public class QuickSort<T>{
	
	Random rd = new Random();
	
	@Test
	@PrintLog(count = 20)
	public void testQuickSort() {
		/*Comparable<Integer> num[] = new Integer[rd.nextInt(20) + 20];
		for (int i = 0; i < num.length; i++) {
			num[i] = rd.nextInt(1000);
		}*/
		//int num [] = MyArrayUtil.getRandomArray();
		// 以第一个为支撑点做快排序
		int [] num = MyArrayUtil.getRandomArray(20,1,1000);
//		int [] num = new int[]{3,6,9,1};
		int start = 0;
		int end = num.length - 1;
		// quickSort(num, start, end);
		//quickSort2(num, start, end);
//		quickSort3((Comparable<T>[]) num, start, end);
//		System.out.println("checkOrder:"+MyArrayUtil.checkArrayOrder(num,true));
		quickSort5( num, start, end);
		System.out.println("result:"+ MyArrayUtil.checkArrayOrder(num,true));
	}
	
	@Test
	@PrintLog(count = 20)
	public void testQuickSort2() {
		int num [] = MyArrayUtil.getRandomArray(10000000,1,100000000);
//		num = new int[]{89, 1, 59, 41, 1, 76, 33, 49, 8, 86};
		// 以第一个为支撑点做快排序
		int start = 0;
		int end = num.length - 1;
//		System.out.println(Arrays.toString(num));
		long startTime = System.currentTimeMillis();
		quickSort6( num, start, end);
		System.out.println(System.currentTimeMillis() - startTime);
//		System.out.println(Arrays.toString(num));
		System.out.println();
		System.out.println("checkOrder:"+MyArrayUtil.checkArrayOrder(num,true));
	}
	
	
	@Test
	@PrintLog(count = 20)
	public void testQuickSort3() {
		int num [] = MyArrayUtil.getRandomArray(10000000,1,100000000);
//		num = new int[]{89, 1, 59, 41, 1, 76, 33, 49, 8, 86};
		// 以第一个为支撑点做快排序
		int start = 0;
		int end = num.length - 1;
		int[] clone = num.clone();
		int[] clone1 = num.clone();
		int[] clone2 = num.clone();
		int[] clone3 = num.clone();
		quickSort( num, start, end);
		long startTime = System.currentTimeMillis();
		quickSort2( clone, start, end);
		long startTime2 = System.currentTimeMillis();
		quickSort4( clone1, start, end);
		long startTime3 = System.currentTimeMillis();
		quickSort7( clone2, start, end);
		long startTime4 = System.currentTimeMillis();
		Arrays.sort(clone3);
		long startTime5 = System.currentTimeMillis();
		System.out.println(startTime2 - startTime);
		System.out.println(startTime3 - startTime2);
		System.out.println(startTime4 - startTime3);
		System.out.println(startTime5 - startTime4);
		System.out.println();
		System.out.println("checkOrder:"+MyArrayUtil.checkArrayOrder(num,true));
	}

	// 双指针，不需要多余判断
	public void quickSort7(int[] num, int start, int end){
		if (start >= end) {
			return;
		}
		int l = start - 1;
		int r = end + 1;
		int base = num[start];
		while (l < r) {
			// 这两个顺序没有关系，不能存在等号
			while (num[--r] > base);
			while (num[++l] < base);
			if (l < r) {
				int tmp = num[l];
				num[l] = num[r];
				num[r] = tmp;
			}
		}
		
		/**
		 * 为啥要用右边递归？
		 * 当上面循环结束后
		 *  其中 l 满足：[l, end]: num >= base , [start, l): num <= base，但是可能存在 start == l，导致 [start, start) 这个区间消失，
		 *              只有一个[start, end]，导致递归参数不会发生变化，从而死递归
		 *
		 *  其中 r 满足：[start, r]: num <= base, (r, end]: num >= base，且 end != r，所以每次都能分成两个区间，所以每次递归参数都会发生变化
		 *              （当 start == l --> [start, start] 和 [start + 1, end] 两个区间）
		 */
		
		// 当 r == end，会死递归，但是 r != end
		quickSort7(num, start, r);
		quickSort7(num, r+1, end);
		
		// 当 l == start，会死递归，l 是可能等于 start 的
//		quickSort7(num, start, l-1);
//		quickSort7(num, l, end);
		
		// 基准元素不会换到基准位置 l，所以不能这样分治递归
//		quickSort7(num, start, l-1);
//		quickSort7(num, l+1, end);
	}
	
	// 第一种方式
	public void quickSort(int[] num, int start, int end) {
		if (start < end) {
			int l = start;
			int r = end;
			int temp = num[start];
			int dirction = 0;
			while (l < r) {
				if (dirction == 0) {
					if (temp <= num[r]) {
						r--;
					} else {
						num[l++] = num[r];
						dirction = 1;
					}
				} else if (dirction == 1) {
					if (temp >= num[l]) {
						l++;
					} else {
						num[r--] = num[l];
						dirction = 0;
					}
				}
			}
			num[l] = temp;
			quickSort(num, start, l - 1);
			quickSort(num, l + 1, end);
		}
	}
	


	//  第二种方式， 挖坑，补坑 ,  
	public void quickSort2(int num[], int start, int end) {

		if (start < end) {
			int l = start;
			int r = end;
			// 挖坑l
			int temp = num[l];
			while (l < r) {
				// 从右往左，找到第一个小于坑位的下标，比较等号可有可无
				while (l < r && num[r] > temp){
					r--;
				}
				if (l < r){
					// 挖坑r,填上了坑l，而且还 l++了，也就不会重复比较了
					num[l++] = num[r];
				}
				// 从左往右找，找到第一个大于坑位的下标
				while (l < r && num[l] < temp)  {
					l++;
				}

				if (l < r){
					// 挖坑l,填上了坑r
					num[r--] = num[l];
				}
			}

			num[l] = temp;
			quickSort2(num, start, l - 1);
			quickSort2(num, l + 1, end);
		}

	}
	
	// 第三种形式， 挖了，不补， 只是找准两个位置（第一个比它大的，第一个比它小的），交互两个位置，最后再和中间的那个换位置
	// 这个比较的时候需要等号  --- 》 确切的说 ， 下面的那个	while(temp>=nums[i] && i<j) 一定要有等于，上面的那个没有等于是正常的
	// 应该只适用于基准在最左，或者最右的情况
	public void quickSort3(Comparable<T>[] num, int  left, int  right) {

		if(left >= right){
			return ; 
		}
		
		Comparable<T> temp = num[left];
		Comparable<T> t;
		int i = left;
		int j = right;
		// 换成小于等于就不行了（死循环） --->这个条件改了，下面的不改，就任何一个判断都不会进去，ij都不会再变，所以死循环
		while(i<j){
			// 从右边开始不能变，因为基准在左边，跳出这个循环的时候，当前位置的值小于基准，所以最后可以直接和基准交换位置
			while(temp.compareTo((T)num[j])<=0 && i<j)
				j-- ;
			// 去掉等号方法，当 i == left && i < j的时候，i++，说明是某段集合第一次从左往右扫描，跳过 i == left的情况
			/*if(i == left && i < j){
				i++;
			}
			while(temp>nums[i] && i<j)
				i++;*/
			// 当首次到达這里，如果没有了等于 ， 那么，下面这个循环一定不会进去，因为首次到达这里，坑没有补上 ，一定有 temp == num[i]  ---> 此时 i == left
			while(temp.compareTo((T)num[i])>=0&& i<j)
				i++;

			if(i<j){
				t = num[j];
				
			    num[j] = num[i];
				num[i] = t;
			}
		}
		
		num[left] = num[i];
		num[i] = temp;
		quickSort3(num, left, i-1);
		quickSort3(num, i+1, right);
	}

	private void quickSort4(int[] num, int left, int right) {
		if(left >= right){
			return ;
		}

		int temp = num[left];
		int t;
		int i = left;
		int j = right;
		// 换成小于等于就不行了（死循环） --->这个条件改了，下面的不改，就任何一个判断都不会进去，ij都不会再变，所以死循环
		while(i<j){

			while(temp<num[j] && i<j){
				j-- ;
			}
			// 当首次到达這里，如果没有了等于 ， 那么，下面这个循环一定不会进去，因为首次到达这里，坑没有补上 ，一定有 temp == num[i]  ---> 此时 i == left
			while(temp>=num[i]&& i<j){
				i++;
			}

			if(i<j){
				t = num[j];
				num[j] = num[i];
				num[i] = t;
			}
		}

		num[left] = num[i];
		num[i] = temp;
		quickSort4(num, left, i-1);
		quickSort4(num, i+1, right);
	}

	/**
	 * 以任何一个元素为基准.
     * 基本思想：先找到基准应该要放的位置，最后基准位置和临界点的一个位置进行交换
	 * @param num
	 * @param left
	 * @param right
	 */
	public void quickSort5(int[] num , int left , int right){
		if(left>=right){
			return ;
		}

		int baseIndex = (left+right)/4;

		if(baseIndex < left){
			baseIndex = left;
		}

		int tmp = num[baseIndex];
		int l = left;
		int r = right;

		while(l<r){

			while(l<r && num[r] >= tmp){
				r--;
			}

			while(l<r && num[l] <= tmp){
				l++;
			}

			if(l<r){
				int t = num[l];
				num[l] = num[r];
				num[r] = t;
			}
		}

		// 判断基准在临界点和哪个元素交换位置
		if(l<baseIndex && num[l]<tmp){
			l++;
		}else if(l>baseIndex && num[l]>tmp){
			l--;
		}

		num[baseIndex] = num[l];
		num[l] = tmp;

		// 这个l必须是和临界点交换的位置
		quickSort5(num,left,l-1);
		quickSort5(num,l+1,right);
		return;
	}
	
    
    /**
     * 同样能以任何位置为基准，实现是通过类似于基准自动定位的方式
     */
    public static void quickSort6(int[] arr,int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int index = (left + right) / 2;
        if(index<l){
            index = l;
        }
        int pivot = arr[index];
        //临时变量，作为交换时使用
        int temp = 0;
        
        while( l < r) {
            
            while( arr[l] < pivot) {
                l ++;
            }
            while(arr[r] > pivot) {
                r --;
            }
            if( l >= r) {
                break;
            }
            
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            
            //这两步实在是迷惑
    
            // remark1 start ---------
    
            // 最终移动都是趋近pivolt，所以当发现l和pivolt相等，即l就在pivolt处，此时r左移动，即r向pivolt趋近
            if(arr[l] == pivot) {
                r--;
            }
            // 原理同上，不过要注意，此时的r可能是经过上一步--后的r，经验算这样并不影响结果。
            if(arr[r] == pivot) {
                l++;
            }
            // remark1 end ---------
        }
        
        // 此处只有两种情况，l == r 或者 l = r+1，根remark1中的两个判断的顺序可以知道pivot的位置。
        // 当先r--后l++ 则 r 的位置为pivot
        // 当先l++后r-- 则 l 的位置为pivot
        if (l == r) {
            l ++;
            r --;
        }else{
            r--;
        }
        
        //向左递归
        if(left < r) {
            quickSort6(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSort6(arr, l, right);
        }
    }
    
    
    //自己对外提供的方法，直接排序数组
    public int[] sort(int[] num){
        quickSort4(num,0,num.length-1);
        return num;
    }
    
    //自己对外提供的方法
    public Comparable<T>[] sort2(int[] num){
        Integer[] comparable = new Integer[num.length];
        for( int i=0 ; i<num.length;i++ ){
            comparable[i] = num[i];
        }
        quickSort3((Comparable<T>[])comparable,0,num.length-1);
        return (Comparable<T>[]) comparable;
    }
    
}
