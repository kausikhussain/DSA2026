package TCS_NQT;

import java.util.*;

public class MaximumProductOfThreeNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;

        int n = sc.nextInt();
        if(n < 3) return; // Need at least 3 elements

        int[] arr = new int[n];

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {

            arr[i] = sc.nextInt();

            // Track Largest three
            if(arr[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            } else if(arr[i] > max2) {
                max3 = max2;
                max2 = arr[i];
            } else if(arr[i] > max3) {
                max3 = arr[i];
            }

            // Track Smallest two
            if(arr[i] < min1) {
                min2 = min1;
                min1 = arr[i];
            } else if(arr[i] < min2) {
                min2 = arr[i];
            }
        }

        // The maximum product can be either the 3 largest elements
        long p1 = 1L * max1 * max2 * max3;

        // Or the 2 smallest elements (which could be negative, and multiplying them gives a large positive)
        // multiplied by the 1 largest element
        long p2 = 1L * min1 * min2 * max1;

        System.out.println(Math.max(p1, p2));
        sc.close();
    }
}
