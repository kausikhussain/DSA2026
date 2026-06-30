package Practice;

import java.util.HashMap;

public class RomanToInteger {
    /**
     * Practice Question: Roman to Integer
     * 
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     * Given a roman numeral string, convert it to an integer.
     */
    public int romanToInt(String s) {
        // Map to store the values of each Roman numeral symbol
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int total = 0;
        
        // Loop through the string
        for (int i = 0; i < s.length(); i++) {
            int currentVal = map.get(s.charAt(i));
            
            // Check if there is a next character and if it's greater than the current one
            // This is the rule for subtraction (e.g., IV = 4, IX = 9)
            if (i < s.length() - 1 && currentVal < map.get(s.charAt(i + 1))) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
        }
        
        return total;
    }

    public static void main(String[] args) {
        RomanToInteger solution = new RomanToInteger();
        
        System.out.println("Test 1 (III): " + solution.romanToInt("III"));       // Expected: 3
        System.out.println("Test 2 (LVIII): " + solution.romanToInt("LVIII"));   // Expected: 58
        System.out.println("Test 3 (MCMXCIV): " + solution.romanToInt("MCMXCIV")); // Expected: 1994
    }
}
