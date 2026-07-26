package Infosys;

public class JumpGame {
    /**
     * LeetCode 55: Jump Game (Infosys)
     * 
     * You are given an integer array nums. You are initially positioned at the array's first index, 
     * and each element in the array represents your maximum jump length at that position.
     * 
     * Return true if you can reach the last index, or false otherwise.
     */
    public boolean canJump(int[] nums) {
        // We can solve this in O(N) time using a Greedy approach.
        // We keep track of the maximum reachable index we can achieve so far.
        int maxReachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater than the maximum reachable index,
            // we cannot progress past this point.
            if (i > maxReachable) {
                return false;
            }
            
            // Update the maximum index we can reach from the current index
            maxReachable = Math.max(maxReachable, i + nums[i]);
            
            // If the maximum reachable index exceeds or equals the last index, we succeed!
            if (maxReachable >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test 1 [2, 3, 1, 1, 4]: " + solution.canJump(nums1)); // Expected: true
        
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Test 2 [3, 2, 1, 0, 4]: " + solution.canJump(nums2)); // Expected: false
    }
}
