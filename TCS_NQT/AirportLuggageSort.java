package TCS_NQT;

import java.util.Arrays;
import java.util.Scanner;

public class AirportLuggageSort {
    /**
     * TCS NQT Coding Question: Airport Luggage Risk Sorting (Sort 0s, 1s, and 2s)
     * 
     * Problem Statement:
     * At an airport security checkpoint, baggage items are categorized based on risk assessment:
     * - 0 represents Low Risk (Green)
     * - 1 represents Medium Risk (Yellow)
     * - 2 represents High Risk (Red)
     * 
     * Given an integer array representing the risk categories of N baggage items, 
     * sort the array in-place such that all 0s appear first, followed by all 1s, and finally all 2s.
     * 
     * Approach:
     * - Dutch National Flag (DNF) Algorithm.
     * - Maintain three pointers:
     *   - low: boundary for 0s (elements before low are 0)
     *   - mid: current element being examined
     *   - high: boundary for 2s (elements after high are 2)
     * - Traverse while mid <= high:
     *   - If arr[mid] == 0: swap(arr[low], arr[mid]), low++, mid++
     *   - If arr[mid] == 1: mid++
     *   - If arr[mid] == 2: swap(arr[mid], arr[high]), high--
     * 
     * Complexity:
     * - Time Complexity: O(N), single-pass linear scan.
     * - Space Complexity: O(1), in-place sorting without extra memory.
     */

    public static void sortLuggage(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else { // arr[mid] == 2
                swap(arr, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");

        int[] test1 = {1, 0, 2, 0, 1, 0, 2};
        sortLuggage(test1);
        System.out.println("Test 1: " + Arrays.toString(test1)); 
        // Expected: [0, 0, 0, 1, 1, 2, 2]

        int[] test2 = {2, 0, 1};
        sortLuggage(test2);
        System.out.println("Test 2: " + Arrays.toString(test2)); 
        // Expected: [0, 1, 2]

        int[] test3 = {0, 0, 0};
        sortLuggage(test3);
        System.out.println("Test 3: " + Arrays.toString(test3)); 
        // Expected: [0, 0, 0]

        int[] test4 = {2, 2, 1, 1, 0};
        sortLuggage(test4);
        System.out.println("Test 4: " + Arrays.toString(test4)); 
        // Expected: [0, 1, 1, 2, 2]

        int[] test5 = {1};
        sortLuggage(test5);
        System.out.println("Test 5: " + Arrays.toString(test5)); 
        // Expected: [1]

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
                }
                sortLuggage(arr);
                for (int i = 0; i < n; i++) {
                    System.out.print(arr[i] + (i == n - 1 ? "\n" : " "));
                }
            }
            sc.close();
        }
    }
}
