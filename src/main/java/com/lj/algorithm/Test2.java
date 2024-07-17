package com.lj.algorithm;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 *  1.在线编程题：共计9个苹果，有2只猴子，一个猴子每次拿2个苹果，一个猴子每次拿3个苹果，如果剩余的苹果不够猴子每次拿的数量，则2只猴子停止拿苹果，请用java多线程模拟上面的描述。
 */
public class Test2 {
    
    
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger total = new AtomicInteger(9);
        // 一次性拿两个的猴子
        new Thread(new Monkey(total, 2)).start();
        // 一次性拿三个的猴子
        new Thread(new Monkey(total, 3)).start();
    }
    
    
    static class Monkey implements Runnable{
        
        AtomicInteger total;
        int every;
    
        public Monkey(AtomicInteger total, int every){
            this.total = total;
            this.every = every;
        }
        
        @Override
        public void run() {
            int tmp;
            while (every <= (tmp = total.get())) {
                if (total.compareAndSet(tmp, tmp - every)) {
                    // 测试用
                    //System.out.println(every);
                }
            }
        }
    }
    
    
    public String modify_dict(String dict, String key, String value){
        
        // 如果 dict 为空，直接拼接 key:value 返回
        if (dict == null || dict.equals("")) {
            return key + ":" + value;
        }

        int idx = -1;
        StringBuffer result = new StringBuffer();
        // 当 key 存在
        if ((idx = dict.indexOf(key+":")) >= 0) {
            // 追加内容 start ~ key:value
            result.append(dict, 0, idx + key.length() + 1).append(value);
            // 获取下一个key的下标，也就是下一个 ; 的下标
            int next = dict.indexOf(";", idx);
            // 当还有下一个key，继续追加后面的内容
            if (next >= 0) {
                result.append(dict.substring(next));
            }
        }else {
            // 当 key 不存在
            result.append(dict).append(";").append(key).append(":").append(value);
        }
        
        return result.toString();
    }
    
    @Test
    public void testss() {
        String dicts = "A:1;B:20;C:300";
        System.out.println(modify_dict(dicts, "A", "123"));
        System.out.println(modify_dict(dicts, "B", "xxx"));
        System.out.println(modify_dict(dicts, "C", "1"));
        System.out.println(modify_dict(dicts, "D", "k"));
    }
}
