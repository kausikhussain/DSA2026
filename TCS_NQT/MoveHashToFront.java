package TCS_NQT;

import java.util.Scanner;

public class MoveHashToFront {
    /**
     * TCS NQT Coding Question: Move Special Character (#) to Beginning
     * 
     * Problem Statement:
     * You are given a string s containing alphanumeric characters and special '#' symbols.
     * Your task is to move all the '#' characters to the front (beginning) of the string 
     * while preserving the original relative order of all the other characters.
     * 
     * Examples:
     * - Input: "Move#Hash#to#Front"
     *   Output: "###MoveHashtoFront"
     * - Input: "###Hello#World"
     *   Output: "####HelloWorld"
     * - Input: "NoHashHere"
     *   Output: "NoHashHere"
     * 
     * Approach:
     * - Single-pass linear scan.
     * - Traverse the string:
     *   - Count the total occurrences of '#'.
     *   - Collect all non-'#' characters in their original relative sequence.
     * - Build the resulting string by prepending the counted '#' characters 
     *   followed by the collected non-'#' characters.
     * 
     * Complexity:
     * - Time Complexity: O(N), where N is the length of the string.
     * - Space Complexity: O(N) for constructing the output string.
     */

    public static String moveHash(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder hashPart = new StringBuilder();
        StringBuilder charPart = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '#') {
                hashPart.append('#');
            } else {
                charPart.append(ch);
            }
        }

        return hashPart.append(charPart).toString();
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");

        String test1 = "Move#Hash#to#Front";
        System.out.println("Test 1 (\"Move#Hash#to#Front\"): " + moveHash(test1));
        // Expected: "###MoveHashtoFront"

        String test2 = "###Hello#World";
        System.out.println("Test 2 (\"###Hello#World\"): " + moveHash(test2));
        // Expected: "####HelloWorld"

        String test3 = "NoHashHere";
        System.out.println("Test 3 (\"NoHashHere\"): " + moveHash(test3));
        // Expected: "NoHashHere"

        String test4 = "#####";
        System.out.println("Test 4 (\"#####\"): " + moveHash(test4));
        // Expected: "#####"

        String test5 = "a#b#c#d";
        System.out.println("Test 5 (\"a#b#c#d\"): " + moveHash(test5));
        // Expected: "###abcd"

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                System.out.println(moveHash(input));
            }
            sc.close();
        }
    }
}
