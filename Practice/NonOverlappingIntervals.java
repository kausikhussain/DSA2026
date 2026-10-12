package Practice;

import java.util.Arrays;

public class NonOverlappingIntervals {
    /**
     * LeetCode 435: Non-overlapping Intervals
     * 
     * Given an array of intervals where intervals[i] = [start_i, end_i], return the minimum 
     * number of intervals you need to remove to make the rest of the intervals non-overlapping.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        // Sort intervals by start time ascending
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int removals = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            // Overlap detected: current interval starts before previous interval ends
            if (intervals[i][0] < prevEnd) {
                removals++;
                // Greedily keep the interval with the smaller end time to leave 
                // the most room for subsequent intervals
                prevEnd = Math.min(prevEnd, intervals[i][1]);
            } else {
                // No overlap, update prevEnd to current interval's end
                prevEnd = intervals[i][1];
            }
        }

        return removals;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals solution = new NonOverlappingIntervals();

        // Test 1: [[1, 2], [2, 3], [3, 4], [1, 3]]
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Test 1: " + solution.eraseOverlapIntervals(intervals1)); 
        // Expected: 1 (Remove [1, 3])

        // Test 2: [[1, 2], [1, 2], [1, 2]]
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Test 2: " + solution.eraseOverlapIntervals(intervals2)); 
        // Expected: 2 (Remove two [1, 2])

        // Test 3: [[1, 2], [2, 3]]
        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println("Test 3: " + solution.eraseOverlapIntervals(intervals3)); 
        // Expected: 0 (Already non-overlapping)
    }
}
