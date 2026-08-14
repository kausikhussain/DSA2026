package Infosys;

public class FindDuplicate {
    /**
     * LeetCode 287: Find the Duplicate Number (Infosys)
     * 
     * Given an array of integers nums containing n + 1 integers where each integer 
     * is in the range [1, n] inclusive.
     * There is only one repeated number in nums, return this repeated number.
     * 
     * Constraints:
     * - You must solve the problem without modifying the array nums and uses only O(1) extra space.
     * - You must run the algorithm in O(N) time complexity.
     */
    public int findDuplicate(int[] nums) {
        // We can solve this using Floyd's Tortoise and Hare (Cycle Detection) algorithm.
        // Since values are in range [1, n], we can treat the array values as pointers to indices.
        
        // Step 1: Detect if a cycle exists (they will meet inside the cycle)
        int tortoise = nums[0];
        int hare = nums[0];
        
        do {
            tortoise = nums[tortoise];       // moves 1 step
            hare = nums[nums[hare]];         // moves 2 steps
        } while (tortoise != hare);
        
        // Step 2: Find the entry point of the cycle (which is the duplicate element)
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        
        return ptr1; // The meeting point is the duplicate number
    }

    public static void main(String[] args) {
        FindDuplicate solution = new FindDuplicate();
        
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Test 1 [1,3,4,2,2]: " + solution.findDuplicate(nums1)); // Expected: 2
        
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("Test 2 [3,1,3,4,2]: " + solution.findDuplicate(nums2)); // Expected: 3
    }
}
