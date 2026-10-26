package Practice;

/**
 * LeetCode 76: Minimum Window Substring
 * Difficulty: Hard
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum 
 * window substring of s such that every character in t (including duplicates) 
 * is included in the window. If there is no such substring, return the empty string "".
 * 
 * The testcases will be generated such that the answer is unique.
 * 
 * Approach:
 * - Dynamic Sliding Window with Frequency Tracking.
 * - Build a frequency table of all required characters in target string t.
 * - Track the number of distinct characters in t whose frequency requirements 
 *   are satisfied (formed == requiredUnique).
 * - Expand the right pointer to include characters into the window until all 
 *   required characters from t are satisfied.
 * - Once valid, contract from the left pointer to find the minimal valid window length.
 * - When contracting causes a required character's frequency to drop below its needed count,
 *   decrement formed and resume expanding the right pointer.
 * 
 * Complexity:
 * - Time: O(m + n), where m = s.length() and n = t.length(). Each character of s is visited 
 *         at most twice (once by right pointer, once by left pointer).
 * - Space: O(k), where k is the character set size (O(1) with fixed array of size 128 for ASCII).
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] targetCount = new int[128];
        int requiredUnique = 0;

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (targetCount[c] == 0) {
                requiredUnique++;
            }
            targetCount[c]++;
        }

        int[] windowCount = new int[128];
        int formed = 0;
        int left = 0;

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCount[c]++;

            // Check if frequency matches target exactly for this character
            if (targetCount[c] > 0 && windowCount[c] == targetCount[c]) {
                formed++;
            }

            // Shrink window from the left as long as it contains all desired characters
            while (formed == requiredUnique && left <= right) {
                int currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                windowCount[leftChar]--;

                // If removing leftChar violates the requirement, decrement formed
                if (targetCount[leftChar] > 0 && windowCount[leftChar] < targetCount[leftChar]) {
                    formed--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        // Test 1: Standard case
        // s = "ADOBECODEBANC", t = "ABC" -> "BANC"
        System.out.println("Test 1: \"" + solution.minWindow("ADOBECODEBANC", "ABC") + "\""); 
        // Expected: "BANC"

        // Test 2: Single character match
        // s = "a", t = "a" -> "a"
        System.out.println("Test 2: \"" + solution.minWindow("a", "a") + "\""); 
        // Expected: "a"

        // Test 3: Insufficient characters
        // s = "a", t = "aa" -> ""
        System.out.println("Test 3: \"" + solution.minWindow("a", "aa") + "\""); 
        // Expected: ""

        // Test 4: Substring at the end
        // s = "ab", t = "b" -> "b"
        System.out.println("Test 4: \"" + solution.minWindow("ab", "b") + "\""); 
        // Expected: "b"
    }
}
