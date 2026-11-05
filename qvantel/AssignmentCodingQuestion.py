"""
Qvantel Assignment Coding Question: Palindromic Number Diamond Pattern

Problem Statement:
Given an integer N, print a number diamond (half/uncentered diamond) where:
- The rows increase in peak from 1 up to N, and then decrease from N-1 down to 1.
- Each row k consists of numbers from 1 up to k, followed by numbers from k-1 down to 1.

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
        
        # Generate the sequence of peak numbers for the diamond
        rows = list(range(1, N + 1)) + list(range(N - 1, 0, -1))
        
        for k in rows:
            left = "".join(str(x) for x in range(1, k + 1))
            right = "".join(str(x) for x in range(k - 1, 0, -1))
            print(left + right)

if __name__ == '__main__':
    solve()
