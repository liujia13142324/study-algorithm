# 2342. 数位和相等数对的最大和
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
#
# 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值。如果不存在这样的下标对，返回 -1。
#
#
#
# 示例 1：
#
# 输入：nums = [18,43,36,13,7]
# 输出：54
# 解释：满足条件的数对 (i, j) 为：
# - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
# - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
# 所以可以获得的最大和是 54 。
# 示例 2：
#
# 输入：nums = [10,12,19,14]
# 输出：-1
# 解释：不存在满足条件的数对，返回 -1 。
#
#
# 提示：
#
# 1 <= nums.length <= 105
# 1 <= nums[i] <= 109
from cmath import inf
from typing import List


class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        def get_bit_sum(num: int):
            ans = 0
            while num > 0:
                ans += (num % 10)
                num = num // 10
            return ans
        mapping = [-inf] * 82
        ans = -1
        for num in nums:
            sums = get_bit_sum(num)
            ans = max(ans, mapping[sums] + num)
            mapping[sums] = max(mapping[sums], num)
        return ans

    def maximumSum(self, nums: List[int]) -> int:
        mapping = [-inf] * 82
        ans = -1
        for num in nums:
            sums = sum(map(int, str(num)))
            ans = max(ans, mapping[sums] + num)
            mapping[sums] = max(mapping[sums], num)
        return ans

if __name__ == '__main__':
    print(Solution().maximumSum([18,43,36,13,7]))