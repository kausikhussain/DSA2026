package Practice;

public class PermutationInString {
    /**
     * LeetCode 567: Permutation in String
     * Difficulty: Medium
     * 
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1, 
     * or false otherwise.
     * 
     * In other words, return true if one of s1's permutations is the substring of s2.
     * 
     * Approach:
     * - Fixed-size Sliding Window with Character Frequency Matching.
     * - A window of size s1.length() slides across s2.
     * - We maintain frequency counts of characters in s1 and current window in s2.
     * - Track the number of matching character frequencies (out of 26 letters).
     * - When matches == 26, a valid permutation is present.
     * 
     * Complexity:
     * - Time: O(n), where n = s2.length() (each character is visited at most twice).
     * - Space: O(1), since arrays are fixed at size 26 for lowercase English letters.
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) {
                matches++;
            }
        }

        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {
            if (matches == 26) {
                return true;
            }

            // Include incoming character from the right
            int inIndex = s2.charAt(right) - 'a';
            s2Count[inIndex]++;
            if (s2Count[inIndex] == s1Count[inIndex]) {
                matches++;
            } else if (s2Count[inIndex] == s1Count[inIndex] + 1) {
                matches--;
            }

            // Exclude outgoing character from the left
            int outIndex = s2.charAt(left) - 'a';
            s2Count[outIndex]--;
            if (s2Count[outIndex] == s1Count[outIndex]) {
                matches++;
            } else if (s2Count[outIndex] == s1Count[outIndex] - 1) {
                matches--;
            }

            left++;
        }

        return matches == 26;
    }

    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();

        // Test 1: Permutation present
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println("Test 1: " + solution.checkInclusion(s1, s2)); // Expected: true ("ba")

        // Test 2: Permutation not present
        String s3 = "ab", s4 = "eidboaoo";
        System.out.println("Test 2: " + solution.checkInclusion(s3, s4)); // Expected: false

        // Test 3: Exact match
        String s5 = "abc", s6 = "cba";
        System.out.println("Test 3: " + solution.checkInclusion(s5, s6)); // Expected: true

        // Test 4: s1 longer than s2
        String s7 = "hello", s8 = "he";
        System.out.println("Test 4: " + solution.checkInclusion(s7, s8)); // Expected: false
    }
}
