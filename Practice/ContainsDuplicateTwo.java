package Practice;

import java.util.HashMap;

public class ContainsDuplicateTwo {
    /**
     * Practice Question: Contains Duplicate II
     * 
     * Given an integer array nums and an integer k, return true if there are two distinct 
     * indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // We use a HashMap to store the numbers we've seen and their latest index.
        // Key = number, Value = index of its latest occurrence
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // If the number was seen before, check if the distance is <= k
            if (map.containsKey(num)) {
                int lastSeenIndex = map.get(num);
                if (i - lastSeenIndex <= k) {
                    return true; // Found a duplicate within distance k!
                }
            }
            
            // Update the map with the current index (keeps the latest index for future checks)
            map.put(num, i);
        }
        
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateTwo solution = new ContainsDuplicateTwo();
        
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println("Test 1: " + solution.containsNearbyDuplicate(nums1, k1)); // Expected: true (indices 0 and 3, diff is 3)
        
        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println("Test 2: " + solution.containsNearbyDuplicate(nums2, k2)); // Expected: true (indices 2 and 3, diff is 1)
        
        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println("Test 3: " + solution.containsNearbyDuplicate(nums3, k3)); // Expected: false
    }
}
