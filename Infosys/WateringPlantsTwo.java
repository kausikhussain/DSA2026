package Infosys;

public class WateringPlantsTwo {
    /**
     * LeetCode 2105: Watering Plants II (Infosys)
     * 
     * Alice and Bob want to water n plants in a row. The plants are labeled from 0 to n - 1 
     * from left to right.
     * 
     * Alice starts at plant 0 (moving right) and Bob starts at plant n - 1 (moving left). 
     * They meet in the middle.
     * 
     * - Alice's can capacity is capacityA, Bob's is capacityB.
     * - If they need to refill their watering can, they do so instantly (costs 0 steps/time).
     * - If Alice and Bob reach the same plant, the one with more water in their can should water it. 
     *   If they have the same amount of water, Alice should water it.
     * 
     * Return the number of times they needed to refill their watering cans.
     */
    public int minimumRefills(int[] plants, int capacityA, int capacityB) {
        int refills = 0;
        int left = 0;
        int right = plants.length - 1;
        
        int waterA = capacityA;
        int waterB = capacityB;
        
        while (left < right) {
            // Alice waters plant at 'left'
            if (waterA < plants[left]) {
                refills++;
                waterA = capacityA;
            }
            waterA -= plants[left];
            left++;
            
            // Bob waters plant at 'right'
            if (waterB < plants[right]) {
                refills++;
                waterB = capacityB;
            }
            waterB -= plants[right];
            right--;
        }
        
        // If they meet at the exact same plant (odd number of plants)
        if (left == right) {
            // Compare who has more water
            if (Math.max(waterA, waterB) < plants[left]) {
                refills++;
            }
        }
        
        return refills;
    }

    public static void main(String[] args) {
        WateringPlantsTwo solution = new WateringPlantsTwo();
        
        // Test case 1
        int[] plants1 = {2, 2, 3, 3};
        int capA1 = 5, capB1 = 5;
        System.out.println("Test 1: " + solution.minimumRefills(plants1, capA1, capB1)); // Expected: 1
        
        // Test case 2
        int[] plants2 = {2, 2, 3, 3};
        int capA2 = 3, capB2 = 4;
        System.out.println("Test 2: " + solution.minimumRefills(plants2, capA2, capB2)); // Expected: 2
        
        // Test case 3
        int[] plants3 = {5};
        int capA3 = 10, capB3 = 8;
        System.out.println("Test 3: " + solution.minimumRefills(plants3, capA3, capB3)); // Expected: 0
    }
}
