package Practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {
    /**
     * LeetCode 739: Daily Temperatures
     * 
     * Given an array of integers temperatures represents the daily temperatures, 
     * return an array answer such that answer[i] is the number of days you have to wait 
     * after the ith day to get a warmer temperature. 
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];

        // Monotonic decreasing stack storing indices of days
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // While current temperature is warmer than the temperature at the index on top of stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                answer[prevDay] = i - prevDay; // Days waited = current index - previous index
            }
            stack.push(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();

        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Test 1: " + Arrays.toString(solution.dailyTemperatures(temperatures1)));
        // Expected: [1, 1, 4, 2, 1, 1, 0, 0]

        int[] temperatures2 = {30, 40, 50, 60};
        System.out.println("Test 2: " + Arrays.toString(solution.dailyTemperatures(temperatures2)));
        // Expected: [1, 1, 1, 0]

        int[] temperatures3 = {30, 60, 90};
        System.out.println("Test 3: " + Arrays.toString(solution.dailyTemperatures(temperatures3)));
        // Expected: [1, 1, 0]
    }
}
