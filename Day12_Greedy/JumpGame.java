package Day12_Greedy;

public class JumpGame {
    public boolean canJump(int[] nums) {
        // The goal is to reach the last index
        int goal = nums.length - 1;
        
        // Work backwards: see if we can reach the current goal from the previous step
        for (int i = nums.length - 2; i >= 0; i--) {
            // If the current position plus its maximum jump length can reach or surpass the goal,
            // we shift our goal closer to the start
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        
        // If the goal reaches the starting index (0), it means a path exists
        return goal == 0;
    }
}
