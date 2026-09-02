package Practice;

public class LongestPalindromicSubstring {
    /**
     * LeetCode 5: Longest Palindromic Substring
     * 
     * Given a string s, return the longest palindromic substring in s.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes (single character center)
            int len1 = expandAroundCenter(s, i, i);
            // Check even-length palindromes (two character center)
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                // Update start and end boundaries of the longest palindrome
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome = (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

        System.out.println("Test 1 (\"babad\"): " + solution.longestPalindrome("babad"));
        // Expected: "bab" or "aba"

        System.out.println("Test 2 (\"cbbd\"): " + solution.longestPalindrome("cbbd"));
        // Expected: "bb"

        System.out.println("Test 3 (\"a\"): " + solution.longestPalindrome("a"));
        // Expected: "a"
    }
}
