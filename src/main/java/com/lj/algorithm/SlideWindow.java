package com.lj.algorithm;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class SlideWindow {
    
    ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
    int timeSplit = 5;
    TimeUnit timeUnit = TimeUnit.SECONDS;
    LongAdder exceptionCount = new LongAdder();
    int circuitBreakCount = 200;
    
    volatile boolean exit = false;
    
    @Test
    public void testFixWindowNormal() throws InterruptedException {
        
        scheduleTask();
        
        int i = 20;
        // 每 1000 醒来，添加 i 个报错
        long sleepTime = timeUnit.toMillis(timeSplit) / 5;
        
        // 会正常熔断
        while (!exit) {
            exceptionCount.add(i);
            Thread.sleep(sleepTime);
            i++;
        }
    }
    
    
    @Test
    public void testFixWindowAbnormal() throws InterruptedException {
        
        scheduleTask();
        
        int start = 0;
        
        // 不会正常熔断，卡着固定时间窗口的边界写的值
        while (!exit) {
            if (start == 0) {
                start = 1;
                Thread.sleep(timeUnit.toMillis(timeSplit) - 20);
                exceptionCount.add(190);
                Thread.sleep(100);
                exceptionCount.add(190);
            } else {
                Thread.sleep(timeUnit.toMillis(timeSplit) + 100);
                exceptionCount.add(190);
            }
        }
    }
    
    @Test
    public void testFixWindowNormal2() throws Exception {
    
        FixWindow fixWindow = new FixWindow(500, 200);
        
        // 会正常熔断
        exit = !fixWindow.add(190);
        Thread.sleep(199);
        exit = !fixWindow.add(190);
    }
    
    
    @Test
    public void testFixWindowAbnormal2() throws Exception {
    
        FixWindow fixWindow = new FixWindow(500, 200);
    
        //
        for (int i = 0 ; i < 100; i++) {
            if (i % 2 == 0) {
                Thread.sleep( 300);
                // 下面 200 毫秒内发生异常，但是没有熔断
                exit = !fixWindow.add(190);
                Thread.sleep(199);
                exit = !fixWindow.add(190);
            }else {
                Thread.sleep(500);
            }
        }
        
    }
    
    
    @Test
    public void testSlideWindowNormal() throws Exception {
    
        SlidingWindow window = new SlidingWindow(100, 5, 200);
    
        window.addCount(0);
        Thread.sleep( 300);
        window.addCount(190);
        Thread.sleep(199);
        window.addCount(190);
    }
    
    SlidingWindow window = new SlidingWindow(100, 4, 8);
    
    @Test
    public void testSlideWindow() throws Exception {
    
    
        SlidingWindow window = new SlidingWindow(100, 4, 8);
//        SlidingWindow window = new SlidingWindow(100, 4, 9);
        for (int i = 0; i < 100; i++) {
            System.out.println(window.addCount(2));
        
            window.print();
            System.out.println("--------------------------");
            try {
                Thread.sleep(102);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void scheduleTask() {
        
        schedule.schedule(()->{
            
            long ecounts = exceptionCount.sumThenReset();
            System.out.println(DateTime.now() + "exceptionCounts : " + ecounts);
            
            if (ecounts > 200) {
                System.out.println("熔断！");
                exit = true;
                return;
            }
            
            scheduleTask();
            
        }, timeSplit, timeUnit);
        
    }
    
    public class FixWindow{
        long lastUpdateTime;
        int count;
        long timeSplitMilli;
        int threshold;
    
        FixWindow(long timeSplitMilli, int threshold){
            this.timeSplitMilli = timeSplitMilli;
            this.threshold = threshold;
            lastUpdateTime = System.currentTimeMillis();
        }
        
        boolean add(int count) throws Exception {
            long tmp;
            if ((tmp=System.currentTimeMillis() - lastUpdateTime) > timeSplitMilli) {
                lastUpdateTime += (long) Math.ceil((tmp-timeSplitMilli)/(double)timeSplitMilli) * timeSplitMilli;
                this.count = 0;
            }
            
            this.count += count;
            System.out.println("count: " + this.count);
            
            if (this.count >= threshold) {
                throw new Exception("overflow!");
            }
            
            return true;
        }
    }
 
    
    public class SlidingWindow {
        /**
         * 循环队列，就是装多个窗口用，该数量是spliceCount的2倍
         */
        private AtomicInteger[] timeSlices;
        /**
         * 队列的总长度
         */
        private int timeSliceSize;
        /**
         * 每个时间片的时长，以毫秒为单位
         */
        private int timeMillisPerSlice;
        /**
         * 共有多少个时间片,一个完整窗口 windowSize = timeMillisPerSlice * spliceCount
         */
        private int spliceCount;
        /**
         * 在一个完整窗口期内允许通过的最大阈值
         */
        private int threshold;
        /**
         * 该滑窗的起始创建时间，也就是第一个数据
         */
        private long beginTimestamp;
        /**
         * 最后一个数据的时间戳
         */
        private long lastAddTimestamp;
    
    
        public SlidingWindow(int duration, int threshold) {
            //超过10分钟的按10分钟
            if (duration > 600) {
                duration = 600;
            }
            //要求5秒内探测出来的，
            if (duration <= 5) {
                this.spliceCount = 5;
                this.timeMillisPerSlice = duration * 200;
            } else {
                this.spliceCount = 10;
                this.timeMillisPerSlice = duration * 100;
            }
            this.threshold = threshold;
            // 保证存储在至少两个window
            this.timeSliceSize = spliceCount * 2;
        
            reset();
        }
    
        public SlidingWindow(int timeMillisPerSlice, int spliceCount, int threshold) {
            this.timeMillisPerSlice = timeMillisPerSlice;
            this.spliceCount = spliceCount;
            this.threshold = threshold;
            // 保证存储在至少两个window
            this.timeSliceSize = spliceCount * 2;
        
            reset();
        }
    
        /**
         * 初始化
         */
        private void reset() {
            beginTimestamp = SystemClock.now();
            //窗口个数
            AtomicInteger[] localTimeSlices = new AtomicInteger[timeSliceSize];
            for (int i = 0; i < timeSliceSize; i++) {
                localTimeSlices[i] = new AtomicInteger(0);
            }
            timeSlices = localTimeSlices;
        }
    
        private void print() {
            for (AtomicInteger integer : timeSlices) {
                System.out.print(integer + "-");
            }
        }
    
        /**
         * 计算当前所在的时间片的位置
         */
        private int locationIndex() {
            long now = SystemClock.now();
            //如果当前的key已经超出一整个时间片了，那么就直接初始化就行了，不用去计算了
            if (now - lastAddTimestamp > timeMillisPerSlice * spliceCount) {
                reset();
            }
    
            return (int) (((now - beginTimestamp) / timeMillisPerSlice) % timeSliceSize);
        }
    
        /**
         * 增加count个数量
         */
        public boolean addCount(int count) throws Exception {
            //当前自己所在的位置，是哪个小时间窗
            int index = locationIndex();
//        System.out.println("index:" + index);
            //然后清空自己前面spliceCount到2*spliceCount之间的数据格的数据
            //譬如1秒分4个窗口，那么数组共计8个窗口
            //当前index为5时，就清空6、7、8、1。然后把2、3、4、5的加起来就是该窗口内的总和
            clearFromIndex(index);
    
            int sum = 0;
            // 在当前时间片里继续+1
            sum += timeSlices[index].addAndGet(count);
            //加上前面几个时间片
            for (int i = 1; i < spliceCount; i++) {
                sum += timeSlices[(index - i + timeSliceSize) % timeSliceSize].get();
            }
            System.out.println(sum + "---" + threshold);
    
            lastAddTimestamp = SystemClock.now();
    
            if (sum >= threshold) {
                print();
                throw new Exception("overflow..");
            }
            
            return true;
        }
    
        private void clearFromIndex(int index) {
            for (int i = 1; i <= spliceCount; i++) {
                int j = index + i;
                if (j >= spliceCount * 2) {
                    j -= spliceCount * 2;
                }
                timeSlices[j].set(0);
            }
        }
    
    
    }

}
