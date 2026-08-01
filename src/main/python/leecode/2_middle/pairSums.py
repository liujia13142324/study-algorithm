# 面试题 16.24. 数对和
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
#
# 示例 1：
#
# 输入：nums = [5,6,5], target = 11
# 输出：[[5,6]]
# 示例 2：
#
# 输入：nums = [5,6,5,6], target = 11
# 输出：[[5,6],[5,6]]
# 提示：
#
# nums.length <= 100000
# -105 <= nums[i], target <= 105
from typing import List


class Solution:
    def pairSums(self, nums: List[int], target: int) -> List[List[int]]:
        mapping = [0] * 200001
        ans = []
        for num in nums:
            if mapping[target - num + 100000] > 0:
                mapping[target - num + 100000] -= 1
                ans.append([num, target - num])
            else:
                mapping[num + 100000] += 1
        return ans