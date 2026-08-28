package Practice;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {
    /**
     * LeetCode 3: Longest Substring Without Repeating Characters
     * 
     * Given a string s, find the length of the longest substring without duplicate characters.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        // Sliding Window with a HashMap storing the most recent index of each character
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character was seen inside the current window, move the left pointer
            if (lastSeen.containsKey(currentChar)) {
                left = Math.max(left, lastSeen.get(currentChar) + 1);
            }

            // Update the latest index of currentChar
            lastSeen.put(currentChar, right);

            // Calculate current window length and update max
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

        System.out.println("Test 1 (\"abcabcbb\"): " + solution.lengthOfLongestSubstring("abcabcbb")); 
        // Expected: 3 ("abc")

        System.out.println("Test 2 (\"bbbbb\"): " + solution.lengthOfLongestSubstring("bbbbb")); 
        // Expected: 1 ("b")

        System.out.println("Test 3 (\"pwwkew\"): " + solution.lengthOfLongestSubstring("pwwkew")); 
        // Expected: 3 ("wke")
    }
}
