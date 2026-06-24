package Practice;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    /**
     * Practice Question: Two Sum
     * 
     * Given an array of integers nums and an integer target, return indices of the two numbers 
     * such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     * 
     * Challenge: Can you come up with an algorithm that is less than O(n^2) time complexity?
     */
    public int[] twoSum(int[] nums, int target) {
        // Approach: One-pass Hash Table
        // We use a HashMap to store the numbers we've seen so far and their indices.
        // Key = number, Value = index
        HashMap<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Calculate what number we would need to reach the target sum
            int complement = target - nums[i];

            // If the complement exists in our HashMap, we found our pair!
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }

            // Otherwise, we store the current number and its index in the HashMap and move on
            numMap.put(nums[i], i);
        }

        // The problem guarantees there is exactly one solution, so we should never actually reach here.
        return new int[] {};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test 1 [2,7,11,15] target 9: " + Arrays.toString(solution.twoSum(nums1, target1))); 
        // Expected: [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test 2 [3,2,4] target 6: " + Arrays.toString(solution.twoSum(nums2, target2))); 
        // Expected: [1, 2]
    }
}
