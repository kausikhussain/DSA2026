package TCS_NQT;

import java.util.Scanner;

public class CountElementsGreaterThanPrior {
    /**
     * TCS NQT Coding Question: Elements Greater Than Prior Elements
     * 
     * Problem Statement:
     * Given an integer array representing heights or numerical values of size N,
     * count the total number of elements that are strictly greater than all the 
     * elements preceding them.
     * 
     * Note:
     * - The first element is always counted since no elements exist prior to it.
     * - If the array is empty (N = 0), the answer is 0.
     * 
     * Approach:
     * - Single-pass linear scan with running maximum tracker (maxSoFar).
     * - Initialize count = 1 and maxSoFar = arr[0] (for non-empty array).
     * - For each element from index 1 to N - 1:
     *   - If arr[i] > maxSoFar:
     *     - Increment count.
     *     - Update maxSoFar = arr[i].
     * 
     * Complexity:
     * - Time Complexity: O(N), single pass over the array.
     * - Space Complexity: O(1), constant auxiliary memory.
     */

    public static int countGreaterThanPrior(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 1; // First element is always counted
        int maxSoFar = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxSoFar) {
                count++;
                maxSoFar = arr[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");

        int[] test1 = {7, 4, 8, 2, 9};
        System.out.println("Test 1 [7, 4, 8, 2, 9]: " + countGreaterThanPrior(test1)); 
        // Expected: 3 (7, 8, 9)

        int[] test2 = {3, 4, 5, 8, 9};
        System.out.println("Test 2 [3, 4, 5, 8, 9]: " + countGreaterThanPrior(test2)); 
        // Expected: 5 (all strictly increasing)

        int[] test3 = {9, 8, 7, 6, 5};
        System.out.println("Test 3 [9, 8, 7, 6, 5]: " + countGreaterThanPrior(test3)); 
        // Expected: 1 (only first element 9)

        int[] test4 = {5, 5, 5, 5};
        System.out.println("Test 4 [5, 5, 5, 5]: " + countGreaterThanPrior(test4)); 
        // Expected: 1 (duplicates are not strictly greater)

        int[] test5 = {42};
        System.out.println("Test 5 [42]: " + countGreaterThanPrior(test5)); 
        // Expected: 1

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                int[] inputArr = new int[n];
                for (int i = 0; i < n; i++) {
                    inputArr[i] = sc.nextInt();
                }
                System.out.println(countGreaterThanPrior(inputArr));
            }
            sc.close();
        }
    }
}
