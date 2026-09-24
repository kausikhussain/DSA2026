package Practice;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /**
     * LeetCode 46: Permutations
     * 
     * Given an array nums of distinct integers, return all the possible permutations. 
     * You can return the answer in any order.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // Base case: if current permutation contains all numbers
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip elements that are already used in current permutation
            if (used[i]) continue;

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, current, used, result);

            // Unchoose (backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations solution = new Permutations();

        int[] nums1 = {1, 2, 3};
        System.out.println("Test 1 ([1, 2, 3]): " + solution.permute(nums1));
        // Expected: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

        int[] nums2 = {0, 1};
        System.out.println("Test 2 ([0, 1]): " + solution.permute(nums2));
        // Expected: [[0, 1], [1, 0]]

        int[] nums3 = {1};
        System.out.println("Test 3 ([1]): " + solution.permute(nums3));
        // Expected: [[1]]
    }
}
