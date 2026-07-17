package Practice;

public class MaxAverageSubarray {
    /**
     * Practice Question: Maximum Average Subarray I
     * 
     * You are given an integer array nums consisting of n elements, and an integer k.
     * Find a contiguous subarray whose length is equal to k that has the maximum average value 
     * and return this value. Any answer with a calculation error less than 10^-5 will be accepted.
     */
    public double findMaxAverage(int[] nums, int k) {
        // Since we are looking for a fixed-size subarray of length k, 
        // we can solve this efficiently using the Sliding Window technique.
        
        // Step 1: Calculate the sum of the first window (first k elements)
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        
        double maxSum = currentSum;
        
        // Step 2: Slide the window from index k to the end of the array
        for (int i = k; i < nums.length; i++) {
            // Slide window: Add the new element entering the window (nums[i])
            // and subtract the element leaving the window (nums[i - k])
            currentSum += nums[i] - nums[i - k];
            
            // Track the maximum sum found so far
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        
        // Step 3: Return the maximum sum divided by k to get the average
        return maxSum / k;
    }

    public static void main(String[] args) {
        MaxAverageSubarray solution = new MaxAverageSubarray();
        
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Test 1: " + solution.findMaxAverage(nums1, k1)); 
        // Expected: 12.75 (Subarray: [12, -5, -6, 50] has sum 51, 51/4 = 12.75)
        
        int[] nums2 = {5};
        int k2 = 1;
        System.out.println("Test 2: " + solution.findMaxAverage(nums2, k2)); 
        // Expected: 5.0
    }
}
