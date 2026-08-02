# 1749. 任意子数组和的绝对值的最大值
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
#
# 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
#
# abs(x) 定义如下：
#
# 如果 x 是负整数，那么 abs(x) = -x 。
# 如果 x 是非负整数，那么 abs(x) = x 。
#
#
# 示例 1：
#
# 输入：nums = [1,-3,2,3,-4]
# 输出：5
# 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
# 示例 2：
#
# 输入：nums = [2,-5,1,-4,3,-2]
# 输出：8
# 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
#
#
# 提示：
#
# 1 <= nums.length <= 105
# -104 <= nums[i] <= 104
from itertools import accumulate
from typing import List


class Solution:
    def maxAbsoluteSum(self, nums: List[int]) -> int:
        ans = 0
        min_ = 0
        max_ = 0
        for num in nums:
            max_ = max(max_, 0) + num
            min_ = min(min_, 0) + num
            ans = max(ans, max(max_, abs(min_)))
        return ans

    def maxAbsoluteSum(self, nums: List[int]) -> int:
        min_ = 0
        max_ = 0
        sums = 0
        for num in nums:
            sums += num
            max_ = max(max_, sums)
            min_ = min(min_, sums)
        return max_ - min_

    def maxAbsoluteSum(self, nums: List[int]) -> int:
        pre_sums = list(accumulate(nums, initial=0))
        return max(pre_sums) - min(pre_sums)



if __name__ == '__main__':
    pre_sums = list(accumulate([2,-5,1,-4,3,-2], initial=0))
    print(pre_sums)
    pre_sums = list(accumulate([2,-5,1,-4,3,-2], initial=1))
    print(pre_sums)
