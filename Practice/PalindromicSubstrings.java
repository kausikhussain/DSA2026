package Practice;

public class PalindromicSubstrings {
    /**
     * LeetCode 647: Palindromic Substrings
     * 
     * Given a string s, return the number of palindromic substrings in it.
     * A string is a palindrome when it reads the same backward as forward.
     * A substring is a contiguous sequence of characters within the string.
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        int totalCount = 0;

        // There are 2N - 1 possible centers for palindromes
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes (single character center)
            totalCount += countPalindromesAroundCenter(s, i, i);
            // Count even-length palindromes (two character center)
            totalCount += countPalindromesAroundCenter(s, i, i + 1);
        }

        return totalCount;
    }

    private int countPalindromesAroundCenter(String s, int left, int right) {
        int count = 0;
        // Expand outward as long as boundaries are within string and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstrings solution = new PalindromicSubstrings();

        System.out.println("Test 1 (\"abc\"): " + solution.countSubstrings("abc"));
        // Expected: 3 ("a", "b", "c")

        System.out.println("Test 2 (\"aaa\"): " + solution.countSubstrings("aaa"));
        // Expected: 6 ("a", "a", "a", "aa", "aa", "aaa")

        System.out.println("Test 3 (\"racecar\"): " + solution.countSubstrings("racecar"));
        // Expected: 10
    }
}
