# 2266. 统计打字方案数
# 已解答
# 中等
# 相关标签
# premium lock icon
# 相关企业
# 提示
# Alice 在给 Bob 用手机打字。数字到字母的 对应 如下图所示。
#
#
#
# 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
#
# 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
# 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
# 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
#
# 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
# 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
#
# 由于答案可能很大，将它对 109 + 7 取余 后返回。
#
#
#
# 示例 1：
#
# 输入：pressedKeys = "22233"
# 输出：8
# 解释：
# Alice 可能发出的文字信息包括：
# "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
# 由于总共有 8 种可能的信息，所以我们返回 8 。
# 示例 2：
#
# 输入：pressedKeys = "222222222222222222222222222222222222"
# 输出：82876089
# 解释：
# 总共有 2082876103 种 Alice 可能发出的文字信息。
# 由于我们需要将答案对 109 + 7 取余，所以我们返回 2082876103 % (109 + 7) = 82876089 。
#
#
# 提示：
#
# 1 <= pressedKeys.length <= 105
# pressedKeys 只包含数字 '2' 到 '9' 。
from itertools import groupby

f = [1, 1, 2, 4]
g = [1, 1, 2, 4]

for _ in range(10 ** 5 - 3):
    f.append(f[-1] + f[-2] + f[-3])
    g.append(g[-1] + g[-2] + g[-3] + g[-4])


class Solution:
    def countTexts(self, pressedKeys: str) -> int:
        mapping = [0, 0, 3, 3, 3, 3, 3, 4, 3, 4]
        ans = 1
        pre = '0'
        dp = [0] * (len(pressedKeys) + 1)
        dp[0] = 1
        idx = 1
        for c in pressedKeys:
            if pre != c:
                ans = (ans * dp[idx - 1]) % 1000000007
                idx = 1
            dp[idx] = 0
            for i in range(idx - 1, max(-1, idx - mapping[int(c)] - 1), -1):
                dp[idx] += dp[i]
            pre = c
            idx += 1

        ans = (ans * dp[idx - 1]) % 1000000007
        return ans

    def countTexts(self, pressedKeys: str) -> int:
        ans = 1
        n = len(pressedKeys)
        cnt = 0
        for i in range(0, n):
            cnt += 1
            if i == n - 1 or pressedKeys[i] != pressedKeys[i + 1]:
                ans = ans * (g[cnt] if pressedKeys[i] == '7' or pressedKeys[i] == '9' else f[cnt]) % 1000000007
                cnt = 0
        return ans

    def countTexts(self, pressedKeys: str) -> int:
        ans = 1
        for ch, s in groupby(pressedKeys):
            n = len(list(s))
            ans = ans * (g[n] if ch in "79" else f[n]) % 1000000007
        return ans

if __name__ == '__main__':
    print(Solution().countTexts("22233"))
    print(Solution().countTexts("222222222222222222222222222222222222"))
    print(Solution().countTexts("444479999555588866"))
    # for ch, s in groupby("444479999555588866"):
    #     print(ch, "-->", list(s))
