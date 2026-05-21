package Day12_Greedy;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int currentSum = 0;
        int maxSum = nums[0];
        
        for (int num : nums) {
            // If our current running sum is negative, it will only drag down future sums.
            // So we greedily reset the current sum to 0 (effectively starting a new subarray).
            if (currentSum < 0) {
                currentSum = 0;
            }
            
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}
