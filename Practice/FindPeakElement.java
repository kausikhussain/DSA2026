package Practice;

public class FindPeakElement {
    /**
     * LeetCode 162: Find Peak Element
     * 
     * A peak element is an element that is strictly greater than its neighbors.
     * Given a 0-indexed integer array nums, find a peak element, and return its index. 
     * If the array contains multiple peaks, return the index to any of the peaks.
     * 
     * You may imagine that nums[-1] = nums[n] = -infinity.
     * You must write an algorithm that runs in O(log n) time.
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is smaller than its right neighbor, the peak MUST lie on the right
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } 
            // If mid element is greater than its right neighbor, a peak exists at mid or on the left
            else {
                high = mid;
            }
        }

        // When low == high, we have converged on a peak element
        return low;
    }

    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();

        // Test 1: [1, 2, 3, 1]
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1: " + solution.findPeakElement(nums1)); 
        // Expected: 2 (index 2 with value 3 is a peak)

        // Test 2: [1, 2, 1, 3, 5, 6, 4]
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Test 2: " + solution.findPeakElement(nums2)); 
        // Expected: 1 or 5 (index 1 with value 2 or index 5 with value 6)

        // Test 3: [1]
        int[] nums3 = {1};
        System.out.println("Test 3: " + solution.findPeakElement(nums3)); 
        // Expected: 0
    }
}
