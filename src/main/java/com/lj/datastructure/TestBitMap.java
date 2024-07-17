package com.lj.datastructure;

import cn.hutool.bloomfilter.bitMap.LongMap;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class TestBitMap {
    
    
    
    @Test
    public void testBitMap() throws IntrospectionException, NoSuchFieldException, IllegalAccessException {
    
        LongMap longMap = new LongMap(10);
        Field longsField = LongMap.class.getDeclaredField("longs");
        longsField.setAccessible(true);
        long[] longs = (long[]) longsField.get(longMap);
        System.out.println(Arrays.toString(longs));
        longMap.add(0);
        System.out.println(Arrays.toString(longs));
        longMap.add(1);
        System.out.println(Arrays.toString(longs));
        longMap.add(2);
        System.out.println(Arrays.toString(longs));
    
    }
    
    
    
    @Test
    public void testAdd(){
        /**
         * int r = (int) (i / BitMap.MACHINE64);
         * 		long c = i & (BitMap.MACHINE64 - 1);
         * 		longs[r] = longs[r] | (1L << c);
         */
        
        long test = 1L;
    
        System.out.println( test/64 );
        System.out.println( test&63 );
        
    }
    
    
    
    
    
}
