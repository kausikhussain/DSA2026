package Practice;

public class MaximumSubarray {
    /**
     * LeetCode 53: Maximum Subarray
     * 
     * Given an integer array nums, find the subarray with the largest sum, 
     * and return its sum.
     * 
     * A subarray is a contiguous non-empty sequence of elements within an array.
     */
    public int maxSubArray(int[] nums) {
        // Kadane's Algorithm:
        // We maintain two values:
        // 1. currentSum: the maximum sum ending at the current index
        // 2. maxSum: the overall maximum subarray sum seen so far

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // At each step, decide whether to:
            // - extend the previous subarray: currentSum + nums[i]
            // - start a fresh subarray from current element: nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update overall maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1: " + solution.maxSubArray(nums1)); 
        // Expected: 6 (subarray [4, -1, 2, 1])

        int[] nums2 = {1};
        System.out.println("Test 2: " + solution.maxSubArray(nums2)); 
        // Expected: 1

        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("Test 3: " + solution.maxSubArray(nums3)); 
        // Expected: 23 (entire array [5, 4, -1, 7, 8])
    }
}
