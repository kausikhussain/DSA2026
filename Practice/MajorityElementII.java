package Practice;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 229: Majority Element II
 * Difficulty: Medium
 * 
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * 
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * 
 * Approach:
 * - Extended Boyer-Moore Voting Algorithm.
 * - By mathematical deduction, there can be at most 2 elements that appear strictly more 
 *   than ⌊ n/3 ⌋ times in an array of size n.
 * - Pass 1 (Candidate Selection):
 *   - Maintain two candidates (candidate1, candidate2) and their respective vote counts (count1, count2).
 *   - If num matches candidate1, increment count1.
 *   - Else if num matches candidate2, increment count2.
 *   - Else if count1 == 0, set candidate1 = num, count1 = 1.
 *   - Else if count2 == 0, set candidate2 = num, count2 = 1.
 *   - Else, decrement both count1 and count2 (triplet cancellation).
 * - Pass 2 (Verification):
 *   - Reset counters and count the exact occurrences of candidate1 and candidate2 in the array.
 *   - Any candidate with frequency strictly greater than ⌊ n/3 ⌋ is added to the result.
 * 
 * Complexity:
 * - Time: O(n), two linear passes over the array.
 * - Space: O(1), auxiliary space uses only constant primitive counters.
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;

        // Phase 1: Find up to two potential majority candidates
        for (int num : nums) {
            if (count1 > 0 && num == candidate1) {
                count1++;
            } else if (count2 > 0 && num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Phase 2: Verify candidate frequencies
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        int threshold = nums.length / 3;
        if (count1 > threshold) {
            result.add(candidate1);
        }
        if (count2 > threshold) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        MajorityElementII solution = new MajorityElementII();

        // Test 1: [3, 2, 3] -> threshold = 3/3 = 1 -> [3]
        int[] nums1 = {3, 2, 3};
        System.out.println("Test 1 [3, 2, 3]: " + solution.majorityElement(nums1)); 
        // Expected: [3]

        // Test 2: [1] -> threshold = 1/3 = 0 -> [1]
        int[] nums2 = {1};
        System.out.println("Test 2 [1]: " + solution.majorityElement(nums2)); 
        // Expected: [1]

        // Test 3: [1, 2] -> threshold = 2/3 = 0 -> [1, 2]
        int[] nums3 = {1, 2};
        System.out.println("Test 3 [1, 2]: " + solution.majorityElement(nums3)); 
        // Expected: [1, 2]

        // Test 4: [2, 1, 1, 3, 1, 4, 5, 6] -> n = 8, threshold = 2 -> [1] (appears 3 times)
        int[] nums4 = {2, 1, 1, 3, 1, 4, 5, 6};
        System.out.println("Test 4: " + solution.majorityElement(nums4)); 
        // Expected: [1]

        // Test 5: [0, 0, 0] -> [0]
        int[] nums5 = {0, 0, 0};
        System.out.println("Test 5 [0, 0, 0]: " + solution.majorityElement(nums5)); 
        // Expected: [0]
    }
}
