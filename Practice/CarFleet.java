package Practice;

import java.util.Arrays;

public class CarFleet {
    /**
     * LeetCode 853: Car Fleet
     * 
     * There are n cars at given positions traveling towards a destination at mile target.
     * Given position[] and speed[], return the number of car fleets that arrive at the destination.
     */
    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || position.length == 0) {
            return 0;
        }

        int n = position.length;
        // Pair: [position, speed]
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Sort cars by position in descending order (closest to destination first)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        int fleets = 0;
        double maxTime = 0.0;

        for (int i = 0; i < n; i++) {
            // Calculate time needed for car i to reach destination
            double time = (target - cars[i][0]) / cars[i][1];

            // If this car takes strictly longer than the fleet ahead, it cannot catch up
            // and therefore forms a brand new fleet leader.
            if (time > maxTime) {
                fleets++;
                maxTime = time;
            }
            // Otherwise, time <= maxTime: this car catches up to the fleet ahead and merges
        }

        return fleets;
    }

    public static void main(String[] args) {
        CarFleet solution = new CarFleet();

        // Test 1: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
        int[] pos1 = {10, 8, 0, 5, 3};
        int[] speed1 = {2, 4, 1, 1, 3};
        System.out.println("Test 1: " + solution.carFleet(12, pos1, speed1)); 
        // Expected: 3

        // Test 2: target = 10, position = [3], speed = [3]
        int[] pos2 = {3};
        int[] speed2 = {3};
        System.out.println("Test 2: " + solution.carFleet(10, pos2, speed2)); 
        // Expected: 1

        // Test 3: target = 100, position = [0,2,4], speed = [4,2,1]
        int[] pos3 = {0, 2, 4};
        int[] speed3 = {4, 2, 1};
        System.out.println("Test 3: " + solution.carFleet(100, pos3, speed3)); 
        // Expected: 1
    }
}
