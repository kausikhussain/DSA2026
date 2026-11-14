package Infosys;

public class HouseRobber {
    /**
     * LeetCode 198: House Robber (Infosys SP / DSE)
     * 
     * You are a professional robber planning to rob houses along a street. 
     * Each house has a certain amount of money stashed, the only constraint 
     * stopping you from robbing each of them is that adjacent houses have security 
     * systems connected and it will automatically contact the police if two adjacent 
     * houses were broken into on the same night.
     * 
     * Given an integer array nums representing the amount of money of each house, 
     * return the maximum amount of money you can rob tonight without alerting the police.
     * 
     * Approach:
     * - Dynamic Programming with Space Optimization (O(1) space).
     * - At each house, we have two choices:
     *   1. Rob the current house: total money = money + prev2 (money from 2 houses ago).
     *   2. Skip the current house: total money = prev1 (money from previous house).
     * - We maintain two variables `prev1` (max money up to house i-1) and `prev2` (max money up to house i-2).
     * 
     * Complexity:
     * - Time: O(n), single pass through the array.
     * - Space: O(1), constant auxiliary memory.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prev2 = 0;
        int prev1 = 0;
        for (int money : nums) {
            int current = Math.max(
                prev1,
                prev2 + money
            );
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();

        // Test 1: [1, 2, 3, 1] -> Rob house 1 (1) and house 3 (3) -> 4
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1 [1, 2, 3, 1]: " + solution.rob(nums1)); // Expected: 4

        // Test 2: [2, 7, 9, 3, 1] -> Rob house 1 (2), house 3 (9), house 5 (1) -> 12
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Test 2 [2, 7, 9, 3, 1]: " + solution.rob(nums2)); // Expected: 12

        // Test 3: [2, 1, 1, 2] -> Rob house 1 (2) and house 4 (2) -> 4
        int[] nums3 = {2, 1, 1, 2};
        System.out.println("Test 3 [2, 1, 1, 2]: " + solution.rob(nums3)); // Expected: 4

        // Test 4: Single house [5]
        int[] nums4 = {5};
        System.out.println("Test 4 [5]: " + solution.rob(nums4)); // Expected: 5
    }
}
