package com.lj.problem.leetcode;

import org.junit.Test;

import java.util.*;

/**
 *你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *
 * 你的目标是确切地知道 F 的值是多少。
 *
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SuperEggDrop {

    @Test
    public void test1() {
        /*for (int i=1 ; i<=10 ; i++){
            int result= superEggDrop1(i,10);
            System.out.print(result+" ");
        }*/
        long start = new Date().getTime();
        int result= superEggDrop1(2,26);
        long end = new Date().getTime();
        System.out.print(result+"，时间：" + (end - start) + "ms" );
    }

    /**
     * 这种计算方法，要求把每楼都试到，说明可能存在临界楼层不存在的异常情况(可能是0层...)
     * @param eggCount 鸡蛋数
     * @param layer  楼层数
     * @return
     *
     * 效率太慢
     *
     */

    public int superEggDrop1(int eggCount, int layer  ) {

        if(eggCount == 1) {
            return layer;
        }
        // 当楼层为1的时候，还是需要试一次
        if(layer <= 1) return layer;


        List<Integer> l = new ArrayList<>();
        for(int x=1 ; x<=layer ; x++){
            int t1 = superEggDrop1(eggCount - 1, x-1 );
            int t2 = superEggDrop1(eggCount, layer-x );
            l.add(
                    Math.max( t1 , t2) + 1
            );
        }

        return  Collections.min(l);

    }




    @Test
    public void test3(){
        System.out.println( superEggDrop3(2,1000) );
    }


    Map<Integer, Integer> memo = new HashMap();

    /**
     * 提高性能
     */
    public int superEggDrop3( int eggCount , int layer ){
        int key = layer*100+eggCount;
        if(memo.get( key ) == null){
            if( layer<=1 ){
                return layer;
            } else if ( eggCount==1 ) {
                return layer;
            }else{
                // 二分查找X
                int lo = 1;
                int hi = layer;
                // 当 lo < hi 且 t1 < t2 且 lo == mid 的时候，循环会卡住。这时候lo永远只比hi少1，故 lo<hi-1
                while( lo < hi-1 ){
                    int mid = (lo+hi)/2;
                    int t1 = superEggDrop3( eggCount-1 , mid-1 );
                    int t2 = superEggDrop3( eggCount , layer - mid );
                    if(t1>t2){
                        hi = mid;
                    }else if(t1<t2){
                        lo = mid;
                    }else{
                        memo.put(key , t1+1);
                        return t1+1;
                    }
                }

                memo.put(key ,
                       1+Math.min(
                       Math.max( superEggDrop3(eggCount-1,lo-1) , superEggDrop3(eggCount,layer-lo))
                      ,Math.max( superEggDrop3(eggCount-1,hi-1) , superEggDrop3(eggCount,layer-hi)) ) );

            }
        }

        return memo.get( key );
    }


    @Test
    public void test2(){
        /*for (int i=1 ; i<=10 ; i++){
            int result= superEggDrop2(i,10);
            System.out.print(result+" ");
        }*/
        int result= superEggDrop2(3,15);
        System.out.print(result+" ");
    }


    /**
     * 这种计算方法，表示临界楼层必须存在，所以不需要每层都试
     * @param eggCount 鸡蛋数
     * @param layer  楼层数
     * @return
     */

    public int superEggDrop2(int eggCount, int layer  ) {

        if(eggCount == 1){
            if(layer>1){
                return layer-1;
            }else{
                return 0;
            }
        }
        if(layer <= 1) {
            return 0;
        }

        List<Integer> l = new ArrayList<>();
        for(int x=1 ; x<=layer ; x++){
            int t1 = superEggDrop2(eggCount - 1, x );
            int t2 = superEggDrop2(eggCount, layer-x );
            l.add(
                Math.max( t1 , t2) + 1
            );
        }
        return  Collections.min(l);
    }


    @Test
    public void testC(){
        List<Integer> l = new ArrayList<>();
        /*l.add(21);
        l.add(12);
        l.add(32);*/
        System.out.println(Collections.max(l));
    }
}
