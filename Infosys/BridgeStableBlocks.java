package Infosys;

import java.util.Arrays;

public class BridgeStableBlocks {
    
    // Helper Point class to perform 2D LIS
    static class Point implements Comparable<Point> {
        long a;
        long b;
        
        Point(long a, long b) {
            this.a = a;
            this.b = b;
        }
        
        @Override
        public int compareTo(Point other) {
            if (this.a != other.a) {
                return Long.compare(this.a, other.a);
            }
            return Long.compare(this.b, other.b);
        }
    }

    /**
     * Question: Bridge Construction Using Blocks (Infosys)
     * 
     * Find the minimum number of replacements to make the absolute difference 
     * between any two adjacent blocks at most K.
     * 
     * Mathematical formulation:
     * We want to find the longest subsequence of indices where:
     * |strength[j] - strength[i]| <= (j - i) * K for all i < j.
     * 
     * This translates to:
     * A[i] = i * K - strength[i]
     * B[i] = i * K + strength[i]
     * We need both A[i] and B[i] to be non-decreasing.
     * This is a 2D LIS problem solved in O(N log N) by sorting by A and finding LNDS on B.
     */
    public int minimumReplacements(int[] strength, int K) {
        int n = strength.length;
        if (n <= 1) return 0;
        
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            long a = (long) i * K - strength[i];
            long b = (long) i * K + strength[i];
            points[i] = new Point(a, b);
        }
        
        // Step 1: Sort the points primarily by A ascending, and secondarily by B ascending
        Arrays.sort(points);
        
        // Step 2: Extract B values and find the Longest Non-Decreasing Subsequence (LNDS)
        long[] bValues = new long[n];
        for (int i = 0; i < n; i++) {
            bValues[i] = points[i].b;
        }
        
        int lndsLength = lengthOfLNDS(bValues);
        
        // Minimum replacements is total elements minus the maximum elements we can keep
        return n - lndsLength;
    }
    
    // Finds the length of the Longest Non-Decreasing Subsequence in O(N log N)
    private int lengthOfLNDS(long[] arr) {
        long[] tails = new long[arr.length];
        int len = 0;
        for (long x : arr) {
            int i = upperBound(tails, 0, len, x);
            tails[i] = x;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
    
    // Standard upper_bound binary search to find the first element strictly greater than key
    private int upperBound(long[] arr, int start, int end, long key) {
        int low = start;
        int high = end;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        BridgeStableBlocks solution = new BridgeStableBlocks();
        
        // Test case 1
        int[] strength1 = {2, 5, 8, 12, 15};
        int k1 = 3;
        System.out.println("Test 1: " + solution.minimumReplacements(strength1, k1)); 
        // Expected: 2 (since 15-11 = 4 violates K=3, we must replace 2 blocks to make it [2, 5, 8, 11, 14])

        // Test case 2
        int[] strength2 = {1, 7, 2, 10, 4, 9};
        int k2 = 2;
        System.out.println("Test 2: " + solution.minimumReplacements(strength2, k2)); 
        // Expected: 3
    }
}
