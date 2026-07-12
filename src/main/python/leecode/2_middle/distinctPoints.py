# 3694. 删除子字符串后不同的终点
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# 给你一个由字符 'U'、'D'、'L' 和 'R' 组成的字符串 s，表示在无限的二维笛卡尔网格上的移动。
#
# 'U': 从 (x, y) 移动到 (x, y + 1)。
# 'D': 从 (x, y) 移动到 (x, y - 1)。
# 'L': 从 (x, y) 移动到 (x - 1, y)。
# 'R': 从 (x, y) 移动到 (x + 1, y)。
# 你还得到了一个正整数 k。
#
# 你 必须 选择并移除 恰好一个 长度为 k 的连续子字符串 s。然后，从坐标 (0, 0) 开始，按顺序执行剩余的移动。
#
# 返回可到达的 不同 最终坐标的数量。
#
#
#
# 示例 1:
#
# 输入：s = "LUL", k = 1
#
# 输出：2
#
# 解释：
#
# 移除长度为 1 的子字符串后，s 可以是 "UL"、"LL" 或 "LU"。执行这些移动后，最终坐标将分别是 (-1, 1)、(-2, 0) 和 (-1, 1)。有两个不同的点 (-1, 1) 和 (-2, 0)，因此答案是 2。
#
# 示例 2:
#
# 输入：s = "UDLR", k = 4
#
# 输出：1
#
# 解释：
#
# 移除长度为 4 的子字符串后，s 只能是空字符串。最终坐标将是 (0, 0)。只有一个不同的点 (0, 0)，因此答案是 1。
#
# 示例 3:
#
# 输入：s = "UU", k = 1
#
# 输出：1
#
# 解释：
#
# 移除长度为 1 的子字符串后，s 变为 "U"，它总是以 (0, 1) 结束，因此只有一个不同的最终坐标。
#
#
#
# 提示:
#
# 1 <= s.length <= 105
# s 只包含 'U'、'D'、'L' 和 'R'。
# 1 <= k <= s.length
UDLR = {
    'U': [0, 1],
    'D': [0, -1],
    'L': [-1, 0],
    'R': [1, 0]
}

class Solution:

    def distinctPoints(self, s: str, k: int) -> int:
        n = len(s)
        cache = set()
        x = 0
        y = 0
        for i, c in enumerate(s):
            x += UDLR[c][0]
            y += UDLR[c][1]
            if i < k - 1:
                continue
            cache.add((x, y))
            x -= UDLR[s[i - k]][0]
            y -= UDLR[s[i - k]][1]

        return len(cache)

    def distinctPoints(self, s: str, k: int) -> int:
        n = len(s)
        cache = set()
        x = 0
        y = 0
        cache.add((0, 0))
        for i in range(k, n):
            x += UDLR[s[i]][0] - UDLR[s[i - k]][0]
            y += UDLR[s[i]][1] - UDLR[s[i - k]][1]
            cache.add((x, y))

        return len(cache)

if __name__ == '__main__':
    print(Solution().distinctPoints("LUL", 1))