package Practice;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

    // Phone keypad mapping from digits '2' through '9'
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    /**
     * LeetCode 17: Letter Combinations of a Phone Number
     * 
     * Given a string containing digits from 2-9 inclusive, return all possible 
     * letter combinations that the number could represent.
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // Base case: if current path length equals digits length, a valid combination is formed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));               // Choose
            backtrack(digits, index + 1, current, result);   // Explore
            current.deleteCharAt(current.length() - 1);       // Unchoose (backtrack)
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();

        System.out.println("Test 1 (\"23\"): " + solution.letterCombinations("23"));
        // Expected: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        System.out.println("Test 2 (\"\"): " + solution.letterCombinations(""));
        // Expected: []

        System.out.println("Test 3 (\"2\"): " + solution.letterCombinations("2"));
        // Expected: ["a","b","c"]
    }
}
