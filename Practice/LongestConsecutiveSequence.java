package Practice;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    /**
     * LeetCode 128: Longest Consecutive Sequence
     * 
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     * You must write an algorithm that runs in O(n) time.
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // We use a HashSet to achieve O(1) lookups.
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int longestStreak = 0;
        
        for (int num : set) {
            // Check if this number is the START of a consecutive sequence.
            // If the set contains (num - 1), then 'num' is not the start of a sequence.
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // Keep incrementing and checking if the next consecutive number exists
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Test 1 [100,4,200,1,3,2]: " + solution.longestConsecutive(nums1)); 
        // Expected: 4 (sequence is [1, 2, 3, 4])
        
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Test 2 [0,3,7,2,5,8,4,6,0,1]: " + solution.longestConsecutive(nums2)); 
        // Expected: 9 (sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8])
    }
}
