package Practice;

public class FindMinRotatedSortedArray {
    /**
     * LeetCode 153: Find Minimum in Rotated Sorted Array
     * 
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
     * For example, the array nums = [0,1,2,4,5,6,7] might become:
     * - [4,5,6,7,0,1,2] if it was rotated 4 times.
     * - [0,1,2,4,5,6,7] if it was rotated 7 times.
     * 
     * Return the minimum element of this array.
     * 
     * Constraints:
     * - You must write an O(log n) time complexity algorithm.
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        // Binary search
        while (low < high) {
            int mid = (low + high) >>> 1;
            
            // If mid element is greater than the rightmost element, 
            // the minimum must be in the right half of the array (e.g. [4,5,6,7,0,1,2], mid=7, right=2)
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } 
            // If mid element is less than or equal to the rightmost element,
            // the minimum is in the left half, including the mid element itself.
            else {
                high = mid;
            }
        }
        
        // When low == high, we have narrowed down to the minimum element
        return nums[low];
    }

    public static void main(String[] args) {
        FindMinRotatedSortedArray solution = new FindMinRotatedSortedArray();
        
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1 [3,4,5,1,2]: " + solution.findMin(nums1)); // Expected: 1
        
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2 [4,5,6,7,0,1,2]: " + solution.findMin(nums2)); // Expected: 0
        
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test 3 [11,13,15,17]: " + solution.findMin(nums3)); // Expected: 11
    }
}
