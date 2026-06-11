package Practice;

public class ValidAnagram {
    /**
     * Practice Question: Valid Anagram
     * 
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
     * typically using all the original letters exactly once.
     */
    public boolean isAnagram(String s, String t) {
        // If the lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        // Create a frequency array for all 26 lowercase English letters
        int[] charCounts = new int[26];
        
        // Increment the count for characters in s, decrement for characters in t
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }
        
        // If they are valid anagrams, all counts should perfectly cancel out to 0
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        
        // Test cases
        System.out.println("Test 1 (anagram, nagaram): " + solution.isAnagram("anagram", "nagaram")); // true
        System.out.println("Test 2 (rat, car): " + solution.isAnagram("rat", "car")); // false
    }
}
