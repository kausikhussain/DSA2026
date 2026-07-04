package Practice;

import java.util.HashMap;

public class SubarraySumEqualsK {
    /**
     * Practice Question: Subarray Sum Equals K
     * 
     * Given an array of integers nums and an integer k, 
     * return the total number of subarrays whose sum equals to k.
     * A subarray is a contiguous non-empty sequence of elements within an array.
     */
    public int subarraySum(int[] nums, int k) {
        // We use a prefix sum map to solve this in O(N) time.
        // Key = prefix sum, Value = frequency of this prefix sum
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: prefix sum of 0 has occurred once (empty prefix)
        prefixSumMap.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // If (currentSum - k) exists in our map, it means we have found
            // a subarray (or multiple subarrays) ending at the current index that sum to k.
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }
            
            // Add the current prefix sum to the map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("Test 1: " + solution.subarraySum(nums1, k1)); // Expected: 2 (subarrays: [1,1] at index 0..1 and 1..2)
        
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("Test 2: " + solution.subarraySum(nums2, k2)); // Expected: 2 (subarrays: [1,2] and [3])
    }
}
