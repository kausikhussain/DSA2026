package Practice;

public class GasStation {
    /**
     * LeetCode 134: Gas Station
     * 
     * There are n gas stations along a circular route, where the amount of gas 
     * at the i-th station is gas[i].
     * 
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel 
     * from the i-th station to its next (i + 1)-th station. You begin the journey 
     * with an empty tank at one of the gas stations.
     * 
     * Given two integer arrays gas and cost, return the starting gas station's index 
     * if you can travel around the circuit once in the clockwise direction, otherwise return -1. 
     * If there exists a solution, it is guaranteed to be unique.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }

        int totalSurplus = 0;
        int currentTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            totalSurplus += netGas;
            currentTank += netGas;

            // If currentTank drops below zero, we cannot reach station i + 1
            // from any starting point between startStation and i.
            // Hence, the next candidate starting point must be i + 1.
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0; // Reset tank for the new journey candidate
            }
        }

        // If the overall gas across the entire circuit is less than the total cost,
        // it is impossible to complete the full circuit from any station.
        return totalSurplus >= 0 ? startStation : -1;
    }

    public static void main(String[] args) {
        GasStation solution = new GasStation();

        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1: " + solution.canCompleteCircuit(gas1, cost1)); 
        // Expected: 3 (Starting at station 3 allows full circuit)

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test 2: " + solution.canCompleteCircuit(gas2, cost2)); 
        // Expected: -1 (Total gas 9 < total cost 10)

        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        System.out.println("Test 3: " + solution.canCompleteCircuit(gas3, cost3)); 
        // Expected: 4
    }
}
