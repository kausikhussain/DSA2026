package Practice;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers {
    /**
     * Practice Question: Find All Numbers Disappeared in an Array
     * 
     * Given an array nums of n integers where nums[i] is in the range [1, n], 
     * return an array of all the integers in the range [1, n] that do not appear in nums.
     * 
     * Challenge: Could you do it without extra space and in O(n) runtime? 
     * You may assume the returned list does not count as extra space.
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        // In-place marking approach:
        // Since values are in the range [1, n], we can map each value to an index (value - 1).
        // We iterate through the array and for each value we see, we mark the element 
        // at its corresponding index as negative (if it's not already negative).
        for (int i = 0; i < nums.length; i++) {
            // Get the index that the current number points to
            int index = Math.abs(nums[i]) - 1;
            
            // Mark the value at that index as negative to denote we've seen the number (index + 1)
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        // Second pass: Any index that remains positive indicates that (index + 1) 
        // was never seen in the array.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers solution = new FindDisappearedNumbers();
        
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Test 1 [4,3,2,7,8,2,3,1]: " + solution.findDisappearedNumbers(nums1)); 
        // Expected: [5, 6]
        
        int[] nums2 = {1, 1};
        System.out.println("Test 2 [1,1]: " + solution.findDisappearedNumbers(nums2)); 
        // Expected: [2]
    }
}
