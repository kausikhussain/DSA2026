package Practice;

public class DecodeWays {
    /**
     * LeetCode 91: Decode Ways
     * Difficulty: Medium
     * 
     * A message containing letters from A-Z can be encoded into numbers using the mapping:
     * 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26"
     * 
     * Given a string s containing only digits, return the number of ways to decode it.
     * 
     * Approach:
     * - Space-Optimized Dynamic Programming (O(1) Auxiliary Space).
     * - Single-digit decode: If s[i] != '0', it contributes prev1 ways.
     * - Two-digit decode: If number (s[i-1..i]) is between 10 and 26, it contributes prev2 ways.
     * - Roll the states: prev2 = prev1, prev1 = current.
     * 
     * Complexity:
     * - Time: O(n), where n = s.length() (single linear pass).
     * - Space: O(1), constant extra memory.
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int prev2 = 1;
        int prev1 = 0;

        if (s.charAt(0) != '0') {
            prev1 = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            int current = 0;

            // Single-digit decoding
            if (s.charAt(i) != '0') {
                current += prev1;
            }

            // Two-digit decoding
            int number = (s.charAt(i - 1) - '0') * 10
                       + (s.charAt(i) - '0');

            if (number >= 10 && number <= 26) {
                current += prev2;
            }

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        DecodeWays solution = new DecodeWays();

        System.out.println("Test 1 (\"12\"): " + solution.numDecodings("12")); 
        // Expected: 2 ("AB" or "L")

        System.out.println("Test 2 (\"226\"): " + solution.numDecodings("226")); 
        // Expected: 3 ("BZ", "VF", or "BBF")

        System.out.println("Test 3 (\"06\"): " + solution.numDecodings("06")); 
        // Expected: 0 (leading zero is invalid)

        System.out.println("Test 4 (\"10\"): " + solution.numDecodings("10")); 
        // Expected: 1 ("J")
    }
}
