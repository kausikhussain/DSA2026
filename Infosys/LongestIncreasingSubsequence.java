package Infosys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    /**
     * Infosys Medium / LeetCode 300: Longest Increasing Subsequence (LIS)
     * 
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     * 
     * A subsequence is an array that can be derived from another array by deleting some or no 
     * elements without changing the order of the remaining elements.
     */

    // Approach 1: Binary Search / Patience Sorting - O(N log N) time, O(N) space (Optimal for SP Round)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // tails[i] stores the smallest tail of all increasing subsequences of length i + 1 found so far
        List<Integer> tails = new ArrayList<>();

        for (int x : nums) {
            // Binary search to find the insertion index of x in tails
            int idx = Collections.binarySearch(tails, x);

            if (idx < 0) {
                // If not found, Collections.binarySearch returns (-(insertion point) - 1)
                idx = -(idx + 1);
            }

            // If x is greater than all existing elements, append to tails (extends LIS length)
            if (idx == tails.size()) {
                tails.add(x);
            } else {
                // Otherwise replace the existing element to allow smaller tails for future elements
                tails.set(idx, x);
            }
        }

        return tails.size();
    }

    // Approach 2: Classic 1D Dynamic Programming - O(N^2) time, O(N) space
    public int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each element alone forms an LIS of length 1

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test 1 [10, 9, 2, 5, 3, 7, 101, 18]: " + solution.lengthOfLIS(nums1)); 
        // Expected: 4 (e.g., [2, 3, 7, 101] or [2, 5, 7, 101])

        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Test 2 [0, 1, 0, 3, 2, 3]: " + solution.lengthOfLIS(nums2)); 
        // Expected: 4 ([0, 1, 2, 3])

        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Test 3 [7, 7, 7, 7, 7, 7, 7]: " + solution.lengthOfLIS(nums3)); 
        // Expected: 1 ([7])
    }
}
