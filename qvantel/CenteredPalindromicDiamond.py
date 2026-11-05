"""
Qvantel Related Coding Question: Centered Palindromic Number Diamond Pattern

Problem Statement:
Given an integer N, print a centered symmetric diamond pattern of numbers where:
- The rows increase in peak from 1 up to N, and then decrease from N-1 down to 1.
- Each row k is indented with (N - k) spaces to align the numbers into a centered diamond.
- Numbers in row k go from 1 up to k, then descend from k-1 down to 1.

Example (N = 3):
  1
 121
12321
 121
  1

Example (N = 4):
   1
  121
 12321
1234321
 12321
  121
   1

Complexity:
- Time: O(T * N^2), where T is the number of test cases and N is the peak value.
- Space: O(N) auxiliary space per printed line.
"""

import sys

def solve():
    # Read all tokens from standard input (handles spaces and newlines)
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    # Process each test case independently
    for token in input_data:
        N = int(token)
        if N <= 0:
            continue
        
        # Sequence of peak values for each row: 1..N..1
        rows = list(range(1, N + 1)) + list(range(N - 1, 0, -1))
        
        for k in rows:
            spaces = " " * (N - k)
            left = "".join(str(x) for x in range(1, k + 1))
            right = "".join(str(x) for x in range(k - 1, 0, -1))
            print(spaces + left + right)

if __name__ == '__main__':
    solve()
