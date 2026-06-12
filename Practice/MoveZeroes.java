package Practice;

import java.util.Arrays;

public class MoveZeroes {
    /**
     * Practice Question: Move Zeroes
     * 
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        // insertPos tracks where the next non-zero element should be placed
        int insertPos = 0;
        
        // First pass: Move all non-zero elements to the front of the array.
        // We maintain their relative order because we iterate from left to right.
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        
        // Second pass: Fill all the remaining positions from insertPos to the end of the array with zeroes.
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();
        
        // Test case
        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("Original array: " + Arrays.toString(nums));
        
        solution.moveZeroes(nums);
        System.out.println("After moving zeroes: " + Arrays.toString(nums)); 
        // Expected Output: [1, 3, 12, 0, 0]
    }
}
