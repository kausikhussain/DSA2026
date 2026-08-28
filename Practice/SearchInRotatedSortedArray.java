package Practice;

public class SearchInRotatedSortedArray {
    /**
     * LeetCode 33: Search in Rotated Sorted Array
     * 
     * Given the array nums after a possible rotation and an integer target, 
     * return the index of target if it is in nums, or -1 if it is not in nums.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half of the array is normally sorted:
            
            // Case 1: Left half [low...mid] is sorted
            if (nums[low] <= nums[mid]) {
                // Check if target lies within the sorted left half
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } 
            // Case 2: Right half [mid...high] is sorted
            else {
                // Check if target lies within the sorted right half
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 1 (target 0): " + solution.search(nums1, 0)); // Expected: 4
        System.out.println("Test 2 (target 3): " + solution.search(nums1, 3)); // Expected: -1

        int[] nums2 = {1};
        System.out.println("Test 3 (target 0): " + solution.search(nums2, 0)); // Expected: -1
        System.out.println("Test 4 (target 1): " + solution.search(nums2, 1)); // Expected: 0
    }
}
