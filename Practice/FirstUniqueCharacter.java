package Practice;

public class FirstUniqueCharacter {
    /**
     * Practice Question: First Unique Character in a String
     * 
     * Given a string s, find the first non-repeating character in it and return its index. 
     * If it does not exist, return -1.
     */
    public int firstUniqChar(String s) {
        // Step 1: Create an array to store the frequency of each character.
        // We use size 26 because the problem assumes only lowercase English letters.
        int[] charCounts = new int[26];
        
        // Step 2: Populate the frequency array.
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
        }
        
        // Step 3: Iterate through the string AGAIN to find the first character with a count of 1.
        for (int i = 0; i < s.length(); i++) {
            if (charCounts[s.charAt(i) - 'a'] == 1) {
                return i; // Found the first unique character! Return its index.
            }
        }
        
        // If no unique character was found, return -1.
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueCharacter solution = new FirstUniqueCharacter();
        
        // Test cases
        System.out.println("Test 1 ('leetcode'): " + solution.firstUniqChar("leetcode")); // Expected: 0 (character 'l')
        System.out.println("Test 2 ('loveleetcode'): " + solution.firstUniqChar("loveleetcode")); // Expected: 2 (character 'v')
        System.out.println("Test 3 ('aabb'): " + solution.firstUniqChar("aabb")); // Expected: -1 (all characters repeat)
    }
}
