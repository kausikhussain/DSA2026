package Infosys;

import java.util.Scanner;

public class WateringPlants {
    /**
     * Question: Watering Plants (Infosys)
     * 
     * Determine the total number of steps a robot must take to water all the plants 
     * sequentially from left to right.
     * 
     * Math Logic:
     * - Starting at -1 with capacity C.
     * - At index i (0-based):
     *   - If we have enough water: steps += 1, remainingWater -= water[i].
     *   - If we do not have enough water: steps += (2 * i) + 1, remainingWater = C - water[i].
     */
    public static int wateringPlants(int[] plants, int capacity) {
        int steps = 0;
        int remainingWater = capacity;
        
        for (int i = 0; i < plants.length; i++) {
            if (remainingWater >= plants[i]) {
                // Enough water in the can to water the current plant
                steps += 1;
                remainingWater -= plants[i];
            } else {
                // Insufficient water. Must go back to water source (-1), refill, and return to plant i
                // Distance from i-1 to -1 is i steps.
                // Distance from -1 to i is i + 1 steps.
                // Total travel steps = i + (i + 1) = 2 * i + 1 steps.
                steps += (2 * i) + 1;
                remainingWater = capacity - plants[i];
            }
        }
        
        return steps;
    }

    public static void main(String[] args) {
        // Test cases
        int[] plants1 = {2, 2, 3, 3};
        int capacity1 = 5;
        System.out.println("Test 1: " + wateringPlants(plants1, capacity1)); // Expected: 14

        int[] plants2 = {1, 1, 1, 4, 2, 3};
        int capacity2 = 4;
        System.out.println("Test 2: " + wateringPlants(plants2, capacity2)); // Expected: 30

        int[] plants3 = {7, 7, 7, 7, 7, 7, 7};
        int capacity3 = 8;
        System.out.println("Test 3: " + wateringPlants(plants3, capacity3)); // Expected: 49
    }
}
