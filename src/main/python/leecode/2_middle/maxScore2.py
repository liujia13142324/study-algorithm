from typing import List


# 2587. 重排数组以得到最大前缀分数
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个下标从 0 开始的整数数组 nums 。你可以将 nums 中的元素按 任意顺序 重排（包括给定顺序）。
#
# 令 prefix 为一个数组，它包含了 nums 重新排列后的前缀和。换句话说，prefix[i] 是 nums 重新排列后下标从 0 到 i 的元素之和。nums 的 分数 是 prefix 数组中正整数的个数。
#
# 返回可以得到的最大分数。
#
#
#
# 示例 1：
#
# 输入：nums = [2,-1,0,1,-3,3,-3]
# 输出：6
# 解释：数组重排为 nums = [2,3,1,-1,-3,0,-3] 。
# prefix = [2,5,6,5,2,2,-1] ，分数为 6 。
# 可以证明 6 是能够得到的最大分数。
# 示例 2：
#
# 输入：nums = [-2,-3,0]
# 输出：0
# 解释：不管怎么重排数组得到的分数都是 0 。
#
#
# 提示：
#
# 1 <= nums.length <= 105
# -106 <= nums[i] <= 106

class Solution:
    def maxScore(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        sums = 0
        for i, num in enumerate(nums):
            sums += num
            if sums <= 0:
                return i - 1
        return len(nums)


if __name__ == '__main__':
    print(Solution().maxScore([2,-1,0,1,-3,3,-3]))