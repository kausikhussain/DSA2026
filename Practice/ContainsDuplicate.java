package Practice;

import java.util.HashSet;

public class ContainsDuplicate {
    /**
     * Practice Question: Contains Duplicate
     * 
     * Given an integer array nums, return true if any value appears at least twice in the array, 
     * and return false if every element is distinct.
     */
    public boolean containsDuplicate(int[] nums) {
        // A HashSet only stores unique elements.
        HashSet<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            // If the element is already in the HashSet, we found a duplicate!
            if (seen.contains(num)) {
                return true;
            }
            // Otherwise, add it to the set to keep track of it
            seen.add(num);
        }
        
        // If we finish the loop without returning true, all elements are distinct
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();
        
        int[] test1 = {1, 2, 3, 1};
        System.out.println("Test 1 [1, 2, 3, 1]: " + solution.containsDuplicate(test1)); // Expected: true
        
        int[] test2 = {1, 2, 3, 4};
        System.out.println("Test 2 [1, 2, 3, 4]: " + solution.containsDuplicate(test2)); // Expected: false
    }
}
