package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 *
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 *
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 *      其中，每一个子串都由独特字符构成。
 *      所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 *
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 *
 * 输入：s = "LEETCODE"
 * 输出：92
 *
 */
public class GetSubStrUniqueLetterCount {

    @Test
    public void test1(){
        // 620286
//        System.out.println(uniqueLetterString("PNJBRGRPQSLSXPQBQKGGTUSHTKLLXHGXZBDMHNBZXKWZXTAANMHTOIRXHEYANOPLBVJROVZUDZNMETKKXRDMRHVMLDBHQTRIYGQFMBTZPPXGCLRJJYWHZZURDNTKWPNHLEJHFPPVQJAGSZCNUDMZWWCYUAQZGTUDMPJKLUQOSESLYGYWZKIXJQGHSOCVJQIGVXWQLOYUGFHCJSCJGHQMIGLGYAZWELSHZAPAEZQGMCMRMFRFZTTDGQUIZYDUCBVXZZUIDDCNWUAAPDUNZLBAGNIFNDBJYALQQGBRAMHBIVVERVXRTCSZSZWIGRLWZMUTEYSWZAGUDTPVLRJMOBUHOZBGHKHVOXAWCXMJNAZLQLKQQQNOCLUFGKOVBOKVKOEZEKNWHCFGCENVAABLPVTCEJVZNDTZNCRELHEDWLWIQGDBDGCTGUBZCZGTOVUFNCICJLWSMFDCRQEAGHUEVYEXQDHFFIKVECUAZRELOFJMYJJZNNJDKIMBKLRHSJUSBSTQHVLEJTJCCZQNZBVYFZXGAUDYOSCKYSMMINOANJMBAFHTNBRRZQAGLLWXLXMJANYFELMWRUFTLZUUHBSJEXOOBJKMYMLITIWJTDXSCOTZVZNVIXPDHNSXSODIEATIPIAODGCMDGYVZRJRVFCCMECCHXTIRAIQIJOWZWNRVRKOSIMQSDYRSCBONPPJTEYOEBNUOMRBIFRBQBTECLFQZTBBYROOMREHVFWTRVCODLLGJCTGUXEICJOUDMXBEVZRVRAVKIDNRICWSBNXMXVDCKZAHMQZBRLQUGTMJVOQBXARMLGJEQCORHNODVNOQFOMDPKRCOQOUDCPEOCHKHNHDGHBBYJIIYRVPKVSDYDIWOWCTGDZEHQAFDSZHJKTAYAYQCFVNAJQUQUQGFTPTRYAMLLXNSYSISFBEFNTDJWHZBWRVKRIHQXUOFBHRJKRIGJLDKRMUJQWAJGUFKSQEKMFAIJZSJIOTMCIVROAOEQPOIUD"));
        System.out.println(uniqueLetterString("LEETCODE"));
    }

    /**
     * 暴力
     * @param s
     * @return
     */
    /*@PrintLog
    public int uniqueLetterString(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        int len = s.length();
        Map<Character,Byte> counterMap = new HashMap<>(len);
        Map<Character,Byte> removeMap = new HashMap<>(len);

        int i;
        int j;
        int k;

        // 子串从 1->len
        for(int l=0; l<len; l++){
            for(i=0,j=i+l; i<len-l; i++,j++){
                counterMap.clear();
                removeMap.clear();
                for(k=i; k<=j; k++){
//                    System.out.print(chars[k]);
                    if(counterMap.containsKey(chars[k])){
                        counterMap.remove(chars[k]);
                        removeMap.put(chars[k],null);
                    }else if(!removeMap.containsKey(chars[k])){
                        counterMap.put(chars[k],null);
                    }
                }
//                System.out.println();
                result += counterMap.size();
            }
        }
        return result;
    }*/


    /**
     * 分别计算每个字符的贡献度，即计算有多少个子串仅包含该字符一次，可以通过一个实例来分析计算共识
     * 01234567
     * LEETCOED  (故意把E放在前面，方便推导)
     * (i-k) * (j-i)   j=下次该字符出现的位置，如果没下次，那么就等于length；k=上次该字符出现的位置，如果没上次，就等于-1
     *
     * L: 0     1*8=8
     * E: 1,2,6 2+1*4+4*2=14
     * T: 3     4*5=20
     * C: 4     5*4=20
     * O: 5     6*3=18
     * D: 7     8*1=8
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {

        int result = 0;
        // 预处理
        Map<Character, List<Integer>> preHandle = new HashMap<>();
        List<Integer> positions;
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if( (positions = preHandle.get(chars[i]))== null){
                positions = new ArrayList<>();
                preHandle.put(chars[i],positions);
            }
            positions.add(i);
        }

        // 计算
        for(List<Integer> position: preHandle.values()){
            for( int i=0; i<position.size(); i++ ){
                // (i-k) * (j-i)
                result += (position.get(i) - ((i-1>=0)?position.get(i-1):-1))
                        * (((i+1)>=position.size() ? chars.length : position.get(i+1)) - position.get(i)) ;
            }
        }

        return result;
    }
}
