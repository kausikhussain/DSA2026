package Infosys;

import java.util.Arrays;

public class MinimumPlatforms {
    /**
     * Infosys Medium / Classic Problem: Minimum Platforms Required for Railway Station
     * 
     * Given arrival and departure times of all trains that reach a railway station,
     * find the minimum number of platforms required so that no train is kept waiting.
     * 
     * Times are represented in 24-hour format as integers (e.g., 900 for 9:00 AM, 1100 for 11:00 AM).
     */
    public int findPlatform(int[] arr, int[] dep) {
        if (arr == null || dep == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // Step 1: Sort both arrival and departure arrays independently.
        // We only care about the chronological sequence of events (arrival vs departure),
        // not which specific train arrives or departs.
        Arrays.sort(arr);
        Arrays.sort(dep);

        // Two Pointers approach
        int i = 0; // Pointer for arrivals
        int j = 0; // Pointer for departures

        int platformsNeeded = 0;
        int maxPlatforms = 0;

        // Step 2: Traverse through all arrivals and departures chronologically
        while (i < n && j < n) {
            // If next event is an arrival, we need an additional platform
            if (arr[i] <= dep[j]) {
                platformsNeeded++;
                maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
                i++;
            } 
            // If next event is a departure, a platform becomes free
            else {
                platformsNeeded--;
                j++;
            }
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        MinimumPlatforms solution = new MinimumPlatforms();

        int[] arr1 = {900, 940, 950, 1100, 1500, 1800};
        int[] dep1 = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Test 1: " + solution.findPlatform(arr1, dep1)); 
        // Expected: 3

        int[] arr2 = {900, 1100, 1235};
        int[] dep2 = {1000, 1200, 1240};
        System.out.println("Test 2: " + solution.findPlatform(arr2, dep2)); 
        // Expected: 1 (trains do not overlap)

        int[] arr3 = {100, 200, 300};
        int[] dep3 = {400, 400, 400};
        System.out.println("Test 3: " + solution.findPlatform(arr3, dep3)); 
        // Expected: 3 (all 3 trains stay simultaneously)
    }
}
