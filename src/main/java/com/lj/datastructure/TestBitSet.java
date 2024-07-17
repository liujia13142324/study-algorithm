package com.lj.datastructure;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

public class TestBitSet {
    
    
    @Test
    public void test1(){
        byte[] bts = {12, -24, 23};
        for (byte b : bts){
            System.out.println(Integer.toBinaryString(b));
        }
        BitSet bitSet = BitSet.valueOf(bts);
        System.out.println(bitSet.length());
        System.out.println(Arrays.toString(bitSet.toByteArray()));
        for(int i=0 ; i<bitSet.length() ; i++){
            System.out.print(bitSet.get(i) ? "1" :"0");
        }
        System.out.println();
    }
    
}
