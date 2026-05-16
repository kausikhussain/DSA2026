package Day10_1D_DP;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // We only need to track the max loot from the last two houses
        int rob1 = 0; // Represents max loot up to the house BEFORE the previous one
        int rob2 = 0; // Represents max loot up to the previous house
        
        for (int n : nums) {
            // max(rob current house + rob1, don't rob current house and keep rob2)
            int currentMax = Math.max(n + rob1, rob2);
            rob1 = rob2;
            rob2 = currentMax;
        }
        
        return rob2;
    }
}
