package Practice;

public class LengthOfLastWord {
    /**
     * Practice Question: Length of Last Word
     * 
     * Given a string s consisting of words and spaces, return the length of the last word in the string.
     * A word is a maximal substring consisting of non-space characters only.
     */
    public int lengthOfLastWord(String s) {
        // We can solve this efficiently by iterating backward from the end of the string
        int length = 0;
        int i = s.length() - 1;
        
        // Step 1: Skip any trailing spaces at the very end of the string
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // Step 2: Count the characters of the last word until we hit another space or the beginning
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }

    public static void main(String[] args) {
        LengthOfLastWord solution = new LengthOfLastWord();
        
        System.out.println("Test 1 ('Hello World'): " + solution.lengthOfLastWord("Hello World"));           // Expected: 5
        System.out.println("Test 2 ('   fly me   to   the moon  '): " + solution.lengthOfLastWord("   fly me   to   the moon  ")); // Expected: 4
        System.out.println("Test 3 ('luffy is still joyboy'): " + solution.lengthOfLastWord("luffy is still joyboy")); // Expected: 6
    }
}
