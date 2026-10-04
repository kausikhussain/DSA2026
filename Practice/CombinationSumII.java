package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    /**
     * LeetCode 40: Combination Sum II
     * 
     * Given a collection of candidate numbers (candidates) and a target number (target), 
     * find all unique combinations in candidates where the candidate numbers sum to target.
     * 
     * Each number in candidates may only be used once in the combination.
     * Note: The solution set must not contain duplicate combinations.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        // Sort candidates so we can prune branches and skip duplicate values at same depth
        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: array is sorted, so if candidates[i] > remain, all subsequent elements are also too large
            if (candidates[i] > remain) {
                break;
            }

            // Skip duplicate elements at the same tree depth to avoid duplicate combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choose
            current.add(candidates[i]);

            // Explore (i + 1 because each number can only be used once)
            backtrack(candidates, remain - candidates[i], i + 1, current, result);

            // Unchoose (backtrack)
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII solution = new CombinationSumII();

        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println("Test 1: " + solution.combinationSum2(candidates1, target1));
        // Expected: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println("Test 2: " + solution.combinationSum2(candidates2, target2));
        // Expected: [[1, 2, 2], [5]]
    }
}
