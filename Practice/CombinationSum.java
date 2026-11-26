package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 39: Combination Sum
 * Difficulty: Medium
 * 
 * Given an array of distinct integers candidates and a target integer target, 
 * return a list of all unique combinations of candidates where the chosen numbers 
 * sum to target. You may return the combinations in any order.
 * 
 * The same number may be chosen from candidates an unlimited number of times. 
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * 
 * Approach:
 * - Backtracking with Sorting & Early Pruning.
 * - Sort the candidates array in ascending order to enable early loop termination.
 * - At each step in recursion:
 *   - Base case: If remain == 0, current path is a valid combination; record a copy.
 *   - For each candidate starting from the current index i:
 *     - Prune: If candidates[i] > remain, break immediately (all later numbers are larger).
 *     - Choose: Append candidates[i] to current combination list.
 *     - Recurse: Call backtrack with index i (allowing the same number to be reused) 
 *       and updated remainder (remain - candidates[i]).
 *     - Backtrack: Remove the last added element before exploring the next candidate.
 * 
 * Complexity:
 * - Time: O(N^(T/M + 1)), where N is candidates.length, T is target, and M is the smallest candidate.
 * - Space: O(T/M) auxiliary recursion stack and path list storage.
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return result;
        }

        // Sort candidates to enable early branch pruning
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
            // Early pruning: since candidates are sorted, all subsequent elements will also exceed remain
            if (candidates[i] > remain) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // Explore: pass i (not i + 1) because elements can be reused unlimited times
            backtrack(candidates, remain - candidates[i], i, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();

        // Test 1: candidates = [2, 3, 6, 7], target = 7
        // Combinations: [[2, 2, 3], [7]]
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Test 1: " + solution.combinationSum(candidates1, target1));
        // Expected: [[2, 2, 3], [7]]

        // Test 2: candidates = [2, 3, 5], target = 8
        // Combinations: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Test 2: " + solution.combinationSum(candidates2, target2));
        // Expected: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

        // Test 3: candidates = [2], target = 1
        // No combinations possible
        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println("Test 3: " + solution.combinationSum(candidates3, target3));
        // Expected: []
    }
}
