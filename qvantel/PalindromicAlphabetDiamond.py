"""
Qvantel Practice Coding Question: Palindromic Alphabet Diamond Pattern

Problem Statement:
Given an integer N (or a character C corresponding to the N-th uppercase letter), 
print a symmetric, centered diamond pattern of uppercase English alphabets where:
- The rows increase in peak character from 'A' up to the N-th letter, and then decrease back to 'A'.
- Each row k is indented with (N - k) spaces to center the diamond.
- Row k prints characters ascending from 'A' up to the k-th letter, then descending back to 'A'.

Example (N = 3 or C = 'C'):
  A
 ABA
ABCBA
 ABA
  A

Example (N = 4 or C = 'D'):
   A
  ABA
 ABCBA
ABCDCBA
 ABCBA
  ABA
   A

Complexity:
- Time: O(T * N^2), where T is the number of test cases and N is the peak character index (1 <= N <= 26).
- Space: O(N) auxiliary space per generated line.
"""

import sys

def parse_n(token):
    """Helper to convert token (either integer 1-26 or character A-Z) to integer N."""
    token = token.strip()
    if token.isdigit():
        return int(token)
    elif len(token) == 1 and token.isalpha():
        return ord(token.upper()) - ord('A') + 1
    return None

def solve():
    # Read all tokens from standard input (handles spaces and newlines)
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    # Process each test case independently
    for token in input_data:
        N = parse_n(token)
        if N is None or N <= 0 or N > 26:
            continue

        # Sequence of peak lengths for the diamond: 1..N..1
        rows = list(range(1, N + 1)) + list(range(N - 1, 0, -1))

        for k in rows:
            spaces = " " * (N - k)
            left = "".join(chr(ord('A') + x - 1) for x in range(1, k + 1))
            right = "".join(chr(ord('A') + x - 1) for x in range(k - 1, 0, -1))
            print(spaces + left + right)

if __name__ == '__main__':
    solve()
