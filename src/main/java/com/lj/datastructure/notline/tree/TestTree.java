package com.lj.datastructure.notline.tree;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class TestTree {
    
    
    @Test
    public void testCompress(){
        String str = "i like like like java do you like a java";
        byte[] originBytes = str.getBytes();
        String binaryStr = "";
        for(byte b : originBytes){
            /**
             * "105 32 108 105 107 101 32 108 105 107 101 32 108 105 107 101 32 106 97 118 97 32 100 111 32 121 111 117 32 108 105 107 101 32 97 32 106 97 118 97"
             * 这样的形式，每个字节之间有还要添加一个空格
             */
            String t = Integer.toBinaryString(b);
            while(t.length()<8){
                t = "0"+t;
            }
            binaryStr += t+" ";
        }
        binaryStr = binaryStr.substring(0,binaryStr.length()-1);
        System.out.println(binaryStr.length());
        System.out.println(binaryStr);
    }
    
    /**
     * 01101001 00100000 01101100 01101001 01101011 01100101 00100000 01101100 01101001 01101011 01100101 00100000 01101100 01101001 01101011 01100101 00100000 01101010 01100001 01110110 01100001 00100000 01100100 01101111 00100000 01111001 01101111 01110101 00100000 01101100 01101001 01101011 01100101 00100000 01100001 00100000 01101010 01100001 01110110 01100001
     */
    @Test
    public void testBinaryStr2Byte(){
        System.out.println(Integer.toBinaryString(-105));
        System.out.println(Integer.toBinaryString(105));
        System.out.println(Integer.parseInt("110010111",2));
        
    }
    
    @Test
    public void testMinus(){
        System.out.println(Integer.toBinaryString(-12));
    }
    
    @Test
    public void testDepressAndCompress() throws IllegalAccessException, InstantiationException {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("origin:"+str);
        System.out.println("origin length:"+bytes.length);
        System.out.println("origin bts:"+Arrays.toString(bytes));
        HoffManTree<Byte> byteHoffManTree = HoffManTree.byteHoffManTree(bytes);
        byteHoffManTree.showCodes();
        Map<Byte, String> codes = byteHoffManTree.getCodes();
        byte[] compressResult = HoffManTree.compress(bytes,codes);
        System.out.println("compressed length:"+compressResult.length);
        System.out.println("compressed bts:"+Arrays.toString(compressResult));
        byte[] decompress = HoffManTree.decompress(compressResult,codes);
        System.out.println("depressed length:"+decompress.length);
        System.out.println("depressed bts:"+Arrays.toString(decompress));
        System.out.println("depressed result:"+new String(decompress));
    }
    
    @Test
    public void testCompressFile() throws IllegalAccessException, InstantiationException {
        String absolute = "C:\\Users\\l\\Pictures\\Feedback\\{9311C712-2966-4E7A-9A08-07B3D96F238B}\\Capture001.png";
        int lastSplit = absolute.lastIndexOf("\\");
        String directory = absolute.substring(0,lastSplit+1);
        byte[] bytes = FileUtil.readBytes(new File(absolute));
        HoffManTree<Byte> byteHoffManTree = HoffManTree.byteHoffManTree(bytes);
        byteHoffManTree.showCodes();
        Map<Byte, String> codes = byteHoffManTree.getCodes();
        byte[] compressResult = HoffManTree.compress(bytes,codes);
        FileUtil.writeBytes(compressResult,absolute+".haffman");
        System.out.println("origin size:"+bytes.length);
        System.out.println("compress size:"+compressResult.length);
        // 把霍夫曼编码也写出去
        try (
                FileOutputStream codeOut = new FileOutputStream(directory+"codes");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(codeOut)
        ){
            objectOutputStream.writeObject(codes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDepressFile()  {
        String absolute = "C:\\Users\\l\\Pictures\\Feedback\\{9311C712-2966-4E7A-9A08-07B3D96F238B}\\Capture001.png";
        int lastSplit = absolute.lastIndexOf("\\");
        String fileName = absolute.substring(lastSplit+1);
        String directory = absolute.substring(0,lastSplit+1);;
        byte[] bytes = FileUtil.readBytes(new File(absolute+".haffman"));
        Map<Byte,String> codes = null;
        try (
                FileInputStream codeIn = new FileInputStream(directory+"codes");
                ObjectInputStream objectOutputStream = new ObjectInputStream(codeIn)
        ){
            codes = (Map<Byte, String>) objectOutputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
        byte[] decompressResult = HoffManTree.decompress(bytes,codes);
        FileUtil.writeBytes(decompressResult,directory+"decompressed_"+fileName);
        System.out.println("compress size:"+bytes.length);
        System.out.println("decompress size:"+decompressResult.length);
    }
}
