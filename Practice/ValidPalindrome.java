package Practice;

public class ValidPalindrome {
    /**
     * Practice Question: Valid Palindrome
     * 
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
     * and removing all non-alphanumeric characters, it reads the same forward and backward.
     * Alphanumeric characters include letters and numbers.
     * 
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */
    public boolean isPalindrome(String s) {
        // Approach: Two Pointers
        // We use one pointer at the start and one at the end of the string.
        // We move them towards the center, skipping non-alphanumeric characters.
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Move left pointer if it's not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Move right pointer if it's not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare the characters (ignoring case)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // Mismatch found!
            }
            
            // Move both pointers inward for the next comparison
            left++;
            right--;
        }
        
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        
        System.out.println("Test 1: " + solution.isPalindrome("A man, a plan, a canal: Panama")); // Expected: true
        System.out.println("Test 2: " + solution.isPalindrome("race a car")); // Expected: false
        System.out.println("Test 3: " + solution.isPalindrome(" ")); // Expected: true
    }
}
