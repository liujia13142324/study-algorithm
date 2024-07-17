package com.lj.problem.hwod;

import java.util.ArrayList;
import java.util.List;

/**
 * 1)出拳形状之间的胜负规则如下:A>B:B>C;C>A“>"左边一个字母，表示相对优势形状。 右边一个字母，表示相对劣势形状,
 * 2)当本场次中有且仅有一种出拳形状优于其它出拳形状，则该形状的玩家是胜利者， 否则认为是平局，例如1 三个玩家出举分别是 A,B,C。由于出现三方优势循环(即没有任何一方优于其它出拳者),判断为平局,例如2 三个玩家，出拳分别是 A,B。出拳A的获胜。例如3 三个玩家，出拳全部是 A。 判为平局
 * 3)当发生平局，没有赢家。有多个胜利者时，同为真家。
 
 * 输入描述:在一场游戏中，每个玩家的信息为一行。玩家数量不超过1000。玩家信息2个字段，用空格隔开:
 *  1)玩家ID:一个仅由英文字母和数字组成的字符串。
 *  2)出拳形状:以英文大写字母表示，A、B、C形状,
 *  3)出拳时间:正整数，越小表示时间越早
 *
 * 例如
 * abcl A
 * Xyz B
 * 解释:玩家abc1 出拳为石头(A)。玩家 xyz 出拳为剪刀(B)
 * 输出描述:输出为贏家的玩家ID列表(一个或多个),每个D一行,技字符串升序排列。如果没有贏家,输出为“NULL”字符串。
 * 例如:
 * abc1
 *
 * 示例1
 * 输入:abclA
 * Xyz B
 * 输出:abcl
 * 说明:A比B有优势，abc1胜出
 *
 * 示例2
 * 输入:abcl A
 * Xyz A
 * 输出:NULL
 * 说明:没有优胜的出拳形状，平局
 *
 * 示例3
 * 输入:abcl A
 * def A
 * alic A
 * Xyz B
 * 输出:abc1
 * alic
 * def
 * 说明:A为优胜方，有三个赢家
 */
public class FingerGuess {

    public String solve(String[] input) {
    
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();
        int typeCount = 0;
        
        
        for (int i=0; i<input.length; i++) {
            
            String[] tmp = input[i].split(" ");
            
            if (tmp[1].equals("A")) {
                a.add(tmp[0]);
                if (a.size() == 1) {
                    typeCount++;
                }
            }else if (tmp[1].equals("B")) {
                b.add(tmp[0]);
                if (b.size() == 1) {
                    typeCount++;
                }
            }else {
                c.add(tmp[0]);
                if (c.size() == 1) {
                    typeCount++;
                }
            }
            
            if (typeCount >= 3) {
                return null;
            }
        }
        
        if (typeCount == 2) {
            if (a.size() > 0 && b.size() > 0) {
                return String.join("\n", a.toArray(new String[a.size()]));
            }else if (a.size() > 0 && c.size() > 0) {
                return String.join("\n", a.toArray(new String[c.size()]));
            }else {
                return String.join("\n", a.toArray(new String[b.size()]));
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(new FingerGuess().solve(
                new String[] {"abcl A", "def A", "alic A", "Xyz B"}
        ) + "\n");
        
        System.out.println(new FingerGuess().solve(
                new String[] {"abcl A", "def A"}
        ) + "\n");
    
        System.out.println(new FingerGuess().solve(
                new String[] {"abcl A", "def A", "alic C", "Xyz B"}
        ) + "\n");
    }

}
