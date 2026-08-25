package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /**
     * LeetCode 15: 3Sum
     * 
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * 
     * Notice that the solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        // Step 1: Sort the array to easily handle duplicates and use two pointers
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Early stopping: if the smallest number is > 0, sum can never be 0
            if (nums[i] > 0) break;

            // Skip duplicate values for the first element of triplet
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right pointers
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Need a larger sum
                } else {
                    right--; // Need a smaller sum
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test 1 [-1, 0, 1, 2, -1, -4]: " + solution.threeSum(nums1));
        // Expected: [[-1, -1, 2], [-1, 0, 1]]

        int[] nums2 = {0, 1, 1};
        System.out.println("Test 2 [0, 1, 1]: " + solution.threeSum(nums2));
        // Expected: []

        int[] nums3 = {0, 0, 0};
        System.out.println("Test 3 [0, 0, 0]: " + solution.threeSum(nums3));
        // Expected: [[0, 0, 0]]
    }
}
