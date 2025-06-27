package com.lj.algorithm;

import com.lj.study.common.aop.annotation.PrintLog;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AlgorithmUtil {
    
    
    /**
     * 组合
     *
     * @return
     */
    public static List<List<String>> getCnm(int n, String collections) {
        String [] chars = new String[collections.length()];
        int index = 0 ;
        for(char c : collections.toCharArray()){
            chars[index++] = String.valueOf(c);
        }
       return getCnm(n,chars);
    }
    
    /**
     * 组合
     *
     * @return
     */
    public static List<List<String>>  getCnm(int n, String[] chars) {
        List<List<String>> result = new ArrayList<>();
        
        if (n <= 0) {
            return result;
        }
        
        
        int len;
        if (n == (len = chars.length) ) {
            result.add( new ArrayList(Arrays.asList(chars)) );
            return result;
        }
        
        int points[] = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = i;
        }
        
        int maxPos = len - n;
        int i,nextPoint,next=0;
        
        
        // 当第一个指针没有大于 len-n
        outside:
        while (points[0] <= maxPos) {
            List<String> one = new ArrayList<>();
            for (i = 0; i < n; i++) {
                one.add( chars[points[i]] ) ;
            }
            result.add(one);
            
            // 如果最后一个指针已经移动到最后，要开始移动前面的指针，并且指针重新置位
            i = 0;
            while( (nextPoint=n-i-1)>=0 && (next=points[nextPoint]+1) >= len-i){
                i++;
            }
            
            // 这种情况说明第一个指针已经无法移动到合适的位置，说明结果已经出来了
            if(nextPoint < 0){
                break outside;
            }
            
            // nextPoint：第一个需要移动的指针
            points[nextPoint] = next;
            
            // 如果 nextPoint 不是最后一个指针，重置该指针之后的全部索引值 后面=前面+1
            for( i=nextPoint+1 ; i<n ; i++){
                points[i] = points[i-1] + 1;
            }
        }
        
        return result;
    }
    
    /**
     * 求幂集： Cn1 + Cn2 + Cn3 + ... + Cnn;
     * @return
     */
    public static List<List<String>> powerSet(String[] chars){
        List<List<String>> result = new ArrayList((int) (Math.pow(2,chars.length)-1));
        for(int i=1,j=chars.length; i<=j; i++){
            result.addAll(getCnm(i,chars));
        }
        return result;
    }
    
    /**
     * 组合2
     *
     * @return
     */
    public static List<String> getCnm2(int num, String str) {
        List<String> result = new ArrayList<String>();
        if (num == 1) {
            for (char c : str.toCharArray()) {
                result.add(String.valueOf(c));
            }
            return result;
        }
        if (num >= str.length()) {
            result.add(str);
            return result;
        }
        int strlen = str.length();
        for (int i = 0; i < (strlen - num + 1); i++) {
            // 递归求解 Cnm = C(n-1)(m-1)
            List<String> cr = getCnm2(num - 1, str.substring(i + 1));//从i+1处直至字符串末尾
            char c = str.charAt(i);//得到上面被去掉的字符，进行组合
            for (String s : cr) {
                result.add(c + s);
            }
        }
        return result;
    }
    
    public static List<List<String>> getAnm(int n , String collections){
        String realCollections[] = new String[collections.length()];
        int index = 0;
        for(char c : collections.toCharArray()){
            realCollections[index++] = String.valueOf(c);
        }
        return getAnm(n,realCollections,new int[realCollections.length]);
    }
    
    /**
     * 排列
     * @return
     */
    public static List<List<String>> getAnm(int n , String[] collections){
        return getAnm(n,collections,new int[collections.length]);
    }
    
    private static List<List<String>> getAnm(int n, String[] collections, int[] isPointed) {
        List<List<String>> result = new ArrayList();
        int i = 0;
        int len = collections.length;
        if( n <= 1){
            for(; i<len ; i++){
                if(isPointed[i] == 0){
                    List list = new ArrayList();
                    list.add(collections[i]);
                    result.add(list);
                }
            }
            return result;
        }
        
        for(; i<len ; i++){
            if(isPointed[i] == 0){
                isPointed[i] = 1;
                List<List<String>> anm = getAnm(n - 1, collections, isPointed);
                for(List<String> one : anm){
                    one.add(collections[i]);
                    result.add(one);
                }
                isPointed[i] = 0;
            }
        }
        
        return result;
    }
    
    @Test
    public void testCnm(){
        List result = getCnm(3,"1,2,3,4,5,6,7,8,9,10".split(","));
        System.out.println(result.size());
        System.out.println(result);
    }
    
    @Test
    public void testAnm(){
        List result = getAnm(2,"1,2".split(","));
        result = getAnm(4, "1234".split(""));
        System.out.println(result.size());
        System.out.println(result);
    }
    
    
    @Test
    public void testCombineResult(){
        testOther();
        testOther();
        testMy();
        System.out.println("正式开始====>");
        testOther();
        testMy();
    }
    
    @PrintLog(count = 1000000)
    private void testOther() {
        String str = UUID.randomUUID().toString().substring(0,12);
        int len = RandomUtils.nextInt(0,str.length());
        getCnm(len,str);
    }
    
    @PrintLog(count = 1000000)
    private void testMy() {
        String str = UUID.randomUUID().toString().substring(0,12);
        int len = RandomUtils.nextInt(0,str.length());
        getCnm(len,str);
    }
    
    @Test
    public void testPowerSet(){
        List<List<String>> lists = powerSet(new String[]{"k1", "k2", "k3", "k4", "k5"});
        System.out.println(lists.size());
        System.out.println(lists);
    }
    
}
