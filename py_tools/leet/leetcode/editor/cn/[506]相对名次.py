# 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal",
#  "Silver Medal", "Bronze Medal"）。 
# 
#  (注：分数越高的选手，排名越靠前。) 
# 
#  示例 1: 
# 
#  
# 输入: [5, 4, 3, 2, 1]
# 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
# 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and 
# "Bronze Medal").
# 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。 
# 
#  提示: 
# 
#  
#  N 是一个正整数并且不会超过 10000。 
#  所有运动员的成绩都不相同。 
#  
#  👍 55 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def findRelativeRanks(self, nums: List[int]) -> List[str]:
        # res = []
        # for i in range(len(nums)):
        #     res.append(str(i + 1))
        # res[0] = 'Gold Medal'
        # res[1] = 'Silver Medal'
        # res[2] = 'Bronze Medal'
        #
        # return res[0:len(nums)]

        # return [x[0] for x in sorted(
        #     zip(["Gold Medal", "Silver Medal", "Bronze Medal"] + list(map(str, list(range(4, len(nums) + 1)))),
        #         [x[1] for x in sorted(map(lambda x: (x[1], x[0]), enumerate(nums)), reverse=True)]),
        #     key=lambda x: x[1])]

        i = list(map(lambda x: (x[1], x[0]), enumerate(nums)))
        # print(i)

        l = sorted(i, reverse=True)
        # print(l)

        list1 = list(x[1] for x in l)
        # print(list1)

        iterator = list(
            zip(["Gold Medal", "Silver Medal", "Bronze Medal"] + list(map(str, range(4, len(nums) + 1))), list1))
        # print(iterator)

        # iterator[0] = 'Gold Medal'
        # iterator[1] = 'Silver Medal'
        # iterator[2] = 'Bronze Medal'
        # print(iterator)

        sorted1 = sorted(iterator, key=lambda x: x[1])
        # print(sorted1)

        list2 = list(x[0] for x in sorted1)
        # print(list2)
        return list2


# leetcode submit region end(Prohibit modification and deletion)

e = [10, 3, 8, 9, 4]
ranks = Solution().findRelativeRanks(e)
print(ranks)

#
#
# print(list(enumerate(e)))
# print([x[1] for x in sorted(map(lambda x: (x[1], x[0]), enumerate(e)), reverse=True)])
#
# a = [1, 2, 3, 4]
# b = [a, 2, 'v', 1, 'b']
# print(list(zip(a, b)))
