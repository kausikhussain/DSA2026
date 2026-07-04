package Practice;

import java.util.Arrays;

public class TwoSumTwoSorted {
    /**
     * Practice Question: Two Sum II - Input Array Is Sorted
     * 
     * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, 
     * find two numbers such that they add up to a specific target number.
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
     * 
     * Constraints:
     * - Your solution must use only O(1) constant extra space.
     */
    public int[] twoSum(int[] numbers, int target) {
        // Since the array is sorted, we can use the Two Pointers approach
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            
            if (currentSum == target) {
                // Return 1-based indices as required by the problem
                return new int[] {left + 1, right + 1};
            } else if (currentSum < target) {
                // Sum is too small, move left pointer to increase the sum
                left++;
            } else {
                // Sum is too big, move right pointer to decrease the sum
                right--;
            }
        }
        
        // No solution found (problem guarantees exactly one solution)
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        TwoSumTwoSorted solution = new TwoSumTwoSorted();
        
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Test 1: " + Arrays.toString(solution.twoSum(numbers, target))); // Expected: [1, 2]
    }
}
