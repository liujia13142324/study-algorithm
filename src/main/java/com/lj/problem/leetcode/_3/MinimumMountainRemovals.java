package com.lj.problem.leetcode._3;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1671. 得到山形数组的最少删除次数
 * 提示
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 *
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
 *
 * 示例 1：
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 *
 * 示例 2：
 * 输入：nums = [2,1,1,5,6,2,3,1]
 *
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 *
 * 提示：
 *
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * 题目保证 nums 删除一些元素后一定能得到山形数组。
 *
 * 通过次数
 * 22,727/45.2K
 * 通过率
 * 50.2%
 */
public class MinimumMountainRemovals {

    @Test
    public void test() {
//        System.out.println(minimumMountainRemovals(new int[]{1,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{2,1,1,5,6,2,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{4,3,2,1,1,2,3,1}));
//        System.out.println(minimumMountainRemovals(new int[]{1,2,3,4,4,3,2,1}));
//        System.out.println(minimumMountainRemovals(new int[]{2,9,19,45,41,96,72,40,100,37,36,13,7}));
//        System.out.println(minimumMountainRemovals(new int[]{1,3,3,3,3,2,1}));
//        System.out.println(minimumMountainRemovals(new int[]{4,5,13,17,1,7,6,11,2,8,10,15,3,9,12,14,16}));
//        System.out.println(minimumMountainRemovals(new int[]{724906216,172493908,932988237,701825358,614594743,661804477,451395882,925480930,191202933,55220489,342843585,162273090,121020019,13032983,121374302,422355598,434507099,400358908,544925912,777097488,233640334,934196010,984677835,944581539,213181219,253049123,304102010,492097245,615900484,584532697,288320843,300354261,635260828,55121841,432189331,331657950,349655435,802471360,474313956,313858367,424522807,495008298,59201642,664431112,726435922,487186125,547847259,490855496,885826364,496503822,38486359,593690511,502281316,506441555,350582377,735329754,436422720,435269046,251832311,430500663,816586458,205901929,403257615,341553668,732464117,360453324,551303811,91623045,591546967,704234558,951715253,481336106,662582235,716194990,738029745,871546854,177355301,536487115,85538730,937368843,70470491,194455444,908571565,31120310,663044003,681903935,769538117,870076729,915979496,125040558,966858710,677269408,681096697,522371039,705411621,579612767,66333184,891994967,647253204,879526390,617754708,198600729,741196105,229005999,657686669,976282292,267540855,971734007,556194333,319299662,571408810,319675449,476527277,941962679,153976863,800586543,432904731,879101636,873255236,133709778,892394028,373072963,470933521,855025984,649606605,872389657,503256311,783346057,252799173,857785067,819484876,954743966,414201558,569212940,409926546,738466683,292930708,771853606,752151148,871700534,513953607,554470576,876772460,232777750,810053433,437658290,713494490,389666534,369435654,871441228,252124948,368387292,209518729,78578068,200168654,794253765,83061456,618357768,167666569,33671432,739868293,729866277,834919519}));
        System.out.println(minimumMountainRemovals(new int[]{1,2,3,4,3,4,3,2,1}));
    }

    public int minimumMountainRemovals(int[] nums) {
        int[] dp = new int[nums.length];

        int[] ans1 = new int[nums.length];
        int[] peak1 = new int[nums.length];
        int[] ans2 = new int[nums.length];
        int[] peak2 = new int[nums.length];

        int len = nums.length - 1;
        int idx1 = 0;
        int idx2 = len;
        int tmp;

        for (int i = 0; i < nums.length; i++) {
            tmp = find1(-1, idx1, nums[i], dp);
            if (tmp == idx1) {
                dp[idx1++] = nums[i];
                peak1[i] = nums[i];
            }else {
                dp[tmp] = nums[i];
                peak1[i] = peak1[i - 1];
            }
//            ans1[i] = tmp + 1;
            ans1[i] = idx1;
        }

//        Arrays.fill(dp, 0);
        for (int j = len; j >= 0; j--) {
            tmp = find2(idx2, nums.length, nums[j], dp);
            if (tmp == idx2) {
                dp[idx2--] = nums[j];
                peak2[j] = nums[j];
            }else {
                dp[tmp] = nums[j];
                peak2[j] = peak2[j + 1];
            }
//            ans2[j] = nums.length - tmp;
            ans2[j] = len - idx2;
        }

        int max = 3;
        for (int i = 0; i < nums.length - 1; i++) {
            // 1 不能做峰顶，
            // 左右峰顶相等则减去1
            if (ans1[i] == 1) {
                if (nums[i] < peak2[i + 1]) max = Math.max(max, ans1[i] + ans2[i + 1]);
            }else if (ans2[i + 1] == 1) {
                if (peak1[i] > nums[i + 1]) max = Math.max(max, ans1[i] + ans2[i + 1]);
            }else if (peak1[i] == peak2[i + 1]) {
                max = Math.max(max, ans1[i] + ans2[i + 1] - 1);
            }else {
                max = Math.max(max, ans1[i] + ans2[i + 1]);
            }
        }

        return nums.length - max;
    }

    private int find1(int l, int r, int target, int[] nums) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            }else {
                l = mid;
            }
        }
        return r;
    }

    private int find2(int l, int r, int target, int[] nums) {
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                l = mid;
            }else {
                r = mid;
            }
        }
        return l;
    }

}
