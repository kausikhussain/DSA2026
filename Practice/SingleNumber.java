package Practice;

public class SingleNumber {
    /**
     * Practice Question: Single Number
     * 
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     */
    public int singleNumber(int[] nums) {
        // We use the bitwise XOR operator (^) to solve this in O(N) time and O(1) space.
        // Property 1: a ^ a = 0 (XORing a number with itself cancels it out)
        // Property 2: a ^ 0 = a (XORing a number with 0 leaves it unchanged)
        // By XORing all numbers together, all the pairs will cancel each other out to 0,
        // leaving only the single number that appears once!
        
        int result = 0;
        for (int num : nums) {
            result ^= num; // Same as result = result ^ num;
        }
        
        return result;
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        
        int[] test1 = {2, 2, 1};
        System.out.println("Test 1 [2,2,1]: " + solution.singleNumber(test1)); // Expected: 1
        
        int[] test2 = {4, 1, 2, 1, 2};
        System.out.println("Test 2 [4,1,2,1,2]: " + solution.singleNumber(test2)); // Expected: 4
    }
}
