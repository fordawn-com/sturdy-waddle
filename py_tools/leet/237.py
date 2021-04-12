# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def deleteNode(self, node):
        """
        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """

        print(123)


if __name__ == '__main__':
    a = ListNode(1)
    b = ListNode(2)
    a.next = b
    Solution.deleteNode(a)
