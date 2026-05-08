# Day 3: Sliding Window

## Theory
The sliding window technique is a subset of the two-pointer approach, typically used for finding subarrays or substrings that meet certain criteria (e.g., longest, shortest, or containing specific elements).

Instead of recalculating overlapping parts of the subarray, we simply "slide" our window by advancing the right pointer to expand, and the left pointer to shrink when our condition breaks.

## Key Pattern
If a problem asks for:
- Maximum/minimum length of a contiguous subarray/substring
- Something involving "contiguous" or "substrings" (not subsequences)
Think **Sliding Window**.
