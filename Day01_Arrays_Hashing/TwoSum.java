package Day01_Arrays_Hashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // Map to store value and its index
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // If the complement exists in the map, we found the pair
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            // Store the current number and its index
            map.put(nums[i], i);
        }
        
        // No solution found (though problem says exactly one solution exists)
        return new int[] {};
    }
}
