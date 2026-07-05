package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IntersectionOfTwoArraysTwo {
    /**
     * Practice Question: Intersection of Two Arrays II
     * 
     * Given two integer arrays nums1 and nums2, return an array of their intersection. 
     * Each element in the result must appear as many times as it shows in both arrays 
     * and you may return the result in any order.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // We use a HashMap to store the frequencies of elements in nums1
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        ArrayList<Integer> intersectionList = new ArrayList<>();
        // Iterate through nums2 and check if it's in the map with a frequency > 0
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                intersectionList.add(num);
                // Decrement the frequency in the map
                map.put(num, map.get(num) - 1);
            }
        }
        
        // Convert the List to a primitive int array
        int[] result = new int[intersectionList.size()];
        for (int i = 0; i < intersectionList.size(); i++) {
            result[i] = intersectionList.get(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArraysTwo solution = new IntersectionOfTwoArraysTwo();
        
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Test 1: " + Arrays.toString(solution.intersect(nums1, nums2))); // Expected: [2, 2]
        
        int[] nums1_2 = {4, 9, 5};
        int[] nums2_2 = {9, 4, 9, 8, 4};
        System.out.println("Test 2: " + Arrays.toString(solution.intersect(nums1_2, nums2_2))); // Expected: [4, 9] (or [9, 4])
    }
}
