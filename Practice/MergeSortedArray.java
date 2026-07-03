package Practice;

import java.util.Arrays;

public class MergeSortedArray {
    /**
     * Practice Question: Merge Sorted Array
     * 
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
     * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     * 
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * The final sorted array should not be returned by the function, but instead be 
     * stored inside the array nums1. To accommodate this, nums1 has a length of m + n.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Three pointers approach: iterating backward from the end.
        // p1: pointer for nums1 (starts at m - 1)
        // p2: pointer for nums2 (starts at n - 1)
        // p: pointer for the insertion index at the end of nums1 (starts at m + n - 1)
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        
        // Loop while we still have elements to process in both arrays
        while (p1 >= 0 && p2 >= 0) {
            // Compare the elements from the back and place the larger one at index p
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        
        // If there are still elements remaining in nums2, copy them.
        // (Note: if there are remaining elements in nums1, they are already in the correct place!)
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

    public static void main(String[] args) {
        MergeSortedArray solution = new MergeSortedArray();
        
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        
        solution.merge(nums1, m, nums2, n);
        System.out.println("Merged Array: " + Arrays.toString(nums1)); // Expected: [1, 2, 2, 3, 5, 6]
    }
}
