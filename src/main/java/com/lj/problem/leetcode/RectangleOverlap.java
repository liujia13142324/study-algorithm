package com.lj.problem.leetcode;

import org.junit.Test;

/**
 *矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 */
public class RectangleOverlap {

    @Test
    public void test2(){
        System.out.println(isRectangleOverlap(new int[]{-257926405,-680763313,702840196,454409669},
                new int[]{-275916328,-417802221,22808107,675629469}));
    }

    /**
     * 检查位置
     * @param rec1
     * @param rec2
     * @return
     */

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    /**
     * 检查区域，一定是先左后右的，即 rec[2] > rec[0] x2>x1   rec[3] > rec[1]
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    @Test
    public void test(){
        System.out.println(isOverLap(new int[]{-257926405,-680763313,702840196,454409669},
                new int[]{-275916328,-417802221,22808107,675629469}));
    }

    /**
     * 我的算是检查区域，不过两点可以不是先左后右
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isOverLap(int[] rec1, int[] rec2) {

        int [] areX1 = new int[]{  Math.min(rec1[0] , rec1[2]) , Math.max(rec1[0] , rec1[2]) };
        int [] areY1 = new int[]{  Math.min(rec1[1] , rec1[3]) , Math.max(rec1[1] , rec1[3]) };
        int [] areX2 = new int[]{  Math.min(rec2[0] , rec2[2]) , Math.max(rec2[0] , rec2[2]) };
        int [] areY2 = new int[]{  Math.min(rec2[1] , rec2[3]) , Math.max(rec2[1] , rec2[3]) };

        boolean result = false;

        int relationX = areaInfo(areX1,areX2);
        int relationY = areaInfo(areY1,areY2);
        if( relationX ==1 || relationY==1 ){
            result = false;
        }else {
            result = true;
        }
        return result;
    }



    @Test
    public void testIntersect(){
        System.out.println(areaInfo(new int[]{-521,-487} , new int[]{-557,-180} ));
        System.out.println(areaInfo(new int[]{-586,992} , new int[]{114,267} ));
        System.out.println(isAContainB( new int[]{-557,-180} , new int[]{-521,-487}  ));
    }

    public boolean isAContainB( int[] area1 , int[] area2 ){
        if(area1[0] < area2[0] && area1[1] > area2[1]){
            return true;
        }
        return false;
    }

    /**
     * 1 相离
     * 2 相交
     * 3 包含
     */
    public int areaInfo( int[] area1 , int[] area2 ){
        long length1 = getAreLength(area1);
        long length2 = getAreLength(area2);
        long distance = Math.max( Math.abs(area1[0] - area2[1]) , Math.abs(area2[0] - area1[1]) );
        int result = 1;
        if( distance <  length1 + length2 ){
            long maxL = Math.max(length1,length2);
            result = 2;
            if( distance < maxL ){
                result = 3;
            }
        }
        return result;
    }

    public long getAreLength(int area[]){
        return Math.abs(area[0]-area[1]);
    }


}
