package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 57: Insert Interval
 * Difficulty: Medium
 * 
 * You are given an array of non-overlapping intervals `intervals` where 
 * intervals[i] = [start_i, end_i] represent the start and the end of the ith interval 
 * and intervals is sorted in ascending order by start_i. You are also given an 
 * interval newInterval = [start, end] that represents the start and end of another interval.
 * 
 * Insert newInterval into intervals such that intervals is still sorted in ascending 
 * order by start_i and intervals still does not have any overlapping intervals 
 * (merge overlapping intervals if necessary).
 * 
 * Return intervals after the insertion.
 * 
 * Approach:
 * - Single-pass linear scan with three phases:
 *   1. Left non-overlapping: Add all intervals ending before newInterval starts.
 *   2. Overlapping: Merge all intervals that overlap with newInterval by updating
 *      newInterval's start and end boundaries (min start, max end).
 *   3. Right non-overlapping: Add the merged newInterval, then append all remaining
 *      intervals that start after newInterval ends.
 * 
 * Complexity:
 * - Time: O(n), where n is the number of intervals (single pass).
 * - Space: O(n) for the output array.
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Step 1: Add all intervals that come strictly before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add all intervals that come strictly after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();

        // Test 1: Standard merge in middle
        // intervals = [[1,3],[6,9]], newInterval = [2,5] -> [[1,5],[6,9]]
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println("Test 1: " + Arrays.deepToString(solution.insert(intervals1, newInterval1)));
        // Expected: [[1, 5], [6, 9]]

        // Test 2: Multi-interval overlap
        // intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] -> [[1,2],[3,10],[12,16]]
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        System.out.println("Test 2: " + Arrays.deepToString(solution.insert(intervals2, newInterval2)));
        // Expected: [[1, 2], [3, 10], [12, 16]]

        // Test 3: Insert into empty intervals
        int[][] intervals3 = {};
        int[] newInterval3 = {5, 7};
        System.out.println("Test 3: " + Arrays.deepToString(solution.insert(intervals3, newInterval3)));
        // Expected: [[5, 7]]

        // Test 4: Insert at beginning without overlap
        int[][] intervals4 = {{3, 5}, {7, 9}};
        int[] newInterval4 = {1, 2};
        System.out.println("Test 4: " + Arrays.deepToString(solution.insert(intervals4, newInterval4)));
        // Expected: [[1, 2], [3, 5], [7, 9]]
    }
}
