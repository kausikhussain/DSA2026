package Practice;

public class DecodeWays {
    /**
     * LeetCode 91: Decode Ways
     * 
     * A message containing letters from A-Z can be encoded into numbers using the mapping:
     * 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26"
     * 
     * Given a string s containing only digits, return the number of ways to decode it.
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();

        // Space-optimized DP:
        // prev2 corresponds to dp[i - 2], initialized to 1 for the empty string base case
        int prev2 = 1;
        // prev1 corresponds to dp[i - 1], initialized to 1 since s[0] != '0'
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {
            int current = 0;

            // Check single digit decode: s[i - 1] must be between '1' and '9'
            char singleChar = s.charAt(i - 1);
            if (singleChar != '0') {
                current += prev1;
            }

            // Check two-digit decode: s[i - 2 ... i - 1] must be between 10 and 26
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }

            // Move state forward
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
