package Practice;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    /**
     * LeetCode 763: Partition Labels
     * Difficulty: Medium
     * 
     * You are given a string s. We want to partition the string into as many parts 
     * as possible so that each letter appears in at most one part.
     * 
     * Note that the partition is done so that after concatenating all the parts in order, 
     * the resultant string should be s.
     * 
     * Return a list of integers representing the size of these parts.
     * 
     * Approach:
     * - Greedy with Last Occurrence Map.
     * - First pass: Record the last occurrence index of each character in s.
     * - Second pass: Traverse the string maintaining the current partition's boundaries.
     *   The end of the current partition must be at least the maximum last occurrence of 
     *   all characters seen so far in this partition.
     * - When the loop index reaches this 'end', we can close the current partition and 
     *   record its size (end - start + 1).
     * 
     * Complexity:
     * - Time: O(n), where n = s.length() (two linear scans).
     * - Space: O(1), auxiliary space is bounded by the alphabet size (26 integers).
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        // Store the last index where each character appears
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']);

            // If current index reaches the maximum boundary for all characters seen so far,
            // we have found a valid partition.
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PartitionLabels solution = new PartitionLabels();

        // Test 1: "ababcbacadefegdehijhklij"
        // Partitions: "ababcbaca" (size 9), "defegde" (size 7), "hijhklij" (size 8)
        String s1 = "ababcbacadefegdehijhklij";
        System.out.println("Test 1: " + solution.partitionLabels(s1)); 
        // Expected: [9, 7, 8]

        // Test 2: "eccbbbbdec"
        // Partitions: "eccbbbbdec" (size 10, all letters overlap)
        String s2 = "eccbbbbdec";
        System.out.println("Test 2: " + solution.partitionLabels(s2)); 
        // Expected: [10]

        // Test 3: "abc"
        // Partitions: "a" (1), "b" (1), "c" (1)
        String s3 = "abc";
        System.out.println("Test 3: " + solution.partitionLabels(s3)); 
        // Expected: [1, 1, 1]
    }
}
