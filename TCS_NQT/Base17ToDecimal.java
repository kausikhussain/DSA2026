package TCS_NQT;

import java.util.Scanner;

public class Base17ToDecimal {
    /**
     * TCS NQT Coding Question: Sweet Seventeen (Base-17 to Decimal Conversion)
     * 
     * Problem Statement:
     * Given a number represented as a string in Base-17, convert and return its 
     * equivalent decimal (Base-10) value.
     * 
     * Base-17 uses 17 distinct symbols:
     * - '0' through '9' with values 0 to 9
     * - 'A' through 'G' (or 'a' through 'g') with values:
     *   A/a = 10, B/b = 11, C/c = 12, D/d = 13, E/e = 14, F/f = 15, G/g = 16
     * 
     * Approach:
     * - Parse the string from left to right (Horner's rule):
     *   decimalValue = decimalValue * 17 + digitValue
     * - Validate each character to ensure it belongs to the valid Base-17 digit set.
     * 
     * Complexity:
     * - Time Complexity: O(L), where L is the length of the string.
     * - Space Complexity: O(1) auxiliary space.
     */

    public static Long convertBase17ToDecimal(String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }

        s = s.trim();
        long result = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int digitValue;

            if (ch >= '0' && ch <= '9') {
                digitValue = ch - '0';
            } else if (ch >= 'A' && ch <= 'G') {
                digitValue = ch - 'A' + 10;
            } else if (ch >= 'a' && ch <= 'g') {
                digitValue = ch - 'a' + 10;
            } else {
                return null; // Invalid Base-17 character
            }

            result = result * 17 + digitValue;
        }

        return result;
    }

    public static void printConversion(String input) {
        Long result = convertBase17ToDecimal(input);
        if (result == null) {
            System.out.println("Input: \"" + input + "\" -> INVALID INPUT");
        } else {
            System.out.println("Input: \"" + input + "\" -> Decimal: " + result);
        }
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");

        printConversion("23GF");  // Expected: 10980
        printConversion("1A");    // Expected: 27
        printConversion("G");     // Expected: 16
        printConversion("10");    // Expected: 17
        printConversion("c");     // Expected: 12 (lowercase support)
        printConversion("23H");   // Expected: INVALID INPUT ('H' is outside base 17)

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNext()) {
                String input = sc.next();
                Long res = convertBase17ToDecimal(input);
                if (res == null) {
                    System.out.println("INVALID INPUT");
                } else {
                    System.out.println(res);
                }
            }
            sc.close();
        }
    }
}
