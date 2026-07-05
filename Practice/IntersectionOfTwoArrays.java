package Practice;

import java.util.Arrays;
import java.util.HashSet;

public class IntersectionOfTwoArrays {
    /**
     * Practice Question: Intersection of Two Arrays
     * 
     * Given two integer arrays nums1 and nums2, return an array of their intersection. 
     * Each element in the result must be unique and you may return the result in any order.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // We use a HashSet to store unique elements of nums1
        HashSet<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        
        // We use another HashSet to capture elements of nums2 that are in set1
        HashSet<Integer> intersectionSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersectionSet.add(num);
            }
        }
        
        // Convert the intersection set to a primitive int array
        int[] result = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            result[index++] = num;
        }
        
        return result;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();
        
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Test 1: " + Arrays.toString(solution.intersection(nums1, nums2))); // Expected: [2]
        
        int[] nums1_2 = {4, 9, 5};
        int[] nums2_2 = {9, 4, 9, 8, 4};
        System.out.println("Test 2: " + Arrays.toString(solution.intersection(nums1_2, nums2_2))); // Expected: [4, 9] (or [9, 4])
    }
}
