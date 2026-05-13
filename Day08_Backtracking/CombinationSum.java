package Day08_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain < 0) {
            // Base case: Exceeded the target, stop exploring this path
            return;
        } else if (remain == 0) {
            // Base case: Reached the target, add a copy of the combination to the result
            result.add(new ArrayList<>(tempList));
        } else {
            // Recursive case: Explore further
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                
                // Note: we pass 'i' instead of 'i + 1' because we can reuse the same element
                backtrack(result, tempList, candidates, remain - candidates[i], i);
                
                // Backtrack: remove the last element before the next iteration
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
