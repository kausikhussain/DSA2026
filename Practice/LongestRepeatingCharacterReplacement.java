package Practice;

public class LongestRepeatingCharacterReplacement {
    /**
     * LeetCode 424: Longest Repeating Character Replacement
     * 
     * You are given a string s and an integer k. You can choose any character of the string 
     * and change it to any other uppercase English character at most k times.
     * 
     * Return the length of the longest substring containing the same letter you can get 
     * after performing the operations.
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int charIndex = s.charAt(right) - 'A';
            count[charIndex]++;
            maxFreq = Math.max(maxFreq, count[charIndex]);

            // Number of characters needing replacement in window is (windowLength - maxFreq).
            // If this exceeds k, shrink the window from the left.
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();

        // Test 1: "ABAB", k = 2
        System.out.println("Test 1 (\"ABAB\", k=2): " + solution.characterReplacement("ABAB", 2)); 
        // Expected: 4 (Replace two 'A's with 'B's or two 'B's with 'A's)

        // Test 2: "AABABBA", k = 1
        System.out.println("Test 2 (\"AABABBA\", k=1): " + solution.characterReplacement("AABABBA", 1)); 
        // Expected: 4 (Substring "AABA" -> "AAAA" or "BB" -> "BBBB")

        // Test 3: "ABBB", k = 2
        System.out.println("Test 3 (\"ABBB\", k=2): " + solution.characterReplacement("ABBB", 2)); 
        // Expected: 4
    }
}
