package Practice;

public class IsSubsequence {
    /**
     * Practice Question: Is Subsequence
     * 
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     * 
     * A subsequence of a string is a new string that is formed from the original string 
     * by deleting some (can be none) of the characters without disturbing the relative 
     * positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     */
    public boolean isSubsequence(String s, String t) {
        // Base case: an empty string is always a subsequence of any string
        if (s.length() == 0) return true;
        
        // Two Pointers approach
        int sPointer = 0;
        int tPointer = 0;
        
        // Loop through both strings
        while (tPointer < t.length()) {
            // If the characters match, move the sPointer forward
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
                // If sPointer reaches the end of s, it means we found the entire subsequence
                if (sPointer == s.length()) {
                    return true;
                }
            }
            // Always move the tPointer forward to keep scanning the source string
            tPointer++;
        }
        
        return false;
    }

    public static void main(String[] args) {
        IsSubsequence solution = new IsSubsequence();
        
        System.out.println("Test 1 s=\"abc\", t=\"ahbgdc\": " + solution.isSubsequence("abc", "ahbgdc")); // Expected: true
        System.out.println("Test 2 s=\"axc\", t=\"ahbgdc\": " + solution.isSubsequence("axc", "ahbgdc")); // Expected: false
    }
}
