package Practice;

public class MaximumProductSubarray {
    /**
     * LeetCode 152: Maximum Product Subarray
     * 
     * Given an integer array nums, find a subarray that has the largest product, 
     * and return the product.
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // Since multiplying by a negative number can turn a minimum into a maximum,
        // we track both the maximum and minimum products ending at the current position.
        int curMax = nums[0];
        int curMin = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // If the current number is negative, swapping curMax and curMin
            // accounts for the sign flip when multiplying.
            if (num < 0) {
                int temp = curMax;
                curMax = curMin;
                curMin = temp;
            }

            // At index i, max product is either num itself or num * curMax
            curMax = Math.max(num, curMax * num);
            // Similarly for min product
            curMin = Math.min(num, curMin * num);

            // Update overall global maximum product
            globalMax = Math.max(globalMax, curMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();

        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Test 1: " + solution.maxProduct(nums1)); 
        // Expected: 6 (subarray [2, 3])

        int[] nums2 = {-2, 0, -1};
        System.out.println("Test 2: " + solution.maxProduct(nums2)); 
        // Expected: 0

        int[] nums3 = {-2, 3, -4};
        System.out.println("Test 3: " + solution.maxProduct(nums3)); 
        // Expected: 24 (entire array [-2, 3, -4])
    }
}
