package Practice;

public class HouseRobberII {
    /**
     * LeetCode 213: House Robber II
     * 
     * You are a professional robber planning to rob houses along a street. 
     * All houses at this place are arranged in a circle. 
     * That means the first house is the neighbor of the last one.
     * 
     * Adjacent houses have a security system connected, and it will automatically 
     * contact the police if two adjacent houses were broken into on the same night.
     * 
     * Given an integer array nums representing the amount of money of each house, 
     * return the maximum amount of money you can rob tonight without alerting the police.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int n = nums.length;
        // Case 1: Rob houses from index 0 to n - 2 (exclude the last house)
        int robExcludingLast = robLinear(nums, 0, n - 2);

        // Case 2: Rob houses from index 1 to n - 1 (exclude the first house)
        int robExcludingFirst = robLinear(nums, 1, n - 1);

        return Math.max(robExcludingLast, robExcludingFirst);
    }

    private int robLinear(int[] nums, int start, int end) {
        int prevRob = 0;
        int maxRob = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(maxRob, prevRob + nums[i]);
            prevRob = maxRob;
            maxRob = current;
        }

        return maxRob;
    }

    public static void main(String[] args) {
        HouseRobberII solution = new HouseRobberII();

        int[] nums1 = {2, 3, 2};
        System.out.println("Test 1 ([2, 3, 2]): " + solution.rob(nums1)); 
        // Expected: 3 (cannot rob house 1 and 3 because they are adjacent in circle)

        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Test 2 ([1, 2, 3, 1]): " + solution.rob(nums2)); 
        // Expected: 4 (rob house 1 and 3 -> 1 + 3 = 4)

        int[] nums3 = {1, 2, 3};
        System.out.println("Test 3 ([1, 2, 3]): " + solution.rob(nums3)); 
        // Expected: 3
    }
}
