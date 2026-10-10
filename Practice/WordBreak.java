package Practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    /**
     * LeetCode 139: Word Break
     * 
     * Given a string s and a dictionary of strings wordDict, return true if s can be 
     * segmented into a space-separated sequence of one or more dictionary words.
     * 
     * The same word in the dictionary may be reused multiple times in the segmentation.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) return true;

        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();

        // dp[i] is true if substring s[0...i-1] can be segmented into dictionary words
        boolean[] dp = new boolean[n + 1];

        // Base case: empty string
        dp[0] = true;

        // Find the maximum length of words in dictionary to optimize inner search window
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 1; i <= n; i++) {
            // Only search backwards up to maxLen characters
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid split for prefix i
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        WordBreak solution = new WordBreak();

        // Test 1: "leetcode", ["leet", "code"]
        System.out.println("Test 1: " + solution.wordBreak("leetcode", Arrays.asList("leet", "code"))); 
        // Expected: true

        // Test 2: "applepenapple", ["apple", "pen"]
        System.out.println("Test 2: " + solution.wordBreak("applepenapple", Arrays.asList("apple", "pen"))); 
        // Expected: true

        // Test 3: "catsandog", ["cats", "dog", "sand", "and", "cat"]
        System.out.println("Test 3: " + solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); 
        // Expected: false
    }
}
