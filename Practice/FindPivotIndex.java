package Practice;

public class FindPivotIndex {
    /**
     * Practice Question: Find Pivot Index
     * 
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to the 
     * left of the index is equal to the sum of all the numbers strictly to the index's right.
     * 
     * If the index is on the left edge of the array, then the left sum is 0 because 
     * there are no elements to the left. This also applies to the right edge of the array.
     * 
     * Return the leftmost pivot index. If no such index exists, return -1.
     */
    public int pivotIndex(int[] nums) {
        // Calculate the total sum of all elements in the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        
        // Iterate through the array and check if leftSum equals rightSum
        for (int i = 0; i < nums.length; i++) {
            // The sum of elements strictly to the right of index i is:
            // rightSum = totalSum - leftSum - nums[i]
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i; // Leftmost pivot index found!
            }
            // Update leftSum to include the current element for the next iteration
            leftSum += nums[i];
        }
        
        return -1; // No pivot index found
    }

    public static void main(String[] args) {
        FindPivotIndex solution = new FindPivotIndex();
        
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Test 1 [1, 7, 3, 6, 5, 6]: " + solution.pivotIndex(nums1)); 
        // Expected: 3 (left sum = 1+7+3 = 11, right sum = 5+6 = 11)
        
        int[] nums2 = {1, 2, 3};
        System.out.println("Test 2 [1, 2, 3]: " + solution.pivotIndex(nums2)); 
        // Expected: -1
        
        int[] nums3 = {2, 1, -1};
        System.out.println("Test 3 [2, 1, -1]: " + solution.pivotIndex(nums3)); 
        // Expected: 0 (left sum = 0, right sum = 1+(-1) = 0)
    }
}
