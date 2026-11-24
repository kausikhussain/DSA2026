package Practice;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 84: Largest Rectangle in Histogram
 * Difficulty: Hard
 * 
 * Given an array of integers heights representing the histogram's bar height 
 * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * 
 * Approach:
 * - Monotonic Increasing Stack (Storing Bar Indices).
 * - Maintain a stack of bar indices in strictly non-decreasing order of heights.
 * - When current bar height is smaller than the height at stack.peek(), the bar at 
 *   stack.peek() cannot extend further to the right:
 *   - Pop the bar index and record its height.
 *   - Determine width:
 *     - If the stack is now empty, this popped bar was the shortest bar seen so far, 
 *       so it can extend from index 0 all the way to index i - 1 (width = i).
 *     - If the stack is not empty, the left boundary is determined by stack.peek(), 
 *       giving width = i - stack.peek() - 1.
 *   - Update maxArea = max(maxArea, height * width).
 * - A sentinel value of height 0 at virtual index n ensures all remaining bars 
 *   in the stack are popped and processed.
 * 
 * Complexity:
 * - Time: O(n), where n is heights.length. Each index is pushed and popped at most once.
 * - Space: O(n) auxiliary space for the monotonic stack.
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Sentinel height 0 at index n forces stack flushing
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();

        // Test 1: Standard case
        // heights = [2, 1, 5, 6, 2, 3] -> largest rectangle is formed by bars [5, 6] with area 5 * 2 = 10
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println("Test 1: " + solution.largestRectangleArea(heights1)); 
        // Expected: 10

        // Test 2: Two bars
        // heights = [2, 4] -> largest is bar [4] with area 4 * 1 = 4
        int[] heights2 = {2, 4};
        System.out.println("Test 2: " + solution.largestRectangleArea(heights2)); 
        // Expected: 4

        // Test 3: Single bar
        int[] heights3 = {1};
        System.out.println("Test 3: " + solution.largestRectangleArea(heights3)); 
        // Expected: 1

        // Test 4: Valley shape
        // heights = [2, 1, 2] -> 1 * 3 = 3
        int[] heights4 = {2, 1, 2};
        System.out.println("Test 4: " + solution.largestRectangleArea(heights4)); 
        // Expected: 3

        // Test 5: Strictly increasing
        // heights = [1, 2, 3, 4, 5] -> 3 * 3 = 9
        int[] heights5 = {1, 2, 3, 4, 5};
        System.out.println("Test 5: " + solution.largestRectangleArea(heights5)); 
        // Expected: 9
    }
}
