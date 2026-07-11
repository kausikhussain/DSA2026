package Practice;

import java.util.Arrays;

public class ProductExceptSelf {
    /**
     * Practice Question: Product of Array Except Self
     * 
     * Given an integer array nums, return an array answer such that answer[i] 
     * is equal to the product of all the elements of nums except nums[i].
     * 
     * Constraints:
     * - You must write an algorithm that runs in O(n) time.
     * - You must solve it without using the division operation.
     * 
     * Challenge: Could you solve it with O(1) extra space complexity? 
     * (The output array does not count as extra space for space complexity analysis.)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Step 1: Calculate the prefix products and store them in the answer array.
        // answer[i] will store the product of all elements to the left of index i.
        answer[0] = 1; // There are no elements to the left of the first element
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Step 2: Calculate the suffix products on the fly using a running variable,
        // and multiply it with the prefix products already stored in the answer array.
        int suffixProduct = 1; // Suffix product for the rightmost element is 1
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffixProduct;
            suffixProduct = suffixProduct * nums[i]; // Update running suffix product
        }
        
        return answer;
    }

    public static void main(String[] args) {
        ProductExceptSelf solution = new ProductExceptSelf();
        
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test 1 [1,2,3,4]: " + Arrays.toString(solution.productExceptSelf(nums1))); 
        // Expected: [24, 12, 8, 6]
        
        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println("Test 2 [-1,1,0,-3,3]: " + Arrays.toString(solution.productExceptSelf(nums2))); 
        // Expected: [0, 0, 9, 0, 0]
    }
}
