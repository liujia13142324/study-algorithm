package com.lj.problem.leetcode._1;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleProblem {


    @Test
    public void testReorderSpace(){
        System.out.println(reorderSpaces("  this   is  a sentence "));
        System.out.println(reorderSpaces(" practice   makes   perfect"));
        System.out.println(reorderSpaces("hello   world"));
        System.out.println(reorderSpaces("  walks  udp package   into  bar a"));
        System.out.println(reorderSpaces("a"));
    }

    /**
     * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，
     * 并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
     *
     * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，
     * 请将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
     *
     * 返回 重新排列空格后的字符串 。
     *
     * 输入：text = "  this   is  a sentence "
     * 输出：       "this   is   a   sentence"
     * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
     *
     * 输入：text = " practice   makes   perfect"
     * 输出：       "practice   makes   perfect "
     * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
     *
     * 输入：text = "hello   world"
     * 输出：       "hello   world"
     *
     * 输入：text = "  walks  udp package   into  bar a"
     * 输出：       "walks  udp  package  into  bar  a "
     *
     * 输入：text = "a"
     * 输出："a"
     */
    public String reorderSpaces(String text) {

        List<String> normalLetters = new ArrayList<>();
        int countSpace = 0;
        int i,k;
        StringBuilder stringBuilder = null;
        char[] chars = text.toCharArray();

        for(i=0 ; i<chars.length; ){

            if(chars[i] == ' '){
                countSpace++;
                i++;
                continue;
            }
            stringBuilder = new StringBuilder();
            while( i<chars.length && chars[i] != ' ' ){
                stringBuilder.append( chars[i]  );
                i++;
            }

            normalLetters.add(stringBuilder.toString());
        }

        if(normalLetters.size() > 1){
            stringBuilder = new StringBuilder();
            int n = countSpace / (normalLetters.size()-1);
            int remain = countSpace%(normalLetters.size()-1);

            for(i=0; i<normalLetters.size()-1; i++ ){
                stringBuilder.append( normalLetters.get(i) );
                for(k=0; k<n; k++){
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append( normalLetters.get(normalLetters.size()-1) );

            for(k=0; k < remain; k++){
                stringBuilder.append(" ");
            }
        }else if(countSpace != 0){
            for(k=0; k < countSpace; k++){
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }




    /**
     * 每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。
     * 下面给出对变更操作的说明：
     *
     * "../" ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 继续停留在当前文件夹 。
     * "./" ：继续停留在当前文件夹。
     * "x/" ：移动到名为 x 的子文件夹中。题目数据 保证总是存在文件夹 x 。
     * 给你一个字符串列表 logs ，其中 logs[i] 是用户在 ith 步执行的操作。
     * 文件系统启动时位于主文件夹，然后执行 logs 中的操作。
     * 执行完所有变更文件夹操作后，请你找出 返回主文件夹所需的最小步数 。
     *
     * 输入：logs = ["d1/","d2/","../","d21/","./"]
     * 输出：2
     * 解释：执行 "../" 操作变更文件夹 2 次，即可回到主文件夹
     *
     * 输入：logs = ["d1/","d2/","./","d3/","../","d31/"]
     * 输出：3
     *
     * 输入：logs = ["d1/","../","../","../"]
     * 输出：0
     *
     * @param logs
     * @return
     */
    public int minOperations(String[] logs) {

        int result=0;
        for(int i=0;i<logs.length;i++){
            if("./".equals(logs[i])){
                continue;
            }else if("../".equals(logs[i])){
                if(result>0){
                    result-- ;
                    
                }
            }else{
                result++;
            }
        }

        return result;
    }


    /**
     *
     * 1608. 特殊数组的特征值
     * 给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
     *
     * 注意： x 不必 是 nums 的中的元素。
     *
     * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     *
     * 输入：nums = [3,5]
     * 输出：2
     * 解释：有 2 个元素（3 和 5）大于或等于 2 。
     *
     * 输入：nums = [0,0]
     * 输出：-1
     * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
     * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
     * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
     * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
     * x 不能取更大的值，因为 nums 中只有两个元素。
     *
     * 输入：nums = [0,4,3,0,4]
     * 输出：3
     * 解释：有 3 个元素大于或等于 3 。
     *
     * 输入：nums = [3,6,7,7,0]
     * 输出：-1
     *
     */
    @Test
    public void testSpecialArray(){
        System.out.println(specialArray(Arrays.stream("0,4,3,0,4".split(",")).mapToInt(e->Integer.parseInt(e)).toArray()));
        System.out.println(specialArray(Arrays.stream("3,6,7,7,0".split(",")).mapToInt(e->Integer.parseInt(e)).toArray()));
        System.out.println(specialArray(Arrays.stream("3,5".split(",")).mapToInt(e->Integer.parseInt(e)).toArray()));
    }


    public int specialArray(int[] nums) {
        int result = 0;
        Arrays.sort(nums);

        for( int i=nums.length-1; i>=0; i-- ){
            if(nums[i] <= result){
                if(nums[i] == result){
                    return -1;
                }
                return result;
            }else{
                result++;
            }
        }

        return result;
    }


    /**
     * 删除某些元素后的数组均值
     *
     * 给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
     * 与 标准答案 误差在 10-5 的结果都被视为正确结果。
     *
     *
     * 输入：arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
     * 输出：2.00000
     * 解释：删除数组中最大和最小的元素后，所有元素都等于 2，所以平均值为 2 。
     *
     * 输入：arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
     * 输出：4.00000
     *
     * 输入：arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
     * 输出：4.77778
     *
     * 输入：arr = [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
     * 输出：5.27778
     *
     * 输入：arr = [4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1]
     * 输出：5.29167
     *
     * 输入：
     * [77014,31380,71838,27969,33049,3898,67868,64702,53537,52816,17519,28838,59051,13230,73001,33827,32240,46354,8855,30273,39390,86556,50290,16477,99563,44257,7351,81523,63513,82380,29575,41828,51542,2780,54375,28881,64406,61289,80186,54848,39868,3835,57351,80742,87340,87345,52223,32533,79419,78700,53644,55640,77066,48359,87122,13696,64113,94355,37562,14690,4705,50581,88483,84764,69135,84169,91375,85633,15406,17388,25798,27145,96021,32339,92062,60871,46661,87201,31021,61525,58445,9696,57520,78762,24725,17911,9614,99570,81627,53031,61386,97294,30116,38049,80132,75757,11685,61802,21344,34914]
     * 输出：
     * 51779.58984
     * 预期结果：
     * 51779.58889
     *
     */
    @Test
    public void testTrimMean(){
        // 验证demo
        List<Integer> collect = Arrays.stream("6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4".split(","))
                .map(e -> Integer.parseInt(e)).collect(Collectors.toList());
        Collections.sort(collect);
        System.out.println(collect+"-->"+collect.size());
        int sum = Arrays.stream("0, 0, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9,".split(","))
                .mapToInt(e -> Integer.parseInt(e.trim())).sum();
        System.out.println(sum/36f);

        System.out.println(trimMean( Arrays.stream("77014,31380,71838,27969,33049,3898,67868,64702,53537,52816,17519,28838,59051,13230,73001,33827,32240,46354,8855,30273,39390,86556,50290,16477,99563,44257,7351,81523,63513,82380,29575,41828,51542,2780,54375,28881,64406,61289,80186,54848,39868,3835,57351,80742,87340,87345,52223,32533,79419,78700,53644,55640,77066,48359,87122,13696,64113,94355,37562,14690,4705,50581,88483,84764,69135,84169,91375,85633,15406,17388,25798,27145,96021,32339,92062,60871,46661,87201,31021,61525,58445,9696,57520,78762,24725,17911,9614,99570,81627,53031,61386,97294,30116,38049,80132,75757,11685,61802,21344,34914".split(","))
                .mapToInt(e->Integer.parseInt(e.trim())).toArray()));

    }

    public double trimMean(int[] arr) {
        int result = 0;
        Arrays.sort(arr);
        int gap = (int) (arr.length * 0.05);
        int end = arr.length-gap;

        for(int i=gap;i<end;i++){
            result+=arr[i];
        }

        return result/(arr.length-2d*gap);
    }

}
