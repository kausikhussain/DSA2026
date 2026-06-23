package Practice;

public class MissingNumber {
    /**
     * Practice Question: Missing Number
     * 
     * Given an array nums containing n distinct numbers in the range [0, n], 
     * return the only number in the range that is missing from the array.
     * 
     * Challenge: Implement a solution using only O(1) extra space complexity and O(n) runtime complexity.
     */
    public int missingNumber(int[] nums) {
        // Approach: Mathematical Sum Formula (Gauss' Formula)
        // We know the sum of numbers from 0 to n is (n * (n + 1)) / 2
        
        int n = nums.length;
        
        // Calculate the expected sum if no numbers were missing
        int expectedSum = (n * (n + 1)) / 2;
        
        // Calculate the actual sum of the numbers in our array
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        
        // The missing number is exactly the difference between the expected and actual sums!
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        MissingNumber solution = new MissingNumber();
        
        int[] test1 = {3, 0, 1};
        System.out.println("Test 1 [3, 0, 1]: " + solution.missingNumber(test1)); // Expected: 2
        
        int[] test2 = {0, 1};
        System.out.println("Test 2 [0, 1]: " + solution.missingNumber(test2)); // Expected: 2
        
        int[] test3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Test 3 [9, 6, 4, 2, 3, 5, 7, 0, 1]: " + solution.missingNumber(test3)); // Expected: 8
    }
}
