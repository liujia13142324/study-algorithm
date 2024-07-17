package com.lj.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        
        
        // 初始化广播台信息
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        
        HashSet hashSetK1 = new HashSet();
        hashSetK1.add("北京");
        hashSetK1.add("上海");
        hashSetK1.add("天津");
        
        HashSet hashSetK2 = new HashSet();
        hashSetK2.add("广州");
        hashSetK2.add("北京");
        hashSetK2.add("深圳");
        
        HashSet hashSetK3 = new HashSet();
        hashSetK3.add("成都");
        hashSetK3.add("上海");
        hashSetK3.add("杭州");
        
        HashSet hashSetK4 = new HashSet();
        hashSetK4.add("上海");
        hashSetK4.add("天津");
        HashSet hashSetK5 = new HashSet();
        hashSetK5.add("杭州");
        hashSetK5.add("大连");
        
        
        
        
        broadcasts.put("K1", hashSetK1/*new HashSet(Arrays.asList(new String[] { "北京", "上海", "天津" }))*/);
        broadcasts.put("K2", hashSetK2/*new HashSet(Arrays.asList(new String[] { "广州", "北京", "深圳" }))*/);
        broadcasts.put("K3", hashSetK3/*new HashSet(Arrays.asList(new String[] { "成都", "上海", "杭州" }))*/);
        broadcasts.put("K4", hashSetK4/*new HashSet(Arrays.asList(new String[] { "上海", "天津" }))*/);
        broadcasts.put("K5", hashSetK5/*new HashSet(Arrays.asList(new String[] { "杭州", "大连" }))*/);
        
        // 需要覆盖的全部地区
        //HashSet<String> allAreas = new HashSet(
        //Arrays.asList(new String[] { "北京", "上海", "天津", "广州", "深圳", "成都", "杭州", "大连" }));
        
        
        HashSet<String> allAreas = new HashSet();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        
        
        // 所选择的广播台列表
        List<String> selects = new ArrayList<String>();
        
        HashSet<String> tempSet = new HashSet<String>();
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 求出2个集合的交集，此时tempSet会被赋值为交集的内容，所以使用临时变量
                tempSet.retainAll(allAreas);
                // 如果该集合包含的地区数量比原本的集合多
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("selects:" + selects);
    }
}
