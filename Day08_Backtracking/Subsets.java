package Day08_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        // Add a copy of the current subset to the result
        result.add(new ArrayList<>(tempList));
        
        // Iterate over the remaining elements to form new subsets
        for (int i = start; i < nums.length; i++) {
            // Include the element
            tempList.add(nums[i]);
            
            // Move to the next element
            backtrack(result, tempList, nums, i + 1);
            
            // Exclude the element (backtrack)
            tempList.remove(tempList.size() - 1);
        }
    }
}
